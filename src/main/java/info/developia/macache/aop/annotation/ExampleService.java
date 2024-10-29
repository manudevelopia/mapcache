package info.developia.macache.aop.annotation;

import java.lang.reflect.Method;

public class ExampleService {
    private CacheAspect cacheAspect;

    public ExampleService() {
        this.cacheAspect = new CacheAspect();
    }

    @Cacheable
    public String getDataFromDatabase(String query) {
        // Simulate some expensive operation
        System.out.println("Executing database query...");
        return "Result of database query for: " + query;
    }

    public String getDataFromDatabaseWithCache(String query) throws Throwable {
        Method method = this.getClass().getMethod("getDataFromDatabase", String.class);
        return (String) cacheAspect.intercept(this, method, new Object[]{query});
    }

    public static void main(String[] args) throws Throwable {
        ExampleService service = new ExampleService();

        // First call - actual method execution
        System.out.println(service.getDataFromDatabase("query1"));

        // Second call with the cache - returns the cached result
        System.out.println(service.getDataFromDatabaseWithCache("query1"));

        System.out.println(service.getDataFromDatabaseWithCache("query1"));
    }
}

