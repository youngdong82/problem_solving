//정답률 87~72%
//생각보다 LV0이랑 크게 차이 안나고 쉬워서
//2개씩 품
//------------------------------------------------------------------------------ 0_1 나머지가 1이 되는 수 찾기 / 자연수 뒤집어 배열로 만들기
class Solution {
    public int solution(int n) {
        int answer = 0;
        //n-1을 만들 수 있는 약수 중 1을 제외한 가장 작은 수?
        for(int i=2; i<=n; i++){
            if(n%i==1){
                answer = i;
                break;
            }
        }
        return answer;
    }
}

import java.util.*;

class Solution {
    public int[] solution(long n) {
        int[] answer = {};
        String strN = "" + n;
        StringBuilder sb = new StringBuilder(strN);
        String reversedN = sb.reverse().toString();
        answer = Arrays.stream(reversedN.split("")).mapToInt(Integer::parseInt).toArray();
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_2 x만큼 간격이 있는 n개의 숫자 / 짝수와 홀수
class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        for(int i=0; i<n; i++){
            answer[i] = x* (long)(i+1);
        }
        return answer;
    }
}

class Solution {
    public String solution(int num) {
        return num%2 == 0 ? "Even" : "Odd";
    }
}
//------------------------------------------------------------------------------ 0_3 문자열 내 p와 y의 개수 / 정수 내림차순으로 배치하기
class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int count = 0;
        String lowS = s.toLowerCase();
        for(char e: lowS.toCharArray()){
            if(e == 'p'){
                count += 1;
            }else if(e == 'y'){
                count -= 1;
            }
        }

        if(count != 0){
            answer = false;
        }
        return answer;
    }
}

import java.util.*;

class Solution {
    public long solution(long n) {
        String strN = n + "";
        char [] strArrN = strN.toCharArray();
        //정렬
        Arrays.sort(strArrN);
        //뒤집기
        StringBuilder sb = new StringBuilder(new String(strArrN));
        String reversedN = sb.reverse().toString();
        //합치고 정수로 만들기
        return Long.parseLong(reversedN);
    }
}

//------------------------------------------------------------------------------ 0_4 두 정수 사이의 합  / 서울에서 김서방 찾기
class Solution {
    public long solution(int a, int b) {
        long answer = 0;
        long diff = Math.abs(b-a);
        
        if(diff == 0){
            return (long) a;
        }
        if(diff%2 == 0){
            answer = (long)(((a + b) * (diff/2)) + ((a + b)/2));
        }else{
            answer = (long)((a + b) * ((diff+1)/2));
        }
        return answer;
    }
}
// 훌륭하네... 그래도 어쩔 수 없다!
class Solution {

    public long solution(int a, int b) {
        return sumAtoB(Math.min(a, b), Math.max(b, a));
    }

    private long sumAtoB(long a, long b) {
        return (b - a + 1) * (a + b) / 2;
    }
}

class Solution {
    public String solution(String[] seoul) {
        for(int i=0; i<seoul.length;i++){
            if(seoul[i].equals("Kim")){
                String indexStr = "" + i;
                return "김서방은 " + indexStr +"에 있다";
            }
        }
        return "";
    }
}

//------------------------------------------------------------------------------ 0_5 음양 더하기 / 없는 숫자 더하기
class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        for(int i=0; i<absolutes.length; i++){
            if(signs[i] == true){
                answer += absolutes[i];
            }else{
                answer += Integer.parseInt("-" +absolutes[i]);
            }
        }
        return answer;
    }
}

import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        int numbersSum = Arrays.stream(numbers).sum();
        return 45 - numbersSum;
    }
}

//------------------------------------------------------------------------------ 0_6 수박수박수박수박수박수? / 약수의 개수와 덧셈
class Solution {
    public String solution(int n) {
        String answer = "";
        for(int i=0; i<n; i++){
            String plus = i%2 == 0 ? "수" : "박";
            answer += plus;
        }
        return answer;
    }
}

