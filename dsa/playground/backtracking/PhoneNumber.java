package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneNumber {
    public static void main(String[] args) {
        String digits = "34";
        System.out.println(letterCombinations(digits));
    }
        public static List<String> letterCombinations(String digits) {
            Map<Character, String> digitToChars = new HashMap<>();
            digitToChars.put('2', "abc");
            digitToChars.put('3', "def");
            digitToChars.put('4', "ghi");
            digitToChars.put('5', "jkl");
            digitToChars.put('6', "mno");
            digitToChars.put('7', "pqrs");
            digitToChars.put('8', "tuv");
            digitToChars.put('9', "wxyz");
            List<String> output = new ArrayList<>();
             helper(digitToChars, digits, 0, output, new StringBuilder());
             return output;
        }

        public static void helper(Map<Character, String> map,
                                  String input, int index, List<String> output, StringBuilder stringBuilder) {
        if(index == input.length()) {
            if(stringBuilder.length()>0)
            output.add(stringBuilder.toString());
            return;
        }
        Character current = input.charAt(index);
        String options = map.get(current);
            for (int i = 0; i < options.length(); i++) {
                stringBuilder.append(options.charAt(i));
                helper(map, input, index+1, output, stringBuilder);
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
            }
        }



}
