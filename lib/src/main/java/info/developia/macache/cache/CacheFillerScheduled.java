package info.developia.macache.cache;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

class CacheFillerScheduled<K, V> extends CacheBasic<K, V> {
    private final Supplier<Map<K, V>> filler;

    public CacheFillerScheduled(Supplier<Map<K, V>> filler, Duration refillPeriod) {
        this.filler = filler;
        long refillPeriodAsMillis = refillPeriod.toMillis();
        fillCache();
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(this::fillCache, refillPeriodAsMillis, refillPeriodAsMillis, MILLISECONDS);
    }

    private void fillCache() {
        data.putAll(this.filler.get());
    }
}
