package info.developia.macache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    protected final Map<K, V> data = new HashMap<>();

    protected final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    protected final Lock readLock = reentrantReadWriteLock.readLock();
    protected final Lock writeLock = reentrantReadWriteLock.readLock();

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

    public void del(K key) {
        if (key == null) return;
        try {
            readLock.lock();
            data.remove(key);
        } finally {
            readLock.unlock();
        }
    }

    public void clear() {
        data.clear();
    }

    public int size() {
        return data.size();
    }
}
