package FebMonth;

import java.util.Arrays;

public class Day11_LC1463 {

    public static void main(String[] args) {
        Day11_LC1463 obj = new Day11_LC1463();
        int[][] grid = {
//                {3,1,1},
//                {2,5,1},
//                {1,5,5},
//                {2,1,1}
                {1,0,0,0,0,0,1},
                {2,0,0,0,0,3,0},
                {2,0,9,0,0,0,0},
                {0,3,0,5,4,0,0},
                {1,0,2,3,0,0,6}
        };
        System.out.println(obj.cherryPickup1(grid));
        System.out.println(obj.cherryPickup3(grid));
    }
    public int cherryPickup1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[m][n][n];

        for(int[][] matrix : dp){
            for(int[] row : matrix){
                Arrays.fill(row, -1);
            }
        }

       // return collectMaxCherry(0, 0, n-1, grid, m, n);
        return collectMaxCherry1(0, 0, n-1, grid, m, n, dp);
    }
    // Recursion :- TC - 3^n * 3^n SC - O(m)
    public int collectMaxCherry(int i, int j1, int j2, int[][] grid, int m, int n){
        if(j1 < 0 || j2 < 0 || j1 >= n || j2 >= n){
            return Integer.MIN_VALUE;
        }
        if(i == m-1){
            if(j1 == j2) return grid[i][j1];

            return  grid[i][j1] + grid[i][j2];
        }

        int maxi = 0;
        for(int dj1 = -1; dj1 <= 1; dj1++){
            for(int dj2 = -1; dj2 <= 1; dj2++){
                int value = 0;
                if(j1 == j2) value = grid[i][j1];
                else value = grid[i][j1] + grid[i][j2];

                value += collectMaxCherry(i+1, j1+dj1, j2+dj2, grid, m, n);
                maxi = Math.max(maxi, value);
            }
        }
        return maxi;
    }

    // Memoization : TC - O(M*N*N) SC (M*N*N + M)
    public int collectMaxCherry1(int i, int j1, int j2, int[][] grid, int m, int n, int[][][] dp){
        if(j1 < 0 || j2 < 0 || j1 >= n || j2 >= n){
            return Integer.MIN_VALUE;
        }
        if(i == m-1){
            if(j1 == j2) return grid[i][j1];

            return  grid[i][j1] + grid[i][j2];
        }
        if(dp[i][j1][j2] != -1) return dp[i][j1][j2];
        int maxi = 0;
        for(int dj1 = -1; dj1 <= 1; dj1++){
            for(int dj2 = -1; dj2 <= 1; dj2++){
                int value = 0;
                if(j1 == j2) value = grid[i][j1];
                else value = grid[i][j1] + grid[i][j2];

                value += collectMaxCherry1(i+1, j1+dj1, j2+dj2, grid, m, n, dp);
                maxi = Math.max(maxi, value);
            }
        }
        return dp[i][j1][j2] = maxi;
    }

    // Tabulation : TC - O(M*N*N) SC (M*N*N)
    public int cherryPickup3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[m][n][n];

        for(int j1 = 0; j1 < n; j1++){
            for(int j2 = 0; j2 < n; j2++){
                if(j1 == j2) dp[m-1][j1][j2] = grid[m-1][j1];
                else dp[m-1][j1][j2] = grid[m-1][j1] + grid[m-1][j2];
            }
        }

        for(int i = m-2; i >= 0; i--){
            for(int j1 = 0; j1 < n; j1++){
                for(int j2 = 0; j2 < n; j2++){
                    int maxi = 0;
                    for(int dj1 = -1; dj1 <= 1; dj1++){
                        for(int dj2 = -1; dj2 <= 1; dj2++){
                            int value = 0;
                            if(j1 == j2) value = grid[i][j1];
                            else value = grid[i][j1] + grid[i][j2];

                            if(j1+dj1 >= 0 && j1+dj1 < n && j2+dj2 >= 0 && j2+dj2 < n)
                               value += dp[i+1][j1+dj1][j2+dj2];
                            else value += Integer.MIN_VALUE;

                            maxi = Math.max(maxi, value);
                        }
                    }
                    dp[i][j1][j2] = maxi;
                }
            }
        }
        return dp[0][0][n-1];
    }

    // copied from leetcode
    public int cherryPickup2(int[][] grid) {
        int C = grid[0].length;
        int[][] dp = new int[C][C], old = new int[C][C];
        for(int r = grid.length - 1; r >= 0; r--) {
            for(int c1 = Math.min(r, C - 1); c1 >= 0; c1--) {
                for(int c2 = Math.max(c1, C - 1 - r); c2 < C; c2++) {
                    int max = 0;
                    for(int i = c1 - 1; i <= c1 + 1; i++) {
                        for(int j = c2 - 1; j <= c2 + 1; j++) {
                            if(i >= 0 && i < C && j >= 0 && j < C && i <= j) max = Math.max(max, old[i][j]);
                        }
                    }
                    dp[c1][c2] = max + grid[r][c1] + (c1 == c2 ? 0 : grid[r][c2]);
                }
            }
            int[][] temp = dp; dp = old; old = temp;
        }
        return old[0][C - 1];
    }
}
