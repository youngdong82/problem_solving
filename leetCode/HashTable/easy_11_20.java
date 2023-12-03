// hash_table_easy_11 --------------------------------------------------------------- Find the Losers of the Circular Game / 48.3%
//배열로 하는게 더 깔끔할 것 같긴 한데... 일단 Hashmap으로 하자

class Solution {
    public int[] circularGameLosers(int n, int k) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        int ballPlayer = 1;
        int cnt = 0;
        for(int i=1; i<n+1; i++){
            map.put(i,false);
        }
        while(true){
            if(!map.get(ballPlayer)){
                map.put(ballPlayer, true);
            }else{
                break;
            }
            cnt++;
            ballPlayer += (k*cnt); //next ballPlayer
            ballPlayer %= n;
            if(ballPlayer == 0){
                ballPlayer = n;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer, Boolean> entrySet : map.entrySet()){
            if(!entrySet.getValue()){
                list.add(entrySet.getKey());
            }
        }

        return list.stream().mapToInt(e -> e).toArray();
    }
}

//배열 버전 2배정도 빠름
class Solution {
    public int[] circularGameLosers(int n, int k) {
        boolean[] visited = new boolean[n];
        int ballPlayer = 0;
        int cnt = 0;
        while(!visited[ballPlayer]){
            visited[ballPlayer] = true;
            cnt+=k;
            ballPlayer += cnt; //next ballPlayer
            ballPlayer %= n;
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<visited.length; i++){
            if(!visited[i]){
                list.add(i+1);
            }
        }

        return list.stream().mapToInt(e -> e).toArray();
    }
}

// hash_table_easy_12 --------------------------------------------------------------- Check if One String Swap Can Make Strings Equal / 45.4%
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        //애초에 구성이 다르면 불가능해
        char[] s1CharArr = s1.toCharArray();
        char[] s2CharArr = s2.toCharArray();
        Arrays.sort(s1CharArr);
        Arrays.sort(s2CharArr);
        for(int i=0; i<s1.length(); i++){
            if(s1CharArr[i] != s2CharArr[i]){
                return false;
            }
        }

        int diffCnt = 0;
        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                diffCnt++;
            }
        }
        if(diffCnt == 2 || diffCnt == 0){
            return true;
        }
        return false;
    }
}

