package info.developia.lib.mapcache.cache;

import java.util.Map;
import java.util.function.Supplier;

public class CacheFiller<K, V> extends CacheBasic<K, V> {
    private final Supplier<Map<K, V>> filler;

    public CacheFiller(int maxSize,
                       Supplier<Map<K, V>> filler) {
        super(maxSize);
        this.filler = filler;
        fill();
    }

    public void fill() {
        filler.get().forEach(this::put);
    }
}
