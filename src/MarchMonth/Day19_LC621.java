package MarchMonth;

import java.util.Arrays;

public class Day19_LC621 {
    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C'};
        int n = 2;
        System.out.println(leastInterval(tasks, n));
    }
    public static int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(char task : tasks){
            freq[task - 'A']++;
        }
        Arrays.sort(freq);
        int chunk = freq[25] - 1;
        int idleSpots = chunk * n;

        for(int i = 24; i >= 0; i--){
            idleSpots -= Math.min(chunk, freq[i]);
        }

        return idleSpots < 0 ? tasks.length : idleSpots + tasks.length;
    }
}
/*

-> Cal freq of each task.
-> Execute max freq first so that we can fill idle spots with other task. if execute less freq task it leads to
   give max size.
   Ex : A - 2, B - 3

   A    B   A   B   _   B  :  total time is 6
   Place B first and try
   B    A   B   A   B : total time is 5

-> Ex : A - 3, B - 2, C - 2 and n = 2

execute A first     A   _   _   A   _   _   A
Execute B           A   B   _   A   B   _   A
Execute C           A   B   C   A   B   C   A
Total time here is 7

-> First find chunk size : Chunk size is maxFreq - 1 : 3-1 = 2
-> Now we can find idle spot after we executed maxFreq task : chunk * n = 2 * 2 = 4
-> Fill these idle spots with other tasks.




 */