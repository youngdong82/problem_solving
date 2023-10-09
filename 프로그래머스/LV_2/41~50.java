//------------------------------------------------------------------------------ 2_41 롤케이크 자르기
//동일한 가짓수의 토핑이 올라가면 공평하게 롤케이크가 나누어진 것으로 생각
import java.util.*;


class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        for(int i=1; i<topping.length; i++){
            int[] arr1 = Arrays.copyOfRange(topping, 0,i);
            int[] arr2 = Arrays.copyOfRange(topping, i,topping.length);
            
            ArrayList<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();
            for(int j=0; j<arr1.length; j++){
                list1.add(arr1[j]);
            }
            for(int j=0; j<arr2.length; j++){
                list2.add(arr2[j]);
            }
            
            Set<Integer> set1 = new HashSet(list1);
            Set<Integer> set2 = new HashSet(list2);
            
            if(set1.size() == set2.size()){
                answer ++;
            }
        }
        return answer;
    }
}
//미리 HashMap을 만들어놓고 하나씩 옮길수가 있나...? => 좀 낫긴한데 비슷함ㅋㅋㅋ
//동일한 가짓수의 토핑이 올라가면 공평하게 롤케이크가 나누어진 것으로 생각
import java.util.*;


class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        
        for(int i=0; i<topping.length; i++){
            map1.put(topping[i], map1.getOrDefault(topping[i],0)+1);
            map2.put(topping[i],0);
        }
        
        for(int i=0; i<topping.length; i++){
            // map1에서 빼고
            map1.put(topping[i], map1.get(topping[i])-1);
            // map2에서 더하고
            map2.put(topping[i], map2.get(topping[i])+1);
            
            int map1NotZerocnt = 0;
            int map2NotZerocnt = 0;
            for(int key: map1.keySet()){
                if(map1.get(key) != 0){
                    map1NotZerocnt++;
                }
                if(map2.get(key) != 0){
                    map2NotZerocnt++;
                }
            }
            if(map1NotZerocnt == map2NotZerocnt){
                answer++;
            }
                
        }
        return answer;
    }
}
//2번째의 문제는 topping의 i번째마다 map을 전부 돌면서
//value가 0이 아닌 애들을 일일이 세고 있다
//최대 10000 * 1000000으로 10억 초과

//map 1개를 set으로 바꾸고 => set.size()로 바로 확인 가능
//어짜피 topping[i]를 다른 곳으로 옮기는 과정에서 map에서 빠지는 값을 확인하니까
//일일히 세지 말고 0일 경우에만 외부의 map1NotZerocnt에서 빼주는걸로 변경
import java.util.*;


class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        HashMap<Integer, Integer> map1 = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        
        for(int i=0; i<topping.length; i++){
            map1.put(topping[i], map1.getOrDefault(topping[i],0)+1);
        }
        
        int map1NotZerocnt = map1.size();
        
        for(int i=0; i<topping.length; i++){
            int tmp =  map1.get(topping[i])-1;
            if(tmp == 0){
                map1NotZerocnt--;
            }
            // map1에서 빼고
            map1.put(topping[i], tmp);
            // map2에서 더하고
            set.add(topping[i]);
            
            if(map1NotZerocnt == set.size()){
                answer++;
            }
                
        }
        return answer;
    }
}


//------------------------------------------------------------------------------ 2_42 숫자 변환하기 (dp)
// x, n이 짝수일 경우 홀수는 절대 못 만듦
// 이후엔 BFS
// (작아지지 않음)커지다가, y를 넘어가버리면 못 만듦
import java.util.*;


class Solution {
    public int solution(int x, int y, int n) {
        int answer = -1;
        //x, n이 짝수일 경우 홀수는 절대 못만듦
        if(x%2 == 0 && n%2==0 && y%2!=0){
            return answer;
        }
        //여기서부터 bfs (visited 필요없음)
        LinkedList<Node> que = new LinkedList<>();
        que.add(new Node(0,x));
        while(!que.isEmpty()){
            Node now = que.poll();
            if(now.getValue() == y){
                answer = now.getDepth();
                break;
            }
            if(now.getValue() < y){
                for(int i=0; i<3; i++){
                    if(i==0){
                        que.add(new Node(now.getDepth()+1, now.getValue()*2));
                    }else if(i==1){
                        que.add(new Node(now.getDepth()+1, now.getValue()*3));
                    }else{
                        que.add(new Node(now.getDepth()+1, now.getValue()+n));
                    }
                }
            }
        }
        
        return answer;
    }
    class Node {
        private int depth;
        private int value;
        public Node(int depth, int value){
            this.depth = depth;
            this.value = value;
        }
        public int getDepth(){
            return depth;
        }
        public int getValue(){
            return value;
        }
    }
}

