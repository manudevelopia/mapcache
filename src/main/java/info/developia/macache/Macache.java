package info.developia.macache;

import info.developia.macache.config.Config;

public class Macache {

    public static <K, V> Config<K, V> cache() {
        return new Config<>();
    }
}
