package info.developia.lib.mapcache

import spock.lang.Specification

class MapCacheBasicSpec extends Specification {

    def "Should return a cache with default configuration"() {
        when:
        def cache = MapCache.cache()
        then:
        cache.maxSize() == 10000
    }

    def "Should have size 0 right after creation"() {
        when:
        def cache = MapCache.cache()
        then:
        cache.size() == 0
    }

    def "Should increase size after add an element"() {
        given:
        def cache = MapCache.cache()
        when:
        cache.put('key1', 'value1')
        then:
        cache.size() == 1
    }

    def "Should not increase size after add several times element with same key"() {
        given:
        def cache = MapCache.cache()
        when:
        10.times { cache.put('key1', "value$it") }
        then:
        cache.size() == 1
    }

    def "Should increase size after add several elements with different key"() {
        given:
        def items = 10
        def cache = MapCache.cache()
        when:
        items.times { cache.put("key$it", "value$it") }
        then:
        cache.size() == items
    }

    def "Should increase size over max size"() {
        given:
        def cache = MapCache.cache()
        when:
        (cache.maxSize() + 1).times { cache.put("key$it", "value$it") }
        then:
        cache.size() == cache.maxSize()
    }

    def "Should contain added element"() {
        given:
        def cache = MapCache.cache()
        when:
        cache.put('key1', 'value1')
        then:
        cache.contains('key1')
    }

    def "Should not contain not added element"() {
        given:
        def cache = MapCache.cache()
        when:
        cache.put('key1', 'value1')
        then:
        !cache.contains('notAddedKey')
    }

    def "Should decrease size after remove an element(s)"() {
        given:
        def items = 10
        def itemsToDelete = 3

        def cache = MapCache.cache()
        items.times { cache.put("key$it", "value$it") }
        when:
        itemsToDelete.times { cache.del("key$it") }
        then:
        cache.size() == items - itemsToDelete
    }

    def "Should size equal to 0 after remove all added element(s)"() {
        given:
        def items = 10
        def cache = MapCache.cache()
        items.times { cache.put("key$it", "value$it") }
        when:
        items.times { cache.del("key$it") }
        then:
        cache.size() == 0
    }

    def "Should size equal to 0 after clear cache"() {
        given:
        def items = 10
        def cache = MapCache.cache()
        items.times { cache.put("key$it", "value$it") }
        when:
        cache.clear()
        then:
        cache.size() == 0
    }
}
