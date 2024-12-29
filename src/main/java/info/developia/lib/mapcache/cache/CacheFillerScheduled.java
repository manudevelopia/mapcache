package info.developia.lib.mapcache.cache;

import info.developia.lib.mapcache.task.TaskManager;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class CacheFillerScheduled<K, V> extends CacheFiller<K, V> {
    private final TaskManager taskManager = TaskManager.getInstance();

    public CacheFillerScheduled(int maxSize,
                                Supplier<Map<K, V>> filler,
                                Duration refillPeriod) {
        super(maxSize, filler);
        taskManager.schedule(super::fill, refillPeriod);
    }

    @Override
    public void close() {
        super.close();
        taskManager.close();
    }
}
