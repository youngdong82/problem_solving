//------------------------------------------------------------------------------ 0_31 분수의 덧셈
class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = new int[2];
        int tempNumer1 = numer1 * denom2;
        int tempNumer2 = numer2 * denom1;
        int publicDenom = denom1 * denom2;
        
        int publicNumer = tempNumer1 + tempNumer2;
        
        int gcd = gcd(Math.max(publicDenom, publicNumer), Math.min(publicDenom, publicNumer));
        
        answer[0] = publicNumer/gcd;
        answer[1] = publicDenom/gcd;
        
        return answer;
    }
    
    int gcd(int a, int b){
        if(b==0){
            return a;
        } else{
            return gcd(b, a%b);
        }
    }
}


//------------------------------------------------------------------------------ 0_32 최빈값 구하기 *
import java.util.*;

class Solution {
    public int solution(int[] array) {
        int answer = 0;
        
        if(array.length == 1){
            return array[0];
        }
        
        Arrays.sort(array);
        //최빈값, 갯수 변수 선언
        //하나씩 돌면서 업데이트
        int mostCommonInt = -1;
        int mostCommonCnt = 0;
        boolean severalCommon = false;
        
        int temp = -1;
        int tempCnt = 0;
        for(int i=0; i<array.length; i++){
            if(i!=0){
                //첫번째가 아닌 경우
                if(array[i] != array[i-1]){
                    //전에꺼랑 다를 때 새로운 것으로 세팅
                    temp = array[i];
                    tempCnt = 1;
                }else{
                    //전에꺼와 같을 때
                    tempCnt += 1;
                    if(tempCnt > mostCommonCnt){        //지금껏 쌓아온 것이 
                        severalCommon = false;
                        mostCommonInt = temp;
                        mostCommonCnt = tempCnt;
                    } else if(tempCnt == mostCommonCnt){
                        //같으면
                        severalCommon = true;
                    }
                }
            } else{
                //첫번째일 경우
                temp = array[i];
                tempCnt = 1;
            }   
        }
        if(severalCommon){
            answer = -1;
        }else{
            answer = mostCommonInt;
        }
        
        return answer;
    }
}

//----------------------------------------- HashMap 사용한건데 매우 깔끔함
import java.util.*;

class Solution {
    public int solution(int[] array) {
        int maxCount = 0;
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int number : array){
            int count = map.getOrDefault(number, 0) + 1;
            if(count > maxCount){
                maxCount = count;
                answer = number;
            }
            else if(count == maxCount){
                answer = -1;
            }
            map.put(number, count);
        }
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_33 n의 배수
class Solution {
    public int solution(int num, int n) {
        int answer = 0;
        if(num%n == 0){
            answer = 1;
        }else{
            answer = 0;
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_34 개미 군단
class Solution {
    public int solution(int hp) {
        int answer = 0;
        int[] antArray = {5,3,1};
        for(int ant: antArray){
            answer += hp/ant;
            hp = hp%ant;
        }
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_35 A 강조하기
import java.util.*;

class Solution {
    public String solution(String myString) {
        String answer = "";
        ArrayList<String> temp = new ArrayList<>();
        
        String[] myStringArray = myString.split("");
        for(String e : myStringArray){
            if(e.equals("a") || e.equals("A")){
                temp.add("A");
            }else{
                temp.add(e.toLowerCase());
            }
        }
        answer = String.join("", temp);
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_36 더 크게 합치기
import java.util.*;
    
class Solution {
    public int solution(int a, int b) {
        int answer = 0;
        //1.무지성 합치고 비교
        String stringA = Integer.toString(a);
        String stringB = Integer.toString(b);
        
        ArrayList<String> intArrayListA = new ArrayList<>();
        ArrayList<String> intArrayListB = new ArrayList<>();
        intArrayListA.add(stringA);
        intArrayListA.add(stringB);
        intArrayListB.add(stringB);
        intArrayListB.add(stringA);
        
        String ab = String.join("", intArrayListA);
        String ba = String.join("", intArrayListB);
        
        if(Integer.parseInt(ab) > Integer.parseInt(ba)){
            answer = Integer.parseInt(ab);
        }else{
            answer = Integer.parseInt(ba);
        }
        
        return answer;
    }
}
int => string => ArrayList에 담고 => String => Int
ㅋㅋㅋㅋㅋㅋㅋ


class Solution {
    public int solution(int a, int b) {
        String strA = String.valueOf(a);
        String strB = String.valueOf(b);
        String strSum1 = strA + strB;
        String strSum2 = strB + strA;
        return Math.max(Integer.valueOf(strSum1), Integer.valueOf(strSum2));
    }
}

//------------------------------------------------------------------------------ 0_37 공백으로 구분하기 2
import java.util.*;

class Solution {
    public String[] solution(String my_string) {
        String[] answer = {};
        String[] temp = my_string.split(" ");
        answer = Arrays.stream(temp)
            .filter(e -> !e.isBlank())
            .toArray(String[]::new);
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_38 뒤에서 5등 위로
import java.util.*;

class Solution {
    public int[] solution(int[] num_list) {
        int[] answer = {};
        Arrays.sort(num_list);
        
        answer = Arrays.copyOfRange(num_list, 5, num_list.length);
        return answer;
    }
}

import java.util.Arrays;

class Solution {
    public int[] solution(int[] numList) {
        return Arrays.stream(numList).sorted().skip(5).toArray();
    }
}

//------------------------------------------------------------------------------ 0_39 주사위 게임 1
class Solution {
    public int solution(int a, int b) {
        int answer = 0;
        boolean isOddA = a%2 == 0 ? false : true;
        boolean isOddB = b%2 == 0 ? false : true;
        
        if(isOddA && isOddB){
            answer = (a*a) + (b*b);
        }else if(isOddA || isOddB){
            answer = 2*(a + b);
        }else{
            answer = Math.abs(a-b);
        }
        return answer;
    }
}


//------------------------------------------------------------------------------ 0_40 공백으로 구분하기 1
import java.util.*;

class Solution {
    public String[] solution(String my_string) {
        String[] answer = my_string.split(" ");
        return answer;
    }
}