// gready_easy_11 --------------------------------------------------------------- K Items With the Maximum Sum / 61.1%
class Solution {
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if(k<=numOnes){      //k가 numOnes보다 작다면
            return k;
        }else if(k<=numOnes + numZeros){     //k가 numOnes + numZeros 보다 작다면
            return numOnes;
        }else if(k<=numOnes + numZeros + numNegOnes){   //k가 numOnes + numZeros + numNegOnes 보다 작다면
            int leftK = k-(numOnes+numZeros);
            return numOnes - leftK;
        }
        return 1;
    }
}
// gready_easy_12 --------------------------------------------------------------- Maximum Difference by Remapping a Digit / 58.9%
class Solution {
    public int minMaxDifference(int num) {
        String strNum = Integer.toString(num);
        String strFirstDigit = "";

        //최대 = 첫번째를 9가 아닌 것을 9로 변경
        for(int i=0; i<strNum.length(); i++){
            if(strNum.charAt(i) != '9'){
                strFirstDigit = "" + strNum.charAt(i);
                break;
            }
        }
        String maxStr = "";
        if(strFirstDigit == ""){
            maxStr = strNum;
        }else{
            maxStr = strNum.replaceAll(strFirstDigit, "9");
        }
        //최소 = 첫번째를 0으로 변경
        String digitForMin = strFirstDigit = "" + strNum.charAt(0);
        String minStr = strNum.replaceAll(digitForMin, "0");

        return Integer.parseInt(maxStr) - Integer.parseInt(minStr);
    }
}

// gready_easy_13 --------------------------------------------------------------- Minimum Amount of Time to Fill Cups / 57.1% 
// 결국 못풀었다... 자괴감 미친
// 근데 정답처럼 확실히 풀이법을 간파하지 못한 것도 사실임.
class Solution {
    public int fillCups(int[] amount) {
        int ans = 0;
        while (amount[0] + amount[1] + amount[2] > 0) {
            Arrays.sort(amount);
            ans++;
            //가장 큰 거 빼주고
            amount[2]--;
            //중간애를 1개 빼주거나 0으로 변경
            amount[1] = Math.max(0, amount[1] - 1);
        }
        return ans;
    }
}


// gready_easy_14 --------------------------------------------------------------- Minimum Moves to Convert String / 54.4%
class Solution {
    public int minimumMoves(String s) {
        int idx = 0;
        int ans = 0;
        while(idx < s.length()){
            if(s.charAt(idx) == 'X'){
                idx+=3;
                ans++;
            }else{
                idx++;
            }
        }
        return ans;
    }
}

// gready_easy_15 --------------------------------------------------------------- Lemonade Change / 53.1%
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int cnt5 = 0;
        int cnt10 = 0;
        for(int i=0; i<bills.length; i++){
            if(bills[i] == 5){
                cnt5 ++;
            }else if(bills[i] == 10){
                cnt5 --;
                cnt10 ++;
            }else if(bills[i] == 20){
                if(cnt10 >= 1){
                    cnt5 --;
                    cnt10 --;
                }else{
                    cnt5 -=3;
                }
            }
            if(cnt5 < 0){
                return false;
            }
        }
        return true;
    }
}


// gready_easy_16 --------------------------------------------------------------- Assign Cookies / 49.7%
// 최소 1개 이상
// g.length 명 있고
// 각 최소 g[i]만큼 원한다. (커도 상관없음)
// 최대 g.length가 30000이고
// 최대 s.length가 3000이다.
// 무지성 2중 포문 돌리면 900,000,000 아슬아슬하게 가능할지도?
// => 가능

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        int answer = 0;
        boolean[] visited = new boolean[s.length];
        for(int i=0; i<g.length; i++){
            int minVal = Integer.MAX_VALUE;
            int minIdx = -1;
            for(int j=0; j<s.length; j++){
                if(visited[j] == false){    //할당되지 않은 쿠키라면
                    if(g[i] <= s[j] && s[j] < minVal){  //g[i]보다 큰 것 중에 가장 작아야해
                        minVal = s[j];
                        minIdx = j;
                    }
                }
            }
            if(minIdx != -1){
                answer ++;
                visited[minIdx] = true;
            }
        }

        return answer;
    }
}

// 정렬 후 s의 Idx를 증가하는 방법
// visited가 필요없는 이유는
// 둘 다 정렬되어있으니 작아서 넘어간 것은 어짜피 사용할 수 없다.

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int answer = 0;
        int sIdx = 0;
        for(int i=0; i<g.length; i++){
            while(sIdx < s.length){
                if(g[i] <= s[sIdx]){
                    answer++;
                    sIdx++;
                    break;
                }else{
                    sIdx++;
                }
            }
        }

        return answer;
    }
}

