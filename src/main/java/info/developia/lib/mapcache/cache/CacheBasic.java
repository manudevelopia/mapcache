package info.developia.lib.mapcache.cache;

import info.developia.lib.mapcache.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheBasic<K, V> implements Cache<K, V> {
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = reentrantReadWriteLock.readLock();
    private final Lock writeLock = reentrantReadWriteLock.readLock();
    private final Map<K, V> data;
    private final int maxSize;

    public CacheBasic(int maxSize) {
        this.data = new HashMap<>(maxSize);
        this.maxSize = maxSize;
    }

    @Override
    public void put(K key, V value) {
        if (key == null || maxSize <= size()) return;
        try {
            writeLock.lock();
            data.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public V get(K key) {
        if (key == null) return null;
        try {
            readLock.lock();
            return data.get(key);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean contains(K key) {
        return data.containsKey(key);
    }

    @Override
    public void del(K key) {
        if (key == null) return;
        try {
            readLock.lock();
            data.remove(key);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public int maxSize() {
        return maxSize;
    }

    @Override
    public void close() {
        clear();
    }
}
