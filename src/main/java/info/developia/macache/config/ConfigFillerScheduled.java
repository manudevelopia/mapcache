package info.developia.macache.config;

import info.developia.macache.Cache;
import info.developia.macache.cache.CacheFillerScheduled;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class ConfigFillerScheduled<K, V> extends Config<K, V> {
    private final Supplier<Map<K, V>> filler;
    private final Duration refillPeriod;

    public ConfigFillerScheduled(Supplier<Map<K, V>> filler, Duration refillPeriod) {
        this.filler = filler;
        this.refillPeriod = refillPeriod;
    }

    public ConfigFillerScheduled<K, V> maxSize(long maxSize) {
        super.setMaxSize(maxSize);
        return this;
    }

    public Cache<K, V> build() {
        return new CacheFillerScheduled<>(filler, refillPeriod);
    }
}
