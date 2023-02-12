package info.developia.macache;


import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class Macache {

    public static <V, K> Cache<V, K> basic() {
        return new CacheBasic<>();
    }

    public static <V, K> CacheFilledOnce<K, V> filledOnce(Supplier<Map<K, V>> filler) {
        return new CacheFilledOnce<>(filler);
    }

    public static Cache<String, String> expireIn(Duration duration) {
        return new CacheExpire<>(duration);
    }
}
