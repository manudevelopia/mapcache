package info.developia.macache.config;

import info.developia.macache.Cache;

abstract class Config<K, V> {
    private long maxSize;

    protected void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public abstract Config<K, V> maxSize(long maxSize);

    public abstract Cache<K, V> build();
}
