package consistenthashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Demonstrates the usage of ConsistentHashing implementation.
 * Shows how to add/remove nodes and distribute keys across nodes.
 */
public class ConsistentHashingDemo {
    public static void main(String[] args) {
        // Create a consistent hashing instance with 10 virtual nodes per physical node
        ConsistentHashing<String> consistentHashing = new ConsistentHashing<>(10, new MD5HashFunction());

        // Add some nodes (could be server names, cache instances, etc.)
        String[] nodes = {"server1", "server2", "server3", "server4"};
        Arrays.stream(nodes).forEach(consistentHashing::addNode);

        // Simulate distributing keys
        String[] keys = {
            "user1", "user2", "user3", "user4", "user5",
            "post1", "post2", "post3", "post4", "post5"
        };

        // Show initial distribution
        System.out.println("Initial key distribution:");
        showDistribution(consistentHashing, keys);

        // Remove a node (simulating server failure)
        System.out.println("\nRemoving server2...");
        consistentHashing.removeNode("server2");

        // Show distribution after node removal
        System.out.println("\nKey distribution after removing server2:");
        showDistribution(consistentHashing, keys);

        // Add a new node (simulating adding capacity)
        System.out.println("\nAdding server5...");
        consistentHashing.addNode("server5");

        // Show distribution after adding new node
        System.out.println("\nKey distribution after adding server5:");
        showDistribution(consistentHashing, keys);

        // Demonstrate weighted nodes
        System.out.println("\nAdding server6 with weight 2...");
        consistentHashing.addWeightedNode("server6", 2);

        // Show distribution with weighted node
        System.out.println("\nKey distribution after adding weighted server6:");
        showDistribution(consistentHashing, keys);
    }

    private static void showDistribution(ConsistentHashing<String> ch, String[] keys) {
        Map<String, Integer> distribution = new HashMap<>();
        
        // Count keys per node
        for (String key : keys) {
            String node = ch.getNode(key);
            distribution.merge(node, 1, Integer::sum);
        }

        // Show distribution
        distribution.forEach((node, count) -> 
            System.out.printf("%s: %d keys (%s)%n", 
                node, count, 
                String.join(", ", getKeysForNode(ch, node, keys)))
        );
    }

    private static String[] getKeysForNode(ConsistentHashing<String> ch, String targetNode, String[] keys) {
        return Arrays.stream(keys)
            .filter(key -> ch.getNode(key).equals(targetNode))
            .toArray(String[]::new);
    }
} 