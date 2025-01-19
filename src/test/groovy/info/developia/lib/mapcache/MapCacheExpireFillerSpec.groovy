package info.developia.lib.mapcache

import spock.lang.Specification

import java.time.Duration
import java.util.function.Supplier

class MapCacheExpireFillerSpec extends Specification {
    def data = ['key1': 'value1', 'key2': 'value2', 'key3': 'value3']
    Supplier<Map<String, String>> filler = () -> data

    def "Should retrieve key value if key is not expired"() {
        given:
        def cache = MapCache.config().filler(filler).expireIn(Duration.ofMillis(500)).cache()
        when:
        sleep 300
        then:
        cache.get("key1") != null
    }

    def "Should not retrieve key value if key is expired"() {
        given:
        def cache = MapCache.config().filler(filler).expireIn(Duration.ofMillis(500)).cache()
        when:
        sleep 501
        then:
        cache.get("key1") == null
    }
}
