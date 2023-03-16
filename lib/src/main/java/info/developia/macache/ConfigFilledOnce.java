package info.developia.macache;

import java.util.Map;
import java.util.function.Supplier;

public class ConfigFilledOnce<K, V> extends Config<K, V> {
    private final Supplier<Map<K, V>> filler;

    public ConfigFilledOnce(Supplier<Map<K, V>> filler) {
        this.filler = filler;
    }

    public ConfigFilledOnce<K, V> maxSize(long maxSize) {
        super.setMaxSize(maxSize);
        return this;
    }

    public Cache<K, V> build() {
        return new CacheFilledOnce<>(filler);
    }
}
