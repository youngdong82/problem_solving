//------------------------------------------------------------------------------ 0_21 자릿수 더하기
import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        String strInt = Integer.toString(n);
        String[] splitedStrInt = strInt.split("");
        
        for(String each : splitedStrInt){
            answer += Integer.parseInt(each);
        }

        return answer;
    }
}

//------------------------------------------------------------------------------ 0_22 아이스 아메리카노
class Solution {
    public int[] solution(int money) {
        int[] answer = new int[2];
        answer[0] = money/5500;
        answer[1] = money%5500;
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_23 짝수는 싫어요
import java.util.*;

class Solution {
    public int[] solution(int n) {
        ArrayList<Integer> tmpAnswer = new ArrayList<>();
        for(int i=1; i<n+1; i++){
            if(i%2 != 0){
                tmpAnswer.add(i);
            }
        }
        return tmpAnswer.stream().mapToInt(x -> x).toArray();
    }
}


//------------------------------------------------------------------------------ 0_24 숨어있는 숫자의 덧셈 (1)
class Solution {
    public int solution(String my_string) {
        int answer = 0;
        char[] myCharArray = my_string.toCharArray();
        
        for(char each: myCharArray){
            if(Character.isDigit(each)){
                answer += Character.getNumericValue(each);
            }   
        }
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_25 중앙값 구하기
import java.util.*;

class Solution {
    public int solution(int[] array) {
        int answer = 0;
        Arrays.sort(array);
        answer = array[array.length/2];
        
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_26 n보다 커질 때까지 더하기
class Solution {
    public int solution(int[] numbers, int n) {
        int answer = 0;
        for(int i=0; i<numbers.length; i++){
            if(answer > n){
                break;
            }
            answer += numbers[i];
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_27 문자열 정수의 합
import java.util.*;

class Solution {
    public int solution(String num_str) {
        int answer = 0;
        String[] numString = num_str.split("");
        for(String e : numString){
            answer += Integer.parseInt(e);
        }
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_28 길이에 따른 연산
import java.util.*;

class Solution {
    public int solution(int[] num_list) {
        int answer = 0;
        if(num_list.length >= 11){
            //전부 합치기
            answer = Arrays.stream(num_list).sum();
        }else{
            //전부 곱하기
            answer = Arrays.stream(num_list).reduce(1,(x,y) -> x*y);
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_29 원소들의 곱과 합
import java.util.*;

class Solution {
    public int solution(int[] num_list) {
        int answer = 0;
        int sum = Arrays.stream(num_list).sum();
        int multipl = Arrays.stream(num_list).reduce(1,(x,y) -> x*y);
        
        answer = sum*sum > multipl ? 1 : 0;
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_30 정수 부분
class Solution {
    public int solution(double flo) {
        int answer = 0;
        answer = (int)flo;
        return answer;
    }
}
