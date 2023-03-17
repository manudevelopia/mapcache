package info.developia.macache.cache;

import java.util.Map;
import java.util.function.Supplier;

class CacheFilledOnce<K, V> extends CacheBasic<K, V> {

    public CacheFilledOnce(Supplier<Map<K, V>> filler) {
        data.putAll(filler.get());
    }
}
