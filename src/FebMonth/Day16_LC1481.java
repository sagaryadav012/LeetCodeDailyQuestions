package FebMonth;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Day16_LC1481 {
    public static void main(String[] args) {
        int[] arr = {5,5,4};
        int k = 1;
        System.out.println(findLeastNumOfUniqueInts(arr, k));
    }
    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : arr){
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        PriorityQueue<Pair> q = new PriorityQueue<>((p1, p2) -> p1.val - p2.val);

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            q.add(new Pair(entry.getKey(), entry.getValue()));
        }
//        while(!q.isEmpty()){
//            Pair pair = q.poll();
//            System.out.println(pair.key + " "+ pair.val);
//        }
        int NumofUnique = map.size();
        for(Pair pair : q){
            k -= pair.val;
            NumofUnique -= 1;
            if(k < 0){
                NumofUnique += 1;
                break;
            }
        }
        return NumofUnique;
    }
    public static int findLeastNumOfUniqueInts1(int[] arr, int k) { // TC - O(N + KlogN) SC - O(N + N)
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : arr){
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        PriorityQueue<Integer> q = new PriorityQueue<>(map.values());

        int elementsRemoved = 0;
        while(!q.isEmpty()){
            elementsRemoved += q.peek();

            if(elementsRemoved > k){
                return q.size();
            }
            q.poll();
        }
        return 0;
    }
}
class Pair{
    int key;
    int val;

    Pair(int key, int val){
        this.key = key;
        this.val = val;
    }

//    @Override
//    public int compareTo(Pair o) {
//        return this.val - o.val;
//    }
}
