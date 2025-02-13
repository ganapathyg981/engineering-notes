package recursion;

import java.util.ArrayList;
import java.util.List;

public class Recursion {


    public static void main(String[] args) {
        Recursion recursion  = new Recursion();
//        List<Integer> integers = new ArrayList<>();
//        recursion.count(10, integers);
//        System.out.println(integers);
        System.out.println(recursion.sumParameter(10,0));
        System.out.println(recursion.sumFunctional(10));
    }


    private void count (int i, List<Integer> state) {
        System.out.println(i);
        state.add(i);

        if(i>1) {
            count(i-1, state);
        }
    }
    // result travels through parameters
    private int sumParameter(int n, int sum) {
        if (n == 0) {
            return sum; // Use the result of the recursive call
        }
        return sumParameter(n - 1, sum + n); // Base case: when n is 0, return the accumulated sum
    }

    private int sumFunctional(int n){
        if(n==0) return 0;
        return n + sumFunctional(n-1);
    }

}
