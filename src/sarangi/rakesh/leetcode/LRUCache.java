package sarangi.rakesh.leetcode;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        System.out.println(lruCache.get(1));
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }

    private Map<Integer, Node> map;
    private LinkedList<Node> list;
    private Integer capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.list = new LinkedList<>();
        this.capacity = capacity;

    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            list.remove(node);
            list.addFirst(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            // Move node to front of list
            list.remove(node);
            list.addFirst(node);
        } else {
            Node node = new Node(key, value);
            if (list.size() < this.capacity) {
                list.addFirst(node);
                map.put(key, node);
            } else {
                list.addFirst(node);
                map.put(key, node);
                Node removedNode = list.removeLast();
                map.remove(removedNode.key);
            }
        }

    }
}

class Node {
    int key;
    int value;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
