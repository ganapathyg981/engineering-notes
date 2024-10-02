package graph;

import java.util.*;

public class WordLadder {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Your solution here
        return 0;
    }

    public static void main(String[] args) {
        // Test case 1: General case
        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println("Test case 1: " + ladderLength("hit", "cog", wordList1)); // Expected: 5

        // Test case 2: No valid transformation
        List<String> wordList2 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        System.out.println("Test case 2: " + ladderLength("hit", "cog", wordList2)); // Expected: 0
    }
}
       