package intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class InsertIntervals {


    public static void main(String[] args) {
        int[][] input = {{1,5},{6,8}};
        int[][] insert = insert(input, new int[]{4, 6});
        System.out.println(Arrays.deepToString(insert));
    }
    public static int[][] insert(int[][] intervals, int[] insert) {
        int i=0;
        List<int[]> output = new ArrayList<>();
        while(i< intervals.length && intervals[i][1] < insert[0]) {
            output.add(intervals[i]);
            i++;
        }
        while(i< intervals.length && (insert[0]< intervals[i][1])) {
            insert[0] = Math.min(insert[0], intervals[i][0]);
            insert[1] = Math.max(insert[1], intervals[i][1]);
            i++;
        }
        output.add(insert);
        while (i< intervals.length) {
            output.add(intervals[i]);
            i++;
        }
    // Manually convert List<int[]> to int[][]
            int[][] result = new int[output.size()][2];
            for (int j = 0; j < output.size(); j++) {
                result[j] = output.get(j);
            }

            return result;
    }
}
