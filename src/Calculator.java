// src/Calculator.java
public class Calculator {

    // 加法
    public int add(int a, int b) {
        return a + b;
    }

    // 减法
    public int subtract(int a, int b) {
        return a - b;
    }

    // 乘法
    public int multiply(int a, int b) {
        return a * b;
    }

    // 除法
    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("除数不能为零");
        }
        return (double) a / b;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // 测试加法
        int result1 = calculator.add(10, 5);
        System.out.println("10 + 5 = " + result1);

        // 测试减法
        int result2 = calculator.subtract(10, 5);
        System.out.println("10 - 5 = " + result2);

        // 测试乘法
        int result3 = calculator.multiply(10, 5);
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
    }
}
