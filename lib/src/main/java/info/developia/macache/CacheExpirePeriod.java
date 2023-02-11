package info.developia.macache;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class CacheExpirePeriod<K, V> extends Cache<K, V> {
    private final List<K> order = new ArrayList<>();

    public CacheExpirePeriod(Duration cacheValidPeriod) {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(this::clear, cacheValidPeriod.toMillis(), 1, MILLISECONDS);
    }

    @Override
    public void put(K key, V value) {
        super.put(key, value);
    }

    @Override
    public V get(K key) {
        return super.get(key);
    }
}
