package FebMonth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day9_LC368 {
    public static void main(String[] args) {
        int[] nums = {4,6,2,3,8,16,32,9};
        System.out.println(largestDivisibleSubset(nums));
        System.out.println(largestDivisibleSubset1(nums));
    }
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int[] count = new int[n];
        int[] prev = new int[n];

        int maxCount = 0;
        int index = -1;
        for(int i = 0; i < n; i++){
            count[i] = 1;
            prev[i] = -1;
            for(int j = i-1; j >= 0; j--){
                if(nums[i] % nums[j] == 0){
                    if(1 + count[j] > count[i]){
                        count[i] = count[j] + 1;
                        prev[i] = j;
                    }
                }
            }
            if(count[i] > maxCount){
                maxCount = count[i];
                index = i;
            }
        }

        List<Integer> res = new ArrayList<>();
        while(index != -1){
            res.add(nums[index]);
            index = prev[index];
        }

        return  res;

//        Arrays.sort(nums);
//        int n = nums.length;
//        int[] maxLen = new int[n];
//        Arrays.fill(maxLen, 1);
//
//        for(int i = 1; i < n; i++){
//            for(int j = i-1; j >= 0; j--){
//                if(nums[i] % nums[j] == 0){
//                    maxLen[i] = Math.max(maxLen[j] + 1, maxLen[i]);
//                }
//            }
//        }
//
//        int max = 0;
//        int maxVal = -1;
//        for(int i = 0; i < n; i++){
//            if(max < maxLen[i]){
//                max = maxLen[i];
//                maxVal = nums[i];
//            }
//        }
//
//        List<Integer> res = new ArrayList<>();
//        res.add(maxVal);
//
//        for(int i = n-1; i >= 0; i--){
//            if(maxLen[i] < max && maxVal % nums[i] == 0){
//                res.add(nums[i]);
//            }
//        }
//
//        return res;
    }
    static List<Integer> ans = new ArrayList<>();
    public static List<Integer> largestDivisibleSubset1(int[] nums) { // TC - O(2^n) SC - O(n + n)
        List<Integer> subset = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        subSets(0, 1, nums, n, subset);
        return ans;
    }

    public static void subSets(int index, int prev, int[] nums, int n, List<Integer> subset){
        if(index == n){
            List<Integer> res = new ArrayList<>(subset);
            if(ans.size() < res.size()){
                ans = new ArrayList<>(res);
            }
            return;
        }
        if(nums[index] % prev == 0){
            subset.add(nums[index]);
            subSets(index + 1, nums[index], nums, n, subset);
            subset.remove(subset.size() - 1);
        }
        subSets(index + 1, prev, nums, n, subset);
    }
}

/*
Explanation :

Approach 1 :
-> Generate all subsets while generate check nums[i]%nums[j] == 0 or nums[j]%nums[i] == 0, if yes add to list
-> TC - O(2^n) SC - O(n + n)


Approach 2 :
-> similar as Longest increasing subsequence
-> we know that a%b -> Here b <= a then only it is possible to get 0
-> so if we sort array we can check only one condition i.e. num[i]%num[j] == 0 or not
-> since we are finding longest subset means subset doesn't consider sequence so sorting given nums is fine.
-> if we don't sort array we have to check both conditions nums[i]%nums[j] == 0 or nums[j]%nums[i] == 0
-> example - 2,4,8 -> if 8%4 = 0 then 8%2 = 0 since 4%2 = 0.
-> Iterate over array check nums[i] % nums[j] == 0, if yes take LIS count + 1 or max till nums[i];
-> while iterating track the sequence to print subset
-> TC - O(NlogN + N^2 + N)  SC - O(N + N)
 */
