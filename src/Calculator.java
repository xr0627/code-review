
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
    public int divide(int a, int b) {
        if (b == 0) {
            System.out.println("Error: Division by zero is not allowed.");
            return 0;  // 返回 0 作为默认值以避免崩溃
        }
        return a / b;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        int result1 = calculator.add(10, 5);
        System.out.println("10 + 5 = " + result1);

        int result2 = calculator.subtract(10, 5);
        System.out.println("10 - 5 = " + result2);

        // 调用乘法方法
        int result3 = calculator.multiply(10, 5);
        System.out.println("10 * 5 = " + result3);

        // 调用除法方法
        int result4 = calculator.divide(10, 5);
        System.out.println("10 / 5 = " + result4);

        // 测试除以零的情况
        int result5 = calculator.divide(10, 0);
        System.out.println("10 / 0 = " + result5);
    }
}

