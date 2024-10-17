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

    // 幂运算
    public double power(double a, double b) {
        return Math.pow(a, b);
    }

    // 平方根 - 处理负数的情况
    public double sqrt(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("不能计算负数的平方根");
        }
        return Math.sqrt(a);
    }

    // 阶乘 - 只支持非负整数
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("阶乘只支持非负整数");
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // 最大公约数
    public int gcd(int a, int b) {
        if (a == 0 || b == 0) {
            throw new IllegalArgumentException("GCD 参数不能为 0");
        }
        return gcdHelper(Math.abs(a), Math.abs(b));
    }

    // 辅助方法用于递归计算 GCD
    private int gcdHelper(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcdHelper(b, a % b);
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

        // 测试幂运算
        double result7 = calculator.power(2, 3);
        System.out.println("2 ^ 3 = " + result7);

        // 测试平方根
        try {
            double result8 = calculator.sqrt(16);
            System.out.println("√16 = " + result8);
        } catch (IllegalArgumentException e) {
            System.out.println("错误: " + e.getMessage());
        }

        // 测试阶乘
        try {
            long result9 = calculator.factorial(5);
            System.out.println("5! = " + result9);
        } catch (IllegalArgumentException e) {
            System.out.println("错误: " + e.getMessage());
        }

        // 测试最大公约数
        try {
            int result10 = calculator.gcd(48, 18);
            System.out.println("GCD of 48 and 18 = " + result10);
        } catch (IllegalArgumentException e) {
            System.out.println("错误: " + e.getMessage());
        }
    }
}
