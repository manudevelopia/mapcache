package info.developia.app;

import info.developia.macache.Macache;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class App {

    public static void main(String[] args) throws InterruptedException {
        var cache = new Macache<String, String>();
        cache.put("key", "value");
        cache.get("key");
        cache.size();

        Supplier<Map<String, String>> filler = () -> Map.of(
                "key1", "value1",
                "key2", "value2",
                "key3", "value3"
        );

        var cacheWithFiller = new Macache<>(filler);
        cacheWithFiller.size();

        var cacheWithFillerSchedule = new Macache<>(filler, Duration.ofSeconds(12));
        cacheWithFillerSchedule.size();
        while (true){
            Thread.sleep(1000);
        }
    }
}
