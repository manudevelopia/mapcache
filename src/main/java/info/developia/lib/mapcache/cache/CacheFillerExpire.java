package info.developia.lib.mapcache.cache;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class CacheFillerExpire<K, V> extends CacheFiller<K, V> {
    private Duration expirePeriod;

    public CacheFillerExpire(int maxSize,
                             Supplier<Map<K, V>> filler,
                             Duration expirePeriod) {
        super(maxSize, filler);
        this.expirePeriod = expirePeriod;
    }
}
