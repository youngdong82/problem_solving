//------------------------------------------------------------------------------ 2_11 의상 / 기능개발
import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(String[] cloth: clothes){
            ArrayList<String> gotList = map.getOrDefault(cloth[1], new ArrayList<String>());
            gotList.add(cloth[0]);
            map.put(cloth[1], gotList);
        }

        for(String key: map.keySet()){
            List<String> getArr = map.get(key);
            answer *= (getArr.size() + 1);
        }
        
        
        return answer-1;
    }
}
//======================== 뭔가 너무 더러워!!
import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        //거꾸로 stack에 넣은 다음
        Stack<Integer> stack = new Stack<>();
        for(int i=speeds.length-1; i>=0;i--){
            stack.push(progresses[i]);
        }
        
        int days = 0;
        ArrayList<Integer> tmp = new ArrayList<>();
        
        for(int i=0; i<speeds.length; i++){
            int needProg = 100-(stack.peek() + (speeds[i] * days)); //필요한 작업양
            if(needProg <=0){    //대기중이라면
                tmp.set(tmp.size() - 1, tmp.get(tmp.size() - 1)+1);
                stack.pop();
            }else{              //추가 작업일이 필요하다면
                int needDay = needProg / speeds[i];   //필요한 일수
                if(needProg % speeds[i] != 0){
                    needDay+=1;
                };
                days += needDay;
                stack.pop();
                tmp.add(1);
            }
        }
         
        return tmp.stream().mapToInt(e-> e).toArray();
    }
}


//progresses, speeds 돌면서 작업이 끝나기까지 남은 일수 배열 생성
import java.util.*;


class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] leftDayArr = new int[speeds.length];
        
        for(int i=0; i<speeds.length; i++){
            int leftProg = 100-progresses[i];
            int leftDays = leftProg / speeds[i];
            if(leftProg % speeds[i] != 0){
                leftDays += 1;
            }
            
            leftDayArr[i] = leftDays;
        }
        int count = 1;
        int beforeMax = leftDayArr[0];
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i=1; i<leftDayArr.length; i++){
            if(beforeMax >= leftDayArr[i]){
                count ++;
            }else{
                tmp.add(count);
                count = 1;
                beforeMax = leftDayArr[i];
            }
            if(i == leftDayArr.length-1){
                tmp.add(count);
            }
        }
        
        return tmp.stream().mapToInt(e->e).toArray();
    }
}

//------------------------------------------------------------------------------ 2_12 프로세스 / 피로도
import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        LinkedList<Integer> que= new LinkedList<>();
        for(int priority : priorities){
            que.add(priority);
        }
        while(true){
            int now = que.poll();
            int max = 0;
            for(int i=0; i<que.size(); i++){
                max = Math.max(max,que.get(i));
            }
            if(now < max){  //더 큰 우선순위가 있다면
                que.add(now);
                //location 체크
                if(location == 0){  //방금 뺀거였으면
                    location = que.size()-1;
                }else{
                    location--;
                } 
            }else{
                //location 체크
                if(location == 0){  //방금 뺀거였으면
                    answer = priorities.length - que.size();
                    break;
                }else{
                    location--;
                }  
            }
        }
        
        return answer;
    }
}



import java.util.*;

class Solution {
    int maxDepth = 0;
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        boolean[] visited = new boolean[dungeons.length];
        dfs(0, dungeons, k, visited);
        
        return maxDepth;
    }
    void dfs(int depth, int[][] maps, int k, boolean[] visited){
        if(depth == maps.length){
            maxDepth = depth;
            return;
        }
        maxDepth = Math.max(maxDepth, depth);
        for(int i=0; i<maps.length; i++){
            if(visited[i] == false){        //방문했던 곳인지?
                if(k >= maps[i][0]){       //체력이 되는지?
                    visited[i] = true;
                    dfs(depth+1, maps, k-maps[i][1], visited);
                    visited[i] = false;
                }
            }
        }
    }
}


