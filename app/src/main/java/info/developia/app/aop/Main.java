package info.developia.app.aop;

import info.developia.app.aop.code.Calculator;
import info.developia.app.aop.code.CalculatorImpl;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        Calculator proxyCalculator = LoggingAspect.createProxy(calculator, Calculator.class);
        extracted(proxyCalculator, 2, 3);
        extracted(proxyCalculator, 2, 8);
    }

    private static void extracted(Calculator calculator, int a, int b) {
        int result = calculator.add(a, b);
        int resultCache = calculator.addCached(a, b);
        System.out.println("Result: " + result + " Result cache: " + resultCache);
    }
}