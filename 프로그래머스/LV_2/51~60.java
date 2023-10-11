//------------------------------------------------------------------------------ 2_51 점 찍기
import java.util.*;


class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        boolean[][] maps = new boolean[d+1][d+1];
        int[] dx = {1,0};
        int[] dy = {0,1};
        LinkedList<int[]> que = new LinkedList<>();
        que.add(new int[]{0,0});
        maps[0][0] = true;
        while(!que.isEmpty()){
            int[] now = que.poll();
            answer ++;
            for(int i=0; i<2; i++){
                int nx = now[0] + (dx[i]*k);
                int ny = now[1] + (dy[i]*k);
                if((nx*nx)+(ny*ny) <= (d*d)){     //범위를 벗어날 리 없음
                    if(maps[ny][nx] == false){
                        que.add(new int[]{nx,ny});
                        maps[ny][nx] = true;
                    }
                }
            }
        }
        return answer;
    }
}

//메모리 초과 뜸
//maps를 없애고
//2. String 형태로 HashMap에 저장
import java.util.*;


class Solution {
    public long solution(int k, int d) {
        int[] dx = {1,0};
        int[] dy = {0,1};
        HashMap<String, Integer> visited = new HashMap<>();
        LinkedList<long[]> que = new LinkedList<>();
        que.add(new long[]{0,0});
        visited.put("0_0", 1);
        while(!que.isEmpty()){
            long[] now = que.poll();
            for(int i=0; i<2; i++){
                long nx = now[0] + (dx[i]*k);
                long ny = now[1] + (dy[i]*k);
                if(((long)nx*(long)nx)+((long)ny*(long)ny) <= ((long)d*(long)d)){     //범위를 벗어날 리 없음
                    if(visited.getOrDefault(nx+"_"+ny, 0) == 0){
                        que.add(new long[]{nx,ny});
                        visited.put(nx+"_"+ny, 1);  
                    }
                }
            }
        }
        // for(String key: visited.keySet()){
        //     System.out.println(key);
        // }
        return visited.size();
    }
}

// ==> 시간초과 뜸
//하나하나 돌지 말고
//끝점 구한 후 k로 나눈 몫 추가하기

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long dSqaure = (long)d*(long)d;
        for(int i=0; i<d+1; i+=k){
            long tmp = (long)Math.sqrt(dSqaure - ((long)i*(long)i));
            answer += (tmp/k) + 1;
        }
        return answer;
    }
}


//------------------------------------------------------------------------------ 2_52 미로 탈출
// bfs로 먼저 풀어보자
import java.util.*;


class Solution {
    public int solution(String[] maps) {
        int answer = 0;
        int[] start = new int[2];
        int[] toggle = new int[2];
        int[] exit = new int[2];
        
        for(int i=0; i<maps.length; i++){
            if(maps[i].indexOf("S") >= 0){
                start[0] = maps[i].indexOf("S");
                start[1] = i;
            }
            if(maps[i].indexOf("L") >= 0){
                toggle[0] = maps[i].indexOf("L");
                toggle[1] = i;
            }
            if(maps[i].indexOf("E") >= 0){
                exit[0] = maps[i].indexOf("E");
                exit[1] = i;
            }
        }
        int startToggleDist = bfs(maps, start, toggle);
        if(startToggleDist == -1){
            return -1;
        }else{
            answer += startToggleDist;
        }
        int toggleExitDist = bfs(maps, toggle, exit);
        if(toggleExitDist == -1){
            return -1;
        }else{
            answer += toggleExitDist;
        }
        return answer;
    }
    int bfs(String[] maps, int[] start,  int[] target){
        Node answerNode = new Node(target[0],target[1],0);
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        
        LinkedList<Node> que = new LinkedList<>();
        que.add(new Node(start[0], start[1], 0));
        visited[start[1]][start[0]] = true;
        
        while(!que.isEmpty()){
            Node now = que.poll();
            //target인지 확인
            if(now.getX() == target[0] && now.getY() == target[1]){
                answerNode = now;
                break;
            }
            for(int i=0; i<4; i++){
                int nx = now.getX() + dx[i];
                int ny = now.getY() + dy[i];
                if(0<= nx && nx < maps[0].length() &&
                   0<= ny && ny < maps.length ){  //범위 내라면
                    if(visited[ny][nx] == false){   //안 간 곳이라면
                        char nextChar = maps[ny].charAt(nx);
                        if(nextChar != 'X'){   //벽이 아니라면
                            que.add(new Node(nx, ny, now.getDist()+1));
                            visited[ny][nx] = true;
                        }
                    }
                }
            }
        }
        if(answerNode.getDist() == 0){
            return -1;
        }else{
            return answerNode.getDist();
        }
    }
    class Node{
        public int x;
        public int y;
        public int dist;
        Node(){}
        Node(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
        public int getDist(){
            return dist;
        }
    }
}
//------------------------------------------------------------------------------ 2_53 테이블 해시 함수
import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (a,b) -> {
            int compareResult = Integer.compare(a[col-1], b[col-1]);
            if(compareResult != 0){
                return compareResult;
            }else{
                return -1 * Integer.compare(a[0], b[0]);
            }
        });
        
        int answer = -1;
        for(int i=row_begin; i<row_end+1; i++){
            int S_i = 0;
            for(int j=0; j<data[i-1].length; j++){
                S_i += (data[i-1][j]%i);
            }
            if(answer == -1){
                answer = S_i;
            }else{
                answer = answer ^ S_i;
            }
        }

        return answer;
    }
}
//------------------------------------------------------------------------------ 2_54 과제 진행하기
import java.util.*;


