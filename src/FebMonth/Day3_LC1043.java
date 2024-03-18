package FebMonth;

import java.util.Arrays;

public class Day3_LC1043 {
    public static void main(String[] args) {
        Day3_LC1043 obj = new Day3_LC1043();
        int[] arr = {1,15,7,9,2};
        int k = 3;
        System.out.println(obj.maxSumAfterPartitioning(arr, k));
    }
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
//        return maxSum(0, arr, n, k);
        return maxSum1(0, arr, n, k, dp);
    }

    // BruteForce --> TC - O(K^N) SC - O(N)
    public int maxSum(int idx, int[] arr, int n, int k){
        if(idx == n) return 0;

        int size = Math.min(idx+k, n);
        int len = 0; int maxVal = -1;
        int maxi = -1;
        for(int j = idx; j < size; j++){
            len += 1;
            maxVal = Math.max(maxVal, arr[j]);
            int sum = len * maxVal + maxSum(j + 1, arr, n, k);
            maxi = Math.max(maxi, sum);
        }

        return  maxi;
    }

    // Better --> TC - O(N*K) SC - O(N + N)
    public int maxSum1(int idx, int[] arr, int n, int k, int[] dp){
        if(idx == n) return 0;

        if(dp[idx] != -1) return  dp[idx];

        int size = Math.min(idx+k, n);
        int len = 0; int maxVal = -1;
        int maxi = -1;
        for(int j = idx; j < size; j++){
            len += 1;
            maxVal = Math.max(maxVal, arr[j]);
            int sum = len * maxVal + maxSum1(j + 1, arr, n, k, dp);
            maxi = Math.max(maxi, sum);
        }
        dp[idx] = maxi;
        return  maxi;
    }

    // BruteForce --> TC - O(K^N) SC - O(N)
//    public int maxSum2(int idx, int[] arr, int n, int k, int[] dp){
//
//    }

}
