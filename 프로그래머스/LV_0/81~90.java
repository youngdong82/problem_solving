//------------------------------------------------------------------------------ 0_81 문자열 계산하기
class Solution {
    public int solution(String my_string) {
        String[] splitedMyStr = my_string.split(" ");
        int answer = Integer.parseInt(splitedMyStr[0]);
        for(int i=1; i<splitedMyStr.length; i++){
            if(i%2 != 0){
                //연산자일때
                if(splitedMyStr[i].equals("+")){
                    answer += Integer.parseInt(splitedMyStr[i+1]);
                }else{
                    answer -= Integer.parseInt(splitedMyStr[i+1]);
                }
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_82 왼쪽 오른쪽
import java.util.*;

class Solution {
    public String[] solution(String[] str_list) {
        String[] answer = {};
        
        for(int i=0; i<str_list.length; i++){
            if(str_list[i].equals("l")){
                answer = Arrays.copyOfRange(str_list, 0, i);
                break;
            }else if(str_list[i].equals("r")){
                answer = Arrays.copyOfRange(str_list, i+1, str_list.length);
                break;
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_83 정사각형으로 만들기
class Solution {
    public int[][] solution(int[][] arr) {
        
        int width = arr.length;
        int height = arr[0].length;
        int standard = Math.max(width, height);

        int[][] answer = new int[standard][standard];
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[i].length; j++){
                answer[i][j] = arr[i][j];
            }
        }
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_84 배열 만들기4
import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
      int[] stk = {};
      //String으로도 가능할듯
      ArrayList<Integer> tmp = new ArrayList<>();
      for(int i=0; i<arr.length; i++){
          if(tmp.size() == 0){
              tmp.add(arr[i]);
          }else{
              if(tmp.get(tmp.size()-1) < arr[i]){
                  tmp.add(arr[i]);
              }else{
                  tmp.remove(tmp.size()-1);
                  i--;
              }
          }
      }
      return tmp.stream().mapToInt(e -> e).toArray();
    }
}
//------------------------------------------------------------------------------ 0_85 배열 만들기 6
import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int[] answer = new int[1];
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<arr.length; i++){
            if(stack.empty()){
                stack.push(arr[i]);
            }else{
                if(stack.peek() == arr[i]){
                    stack.pop();
                }else{
                    stack.push(arr[i]);
                }
            }
        }
        if(stack.size() == 0){
            answer[0] = -1;
        }else{
            answer = stack.stream().mapToInt(e->e).toArray();
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_86 직사각형 넓이 구하기
class Solution {
    public int solution(int[][] dots) {
        int answer = 0;
        int minX = 256, minY = 256, maxX = -256, maxY = -256;
        for(int i=0; i<dots.length; i++){
            if(dots[i][0] >= maxX){
                maxX = dots[i][0];
            }
            if(dots[i][0] <= minX){
                minX = dots[i][0];
            }
            if(dots[i][1] >= maxY){
                maxY = dots[i][1];
            }
            if(dots[i][1] <= minY){
                minY = dots[i][1];
            }
        }
        int width = maxX - minX;
        int height = maxY - minY;
        answer = width * height;
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 0_87 캐릭터의 좌표 ㅋㅋㅋㅋ 오랜만에 재밌었다
import java.util.*;

class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int[] answer = new int[2];
        int[][] map = new int[board[1]][board[0]];
        
        int[] now = {map[0].length/2, map.length/2};
        map[now[1]][now[0]] = 1;
        System.out.println(Arrays.toString(now));
        
        for(String direc: keyinput){
            int[] tmp = {now[0], now[1]};
            if(direc.equals("up")){
                tmp[1] += 1; 
            }else if(direc.equals("right")){
                tmp[0] += 1; 
            }else if(direc.equals("down")){
                tmp[1] -= 1; 
            }else if(direc.equals("left")){
                tmp[0] -= 1; 
            }
            System.out.println("tmp: " + Arrays.toString(tmp));
            //유효성 검사하고
            if(0>tmp[0] || tmp[0]>=map[0].length){
                tmp = now;
                System.out.println("x좌표 기각" + Arrays.toString(tmp));
                continue;
            }
            if(0>tmp[1] || tmp[1]>=map.length){
                tmp = now;
                System.out.println("y좌표 기각" + Arrays.toString(tmp));
                continue;
            }
            System.out.println("반영 후: " + Arrays.toString(tmp));
            //유효 now에 반영, map에 반영
            map[tmp[1]][tmp[0]] = map[now[1]][now[0]] + 1; 
            now = tmp;
            checkConsole(map);
        }
        answer[0] = now[0] - (map[0].length/2);
        answer[1] = now[1] - (map.length/2);

        return answer;
    }
    
    void checkConsole(int[][] feild){
        for(int i=0; i<feild.length; i++){
            for(int j=0; j<feild[0].length; j++){
                System.out.print(feild[i][j]);
            }
             System.out.println("");
        }
        System.out.println("");
        System.out.println("");
       
    }
        
}

//------------------------------------------------------------------------------ 0_88 문자열 여러 번 뒤집기
import java.util.*;

class Solution {
    public String solution(String my_string, int[][] queries) {
        String answer = my_string;
        
        for(int[] each: queries){
            answer = remake(answer, each);
        }
        return answer;
    }
    String remake(String origin, int[] query){
        String leftStr = origin.substring(0,query[0]);
        String reverseStr = origin.substring(query[0],query[1]+1);
        String rightStr = origin.substring(query[1]+1, origin.length());
        return leftStr + reverseString(reverseStr) + rightStr;
    }
    String reverseString(String target){
        StringBuffer sb = new StringBuffer(target);
        return sb.reverse().toString();
    }

}

//------------------------------------------------------------------------------ 0_89 무작위로 K개의 수 뽑기
import java.util.*;

class Solution {
    public int[] solution(int[] arr, int k) {
        int[] answer = {};
        ArrayList<Integer> tmpAnswer = new ArrayList<>();
        
        for(int e: arr){
            if(tmpAnswer.size() == k){
                break;
            }
            if(!tmpAnswer.contains(e)){
                tmpAnswer.add(e);
            }
        }
        // 나머지 값을 전부 -1로 채우기
        if(tmpAnswer.size() != k){
            int rotateCount = k-tmpAnswer.size();
            for(int i=0; i<rotateCount; i++){
                tmpAnswer.add(-1);
            }
        }
        return tmpAnswer.stream().mapToInt(e -> e).toArray();
    }
}


//------------------------------------------------------------------------------ 0_90 로그인 성공?
import java.util.*;

class Solution {
    public String solution(String[] id_pw, String[][] db) {
        String answer = "";
        for(String[] e: db){
            if(checkId(id_pw[0], e)){
                if(checkPw(id_pw[1], e)){
                    answer = "login";
                    break;
                }
                answer = "wrong pw";
            }
        }
        if(answer.equals("")){
            answer = "fail";
        }
        return answer;
    }
    boolean checkId(String id, String[] db){   
        boolean isIdExist = Arrays.asList(db).contains(id);
        return isIdExist;
    }
    boolean checkPw(String pw, String[] db){
        boolean isPwExist = Arrays.asList(db).contains(pw);
        return isPwExist;
    }
}