// 최악의 경우  depth가 쌓일때마다 3의 제곱씩 늘어남
// depth가 19만 되어도 시간초과
// 뺄때 확인하지 말고 넣을 때 확인만 해도 제곱의 다음 수만큼 절약 가능
import java.util.*;


class Solution {
    public int solution(int x, int y, int n) {
        int answer = -1;
        //x, n이 짝수일 경우 홀수는 절대 못만듦
        if(x%2 == 0 && n%2==0 && y%2!=0){
            return answer;
        }
        //여기서부터 bfs
        LinkedList<Node> que = new LinkedList<>();
        que.add(new Node(0,x));
        while(!que.isEmpty()){
            Node now = que.poll();
            for(int i=0; i<3; i++){
                int temp;
                if(i==0){
                    temp = now.getValue()*2;
                }else if(i==1){
                    temp = now.getValue()*3;
                }else{
                    temp = now.getValue()+n;
                }
                if(temp == y){
                    answer = now.getDepth()+1;
                    return answer;
                }
                if(temp < y){
                    que.add(new Node(now.getDepth()+1, temp));
                }
            }
        }
        return answer;
    }
    class Node {
        private int depth;
        private int value;
        public Node(int depth, int value){
            this.depth = depth;
            this.value = value;
        }
        public int getDepth(){
            return depth;
        }
        public int getValue(){
            return value;
        }
    }
}
//아이디어만 가져옴 dp로 풀어봐
import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int[] dp = new int[y + 1]; //0부터 y+1까지
        dp[x] = 1;
        //해당 인덱스가 계산 결과값이고
        //인덱스의 value는 계산 횟수
        for (int i=x; i<y+1; i++) { //한칸씩 돌꺼야
            if(dp[i] == 0){ //0이면 해당 값으로 갈 수 없다는 뜻이니까 패스
                continue;
            }
            if(i*2 <y+1){
                dp[i*2] = (dp[i*2] == 0) ?  dp[i] + 1 : Math.min(dp[i*2], dp[i]+1);
            }
            if(i*3 <y+1){
               dp[i*3] = (dp[i*3] == 0) ?  dp[i] + 1 : Math.min(dp[i*3], dp[i]+1);
            }
            if(i+n <y+1){
               dp[i+n] = (dp[i+n] == 0) ?  dp[i] + 1 : Math.min(dp[i+n], dp[i]+1);
            }
        }
        return dp[y]-1;
    }
}

//------------------------------------------------------------------------------ 2_43 2 x n 타일링 => 확인하셈
import java.util.*;

class Solution {
    public int solution(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3; i<n+1; i++){
            dp[i] = (dp[i-1] + dp[i-2])%1000000007;
            // System.out.println(Arrays.toString(dp));
        }
        
        return dp[n];
    }
}
//------------------------------------------------------------------------------ 2_44 택배상자
// 순서대로(1번 상자부터) 상자를 내릴 수 있다
// 미리 알려준 순서에 맞게 영재가 택배상자를 실어야
// 보조 컨테이너는 stack
import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int orderIdx = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=1; i<order.length+1; i++){
            if(order[orderIdx] == i){   //1. 순서에 맞는가? 예
                answer++;
                orderIdx++;
            }else{
                if(!stack.isEmpty() && stack.peek() == order[orderIdx]){
                    stack.pop();
                    i--;
                    answer++;
                    orderIdx++;
                }else{
                    stack.push(i);
                }
            }
        }
        while(!stack.isEmpty() && stack.peek() == order[orderIdx]){
            stack.pop();
            answer++;
            orderIdx++;
        }
        
        
        return answer;
    }
}

// if문 더 깔끔하게??
import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int orderIdx = 0;
        int i = 1;
        Stack<Integer> stack = new Stack<>();

        while(i <= order.length || (!stack.isEmpty() && stack.peek() == order[orderIdx] )){
            if(order[orderIdx] == i){   //1. 순서에 맞는가? 예
                i++;
                answer++;
                orderIdx++;
            }else{
                if(!stack.isEmpty() && stack.peek() == order[orderIdx]){
                    stack.pop();
                    answer++;
                    orderIdx++;
                }else{
                    stack.push(i);
                    i++;
                }
            }
        }        
        
        return answer;
    }
}
//------------------------------------------------------------------------------ 2_45 연속된 부분 수열의 합
//부분 수열이 여러 개인 경우 길이가 짧은 수열
//길이가 같을 경우 앞쪽에 나오는 수열

