package info.developia.lib.mapcache

import spock.lang.Specification

import java.util.function.Supplier

class MapCacheFillerSpec extends Specification {
    def data = ['key1': 'value1', 'key2': 'value2', 'key3': 'value3']
    Supplier<Map<String, String>> filler = () -> data

    def "Should have data after initialization with filler"() {
        given:
        def cache = MapCache.config().filler(filler).cache()
        when:
        int result = cache.size()
        then:
        result == data.size()
    }
}
