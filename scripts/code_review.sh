#!/bin/sh

# 从环境变量读取API密钥
apiKeySecret="${API_KEY_SECRET:-}"
if [ -z "$apiKeySecret" ]; then
  echo "Error: API_KEY_SECRET is not set. Please set the API key as an environment variable."
  exit 1
fi

# 定义API URL
url="https://open.bigmodel.cn/api/paas/v4/chat/completions"

# 生成时间戳和文件名
timestamp=$(date +%Y%m%d%H%M%S)
originalFilename="codeReviewLog/originalFilename_$timestamp.json"
processedFilename="codeReviewLog/codeReviewResult_$timestamp.md"

# 确保目录存在
directory="codeReviewLog"
if [ ! -d "$directory" ]; then
  echo "Directory $directory does not exist. Creating..."
  mkdir -p "$directory"
fi

# 获取暂存区的代码差异和提交信息
changes=$(git diff --cached)
commitHash=$(git rev-parse HEAD)
author=$(git log -1 --pretty=format:'%an <%ae>')

if [ -z "$changes" ]; then
  echo "No changes found in the current commit."
  exit 0
fi

# 打印提交信息和代码差异
echo "Commit: $commitHash"
echo "Author: $author"
echo "提交内容："
echo "$changes"

# 转义换行符和引号
changes=$(echo "$changes" | tr '\n' ' ' | sed 's/\\n$//' | sed 's/\\$//')
changes=$(echo "$changes" | sed 's/"/\\"/g; s/\n/\\n/g')

# 构建请求体
jsonInputString=$(cat <<EOF
{
    "model": "glm-4-flash",
    "messages": [
        {
            "role": "user",
            "content": "你是一位资深的编程架构师，精通架构设计、最佳实践、以及各种编程语言。请根据以下git diff记录，对代码进行全面评审，重点关注以下几点：1. 代码是否符合最佳实践和设计模式？2. 是否存在潜在的bug或安全隐患？3. 代码的可读性和可维护性如何？4. 是否有需要优化的地方，如性能或逻辑简化？代码变更如下：: $changes"
        }
    ]
}
EOF
)

# 发送HTTP POST请求并保存原始响应到文件
response=$(curl -s -o "$originalFilename" -w "%{http_code}" -X POST \
  -H "Authorization: Bearer $apiKeySecret" \
  -H "Content-Type: application/json" \
  -H "User-Agent: Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)" \
  -d "$jsonInputString" \
  "${url}")

# 打印HTTP响应码
echo "Response Code: $response"

# 响应码处理
if [ "$response" -eq 200 ]; then
  echo "Processing response and saving to processed file..."

  # 提取评审内容并保存为Markdown格式
  jq -r '.choices[0].message.content' "$originalFilename" | while IFS= read -r line; do
    echo "${line%\"}" | sed 's/^"//; s/"$//' >> "$processedFilename"
  done

  # 增强评审结果，添加提交信息到日志文件
  echo -e "\n---\n" >> "$processedFilename"
  echo "**Commit Hash**: $commitHash" >> "$processedFilename"
  echo "**Author**: $author" >> "$processedFilename"
  echo "**Timestamp**: $(date)" >> "$processedFilename"

  echo "Processed data saved to $processedFilename"

  # 打印生成的Markdown文件内容到控制台
  echo "Generated Markdown file content:"
  cat "$processedFilename"

  # 检查处理后的文件是否有效
  if [ -s "$processedFilename" ] && [ "$(cat "$processedFilename")" = 'null' ]; then
    echo "本次没有评审结果"
  else
    echo "本次有评审结果"
    rm "$originalFilename"  # 清理原始文件
  fi

elif [ "$response" -eq 401 ]; then
  echo "Authentication failed. Please check your API key."
elif [ "$response" -eq 500 ]; then
  echo "Server error. Please try again later."
else
  echo "An unexpected error occurred. Response Code: $response"
  cat "$originalFilename"  # 打印原始响应以便调试
fi
