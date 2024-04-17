package MarchMonth;

import java.util.Arrays;

public class Day18_LC452 {
    public static void main(String[] args) {
        int[][] points = {
                {-2147483646,-2147483645},
                {2147483646,2147483647}
//                {10,16},
//                {2,8},
//                {1,6},
//                {7,12}
        };
        System.out.println(findMinArrowShots(points));
    }
    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] < b[0] ? -1 : a[0] == b[0] ? 0 : 1); // Can use Integer.compare(), Explore it.
        int n = points.length;

        int diameterEnd = points[0][1];
        int arrows = 1;

        for(int i = 1; i < n; i++){
            if(diameterEnd < points[i][0]){
                diameterEnd = points[i][1];
                arrows++;
            }
        }
        return arrows;
    }
}
/*
Test cases :
[[-2147483646,-2147483645],[2147483646,2147483647]]
[[3,9],[7,12],[3,8],[6,8],[9,10],[2,9],[0,9],[3,9],[0,6],[2,8]]
why we need to sort on 1 index : Run this [0,9], [0,6], [7,12]

-> Sort diameters on end.
-> Now check consecutive overlapped diameters, if they overlap that means we can burst with one arrow.
-> Take end of first diameter, compare it with next consecutive diameters of start. if end < start means not overlapped
   else overlapped.
-> If they overlapped do nothing, if we find not overlapped diameter means we have to check again consecutive overlapped
   diameters.
-> initially take arrows = 1, because for last consecutive overlapped diameter we won't increment arrows so take
   it first only.
 */