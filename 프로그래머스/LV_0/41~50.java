//------------------------------------------------------------------------------ 0_41 가장 큰 수 찾기
class Solution {
    public int[] solution(int[] array) {
        int[] answer = new int[2];
        int temp = 0;
        int tempIndex = 0;
        
        for(int i=0; i<array.length; i++){
            if(array[i] > temp){
                tempIndex = i;
                temp = array[i];
            }
        }
        
        answer[0] = temp;
        answer[1] = tempIndex;
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_42 두 수의 연산값 비교하기
class Solution {
    public int solution(int a, int b) {
        int answer = 0;
        answer = Math.max(Integer.parseInt("" + a + b), 2*a*b);
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_43 주사위의 개수
import java.util.*;

class Solution {
    public int solution(int[] box, int n) {
        int answer = 1;
        for(int e : box){
            answer *= (e/n);
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_44 x 사이의 개수
import java.util.*;

class Solution {
    public int[] solution(String myString) {
        int[] answer = {};
        String[] splitedMyString = myString.split("x");
        int [] almostAnswer = Arrays.stream(splitedMyString).mapToInt(e -> e.length()).toArray();
        
        //마지막이 x라면 0추가
        if(myString.charAt(myString.length()-1) == 'x'){
            answer = Arrays.copyOf(almostAnswer, almostAnswer.length + 1);
            answer[answer.length-1] = 0;
        }else{
            answer = almostAnswer;
        }
        
        return answer;
    }
}


import java.util.Arrays;

class Solution {
    public int[] solution(String myString) {

        return Arrays.stream(myString.split("x", myString.length()))
            .mapToInt(x -> x.length())
            .toArray();
    }
}


//------------------------------------------------------------------------------ 0_45 주사위 게임 2
class Solution {
    public int solution(int a, int b, int c) {
        int answer = 0;
        if(a == b && b == c){
            answer = (a + b + c) * ((a*a) + (b*b) + (c*c)) * ((a*a*a) + (b*b*b) + (c*c*c));
        } else if(a==b || a==c || b==c){
            answer = (a + b + c) * ((a*a) + (b*b) + (c*c));
        }else{
            answer = a + b + c;
        }
        
        return answer;
    }
}


//------------------------------------------------------------------------------ 0_46 외계행성의 나이
import java.util.*;

class Solution {
    public String solution(int age) {
        String answer = "";
        
        int[] intArray = Arrays.stream(Integer.toString(age).split("")).mapToInt(e -> Integer.parseInt(e)).toArray();
        String alpha = "abcdefghij";
        String[] alphaArray = alpha.split("");
        
        for(int e : intArray){
            answer += alphaArray[e];
        }
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_47 가까운 1 찾기
import java.util.*;

class Solution {
    public int solution(int[] arr, int idx) {
        int answer = 0;
        int[] skippedIntArray = Arrays.stream(arr).skip(idx).toArray();

        for(int i=0; i<skippedIntArray.length; i++){
            if(skippedIntArray[i] == 1){
                answer = i + idx;
                break;
            } else if(i == skippedIntArray.length-1){
                answer = -1;
            }
        }
        
        return answer;
    }
}



//------------------------------------------------------------------------------ 0_48 피자 나눠 먹기 (2)
class Solution {
    public int solution(int n) {
        int wholePizza = n;
        while(wholePizza % 6 != 0){
            wholePizza += n;
        }
        
        return wholePizza / 6;
    }
}


//------------------------------------------------------------------------------ 0_49 간단한 식 계산하기
class Solution {
    public int solution(String binomial) {
        int answer = 0;
        String[] temp = binomial.split(" ");
        if(temp[1].equals("+")){
            answer = Integer.parseInt(temp[0]) + Integer.parseInt(temp[2]);
        } else if(temp[1].equals("-")){
            answer = Integer.parseInt(temp[0]) - Integer.parseInt(temp[2]);
        } else if(temp[1].equals("*")){
            answer = Integer.parseInt(temp[0]) * Integer.parseInt(temp[2]);
        }
        return answer;
    }
}

class Solution {
    public int solution(String binomial) {
        int answer = 0;
        String[] temp = binomial.split(" ");
        int a = Integer.parseInt(temp[0]);
        int b = Integer.parseInt(temp[2]);
        
        switch(temp[1]){
            case "+":
                answer =  a+b; 
                break;
            case "-":
                answer =  a-b; 
                break;
            case "*":
                answer =  a*b;     
                break;
        }

        return answer;
    }
}

//------------------------------------------------------------------------------ 0_50 9로 나눈 나머지
import java.util.*;

class Solution {
    public int solution(String number) {
        int answer = 0;
        int sum = Arrays.stream(number.split("")).mapToInt(e -> Integer.parseInt(e)).sum();
        answer = sum % 9;
        return answer;
    }
}

import java.util.*;

class Solution {
    public int solution(String number) {
        int answer = 0;
        String[] stringArray = number.split("");
        for(String e: stringArray){
            answer += Integer.parseInt(e);
        }
        
        return answer % 9;
    }
}

