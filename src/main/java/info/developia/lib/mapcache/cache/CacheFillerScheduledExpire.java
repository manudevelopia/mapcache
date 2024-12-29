package info.developia.lib.mapcache.cache;

import info.developia.lib.mapcache.task.TaskManager;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CacheFillerScheduledExpire<K, V> extends CacheFillerScheduled<K, V> {
    private final Map<K, Long> keyTimestamp = new LinkedHashMap<>();
    private final TaskManager taskManager =  TaskManager.getInstance();
    private final long expirePeriodInMillis;

    public CacheFillerScheduledExpire(int maxSize,
                                      Supplier<Map<K, V>> filler,
                                      Duration refillPeriod,
                                      Duration expirePeriod) {
        super(maxSize, filler, refillPeriod);
        taskManager.schedule(this::delExpiredKeys, expirePeriod);
        expirePeriodInMillis = expirePeriod.toMillis();
    }

    private void delExpiredKeys() {
        long now = System.currentTimeMillis();
        keyTimestamp.entrySet().stream().takeWhile((entry -> entry.getValue() < now))
                .forEach(entry -> del(entry.getKey()));
    }

    @Override
    public void put(K key, V value) {
        super.put(key, value);
        keyTimestamp.put(key, System.currentTimeMillis() + expirePeriodInMillis);
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
        taskManager.close();
    }
}
