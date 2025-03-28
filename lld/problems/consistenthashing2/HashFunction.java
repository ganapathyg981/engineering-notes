package consistenthashing2;

/**
 * Interface for hash functions used in consistent hashing implementations.
 */
public interface HashFunction {
    /**
     * Generates a hash value for the given key.
     * @param key the key to hash
     * @return the hash value
     */
    long hash(String key);
} 