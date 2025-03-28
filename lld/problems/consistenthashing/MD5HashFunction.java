package consistenthashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5-based implementation of HashFunction interface.
 * Uses MD5 for consistent hash generation.
 */
public class MD5HashFunction implements ConsistentHashing.HashFunction {
    private final MessageDigest md5;

    public MD5HashFunction() {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
    }

    @Override
    public long hash(String key) {
        md5.reset();
        md5.update(key.getBytes());
        byte[] digest = md5.digest();
        
        // Use the first 8 bytes of the digest to create a long value
        long hash = 0;
        for (int i = 0; i < 8; i++) {
            hash <<= 8;
            hash |= ((int) digest[i]) & 0xFF;
        }
        return hash;
    }
} 