//정답률 87~72%
//생각보다 LV0이랑 크게 차이 안나고 쉬워서
//2개씩 품
//------------------------------------------------------------------------------ 0_11 나누어 떨어지는 숫자 배열 / 콜라츠 추측
import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        int[] answer = {};
        ArrayList<Integer> answerArr = new ArrayList<>();
        for(int e: arr){
            if(e%divisor == 0){
                answerArr.add(e);
            }
        }
        if(answerArr.size() == 0){
            answerArr.add(-1);
        }
        //answerArr.sort(Comparator.naturalOrder());
        answer = answerArr.stream().sorted().toArray();
        System.out.println(answer);
        
        return answer;
    }
}
class Solution {
    public int solution(int num) {
        int answer = 0;
        if(num == 1) return 0;
        long longNum = num;
        for(int i=0; i<500; i++){
            System.out.println(longNum);
            if(i==499){
                answer = -1;
                break;
            }
            if(longNum%2==0){
                longNum = longNum/2;
            }else{
                longNum = longNum*3 +1;
            }
            if(longNum == 1){
                answer = i+1;
                break;
            }      
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_12 하샤드 수 / 정수 제곱근 판별
class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        String strX = "" + x;
        String[] strArrayX = strX.split("");
        int summ = 0;
        for(String e: strArrayX){
            summ += Integer.parseInt(e);
        }
        if(x % summ != 0){
            answer = false;
        }
        return answer;
    }
    
}

class Solution {
    public long solution(long n) {
        long answer = 0;
        long sqrt = (long)Math.sqrt(n);
        if(sqrt*sqrt != n){
            answer = -1;
        }else{
            answer = (sqrt+1)*(sqrt+1);
        }
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_13 문자열을 정수로 바꾸기 / 평균 구하기
//ㅅㅂ...이게 머야 너무 쉽잖아
class Solution {
    public int solution(String s) {
        int answer = Integer.parseInt(s);
        return answer;
    }
}

import java.util.*;

class Solution {
    public double solution(int[] arr) {
        double answer = Arrays.stream(arr).average().orElse(0);
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_14 가운데 글자 가져오기 / 내적
class Solution {
    public String solution(String s) {
        String answer = "";
        int len = s.length();
        System.out.println(len);
        if(len %2 == 0){
            System.out.println("홀수");
            //홀수일때 1개 리턴
            answer = s.substring(len/2-1, len/2+1);
        }else{
            System.out.println("짝수");
            //짝수일때 2개 리턴
            answer = s.substring(len/2, len/2+1);
        }

        return answer;
    }
}

class Solution {
    public int solution(int[] a, int[] b) {
        int answer = 0;
        for(int i=0; i<a.length; i++){
            answer += (a[i]*b[i]);
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_15 문자열 내림차순으로 배치하기 / 문자열 다루기 기본
import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        char[] charArrayS = s.toCharArray();
        Arrays.sort(charArrayS);
        StringBuilder sb = new StringBuilder(new String(charArrayS));
        String sortedCharArrayS = sb.reverse().toString();
        
        return sortedCharArrayS;
    }
}

class Solution {
    public boolean solution(String s) {
        boolean answer = true;
        if(s.length() == 4 || s.length() == 6){
            try{
                int test = Integer.parseInt(s);
            }catch(Exception e){
                answer = false;
            }
        }else{
            answer = false;
        }

        return answer;
    }
}

//------------------------------------------------------------------------------ 0_16 직사각형 별찍기 / 예산
import java.util.Scanner;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        for(int i=0; i<b; i++){
            for(int j=0; j<a; j++){
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}

import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        Arrays.sort(d);
        
        for(int e: d){
            budget -= e;
            if(budget < 0){
                break;
            }else{
                answer+=1;
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_17 시저 암호 / K번째수
class Solution {
    public String solution(String s, int n) {
        //ascii 모르면 하나하나 써도 됨
        String answer = "";
        char[] charArrayS = s.toCharArray();
        for(char e: charArrayS){
            if(e == ' '){
                //공백
                answer += e;
            }else if(65<= e && e<91){
                //대문자              
                answer += (char)(((e+n-65)%26)+65);
            }else if(97<= e && e<123){
                //소문자
                answer += (char)(((e+n-97)%26)+97);
            }
        }
        return answer;
    }
}

import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for(int i=0; i<commands.length; i++){
            int[] command = commands[i];
            int[] cuttedCommand = Arrays.copyOfRange(array, command[0]-1, command[1]);
            Arrays.sort(cuttedCommand);
            answer[i] = cuttedCommand[command[2]-1];
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_18 [1차] 비밀지도
import java.util.*;

class Solution {
    public String[] solution(long n, int[] arr1, int[] arr2) {
        String[] answer = new String[arr1.length];
        
        for(int i=0; i<n; i++){
            String temp = "";
            long[] convertedArr1 = converToFillBinary(arr1[i],arr1.length);
            long[] convertedArr2 = converToFillBinary(arr2[i],arr1.length);
            for(int j=0; j<n; j++){
                if(convertedArr1[j] + convertedArr2[j] > 0){
                    temp += "#";
                }else{
                    temp += " ";
                }
            }
            answer[i] = temp;
        }
        
        return answer;
    }
    private long[] converToFillBinary(int val, int len){
        String binary = Integer.toString(val,2);
        String fillBinary = String.format("%0" + len + "d", Long.parseLong(binary));
        String[] splitedFillBinary = fillBinary.split("");
        
        return Arrays.stream(splitedFillBinary).mapToLong(Long::parseLong).toArray();
    }
}



//------------------------------------------------------------------------------ 0_19 3진법 뒤집기
class Solution {
    public int solution(int n) {
        int answer = 0;
        String converted3 = Integer.toString(n,3);
        StringBuilder sb = new StringBuilder(converted3);
        String reversedConverted3 = sb.reverse().toString();
        answer = Integer.parseInt(reversedConverted3,3);
        return answer;
    }
}

// 이거 한번 해보면서 찍어보셈=================== ************** 비교할 수 없을정도로 위에가 훨씬 빠름

class Solution {
    public int solution(int n) {
        String a = "";
        while(n > 0){
            a = (n % 3) + a;
            n /= 3;
        }
        a = new StringBuilder(a).reverse().toString();

        return Integer.parseInt(a,3);
    }
}

//------------------------------------------------------------------------------ 0_20 숫자 문자열과 영단어
import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        String[] arrayStrDigit = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String strAnswer = "";
        String tmp = "";
        char[] charArrayS = s.toCharArray();
        for(char e: charArrayS){
            if(Character.isDigit(e)){
                strAnswer += e;
            }else{
                tmp += e;
                if(Arrays.asList(arrayStrDigit).contains(tmp)){
                    strAnswer += Arrays.asList(arrayStrDigit).indexOf(tmp);
                    tmp = "";
                }
            }
        }
        answer = Integer.parseInt(strAnswer);
        return answer;
    }
}

//replace를 사용하자...

class Solution {
    public int solution(String s) {
        String[] strArr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for(int i = 0; i < strArr.length; i++) {
            s = s.replaceAll(strArr[i], Integer.toString(i));
        }
        return Integer.parseInt(s);
    }
}




