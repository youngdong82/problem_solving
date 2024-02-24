// string_easy_01 --------------------------------------------------------------- 242. Valid Anagram

class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            //System.out.println("길이 다름");
            return false;
        }
        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();
        char[] sCharArr = s.toCharArray();
        char[] tCharArr = t.toCharArray();
        for(char sChar : sCharArr){
            sMap.put(sChar,sMap.getOrDefault(sChar, 0)+1);
        }
        for(char tChar : tCharArr){
            tMap.put(tChar,tMap.getOrDefault(tChar, 0)+1);
        }
        for(Map.Entry<Character, Integer> entry : sMap.entrySet()){
            char key = entry.getKey();
            if(!tMap.containsKey(key)){
                System.out.println("키 없음");
                return false;
            }
            if(tMap.get(key) != sMap.get(key)){
                System.out.println("갯수 다름");
                return false;
            }
        }

        return true;
    }
}

왜 안돼 미친;;;

// string_easy_02 --------------------------------------------------------------- 412. Fizz Buzz
class Solution {
    public List<String> fizzBuzz(int n) {
        ArrayList<String> answer = new ArrayList<>();

        for(int i=1; i<n+1; i++){
            if(i%3 == 0 && i%5 == 0){
                answer.add("FizzBuzz");
            }else if(i%3 == 0){
                answer.add("Fizz");
            }else if(i%5 == 0){
                answer.add("Buzz");
            }else{
                answer.add("" + i);
            }
        }

        return answer;
    }
}

// string_easy_03 --------------------------------------------------------------- 1496. Path Crossing
class Solution {
    public boolean isPathCrossing(String path) {
        char[] charPath = path.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        int x = 0;
        int y = 0;
        list.add(x + " : " + y);
        for(char eachPath : charPath){
            if(eachPath == 'N'){
                y += 1;
            }else if(eachPath == 'S'){
                y -= 1;
            }else if(eachPath == 'E'){
                x += 1;
            }else if(eachPath == 'W'){
                x -= 1;
            }
            String now = x +" : "+ y;
            if(list.contains(now)){
                return true;
            }else{
                list.add(now);
            }
        }
        return false;
    }
}


// string_easy_04 --------------------------------------------------------------- 344. Reverse String
class Solution {
    public void reverseString(char[] s) {
        for(int i=0; i<s.length/2; i++){
            char tmp = s[i];
            s[i] = s[s.length-1-i];
            s[s.length-1-i] = tmp;
        }
    }
}


// string_easy_05 --------------------------------------------------------------- 387. First Unique Character in a String
class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            char now = s.charAt(i);
            map.put(now, map.getOrDefault(now, 0)+1);
        }
        for(int i=0; i<s.length(); i++){
            char now = s.charAt(i);
            if(map.get(now) == 1){
                return i;
            }
            
        }
        return -1;
    }
}

class Solution {
    public int firstUniqChar(String s) {
        for(int i = 0; i < s.length(); i ++){
            char now = s.charAt(i);
            if(s.indexOf(now) == s.lastIndexOf(now)){
                return s.indexOf(now);
            }
        }
        return -1;
    }
}

// string_easy_06 --------------------------------------------------------------- 14. Longest Common Prefix
class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder prefix = new StringBuilder(strs[0]);
        for(int i=1; i<strs.length; i++){
            int shortLen = Math.min(prefix.length(), strs[i].length());
            boolean toggle = true;
            for(int j=0; j<shortLen; j++){
                if(strs[i].charAt(j) != prefix.charAt(j)){
                    prefix.setLength(j);
                    toggle = false;
                    break;
                }
            }
            if(toggle){
                prefix.setLength(shortLen);
            }
        }
        return prefix.toString();
    }
}

// string_easy_07 --------------------------------------------------------------- 1160. Find Words That Can Be Formed by Characters
class Solution {
    public int countCharacters(String[] words, String chars) {
        int answer = 0;
        for(String word : words){
            StringBuilder sb = new StringBuilder(chars);
            String[] wordArr = word.split("");
            boolean isGood = true;
            for(int i=0; i<wordArr.length; i++){
                int idx = sb.indexOf(wordArr[i]);
                if(idx == -1){
                    //넘어가
                    isGood = false;
                    break;
                }else{
                    sb.deleteCharAt(idx);
                }
            }
            if(isGood){
                answer += wordArr.length;
            }
        }
        return answer;
    }
}


// string_easy_08 --------------------------------------------------------------- 20. Valid Parentheses
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] sCharArr = s.toCharArray();
        for(char each: sCharArr){
            if(stack.size() > 0){
                if(each == ')'){
                    if(stack.peek() != '('){
                        return false;
                    }else{
                        stack.pop();
                    }
                }else if(each == '}'){
                    if(stack.peek() != '{'){
                        return false;
                    }else{
                        stack.pop();
                    }
                }else if(each == ']'){
                    if(stack.peek() != '['){
                        return false;
                    }else{
                        stack.pop();
                    }
                }else{
                    stack.push(each);
                }
            }else{
                stack.push(each);
            }
        }
        if(stack.size() == 0){
            return true;
        }
        return false;
    }
}


// string_easy_09 --------------------------------------------------------------- 1422. Maximum Score After Splitting a String

// 일단 무식하게 ㄱㄱ
class Solution {
    public int maxScore(String s) {
        int answer = -1;
        char[] sChar = s.toCharArray();
        for(int i=0; i<s.length()-1; i++){
            int leftScore = 0;
            int rightScore = 0;
            for(int j=0; j<i+1; j++){
                // System.out.println("left: " + j);
                if(sChar[j] == '0' ){
                    leftScore += 1;
                }
            }
            for(int j=i+1; j<s.length(); j++){
                if(sChar[j] == '1' ){
                    rightScore += 1;
                }
            }
            int score = leftScore + rightScore;
            answer = Math.max(answer, score);
        }
        return answer;

    }
}

//좀 더 똑똑하게
class Solution {
    public int maxScore(String s) {
        int leftScore = 0;
        int rightScore = 0;
        char[] sChar = s.toCharArray();

        for(int i=0; i<s.length(); i++){
            if(i == 0){
                if(sChar[i] == '0'){
                    leftScore += 1;
                }
            }else{
                if(sChar[i] == '1'){
                    rightScore += 1;
                }
            }
        }
        int answer = leftScore + rightScore;
        int tmpAnswer = leftScore + rightScore;
        
        for(int i=1; i<s.length()-1; i++){
            //총 몇개의 0을 가져올 수 있나?
            if(sChar[i] == '0'){
                tmpAnswer +=1;
            }else{
                tmpAnswer -=1;
            }
            answer = Math.max(answer,tmpAnswer);
        }

        return answer;
    }
}


// string_easy_10 --------------------------------------------------------------- 1436. Destination City
class Solution {
    public String destCity(List<List<String>> paths) {
        HashMap<String,Boolean> leftMap = new HashMap<>();
        HashMap<String,Boolean> rightMap = new HashMap<>();

        for(int i=0; i<paths.size(); i++){
            List<String> each = paths.get(i);
            leftMap.put(each.get(0), true);
            rightMap.put(each.get(1), true);
        }

        // System.out.println(leftMap);
        // System.out.println(rightMap);
        String answer = "";
        for(String key :rightMap.keySet()){
            if(leftMap.get(key) == null){
                answer = key;
                break;
            }
        }
        return answer;
    }
}

