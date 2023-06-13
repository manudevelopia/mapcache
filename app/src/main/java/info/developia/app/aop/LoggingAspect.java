package info.developia.app.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggingAspect {
    // Step 2: Implement the InvocationHandler
    static class LogInvocationHandler implements InvocationHandler {
        private final Object target;

        public LogInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // Before method execution
            System.out.println("Method " + method.getName() + " is about to be executed.");

            // Invoke the original method
            Object result = method.invoke(target, args);

            // After method execution
            System.out.println("Method " + method.getName() + " has been executed.");

            return result;
        }
    }

    // Step 3 and 4: Create Proxy Objects and Apply the Aspect
    public static <T> T createProxy(T target) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new LogInvocationHandler(target)
        );
    }
}
