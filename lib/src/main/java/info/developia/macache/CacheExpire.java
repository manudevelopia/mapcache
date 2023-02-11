package info.developia.macache;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class CacheExpire<K, V> extends Cache<K, V> {
    private final Map<K, Long> keyTimestamp = new LinkedHashMap<>();
    private final long cacheValidPeriodInMillis;

    public CacheExpire(Duration cacheValidPeriod) {
        this(cacheValidPeriod, 20000);
    }

    public CacheExpire(Duration cacheValidPeriod, long defaultDeletePeriod) {
        cacheValidPeriodInMillis = cacheValidPeriod.toMillis();
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(this::delExpiredKeys, this.cacheValidPeriodInMillis, defaultDeletePeriod, MILLISECONDS);
    }

    private void delExpiredKeys() {
        long now = System.currentTimeMillis();
        keyTimestamp.entrySet().stream().takeWhile((entry -> entry.getValue() < now)).forEach(entry -> del(entry.getKey()));
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
}
