package info.developia.macache;


import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class Macache {

    public static <V, K> Cache<V, K> basic() {
        return new CacheBasic<>();
    }

    public static <V, K> Cache<K, V> filledOnce(Supplier<Map<K, V>> filler) {
        return new CacheFilledOnce<>(filler);
    }

    public static <V, K> Cache<V, K> expireIn(Duration duration) {
        return new CacheExpire<>(duration);
    }

    public static <V, K> Cache<V, K> scheduledFiller(Supplier<Map<V, K>> filler, Duration duration) {
        return new CacheScheduledFiller<>(filler, duration);
    }
}
