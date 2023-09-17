//------------------------------------------------------------------------------ 2_21 스킬트리
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(String skill_tree: skill_trees){       //최대 20개
            char[] skillCharArr = skill_tree.toCharArray();
            boolean pass = true;
            int idxCheck = 0;
            for(char each: skillCharArr){
                int idx = skill.indexOf(each);
                if(idx != -1){
                    if(idx == idxCheck){
                        idxCheck++;
                    }else{
                        pass = false;
                        break;
                    }
                }
            }
            if(pass){
                answer++;
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 2_22 2개 이하로 다른 비트 (규칙 찾아내기)
class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for(int i=0; i<numbers.length; i++){
            String biNum = Long.toString(numbers[i],2);
            
            StringBuilder sb = new StringBuilder(biNum);
            if(biNum.charAt(biNum.length()-1) == '0' ){ // 끝이 0이면
                sb.setCharAt(biNum.length()-1, '1');    // 1더하고 끝
            }else{      // 끝이 0이 아니면 오른쪽부터 찾아서 i, i+1끼리 바꾸기
                int lastZeroIdx = biNum.lastIndexOf('0');
                if(lastZeroIdx == -1){
                    sb.setCharAt(0,'0');
                    sb.insert(0,"1");
                }else{
                    sb.setCharAt(lastZeroIdx,'1');
                    sb.setCharAt(lastZeroIdx+1,'0');
                }
            }
            answer[i] = Long.parseLong(sb.toString(),2);
        }   
        return answer;
    }
}

//------------------------------------------------------------------------------ 2_23 다리를 지나는 트럭 (큐)
import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        LinkedList<Integer> que = new LinkedList<>();
        for(int i=0; i<bridge_length; i++){
            que.add(0);
        }
        
        int second = 0;
        int summ = 0;
        int waitIdx = 0;

        while(waitIdx < truck_weights.length){
            //계속 밀어내야하니까 무게때문에 못갈때는 0을 추가하자

            int pass = que.remove();        
            summ -= pass;                   //pop, summ에서 빼기

            if(waitIdx < truck_weights.length){
                //i번째 트럭이 추가되었을 때 weight을 넘지 않는지 확인
                if(summ + truck_weights[waitIdx] > weight){ //넘는다면
                    que.add(0);                             //0추가
                }else{                                      //안넘는다면
                    que.add(truck_weights[waitIdx]);        //que에 트럭무게 추가
                    summ += truck_weights[waitIdx];         //summ에 추가
                    waitIdx++;
                }
            }
            second++;
        }
        return bridge_length + second;
    }
}
//------------------------------------------------------------------------------ 2_24 가장 큰 수 (sort 2번째 인자)
//---------------------------- sort에 두번째 인자를 이용한 손쉬운 커스텀 정렬
import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();
        //String으로 바꾸고 정렬하면...?
        LinkedList<String> que = new LinkedList<>();
        for(int num: numbers){
            String strNum = Integer.toString(num);
            que.add(strNum);
        }
        
        Collections.sort(que,(s1,s2) -> (s2+s1).compareTo((s1+s2)));
        
        if (que.get(0).equals("0")) {
            return "0";
         }
        for(String each: que){
            answer.append(each);
        }
        
        return answer.toString();
    }
}

//---------------------------- Comparator implements 한 클래스 생성

import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();
        //String으로 바꾸고 정렬하면...?
        LinkedList<String> que = new LinkedList<>();
        for(int num: numbers){
            String strNum = Integer.toString(num);
            que.add(strNum);
        }
        
        Collections.sort(que,new CostomSort());
        if (que.get(0).equals("0")) {
            return "0";
         }
        for(String each: que){
            answer.append(each);
        }
        
        return answer.toString();
    }
    
    class CostomSort implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            String converted1 = str1+str2;
            String converted2 = str2+str1;
            return converted2.compareTo(converted1);
            //=> 원래는 converted1.compareTo(converted2);
            //이래야하지만, 서로 바꿈으로써 reversed()하는 효과
            
        }
    }
}

//------------------------------------------------------------------------------ 2_25 쿼드압축 후 개수 세기 (재귀)
class Solution {
    int[] answer = new int[2];
    public int[] solution(int[][] arr) {
        goDeeper(arr, arr.length, 0,0);
         
        return answer;
    }
    void goDeeper(int[][] map, int gap, int startX, int startY){
        int standard = map[startY][startX];
        boolean result = true;
        for(int i=startY; i<startY+gap; i++){
            boolean temp = true;
            for(int j=startX; j<startX+gap; j++){
                if(map[i][j] != standard){
                    temp = false;
                    break;
                }
            }
            if(temp == false){
                result = false;
                break;
            }
        }
        if(result){
            if(standard == 1){
                answer[1] = answer[1]+1;
            }else{
                answer[0] = answer[0]+1;
            }
        }else{
            int newGap = gap/2;
            if(newGap == 0){
                return;
            }
            goDeeper(map,newGap, startX, startY);
            goDeeper(map,newGap, startX+newGap, startY);
            goDeeper(map,newGap, startX, startY+newGap);
            goDeeper(map,newGap, startX+newGap, startY+newGap);
        }
        return;
    }
}

//------------------------------------------------------------------------------ 2_26 소수 찾기 (조합)
import java.util.*;


