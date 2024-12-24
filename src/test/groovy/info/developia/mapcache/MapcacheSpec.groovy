package info.developia.mapcache

import spock.lang.Specification

class MapcacheSpec extends Specification {
    def cache = Mapcache.cache().maxSize(10).build()

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

    def "Should return null for key with null value"() {
        given:
        cache.put("key", null)
        when:
        def result = cache.get("key")
        then:
        cache.size() == 1
        result == null
    }

    def "Should not increment size over max capacity"() {
        given:
        cache.maxSize().times { cache.put("key$it", "value$it") }
        when:
        cache.put("key11", "value11")
        then:
        cache.size() == cache.maxSize()
    }
}
