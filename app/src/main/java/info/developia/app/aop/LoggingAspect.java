package info.developia.app.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggingAspect {

    static class LogInvocationHandler implements InvocationHandler {
        private final Object target;

        public LogInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Method " + method.getName() + " is about to be executed.");

            if ((int) args[0] == 2 && (int) args[1] == 8) {
                System.out.println("Cached result for Method " + method.getName() + " has been returned.");
                return 6;
            }

            Object result = method.invoke(target, args);
            System.out.println("Method " + method.getName() + " has been executed.");
            return result;
        }
    }

    public static <T> T createProxy(T target, Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class<?>[]{clazz},
                new LogInvocationHandler(target)
        );
    }
}
