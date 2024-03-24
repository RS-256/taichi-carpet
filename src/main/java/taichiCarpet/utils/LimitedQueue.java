package taichiCarpet.utils;
import java.util.*;

public class LimitedQueue<K, V> {
    private final int maxSize;
    private final Map<K, V> map;

    public LimitedQueue(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be greater than zero");
        }
        this.maxSize = maxSize;
        this.map = new LinkedHashMap<K, V>(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxSize;
            }
        };
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized V remove(K key) {
        return map.remove(key);
    }

    public synchronized int size() {
        return map.size();
    }

    public synchronized boolean isEmpty() {
        return map.isEmpty();
    }

    public synchronized boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public synchronized Collection<V> values() {
        return map.values();
    }

    public synchronized Set<Map.Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    public synchronized void clear() {
        map.clear();
    }
}
