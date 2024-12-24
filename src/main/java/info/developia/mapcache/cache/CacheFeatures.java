package info.developia.mapcache.cache;

public interface CacheFeatures<K, V> {
    void put(K key, V value);
    V get(K key);
    boolean contains (K key);
    void del(K key);
    void clear();
    int size();
    int maxSize();
    void close();
}
