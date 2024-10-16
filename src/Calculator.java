// src/Calculator.java
public class Calculator {

    // 加法 - 支持整数和浮点数
    public double add(double a, double b) {
        return a + b;
    }

    // 减法 - 支持整数和浮点数
    public double subtract(double a, double b) {
        return a - b;
    }

    // 乘法 - 支持整数和浮点数
    public double multiply(double a, double b) {
        return a * b;
    }

    // 除法 - 支持浮点数并处理除以零的情况
    public double divide(double a, double b) {
        checkDivideByZero(b);
        return a / b;
    }

    // 取模运算
    public int mod(int a, int b) {
        checkDivideByZero(b);
        return a % b;
    }

    // 私有方法用于检查除以零
    private void checkDivideByZero(double b) {
        if (b == 0) {
            throw new IllegalArgumentException("除数不能为零");
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // 测试加法
        double result1 = calculator.add(10, 5);
        System.out.println("10 + 5 = " + result1);

        // 测试减法
        double result2 = calculator.subtract(10, 5);
        System.out.println("10 - 5 = " + result2);

        // 测试乘法
        double result3 = calculator.multiply(10, 5);
        System.out.println("10 * 5 = " + result3);

        // 测试除法
        try {
            double result4 = calculator.divide(10, 5);
            System.out.println("10 / 5 = " + result4);
            // 测试除以零
            double result5 = calculator.divide(10, 0);
            System.out.println("10 / 0 = " + result5);
        } catch (IllegalArgumentException e) {
            System.out.println("错误: " + e.getMessage());
        }

        // 测试取模运算
        try {
            int result6 = calculator.mod(10, 3);
            System.out.println("10 % 3 = " + result6);
        } catch (IllegalArgumentException e) {
            System.out.println("错误: " + e.getMessage());
        }
    }
}
