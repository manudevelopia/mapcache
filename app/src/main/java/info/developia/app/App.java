package info.developia.app;

import info.developia.macache.Cache;
import info.developia.macache.Macache;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Supplier;

public class App {
    private static final Supplier<Map<String, String>> filler = () -> Map.of(
            "key1", "value1",
            "key2", "value2",
            "key3", "value3"
    );

    public static void main(String[] args) throws InterruptedException {
        Cache<String, String> cache = Macache.basic();
        cache.put("key", "value");
        cache.get("key");
        cache.size();

        Cache<String, String> cacheFilledOnce = Macache.filledOnce(filler);
        cacheFilledOnce.put("key4", "value4");
        cacheFilledOnce.get("key4");
        cacheFilledOnce.size();
        Cache<String, String> cacheExpire = Macache.expireIn(Duration.ofMinutes(1));
        filler.get().forEach(cacheExpire::put);
        int counter = 50;
        while (--counter > 0) {
            Thread.sleep(1000);
            var print = cacheExpire.get("key1");
            System.out.println("counter %d time %s value %s".formatted(counter, LocalDateTime.now(), print));
        }

//        var cache = new Macache<String, String>();
//        cache.put("key", "value");
//        cache.get("key");
//        cache.size();
//
//
//        var cacheWithFiller = new Macache<>(filler);
//        cacheWithFiller.size();
//
//        var cacheWithFillerSchedule = new Macache<>(filler, Duration.ofSeconds(12));
//        cacheWithFillerSchedule.size();
    }
}
