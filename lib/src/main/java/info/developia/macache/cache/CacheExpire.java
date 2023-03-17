package info.developia.macache.cache;

import info.developia.macache.task.TaskManager;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

class CacheExpire<K, V> extends CacheBasic<K, V> {
    private final Map<K, Long> keyTimestamp = new LinkedHashMap<>();
    private final long cacheValidPeriodInMillis;
    private final TaskManager taskManager = new TaskManager();

    public CacheExpire(Duration cacheValidPeriod) {
        cacheValidPeriodInMillis = cacheValidPeriod.toMillis();
        taskManager.schedule(this::delExpiredKeys, cacheValidPeriodInMillis);
    }

    private void delExpiredKeys() {
        long now = System.currentTimeMillis();
        keyTimestamp.entrySet().stream().takeWhile((entry -> entry.getValue() < now))
                .forEach(entry -> del(entry.getKey()));
    }

    @Override
    public void put(K key, V value) {
        super.put(key, value);
        keyTimestamp.put(key, System.currentTimeMillis() + cacheValidPeriodInMillis);
    }

    @Override
    public V get(K key) {
        if (key == null || !keyTimestamp.containsKey(key)) return null;
        if (keyTimestamp.get(key) > System.currentTimeMillis()) {
            return super.get(key);
        }
        return null;
    }

    @Override
    public void del(K key) {
        super.del(key);
        keyTimestamp.remove(key);
    }

    @Override
    public void clear() {
        super.clear();
        keyTimestamp.clear();
    }

    public void close() {
        super.close();
        taskManager.shutdown();
    }
}
