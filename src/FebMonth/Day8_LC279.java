package FebMonth;

public class Day8_LC279 {
    public static void main(String[] args) {
        int n = 60;
        int[] dp = new int[n+1];

        System.out.println(numSquares2(n));
        System.out.println(numSquares1(n, dp));
        System.out.println(numSquares(n));
    }
    public static int numSquares(int n){ // TC - O(sqrt(N) ^ N) SC - O(N)
       if(n == 0) return 0;
       if(n < 0) return Integer.MAX_VALUE;

       int min = Integer.MAX_VALUE;
       for(int i = 1; i*i <= n; i++){
           int count = numSquares(n - i*i);
           min = Math.min(count, min);
       }
       return min + 1;
    }
    public static int numSquares1(int n, int[] dp){ // TC - O(N * sqrt(N)) SC - O(N + N)
        if(n == 0) return 0;
        if(n < 0) return Integer.MAX_VALUE;

        int min = Integer.MAX_VALUE;
        for(int i = 1; i*i <= n; i++){
            int count = Integer.MAX_VALUE;

            if(dp[n - i*i] != 0) count = dp[n - i*i];
            else count = numSquares1(n - i*i, dp);

            min = Math.min(count, min);
        }
        return dp[n] = min + 1;
    }
    public static int numSquares2(int n){ // TC - O(N * sqrt(N)) SC - O(N)
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++){
            int squareCount = Integer.MAX_VALUE;
            for(int j = 1; j*j <= i; j++){
               squareCount = Math.min(squareCount, dp[i - j*j]);
            }
            dp[i] = squareCount + 1;
        }
        return dp[n];
    }
}
