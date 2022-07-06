package Programmers;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Programmers_42587 {
    public static void main(String[] args) {
        new Solution().solution(new int[]{}, 0);
    }

    static class Solution {
        private Queue<Document> printQueue = new LinkedList<>();
        private PriorityQueue<Document> priorityQueue = new PriorityQueue<>((a, b) -> b.priority - a.priority);

        public int solution(int[] priorities, int location) {
            preparePrinting(priorities);

            return getAnswer(location);
        }

        private void preparePrinting(int[] priorities) {
            int location = 0;
            for(int priority : priorities) {
                Document document = new Document(priority, location++);
                printQueue.add(document);
                priorityQueue.add(document);
            }
        }

        private int getAnswer(int goalLocation) {
            int printCount = 1;

            while(true) {
                Document nextDoc = printQueue.peek();
                if(priorityQueue.peek().priority <= nextDoc.priority) {
                    if(nextDoc.location == goalLocation) {
                        break;
                    }
                    print();
                    printCount++;
                } else {
                    nextDoc = printQueue.poll();
                    printQueue.add(nextDoc);
                }
            }

            return printCount;
        }

        private void print() {
            printQueue.poll();
            priorityQueue.poll();
        }

        class Document {
            int priority, location;

            public Document(int priority, int location) {
                this.priority = priority;
                this.location = location;
            }
        }
    }
}
