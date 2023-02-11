package info.developia.macache;

import java.time.Duration;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class CacheExpirePeriod<K, V> extends CacheBasic<K, V> {

    public CacheExpirePeriod(Duration cacheValidPeriod) {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(this::clear, cacheValidPeriod.toMillis(), 1, MILLISECONDS);
    }
}
