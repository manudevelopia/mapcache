package info.developia.macache;

abstract class Config<K, V> {
    private long maxSize;

    protected void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public abstract Config<K, V> maxSize(long maxSize);

    public abstract Cache<K, V> build();
}
