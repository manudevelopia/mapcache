package info.developia.lib.mapcache.cache;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CacheFillerScheduled<K, V> extends CacheFiller<K, V> {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final ExecutorService taskExecutor = Executors.newVirtualThreadPerTaskExecutor();

    public CacheFillerScheduled(int maxSize,
                                Supplier<Map<K, V>> filler,
                                Duration refillPeriod) {
        super(maxSize, filler);
        schedule(super::fill, refillPeriod);
    }

    private void schedule(Runnable command, Duration delay) {
        scheduler.schedule(() -> taskExecutor.execute(command), delay.toMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void close() {
        super.close();
        scheduler.shutdown();
    }
}
