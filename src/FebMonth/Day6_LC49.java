package FebMonth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day6_LC49 {
    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> lists = groupAnagrams(strs);
        for(List<String> list : lists){
            System.out.println(list);
        }
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for(String str : strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String st = new String(chars);

            if(!map.containsKey(st)){
                map.put(st, new ArrayList<>());
            }

            map.get(st).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
