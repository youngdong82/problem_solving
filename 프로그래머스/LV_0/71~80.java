//------------------------------------------------------------------------------ 0_71 인덱스 바꾸기
class Solution {
    public String solution(String my_string, int num1, int num2) {
        String answer = "";
        String[] splitedMyStr = my_string.split("");
        for(int i=0; i<splitedMyStr.length; i++){
            if(i == num1){
                answer += splitedMyStr[num2];
            }else if(i == num2){
                answer += splitedMyStr[num1];
            }else{
                answer += splitedMyStr[i];
            }
        }
        
        return answer;
        
    }
}

class Solution {
    public String solution(String my_string, int num1, int num2) {
        String answer = "";

        char[] ch = my_string.toCharArray();

        ch[num1] = my_string.charAt(num2);
        ch[num2] = my_string.charAt(num1);

        answer = String.valueOf(ch);
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_72 A로 B 만들기
import java.util.*;

class Solution {
    public int solution(String before, String after) {
        //사전sorting 후 반복문 하나하나 비교
        char[] sortedBefore = sortingString(before);
        char[] sortedAfter = sortingString(after);
        
        for(int i=0; i<sortedBefore.length; i++){
            if(sortedBefore[i] != sortedAfter[i]){
                return 0;
            }
        }
        return 1;
    }
    char[] sortingString (String a){
        char[] charArray = a.toCharArray();
        Arrays.sort(charArray);
        return charArray;
        
    }
}

//------------------------------------------------------------------------------ 0_73 문자열 뒤집기
class Solution {
    public String solution(String my_string, int s, int e) {
        String answer = "";
        char[] charArray = my_string.toCharArray();
        
        int sum = e + s;
        for(int i=s; i<(s+((e-s)/2))+1; i++){
            char tempChar = charArray[i];
            charArray[i] = charArray[sum-i];
            charArray[sum-i] = tempChar;
            
        }
        answer = new String(charArray);
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_74 카운트 업
class Solution {
    public int[] solution(int start, int end) {
        int leng = end-start+1;
        int[] answer = new int[leng];
        for(int i=0; i<leng; i++){
            answer[i] = start+i;
        }
        return answer;
    }
}

import java.util.stream.*;

class Solution {
    public int[] solution(int start, int end) {
        int[] answer = IntStream.rangeClosed(start,end).toArray();
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_75 문자열로 변환, 조건에 맞게 수열 변환하기 1
// 너무 쉬운데 이건;;
class Solution {
    public String solution(int n) {
        String answer = "";
        return answer + n;
    }
}

class Solution {
    public int[] solution(int[] arr) {
        int[] answer = new int[arr.length];
        for(int i=0; i<arr.length; i++){
            if(arr[i]<50 && arr[i]%2 != 0){
                answer[i] = arr[i] * 2;
            }else if(arr[i]>=50 && arr[i]%2 == 0){
                answer[i] = arr[i] / 2;
            }else{
                answer[i] = arr[i];
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_76 부분 문자열 수 조작하기 1
class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        answer = str2.indexOf(str1) == -1 ? 0 : 1;
        return answer;
    }
}

class Solution {
    public int solution(int n, String control) {
        int answer = n;
        
        for(String e: control.split("")){
            switch(e){
                case "w": answer += 1;
                    break;
                case "s": answer -= 1;
                    break;
                case "d": answer += 10;
                    break;
                case "a": answer -= 10;
                    break;
                default: answer = -1;
                    break;
            }
        }
        return answer;
    }
}
//-------------------------- char이 훨씬 빠름
class Solution {
    public int solution(int n, String control) {
        int answer = n;
        
        for(char e: control.toCharArray()){
            switch(e){
                case 'w': answer += 1;
                    break;
                case 's': answer -= 1;
                    break;
                case 'd': answer += 10;
                    break;
                case 'a': answer -= 10;
                    break;
                default: answer = -1;
                    break;
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_77 세균 증식 배열 비교하기
class Solution {
    public int solution(int n, int t) {
        int answer = n;
        for(int i=0; i<t; i++){
            answer*=2;
        }
        return answer;
    }
}

class Solution {
    public int solution(int n, int t) {
        int answer = 0;

        answer = n << t;

        return answer;
    }
}

import java.util.*;

class Solution {
    public int solution(int[] arr1, int[] arr2) {
        int answer = 0;
        if(arr1.length > arr2.length){
            answer = 1;
        }else if(arr1.length == arr2.length){
            int arr1Sum = Arrays.stream(arr1).sum();
            int arr2Sum = Arrays.stream(arr2).sum();
            if(arr1Sum > arr2Sum){
                answer = 1;
            }else if(arr1Sum == arr2Sum){
                answer = 0;
            }else{
                answer = -1;
            }
        } else{
            answer = -1;
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_78 등차수열의 특정한 항만 더하기
class Solution {
    public int solution(int a, int d, boolean[] included) {
        int answer = 0;
        int temp = 0;
        for(int i=0; i<included.length; i++){
            if(i == 0){
                temp = a;
            }else{
                temp += d;
            }
            if(included[i] == true){
                answer += temp;
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_79 문자열이 몇 번 등장하는지 세기
class Solution {
    public int solution(String myString, String pat) {
        int answer = 0;
        for(int i=0; i<myString.length() - pat.length() +1; i++){
            String sub = myString.substring(i, (i+pat.length()));
            if(sub.equals(pat)){
                answer+=1;
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_80 두 수의 합
import java.math.BigDecimal;

class Solution {
    public String solution(String a, String b) {
        String answer = "";
        BigDecimal bigA = new BigDecimal(a);
        BigDecimal bigB = new BigDecimal(b);
        
        BigDecimal bigAnswer = bigA.add(bigB);
        
        return bigAnswer.toString();
    }
}

