package info.developia.lib.mapcache.cache;

import info.developia.lib.mapcache.task.KeyExpireRegistry;

import java.time.Duration;

public class CacheExpire<K, V> extends CacheBasic<K, V> {
    private final KeyExpireRegistry<K> keyExpireRegistry;

    public CacheExpire(int maxSize, Duration expirePeriod) {
        super(maxSize);
        keyExpireRegistry = new KeyExpireRegistry<>(expirePeriod, this::del);
    }

    @Override
    public void put(K key, V value) {
        super.put(key, value);
        keyExpireRegistry.put(key);
    }

    @Override
    public V get(K key) {
        if (key == null || !keyExpireRegistry.containsKey(key)) return null;
        return super.get(key);
    }

    @Override
    public void del(K key) {
        super.del(key);
        keyExpireRegistry.remove(key);
    }

    @Override
    public void clear() {
        super.clear();
        keyExpireRegistry.clear();
    }

    public void close() {
        super.close();
        keyExpireRegistry.close();
    }
}
