package MarchMonth;

import java.util.Arrays;

public class Day5_LC1750 {
    public static void main(String[] args) {
        Day5_LC1750 obj = new Day5_LC1750();

        String s = "Hi Hello";
        System.out.println(obj.minimumLength(s));

        int n = 2;
        int[] ans = new int[n+1];
        for(int i = 0; i <= n; i++){
            int count = 0;;
            while(i > 0){
                if((i&1) == 1) count += 1;
                i >>= 1;
            }
            ans[i] = count;
        }
        System.out.println(Arrays.toString(ans));
    }
    public int minimumLength(String s) {
        char[] chars = s.toCharArray();

        int n = s.length();

        int p1 = 0, p2 = n-1;

        while(p1 < p2){
            if(chars[p1] != chars[p2]){
                return p2-p1+1;
            }

            while(p1 < p2 && chars[p1] == chars[p1+1]){
                p1++;
            }
            while(p2 > p1 && chars[p2] == chars[p2-1]){
                p2--;
            }
            p1++;
            p2--;
        }

        return p1 == p2 ? 1 : 0;
    }
}

/*
Test cases :
"cabaabac"
"aabccabba"
"a"
"bbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbbbbbbbbbbbbccbcbcbccbbabbb"
"aaaaaaaaaaaaa"
 */