package info.developia.lib.mapcache;

public interface Cache<K, V> extends AutoCloseable {
    void put(K key, V value);
    V get(K key);
    boolean contains(K key);
    void del(K key);
    void clear();
    int size();
    int maxSize();
}
