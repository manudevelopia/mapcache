package info.developia.macache

import info.developia.macache.cache.CacheFeatures
import spock.lang.Specification

import java.time.Duration

class MacacheExpireSpec extends Specification {
    Map<String, String> data = Map.of(
            "key1", "value1",
            "key2", "value2",
            "key3", "value3"
    )
    CacheFeatures<String, String> cache = Macache.cache().expireIn(Duration.ofSeconds(5))

    def "Should be empty right after initialization"() {
        when:
        int result = cache.size()
        then:
        result == 0
    }

    def "Should increment size and return value of an added item"() {
        given:
        data.each { key, value -> cache.put(key, value) }
        when:
        def result = cache.size()
        then:
        result == data.size()
        data.every { cache::contains }
    }

    def "Should return null for null key"() {
        given:
        int initialSize = cache.size()
        cache.put(null, "value")
        when:
        def result = cache.get(null)
        then:
        cache.size() == initialSize
        result == null
    }

    def "Should return null for null for null item value"() {
        given:
        cache.put("key", null)
        when:
        def result = cache.get("key")
        then:
        result == null
    }
}
