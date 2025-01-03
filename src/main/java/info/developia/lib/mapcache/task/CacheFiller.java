package info.developia.lib.mapcache.task;

import java.time.Duration;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class CacheFiller<K, V> {
    private final Supplier<Map<K, V>> filler;
    private final TaskManager taskManager;

    public CacheFiller(Supplier<Map<K, V>> filler) {
        this.filler = filler;
        taskManager = null;
    }

    public CacheFiller(Supplier<Map<K, V>> filler,
                       Duration refillPeriod,
                       BiConsumer<K, V> put) {
        this.filler = filler;
        taskManager = TaskManager.getInstance();
        taskManager.schedule(() -> fill(put), refillPeriod);
    }

    public void fill(BiConsumer<K, V> put) {
        filler.get().forEach(put);
    }

    public void close() {
        if (taskManager != null) {
            taskManager.close();
        }
    }
}
