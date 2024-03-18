package FebMonth;

import java.util.*;

public class Day24_LC2092 {
    public static void main(String[] args) {
        int n = 6;
        int[][] meetings = {
//                {3,1,3},
//                {1,2,2},
//                {0,3,3}
                {1,2,5},
                {2,3,8},
                {1,5,10}
        };
        int firstPerson = 1;
        System.out.println(findAllPeople(n, meetings, firstPerson));
    }
    // TC - O(VlogE) SC - O(V+E)
    public static List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        List<List<int[]>> graphMeetings = new ArrayList<>();

        for(int i = 0; i < n; i++){
            graphMeetings.add(new ArrayList<>());
        }

        for(int[] meeting : meetings){
            int p1 = meeting[0];
            int p2 = meeting[1];
            int time = meeting[2];
            // p1 has meeting with p2 -> means -> p2 has meeting with p1
            graphMeetings.get(p1).add(new int[]{time, p2});
            graphMeetings.get(p2).add(new int[]{time, p1});
        }

        int[] knownTimes = new int[n];
        // assume all persons knows secret at 10^6 time
        Arrays.fill(knownTimes, 1000000);

        // 0 and firstPerson knows secret
        knownTimes[0] = 0;
        knownTimes[firstPerson] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // int[] time, person
        // there is a chance that 0 can have meetings with other members. so may others can also know secret from 0
        pq.add(new int[]{0, 0});
        pq.add(new int[]{0, firstPerson});

        while(!pq.isEmpty()){
            int[] pair = pq.poll();
            int t1 = pair[0];
            int p1 = pair[1];

            for(int[] meeting : graphMeetings.get(p1)){
                int t2 = meeting[0];
                int p2 = meeting[1];

                if(t1 > t2) continue;

                if(knownTimes[p2] > t2){
                    knownTimes[p2] = t2;
                    pq.add(new int[] {t2, p2});
                }
            }
        }
        List<Integer> persons = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int time = knownTimes[i];
            if(time == 1000000) continue;
            persons.add(i);
        }

        return persons;
    }
}

/*
Test Cases :
n = 11
meetings = [[5,1,4],[0,4,18]]
firstPerson = 1;
ans = {};

n = 6
meetings = [[0,2,1],[1,3,1],[4,5,1]]
firstPerson = 1
ans = {}


Approach :
-> We can fetch meetings which scheduled first using PQ and we maintain boolean array to track who knows secret
   Ex : [1,3,5] [2,4,1] [3,6,6] and 0 shared secret to first person 1
   knownPersons -> 0,1,3,6
   1 -> [2,4,1] : 2 has meeting with 4 at time 1 -> neither 2 nor 4 know secret.
   2 -> [1,3,5] : 1 has meeting with 3 at time 5 -> 1 shared secret with 3.
   3 -> [3,6,6] : 3 has meeting with 6 at time 6 -> 3 shared secret with 6.

-> So we add all meetings to PQ and fetch one by one and we can mark who shared secret to who.
-> But the problem is at a time multiple meetings can happen, In that case how can we solve?
   Ex : At time 3 meetings scheduled are [1,5] [4,2] [2,1] [3,5] in these 4 knows secret
        From 4, 2 knows. From 2, 1 knows. From 1, 5 knows, From 5, 3 knows.

-> The above problem can be solved by graphs.
-> Create adjacency list, remember it is undirected graph.
-> Create PQ, it sorted on time. Put which person knows secret at what time and check the meetings with this person.
-> Here we have to handle 2 cases
   1 : A person doesn't know secret but later he knows
   Ex: 2 has meeting with 4 and 8 at time 3 and 6 respectively.
       At time 5, 3 has meeting with 2 and 3 knows secret and shared with 2 at time 5.
       But 2 has meeting with 4 at time 3 and it has ended before 3 come and tells secret. so we can't go
       back and tell right? so 2 tells secret to 8 only at time 6, it comes after 3 and 2's meeting
       So check line 57 -> t1 > t2 continue;

   2 : another case : example 6 knows secret at time 4, now 2 has meeting with 6 at time 9. 2 know secret now
       2 tells secret to 6 but 6 knows at time 4 only, 2 came at time 9, 4 < 9 so we store 4 at 6 because
       if 6 has meeting with some others at 5 or 6 or 7 then can share right if we store 9 we can't share.

       handled at 59 line knownTimes[p2] > t2 then only update.
 */