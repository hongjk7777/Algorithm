package Programmers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Programmers_42888 {
    public static void main(String[] args) {
        new Solution().solution(new String[]{});
    }

    static class Solution {
        Map<String, String> userMap = new HashMap<>();
        Queue<Log> logQueue= new LinkedList<>();
        String[] answer;

        public String[] solution(String[] records) {

            for (String record : records) {
                manageLog(record);
            }

            answer = new String[logQueue.size()];
            int num = 0;
            while(!logQueue.isEmpty()){
                appendLog(num++);
            }

            return answer;
        }

        public void manageLog(String record) {
            String[] data = record.split(" ");
            String type = data[0];
            String id = data[1];
            String userName;

            if(type.equals("Enter")){
                userName = data[2];
                if(!userMap.containsKey(id)){
                    userMap.put(id, userName);
                } else if(!userMap.get(id).equals(userName)) {
                    userMap.remove(id);
                    userMap.put(id, userName);
                }
                logQueue.add(new Log(type, id));
            } else if(type.equals("Leave")) {
                logQueue.add(new Log(type, id));
            } else {
                userName = data[2];
                userMap.remove(id);
                userMap.put(id, userName);
            }
        }

        public void appendLog(int num) {
            StringBuilder builder = new StringBuilder();
            Log poll = logQueue.poll();
            String userName = userMap.get(poll.id);

            builder.append(userName);
            if(poll.type.equals("Enter")) {
                builder.append("님이 들어왔습니다.");
            } else {
                builder.append("님이 나갔습니다.");
            }

            answer[num] = builder.toString();
        }

        class Log {
            String type, id;

            public Log(String type, String id) {
                this.type = type;
                this.id = id;
            }
        }
    }
}