class Solution {
    public String[] solution(String[][] plans) {
        ArrayList<String> tmpAns = new ArrayList<>();
        Stack<String[]> delayStack = new Stack<>();
        
        for(int i=0; i<plans.length; i++){
            String start = plans[i][1];
            String[] splitedStart = start.split(":");
            int minutes = (Integer.parseInt(splitedStart[0]) * 60) + Integer.parseInt(splitedStart[1]);
            plans[i][1] = Integer.toString(minutes);
        }
        Arrays.sort(plans, (a,b) -> Integer.compare(Integer.parseInt(a[1]), Integer.parseInt(b[1])));

        for(int i=0; i<plans.length; i++){
            String[] now = plans[i];
            int nowStart = Integer.parseInt(now[1]);
            int nowLast = Integer.parseInt(now[2]);
            if(i == plans.length-1){        //마지막이라면 그냥 추가
                tmpAns.add(now[0]);
            }else{
                int nextStart = Integer.parseInt(plans[i+1][1]);

                if(nowStart + nowLast <= nextStart){ //next 시작전에 끝난다면 
                    int leftTime = nextStart - (nowStart + nowLast);
                    tmpAns.add(now[0]);
                    //시간이 남았다면
                    while(!delayStack.isEmpty() && leftTime > 0){      //delayStack이 비어있지 않다면      //!!!!이거 반복해야함
                        String[] latestDelayTask = delayStack.pop();
                        int lastTime = Integer.parseInt(latestDelayTask[1]);
                        if(lastTime <= leftTime){   //남은 시간 안에 끝난다면
                            tmpAns.add(latestDelayTask[0]);
                        }else{                                      //남은 시간 안에 안끝난다면
                            String[] delayTask = new String[2];
                            delayTask[0] = latestDelayTask[0];
                            delayTask[1] = Integer.toString(lastTime - leftTime);
                            delayStack.push(delayTask);
                        }
                        leftTime -= lastTime;
                    }
                }else{                  //next 시작전에 안끝난다면 
                    //delay에 들어가
                    String[] delayTask = new String[2];
                    delayTask[0] = now[0];
                    delayTask[1] = Integer.toString(nowStart + nowLast - nextStart);
                    delayStack.push(delayTask);
                }
            }
        }
        // 남아있는 delayStack 처리
        while(!delayStack.isEmpty()){
            String[] left = delayStack.pop();
            tmpAns.add(left[0]);
        }
        
        return tmpAns.toArray(new String[0]);
    }
}
//------------------------------------------------------------------------------ 2_55 리코쳇 로봇
//장애물이나 맨 끝에 부딪힐 때까지 미끄러져 이동하는 것을 한 번의 이동
//왔던 곳을 같은 방향으로 오면 끝

import java.util.*;