// hash_table_easy_13 --------------------------------------------------------------- Contains Duplicate II / 43.9%
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.get(nums[i]) == null){
                ArrayList<Integer> tmpList = new ArrayList<>();
                tmpList.add(i);
                map.put(nums[i], tmpList);
            }else{
                ArrayList<Integer> list = map.get(nums[i]);
                list.add(i);
                map.put(nums[i], list);
            }
        }
        for(Integer key: map.keySet()){
            ArrayList<Integer> eachList = map.get(key);
            if(eachList.size() > 1){
                for(int i=0; i<eachList.size()-1; i++){
                    for(int j=i+1; j<eachList.size(); j++){
                        if(Math.abs(eachList.get(i)-eachList.get(j)) <= k){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

//map.get(nums[i]) == null 을 containsKey로만 변경했는데
// 2배나 빨라졌다.ㄷㄷ 어떻게 구현되어있는거야..?

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                ArrayList<Integer> list = map.get(nums[i]);
                list.add(i);
                map.put(nums[i], list);
            }else{
                ArrayList<Integer> tmpList = new ArrayList<>();
                tmpList.add(i);
                map.put(nums[i], tmpList);
            }
        }
        for(Integer key: map.keySet()){
            ArrayList<Integer> eachList = map.get(key);
            if(eachList.size() > 1){
                for(int i=0; i<eachList.size()-1; i++){
                    for(int j=i+1; j<eachList.size(); j++){
                        if(Math.abs(eachList.get(i)-eachList.get(j)) <= k){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}


class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int ans=Integer.MAX_VALUE;
        HashMap<Integer,Integer> map =new HashMap<>();
        for(int i=0; i<nums.length; i++){
            System.out.println(map);
            if(map.containsKey(nums[i]) && Math.abs((i-map.get(nums[i])))<=k){
                return true;
            }else{
                map.put(nums[i],i);
            }
        }
        return false;
    }
}

// abs(i-j) <= k 인것을 알기 위해서
// 값이 같은 애들을 다 모아서 2중 포문으로 하나하나 확인해야 하는 줄 알았는데...
// i와 j는 idx라서 abs(i-j)가 k보다 크다면 그 이후의 같은 값이 있더라도 이전보다 커지기만 하기에
// 같은 값을 가진 애들을 모아놓을 필요가 없이 최신의 값을 갱신하기만 하면 된다.
// 이걸 간파하는

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer,Integer> map =new HashMap<>();
        for(int i=0; i<nums.length; i++){
            System.out.println(map);
            if(map.containsKey(nums[i]) && Math.abs((i-map.get(nums[i])))<=k){
                return true;
            }else{
                map.put(nums[i],i);
            }
        }
        return false;
    }
}


// hash_table_easy_14 --------------------------------------------------------------- Find Subsequence of Length K With the Largest Sum / 42.9%
class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        ArrayList<Integer> numsList = new ArrayList<>();
        for(int num: nums){
            numsList.add(num);
        }
        Arrays.sort(nums);

        for(int i=0; i<nums.length-k; i++){
            numsList.remove(numsList.indexOf(nums[i]));
        }

        return numsList.stream().mapToInt(e->e).toArray();
    }
}

// hash_table_easy_15 --------------------------------------------------------------- Word Pattern / 41.7%
class Solution {
    public boolean wordPattern(String pattern, String s) {
        HashMap<Character, String> map1 = new HashMap<>();
        HashMap<String, Character> map2 = new HashMap<>();
        String[] splitedS = s.split(" ");
        if(pattern.length() != splitedS.length){
            return false;
        }
        for(int i=0; i<pattern.length(); i++){
            if(map1.containsKey(pattern.charAt(i))){
                String eachVal = map1.get(pattern.charAt(i));
                if(!eachVal.equals(splitedS[i])){
                    return false;
                }
            }else{
                map1.put(pattern.charAt(i), splitedS[i]);
                map2.put(splitedS[i], pattern.charAt(i));
            }   
            if(map1.size() != map2.size()){
                return false;
            }            
        }
        return true;
    }
}


// hash_table_easy_16 --------------------------------------------------------------- Number of Different Integers in a String / 37.2%
// 아무리 간단한 문제라도 디테일 조정으로 이렇게 괴롭힐 수가 있구나...
class Solution {
    public int numDifferentIntegers(String word) {
        boolean toggle = false;
        HashMap<String,Boolean> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<word.length(); i++){
            if(Character.isDigit(word.charAt(i))){
                if(toggle == false){
                    toggle = true;
                }
                sb.append(word.charAt(i));
            }else{
                if(toggle == true){
                    if(!map.getOrDefault(sb.toString(),false)){   
                        String putObj = sb.toString();
                        StringBuilder afterPutObj = new StringBuilder();
                        boolean toggle2 = false;
                        for(int j=0; j<putObj.length(); j++){
                            //0이 아닌 이상 앞에 0을 날려버려
                            if(putObj.charAt(j) == '0' && toggle2 == false){
                                continue;
                            }else{
                                toggle2 = true;
                            }
                            afterPutObj.append(putObj.charAt(j));
                        }
                        if(afterPutObj.length() == 0){
                            map.put("0", true);
                        }else{
                            map.put(afterPutObj.toString(), true);
                        }
                    }
                    sb.setLength(0);
                    toggle = false;
                }
            }
        }
        if(sb.length() != 0){
            //앞에 0을 날려버려
            String putObj = sb.toString();
            StringBuilder afterPutObj = new StringBuilder();
            boolean toggle2 = false;
            for(int j=0; j<putObj.length(); j++){
                //0이 아닌 이상 앞에 0을 날려버려
                if(putObj.charAt(j) == '0' && toggle2 == false){
                    continue;
                }else{
                    toggle2 = true;
                }
                afterPutObj.append(putObj.charAt(j));
            }
            if(afterPutObj.length() == 0){
                map.put("0", true);
            }else{
                map.put(afterPutObj.toString(), true);
            }
        }
        return map.size();
    }
}

