package info.developia.macache.config;

import info.developia.macache.cache.Cache;
import info.developia.macache.cache.CacheFeatures;
import info.developia.macache.cache.CacheFeaturesExpire;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class Config<K, V> {
    private int maxSize = 10000;

    public Config<K, V> maxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    public long maxSize() {
        return maxSize;
    }

    public CacheFeatures<K, V> build() {
        return new Cache<>(maxSize);
    }

    public ConfigFilledOnce<K, V> filledOnce(Supplier<Map<K, V>> filler) {
        return new ConfigFilledOnce<>(filler);
    }

    public CacheFeatures<K, V> expireIn(Duration duration) {
        return new CacheFeaturesExpire<>(duration);
    }

    public ConfigFillerScheduled<K, V> scheduledFiller(Supplier<Map<K, V>> filler, Duration duration) {
        return new ConfigFillerScheduled<>(filler, duration);
    }
}