// gready_easy_17 --------------------------------------------------------------- Latest Time by Replacing Hidden Digits / 42.5%
class Solution {
    public String maximumTime(String time) {
        String[] splitedTime = time.split("");
        String hour1 = splitedTime[0];
        String hour2 = splitedTime[1];
        String min1 = splitedTime[3];
        String min2 = splitedTime[4];

        //시
        if(hour1.equals("?")){
            if(hour2.equals("3") || hour2.equals("2") || hour2.equals("1") || hour2.equals("0")|| hour2.equals("?")){
                hour1 = "2";
            }else{
                hour1 = "1";
            }
        }
        if((hour1.equals("0") || hour1.equals("1")) && hour2.equals("?")){
            hour2 = "9";
        } else if(hour1.equals("2") && hour2.equals("?")){
            hour2 = "3";
        }

        //분
        if(min1.equals("?")){
            min1 = "5";
        }
        if(min2.equals("?")){
            min2 = "9";
        }
        
        return hour1 + hour2 + ":"+ min1 + min2;
    }
}

// split은 확실히 느리다
class Solution {
    public String maximumTime(String time) {
        char hour1 = time.charAt(0);
        char hour2 = time.charAt(1);
        char min1 = time.charAt(3);
        char min2 = time.charAt(4);

        //시
        if(hour1 == '?'){
            if(hour2 == '3' || hour2 == '2' || hour2 == '1' || hour2 == '0'|| hour2 == '?'){
                hour1 = '2';
            }else{
                hour1 = '1';
            }
        }
        if((hour1 == '0' || hour1 == '1') && hour2 == '?'){
            hour2 = '9';
        } else if(hour1 == '2' && hour2 == '?'){
            hour2 = '3';
        }

        //분
        if(min1 == '?'){
            min1 = '5';
        }
        if(min2 == '?'){
            min2 = '9';
        }
        StringBuilder sb = new StringBuilder();
        sb.append(hour1);
        sb.append(hour2);
        sb.append(":");
        sb.append(min1);
        sb.append(min2);
        return sb.toString();
    }
}

// gready_easy_18 --------------------------------------------------------------- Minimum Hours of Training to Win a Competition / 40.9%
class Solution {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int needEnergySum = 0;
        int needExperienceSum = 0;
        for(int i=0; i<energy.length; i++){
            //에너지
            needEnergySum += energy[i];
            //경험
            if(initialExperience > experience[i]){
                initialExperience += experience[i];
            }else{
                int needExperience = (experience[i] - initialExperience +1);
                initialExperience += (needExperience + experience[i]);
                needExperienceSum += needExperience;
            }
        }
        int trainHourForEnergy = 0;
        if(initialEnergy <= needEnergySum){
            trainHourForEnergy = needEnergySum - initialEnergy +1;
        }

        return trainHourForEnergy + needExperienceSum;
    }
}


// gready_easy_19 --------------------------------------------------------------- Can Place Flowers / 29.9%
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        boolean ans = false;
        if(n == 0){
            return true;
        }
        if(flowerbed.length == 1 && flowerbed[0] == 0){
            if(n <= 1){
                return true;
            }else{
                return false;
            }
        }
        for(int i=0; i<flowerbed.length; i++){
            // System.out.println(i + "번째: " + Arrays.toString(flowerbed));
            if(i == 0 || i+1 == flowerbed.length){ //양 끝쪽이라면
                if(i == 0){
                    if(flowerbed[i] == 0 && flowerbed[i+1] == 0){
                        n--;
                        flowerbed[i] = 1;
                    }
                }else{
                    if(flowerbed[i-1] == 0 && flowerbed[i] == 0){
                        n--;
                        flowerbed[i] = 1;
                    }
                }
            }else{  //중간이라면
                if(flowerbed[i-1] == 0 && flowerbed[i] == 0 && flowerbed[i+1] == 0){
                    n--;
                    flowerbed[i] = 1;
                }
            }
            if(n == 0){ //n을 전부 썼다면 멈춰
                ans = true;
                break;
            }
        }

        return ans;
    }
}


// gready_easy_20 --------------------------------------------------------------- Maximize Sum Of Array After K Negations / 50.8%
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] < 0 && k > 0){    //최대한 음수를 없애자
                nums[i] = -nums[i];
                k--;
            }
            sum += nums[i];
        }
        
        if(k%2 == 0){   //남은 k가 2의 배수라면
            return sum;
        }else{          //k가 1
            Arrays.sort(nums);
            sum -= (nums[0]*2);
        }
        
        return sum;
    }
}