//길이가 짧은게 먼저 + 비내림차순이니까, 다 돌긴 해야 함
import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        ArrayList<int[]> resultList = new ArrayList<>();
        
        int start = 0;
        int end = 0;
        int sum = sequence[0];
        while(end < sequence.length && start <= end ){
            if(sum <= k){  //작거나 같으면
                if(sum == k){ // 같으면
                    resultList.add(new int[]{start, end});
                }
                if(end+1 < sequence.length){
                    end++;
                    sum += sequence[end];
                }else{
                    break;
                }
            }else{  //크면
                sum -= sequence[start];
                start ++;
            }
        }
        //여러개일 경우 짧은 것, 앞에있는 것
        Collections.sort(resultList, (a, b) -> {
            int aLen = a[1]-a[0]+1;
            int bLen = b[1]-b[0]+1;
            if(aLen == bLen){
                if(a[0] <= b[0]){
                    return -1;
                }else{
                    return 1;
                }
            }else if(aLen < bLen){
                return -1;
            }else{
                return 1;
            }
        });

        return resultList.get(0);
    }
}
//------------------------------------------------------------------------------ 2_46 무인도 여행
//구현문제
import java.util.*;


class Solution {
    public int[] solution(String[] maps) {
        // char[][] newMap = new char[maps.length][maps[0].length()];
        
        ArrayList<Integer> tmpAnswer = new ArrayList<>();
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[i].length(); j++){

                if(maps[i].charAt(j) != 'X'){   //바다가 아니라면
                    int foodSum =  bfs(maps, i,j);
                    tmpAnswer.add(foodSum);
                }
            }
        }
        if(tmpAnswer.size() == 0){
            return new int[]{-1};
        }
        Collections.sort(tmpAnswer);
        
        return tmpAnswer.stream().mapToInt(e -> e).toArray();
    }
    public int bfs(String[] maps, int startY, int startX){
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        LinkedList<int[]> que = new LinkedList<>();
        que.add(new int[]{startX, startY});
        //넣을때 추가해야함
        int summ = Character.getNumericValue(maps[startY].charAt(startX));
        //넣을때 X로 바꿔야함
        maps[startY] = maps[startY].substring(0,startX) +'X'+ maps[startY].substring(startX+1,maps[0].length());
        while(!que.isEmpty()){
            int[] now = que.poll();
            int nowX = now[0];
            int nowY = now[1];
            for(int i=0; i<4; i++){
                int nx = nowX+dx[i];
                int ny = nowY+dy[i];
                if(0 <= nx && nx < maps[0].length() &&
                   0 <= ny && ny < maps.length
                  ){   //범위 안이고
                    if(maps[ny].charAt(nx) != 'X'){ //바다가 아니라면
                        que.add(new int[]{nx, ny});
                        summ += Character.getNumericValue(maps[ny].charAt(nx));
                        maps[ny] = maps[ny].substring(0,nx) +'X'+ maps[ny].substring(nx+1,maps[0].length());
                    }
                }
            }
        }
        return summ;
    }
}


//------------------------------------------------------------------------------ 2_47 호텔 대실 =====> 질문
import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        ArrayList<Room> roomList = new ArrayList<>();
        for(int i=0; i<book_time.length; i++){
            boolean toggle = false;
            //전체 방 확인
            for(int j=0; j<roomList.size(); j++){
                Room nowRoom = roomList.get(j);
                if(nowRoom.isAvailable(book_time[i][0], book_time[i][1])){
                    nowRoom.booking(book_time[i][0], book_time[i][1]);
                    toggle = true;
                    break;
                }
            }
            //가능한 것이 없을 경우 하나 추가
            if(!toggle){
                roomList.add(new Room(book_time[i][0], book_time[i][1]));
            }
        }
        return roomList.size();
    }
    class Room{
        int[] timeTable = new int[1450];
        public Room(){
        }
        
        //생성 && booking
        public Room(String start, String end){
            int[] pasred = parseTime(start, end);
            for(int i=pasred[0]; i<pasred[1]+11; i++){
                if(i==pasred[0] || i == pasred[1]+10){   //처음과 (끝+쉬는시간)이라면
                    timeTable[i] += 1;
                }else{  //중간이라면
                    timeTable[i] += 2;
                }
                
            }
        }
        public boolean isAvailable(String start, String end){
            int[] pasred = parseTime(start, end);
            for(int i=pasred[0]; i<pasred[1]+11; i++){
                if(timeTable[i] == 2){   //예약이 되어있다면
                    // System.out.println("false");
                    return false;
                }
            }
            return true;
        }
        public void booking(String start, String end){
            int[] pasred = parseTime(start, end);
            //원래는 여기서 isAvailable로 유효성 체크 해야함
            for(int i=pasred[0]; i<pasred[1]+11; i++){                
                if(i==pasred[0] || i == pasred[1]+10){   //처음과 (끝+쉬는시간)이라면
                    timeTable[i] += 1;
                }else{  //중간이라면
                    timeTable[i] += 2;
                }
            }
        }
        private int[] parseTime(String start, String end){
            String[] splitedStart = start.split(":");
            String[] splitedEnd = end.split(":");
            int parseStart = (Integer.parseInt(splitedStart[0]) *60) + (Integer.parseInt(splitedStart[1]));
            int parseEnd = (Integer.parseInt(splitedEnd[0])*60) + (Integer.parseInt(splitedEnd[1]));
            
            return new int[]{parseStart, parseEnd};
        }
    }
}

