package info.developia.macache;

public interface Cache<K, V> {
    void put(K key, V value);
    V get(K key);
    void del(K key);
    void clear();
    int size();
    void close();
}
