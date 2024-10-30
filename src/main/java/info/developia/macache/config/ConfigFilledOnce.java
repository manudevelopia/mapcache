package info.developia.macache.config;

import info.developia.macache.cache.CacheFeatures;
import info.developia.macache.cache.CacheFeaturesFilledOnce;

import java.util.Map;
import java.util.function.Supplier;

public class ConfigFilledOnce<K, V> extends Config<K, V> {
    private final Supplier<Map<K, V>> filler;

    public ConfigFilledOnce(Supplier<Map<K, V>> filler) {
        this.filler = filler;
    }

    public ConfigFilledOnce<K, V> maxSize(int maxSize) {
        super.maxSize(maxSize);
        return this;
    }

    public CacheFeatures<K, V> build() {
        return new CacheFeaturesFilledOnce<>(filler);
    }
}
