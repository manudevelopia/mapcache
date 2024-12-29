package info.developia.lib.mapcache

import spock.lang.Specification

import java.time.Duration

class MapCacheExpireSpec extends Specification {
    Map<String, String> data = Map.of(
            "key1", "value1",
            "key2", "value2",
            "key3", "value3"
    )

    def "Should be empty right after initialization"() {
        given:
        Cache<String, String> cache = MapCache.config().expireIn(Duration.ofMillis(100)).cache()
        when:
        data.forEach cache::put
        def result = true
        while (result) sleep 1000
        then:
        cache.size() == 0
    }
}
