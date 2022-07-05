package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_42583 {
    public static void main(String[] args) {
        new Solution().solution(0, 0, new int[]{});
    }

    static class Solution {
        public int solution(int bridge_length, int weight, int[] truck_weights) {
            int time = 0, finished = 0, curWeight = 0, nextTruck = 0;
            Queue<Truck> truckOnBridge = new LinkedList<>();

            while(finished < truck_weights.length) {
                if(!truckOnBridge.isEmpty() &&
                        time - truckOnBridge.peek().time >= bridge_length) {
                    curWeight -= truckOnBridge.poll().weight;
                    finished++;
                }

                if(nextTruck < truck_weights.length &&
                        curWeight + truck_weights[nextTruck] <= weight) {
                    curWeight += truck_weights[nextTruck];
                    truckOnBridge.add(new Truck(time, truck_weights[nextTruck++]));
                }
                time++;
            }
            return time;
        }

        class Truck {
            int time, weight;

            public Truck(int time, int weight) {
                this.time= time;
                this.weight = weight;
            }
        }
    }
}
