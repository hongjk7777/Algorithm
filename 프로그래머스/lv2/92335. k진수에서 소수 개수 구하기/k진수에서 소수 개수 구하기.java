class Solution {
        public int solution(int n, int k) {
            int count = 0;
            String changedNum = changeNotation(n, k);
            String[] splitNumArr = changedNum.split("0");
            for (String splitNum : splitNumArr) {
                if (isPrime(splitNum)) {
                    count++;
                }
            }

            return count;
        }

        private String changeNotation(int n, int k) {
            StringBuilder changedNum = new StringBuilder();
            while (n > 0) {
                int remainder = n % k;
                changedNum.append(remainder);
                n /= k;
            }

            return changedNum.reverse().toString();
        }

        private boolean isPrime(String splitNum) {
            if (splitNum.equals("")) {
                return false;
            }

            long num = Long.parseLong(splitNum);

            if (num == 1){
                return false;
            }

            long limit = (long) Math.sqrt(num) + 1;

            for (int i = 2; i < limit; i++) {
                if (num % i == 0) {
                    return false;
                }
            }

            return true;
        }
    }