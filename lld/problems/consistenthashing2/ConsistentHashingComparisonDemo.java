package consistenthashing2;

import consistenthashing.ConsistentHashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Demonstrates and compares the usage of both Consistent Hashing implementations.
 * Shows how virtual nodes provide better distribution of keys.
 */
public class ConsistentHashingComparisonDemo {
    public static void main(String[] args) {
        // Create instances of both implementations
        BasicConsistentHashing<String> basicHashing = new BasicConsistentHashing<>(new MD5HashFunction());
        VirtualNodeConsistentHashing<String> virtualNodeHashing = new VirtualNodeConsistentHashing<>(10, new MD5HashFunction());

        // Add nodes to both implementations
        String[] nodes = {"server1", "server2", "server3", "server4"};
        Arrays.stream(nodes).forEach(basicHashing::addNode);
        Arrays.stream(nodes).forEach(virtualNodeHashing::addNode);

        // Generate test keys
        String[] keys = generateTestKeys(100);

        // Compare distributions
        System.out.println("=== Basic Consistent Hashing (without virtual nodes) ===");
        showDistribution(basicHashing, keys);

        System.out.println("\n=== Virtual Node Consistent Hashing ===");
        showDistribution(virtualNodeHashing, keys);

        // Demonstrate node removal
        System.out.println("\n=== After Removing server2 ===");
        basicHashing.removeNode("server2");
        virtualNodeHashing.removeNode("server2");

        System.out.println("\nBasic Hashing Distribution:");
        showDistribution(basicHashing, keys);

        System.out.println("\nVirtual Node Hashing Distribution:");
        showDistribution(virtualNodeHashing, keys);

        // Demonstrate weighted nodes
        System.out.println("\n=== Adding server5 with weight 2 ===");
        virtualNodeHashing.addWeightedNode("server5", 2);

        System.out.println("\nVirtual Node Hashing Distribution with Weighted Node:");
        showDistribution(virtualNodeHashing, keys);
    }

    private static String[] generateTestKeys(int count) {
        String[] keys = new String[count];
        for (int i = 0; i < count; i++) {
            keys[i] = "key" + i;
        }
        return keys;
    }

    private static void showDistribution(BasicConsistentHashing<String> ch, String[] keys) {
        Map<String, Integer> distribution = new HashMap<>();
        
        // Count keys per node
        for (String key : keys) {
            String node = ch.getNode(key);
            distribution.merge(node, 1, Integer::sum);
        }

        // Calculate statistics
        int min = distribution.values().stream().mapToInt(Integer::intValue).min().orElse(0);
        int max = distribution.values().stream().mapToInt(Integer::intValue).max().orElse(0);
        double avg = distribution.values().stream().mapToInt(Integer::intValue).average().orElse(0.0);
        double stdDev = calculateStandardDeviation(distribution.values().stream().mapToInt(Integer::intValue).toArray());

        // Show distribution
        System.out.println("Key Distribution:");
        distribution.forEach((node, count) -> 
            System.out.printf("%s: %d keys%n", node, count)
        );

        // Show statistics
        System.out.printf("\nStatistics:%n");
        System.out.printf("Min keys per node: %d%n", min);
        System.out.printf("Max keys per node: %d%n", max);
        System.out.printf("Average keys per node: %.2f%n", avg);
        System.out.printf("Standard deviation: %.2f%n", stdDev);
    }

    private static void showDistribution(VirtualNodeConsistentHashing<String> ch, String[] keys) {
        Map<String, Integer> distribution = new HashMap<>();
        
        // Count keys per node
        for (String key : keys) {
            String node = ch.getNode(key);
            distribution.merge(node, 1, Integer::sum);
        }

        // Calculate statistics
        int min = distribution.values().stream().mapToInt(Integer::intValue).min().orElse(0);
        int max = distribution.values().stream().mapToInt(Integer::intValue).max().orElse(0);
        double avg = distribution.values().stream().mapToInt(Integer::intValue).average().orElse(0.0);
        double stdDev = calculateStandardDeviation(distribution.values().stream().mapToInt(Integer::intValue).toArray());

        // Show distribution
        System.out.println("Key Distribution:");
        distribution.forEach((node, count) -> 
            System.out.printf("%s: %d keys (virtual nodes: %d)%n", 
                node, count, ch.getVirtualNodeCount(node))
        );

        // Show statistics
        System.out.printf("\nStatistics:%n");
        System.out.printf("Min keys per node: %d%n", min);
        System.out.printf("Max keys per node: %d%n", max);
        System.out.printf("Average keys per node: %.2f%n", avg);
        System.out.printf("Standard deviation: %.2f%n", stdDev);
    }

    private static double calculateStandardDeviation(int[] numbers) {
        double mean = Arrays.stream(numbers).average().orElse(0.0);
        double variance = Arrays.stream(numbers)
            .mapToDouble(num -> Math.pow(num - mean, 2))
            .average()
            .orElse(0.0);
        return Math.sqrt(variance);
    }
} 