class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        for(int i=left; i<right+1; i++){
            if(isOdd(i)){
                answer -= i;
            }else{
                answer += i;
            }
        }
        return answer;
    }
    boolean isOdd(int target){
        return Math.ceil(Math.sqrt(target)) == Math.sqrt(target);
    }
}
//------------------------------------------------------------------------------ 0_7 부족한 금액 계산하기 / 행렬의 덧셈
class Solution {
    public long solution(int price, int money, int count) {
        long answer = -1;
        long sumPrices = price * (long)(count*(count+1)) /2;
        
        //충분한지?
        if(sumPrices > money){
            answer = sumPrices - money;
        }else{
            answer = 0;
        }

        return answer;
    }
}

class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr1[0].length];
        for(int i=0; i<arr1.length; i++){
            for(int j=0; j<arr1[i].length; j++){
                answer[i][j] = arr1[i][j] + arr2[i][j];
            }
        }
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_8 같은 숫자는 싫어 / 이상한 문자 만들기
import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        Stack<Integer> stack = new Stack();
        for(int i=0; i<arr.length; i++){
            if(i==0){
                stack.push(arr[i]);
            }else{
                if(stack.peek() != arr[i]){
                    stack.push(arr[i]);
                }
            }
        }
        answer = stack.stream().mapToInt(e->e).toArray();
        
        return answer;
    }
}

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] arrayS = s.split(" ",-1);
        String[] tmpAnswer = new String[arrayS.length];
        
        for(int i=0; i<arrayS.length; i++){
            String[] arrayArrayS = arrayS[i].split("");
            String tmp = "";
            for(int j=0; j<arrayArrayS.length; j++){
                if(j%2==0){
                    tmp += arrayArrayS[j].toUpperCase();
                }else{
                    tmp += arrayArrayS[j].toLowerCase();
                }
            }
            tmpAnswer[i] = tmp;
        }
        answer = String.join(" ", tmpAnswer);
        System.out.println(answer);
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_9 최소직사각형 / 자릿수 더하기
class Solution {
    public int solution(int[][] sizes) {
        int maxMaxWidth = 0;
        int maxMinHeight = 0;
        
        for(int i=0; i<sizes.length; i++){
            int big = Math.max(sizes[i][0], sizes[i][1]);
            int small = Math.min(sizes[i][0], sizes[i][1]);
            if(maxMaxWidth < big){
                maxMaxWidth = big;
            }
            if(maxMinHeight < small){
                maxMinHeight = small;
            }       
        }
        
        return maxMaxWidth * maxMinHeight;
    }
}

public class Solution {
    public int solution(int n) {
        int answer = 0;
        String nStr = "" + n;
        String[] nStrArray = nStr.split("");
        for(String e: nStrArray){
            answer += Integer.parseInt(e);
        }

        return answer;
    }
}


//------------------------------------------------------------------------------ 0_10 제일 작은 수 제거하기 / 핸드폰 번호 가리기
import java.util.*;

class Solution {
    public int[] solution(int[] arr) {     
        //최소값 구하기
        int minValue = 100000;
        for(int e : arr){
            if(e < minValue){
                minValue = e;
            }
        }
        //최소값 뺀 배열 만들기
        ArrayList<Integer> answerList = new ArrayList<>();
        for(int e : arr){
            if(e != minValue){
                answerList.add(e);
            }
        }
        //없다면 -1 추가
        if(answerList.size() == 0){
            answerList.add(-1);
        }
        
        int[] answer = answerList.stream().mapToInt(e->e).toArray();
        
        return answer;
    }
}

class Solution {
    public String solution(String phone_number) {
        String answer = "";
        char[] charArray = phone_number.toCharArray();
        for(int i=0; i<charArray.length; i++){
            if(i < charArray.length-4){
                answer += "*";
            }else{
                answer += charArray[i];
            }
        }
        
        return answer;
    }
}
