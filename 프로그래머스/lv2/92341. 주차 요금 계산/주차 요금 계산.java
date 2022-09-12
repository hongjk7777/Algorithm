import java.util.*;

class Solution {
        private static final String LAST_TIME = "23:59";
        private int defaultTime, defaultCost, unitTime, unitCost;
        private Map<String, Integer> enterRecord = new HashMap<>();
        private Map<String, Integer> totalRecord = new HashMap<>();

        public int[] solution(int[] fees, String[] records) {
            defaultTime = fees[0];
            defaultCost = fees[1];
            unitTime = fees[2];
            unitCost = fees[3];
            calcParkingCost(records);
            return getOrderedCostList();
        }

        private void calcParkingCost(String[] records) {
            for (String record : records) {
                String[] infos = record.split(" ");
                int curTime = transferTime(infos[0]);
                String carNumber = infos[1];
                String type = infos[2];

                if (enterRecord.containsKey(carNumber)) {
                    int enterTime = enterRecord.get(carNumber);
                    int totalTime = curTime - enterTime;
                    updateTotalMap(carNumber, totalTime);
                    enterRecord.remove(carNumber);
                } else {
                    enterRecord.put(carNumber, curTime);
                }
            }

            calcRemainCars();
        }

        private int transferTime(String info) {
            int hour = Integer.parseInt(info.substring(0, 2));
            int minute = Integer.parseInt(info.substring(3, 5));

            return hour * 60 + minute;
        }

        private int getCost(int totalTime) {
            int cost = defaultCost;

            if (totalTime > defaultTime) {
                int extraTime = (totalTime - defaultTime) / unitTime;

                if ((totalTime - defaultTime) % unitTime != 0) {
                    extraTime++;
                }

                cost += extraTime * unitCost;
            }
            return cost;
        }

        private void updateTotalMap(String carNumber, int cost) {
            if (totalRecord.containsKey(carNumber)) {
                totalRecord.replace(carNumber, totalRecord.get(carNumber) + cost);
            } else {
                totalRecord.put(carNumber, cost);
            }
        }

        private void calcRemainCars() {
            for (String carNumber : enterRecord.keySet()) {
                int enterTime = enterRecord.get(carNumber);
                int totalTime = transferTime(LAST_TIME) - enterTime;
                updateTotalMap(carNumber, totalTime);
            }
        }

        private int[] getOrderedCostList() {
            ArrayList<String> carNumbers = new ArrayList<>();
            for (String carNumber : totalRecord.keySet()) {
                carNumbers.add(carNumber);
            }
            Collections.sort(carNumbers);

            int[] orderedCostList = new int[carNumbers.size()];
            for (String carNumber : totalRecord.keySet()) {
                int cost = getCost(totalRecord.get(carNumber));
                int index = carNumbers.indexOf(carNumber);
                orderedCostList[index] = cost;
            }

            return orderedCostList;
        }
    }