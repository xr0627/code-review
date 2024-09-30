# Code Review 自动化脚本

该仓库包含一个自动化的代码审查脚本，通过调用AI模型API对代码变更进行评审。脚本将检查当前的 git 提交，并根据最佳实践、潜在的 bug、代码可读性等多个维度给出建议。

## 设置步骤

1. 克隆仓库：

   ```bash
   git clone https://github.com/your-username/your-repository.git
   cd your-repository
   ```

2. 需要安装的依赖工具（例如 curl 和 jq）：

   ```bash
   sudo apt-get install curl jq   # 对于 Ubuntu 或 Debian 系统
   ```

3. 设置 API 密钥作为环境变量：[访问智谱](https://open.bigmodel.cn/usercenter/apikeys)

   ```bash
   export API_KEY_SECRET="your_api_key_here"
   ```

4. 确保脚本有执行权限：

   ```bash
   chmod +x scripts/code_review.sh
   ```

## 使用方法

在提交代码之前，可以通过运行以下命令来触发自动化代码评审：

```bash
./scripts/code_review.sh
```

脚本会检查当前的 git 提交（暂存区的改动），并生成一个代码审查报告，报告会保存到 `codeReviewLog` 目录中。

## 注意事项

- 请确保环境变量 `API_KEY_SECRET` 已正确配置。
- 脚本会生成日志文件，用于记录代码审查结果。



