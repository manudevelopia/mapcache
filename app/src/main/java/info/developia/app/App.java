package info.developia.app;

import info.developia.macache.Macache;

public class App {

    public static void main(String[] args) {
        var cache = new Macache<String, String>();
        cache.put("key", "value");
        cache.get("key");
        cache.size();
    }
}
