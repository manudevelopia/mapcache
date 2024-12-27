package info.developia.lib.mapcache

import spock.lang.Specification

import java.time.Duration

class MapCacheBasicConfigurationSpec extends Specification {

    def "Should have with default configuration"() {
        when:
        def cache = MapCache.config().cache()
        then:
        cache.maxSize() == 10000
    }

    def "Should have max size value in configuration"() {
        given:
        int maxSize = 10
        when:
        def cache = MapCache.config().maxSize(maxSize).cache()
        then:
        cache.maxSize() == maxSize
    }


    def "Should have filler"() {
        given:
        def filler = { -> ['key1': 'value1', 'key2': 'value2'] }
        when:
        def cache = MapCache.config().filler(filler).cache()
        then:
        cache.size() == 2
    }

    def "Should have filler refreshed every duration period"() {
        given:
        def filler = { -> ['key1': 'value1', 'key2': 'value2'] }
        def duration = Duration.ofSeconds(1)
        when:
        def cache = MapCache.config().filler(filler).every(duration).cache()
        then:
        cache.size() == 2
    }
}
