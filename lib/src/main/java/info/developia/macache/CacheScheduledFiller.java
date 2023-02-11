package info.developia.macache;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class CacheScheduledFiller<K, V> extends CacheBasic<K, V> {
    private final Supplier<Map<K, V>> filler;

    public CacheScheduledFiller(Supplier<Map<K, V>> filler, Duration refillPeriod) {
        this.filler = filler;
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(this::fillCache, 0, refillPeriod.toMillis(), MILLISECONDS);
    }

    private void fillCache() {
        data.putAll(this.filler.get());
    }
}
