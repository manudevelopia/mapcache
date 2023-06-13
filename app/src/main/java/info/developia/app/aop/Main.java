package info.developia.app.aop;

public class Main {
    public static void main(String[] args) {
        // Create the target object
        Calculator calculator = new CalculatorImpl();

        // Apply the aspect and get the proxy object
        Calculator proxyCalculator = LoggingAspect.createProxy(calculator);

        // Use the proxy object
        int result = proxyCalculator.add(2, 3);
        System.out.println("Result: " + result);
    }
}