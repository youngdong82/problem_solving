//------------------------------------------------------------------------------ 0_1 두수의 합, 두수의 곱, 두수의 차, 두수의 몫, 나머지 구하기
class Solution {
    public int solution(int num1, int num2) {
        int answer = num1 + num2;
        return answer;
    }
}

class Solution {
    public int solution(int num1, int num2) {
        int answer = num1 * num2;
        return answer;
    }
}

class Solution {
    public int solution(int num1, int num2) {
        int answer = num1 - num2;
        return answer;
    }
}

class Solution {
    public int solution(int num1, int num2) {
        int answer = num1 / num2;
        return answer;
    }
}

class Solution {
    public int solution(int num1, int num2) {
        int answer = num1 % num2;
        return answer;
    }
}


//------------------------------------------------------------------------------ 0_2 숫자 비교하기
class Solution {
    public int solution(int num1, int num2) {
        int answer = 0;
        if(num1 == num2){
            answer = 1;
        }else{
            answer = -1;
        }
        return answer;
    }
}

삼항연산자
class Solution {
    public int solution(int num1, int num2) {
        int answer = (num1 == num2) ? 1 : -1;
        return answer;
    }
}


//------------------------------------------------------------------------------ 0_3 나이 출력
class Solution {
    public int solution(int age) {
        int answer = 2022 - age + 1;
        return answer;
    }
}


//------------------------------------------------------------------------------ 0_4 두 수의 나눗셈
class Solution {
    public int solution(int num1, int num2) {
        int answer = num1 * 1000 / num2;
        return answer;
    }
}


//------------------------------------------------------------------------------ 0_5 각도기
class Solution {
    public int solution(int angle) {
        int answer = 0;
        if(angle == 90){
            answer = 2;
        } else if(angle == 180){
            answer = 4;
        } else if(angle < 90){
            answer = 1;
        } else{
            answer = 3;
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_6 짝수의 합
class Solution {
    public int solution(int n) {
        int answer = 0;
        for(int i=1; i <= n; i++){
            if(i % 2 == 0){
                answer += i;
            }
        }
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_7 배열의 평균값
import java.util.Arrays;

class Solution {
    public double solution(int[] numbers) {

        return Arrays.stream(numbers).average().orElse(0);
    }
}

//------------------------------------------------------------------------------ 0_8 양꼬치
class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        k -= n/10;
        int newK = (k < 0) ? 0 : k;
        
        answer = (12000 * n) + (2000 * newK);
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_9 편지
class Solution {
    public int solution(String message) {
        int answer = 0;
        answer = message.length() * 2;
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_10 피자 나눠 먹기 (1)
class Solution {
    public int solution(int n) {
        int answer = (n%7 > 0) ? n/7 + 1 : n/7;
        return answer;
    }
}
