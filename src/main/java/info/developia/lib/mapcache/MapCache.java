package info.developia.lib.mapcache;

import info.developia.lib.mapcache.config.Config;

public interface MapCache {

    static <K, V> Config<K, V> config() {
        return new Config<>();
    }

    static <K, V> Cache<K, V> cache() {
        return new Config<K, V>().cache();
    }
}
