package FebMonth;

import java.util.*;

public class Day7_LC451 {
    public static void main(String[] args) {
        String s = "Aabb";
        System.out.println(frequencySort(s));
        System.out.println(frequencySort1(s));
    }
    public static String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        List<int[]> pairs = new ArrayList<>();
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            pairs.add(new int[]{entry.getKey(), entry.getValue()});
        }

        Collections.sort(pairs, (p1, p2) -> {return p2[1] - p1[1];});

        StringBuilder sb = new StringBuilder();
        for(int[] p : pairs){
            int freq = p[1];
            while(freq > 0){
                sb.append((char)p[0]);
                freq--;
            }
        }

        return sb.toString();
    }
    public static String frequencySort1(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] freq = new int[128];

        for(char c : chars){
            freq[c]++;
        }

        for(int i = 0; i < n;){
            char c = ',';
            for(int j = 0; j < 128; j++){
                if(freq[j] > freq[c]) c = (char)j;
            }
            while(freq[c] != 0){
                chars[i++] = c;
                freq[c]--;
            }
        }

        return new String(chars);
    }
}
