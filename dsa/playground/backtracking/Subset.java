package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subset {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        List<List<Integer>> subsets = subsets(nums);
        System.out.println(subsets);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsets(nums, new ArrayList<>(), 0, result);
        return result;
    }

    public static void subsets(int[] nums, List<Integer> out, int index, List<List<Integer>> result) {
        if(index == nums.length) {
            result.add(new ArrayList<>(out));  // Create a new list copy
            return;
        }
        out.add(nums[index]);
        subsets(nums, out, index+1, result);
        out.remove(out.size()-1);
        subsets(nums, out, index+1, result);
    }
}

