//------------------------------------------------------------------------------ 2_01 최댓값과 최솟값 / 이진 변환 반복하기
class Solution {
    public String solution(String s) {
        int maxx = Integer.MIN_VALUE;
        int minn = Integer.MAX_VALUE;
        
        String[] splitedS = s.split(" ");
        for(String each: splitedS){
            int intS = Integer.parseInt(each);
            if(intS > maxx){
                maxx = intS;
            }
            if(intS < minn){
                minn = intS;
            }
        }
        return minn + " "+ maxx;
    }
}


class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        int count = 0;
        int zeroCount = 0;
        while(!s.equals("1")){
            //0빼기
            char[] charS = s.toCharArray();
            String tmp = "";
            for(int i=0; i<charS.length; i++){
                if(charS[i] == '0'){
                    zeroCount++;
                }else{
                    tmp += charS[i];
                }
            }
            s = Integer.toBinaryString(tmp.length());
            
            count ++;
        }
        answer[0] = count;
        answer[1] = zeroCount;
        return answer;
    }
}


// 0빼기를 replace로 현명하게 처리
class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        int temp;
        while( !s.equals("1") ) {
            answer[1] += s.length();
            s = s.replaceAll("0", "");
            temp = s.length();
            s = Integer.toBinaryString(temp);

            answer[0]++;
            answer[1] -= temp;
        }
        return answer;  
    }
}

//------------------------------------------------------------------------------ 2_02 최솟값 만들기 (정렬 알고리즘) / 다음 큰 숫자
import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        
        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();
        for(int i=0; i<A.length; i++){
            listA.add(A[i]);
            listB.add(B[i]);
        }
        
        Collections.sort(listA);
        Collections.sort(listB, Collections.reverseOrder());
        
        for(int i=0; i<listA.size(); i++){
            answer += (listA.get(i) * listB.get(i));
        }

        return answer;
    }
}

class Solution {
    public int solution(int n) {
        int answer = n;
        //n의 이진법 1 갯수 세기
        String binaryN = Integer.toBinaryString(n);
        String afterBinaryN = binaryN.replace("1","");
        int countOne = binaryN.length() - afterBinaryN.length();
        while(true){
            answer++;
            String binaryAns = Integer.toBinaryString(answer);
            String afterBinaryAns = binaryAns.replace("1","");
            if(binaryAns.length() - afterBinaryAns.length() == countOne){ //1의 갯수
                break;
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 2_03 짝지어 제거하기 
//시간초과ㅋㅋㅋㅋㅋㅋ
class Solution
{
    public int solution(String s)
    {
        int answer = -1;

        while(true){
            String afterS = erase(s);
            if(afterS.length() == 0){
                answer = 1;
                break;
            }
            if(afterS.equals(s)){
                answer = 0;
                break;
            }
        }

        return answer;
    }
    String erase(String s){
        String abc = "abcdefghijklmnopqrstuvwxyz";
        String[] alphabet = abc.split("");
        
        for(String each: alphabet){
            s = s.replace(each + each,"");
        }   
        
        return s;
    }
}

//아슬아슬
import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = -1;
        Stack<String> stack = new Stack<>();
        String[] splitedS = s.split("");
        for(String each: splitedS){
            if(stack.isEmpty()){
                stack.push(each);
            }else{
                String peeek = stack.peek();
                if(peeek.equals(each)){
                    stack.pop();
                }else{
                    stack.push(each);
                }
            }
        }
        if(stack.size() == 0){
            answer = 1;
        }else{
            answer = 0;
        }

        return answer;
    }
}

//무지성 split 안쓰고 string.charAt으로 인덱스로 접근
import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = -1;
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++){
            if(stack.isEmpty()){
                stack.push(s.charAt(i));
            }else{
                Character peeek = stack.peek();
                if(peeek == s.charAt(i)){
                    stack.pop();
                }else{
                    stack.push(s.charAt(i));
                }
            }
        }
        if(stack.size() == 0){
            answer = 1;
        }else{
            answer = 0;
        }

        return answer;
    }
}

//------------------------------------------------------------------------------ 2_04 영어 끝말잇기 / 점프와 순간 이동
import java.util.*;


class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        //HashMap으로 단어여부 확인
        HashMap<String,Integer> map = new HashMap<>();
        map.put(words[0], 1);
        for(int i=1; i<words.length; i++){
            int isExist = map.getOrDefault(words[i], -1);
            String nowWord = words[i];
            String befWord = words[i-1];
            
            if(befWord.charAt(befWord.length()-1) == nowWord.charAt(0)
            && isExist == -1
              ){
                map.put(words[i],1);
            }else{                      //탈락
                answer[0] = (i+1)%n == 0 ? n:(i+1)%n;      //번호
                answer[1] = (i+1)%n == 0 ? (i+1)/n : (i+1)/n+ 1; 
                break;
            }
        }

        return answer;
    }
}

