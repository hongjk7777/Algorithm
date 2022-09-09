import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {
        private Map<String, Integer> reportMap = new HashMap<>();
        private Map<String, ArrayList<String>> checkedMap = new HashMap<>();


        public int[] solution(String[] id_list, String[] reports, int k) {
            addIdListToMap(id_list);
            addReports(reports);
            String[] bannedIds = getBannedIds(k);

            return getAnswer(id_list, bannedIds);
        }

        private void addIdListToMap(String[] id_list) {
            for (String id : id_list) {
                reportMap.put(id, 0);
            }
        }

        private void addReports(String[] reports) {
            for (String report : reports) {
                String[] ids = report.split(" ");
                String reporter = ids[0];
                String reported = ids[1];

                if (checkedMap.containsKey(reporter) && checkedMap.get(reporter).contains(reported)) {
                    //이미 동일인물을 신고했을 경우
                    continue;
                } else {
                    if(!checkedMap.containsKey(reporter)){
                        checkedMap.put(reporter, new ArrayList<>());
                    }
                    checkedMap.get(reporter).add(reported);
                    reportMap.replace(reported, reportMap.get(reported) + 1);
                }

            }
        }

        private String[] getBannedIds(int limit) {
            ArrayList<String> bannedIds = new ArrayList<>();

            for(Map.Entry<String, Integer> entry : reportMap.entrySet()){
                if(entry.getValue() >= limit) {
                    bannedIds.add(entry.getKey());
                }
            }

            return bannedIds.toArray(new String[0]);
        }

        private int[] getAnswer(String[] id_list, String[] bannedIds) {
            int[] answer = new int[id_list.length];

            for (int i = 0; i < id_list.length; i++) {
                if(checkedMap.containsKey(id_list[i])){
                    for(String bannedId : bannedIds) {
                        if(checkedMap.get(id_list[i]).contains(bannedId)) {
                            answer[i]++;
                        }
                    }
                }
            }

            return answer;
        }
    }