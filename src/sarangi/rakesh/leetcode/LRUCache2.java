package sarangi.rakesh.leetcode;

import java.util.LinkedHashMap;

public class LRUCache2 {
    private LinkedHashMap<Integer, Integer> cache;
    private Integer capacity;

    public LRUCache2(int capacity) {
        this.cache = new LinkedHashMap<>();
        this.capacity = capacity;

    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            int value = cache.get(key);
            cache.remove(key);
            cache.put(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // Move node to front of list
            cache.put(key, value);
        } else {
            if (cache.size() < this.capacity) {
                cache.put(key, value);
            } else {
                cache.remove(cache.keySet().iterator().next());
                cache.put(key, value);
            }
        }

    }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */