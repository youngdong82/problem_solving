//------------------------------------------------------------------------------ 2_31 가장 큰 정사각형 찾기 (dp)
// -------------------------- 시간초과 뜸
class Solution{
    public int solution(int [][]board){
        int minLen = Math.min(board.length, board[0].length);
        int size = 0;
        for(int i=0; i<board[0].length; i++){       //x
            for(int j=0; j<board.length; j++){      //y
                while(size <= minLen){
                    boolean result = check(i,j,board,size);
                    if(!result){
                        break;  //다른 i,j로 이동
                    }else{
                        size++;
                        // System.out.println("size up: " + size);
                    }
                }
                if(size > board[0].length-i || size > board.length-j){
                    break;
                }
            }
        }

        return (size-1) * (size-1);
    }
    boolean check(int nowX, int nowY, int[][] map, int size){
        // System.out.println("in check");
        // System.out.println("nowX: " +nowX+" nowY: "+nowY+" size: "+size );
        for(int i=0; i<size; i++){      //x
            for(int j=0; j<size; j++){  //y
                int nx = nowX+i;
                int ny = nowY+j;
                //여기서 유효성 검사
                if(nx >= map[0].length || ny >= map.length){
                    return false;
                }
                if(map[ny][nx] != 1){
                    return false;
                }
            }
        }
        return true;
    }
}

// 작은거부터 확인하지 말고 큰것부터 확인하면 여러번 이동하는게 줄지 않을까?
// 똑같이 시간초과

class Solution{
    public int solution(int [][]board){
        int minLen = Math.min(board.length, board[0].length);
        int size = minLen;
        while(size > 0){        //다 돌았는데 안돼면 size-- 하나라도 되면 리턴
            for(int i=0; i<board[0].length-size+1; i++){       //x
                for(int j=0; j<board.length-size+1; j++){      //y
                    boolean result = check(i,j,board,size);
                    // System.out.println(result);
                    if(result){
                        return size*size;
                    }
                }
            }
            size--;
        }

        return size*size;
    }
    boolean check(int nowX, int nowY, int[][] map, int size){
        // System.out.println("in check");
        // System.out.println("nowX: " +nowX+" nowY: "+nowY+" size: "+size );
        for(int i=0; i<size; i++){      //x
            for(int j=0; j<size; j++){  //y
                int nx = nowX+i;
                int ny = nowY+j;
                //여기서 유효성 검사
                if(nx >= map[0].length || ny >= map.length){
                    return false;
                }
                if(map[ny][nx] != 1){
                    return false;
                }
            }
        }
        return true;
    }
}

// size 이동을 이분탐색처럼 하는거지
// minLen의 중간부터 시작
// true면 중간 ~ minLen의 중간
// false면 1 ~ 중간 의 중간
// 최대 1000일때
// 500 - 250 - 125 - 63 - 31 - 16 - 8 - 4- 2 -1
// 10번의 이동으로 찾을 수 있음
import java.util.*;


class Solution{
    public int solution(int [][]board){
        int minLen = Math.min(board.length, board[0].length);
        
        int minSize = 1;
        int maxSize = minLen;
        int size = minSize + (maxSize-minSize+1)/2;
        HashMap<Integer, Integer> sizeMap = new HashMap<>();    
        //1:true -1: false 0은 아직
        
        while(minSize< maxSize){  
            // System.out.println(size);
            if(sizeMap.getOrDefault(size, 0) != 0){
                break;
            }
            boolean result = false;
            for(int i=0; i<board[0].length-size+1; i++){       //x
                for(int j=0; j<board.length-size+1; j++){      //y
                    boolean tmpResult = check(i,j,board,size);
                    // System.out.println(result);
                    if(tmpResult){
                        result = true;
                        break;
                    }
                }
                if(result){
                    break;
                }
            }
            if(result){
                minSize = size;  
                sizeMap.put(size, 1);
            }else{
                maxSize = size;
                sizeMap.put(size, -1);
            }
            size = minSize + (maxSize-minSize+1)/2;
        }
        int answer = 0;
        for(Integer key: sizeMap.keySet()){
            if(sizeMap.get(key) == 1){
                answer = Math.max(answer, key);
            }
        }

        return answer*answer;
    }
    boolean check(int nowX, int nowY, int[][] map, int size){
        // System.out.println("in check");
        // System.out.println("nowX: " +nowX+" nowY: "+nowY+" size: "+size );
        for(int i=0; i<size; i++){      //x
            for(int j=0; j<size; j++){  //y
                int nx = nowX+i;
                int ny = nowY+j;
                //여기서 유효성 검사
                if(nx >= map[0].length || ny >= map.length){
                    return false;
                }
                if(map[ny][nx] != 1){
                    return false;
                }
            }
        }
        return true;
    }
}
//------------------------ dp라고 해야하나 이걸...?

class Solution{
    public int solution(int [][]board){
        int answer = 0;
        int minLen = Math.min(board[0].length, board.length);
        if(minLen <2){
            return 1;
        }
            
        for(int i=1; i<board[0].length; i++){       //x
            for(int j=1; j<board.length; j++){      //y
                if(board[j][i] != 0){
                    int tmp = 1000;
                    tmp = Math.min(tmp, board[j-1][i]);
                    tmp = Math.min(tmp, board[j][i-1]);
                    tmp = Math.min(tmp, board[j-1][i-1]);
                    board[j][i] = tmp+1;
                }
            }
        }
        for(int i=1; i<board[0].length; i++){       //x
            for(int j=1; j<board.length; j++){      //y
                answer = Math.max(answer, board[j][i]);
            }
        }
        
        return answer*answer;
    }
}

//------------------------------------------------------------------------------ 2_32 전력망을 둘로 나누기
import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = n;
        int[][] map = new int[n][n];    //0부터 시작
        for(int[] wire: wires){
            int a = wire[0]-1;
            int b = wire[1]-1;
            map[a][b] = 1;
            map[b][a] = 1;
        }
        //하나씩 돌면서
        for(int[] wire: wires){
            int a = wire[0]-1;
            int b = wire[1]-1;
            //끊어보기
            map[a][b] = 0;
            map[b][a] = 0;
            int result = bfs(map,0); // 송전탑 갯수의 차이(절대값)를 리턴
            answer = Math.min(answer, result);
            
            //원상복귀
            map[a][b] = 1;
            map[b][a] = 1;
        }
        
        return answer;
    }
    int bfs(int[][] map, int start){
        int count = 1;
        boolean[] visited = new boolean[map.length];
        LinkedList<Integer> que = new LinkedList<>();
        que.add(start);
        while(!que.isEmpty()){
            int now = que.poll();
            visited[now] = true;
            for(int i=0; i<map.length; i++){
                if(map[now][i] == 1 && !visited[i]){    //연결되어있고 가보지 않았다면
                    que.add(i);
                    count++;
                }
            }
        }
        return Math.abs(count- (map.length-count));
    }
}
//------------------------------------------------------------------------------ 2_33 줄 서는 방법
//메모리 초과????
import java.util.*;

class Solution {
    ArrayList<String> list = new ArrayList<>();
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        boolean[] visited = new boolean[n];
        dfs(0, new StringBuilder(), visited);
        Collections.sort(list);
        String answerStr = list.get((int)k-1);
        for(int i=0; i<n ; i++){
            answer[i] = Character.getNumericValue(answerStr.charAt(i));
        }
        return answer;
    }
    void dfs(int depth, StringBuilder sb, boolean[] visited){
        if(depth == visited.length){
            list.add(sb.toString());
            return;
        }
        for(int i=0; i<visited.length; i++){
            if(visited[i] == false){
                visited[i] = true;
                sb.append(i+1);
                dfs(depth+1, sb, visited);
                sb.deleteCharAt(sb.length()-1);
                visited[i] = false;
            }
        }
    }
}

//=> visited를 없애보자 or 버튼 업으로 해보자
import java.util.*;


class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        ArrayList<String> list = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++){     //value
            StringBuilder sb = new StringBuilder();
            sb.append(i+1);
            stack.push(sb.toString());
            while(!stack.isEmpty()){
                String now = stack.pop();
                if(now.length() == n){
                    list.add(now);
                }else{
                    for(int j=0; j<n; j++){ //value
                        StringBuilder newSb = new StringBuilder(now);
                        if(!now.contains(Integer.toString(j+1))){
                            newSb.append(j+1);
                            stack.push(newSb.toString());
                        }
                    }
                }
            }
        }
        Collections.sort(list);
        String tmp = list.get((int)(k-1));
        for(int i=0; i<n; i++){ 
            answer[i] = Character.getNumericValue(tmp.charAt(i));
        }
        return answer;
    }
}
//------------------------------------------------------------------------------ 2_34 배달 (다익스트라)
import java.util.*;

class Solution {
    static final int INF = 50001;
    public int solution(int N, int[][] roads, int K) {
        int answer = 0;
        int[][] map = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = INF;
            }
        }
        for(int[] road: roads){
            int from = road[0]-1;
            int to = road[1]-1;
            int cost = road[2];
            if (map[from][to] > cost) { //이것도 나름 디테일이네
                map[from][to] = cost;
                map[to][from] = cost;
            }

        }
        print(map);
        return dijkstra(map, K);
    }
    int dijkstra(int[][] map, int limit){
        int[] dist = new int[map.length];
        for(int i=1; i<map.length; i++){
            dist[i] = map[0][i];            //<=여기 다름
        }
        System.out.println("1st dist: "+ Arrays.toString(dist));
        boolean[] visited = new boolean[map.length];
        visited[0] = true;
        
        //해당 인덱스 돌면서??
         for (int i=1; i<map.length; i++) {
            int min_idx = 0;    //0은 자기자신이니까
            int min_value = INF;
            //가장 가까운 인덱스 찾기
            for (int j = 1; j<map.length; j++) {
                if(!visited[j] && min_value > dist[j]) {
                    min_idx = j;
                    min_value = dist[j];
                }
            }
            System.out.println("min_idx: " + (min_idx+1)+"번을 통해~");
            System.out.println("min_value: " + min_value);

            //방문체크
            visited[min_idx] = true;
            System.out.println("visited: " + Arrays.toString(visited));
            // j번째를 통해 갈 수 있는 애들의 cost를 계산해보면서 저렴한것으로 세팅
            for (int j = 1; j < map.length; j++) {
                if(dist[j] > dist[min_idx] + map[min_idx][j]) {
                    System.out.println("1번에서 "+ (min_idx+1)+"번을 통해 "+(j+1)+" 번으로 가는 것이 기존 dist[j]보다 더 저렴" + dist[j] +" vs "+(dist[min_idx] + map[min_idx][j]));
                    dist[j] = dist[min_idx] + map[min_idx][j];
                    System.out.println(Arrays.toString(dist));
                }else{
                    System.out.println("1번에서 "+ (min_idx+1)+"번을 통해 "+(j+1)+" 번으로 가는 것이 기존 dist[j]보다 더 비쌈: " + dist[j] +" vs "+(dist[min_idx] + map[min_idx][j]));
                }
            }
            System.out.println("------" + Arrays.toString(dist)+"------");
         }
        int answer = 0;
        // 만들어진 dist로
        for(int value: dist){
            if(value <= limit){
                answer++;
            }
        }
        return answer;
    }
    
    void print(int[][] target){
        for(int i=0; i<target.length; i++){
            System.out.println(Arrays.toString(target[i]));
        }
        System.out.println("=============");
    }
}
//------------------------------------------------------------------------------ 2_35 멀쩡한 사각형 (별로임;)
class Solution {
    public long solution(int w, int h) {
        int gcd = getGcd(w,h);
        return ((long) w*h)-(((w/gcd)+(h/gcd)-1)*gcd);
        
    }
    int getGcd(int a, int b){
        if(b==0){
            return a;
        } else{
            return getGcd(b, a%b);
        }
    }
}

//------------------------------------------------------------------------------ 2_36 조이스틱 다시 도전해보자
import java.util.Arrays;
class Solution {
    public int solution(String name) {
        int answer = 0;
        int move = name.length() - 1; // 오른쪽으로 쭉 간 횟수
        
        for(int i = 0; i < name.length(); i++) {
            answer += Math.min(name.charAt(i) - 'A', 26 - (name.charAt(i) - 'A')); //상,하 알파벳 맞추기
            //마지막 문자가 아니고 다음 문자가 A일경우
            if (i < name.length() - 1 && name.charAt(i + 1) == 'A') {
                int endA = i + 1;
                //오른쪽으로 끝까지 가면서 A가 아닌게 나올 때까지
                while(endA < name.length() && name.charAt(endA) == 'A'){
                    endA++;
                }
                
                System.out.println("endA: " + endA + " i: "+ i);
                System.out.println("오른쪽으로 갔다 다시 왼쪽으로 꺾기: " + (i * 2 + (name.length() - endA)));
                System.out.println("왼쪽으로 갔다 다시 오른쪽으로 꺾기: " + (i + (name.length() - endA) * 2));
                    
                move = Math.min(move, i * 2 + (name.length() - endA));// 오른쪽으로 갔다 다시 왼쪽으로 꺾기 일때 수
                move = Math.min(move, i + (name.length() - endA) * 2);// 왼쪽으로 갔다 다시 오른쪽으로 꺾기 일때 수
            }
        }
        return answer + move;
    }
}
// 반례들
// ABABAAAAABA         10
// BABAAAB             7
// BBAABAB             9
// BBAAAAB             6
// AB                  2
// A                   0
// AA                  0

//------------------------------------------------------------------------------ 2_37 귤 고르기
import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int each: tangerine){
            map.put(each, map.getOrDefault(each,0)+1);
        }
        
        Integer[] countArr = new Integer[map.keySet().size()];
        int idx = 0;
        for(int key: map.keySet()){
            countArr[idx] = map.get(key); 
            idx++;
        }
        
        Arrays.sort(countArr, (a,b) -> (b).compareTo(a));
        
        
        for(int i=0; i<countArr.length; i++){
            k -= countArr[i];
            answer++;
            if(k <= 0){
                break;
            }
        } 
        return answer;
    }
}


