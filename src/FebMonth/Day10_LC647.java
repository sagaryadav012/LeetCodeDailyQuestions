package FebMonth;

public class Day10_LC647 {
    public static void main(String[] args) {
        String s = "gcgc";
        System.out.println(countSubstrings1(s));
        System.out.println(countSubstrings2(s));
        System.out.println(countSubstrings3(s));
    }
    public static int countSubstrings1(String s) { // TC - O(N*N + N*N) SC - O(N*N)
        int n = s.length();
        int total_substrings = n * (n+1)/2;
        int[][] ss = new int[total_substrings][2];

        int k = 0;
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                ss[k++] = new int[]{i, j};
            }
        }

        int count = 0;
        for(int[] subString : ss){
            if(isPalindrome(subString[0], subString[1], s)) count++;
        }

        return count;
    }
    public static boolean isPalindrome(int s, int e, String str){
        while(s < e){
            if(str.charAt(s) != str.charAt(e)) return false;
            s++;
            e--;
        }
        return true;
    }

    public static int countSubstrings2(String s) { // TC - O(N*N) SC - O(N*N)
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int j = 0; j < n; j++){
            for(int i = 0; i < n; i++){
                if(i >= j) dp[i][j] = 1;
                else if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1] == 1) dp[i][j] = 1;
                else dp[i][j] = 0;
            }
        }

        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                if(dp[i][j] == 1)  count++;
            }
        }
        return count;
    }

    public static int countSubstrings3(String s) { // TC - O(N*N) SC - O(1)
        int n = s.length();
        char[] arr = s.toCharArray();

        int count = 0;
        for(int i = 0; i < n; i++){
            int oddCount = getPalindromesCount(i, i, n, arr);
            int evenCount = getPalindromesCount(i, i+1, n, arr);
            count += oddCount + evenCount;
        }
        return count;
    }
    public static int getPalindromesCount(int s, int e, int n, char[] str){
        int res = 0;
        while(s >= 0 && e < n && str[s] == str[e]){
            res++;
            s--;
            e++;
        }
        return res;
    }
}