//------------------------------------------------------------------------------ 2_48 마법의 엘리베이터 (이렇게 푸는거 아닌것 같은뎈ㅋㅋㅋ)
//버튼 누른 결과 값이 0보다 작으면 이동x
//올라갈수도 있음
//0층이 가장 아래층
//가장 적게 눌러서 0으로 만들어라

//가장 최소의 방법으로 10의 자리 배수로 만들어야 해
//10 다음 100 다음 1000 이런식으로

// 5일 경우 내리는게 낫고, 6일 경우 올리는게 나아

import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        LinkedList<Integer> que = new LinkedList<>();
        
        while(storey > 0){
            que.add(storey % 10);
            storey /= 10;
        }
        while(!que.isEmpty()){
            // System.out.println(que);
            int now = que.poll();
              
            if(now >5){
                answer += (10-now);
                if(que.isEmpty()){
                    que.add(1);
                }else{
                    que.set(0, que.get(0) + 1); 
                }
            }else if(now == 5 && !que.isEmpty() && que.get(0) >= 5){
                //que.get(0)을 6 이상으로 만들수 있으면...?
                //5라도 올리는것이 좋다.  
                answer += (10-now);
                que.set(0, que.get(0) + 1); 
            } else{
                answer += now;
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 2_49 숫자 카드 나누기 (구현)
//두 조건 중 하나를 만족하는 가장 큰 양의 정수 a의 값
// a의 모든 카드를 나눌 수 있지만 b의 카드는 하나도 나눌 수 없는 정수

import java.util.*;


class Solution {
    public int solution(int[] arr1, int[] arr2) {
        int answer = 0;
        //중복제거한 후 (해봤자 50만번임)
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for(int each: arr1){
            set1.add(each);
        }
        for(int each: arr2){
            set2.add(each);
        }
        //list로 변환 후 
        ArrayList<Integer> list1 = new ArrayList<>(set1);
        ArrayList<Integer> list2 = new ArrayList<>(set2);
        //sort하고
        Collections.sort(list1);
        Collections.sort(list2);
        
        //가장 작은 수의 sqrt 값을 돌면서 
        int list1MinValue = list1.get(0);
        ArrayList<Integer> gcdList1 = new ArrayList<>();
        for(int i=1; i<=(int) Math.sqrt(list1MinValue)+1; i++){
            if(list1MinValue%i == 0){
                int big = list1MinValue/i;
                int small = i;
                boolean bigToggle = true;
                boolean smallToggle = true;
                for(int j=1; j<list1.size(); j++){
                    if(!bigToggle && !smallToggle){
                        break;
                    }
                    if(list1.get(j) % big != 0){
                        bigToggle = false;
                    }
                    if(list1.get(j) % small != 0){
                        smallToggle = false;
                    }
                }
                if(bigToggle){
                    gcdList1.add(big);
                }
                if(smallToggle){
                    gcdList1.add(small);
                }
            }
        }
        
        int list2MinValue = list2.get(0);
        ArrayList<Integer> gcdList2 = new ArrayList<>();
        for(int i=1; i<=(int) Math.sqrt(list2MinValue)+1; i++){
            if(list2MinValue%i == 0){
                int big = list2MinValue/i;
                int small = i;
                boolean bigToggle = true;
                boolean smallToggle = true;
                for(int j=1; j<list2.size(); j++){
                    if(!bigToggle && !smallToggle){
                        break;
                    }
                    if(list2.get(j) % big != 0){
                        bigToggle = false;
                    }
                    if(list2.get(j) % small != 0){
                        smallToggle = false;
                    }
                }
                if(bigToggle){
                    gcdList2.add(big);
                }
                if(smallToggle){
                    gcdList2.add(small);
                }
            }
        }
        
        //최대공약수 하나씩 빼면서 다른 arr 돌면서 확인
        //그 어떤 값으로 나뉘어지지 않는다면 answer 업데이트
        for(int gcd : gcdList1){
            boolean toggle = true;
            for(int j=0; j<list2.size(); j++){
                if(list2.get(j) % gcd == 0){
                    toggle = false;
                    break;
                }
            }
            if(toggle){
                answer = Math.max(answer, gcd);
            }
        }

        for(int gcd : gcdList2){
            boolean toggle = true;
            for(int j=0; j<list1.size(); j++){
                if(list1.get(j) % gcd == 0){
                    toggle = false;
                    break;
                }
            }
            if(toggle){
                answer = Math.max(answer, gcd);
            }
        }      
        
        return answer;
    }
}
//------------------------------------------------------------------------------ 2_50 시소 짝꿍   *** 스스로 효율성 개선!!
//시간초과
class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        for(int i=0; i<weights.length-1; i++){
            for(int j=i+1; j<weights.length; j++){
                if(isMatch(weights[i],weights[j])){
                    answer ++;
                }
            }
        }
        return answer;
    }
    boolean isMatch(int w1, int w2){
        int maxW = Math.max(w1,w2);
        int minW = Math.min(w1,w2);
        //최대공약수 구한 후
        int gcdValue = gcd(maxW, minW);
        
        int afterMaxW = maxW / gcdValue;
        int afterMinW = minW / gcdValue;
        // System.out.println(afterMaxW +","+ afterMinW);
        if(
            (afterMaxW == 1 && afterMinW == 1)
        ||  (afterMaxW == 3 && afterMinW == 2)
        ||  (afterMaxW == 2 && afterMinW == 1)
        ||  (afterMaxW == 4 && afterMinW == 3)
        ){
            return true;
        }else{
            return false;
        }
    }
    int gcd(int a, int b){
        if(b==0){
            return a;
        } else{
            return gcd(b, a%b);
        }
    }
}