class Solution {
    int[] goal = new int[2];
    public int solution(String[] board) {
        int answer = 0;
        int[] start = new int[3];
        for(int i=0; i<board.length; i++){
            if(board[i].indexOf('R') >= 0){
                start[0] = board[i].indexOf('R');
                start[1] = i;
            }
            if(board[i].indexOf('G') >= 0){
                goal[0] = board[i].indexOf('G');
                goal[1] = i;
            }
        }
        start[2] = 0;
        //x_y_방향
        HashMap<String,Integer> visited = new HashMap<>();
        answer = bfs(board, start, visited);
        
        return answer;
    }
    int bfs(String[] board, int[] start, HashMap<String,Integer> visited){
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        LinkedList<int[]> que = new LinkedList<>();
        que.add(start);
        while(!que.isEmpty()){
            int[] now = que.poll();
            // System.out.println(Arrays.toString(now));
            //목적지에 도착했다면
            if(now[0] == goal[0] &&
               now[1] == goal[1]
              ){    
                return now[2];
            }
            // System.out.println("not goal");
            for(int i=0; i<4; i++){
                int jumpCnt = 0;
                int nx = now[0];
                int ny = now[1];
                while(true){        //벽이거나 장애물에 부딫힐 경우
                    jumpCnt++;
                    int tmpX = now[0] + (dx[i]*jumpCnt);
                    int tmpY = now[1] + (dy[i]*jumpCnt);
                    //범위 안에 없으면
                    if(!(0 <= tmpX &&
                         tmpX < board[0].length() &&
                         0 <= tmpY &&
                         tmpY < board.length)
                      ){
                        break;
                    }
                    //벽이라면
                    int tmpChar = board[tmpY].charAt(tmpX);
                    if(tmpChar == 'D'){
                        break;
                    }
                    nx = tmpX;
                    ny = tmpY;
                }
                if(nx != now[0] || ny != now[1]){   //하나라도 다르다면 = 이동했다면
                    String hist = nx + "_" + ny+"_" + i;
                    if(visited.getOrDefault(hist, 0) == 0){ //가본적이 없다면
                        // System.out.println("added nx: ny - "+nx +":"+ ny);
                        que.add(new int[]{nx, ny, now[2]+1});
                        visited.put(hist,1);
                    }
                }
            }
        }
        return -1;
    }
}



//------------------------------------------------------------------------------ 2_56 광물 캐기
//5개 당 1개 셋
//각 셋에서 최선의 선택을 할 경우
//마지막에 5개 다이아인데 돌곡이 남아있을 수 있음
import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int canDeal = 0;
        for(int i=0; i<3; i++){
            canDeal += (picks[i]*5);
        }
        canDeal = Math.min(canDeal, minerals.length);
        ArrayList<TargetSet> list = new ArrayList<>(); 
        int onlyStone = 0;
        int cnt = 0;
        //전부 돌곡으로 썼을 경우 피로도를 구해서 ArrayList에 넣고
        for(int i=0; i<canDeal; i++){ //5개씩 세트로 해서
            if(minerals[i].equals("diamond")){
                onlyStone += 25;
            }else if(minerals[i].equals("iron")){
                onlyStone += 5;
            }else{
                onlyStone += 1;
            }
            cnt++;
            if((i+1)%5 == 0 || i == minerals.length-1){
                list.add(new TargetSet(onlyStone, cnt));
                onlyStone = 0;
                cnt = 0;
            }
        }

        Collections.sort(list,(a,b)->Integer.compare(b.getOnlyStone(),a.getOnlyStone()));
        int listIdx=0;
        for(int i=0; i<3; i++){
            for(int j=0; j<picks[i]; j++){
                if(listIdx == list.size()){     //광물을 다 썼으면
                    break;
                }
                int convertedResult = convert(i, list.get(listIdx));
                answer += convertedResult;
                listIdx++;
            }
        }
        return answer;
    }
    int convert(int type, TargetSet target){ 
        if(type == 0){  //다이아
            return target.getCnt();
        }else if(type == 1){    //철
            //꼭 광물이 5개가 아닌 경우도 있다 이를 고려해야함
            int diaCnt = target.getOnlyStone()/25;
            int nextDia = target.getOnlyStone()%25;
            if(diaCnt == 1 && nextDia == 0){    //철 5개일 경우 || cnt 1인데 다이아일 경우
                return 5; 
            }
            int ironCnt = nextDia/5;
            int stoneCnt = nextDia%5;
            if(diaCnt == 0 && ironCnt == 1 && stoneCnt == 0){ //돌5개일 경우 || cnt 1인데 다이아일 경우
                if(target.getCnt() == 1){
                    return 1;
                }
                return 5;
            }
            return (diaCnt*5) + ironCnt + stoneCnt;
        }else{          //돌
            return target.getOnlyStone();
        }
    }
    class TargetSet{
        int onlyStone;
        int cnt;
        TargetSet(int onlyStone, int cnt){
            this.onlyStone = onlyStone;
            this.cnt = cnt;
        }
        int getOnlyStone(){
            return onlyStone;
        }
        int getCnt(){
            return cnt;
        }
    }
}
//------------------------------------------------------------------------------ 2_57 디펜스 게임 =====> 질문
//1-1. 현재 병사로 최대 버틸 수 있는 라운드(x)까지 미리 계산
//1-2. 현재부터 x까지 정렬 후 가장 큰거에 무적권 사용
//1-3. k가 없어질 때까지 반복

