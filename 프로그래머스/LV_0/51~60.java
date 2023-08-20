//------------------------------------------------------------------------------ 0_51 369게임
class Solution {
    public int solution(int order) {
        int answer = 0;
        String stringOrder = Integer.toString(order);
        String[] stringArrayOrder = stringOrder.split("");
        for(String e: stringArrayOrder){
            if(e.equals("3") || e.equals("6") || e.equals("9")){
                answer ++;
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_52 1로 만들기
class Solution {
    public int solution(int[] num_list) {
        int answer = 0;
        for(int e : num_list){
            answer += devide(e,0);
        }
        return answer;
    }
    int devide(int arg, int count){
        if(arg == 1){
            return count;
        }
        if(arg %2 == 0){
            return devide(arg/2, count+1);
        }else{
            return devide((arg-1)/2, count+1);
        }
    }
}

오... 오랜만에 재귀함수 만들었는데 꽤나 잘 만든듯

//------------------------------------------------------------------------------ 0_53 세 개의 구분자
import java.util.*;

class Solution {
    public String[] solution(String myStr) {
        String[] answer = {};
        String[] regexSplit = myStr.split("[abc]");
        String[] filteredRegexSplit = Arrays.stream(regexSplit).filter(e -> !e.isBlank()).toArray(String[]::new);
        if (filteredRegexSplit.length == 0){
            answer = new String[1];
            answer[0] = "EMPTY";
        } else{
            answer = filteredRegexSplit;
        }

        return answer;
    }
}

//------------------------------------------------------------------------------ 0_54 간단한 논리 연산
class Solution {
    public boolean solution(boolean x1, boolean x2, boolean x3, boolean x4) {
        return andStatement(orStatement(x1, x2), orStatement(x3, x4));
    }
    
    boolean orStatement(boolean a, boolean b){
        if(a==true || b==true){
            return true;
        }else{
            return false;
        }
    }
    boolean andStatement(boolean a, boolean b){
        if(a==true && b==true){
            return true;
        }else{
            return false;
        }
    }
}

//...ㄹㅇ 간단하네
class Solution {
    public boolean solution(boolean x1, boolean x2, boolean x3, boolean x4) {
        boolean answer = (x1||x2)&&(x3||x4);
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_55 빈 배열에 추가, 삭제하기
import java.util.*;

class Solution {
    public int[] solution(int[] arr, boolean[] flag) {
        int[] answer = {};
        ArrayList<Integer> tempAnswer = new ArrayList<>();
        for(int i=0; i<flag.length; i++){
            if(flag[i] == true){
                for(int j=0; j<arr[i]*2; j++){
                    tempAnswer.add(arr[i]);
                }
            }else{
                for(int j=0; j<arr[i]; j++){
                    tempAnswer.remove(tempAnswer.size()-1);
                }
            }
        }
        return tempAnswer.stream().mapToInt(e -> e).toArray();
    }
}

//------------------------------------------------------------------------------ 0_56 특정 문자열로 끝나는 가장 긴 부분 문자열 찾기
import java.util.*;

class Solution {
    public String solution(String myString, String pat) {
        String answer = "";
        for(int i=0; i<myString.length(); i++){
            String temp = myString.substring(0,i+1);
            if(temp.endsWith(pat)){
                answer = temp;
            }
        }
        return answer;
    }
}
//--------------------------------------------------- 이게 훨씬 오래걸림...!!
class Solution {
    public String solution(String myString, String pat) {
        String answer = "";
        int idx = myString.lastIndexOf(pat);
        answer = myString.substring(0, idx) + pat;

        return answer;
    }
}

//------------------------------------------------------------------------------ 0_57 가까운 수
class Solution {
    public int solution(int[] array, int n) {
        int answer = 0;
        int minDistance = 1000;
        for(int e: array){
            int dist = Math.abs(e - n);
            if(dist < minDistance){
                minDistance = dist;
                answer = e;
            }else if (dist == minDistance){
                answer = Math.min(answer, e);
            }
        }
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_58 k의 개수 * 
class Solution {
    public int solution(int i, int j, int k) {
        int answer = 0;
        String stringK = Integer.toString(k);
        for(int a=i; a<j+1; a++){
            String tempString = Integer.toString(a);
            if(tempString.indexOf(stringK) != -1){
                long many = tempString.chars()
                            .filter(c -> c == stringK.charAt(0))
                            .count();
                answer += (int) many;
            }
        }
        return answer;
    }
}
//---------------------------- 와 이건 똑똑했다...
class Solution {
    public int solution(int i, int j, int k) {
        String str = "";
        for(int a = i; a <= j; a++) {
            str += a+"";
        }

        return str.length() - str.replace(k+"", "").length();
    }
}

//------------------------------------------------------------------------------ 0_59 2차원으로 만들기
import java.util.*;

class Solution {
    public int[][] solution(int[] num_list, int n) {
        int[][] answer = new int[num_list.length/n][n];
        int indexx = 0;
        for(int i=0; i<num_list.length/n; i++){
            for(int j=0; j<n; j++){
                indexx ++;
                answer[i][j] = num_list[indexx-1];
            }
        }
        return answer;
    }
}


//------------------------------------------------------------------------------ 0_60 한 번만 등장한 문자
import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        HashMap<String, Integer> stringCounter = new HashMap<>();
        String[] stringArray = s.split("");
        for(String e: stringArray){
            stringCounter.put(e, stringCounter.getOrDefault(e, 0) + 1);
        }
        
        String[] keyArray = stringCounter.keySet().toArray(new String[stringCounter.size()]);
        for(String key: keyArray){
            if(stringCounter.get(key) == 1){
                answer += key;
            }
        }
        //문자열 sorting후 반환
        char[] charArray = answer.toCharArray();
        Arrays.sort(charArray);
        String sortedAnswer = new String(charArray);
        
        return sortedAnswer;
    }
}


