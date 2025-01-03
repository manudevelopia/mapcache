package info.developia.lib.mapcache.cache;

import info.developia.lib.mapcache.task.CacheFiller;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class CacheScheduledFilled<K, V> extends CacheBasic<K, V> {
    private final CacheFiller<K, V> cacheFiller;

    public CacheScheduledFilled(int maxSize,
                                Supplier<Map<K, V>> filler,
                                Duration refillPeriod) {
        super(maxSize);
        cacheFiller = new CacheFiller<>(filler, refillPeriod, this::put);
    }

    @Override
    public void close() {
        super.close();
        cacheFiller.close();
    }
}
