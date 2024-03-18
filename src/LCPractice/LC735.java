package LCPractice;

import java.util.Arrays;
import java.util.Stack;

public class LC735 {
    public static void main(String[] args) {
        int[] asteroids = {2,-1,1,-2};
        LC735 obj = new LC735();
        System.out.println(Arrays.toString(obj.asteroidCollision(asteroids)));
        System.out.println(Arrays.toString(obj.asteroidCollision1(asteroids)));
    }
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for(int asteroid : asteroids) {
            if(stack.isEmpty()){
                stack.push(asteroid);
                continue;
            }

            int lastAsteroid = stack.peek();
            if(lastAsteroid >= 0 && asteroid >= 0 ||
                    lastAsteroid < 0 && asteroid < 0 || lastAsteroid < asteroid){
                stack.push(asteroid);
                continue;
            }

            int absLastAsteroidSize = Math.abs(lastAsteroid);
            int absCurAsteroidSize = Math.abs(asteroid);

            if(absLastAsteroidSize == absCurAsteroidSize){
                stack.pop();
                continue;
            }

            if(absLastAsteroidSize > absCurAsteroidSize){
                continue;
            }

            while(!stack.isEmpty()){
                int last_asteroid = stack.peek();

                // -8 and -15
                if(last_asteroid < 0){
                    stack.push(asteroid);
                    break;
                }

                // 16 and 15
                if(last_asteroid > absCurAsteroidSize){
                    break;
                }

                // +15 and -15
                if(last_asteroid == absCurAsteroidSize){
                    stack.pop();
                    break;
                }
                // 10 and -15
                if(last_asteroid < absCurAsteroidSize){
                    stack.pop();
                }
            }
            if(stack.isEmpty()) stack.add(asteroid);
        }

        int n = stack.size();
        int[] res = new int[n];

        while(!stack.isEmpty()){
            res[n-1] = stack.pop();
            n -= 1;
        }

        return res;
    }
    public int[] asteroidCollision1(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for(int asteroid : asteroids){
            // if stack is empty of we get +ve values their won't be collision, so add to stack
            if(stack.isEmpty() || asteroid > 0){
                stack.push(asteroid);
                continue;
            }
            // when we encounter -ve check is peek value is +ve. If it's +ve, check < cur val, if yes pop it out
            while(!stack.isEmpty() && stack.peek() > 0 &&  stack.peek() < Math.abs(asteroid)){
                stack.pop();
            }

            // In above while loop we pop out all value that are < cur Val, if we get equal +ve value,
            // We have to pop it as well since both will be collided(same size).
            // like 10 2,3,5,6,7,-10 (-10 is cur val)
            if(!stack.isEmpty() && stack.peek() > 0 && stack.peek() == Math.abs(asteroid)){
                stack.pop();
                continue;
            }


            // If above loop is not get executed, this if will executes for condition like  -3, 2, 3, 4, -10
            // cur val is -10, it pops all until get same sign or until it get's smaller than this

            if(stack.isEmpty() || stack.peek() < 0){
                stack.push(asteroid);
            }
        }

        int n = stack.size();
        int[] res = new int[n];

        while(!stack.isEmpty()){
            res[n-1] = stack.pop();
            n -= 1;
        }

        return res;
    }
}
