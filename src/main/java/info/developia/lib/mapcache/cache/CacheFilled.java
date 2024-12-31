package info.developia.lib.mapcache.cache;

import info.developia.lib.mapcache.task.CacheFiller;

import java.util.Map;
import java.util.function.Supplier;

public class CacheFilled<K, V> extends CacheBasic<K, V> {
    private final CacheFiller<K, V> cacheFiller;

    public CacheFilled(int maxSize, Supplier<Map<K, V>> filler) {
        super(maxSize);
        cacheFiller = new CacheFiller<>(filler);
        fill();
    }

    public void fill() {
        cacheFiller.fill(this::put);
    }
}
