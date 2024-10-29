package info.developia.macache.aop.aop;


import info.developia.macache.aop.aop.code.Calculator;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator("normal");
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