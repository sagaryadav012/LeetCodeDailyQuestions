package FebMonth;

import java.util.PriorityQueue;

public class Day17_LC1642 {
    public static void main(String[] args) {
        int[] heights = {4,12,2,7,3,18,20,3,19};
        int bricks = 5;
        int ladders = 2;
        Day17_LC1642 obj = new Day17_LC1642();
        int max = obj.furthestBuilding(heights, bricks, ladders);
        System.out.println(max);
    }
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
         int n = heights.length;
         PriorityQueue<Integer> pq = new PriorityQueue<>((p,q) -> q - p);
         for(int i = 0; i < n-1; i++){
             int diff = heights[i+1] - heights[i];
             if(diff < 0) continue;

             pq.add(diff);
             bricks -= diff;
             if(bricks < 0){
                 //if(pq.isEmpty()) return i;
                 int getBackBricks = pq.peek();
                 bricks += getBackBricks;
                 ladders -= 1;
                 if(ladders < 0) return i;
                 pq.poll();
             }
         }
         return n - 1;
    }
    public int maxReach(int i, int bricks, int ladders, int[] heights, int n){
        if(i == n-1) return 0;
        if(ladders == 0 && bricks == 0) return  0;
        if(ladders == 0 && bricks < heights[i+1] - heights[i]) return 0;

        int hightDiff = heights[i+1] - heights[i];
        if(hightDiff <= 0){
            return 1 + maxReach(i + 1, bricks, ladders, heights, n);

        }
        else {
            int useBricks = 0;
            if (bricks >= hightDiff) {
                useBricks = maxReach(i + 1, bricks - hightDiff, ladders, heights, n);
            }

            int useLadder = maxReach(i + 1, bricks, ladders - 1, heights, n);

            return 1 + Math.max(useBricks, useLadder);
        }
    }
}

/*
Constraints :
        1 <= heights.length <= 105
        1 <= heights[i] <= 106
        0 <= bricks <= 109
        0 <= ladders <= heights.length

 DP won't work here since constrains are very large, It give MLE

 Approach :
 Hint - use ladders when have to do largest jump

 Ex : heights = {4,12,2,7,3,18,20,3,19};
      bricks = 5;
      ladders = 2;

indexes :   0      1      2       3       4       5        6       7       8
            4     12      2       7       3       18       20      3       19
difference :   8     -10      5      -4       15       2      -17      16

Now : To jump from 4 to 12 either you need 8bricks or 1ladder
      To jump from 12 to 2 we don't need ladders or bricks. so, when we get -ve difference we will do continue

      Assume we use bricks continuously until bricks are exhausted, when bricks are used completely,
      we use ladder means replace bricks with ladder. Where we have to replace ?
      Replace bricks where we have used more bricks along the way.
      Here bricks used are difference b/n next building height - current building height.
      So store difference while iterating, store them in PQ. it's easy to get max diff at TC O(1)
      once replace bricks with ladder, decrease count of ladders and brick += replacedBricks

      when we bricks get exhausted, we use ladders to replace bricks(max diff). if ladders also exhausted
      then return current index means from there we can move further

      And if we covered all building means return n-1 means return last building position.
 */
