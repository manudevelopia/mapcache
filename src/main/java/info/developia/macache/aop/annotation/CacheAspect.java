package info.developia.macache.aop.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheAspect {
    private final Map<String, Object> cache = new HashMap<>();

    public Object intercept(Object target, Method method, Object[] args) throws Throwable {
        // Generate a unique key based on method and its parameters
        String cacheKey = generateCacheKey(method, args);

        // Check if the result is already cached
        if (cache.containsKey(cacheKey)) {
            System.out.println("Returning cached result for " + cacheKey);
            return cache.get(cacheKey);
        }

        // Execute the original method
        Object result = method.invoke(target, args);

        // Cache the result
        cache.put(cacheKey, result);

        return result;
    }

    private String generateCacheKey(Method method, Object[] args) {
        return method.getName() + Arrays.toString(args);
    }
}

