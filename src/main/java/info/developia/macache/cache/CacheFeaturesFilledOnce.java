package info.developia.macache.cache;

import java.util.Map;
import java.util.function.Supplier;

public class CacheFeaturesFilledOnce<K, V> extends Cache<K, V> {

    public CacheFeaturesFilledOnce(Supplier<Map<K, V>> filler) {
        super();
        data.putAll(filler.get());
    }
}
