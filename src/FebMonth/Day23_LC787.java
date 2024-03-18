package FebMonth;

import java.util.*;

public class Day23_LC787 {
    public static void main(String[] args) {
        int n = 4;
        int[][] flights = {
                {0,1,100},
                {1,2,100},
                {2,0,100},
                {1,3,600},
                {2,3,200}
        };
        int src = 0;
        int dst = 3;
        int k = 1;
        System.out.println(findCheapestPrice(n, flights, src, dst, k));

    }
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> routes = new ArrayList<>();
        for(int i = 0; i < n; i++){
            routes.add(0, new ArrayList<>());
        }

        for(int[] flight : flights){
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];

            routes.get(from).add(new int[]{to, price});

        }

//        for(List<int[]> city : routes){
//            for(int[] pair : city)
//              System.out.print(Arrays.toString(pair));
//            System.out.println();
//        }

        Queue<Pairr> queue = new LinkedList<>();
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);

        queue.add(new Pairr(0, src, 0));
        dis[src] = 0;

        while(!queue.isEmpty()){
            Pairr p = queue.poll();
            int stops = p.stops;
            int fromCity = p.city;
            int price = p.price;

            if(stops > k) continue;

            for(int[] route : routes.get(fromCity)){
                int toCity = route[0];
                int toCost = route[1];
                int newPrice = price + toCost;
                if(newPrice < dis[toCity]){
                    dis[toCity] = newPrice;
                    queue.add(new Pairr(stops+1, toCity, newPrice));
                }
            }
        }
        System.out.println(Arrays.toString(dis));
        return dis[dst] == Integer.MAX_VALUE ? -1 : dis[dst];
    }
}
class Pairr{
    int stops;
    int city;
    int price;
    Pairr(int stops, int city, int price){
        this.stops = stops;
        this.city = city;
        this.price = price;
    }
}
/*
Explanation :
-> Create Adjacency List
-> This is similar to Dijkstra Algo but Here we have to take care of stops
-> So store in queue stops, city, price and take distance array to store min distance.
-> initially add 0 stops, src city, 0 price.
-> Now poll from queue and calculate price to reach neighbour cities if new price less than previous price
   then update priceValue
-> if stops > k we continue since we don't want more than k stops
-> if k = 2, when we fetch pair, stops of this pair is 2, we will process it since it allows 2 stops
-> why won't use Priority Queue to fetch small stops? because when checking with neighbours we increment stops
   by one only and we put it in queue, so the data in queue is in sorted format only.
   if we increment stops value randomly then we PQ.

 */