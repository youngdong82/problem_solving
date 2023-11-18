// hash_table_easy_01 --------------------------------------------------------------- Number of Good Pairs / 88.9%
//-------------------------------------- Me
class Solution {
    public int numIdenticalPairs(int[] nums) {
        int answer = 0;
        //이중 for문
        for(int i=0; i<nums.length; i++){
            int temp = nums[i];
            for(int j=i+1; j<nums.length; j++){
                if(temp == nums[j]){
                    answer += 1;
                }
            }
        }
        return answer;
    }
}
//-------------------------------------- HashMap
class Solution {
    public int numIdenticalPairs(int[] guestList) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int ans = 0;
        
        for(int friend: guestList){
            int friendCount = hm.getOrDefault(friend,0);
            ans += friendCount;
            hm.put(friend,friendCount+1);
        }
        return ans;
    }
}


// hash_table_easy_02 --------------------------------------------------------------- Jewels and Stones / 88.4%
class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        int answer = 0;
        String[] stringStones = stones.split("");
        for(String stone: stringStones){
            if(jewels.contains(stone)){
                answer += 1;
            }
        }
        return answer;
    }
}

// hash_table_easy_03 --------------------------------------------------------------- Decode the Message / 83.8%
class Solution {
    public String decodeMessage(String key, String message) {
        //테이블 만들기
        HashMap<Character,Character> hmTable = new HashMap<>();
        
        for(int i=0; i<key.length(); i++){
            if(key.charAt(i) != ' '){
                if(hmTable.getOrDefault(key.charAt(i),'-') == '-'){
                    hmTable.put(key.charAt(i),(char)(97 + hmTable.size()));
                }
            }
        }
        //변환
        char[] beforeAnswer = new char[message.length()];
        for(int j=0; j<message.length(); j++){
            if(message.charAt(j) != ' '){            
                beforeAnswer[j] = hmTable.get(message.charAt(j));
            }else{
                beforeAnswer[j] = ' ';
            }
        }

        return String.valueOf(beforeAnswer);
    }
}


// hash_table_easy_04 --------------------------------------------------------------- Sort the People / 80.1%
class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        HashMap<Integer,String> nameHeight = new HashMap<Integer,String>();
        for(int i=0; i<heights.length; i++){
            nameHeight.put(heights[i],names[i]);
        }
        Arrays.sort(heights);
        String[] answer = new String[names.length];
        for(int i=names.length-1; i>=0; i--){
            answer[names.length-1-i] = nameHeight.get(heights[i]);
        }
        return answer;
    }
}

// hash_table_easy_05 --------------------------------------------------------------- Make Array Zero by Subtracting Equal Amounts / 71.9%
class Solution {
    public int minimumOperations(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int i:nums){
            if(i>0){set.add(i);}
        }
        System.out.println(set);
        return set.size();
    }
}

// hash_table_easy_06 --------------------------------------------------------------- Roman to Integer / 59.8%
class Solution {
    public int romanToInt(String s) {
        String[] romanArray = {"I","V","X","L","C","D","M"};
        String[] convertedString = s.split("");

        int answer = 0;
        int idxBefore = 10;
        int valueBefore = 0;
        for(String i:convertedString){
            int idx = Arrays.asList(romanArray).indexOf(i);
            int time = (int) Math.pow(10, idx/2);
            int tempTimes = 0;
            if((idx+1) %2 == 1){
                tempTimes = 1;
            } else{
                tempTimes = 5;
            }
            if(idx <= idxBefore){
                answer += tempTimes * time;
            } else{
                answer += ((tempTimes * time)-(valueBefore*2));
            }
            idxBefore = idx;
            valueBefore = tempTimes * time;
        }
        return answer;
    }
}

// hash_table_easy_07 --------------------------------------------------------------- Two Sum / 51.1%
class Solution {
    public int[] twoSum(int[] nums, int target) {
        List<Integer> temp = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                if(nums[i]+nums[j] == target){
                    temp.add(i);
                    temp.add(j);
                    break;
                }
            }
        }
        int[] answer = temp.stream()
                .mapToInt(i -> i)
                .toArray();
        return answer;
    }
}

// hash_table_easy_08 --------------------------------------------------------------- Most Frequent Even Element / 50.2%
class Solution {
    public int mostFrequentEven(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxValue = 0;
        int minKey = Integer.MAX_VALUE;
        for(int num: nums){
            if(num%2 == 0){
                int nowValue = map.getOrDefault(num,0) +1;
                map.put(num, nowValue);
                if(maxValue < nowValue){
                    maxValue = nowValue;
                    minKey = num;
                }else if(maxValue == nowValue){
                    if(minKey > num){
                        minKey = num;
                    }
                }
            }
        }
        if(map.size() == 0){
            return -1;
        }

        return minKey;
    }
}

// hash_table_easy_09 --------------------------------------------------------------- Check if All the Integers in a Range Are Covered / 49.8%
class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        boolean[] coveredArr = new boolean[50];

        for(int i=0; i<ranges.length; i++){
            for(int j= ranges[i][0]; j<=ranges[i][1]; j++ ){
                coveredArr[j-1] = true;
            }
        }
        for(int i=left; i<right+1; i++){
            if(coveredArr[i-1] == false){
                return false;
            }
        }
        return true;
    }
}


// hash_table_easy_10 --------------------------------------------------------------- Find the Town Judge / 49.2%
class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] peopleArr = new int[n];
        for(int[] each: trust){
            peopleArr[each[0]-1] = -1;      
            if(peopleArr[each[1]-1] != -1){ //믿어야하는 사람이 누군가를 믿고있지 않을때만
                peopleArr[each[1]-1]++;         //tj일 가능성 있다.
            }
        }
        int ans = -1;
        for(int i=0; i<peopleArr.length; i++){
            if(peopleArr[i] == n-1){
                ans = i+1;
                break;
            }
        }

        return ans;
    }
}
