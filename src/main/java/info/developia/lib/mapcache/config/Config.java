package info.developia.lib.mapcache.config;

import info.developia.lib.mapcache.Cache;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class Config<K, V> {
    private final CacheFactory<K, V> cacheFactory = new CacheFactory<>();

    public Cache<K, V> cache() {
        return cacheFactory.build();
    }

    public Config<K, V> maxSize(int maxSize) {
        cacheFactory.withMaxSize(maxSize);
        return this;
    }

    public Config<K, V> filler(Supplier<Map<K, V>> filler) {
        cacheFactory.withFiller(filler);
        return this;
    }

    public Config<K, V> every(Duration refillPeriod) {
        cacheFactory.withRefillPeriod(refillPeriod);
        return this;
    }

    public Config<K, V> expireIn(Duration expirePeriod) {
        cacheFactory.withExpirePeriod(expirePeriod);
        return this;
    }
}