import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;
        while(n>0){
            if(n%2 == 0){   //짝수면 나누고
                n /= 2;
            }else{      //홀수면 -1
                n--;
                ans ++;
            }
        }

        return ans;
    }
}
//------------------------------------------------------------------------------ 2_05 괄호 회전하기 / H-Index
import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        //갯수가 짝수인가?
        if(s.length()%2 != 0){
            return 0;
        }
        //올바른가? => 최악을 막기위해 해야함
        if(!checkValidMild(s)){
            return 0;
        }
        //몇번 올바른가
        for(int i=0; i<s.length(); i++){
            String target = s.substring(i) + s.substring(0,i);
            if(checkValid(target)){
                answer ++;
            }
        }

        return answer;
    }
    boolean checkValidMild(String s){
        String[] banArr = {"[}","[)","{)", "{]", "(]", "(}"};
        List<String> banList = Arrays.asList(banArr);
        
        char[] charArrayS = s.toCharArray();
        for(int i=0; i<charArrayS.length-1; i++){
            String tmp = "" +charArrayS[i] + charArrayS[i+1];
            if(banList.contains(tmp)){
                return false;
            }
        }
        return true;
    }
    boolean checkValid(String target){
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<target.length(); i++){
            if(stack.size() == 0){
                stack.push(target.charAt(i));
                continue;
            }
            if(target.charAt(i) == '['
            || target.charAt(i) == '{'
            || target.charAt(i) == '('
            ){
            stack.push(target.charAt(i));    
            }else{
                Character peeek = stack.peek();
                if((target.charAt(i) == ']' && peeek == '[')
                 ||(target.charAt(i) == '}' && peeek == '{')
                 ||(target.charAt(i) == ')' && peeek == '(')
                  ){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        if(stack.size() != 0){
            return false;
        }
        //System.out.println("valid" + target);
        return true;  
    }
}

import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        ArrayList<Integer> citaList = new ArrayList<>();
        for(int i=0; i<citations.length; i++){
            citaList.add(citations[i]);
        }
        Collections.sort(citaList, Collections.reverseOrder());
        
        for(int i=citations.length; i>=0; i--){
            int count = 0;
            for(Integer each: citaList){
                if(each >= i){
                    count++;
                }else{
                    break;
                }
            }
            if(count >=i){
                answer = Math.max(i,answer);
                break;
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 2_06 JadenCase 문자열 만들기 / 올바른 괄호
import java.util.*;

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        String[] splitedS = s.toLowerCase().split(" ",-1);

        for(int i=0; i<splitedS.length; i++){
            if(i != 0){
                sb.append(" ");
            }
            if(splitedS[i].length()>0){
                if(Character.isAlphabetic(splitedS[i].charAt(0))){
                    sb.append(Character.toUpperCase(splitedS[i].charAt(0)) + splitedS[i].substring(1));
                }else{
                    sb.append(splitedS[i]);
                }   
            }
        }
        
        return sb.toString();
    }
}

class Solution {
    boolean solution(String s) {
        int count = 0;
        for(int i=0; i<s.length(); i++){
            if(count<0){
                return false;
            }
            if(s.charAt(i) == '('){
                count ++;
            }else{
                count --;
            }
        }
        if(count != 0){
            return false;
        }

        return true;
    }
}
//------------------------------------------------------------------------------ 2_07 숫자의 표현 / 피보나치 수 (BigInteger)
class Solution {
    public int solution(int n) {
        int answer = 0;
        int start = 0;
        int end = 1;
        if(n == 1 || n==2){
            return 1;
        }
        
        //자신을 제외하고 만들기
        while( start < end && end <= (n/2)+1){
            if(summ(start,end) < n){    //작으면
                end++;
            }else if(summ(start,end) > n){  //크면
                start++;
            }else{
                answer ++;
                end++;
            }
        }
        //자기자신 추가
        return answer+1;
    }
    int summ (int start, int end){
        return (end*(end+1) /2) - (start*(start+1) /2) ;
    }
}


import java.math.BigInteger;

class Solution {
    public int solution(int n) {
        BigInteger[] intArr = new BigInteger[n+1];
        
        intArr[0] = BigInteger.valueOf(0);
        intArr[1] = BigInteger.valueOf(1);
        
        for(int i=2; i<n+1; i++){
            intArr[i] = intArr[i-1].add(intArr[i-2]);
        }
        
        BigInteger answer = intArr[n].remainder(BigInteger.valueOf(1234567));
        
        return answer.intValue();
    }
}


//------------------------------------------------------------------------------ 2_08 카펫 / 구명보트
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = brown + yellow;
        
        //3이상 약수 구하기
        for(int i=3; i<(int)Math.sqrt(sum)+1; i++){
            if(sum%i == 0){
                int width = sum/i;
                if((width-2) * (i-2) == yellow){
                    answer[0] = width;
                    answer[1] = i;
                }
            }
        }
            
        return answer;
    }
}

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int left= 0, right = people.length-1;
        
        while(true){
            if(left >right) break;
            if(left == right){
              answer++;
              break;  
            } 
            int sum = people[right] + people[left];
            if(sum <= limit){   //둘이 탈 수 있니?
                answer++;
                left++;
                right--;
            }else{              //무거운 애 혼자 타자
                answer++;
                right --;
            }
        }
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 2_09 예상 대진표 / 멀리 뛰기
class Solution{
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        for(int i=n; i>0; i/=2){
            if(a == b){
                answer-=1;
                break;
            }
            answer += 1;
            a = calc(a);
            b = calc(b);
        }

        return answer;
    }
    int calc(int a){
        if(a%2 != 0){
            return (a+1)/2;
        }
        return a/2;
    }
}


//피보나치래;;
class Solution {
    public long solution(int n) {
        long answer = 0;
        long[] dp = new long[n+2];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3; i<n+1; i++){
            dp[i] = (dp[i-1] + dp[i-2]) %1234567;
        }
        answer = dp[n];
        
        return answer;
    }
}
//------------------------------------------------------------------------------ 2_10 n^2 배열 자르기
class Solution {
    public int[] solution(int n, long left, long right) {
        long many = right-left + 1;
        int[] answer = new int[(int)many];
        for(int i=0; i<many; i++){
            long target = left + i;
            long time = (target)/n;
            long andThat = (target)%n;

            if(andThat <= time){
                answer[i] = (int)(time+1);
            }else{
                answer[i] = (int)(time+1 + (andThat-time));
            }
        }
        return answer;
    }
}




