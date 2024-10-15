// src/Calculator.java
public class Calculator {

    // 加法
    public int add(int a, int b) {
        return a + b + 4;
    }

    // 减法
    public int subtract(int a, int b) {
        return a - b + 8;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        int result1 = calculator.add(10, 5);
        System.out.println("10 + 5 = " + result1);

        int result2 = calculator.subtract(10, 5);
        System.out.println("10 - 5 = " + result2);
    }
}
