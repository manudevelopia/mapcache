package info.developia.lib.mapcache.task;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class KeyExpireRegistry<K> {
    private final Map<K, Long> keyTimestamp = new LinkedHashMap<>();
    private final TaskManager taskManager = TaskManager.getInstance();
    private final long expirePeriodInMillis;

    public KeyExpireRegistry(Duration expirePeriod, Consumer<K> cacheDelete) {
        expirePeriodInMillis = expirePeriod.toMillis();
        taskManager.schedule(() -> delExpiredKeys(cacheDelete), expirePeriod);
    }

    private void delExpiredKeys(Consumer<K> cacheDelete) {
        long now = System.currentTimeMillis();
        keyTimestamp.entrySet().stream()
                .takeWhile((entry -> entry.getValue() < now))
                .forEach(entry -> cacheDelete.accept(entry.getKey()));
    }

    public void put(K key) {
        keyTimestamp.put(key, System.currentTimeMillis() + expirePeriodInMillis);
    }

    public boolean containsKey(K key) {
        return keyTimestamp.get(key) > System.currentTimeMillis();
    }

    public void remove(K key) {
        keyTimestamp.remove(key);
    }

    public void clear() {
        keyTimestamp.clear();
    }

    public void close() {
        taskManager.close();
    }
}
