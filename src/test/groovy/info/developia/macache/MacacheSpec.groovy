package info.developia.macache

import info.developia.macache.cache.CacheFeatures
import spock.lang.Specification

class MacacheSpec extends Specification {
    CacheFeatures<String, String> cache

    def setup() {
        cache = Macache.cache().maxSize(100).build()
    }

    def "Should not have any item right after initialization"() {
        when:
        int result = cache.size()
        then:
        result == 0
    }

    def "Should increment size and return value of an added item"() {
        given:
        int initialSize = cache.size()
        cache.put("key", "value")
        when:
        def result = cache.get("key")
        then:
        result == "value"
        cache.size() == initialSize + 1
    }

    def "Should return null for null key"() {
        given:
        cache.put(null, "value")
        when:
        def result = cache.get(null)
        then:
        cache.size() == 0
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