import java.util.*;


class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        if(k >= enemy.length){
            return enemy.length;
        }
        while(k>=0){
            int[] tempResult = getLastRoundAndBiggestRound(n, enemy);
            if(k== 0){  //무적권을 다 썼다면;
                answer = tempResult[0];   //idx니까 +1 && 실패한 지점이니까 -1
                break;
            }
            enemy[tempResult[1]] = 0;   //BiggestRound 0으로 변경
            k--;
        }
        return answer;
    }
    int[] getLastRoundAndBiggestRound(int n, int[] enemy){
        int enemySum = 0;
        int lastRound = -1;
        int maxIdx = -1;
        int maxValue = -1;
        for(int i=0; i<enemy.length; i++){
            if(maxValue <= enemy[i]){    //최대값 찾기
                maxValue = enemy[i];
                maxIdx = i;
            }
            enemySum += enemy[i];
            if(enemySum > n){           //더이상 라운드를 진행하지 못하면
                lastRound = i;
                break;
            }
        }
        if(lastRound == -1){
            return new int[]{enemy.length, maxIdx};
        }else{
            return new int[]{lastRound, maxIdx};
        }
    }
}

//시간초과 뜸
//PriorityQueue로 getLastRoundAndBiggestRound에서 
항상 enemy.length만큼 도는 걸 없애자;

import java.util.*;


class Solution {
    PriorityQueue<Integer> pQue = new PriorityQueue<>();
    int leftN = 0;
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<enemy.length; i++){
            list.add(enemy[i]);
        }
        leftN = n;
        if(k >= enemy.length){
            return enemy.length;
        }
        int start = 0;
        while(k>=0){
            int lastRound = getLastRoundAndBiggestRound(start, list);
            if(k== 0 || lastRound == enemy.length){  //무적권을 다 썼거나 전부 돌았는데 남았다면;
                answer = lastRound;   //idx니까 +1 && 실패한 지점이니까 -1
                break;
            }
            start = lastRound+1;
            int maxIdx = list.indexOf(-1 * pQue.poll());
            list.set(maxIdx, 0);    //BiggestRound 0으로 변경
            k--;
        }
        return answer;
    }
    int getLastRoundAndBiggestRound(int start, ArrayList<Integer> enemy){
        int enemySum = 0;
        int lastRound = -1;
        for(int i=start; i<enemy.size(); i++){
            pQue.add(-1 * enemy.get(i));
            enemySum += enemy.get(i);
            if(enemySum > leftN){           //더이상 라운드를 진행하지 못하면
                lastRound = i;
                break;
            }
        }   
        if(lastRound == -1){                    //끝까지 돌았는데 남는다면
            leftN -= enemySum;
            return enemy.size();
        }else{
            leftN -= (enemySum - (-1*pQue.peek()));  //최대 값 빼고 뺌
            return lastRound;
        }
    }
}
// 여전히 시간초과 뜸ㅠ
// 정답.... 아 이 무슨....;;;
// 앞으로 시간 제한을 둬야겠다;;
import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        for (int e : enemy) {
            queue.add(e);
            answer++;
            n -= e;
            if (n < 0) {
                if(k==0){
                    return answer-1;
                }
                n = n + queue.poll();
                k--;
            }
        }

        return enemy.length;
    }
}

