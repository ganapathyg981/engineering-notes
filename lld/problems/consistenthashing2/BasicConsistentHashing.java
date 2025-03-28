package consistenthashing2;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Basic implementation of Consistent Hashing without virtual nodes.
 * This implementation is thread-safe but may have uneven distribution.
 */
public class BasicConsistentHashing<T> {
    private final HashFunction hashFunction;
    private final SortedMap<Long, T> circle = new TreeMap<>();

    /**
     * Constructor for BasicConsistentHashing.
     * @param hashFunction custom hash function implementation
     */
    public BasicConsistentHashing(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
    }

    /**
     * Adds a node to the hash ring.
     * @param node the node to add
     */
    public synchronized void addNode(T node) {
        circle.put(hashFunction.hash(node.toString()), node);
    }

    /**
     * Removes a node from the hash ring.
     * @param node the node to remove
     */
    public synchronized void removeNode(T node) {
        circle.remove(hashFunction.hash(node.toString()));
    }

    /**
     * Gets the node responsible for the given key.
     * @param key the key to look up
     * @return the node responsible for the key
     */
    public T getNode(String key) {
        if (circle.isEmpty()) {
            return null;
        }

        long hash = hashFunction.hash(key);
        if (!circle.containsKey(hash)) {
            SortedMap<Long, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

    /**
     * Gets all nodes in the hash ring.
     * @return collection of all nodes
     */
    public Collection<T> getNodes() {
        return circle.values();
    }

    /**
     * Gets the size of the hash ring.
     * @return size of the hash ring
     */
    public int getSize() {
        return circle.size();
    }
} 