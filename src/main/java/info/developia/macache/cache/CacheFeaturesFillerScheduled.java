package info.developia.macache.cache;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CacheFeaturesFillerScheduled<K, V> extends Cache<K, V> {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final ExecutorService taskExecutor = Executors.newVirtualThreadPerTaskExecutor();
    private final Supplier<Map<K, V>> filler;

    public CacheFeaturesFillerScheduled(Supplier<Map<K, V>> filler, Duration refillPeriod) {
        this.filler = filler;
        fillCache();
        schedule(this::fillCache, refillPeriod);
    }

    private void fillCache() {
        data.putAll(this.filler.get());
    }

    void schedule(Runnable command, Duration delay) {
        scheduler.schedule(() -> taskExecutor.execute(command), delay.toMillis(), TimeUnit.MILLISECONDS);
    }
}
