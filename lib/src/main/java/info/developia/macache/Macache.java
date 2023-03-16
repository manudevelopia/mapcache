package info.developia.macache;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class Macache {

    public static <K, V> Cache<K, V> basic() {
        return new CacheBasic<>();
    }

    public static <K, V> ConfigFilledOnce<K, V> filledOnce(Supplier<Map<K, V>> filler) {
        return new ConfigFilledOnce<>(filler);
    }

    public static <K, V> ConfigFillerScheduled<K, V> scheduledFiller(Supplier<Map<K, V>> filler, Duration duration) {
        return new ConfigFillerScheduled<>(filler,duration);
    }

    public static <K, V> Cache<K, V> expireIn(Duration duration) {
        return new CacheExpire<>(duration);
    }

    public static <K, V> Cache<K, V> build() {
        return new CacheBasic<>();
    }
}
