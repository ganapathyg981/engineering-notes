package consistenthashing2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Implementation of Consistent Hashing with virtual nodes for better distribution.
 * This implementation is thread-safe and provides more even distribution of keys
 * across physical nodes by using virtual nodes.
 */
public class VirtualNodeConsistentHashing<T> {
    private final HashFunction hashFunction;
    private final int numberOfReplicas; // Virtual nodes per physical node
    private final SortedMap<Long, T> circle = new TreeMap<>(); // The hash ring
    private final Map<T, Integer> nodeWeights = new HashMap<>(); // Weights for weighted distribution

    /**
     * Constructor for VirtualNodeConsistentHashing.
     * @param numberOfReplicas number of virtual nodes per physical node
     * @param hashFunction custom hash function implementation
     */
    public VirtualNodeConsistentHashing(int numberOfReplicas, HashFunction hashFunction) {
        this.numberOfReplicas = numberOfReplicas;
        this.hashFunction = hashFunction;
    }

    /**
     * Adds a node to the hash ring.
     * @param node the node to add
     */
    public synchronized void addNode(T node) {
        addWeightedNode(node, 1);
    }

    /**
     * Adds a node with specific weight to the hash ring.
     * @param node the node to add
     * @param weight the weight of the node
     */
    public synchronized void addWeightedNode(T node, int weight) {
        nodeWeights.put(node, weight);
        for (int i = 0; i < numberOfReplicas * weight; i++) {
            circle.put(hashFunction.hash(node.toString() + i), node);
        }
    }

    /**
     * Removes a node from the hash ring.
     * @param node the node to remove
     */
    public synchronized void removeNode(T node) {
        int weight = nodeWeights.getOrDefault(node, 1);
        for (int i = 0; i < numberOfReplicas * weight; i++) {
            circle.remove(hashFunction.hash(node.toString() + i));
        }
        nodeWeights.remove(node);
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
     * Gets all physical nodes in the hash ring.
     * @return collection of all physical nodes
     */
    public Collection<T> getNodes() {
        return nodeWeights.keySet();
    }

    /**
     * Gets the size of the hash ring (number of virtual nodes).
     * @return size of the hash ring
     */
    public int getSize() {
        return circle.size();
    }

    /**
     * Gets the number of virtual nodes for a physical node.
     * @param node the physical node
     * @return number of virtual nodes for the physical node
     */
    public int getVirtualNodeCount(T node) {
        return numberOfReplicas * nodeWeights.getOrDefault(node, 1);
    }
} 