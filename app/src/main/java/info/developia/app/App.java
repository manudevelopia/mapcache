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
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Cache<String, String> c = Macache.build();
        Cache<String, String> ca = Macache.filledOnce(filler).maxSize(20).build();
//                .config().filler().maxSize()
////                .filler(filler)
////                .maxSize(50)
//                .build();

//        Cache<String, String> cache = Macache.basic();
//        cache.put("key", "value");
//        cache.get("key");
//        cache.size();
//
//        Cache<String, String> cacheFilledOnce = Macache.filledOnce(filler);
//        cacheFilledOnce.put("key4", "value4");
//        cacheFilledOnce.get("key4");
//        cacheFilledOnce.size();
//        Cache<String, String> cacheExpire = Macache.expireIn(Duration.ofMinutes(1));
//        filler.get().forEach(cacheExpire::put);
//        counter = 0;
//        while (++counter < 60) {
//            Thread.sleep(1000);
//            var print = cacheExpire.get("key1");
//            System.out.println("counter %d time %s value %s".formatted(counter, LocalDateTime.now(), print));
//        }

        Cache<String, String> cacheScheduledFiller = Macache
                .scheduledFiller(filler, Duration.ofSeconds(10))
                .build();
        counter = 0;
        System.out.println("cache size %d".formatted(cacheScheduledFiller.size()));
        while (++counter < 60) {
            Thread.sleep(1000);
            var print = cacheScheduledFiller.get("key1");
            System.out.println("counter %d time %s value %s".formatted(counter, LocalDateTime.now(), print));
            System.out.println("cache size %d".formatted(cacheScheduledFiller.size()));
        }
    }
}
