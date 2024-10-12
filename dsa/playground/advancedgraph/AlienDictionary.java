package advancedgraph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

class AlienDictionary {
    
    // Method to derive the order of letters in the alien language
    public String foreignDictionary(String[] words) {
        Map<Character, List<Character>> characterMap = new HashMap<>();
        for (String word: words) {
            for (char character: word.toCharArray()) {
                characterMap.putIfAbsent(character, new ArrayList<>());
            }
        }

        for(int i=0;i<words.length-1; i++) {
            String word1 = words[i];
            String word2 = words[i+1];

            if(word2.length()<word1.length() && word1.startsWith(word2)) {
                return "";
            }
            int j;
            for(j=0;j<Math.min(word1.length(),word2.length());j++) {
                char character1 = word1.charAt(j);
                char character2 = word2.charAt(j);
                if(character1 != character2) {
                    break;
                }
            }
            if(j<Math.min(word1.length(),word2.length()))
            characterMap.get(word1.charAt(j)).add(word2.charAt(j));
        }
        System.out.println(characterMap);
        Set<Character> visited = new HashSet<>();
        Set<Character> visiting = new HashSet<>();
        Stack<Character> result = new Stack<>();
        for (char character : characterMap.keySet()) {
            if(!visited.contains(character)) {
                boolean dfs = dfs(character, visited, visiting, characterMap, result);
                if(!dfs) {
                    return "";
                }
            }
        }
        return stackToArray(result);
    }

    public boolean dfs (Character currentCharacter, Set<Character> visited,
                        Set<Character> visiting, Map<Character, List<Character>> characterMap,
                        Stack<Character> result) {
        if(visited.contains(currentCharacter)) {
            return true;
        }
        visiting.add(currentCharacter);
        List<Character> neighbours = characterMap.get(currentCharacter);
        for (Character neighbor: neighbours) {
            if(visiting.contains(neighbor)) {
                return false;
            }
            boolean dfs = dfs(neighbor, visited, visiting, characterMap, result);
            if(!dfs) {
                return false;
            }
        }
        visiting.remove(currentCharacter);
        visited.add(currentCharacter);
        result.add(currentCharacter);
        return true;
    }

    public static void main(String[] args) {
        AlienDictionary solution = new AlienDictionary();
        
        // Example usage
        String[] words = {"z", "z"};
        
        // Calling the method to get the order of letters
        String result = solution.foreignDictionary(words);
        
        // Print the result (the derived order of letters)
        System.out.println("Order of letters: " + result);
    }

    public static String stackToArray(Stack<Character> stack) {
       StringBuilder out = new StringBuilder();
        while(!stack.empty()){
            out.append(stack.pop());
        }
        return out.toString();
    }
}