//------------- 클래스 생성해서 만들어보기 ***
import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        
        Stack<Step> stack = new Stack<>();
        for(int i=0; i<dungeons.length; i++){
            ArrayList<Integer> hist = new ArrayList<>();
            hist.add(i);
            stack.push(new Step(1, k-dungeons[i][1], hist));
            while(!stack.isEmpty()){
                Step now = stack.pop();
                int nowD = now.depth;
                int nowK = now.currentK;
                ArrayList<Integer> nowHist = now.hist;
                answer = Math.max(answer, nowD);
                if(nowD == dungeons.length){
                    answer = nowD;
                }
                for(int j=0; j<dungeons.length; j++){
                    if(!nowHist.contains(j)){   
                        if(nowK >= dungeons[j][0]){
                            ArrayList<Integer> newHist = new ArrayList<>(nowHist);
                            newHist.add(j);
                            stack.push(new Step(nowD+1, nowK-dungeons[j][1], newHist));
                        }
                    }
                }
            }
        }
        return answer;
    }
    private class Step{
        int depth;
        int currentK;
        ArrayList<Integer> hist;
        
        public Step(int depth, int currentK, ArrayList<Integer> hist){
            this.depth = depth;
            this.currentK = currentK;
            this.hist = hist;
        }
    }
}


//------------------------------------------------------------------------------ 2_13 게임 맵 최단거리
//BFS
import java.util.*;


class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        Queue<String> que = new LinkedList<>();
        int[] dx = {0,1,0,-1};
        int[] dy = {-1,0,1,0};
        
        que.offer("0_0");    //현재 추가
        maps[0][0] = 11;
        while(que.size()>0){
            String now = que.poll();
            String[] spltNow = now.split("_");
            int nowX = Integer.parseInt(spltNow[0]);
            int nowY = Integer.parseInt(spltNow[1]);
            if(nowX == maps[0].length-1 && nowY == maps.length-1){   //목적지라면
                answer = maps[nowY][nowX] -10;
                break;
            }
            //동서남북 돌면서 
            for(int i=0; i<4; i++){
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];
                //유효성 확인 (범위, 벽, 들렀던 곳)
                if(0<=nx && nx< maps[0].length
                && 0<=ny && ny< maps.length
                && maps[ny][nx] == 1
                  ){
                    //위치를 String으로 만들어서
                    //que에 추가
                    String nextString = nx+"_"+ny;
                    que.offer(nextString);
                    maps[ny][nx] = maps[nowY][nowX]+1;
                }
            }
        }
        if(answer == 0){
            answer = -1;
        }
        return answer;
    }
    

    void showMap(int[][] maps){
        for(int[] map: maps){
            System.out.println(Arrays.toString(map));   
        }
        System.out.println("===========");
        
    }
}


//------------------------------------------------------------------------------ 2_14 타겟 넘버
class Solution {
    int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(numbers, 0,target,0);
        return answer;
    }
    public void dfs(int[] numbers, int depth, int target, int sum){
        if(depth == numbers.length){
            if(target == sum){
                answer++;
            } 
        } else {
            dfs(numbers, depth + 1, target, sum + numbers[depth]);
            dfs(numbers, depth + 1, target, sum - numbers[depth]);
        }
    }
}


//------------------------------------------------------------------------------ 2_15 전화번호 목록
import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        HashMap<String, Boolean> map = new HashMap<>();
        for(String phone_num : phone_book){
            map.put(phone_num, false);
        }
        for(String phone_num : phone_book){
            StringBuilder sb = new StringBuilder();
            char[] phone_num_arr = phone_num.toCharArray();
            for(int i=0; i<phone_num_arr.length-1; i++ ){
                sb.append(phone_num_arr[i]);
                if(!map.getOrDefault(sb.toString(), true)){
                    return false;
                }
            }
        }
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 2_16 더 맵게
import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> que = new PriorityQueue();
        for(int each: scoville){
            que.add(each);
        }
        while(true){
            if(que.peek() >= K){
                break;
            }
            if(que.size()<2){
                answer = -1;
                break;
            }
            int min = que.poll();
            int secondMin = que.poll();
            int afterMix = min + (secondMin*2);
            que.add(afterMix);
            answer++;
        }
            
        return answer;
    }
}
//------------------------------------------------------------------------------ 2_17 모음사전 (조합)
import java.util.*;

class Solution {
    private ArrayList<String> dictionary = new ArrayList<>();
    
