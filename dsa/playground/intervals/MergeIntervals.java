package intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MergeIntervals {
    public static void main(String[] args) {
        int[][] input = {{1,6},{4,8}};
        int[][] merge = merge(input);
        System.out.println(Arrays.deepToString(merge));
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> output = new ArrayList<>();
        output.add(intervals[0]);
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            int lastEnd = output.get(output.size()-1)[1];
            if(lastEnd>=start) {
                output.get(output.size()-1)[1] = Math.max(end, lastEnd);
            }
            else {
                output.add(intervals[i]);
            }
        }
        int[][] result = new int[output.size()][2];
        for (int j = 0; j < output.size(); j++) {
            result[j] = output.get(j);
        }

        return result;
    }
}