//BigInteger 사용
import java.math.BigInteger;

class Solution {
    public int numDifferentIntegers(String word) {
        boolean toggle = false;
        Set<BigInteger> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<word.length(); i++){
            if(Character.isDigit(word.charAt(i))){
                if(toggle == false){
                    toggle = true;
                }
                sb.append(word.charAt(i));
            }else{
                if(toggle == true){
                    toggle = false;
                    set.add(new BigInteger(sb.toString()));
                }
                sb.setLength(0);
            }
        }
        if(sb.length() != 0){
            set.add(new BigInteger(sb.toString()));
        }
        return set.size();    
    }
}


// hash_table_easy_17 --------------------------------------------------------------- Check If N and Its Double Exist / 36.8%
class Solution {
    public boolean checkIfExist(int[] arr) {
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length; j++){
                if(i != j && arr[i] == 2*arr[j]){
                    return true;
                }
            }
        }

        return false;
    }
}

// hash_table_easy_18 --------------------------------------------------------------- Buddy Strings / 32.9%
class Solution {
    public boolean buddyStrings(String s, String goal) {
        if(s.length() != goal.length()){
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int diff1 = -1;
        int diff2 = -1;
        for(int i=0; i<s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0) +1);
            if(s.charAt(i) != goal.charAt(i)){
                if(diff1 == -1){
                    diff1 = i;
                }else if(diff2 == -1){
                    diff2 = i;
                }else{
                    return false;
                }
            }
        }
        if(diff1 == -1 && diff2 == -1){
            for(int eachVal : map.values()){
                if(eachVal >=2){
                    return true;
                }
            }      
        }
        if(!(diff1 != -1 && diff2 != -1)){
            return false;            
        }
        
        return s.charAt(diff1) == goal.charAt(diff2) && s.charAt(diff2) == goal.charAt(diff1);
    }
}


// hash_table_easy_19 --------------------------------------------------------------- Set Mismatch / 42.5%
//타임 아웃
class Solution {
    public int[] findErrorNums(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            list.add(nums[i]);
        }
        int[] answer = new int[2];
        for(int i=1; i<nums.length+1; i++){
            int idx = list.indexOf(i);
            if(idx == -1){
                answer[1] = i;
            }else if(list.lastIndexOf(i) != idx){
                answer[0] = i;
            }
        }
        return answer;
    }
}


class Solution {
    public int[] findErrorNums(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] answer = new int[2];
        for(int i=0; i<nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) +1);
        }
        for(int i=1; i<nums.length+1; i++){
            if(map.get(i) == null){
                answer[1] = i;
            }else if(map.get(i) == 2){
                answer[0] = i;
            }
        }
        return answer;
    }
}


// hash_table_easy_20 --------------------------------------------------------------- Most Common Word / 44.4%
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        String low = paragraph.toLowerCase();
        List<String> banList = Arrays.asList(banned);
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int maxx = -1;

        for(int i=0; i<low.length()+1; i++){
            if(i == low.length() || !Character.isAlphabetic(low.charAt(i))){
                if(sb.length() != 0){
                    String word = sb.toString();
                    if(banList.indexOf(word) == -1){
                        int next = map.getOrDefault(word, 0) + 1;
                        maxx = Math.max(maxx, next);
                        map.put(word, next);
                    }
                    sb.setLength(0);
                }
            }else{
                sb.append(low.charAt(i));
            }
        }
        String answer = "";
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            if(entry.getValue() == maxx){
                answer = entry.getKey();
                break;
            }
        }
        return answer;
    }
}






