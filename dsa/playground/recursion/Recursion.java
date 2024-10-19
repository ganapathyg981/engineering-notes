package recursion;

import java.util.ArrayList;
import java.util.List;

public class Recursion {


    public static void main(String[] args) {
        Recursion recursion  = new Recursion();
        List<Integer> integers = new ArrayList<>();
        recursion.count(10, integers);
        System.out.println(integers);
    }


    private void count (int i, List<Integer> state) {
        System.out.println(i);
        state.add(i);

        if(i>1) {
            count(i-1, state);
        }
    }

}
