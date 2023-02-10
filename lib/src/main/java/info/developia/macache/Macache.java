package info.developia.macache;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Macache<K, V> implements AutoCloseable {
    private final Map<K, V> data = new HashMap<>();
    private final Supplier<Map<K, V>> filler;

    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = reentrantReadWriteLock.readLock();
    private final Lock writeLock = reentrantReadWriteLock.readLock();
    private final ScheduledFuture<?> scheduledFuture;
    private final ScheduledExecutorService scheduler;

    public Macache() {
        this.filler = null;
        this.scheduler = null;
        this.scheduledFuture = null;
    }

    public Macache(Supplier<Map<K, V>> filler) {
        this.filler = filler;
        this.scheduler = null;
        this.scheduledFuture = null;
        fillCache();
    }

    public Macache(Duration cacheValidPeriod){
        this.filler = null;
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.scheduledFuture = scheduler.scheduleAtFixedRate(this::invalidateCache, cacheValidPeriod.toMillis(), 1, MILLISECONDS);
    }

    public Macache(Supplier<Map<K, V>> filler, Duration refillPeriod) {
        this.filler = filler;
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.scheduledFuture = scheduler.scheduleAtFixedRate(this::fillCache, 0, refillPeriod.toMillis(), MILLISECONDS);
    }

    private void fillCache() {
        data.putAll(this.filler.get());
    }

    private void invalidateCache() {
        data.clear();
    }

    public void put(K key, V value) {
        if (key == null) return;
        try {
            writeLock.lock();
            data.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public V get(K key) {
        if (key == null) return null;
        try {
            readLock.lock();
            return data.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public int size() {
        return data.size();
    }

    @Override
    public void close() {
        scheduledFuture.cancel(true);
        scheduler.shutdown();
    }
}
