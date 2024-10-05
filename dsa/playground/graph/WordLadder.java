package graph;

import java.util.*;

public class WordLadder {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Map<String,Set<String>> wordMap = new HashMap<>();
        boolean contains = wordList.contains(endWord);
        if(!contains) {
            return 0;
        }
        prepareDictionary(beginWord, wordMap);
        for (String word: wordList) {
            prepareDictionary(word, wordMap);
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int res = 1;boolean found= false;
        System.out.println(wordMap);

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                String word = queue.poll();
                if(word.equals(endWord)) {
                    return res;
                }
                List<String> possibleMutations = getPossibleMutations(word);
                possibleMutations.forEach(possibleMutation->{
                    wordMap.get(possibleMutation).forEach(cWord->{
                        if(!visited.contains(cWord)){
                            visited.add(cWord);
                            queue.add(cWord);
                        }
                    });
                });

            }
            res++;
        }
        return 0;
    }

    public static void main(String[] args) {
        // Test case 1: General case
        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println("Test case 1: " + ladderLength("hit", "cog", wordList1)); // Expected: 5

        // Test case 2: No valid transformation
        List<String> wordList2 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        System.out.println("Test case 2: " + ladderLength("hit", "cog", wordList2)); // Expected: 0

        // Test case 2: No valid transformation
        List<String> wordList3 = Arrays.asList("hot","dot","tog","cog");
        System.out.println("Test case 3: " + ladderLength("hit", "cog", wordList3)); // Expected: 0
    }


    public static List<String> getPossibleMutations(String input) {
        List<String> mutationList = new ArrayList<>();
        char[] chars = input.toCharArray();
        for(int i=0;i<chars.length;i++) {
            char temp = chars[i];
            chars[i] = '*';
            String mutation = new String(chars);
            mutationList.add(mutation);
            chars[i] = temp;
        }
        return mutationList;
    }

    public static boolean isEqual(String string1, String string2) {
        if(string1.length() == string2.length()) {
            for(int i=0; i<string1.length();i++) {
                if((string1.charAt(i)!='*' && string2.charAt(i)!='*') && string1.charAt(i) != string2.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void prepareDictionary(String word, Map<String,Set<String>> map) {
        List<String> possibleMutations = getPossibleMutations(word);
        for (String mutation: possibleMutations) {
            map.putIfAbsent(mutation, new HashSet<>());
            map.get(mutation).add(word);
        }
    }

}
