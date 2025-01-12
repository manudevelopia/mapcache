# MapCache
![mapcache.svg](doc/mapcache.svg)

## Description
MapCache is a local cache for java and jvm applications. It support different cache types, you can seem them on the examples, from the very basic to the most complete.

## Features

### Cache
```java
var cache = MapCache.config()
        .cache();
```

### Cache with filler
```java
var filler = () -> Map.of("key1", "value1", "key2", "value2", "key3", "value3");
var cacheWithFiller = MapCache.config()
        .filler(filler)
        .cache();
```

### Cache with key expire
```java
var cache = MapCache.config()
        .expireIn(Duration.ofMinutes(15))
        .cache();
```

### Cache with filler and key expire
```java
var filler = () -> Map.of("key1", "value1", "key2", "value2", "key3", "value3");
var cache = MapCache.config()
        .filler(filler).expireIn(Duration.ofMinutes(15))
        .cache();
```

### Cache with scheduled filler
```java
var filler = () -> Map.of("key1", "value1", "key2", "value2", "key3", "value3");
var cache = MapCache.config()
        .filler(filler).every(Duration.ofMinutes(5))
        .cache();
```

### Cache with scheduled filler and key expire
```java
var filler = () -> Map.of("key1", "value1", "key2", "value2", "key3", "value3");
var cache = MapCache.config()
        .filler(filler).every(Duration.ofMinutes(5))
        .expireIn(Duration.ofMinutes(15))
        .cache();
```

Links used to build this project
* https://medium.com/analytics-vidhya/advanced-locking-in-java-reentrant-read-write-lock-b40fce0833de
* https://www.codejava.net/java-core/concurrency/java-readwritelock-and-reentrantreadwritelock-example
* https://medium.com/analytics-vidhya/how-to-implement-cache-in-java-d9aa5e9577f2