//------------------------------------------------------------------------------ 2_58 혼자 놀기의 달인
//이 쓸데없이 복잡한 문제로 보아 구현문제가 아닐까...
import java.util.*;

class Solution {
    public int solution(int[] cards) {
        ArrayList<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[cards.length];
        int wholeCnt = cards.length;
        int cntInit = openBoxes(cards,0, visited);
        list.add(cntInit);
        wholeCnt -= cntInit;
        // System.out.println(Arrays.toString(visited));
        while(wholeCnt > 0){
            int secondIdx = 0;
            for(int i=0; i<cards.length; i++){
                if(visited[i] == false){
                    secondIdx = i;
                    break;
                }
            }
            if(secondIdx == 0){ //전부 다 돌았다는 뜻
                break;
            }
            int cnt = openBoxes(cards, secondIdx, visited);
            list.add(cnt);
            wholeCnt -= cnt;
        }
        if(list.size() == 1){
            return 0;
        }
        Collections.sort(list,(a,b) -> b.compareTo(a));
        return list.get(0) * list.get(1);
    }
    
    
    int openBoxes(int[] cards, int startIdx, boolean[] visited){
        int cnt = 0;
        while(true){
            int nextIdx = cards[startIdx]-1;
            if(visited[nextIdx] == false){  //처음이라면
                visited[nextIdx] = true;
                startIdx = nextIdx;
                cnt++;
            }else{
                break;
            }
        }
        return cnt;
    }
}
//------------------------------------------------------------------------------ 2_59 숫자 블록 => 문제가 이상함
//1000000000개에서 바로 메모리 초과
// => 소수라면 1
//    소수가 아니라면 자신의 최소약수로 나눈 값
import java.util.*;


class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = {};
        int len = (int) (end-begin+1);
        int[] tempp = new int[len];
        for(int i=0; i<len; i++){
            int idx = (int)begin+i;
            long result = checkPrime(idx);
            tempp[i] = (int) result;
        }
        return tempp;
    }
    long checkPrime(long target){
        if(target == 0 || target == 1){
            return 0;
        }
        for(int i=2; i<(int)Math.sqrt(target) +1; i++){
            if(target%i == 0){
                return target/i;
            }
        }
        //소수라면
        return 1;
    }
}

//------------------------------------------------------------------------------ 2_60 두 원 사이의 정수 쌍
//한쪽 사분면만 구하고 *4해도 될텐데

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        long r1Sqaure = (long) r1 * (long)r1;
        long r2Sqaure = (long) r2 * (long)r2;
        for(int i=0; i< r2; i++){
            long tmp = (long)Math.sqrt(r2Sqaure - (i*i));
            for(int j=1; j<tmp+1; j++){
                // System.out.println(i +":"+ j);
                if( (i*i)+(j*j) >= r1Sqaure ){
                    answer++;
                } 
            }
            // System.out.println("=====");
        }
        return (long) answer*4;
    }
}

// 시간초과
// 2번째 반복문에서 하나하나 추가하지 말고 
// r1의 바깥점 중 가장 가까운 정수 구해서 한꺼번에 더하기

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        long r1Sqaure = (long) r1 * (long)r1;
        long r2Sqaure = (long) r2 * (long)r2;
        for(int i=0; i< r2; i++){
            long iSquare = (long)i*(long)i;
            long tmp = (long)Math.sqrt(r2Sqaure - iSquare);
            long r1TmpTmp = r1Sqaure - iSquare;
            long r1Tmp = (long)Math.sqrt(r1TmpTmp);
            if((r1Tmp * r1Tmp) != r1TmpTmp){    //딱 나뉘어떨어지지 않는다면
                r1Tmp += 1; 
            }
            if(r1Tmp == 0){
                r1Tmp += 1; 
            }
            answer += (tmp - r1Tmp+1);
        }
        return (long) (answer*4);
    }
}