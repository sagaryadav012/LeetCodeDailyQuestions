package MarchMonth;

import java.util.Arrays;

public class Day2_LC977 {
    public static void main(String[] args) {
        int[] nums = {-7,-3,2,3,11};
        Day2_LC977 obj = new Day2_LC977();
        System.out.println(Arrays.toString(obj.sortedSquares(nums)));
        System.out.println(Arrays.toString(obj.sortedSquares1(nums)));
    }
    public int[] sortedSquares(int[] nums) { // TC - O(N) SC - O(N)
        int n = nums.length;
        boolean[] ar = new boolean[20001];
        int[] res = new int[n];

        for(int val : nums){
            ar[val + 10000] = true;
        }

        int k = 0;
        if(ar[10000]){
            res[0] = 0;
            k++;
        }

        for(int i = 1; i <= 10000; i++){
            if(ar[i + 10000]) res[k++] = i * i;
            if(ar[10000 - i]) res[k++] = i * i;
        }

        return res;
    }
    public int[] sortedSquares1(int[] nums) { // TC - O(NlogN) SC - O(1)
        int n = nums.length;
        for(int i = 0; i < n; i++){
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);

        return nums;
    }
}
