package info.developia.lib.mapcache

import spock.lang.Specification

import java.time.Duration
import java.util.function.Supplier

class MapCacheExpireScheduledSpec extends Specification {
    def data = ['key1': 'value1', 'key2': 'value2', 'key3': 'value3']
    Supplier<Map<String, String>> filler = () -> data

    def "Should retrieve key value, it expires but it's filled "() {
        given:
        def cache = MapCache.config().filler(filler).every(Duration.ofMillis(400)).expireIn(Duration.ofMillis(500)).cache()
        data.forEach cache::put
        when:
        sleep 600
        then:
        cache.get("key1") != null
    }

    def "Should not retrieve key value, it expires but it's not filled "() {
        given:
        def cache = MapCache.config().filler(filler).expireIn(Duration.ofMillis(500)).cache()
        data.forEach cache::put
        when:
        sleep 600
        then:
        cache.get("key1") == null
    }
}
