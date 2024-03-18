package LCPractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Three3Sum {
    public static void main(String[] args) {
        int[] nums = {-1,-1,1,2,2,2,2,3,3,4,4,6};
        int target = 6;
        for(List<Integer> pair : threeSum1(nums, target)){
            System.out.println(pair);
        }
    }
    public static List<List<Integer>> threeSum1(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        for(int i = 0; i < n-2; i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int j = i+1;
            int k = n-1;
            int rSum = target - nums[i];
            while(j < k){
                int sum = nums[j]+nums[k];
                if(sum == rSum){
                    List<Integer> pair = Arrays.asList(nums[i], nums[j], nums[k]);
                    res.add(pair);

                    while(j < k && nums[j] == nums[j+1]) j++;
                    while(j < k && nums[k] == nums[k-1]) k--;

                    j++;
                    k--;
                }
                else if(sum < rSum) j++;
                else k--;
            }
        }

        return res;
    }
}
