//------------------------------------------------------------------------------ 0_61 5명씩
class Solution {
    public String[] solution(String[] names) {
        int answerLength = names.length % 5 == 0 ? names.length/5 : names.length/5 + 1;
        String[] answer = new String[answerLength];
        for(int i=0; i<answer.length; i++){
            answer[i] = names[(i*5)];
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_62 대문자와 소문자
class Solution {
    public String solution(String my_string) {
        String answer = "";

        for(char e : my_string.toCharArray()){
            if(e >= 65 && e <91){
                answer += String.valueOf((char)(e+32));
            }else if(e >= 97 && e <123){
                answer += String.valueOf((char)(e-32));
            }
        }
        return answer;
    }
}

//------------------------------ 위에꺼가 밑에보다 2배정도 빠름

class Solution {
    public String solution(String my_string) {
        String answer = "";
        String[] myStringArray = my_string.split("");
        for(String e : myStringArray){
            if(Character.isLowerCase(e.charAt(0))){
                answer += e.toUpperCase();
            }else{
                answer += e.toLowerCase();
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_63 수 조작하기 2
class Solution {
    public String solution(int[] numLog) {
        String answer = "";
        for(int i=1; i<numLog.length; i++){
            if(Math.abs(numLog[i] - numLog[i-1]) == 10){
                if(numLog[i] > numLog[i-1]){
                    answer += "d";
                }else{
                    answer += "a";
                }
            }else{
                if(numLog[i] > numLog[i-1]){
                    answer += "w";
                }else{
                    answer += "s";
                }
            }
        }
        return answer;
    }
}

class Solution {
    public String solution(int[] numLog) {
        String answer = "";
        for(int i=1; i<numLog.length; i++){
            int j = numLog[i-1] - numLog[i];
            switch(j){
                case -1 : answer+='w'; break;
                case 1 : answer+='s'; break;
                case -10 : answer+='d'; break;
                case 10 : answer+='a'; break;
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_64 합성수 찾기
class Solution {
    public int solution(int n) {
        int answer = 0;
        for(int i=n;  i>=4; i--){
            if(isHapsung(i)){
                answer++;
            }
        }
        return answer;
    }
    
    boolean isHapsung(int a){
        for(int i = 2; i<Math.sqrt(a)+1; i++){
            if(a%i == 0){
                return true;
            }
        }
        return false;
    }
}

//------------------------------------------------------------------------------ 0_65 문자열 정렬하기 (2)
import java.util.*;

class Solution {
    public String solution(String my_string) {
        String answer = "";
        String lowerMyString = my_string.toLowerCase();
        
        //char로 바꾸고 sort 하고 다시 String으로
        char[] tempAnswer = lowerMyString.toCharArray();
        Arrays.sort(tempAnswer);
        answer = new String(tempAnswer);
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_66 중복된 문자 제거
class Solution {
    public String solution(String my_string) {
        String answer = "";
        //뭔가 메소드가 있을 것 같지만... 무지성으로 풀어보자
        String[] splitedString = my_string.split("");
        for (String s : splitedString){
            if(answer.indexOf(s) == -1){
                answer += s;
            }
        }
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_67 숫자 찾기
class Solution {
    public int solution(int num, int k) {
        int answer = 0;
        String tempString = "" + num;
        
        if(tempString.indexOf("" + k) == -1){
            answer = -1;
        }else{
            answer = tempString.indexOf("" + k) + 1;
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_68 
class Solution {
    public String solution(String str1, String str2) {
        String answer = "";
        String[] splitedStr1 = str1.split("");
        String[] splitedStr2 = str2.split("");
        
        for(int i=0; i<splitedStr1.length; i++){
            answer += splitedStr1[i];
            answer += splitedStr2[i];
        }
        return answer;
    }
}

//------------------- string을 배열로 만들 때 무지성 split에서 벗어나자...!

class Solution {
    public String solution(String str1, String str2) {
        String answer = "";

        for(int i = 0; i < str1.length(); i++){
            answer+= str1.charAt(i);
            answer+= str2.charAt(i);
        }

        return answer;
    }
}

//------------------------------------------------------------------------------ 0_69 배열 만들기 3
import java.util.*;

class Solution {
    public int[] solution(int[] arr, int[][] intervals) {
        int[] intArrayA = Arrays.copyOfRange(arr, intervals[0][0], intervals[0][1] + 1);
        int[] intArrayB = Arrays.copyOfRange(arr, intervals[1][0], intervals[1][1] + 1);
        int[] answer = new int[intArrayA.length + intArrayB.length];
        for(int i=0; i<answer.length; i++){
            if(i >= intArrayA.length){
                answer[i] = intArrayB[i-intArrayA.length];
            }else{
                answer[i] = intArrayA[i];
            }
        }
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_70 배열 만들기 5
import java.util.*;

class Solution {
    public int[] solution(String[] intStrs, int k, int s, int l) {
        int[] answer = {};
        ArrayList<String> tempAnswer = new ArrayList<>();
        
        for(String es: intStrs){
            String slicedStr = es.substring(s,s+l);
            if(Integer.parseInt(slicedStr) > k){
                tempAnswer.add(slicedStr);
            }
        }
        answer = tempAnswer.stream().mapToInt(e -> Integer.parseInt(e)).toArray();
        return answer;
    }
}