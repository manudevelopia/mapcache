package info.developia.lib.mapcache.config;

import info.developia.lib.mapcache.Cache;
import info.developia.lib.mapcache.cache.CacheBasic;
import info.developia.lib.mapcache.cache.CacheExpire;
import info.developia.lib.mapcache.cache.CacheFilled;
import info.developia.lib.mapcache.cache.CacheFillerExpire;
import info.developia.lib.mapcache.cache.CacheFilledScheduled;
import info.developia.lib.mapcache.cache.CacheFilledScheduledExpire;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class CacheFactory<K, V> {
    private int maxSize = 10000;
    private Supplier<Map<K, V>> filler;
    private Duration refillPeriod = Duration.ZERO, expirePeriod = Duration.ZERO;

    public Cache<K, V> build() {
        if (filler != null && refillPeriod != Duration.ZERO && expirePeriod != Duration.ZERO) {
            return new CacheFilledScheduledExpire<>(maxSize, filler, refillPeriod, expirePeriod);
        }
        if (filler != null && refillPeriod != Duration.ZERO) {
            return new CacheFilledScheduled<>(maxSize, filler, refillPeriod);
        }
        if (filler != null && expirePeriod != Duration.ZERO) {
            return new CacheFillerExpire<>(maxSize, filler, expirePeriod);
        }
        if (filler != null) {
            return new CacheFilled<>(maxSize, filler);
        }
        if (expirePeriod != Duration.ZERO) {
            return new CacheExpire<>(maxSize, expirePeriod);
        }
        return new CacheBasic<>(maxSize);
    }

    public void withMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void withFiller(Supplier<Map<K, V>> filler) {
        this.filler = filler;
    }

    public void withRefillPeriod(Duration refillPeriod) {
        this.refillPeriod = refillPeriod;
    }

    public void withExpirePeriod(Duration expirePeriod) {
        this.expirePeriod = expirePeriod;
    }
}
