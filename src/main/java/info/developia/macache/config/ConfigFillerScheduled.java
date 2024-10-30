package info.developia.macache.config;

import info.developia.macache.cache.CacheFeatures;
import info.developia.macache.cache.CacheFeaturesFillerScheduled;

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

    public ConfigFillerScheduled<K, V> maxSize(int maxSize) {
        super.maxSize(maxSize);
        return this;
    }

    public CacheFeatures<K, V> build() {
        return new CacheFeaturesFillerScheduled<>(filler, refillPeriod);
    }
}