//------------------------------------------------------------------------------ 2_38 연속 부분 수열 합의 개수
import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> bank = new HashSet<>();
        for(int i=1; i<=elements.length; i++){  //갯수
            for(int j=0; j<elements.length; j++){//시작점
                int sum = getSum(elements,i,j);
                bank.add(sum);
            }            
        }
        return bank.size();
    }
    int getSum(int[] map, int many, int start){
        int tmp = 0;    
        for(int i=start; i<start+many; i++){
            tmp += map[i%map.length];
        }
        return tmp;
    }
}
//------------------------------------------------------------------------------ 2_39 할인 행사
import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        HashMap<String,Integer> map = new HashMap<>();
        for(int i=0; i<number.length; i++){
            map.put(want[i], number[i]);
        }
        
        for(int i=0; i<10; i++){    //처음 세팅
            if(map.getOrDefault(discount[i], 11) != 11){    //있고 0이 아니라면
                map.put(discount[i],map.get(discount[i])-1);
            }
        }
        if(isMapEmpty(map)){
            answer++;
        }
        for(int i=0; i<discount.length-10; i++){    //시작 날
            //i번째 추가
            if(map.getOrDefault(discount[i], 11) != 11 ){
                map.put(discount[i],map.get(discount[i])+1);
            }
            //i+10번째 빼기
            if(map.getOrDefault(discount[i+10], 11) != 11){    //있고
                map.put(discount[i+10],map.get(discount[i+10])-1);
            }
            if(isMapEmpty(map)){
                answer++;
            }
        }
        return answer;
    }
    boolean isMapEmpty(HashMap<String,Integer> map){
        for(String key: map.keySet()){
            if(map.get(key) > 0){
                return false;
            }
        }
        return true;
    }
}
//------------------------------------------------------------------------------ 2_40 뒤에 있는 큰 수 찾기
//시간초과
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i=0; i<numbers.length; i++){
            boolean toggle = false;
            for(int j=i+1; j<numbers.length; j++){
                if(numbers[i] < numbers[j]){
                    answer[i] = numbers[j];
                    toggle = true;
                    break;
                }
            }
            if(!toggle){
                answer[i] = -1;
            }
        }
        return answer;
    }
}

//각 숫자값마다 가장 왼쪽에 있는 idx를 저장해놓은 Array
//2중 포문을 돌리면 시간초과
//  53433333...36
//  56433333...36
//  43212345
//  99999999

//각 숫자값마다 가장 왼쪽에 있는 idx를 저장해놓은 Array => 더 오래 걸림ㅋㅋㅋㅋㅋ
import java.util.*;


class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
        for(int i=0; i<numbers.length; i++){
            LinkedList<Integer> que = map.getOrDefault(numbers[i], new LinkedList<>());
            que.add(i);
            map.put(numbers[i], que);
        }
        // System.out.println(map);
        for(int i=0; i<numbers.length; i++){
            // System.out.println(numbers[i]);
            LinkedList<Integer> que = map.get(numbers[i]);
            que.poll();
            map.put(numbers[i], que);
            // System.out.println(map);
            
            int minIdx = 1000001;
            int whichKey = 0;
            for(Integer key: map.keySet()){
                if(key > numbers[i]){
                    // System.out.println("key: " + key);
                    LinkedList<Integer> eachQue = map.get(key);
                    if(eachQue.size() > 0){
                        int eachQueFirst = eachQue.element();
                        // System.out.println("eachQueFirst: " + eachQueFirst);     
                        if(eachQueFirst < minIdx){
                            minIdx = eachQueFirst;
                            whichKey = key;
                        }
                    }
                }
            }
            // System.out.println("whichKey: " + whichKey);
            // System.out.println("minIdx: " + minIdx);
            if(minIdx == 1000001){
                answer[i] = -1;
            }else{
                answer[i] = whichKey;
            }
            
        }
        return answer;
    }
}

//Stack을 사용해보렴
import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Node> stack = new Stack<>();
        
        for(int i=0; i<numbers.length; i++){
            if(stack.isEmpty()){
                stack.push(new Node(i,numbers[i]));
                continue;
            }
            // 하나씩 빼면서 numbers[i] 보다 작은지 확인해야해
            while(!stack.isEmpty() && stack.peek().getValue() < numbers[i]){
                Node outNode = stack.pop();
                answer[outNode.getIdx()] = numbers[i];
            }
            stack.push(new Node(i,numbers[i]));
        }
        int leftStackLen = stack.size();
        for(int i=0; i<leftStackLen; i++){
            Node outNode = stack.pop();
            answer[outNode.getIdx()] = -1;
        }
        return answer;
    }
    class Node{
        public int idx;
        public int value;
        public Node(int idx, int value){
            this.idx = idx;
            this.value = value;
        } 
        public int getIdx(){
            return this.idx;
        }
        public int getValue(){
            return this.value;
        }
    }
}