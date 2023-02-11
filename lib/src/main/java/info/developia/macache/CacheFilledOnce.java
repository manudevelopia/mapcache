package info.developia.macache;

import java.util.Map;
import java.util.function.Supplier;

public class CacheFilledOnce<K, V> extends CacheBasic<K, V> {

    public CacheFilledOnce(Supplier<Map<K, V>> filler) {
        data.putAll(filler.get());
    }
}