//최대공약수를 나누기 비율로 대체해서 빠르게 만들었지만 아직 부족해
class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        for(int i=0; i<weights.length-1; i++){
            for(int j=i+1; j<weights.length; j++){
                if(isMatch(weights[i],weights[j])){
                    answer ++;
                }
            }
        }
        return answer;
    }
    boolean isMatch(int w1, int w2){
        int maxW = Math.max(w1,w2);
        int minW = Math.min(w1,w2);
        
        double proportion = minW /(double) maxW;
        // System.out.println(proportion);
        if( proportion == (1 /(double) 1)
        ||  proportion == (1 /(double) 2)
        ||  proportion == (2 /(double) 3)
        ||  proportion == (3 /(double) 4)
        ){
            return true;
        }else{
            return false;
        }
    }
}

// weights의 길이는 최대 100,000인데
// 각 weights는 100 ~ 1000
// 가장 다양한 weights가 나온다해도 그 종류는 901개
// 그렇다면 최대 길이의 경우 필연적으로 반복되는 값들이 있을 것
// 그것을 줄여보자
import java.util.*;


class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<weights.length; i++){
            map.put(weights[i], map.getOrDefault(weights[i], 0) + 1);
        }
        // System.out.println(map);
        
        List<Integer> keyList = new ArrayList<>(map.keySet());
        // System.out.println(keyList);
        
        //같은 애들 추가
        for(int i=0; i<keyList.size(); i++){
            int eachMany = map.get(keyList.get(i));
            if(eachMany > 1){
                answer +=  (long) ((long) eachMany*(eachMany-1)) / 2;
            }
        }
        
        
        //다른 애들 추가
        for(int i=0; i<keyList.size()-1; i++){
            for(int j=i+1; j<keyList.size(); j++){
                if(isMatch(keyList.get(i), keyList.get(j))){
                    answer += ((long) map.get(keyList.get(i)) * (long) map.get(keyList.get(j)));
                }
            }
        }
        // System.out.println(answer);
        
        return answer;
    }
    boolean isMatch(int w1, int w2){
        int maxW = Math.max(w1,w2);
        int minW = Math.min(w1,w2);
        
        double proportion = minW /(double) maxW;
        // System.out.println(proportion);
        if( proportion == (1 /(double) 1)
        ||  proportion == (1 /(double) 2)
        ||  proportion == (2 /(double) 3)
        ||  proportion == (3 /(double) 4)
        ){
            return true;
        }else{
            return false;
        }
    }
}

