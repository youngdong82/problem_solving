//------------------------------------------------------------------------------ 0_91 특별한 이차원 배열 1
import java.util.*;

class Solution {
    public int[][] solution(int n) {
        int[][] answer = new int[n][n];
        for(int i=0; i<answer.length; i++){
            answer[i][i] = 1;
        }
        System.out.println(Arrays.deepToString(answer));
        
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_92 문자열 잘라서 정렬하기
import java.util.*;

class Solution {
    public String[] solution(String myString) {
        String[] answer = {};
        String [] splitedStr = myString.split("x");
        
        String [] trimedsplitedStr = Arrays.stream(splitedStr)
                                        .filter(e -> !e.equals(""))
                                        .toArray(String[]::new);
        Arrays.sort(trimedsplitedStr);
        
        return trimedsplitedStr;
    }
}

//------------------------------------------------------------------------------ 0_93 문자열 정렬하기 (1)
import java.util.*;

class Solution {
    public int[] solution(String my_string) {
        int[] answer = {};
        String tmpAnswer = "";
        for(char e: my_string.toCharArray()){
            //ascii로 숫자 확인
            if(e >= 48 && e <58){
                tmpAnswer += e;
            }
        }
        
        answer = Arrays.stream(tmpAnswer.split(""))
            .sorted()
            .mapToInt(Integer::parseInt)
            .toArray();
        
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_94 치킨 쿠폰
class Solution {
    public int solution(int chicken) {
        int answer = -1;
        answer =recursiveBonus(chicken, 0);
        
        return answer;
    }
    int recursiveBonus(int all, int bonus){
        System.out.println(all + " bonus:"+ bonus);
        if((all)/10  == 0){
            return bonus;
        }
        return recursiveBonus(all/10 + all%10, bonus + all/10);
    }
}
//------------------------------------------------------------------------------ 0_95 다음에 올 숫자
class Solution {
    public int solution(int[] common) {
        int answer = 0;
        //3개의 원소로 등비인지 등차인지 확인
        if(common[1] - common[0] == common[2] - common[1]){
            int diff = common[1] - common[0];
            answer = common[common.length-1] + diff;
        }else{
            int diff = common[1] / common[0];
            answer = common[common.length-1] * diff;
        }
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_96 종이 자르기 문자열안에 문자열 
class Solution {
    public int solution(int M, int N) {
        return M*N-1;
    }
}

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        if(str1.contains(str2)){
            answer = 1;
        }else{
            answer = 2;
        }
        return answer;
    }
}



//------------------------------------------------------------------------------ 0_97 그림 확대
class Solution {
    public String[] solution(String[] picture, int k) {
        String[] answer = new String[picture.length * k];

        for(int i=0; i<picture.length; i++){
          String tmp = "";
          for(char c : picture[i].toCharArray()){
            for(int j=0; j<k; j++){
                tmp+=c;
            }
          }
          for(int j=0; j<k; j++){
            answer[(i*k)+j] = tmp;
          }
        }
        return answer;
    }
}

//좀 더 깔끔하게 할 수 있을 것 같은데...

//------------------------------------------------------------------------------ 0_98 연속된 수의 합
class Solution {
    public int[] solution(int num, int total) {
        int[] answer = new int[num];
        // num이 100이하이고 total이 0이상이니까
        // num이 100일때  total이 0일 경우    -100이 최소
        // num이 1때      total이 1000일 경우 1000이 최대
        for(int i=-100; i<1001-num; i++){
            if(sumSequence(i, num) == total){
                for(int j=0; j<num; j++){
                    answer[j] = i+j;
                }
            }
        }
        return answer;
    }
    int sumSequence(int start, int many){
        int sumStartEnd = start + (start + many-1);
        if(many%2 == 0){
            return sumStartEnd * (many/2);
        }else{
            return (sumStartEnd * (many/2)) + (sumStartEnd/2);
        }
    }
}
//------------------------------------------------------------------------------ 0_99 정수를 나선형으로 배치하기
class Solution {
    public int[][] solution(int n) {
        int[][] answer = new int[n][n];
        answer[0][0] = 1;
        
        int[] moveX = {1,0,-1,0};
        int[] moveY = {0,1,0,-1};
        
        int direc = 0;
        int nowX = 0;
        int nowY = 0;
        int count = 1;
        
        while(count < n*n){
            int tmpX = nowX + moveX[direc];
            int tmpY = nowY + moveY[direc];
            
            //유효성 검사
            if(tmpX < 0 || tmpX >= n || tmpY < 0 || tmpY >= n 
               || answer[tmpY][tmpX] != 0){
                direc =  (direc + 1)%4;
                continue;
            }
            
            nowX = tmpX;
            nowY = tmpY;
            count += 1;
            answer[nowY][nowX] = count;
        }
    
        return answer;
    }
}
//------------------------------------------------------------------------------ 0_100 OX퀴즈
class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        for(int i=0; i<quiz.length; i++){
            String[] calcResult = quiz[i].split(" = ");
            int result = Integer.parseInt(calcResult[1]);
            String[] calc = calcResult[0].split(" ");
            
            if(calc[1].equals("+")){
                answer[i] = Integer.parseInt(calc[0]) + Integer.parseInt(calc[2]) == result ? "O" : "X";
            }else{
                answer[i] = Integer.parseInt(calc[0]) - Integer.parseInt(calc[2]) == result ? "O" : "X";
            }
            
        }
        return answer;
    }
}









