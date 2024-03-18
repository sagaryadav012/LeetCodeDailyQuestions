package MarchMonth;

import java.util.Arrays;

public class Day4_LC948 {
    public static void main(String[] args) {
        int[] tokens = {71,55,82};
        int power = 54;
        Day4_LC948 obj = new Day4_LC948();
        System.out.println(obj.bagOfTokensScore(tokens, power));
    }
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int n = tokens.length;
        if(n == 0) return 0;
        int max_score = 0;
        int p1 = 0, p2 = n-1;

        int score = 0;
        while(p1 <= p2){
            if(power >= tokens[p1]){
                power -= tokens[p1];
                score += 1;
                max_score = Math.max(score, max_score);
                p1++;
                continue;
            }

            if(score >= 1){
                power += tokens[p2];
                score -= 1;
                p2--;
            }else{break;}
        }

        return max_score;
    }
}
