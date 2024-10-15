
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


    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        int result1 = calculator.add(10, 5);
        System.out.println("10 + 5 = " + result1);

        int result2 = calculator.subtract(10, 5);
        System.out.println("10 - 5 = " + result2);

        // 调用乘法方法
        int result3 = calculator.multiply(10, 5);
        System.out.println("10 * 5 = " + result3);
        
    }
}

