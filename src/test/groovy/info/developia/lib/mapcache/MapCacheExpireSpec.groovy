package info.developia.lib.mapcache

import spock.lang.Specification

import java.time.Duration

class MapCacheExpireSpec extends Specification {
    def data = ['key1': 'value1', 'key2': 'value2', 'key3': 'value3']

    def "Should retrieve key value if key is not expired"() {
        given:
        def cache = MapCache.config().expireIn(Duration.ofMillis(500)).cache()
        data.forEach cache::put
        when:
        sleep 300
        then:
        cache.get("key1") != null
    }

    def "Should not retrieve key value if key is expired"() {
        given:
        def cache = MapCache.config().expireIn(Duration.ofMillis(500)).cache()
        data.forEach cache::put
        when:
        sleep 501
        then:
        cache.get("key1") == null
    }

    def "Should be size 0 after all keys expire"() {
        given:
        def cache = MapCache.config().expireIn(Duration.ofMillis(500)).cache()
        data.forEach cache::put
        when:
        sleep 1000
        then:
        cache.size() == 0
    }
}
