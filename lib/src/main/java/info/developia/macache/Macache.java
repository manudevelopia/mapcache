package info.developia.macache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Macache<K, V> {
    private final Map<K, V> data = new HashMap<>();

    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = reentrantReadWriteLock.readLock();
    private final Lock writeLock = reentrantReadWriteLock.readLock();

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
}