    public int solution(String word) {
        for(int i=1; i<6; i++){     //갯수 정하기
            combination(0,i,"");
        }
        Collections.sort(dictionary);
        
        return dictionary.indexOf(word) + 1;
    }
    void combination(int depth, int target, String str){
        if(depth == target){
            dictionary.add(str);
            return;
        }
        combination(depth+1, target, str + "A");
        combination(depth+1, target, str + "E");
        combination(depth+1, target, str + "I");
        combination(depth+1, target, str + "O");
        combination(depth+1, target, str + "U");
    }
}

//------------------------------------------------------------------------------ 2_18 땅따먹기
// ------------------------------------ DFS (재귀)
class Solution {
  int maxx = 0;
  int solution(int[][] land){
    int answer = 0;
    for(int i=0; i<4; i++){
      dfs(0,land,land[0][i],i);
    }
    return maxx;
  }
  void dfs(int depth, int[][] land, int summ, int befIdx){
    if(depth == land.length-1){
      maxx = Math.max(maxx, summ);
      return;
    }
    for(int i=0; i<4; i++){
      if(i != befIdx){
        dfs(depth+1, land, summ+land[depth][i], i);
      }
    }
  }
}

// ------------------------------------ DFS (stack)
import java.util.*;

class Solution {
    
    int solution(int[][] land) {
        int answer = 0;
        int depth = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<4; i++){
            stack.push(i);
            stack.push(depth);
            stack.push(land[depth][i]); 
        }
        
        while(stack.size() >= 2){
            int nowSum = stack.pop();
            int nowDepth = stack.pop();
            int befIdx = stack.pop();
            for(int i=0; i<4; i++){
                if(nowDepth == land.length-1){
                    answer = Math.max(answer,nowSum);
                    break;
                }
                if(i != befIdx){
                    stack.push(i);
                    stack.push(nowDepth+1);
                    stack.push(nowSum + land[nowDepth][i]);
                }
            }
        }
        return answer;
    }
}

// ------------------------------------ 분할정복
class Solution {
    int solution(int[][] land) {
        int answer = 0;
        for(int i=1; i<land.length; i++){   //깊이
            for(int j=0; j<4;j++){      //i번째
                int tmpMax = 0;
                for(int k=0; k<4;k++){  //i-1번째
                    if(j!=k){
                        tmpMax = Math.max(tmpMax, land[i][j] + land[i-1][k]);
                    }
                }
                land[i][j] = tmpMax;
            }
        }
        
        for(int i=0; i<4; i++){
            answer = Math.max(answer, land[land.length-1][i]);
        }
        return answer;
    }
}



//------------------------------------------------------------------------------ 2_19 방문 길이
import java.util.*;

class Solution {
    public int solution(String dirs) {
        HashMap<Character, Integer> dirMap = new HashMap<>();
        dirMap.put('L',0);
        dirMap.put('D',1);
        dirMap.put('R',2);
        dirMap.put('U',3);
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};
        int[][] map = new int[11][11];
        int[] now = {5,5};
        Set<String> hist = new HashSet<>();
        for(char dir: dirs.toCharArray()){
            int dirInt = dirMap.get(dir);
            int nx = now[0] + dx[dirInt];
            int ny = now[1] + dy[dirInt];
            if(0<=nx && nx <11 && 0<=ny && ny <11){
                String action = now[0] + "_" + now[1]+"-"+nx+ "_" +ny;
                String action2 = nx+"_"+ny+"-"+ now[0] +"_"+ now[1];
                hist.add(action);
                hist.add(action2);
                now[0] = nx;
                now[1] = ny;
            }
        }
        
        return hist.size()/2;
    }
}

//------------------------------------------------------------------------------ 2_20 주식가격
import java.util.*;


class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        //prices 돌면서
        for(int i=0; i<prices.length; i++){
            int count = 1;
            for(int j=i+1; j<prices.length; j++){
                if(prices[i] <= prices[j]){     //오르거나 같으면
                    count ++;
                }else{                          //떨어졌으면
                    answer[i] = count;
                    break;
                }
                if(j == prices.length-1){
                    answer[i] = count-1;
                }
            }
        }   
        return answer;
    }
}
