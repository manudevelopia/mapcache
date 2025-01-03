package info.developia.lib.mapcache.cache;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class CacheExpireFilled<K, V> extends CacheExpire<K, V> {

    public CacheExpireFilled(int maxSize,
                             Supplier<Map<K, V>> filler,
                             Duration expirePeriod) {
        super(maxSize, expirePeriod);
        filler.get().forEach(this::put);
    }
}
