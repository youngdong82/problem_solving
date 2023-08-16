//------------------------------------------------------------------------------ 0_11 배열 자르기
import java.util.Arrays;

class Solution {
    public int[] solution(int[] numbers, int num1, int num2) {
        int[] answer = {};
        answer = Arrays.copyOfRange(numbers, num1, num2 + 1);
        return answer;
    }
}

import java.util.Arrays;

class Solution {
    public int[] solution(int[] numbers, int num1, int num2) {

        int[] answer = Arrays.stream(numbers, num1, num2+1).toArray();
        return answer;
    }
}

import java.util.stream.IntStream;

class Solution {
    public int[] solution(int[] numbers, int num1, int num2) {

        int[] answer = IntStream.range(num1, num2+1)
            .map(i -> numbers[i]).toArray();
        return answer;
    }
}


//------------------------------------------------------------------------------ 0_12 배열 뒤집기
class Solution {
    public int[] solution(int[] num_list) {
        int[] new_num_list = new int[num_list.length];
        for(int i=0; i<num_list.length ; i++){
            new_num_list[i] = num_list[num_list.length-1-i];
        }
        return new_num_list;
    }
}


//------------------------------------------------------------------------------ 0_13 최댓값 만들기 (1)
class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        int firstMax = 0;
        int secondMax = 0;
        for(int i=0; i<numbers.length; i++){
            if(numbers[i] > secondMax){
                //firstMax보다도 큰지 확인
                if(numbers[i] > firstMax){
                    secondMax = firstMax;
                    firstMax = numbers[i];
                } else{
                    secondMax = numbers[i];
                }
            }
        }
        answer = firstMax * secondMax;
        return answer;
    }
}

import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        Arrays.sort(numbers);
        answer = numbers[numbers.length-1] * numbers[numbers.length-2];

        return answer;
    }
}

//------------------------------------------------------------------------------ 0_14 순서쌍의 갯수
class Solution {
    public int solution(int n) {
        int answer = 0;
        for(int i=1; i<n+1;i++){
            if(n%i == 0){
                answer +=1;
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_15 배열 원소의 길이
class Solution {
    public int[] solution(String[] strlist) {
        int[] answer = new int[strlist.length];
        for(int i=0; i<strlist.length; i++){
            answer[i]  = strlist[i].length();       
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_16 배열의 유사도
class Solution {
    public int solution(String[] s1, String[] s2) {
        int answer = 0;
        //s1 for문 돌면서
        for(int i=0; i<s1.length; i++){
            //s2와 맞는지 확인
            for(int j=0; j<s2.length; j++){
                //맞으면 +1
                if(s1[i].equals(s2[j])){
                    answer += 1;
                }
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_17 삼각형의 완성조건 (1)
import java.util.*;

class Solution {
    public int solution(int[] sides) {
        int answer = 0;
        Arrays.sort(sides);
        int maxLen = sides[sides.length-1];
        int sumRestLen = 0;
        for(int i=0; i<sides.length-1; i++){
            sumRestLen += sides[i];
        }
        if(maxLen < sumRestLen ){
            answer = 1;
        } else{
            answer = 2;
        }
            
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_18 특정 문자 제거하기
class Solution {
    public String solution(String my_string, String letter) {
        String answer = "";      
        answer = my_string.replace(letter,"");
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_19 피자 나눠 먹기 (3)
class Solution {
    public int solution(int slice, int n) {
        int answer = n/slice;
        if(n % slice > 0){
            answer += 1;
        }
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_20 옷가게 할인 받기
class Solution {
    public int solution(int price) {
        int answer = 0;
        if(price >= 500000){
            answer = price * 8 /10;
        }else if(price >= 300000){
            answer = price * 9 /10;
        }else if(price >= 100000){  
            answer = price * 95 / 100;
        }else{
            answer = price;
        }
        return answer;
    }
}

class Solution {
    public int solution(int price) {
        int answer = 0;
        if(price >= 500000){
            answer = (int) (price * 0.8);
        }else if(price >= 300000){
            answer = (int) (price * 0.9);
        }else if(price >= 100000){  
            answer = (int) (price * 0.95);
        }else{
            answer = price;
        }
        return answer;
    }
}

