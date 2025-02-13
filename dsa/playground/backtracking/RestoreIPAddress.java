package backtracking;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
    public static void main(String[] args) {

        System.out.println(restoreIpAddresses("25525511135"));
    }


    public static List<String> restoreIpAddresses(String s) {
        List<String> output = new ArrayList<>();
        helper(s,0,output,new ArrayList<>());
        return output;
    }


    public static void helper(String input, int startIndex, List<String> output, List<String> appender) {
        // Base case: If we have 4 segments and the string is completely consumed
        if (appender.size() == 4) {
            if (startIndex == input.length()) {
                String join = String.join(".", appender);
                output.add(join);
            }
            return;
        }
        for(int i=startIndex;i<input.length() && i < startIndex + 3;i++) {
            String part = input.substring(startIndex,i+1);
            if(valid(part)) {
                appender.add(part);
                helper(input, i+1, output, appender);
                appender.remove(appender.size()-1);
            }
        }
    }

    public static boolean valid(String number) {
        if(number.length()>0 && number.length()<=3) {
            if(number.startsWith("0")) {
                return number.length()==1;
            } else {
                return Integer.valueOf(number) <=255;
            }
        }
        return false;
    }

//    public static boolean valid(String number) {
//        if (number.length() > 3 || number.isEmpty()) {
//            return false;
//        }
//        if (number.startsWith("0") && number.length() > 1) {
//            return false; // Leading zero invalid
//        }
//        int value = Integer.parseInt(number);
//        return value >= 0 && value <= 255;
//    }
}