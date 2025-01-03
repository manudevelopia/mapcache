package info.developia.lib.mapcache.cache;

import info.developia.lib.mapcache.task.CacheFiller;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class CacheExpireScheduledFilled<K, V> extends CacheExpire<K, V> {
    private final CacheFiller<K, V> cacheFiller;

    public CacheExpireScheduledFilled(int maxSize,
                                      Supplier<Map<K, V>> filler,
                                      Duration refillPeriod,
                                      Duration expirePeriod) {
        super(maxSize, expirePeriod);
        cacheFiller = new CacheFiller<>(filler, refillPeriod, this::put);
    }

    @Override
    public void close() {
        cacheFiller.close();
        super.close();
    }
}
