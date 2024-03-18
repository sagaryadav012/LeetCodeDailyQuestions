package FebMonth;

import java.util.*;

public class Day25_LC2709 {
    public static void main(String[] args) {
        int[] nums = {30,45,30,42,50,10,20,30,10};
        Day25_LC2709 obj = new Day25_LC2709();

//        System.out.println(Arrays.toString(obj.createSPF(nums)));
//        System.out.println();
        System.out.println(obj.canTraverseAllPairs(nums));
        System.out.println(obj.canTraverseAllPairs1(nums));
    }
    public boolean canTraverseAllPairs(int[] nums) {
        int[] spf = createSPF(nums);
        Map<Integer, List<Integer>> map = new HashMap<>();


//        for(int num : nums){
//            System.out.println(num+" : "+ primeFactorization(num, spf));
//        }

        for(int i = 0; i < nums.length; i++){
            for(int pf : primeFactorization(nums[i], spf)){
                List<Integer> list = map.getOrDefault(pf, new ArrayList<>());
                list.add(i);
                map.put(pf, list);
            }
        }

//        for(int key : map.keySet()){
//            System.out.println(key +" : " + map.get(key));
//        }

        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            graph.add(i, new ArrayList<>());
        }

        for(int key : map.keySet()){
            List<Integer> indexes = map.get(key);
            for(int i = 1; i < indexes.size(); i++){
                int u = indexes.get(i-1);
                int v = indexes.get(i);
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
        }

        boolean[] vis = new boolean[nums.length];
        dfs(graph, vis, 0);

        return count == nums.length;
    }
    public int getMax(int[] nums){
        int maxVal = 0;
        for(int i = 0; i < nums.length; i++){
            maxVal = Math.max(maxVal, nums[i]);
        }
        return maxVal;
    }
    public int[] createSPF(int[] nums){
        int n = getMax(nums);
        int[] spf = new int[n+1];
        for(int i = 1; i <= n; i++){
            spf[i] = i;
        }

        for(int i = 2; i*i <= n; i++){
            if(spf[i] != i) continue;
            for(int j = i*i; j <= n; j += i){
                spf[j] = Math.min(spf[j], i);
            }
        }

        return spf;
    }

    public List<Integer> primeFactorization(int num, int[] spf){
        Map<Integer, Integer> map = new HashMap<>();

        while(num != 1){
            int sp = spf[num];
            map.put(sp, 1);
            num = num/sp;
        }

        List<Integer> primeFactors = new ArrayList<>();
        for(int key : map.keySet()){
            primeFactors.add(key);
        }

        return primeFactors;
    }

    int count = 0;
    public void dfs(List<List<Integer>> graph, boolean[] vis, int index){
        if(vis[index]) return;

        vis[index] = true;
        count += 1;

        for(int neighbour : graph.get(index)){
            if(vis[neighbour]) continue;
            dfs(graph, vis, neighbour);
        }
    }


    // Other Approach :

    int[] parent;
    int[] countt;

    int max;
    public boolean canTraverseAllPairs1(int[] nums) {
        for (int x : nums)
            max = Math.max(max, x);

        int n = nums.length;
        parent = new int[max +1];
        countt = new int[max+1];

        if(n == 1) return true;

        for(int i = 0; i < n; i++)
            if(nums[i]==1) return false;

        for(int i = 1; i <= max; i++)
            parent[i] = i;

        for(int x:nums)
            countt[x]++;


        boolean[] visited = new boolean[max+1];

        for(int i = 2; i* 2 <= max; i++){
            if(visited[i]) continue;
            for(int j = i+i; j <= max; j+=i){
                visited[j] = true;
                if(countt[j]!=0){
                    union(i, j);
                }
            }
        }

        int p = find(nums[0]);
        for(int i = 1; i < n; i++)
            if(find(nums[i]) != p) return false;

        return true;
    }

    private int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    private void union(int x, int y) {

        x = find(x);
        y = find(y);
        if (x == y)
            return;

        parent[y] = x;
    }
}
