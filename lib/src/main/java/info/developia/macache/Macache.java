package info.developia.macache;


import java.util.Map;
import java.util.function.Supplier;

public class Macache {

    public static <V, K> Cache<V, K> basic() {
        return new Cache<>();
    }

    public static <V, K> CacheFilledOnce<K, V> filledOnce(Supplier<Map<K, V>> filler) {
        return new CacheFilledOnce<>(filler);
    }
}
