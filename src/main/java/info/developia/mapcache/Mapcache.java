package info.developia.mapcache;

import info.developia.mapcache.config.Config;

public class Mapcache {

    public static <K, V> Config<K, V> cache() {
        return new Config<>();
    }
}