class Solution {
    Set<Integer> list = new HashSet<>();
    public int solution(String numbers) {
        int answer = 0;
        char[] charArr = numbers.toCharArray();
        boolean[] visited = new boolean[charArr.length];

        dfs(charArr, visited, new StringBuilder());
        for(int each: list){
            if(isPrime(each)){
                answer++;
            }
        }
        
        return answer;
    }
    void dfs(char[] charArr, boolean[] visited, StringBuilder sb){
        if(sb.length() <= charArr.length && sb.length() > 0){
            list.add(Integer.parseInt(sb.toString()));
        }
        for(int i=0; i<charArr.length; i++){
            if(visited[i] == false){
                visited[i] = true;
                sb.append(charArr[i]);
                dfs(charArr, visited, sb);
                visited[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    boolean isPrime(int target){
        if(target <2){
            return false;
        }
        for(int i=2; i<(int)Math.sqrt(target)+1; i++){
             if(target%i == 0){
                 return false;
             }
        }
        return true;
    }
}
//------------------------------------------------------------------------------ 2_27 삼각 달팽이 (구현)
import java.util.*;

class Solution {
    public int[] solution(int n) {
        int[] answer = new int[(n*(n+1)/2)];
        int[][] map = new int[n][];
        for(int i=0; i<map.length; i++){
            map[i] = new int[i+1];
        }
        int[] dx = {0,1,-1};
        int[] dy = {1,0,-1};
        int[] now = {0,-1};
        int count = 0;
        int dir = 0;

        for(int i=map.length; i>0; i--){
            for(int j=0; j<i; j++){
                int nx = now[0] + dx[dir];
                int ny = now[1] + dy[dir];
                count++;
                map[ny][nx] = count;
                now[0] = nx;
                now[1] = ny;
            }
            dir = (dir+1)%3;
        }

        int idx = 0;
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[i].length; j++){
                answer[idx] = map[i][j];
                idx++;
            }
        }
        
        return answer;
    }
}
//------------------------------------------------------------------------------ 2_28 큰 수 만들기 (스택)
// i가 i+1보다 작으면 삭제
// dpIdx를 이용해서 문제없이 넘어갔던 부분의 계산 절약
// k가 엄청 클 경우 k갯수만큼 돌면서 for문을 만들어야 해
// => while문을 없애기...?
class Solution {
    public String solution(String number, int k) {
        int count = 0;
        int dpIdx = 0;
        StringBuilder sb = new StringBuilder(number);
        while(count >= 0 && count < k){
            for(int i=dpIdx; i<sb.length()-1; i++){
                if(sb.charAt(i) < sb.charAt(i+1)){
                    sb.deleteCharAt(i);
                    if(i == 0){
                        dpIdx = i;
                    }else{
                        dpIdx = i-1;
                    }
                    count++;
                    break;
                }
            }  
        }

        return sb.toString();
    }
}

// 아니 이걸 왜 stack 문제인줄 몰랐을까...?

import java.util.Stack;
class Solution {
    public String solution(String number, int k) {
        char[] result = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }
            stack.push(c);
        }
        for (int i=0; i<result.length; i++) {
            result[i] = stack.get(i);
        }
        return new String(result);
    }
}


//------------------------------------------------------------------------------ 2_29 124 나라의 숫자
// 아 이건 규칙 알아내기 같은데 저번에도 그렇고 진짜 모르겠네ㅋㅋㅋㅋ

class Solution {
  public String solution(int n) {
      String[] num = {"4","1","2"};
      String answer = "";

      while(n > 0){
          answer = num[n % 3] + answer;
          n = (n - 1) / 3;
      }
      return answer;
  }
}
//------------------------------------------------------------------------------ 2_30 행렬 테두리 회전하기 (구현)
class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] map = new int[rows][columns];
        int idx = 0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                idx++;
                map[i][j] = idx;
            }
        }
        for(int i=0; i<queries.length; i++){
            int minn = rotate(queries[i], map);
            answer[i] = minn;
        }
        
        return answer;
    }
    int rotate(int[] query, int[][] map){
        int min = Integer.MAX_VALUE;
        int y1 = query[0]-1;
        int x1 = query[1]-1;
        int y2 = query[2]-1;
        int x2 = query[3]-1;
        
        //위에서 오른쪽
        int temp = 0;
        int temp4 = map[y1+1][x1];
        for(int i=x2; i> x1; i--){
            min = Math.min(min, map[y1][i]);
            if(i == x2){
                temp = map[y1][i];
            }
            map[y1][i] = map[y1][i-1];
        }
        //오른쪽에서 밑으로
        int temp2 = 0;
        for(int i=y2; i> y1; i--){
            min = Math.min(min, map[i][x2]);
            if(i == y2){
                temp2 = map[i][x2];
            }
            if(i == y1+1){
                map[i][x2] = temp;
            }else{
                map[i][x2] = map[i-1][x2];
            }
        }
        //밑에서 왼쪽으로
        int temp3 = 0;
        for(int i=x1; i< x2; i++){
            min = Math.min(min, map[y2][i]);
            if(i == x1){
                temp3 = map[y2][i];
            }
            if(i == x2-1){
                map[y2][i] = temp2;
            }else{
                map[y2][i] = map[y2][i+1];
            }
        }
        //왼쪽에서 위로
        for(int i=y1; i<y2; i++){
            min = Math.min(min, map[i][x1]);
            if(i == y1){
                map[i][x1] = temp4;
            }else if(i == y2-1){
                map[i][x1] = temp3;
            }else{
                map[i][x1] = map[i+1][x1];
            }
        }
        
        return min;
    }
}

