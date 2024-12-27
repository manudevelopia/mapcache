//package info.developia.lib.mapcache
//
//import info.developia.mapcache.Mapcache
//import info.developia.mapcache.cache.CacheFeatures
//import spock.lang.Specification
//
//import java.util.function.Supplier
//
//class MapcacheFillerSpec extends Specification {
//    Map<String, String> data = Map.of(
//            "key1", "value1",
//            "key2", "value2",
//            "key3", "value3"
//    )
//    Supplier<Map<String, String>> filler = () -> data
//    CacheFeatures<String, String> cache
//
//    def setup() {
//        cache = Mapcache.cache().filledOnce(filler).build()
//    }
//
//    def "Should have size as data right after initialization with filler"() {
//        when:
//        int result = cache.size()
//        then:
//        result == data.size()
//    }
//
//    def "Should increment size and return value of an added item"() {
//        given:
//        int initialSize = cache.size()
//        cache.put("key", "value")
//        when:
//        def result = cache.get("key")
//        then:
//        result == "value"
//        cache.size() == initialSize + 1
//    }
//
//    def "Should return null for null key"() {
//        given:
//        int initialSize = cache.size()
//        cache.put(null, "value")
//        when:
//        def result = cache.get(null)
//        then:
//        cache.size() == initialSize
//        result == null
//    }
//
//    def "Should return null for null for null item value"() {
//        given:
//        cache.put("key", null)
//        when:
//        def result = cache.get("key")
//        then:
//        result == null
//    }
//}
