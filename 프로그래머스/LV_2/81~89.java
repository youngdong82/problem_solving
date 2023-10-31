//------------------------------------------------------------------------------ 2_81 괄호 변환 => 문제가 정확히 이해가 안됨;;
//갯수 맞으면 균형
//딱딱 잘 맞으면 바른
class Solution {
    public String solution(String p) {
        //1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다. 
        // if(p.length() == 0){
        //     return "";
        // }
        StringBuilder sb = new StringBuilder();
        while(p.length() > 0){
            // 두 "균형" u,v로 분리.u는 최소갯수 "균형", v는 빈 문자열 가능
            String[] afterDivide = getBalanIdx(p);
            String u = afterDivide[0];
            String v = afterDivide[1];
            if(isGood(u)){  //바르다면
                sb.append(u);
            }else{          //바르지 않다면
                //u처리하고
                String newU = makeToGood(u);
                sb.append(newU);
            }
            p = v;
            // System.out.println("become answer: " + sb.toString());
            // System.out.println("next p: " + p);
            // break;
        }
        return sb.toString();
    }
    String[] getBalanIdx(String now){
        int cnt = 0;
        int idx = 0;
        for(int i=0; i<now.length(); i++){
            if(now.charAt(i) == '('){
                cnt++;
            }else{
                cnt--;
            }
            if(cnt == 0){
                idx = i;
                break;
            }
        }
        String u = now.substring(0,idx+1);
        String v = now.substring(idx+1);
        return new String[]{u, v};
    }
    boolean isGood(String target){
        int cnt = 0;
        for(int i=0; i<target.length(); i++){
            if(target.charAt(i) == '('){
                cnt++;
            }else{
                cnt--;
            }
            if(cnt < 0){
                return false;
            }
        }
        return true;
    }
    String makeToGood(String u){
        StringBuilder sb = new StringBuilder();
        int depth = 0;
        while(!isGood(u)){
            depth++;
            u = reverse(u.substring(1,u.length()-1));
        }
        for(int i=0; i<depth; i++){
            sb.append("(");
        }
        sb.append(u);
        for(int i=0; i<depth; i++){
            sb.append(")");
        }
        return sb.toString();
    }
    String reverse(String target){
        StringBuilder sb = new StringBuilder();        
        for(int i=0; i<target.length(); i++){
            if(target.charAt(i) == ')'){
                sb.append('(');
            }else{
                sb.append(')');
            }
        }
        return sb.toString();
    }
}

//------------------------------------------------------------------------------ 2_82 후보키 => 아직 못풀었음
//후보 키의 최대 개수 return

import java.util.*;


class Solution {
    ArrayList<ArrayList<Integer>> keyContainer = new ArrayList<>();
    
    //1,2로 성공 했다고 해서 
    //1,2를 밴해서
    //1,3이나 2,3이 안되게 하면 안된다
    //0이 포함 되면 안되는 이유는
    //0 자체로 후보키이기 때문
    //1,2가 성공했다면
    //1,2,3이 안되어야함
    //banList 부분을 손봐야함
    
    ArrayList<Integer> banList =  new ArrayList<>();;
    boolean[] isUsed;
    String[][] relationW;
    public int solution(String[][] relation) {
        relationW = relation;
        int answer = 0;
        isUsed = new boolean[relation[0].length];
        for(int i=1; i<relation[0].length+1; i++){  //갯수
            dfs(i,new ArrayList<>());
        }
        return answer;
    }
    // depth 갯수를 가진 숫자조합을 만들어 (중복없고, 금지된거 없이)
    void dfs(int N, ArrayList<Integer> arr){
        if(arr.size() == N){
            if(isCandiKey(arr)){
                //isUsed에 true처리
                for(int i=0; i<arr.size(); i++){
                    isUsed[arr.get(i)] = true;
                    banList.add(arr.get(i));
                }
                keyContainer.add(arr);
            }
            return;
        }
        for(int i=0; i<isUsed.length; i++){
            if(isUsed[i] == false && banList.indexOf(i) < 0){
                isUsed[i] = true;
                arr.add(i);
                dfs(N, (ArrayList<Integer>)arr.clone());
                arr.remove(arr.size()-1);
                isUsed[i] = false;
            }
        }
    }
    boolean isCandiKey(ArrayList<Integer> list){
        HashMap<String, Boolean> map = new HashMap<>();
        //HashMap에다가 넣는데 default값이 아니라면?
        for(String[] each: relationW){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<list.size(); i++){
                sb.append(each[list.get(i)]);
                sb.append("_");
            }
            if(map.getOrDefault(sb.toString(), false) == false){
                map.put(sb.toString(), true);
            }else{
                return false;
            }            
        }
        return true;
    }
}


//------------------------------------------------------------------------------ 2_83 순위 검색
// 개발언어 :   cpp, java, python 중 하나
// 직군    :   backend와 frontend 중 하나
// 경력    :   Junior와 senior 중 하나
// 소울푸드 :   chicken과 pizza 중 하나

// 조건에 맞는 지원자 몇 명
// info  5만 이하      query 10만 이하
// 모든조건String: 점수 인 HashMap만들어서
import java.util.*;


class Solution {
    HashMap<String, ArrayList<Integer>> infoMap = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        for(int i=0; i<info.length; i++){
            String[] splited = info[i].split(" ");
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<4; j++){
                sb.append(splited[j]);
            }
            ArrayList<Integer> tmpList;
            if(infoMap.getOrDefault(sb.toString(), new ArrayList<>()).size() == 0){
                tmpList = new ArrayList<>();
            }else{
                tmpList = infoMap.get(sb.toString());
            }
            tmpList.add(Integer.parseInt(splited[4]));
            infoMap.put(sb.toString(), tmpList);
        }
        // System.out.println(infoMap);

        int[] answer = new int[query.length];
        for(int i=0; i<query.length; i++){
            String[] splited = query[i].split(" ");
            ArrayList<String> filterd = new ArrayList<>();
            for(int j=0; j<splited.length-1; j++){
                if(!splited[j].equals("and")){
                    filterd.add(splited[j]);
                }
            }
            // System.out.println(filterd); 
            int wantScore = Integer.parseInt(splited[7]);
            // System.out.println(wantScore);
            
            int cnt = 0;
            //여기서 infoMap 돌면서
            for(String key: infoMap.keySet()){
                // filterd에 부합하는지
                if(isOk(key, filterd)){
                    ArrayList<Integer> nowList = infoMap.get(key);
                    for(Integer eachSore: nowList){
                        if(eachSore >=wantScore){
                            cnt++;
                        }
                    }
                }
            }
            // System.out.println("cnt: " + cnt);
            answer[i] = cnt;
            
        }
        return answer;
    }
    boolean isOk(String key, ArrayList<String> filterd){
        for(int i=0; i<4; i++){
            String now = filterd.get(i);
            if(now.equals("-")){
                continue;
            }else if(key.indexOf(now) == -1){
                return false;
            }
        }
        return true;
    }
}

// => 시간초과
// 각 query 마다 전체 infoMap을 돌면서 확인 중
// 최대 10만 * 1만  * 20 = 20,000,000 마다 nowList 하나하나 돈다
// - 를 해결해야하는데
// infoMap에 넣을때 -를 포함한 모든 경우의 수를 넣어주면,
// phase1에서 5만 * 15
// 뺄때는 n

아.... 그만;;



//------------------------------------------------------------------------------ 2_84 두 큐 합 같게 만들기
// => 11,28 시간초과
import java.util.*;


class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum = 0;
        long sum1 = 0;
        long sum2 = 0;
        LinkedList<Long> que1 = new LinkedList<>();
        LinkedList<Long> que2 = new LinkedList<>();
        for(int i=0; i<queue1.length; i++){
            sum1 += queue1[i];
            sum += queue1[i];
            que1.add((long)queue1[i]);
        }
        for(int i=0; i<queue2.length; i++){
            sum2 += queue2[i];
            sum += queue2[i];
            que2.add((long)queue2[i]);
        }
        if(sum%2 != 0){
            return -1;
        }
        int answer = 0;
        while(true){
            //sum1 과 sum2 중 큰 것에서 pop
            // 해당 pop이 sum/2보다 클 경우 -1
            if(sum1 > sum2){
                long poped = que1.poll();
                if(poped > sum/2){
                    answer = -1;
                    break;
                }
                que2.add(poped);
                sum1 -= poped;
                sum2 += poped;
                answer++;
            }else if(sum1 < sum2){
                long poped = que2.poll();
                if(poped > sum/2){
                    answer = -1;
                    break;
                }
                que1.add(poped);
                sum2 -= poped;
                sum1 += poped;
                answer++;
            }else{
                break;
            }
        }
        return answer;
    }
}
// 시간 초과가 문제라기 보다는
// 완벽하게 한바퀴 돌았는데도 while문이 끝나지 않는 것이 문제인 것 같다.
// 완벽하게 한바퀴 돈다는 것을 어떻게 알지...?
// sum1이 첫 sum2와 같을 때 && 모든 값이 같을 경우 (한바퀴 돌았다)
// 1,1,1 / 2,1,1이면 어쩔껀데 => 기각
// 완벽하게 한바퀴 돈다 = (queue1의 갯수 + queue2의 갯수) * 2
// 이유는 모르겠지만... 안전빵 *2 함;;
// 최대로 길어봤자 (30만 *2) *2 = 120만 이니까 충분

// 다른 사람 코드 보니까 queue1의 갯수 * 3을 했는데 이유는 잘 모르겠음
// 예시가 q1 = [1, 1, 1, 1, 1, 1] q2=[1, 1, 1, 1, 11, 1]라는데,,, 어떻게 나온거지

import java.util.*;


class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum = 0;
        long sum1 = 0;
        long sum2 = 0;
        LinkedList<Long> que1 = new LinkedList<>();
        LinkedList<Long> que2 = new LinkedList<>();
        for(int i=0; i<queue1.length; i++){
            sum1 += queue1[i];
            sum += queue1[i];
            que1.add((long)queue1[i]);
        }
        for(int i=0; i<queue2.length; i++){
            sum2 += queue2[i];
            sum += queue2[i];
            que2.add((long)queue2[i]);
        }
        
        if(sum%2 != 0){
            return -1;
        }
        int answer = 0;
        while(true){
            if(answer > (queue1.length + queue2.length)*2){
                answer = -1;
                break; 
            }
            //sum1 과 sum2 중 큰 것에서 pop
            // 해당 pop이 sum/2보다 클 경우 -1
            if(sum1 > sum2){
                long poped = que1.poll();
                if(poped > sum/2){
                    answer = -1;
                    break;
                }
                que2.add(poped);
                sum1 -= poped;
                sum2 += poped;
                answer++;
            }else if(sum1 < sum2){
                long poped = que2.poll();
                if(poped > sum/2){
                    answer = -1;
                    break;
                }
                que1.add(poped);
                sum2 -= poped;
                sum1 += poped;
                answer++;
            }else{
                break;
            }
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 2_85 이모티콘 할인행사
// emoticons의 최대 할인 조합 갯수
// 4의 7승 = 16384
// user의 최대 수 100
// 100명을 16384번 돌면서 계산 = 2,000,000 충분

import java.util.*;

class Solution {
    ArrayList<ArrayList<Integer>> condi = new ArrayList<>();
    public int[] solution(int[][] users, int[] emoticons) {
        int[] discount = {10,20,30,40};
        //dfs로 조합
        ArrayList<Integer> now = new ArrayList<>();
        dfs(now, emoticons.length, discount);
        
        
        ArrayList<Result> resultList = new ArrayList<>();
        for(int i=0; i<condi.size(); i++){
            int tmpSum = 0;
            int tmpCnt = 0;
            //각 할인조합 별로 users 돌면서 계산
            for(int j=0; j<users.length; j++){
                Result result = calc(condi.get(i), users[j], emoticons);
                tmpCnt += result.getCnt();
                tmpSum += result.getCost();
            }
            // System.out.println("tmpCnt: "+tmpCnt+ " tmpSum: "+tmpSum);
            resultList.add(new Result(tmpCnt, tmpSum));
        }
        //거꾸로 sort
        Collections.sort(resultList, (a,b) -> {
            if(a.getCnt() > b.getCnt()){
                return -1;
            }else if(a.getCnt() < b.getCnt()){
                return 1;
            }else{
                return Integer.compare(b.getCost(), a.getCost());
            }
        });
        int[] answer = {resultList.get(0).getCnt(), resultList.get(0).getCost()};
        return answer;
    }
    void dfs(ArrayList<Integer> now, int target, int[] discount){
        if(now.size() == target){
            //복사해서 추가
            ArrayList<Integer> copied = new ArrayList<>();
            copied.addAll(now);
            condi.add(copied);
            return;
        }
        for(int i=0; i<discount.length; i++){
            now.add(discount[i]);
            dfs(now, target, discount);
            now.remove(now.size()-1);
        }
    }
    Result calc(ArrayList<Integer> eachCondi, int[] user, int[] emoticons){
        int totalCost = 0;
        for(int i=0; i<emoticons.length; i++){
            //원하는 할인률 이상이라면
            if(eachCondi.get(i) >= user[0]){
                int cost = emoticons[i] / 100 * (100-eachCondi.get(i));
                totalCost += cost;
            }
        }
        if(totalCost >= user[1]){
            return new Result(1,0);
        }else{
            return new Result(0,totalCost);
        }
    }
    class Result{
        private int cnt;
        private int cost;
        
        Result(){};
        Result(int cnt, int cost){
            this.cnt = cnt;
            this.cost = cost;
        }
        
        public int getCnt(){
            return cnt;
        }
        public int getCost(){
            return cost;
        }
    }
}
// 구조적인 변경은 아니지만
// dfs로 condi 만들 때
// users의 최소 할인율을 구한 후 
// 해당 할인율보다 낮으면 넣지 않는걸로 하면 효율화 가능할듯...?


//------------------------------------------------------------------------------ 2_86 양궁대회  (완전탐색 + 구현)
// 10-2 라면 3개를 쏴서 10이상 만들면 10차이
// 10-2 라면 3개를 쏴서 5,4,3      2차이
// 10-2 라면 3개를 쏴서 6,5,4      5차이
// 10-2 라면 3개를 쏴서 7,6,5      8차이
// 10-2 라면 3개를 쏴서 8,7,6      11차이
// => 최대한 상대방꺼 뺏는게 최선
// => 근데 당연히 지는 점수가 있다.

// 10-3 라면 4개를 쏴서 10이상 만들면 10차이
// 10-2 라면 4개를 쏴서 5,4,3,2    4차이
// 10-2 라면 4개를 쏴서 6,5,4,3    8차이
// 10-2 라면 4개를 쏴서 7,6,5,4    13차이

// 근데 당연히 해당 번호가 비어있다는 보장 없음
// 경우의 수가 미친듯이 많음
// => 무조건 완전탐색
import java.util.*;


class Solution {
    HashSet<String> condiSet = new HashSet<>();
    public int[] solution(int n, int[] info) {
        ArrayList<Integer> container = new ArrayList<>();
        dfs(n, container);
        
        ArrayList<String> answerList = new ArrayList<>();
        HashMap<String, Integer> resultMap = new HashMap<>();
        
        int maxResult = 0;
        for(String condi: condiSet){
            int result = whoWin(condi, info);
            if(result > 0){     //condi가 이겼다면
                // resultMap에 넣기
                maxResult = Math.max(maxResult, result);
                resultMap.put(condi, result);
            }
        }
        // hashmap 크기 0이면 -1 리턴
        if(resultMap.size() == 0){
            return new int[]{-1};
        }
        // 가장큰 점수차이만 돌면서 answerList에 추가
        for(String key :resultMap.keySet()){
            if(resultMap.get(key) == maxResult){
                answerList.add(key);
            }
        }
        // 여기서 이제 sorting
        // 가장 낮은 점수를 더 많이 맞힌 경우를 return
        Collections.sort(answerList, (a,b) -> {
            String[] splitedA = a.split("_"); 
            String[] splitedB = b.split("_");
            int sorttt = 0;
            for(int i=10; i>=0; i--){
                if(splitedA[i].equals(splitedB[i])){
                    continue;
                }else{
                    if(Integer.parseInt(splitedA[i]) - Integer.parseInt(splitedB[i]) > 0){
                        sorttt = -1;
                    }else{
                        sorttt = 1;
                    }
                    break;
                }
            }
            return sorttt;
        });
        // System.out.println(answerList);
        
        String[] splited = answerList.get(0).split("_"); 
        int[] answer = new int[11];
        for(int i=0; i<11; i++){
            answer[i] = Integer.parseInt(splited[i]);
        }
        
        return answer;
    }
    
    void dfs(int target, ArrayList<Integer> list){
        //list의 각 갯수를 전부 더한 수
        int cnt = 0;
        for(int i=0; i<list.size(); i++){
            cnt += list.get(i);
        }
        if(cnt == target){
            //갯수 맞춰서 String으로 만들고
            String condi = makeListToCondi(target, list);
            // System.out.println(condi);
            condiSet.add(condi);
            return;
        }
        for(int i=0; i<11; i++){
            if(list.size() <= 10){      //10개를 넘지 못하게 (0점 빼)
                if(cnt + i <= target){  //추가되는 숫자를 포함해 갯수가 target보다 같거나 작을경우
                    list.add(i);
                    dfs(target, list);
                    list.remove(list.size()-1);
                } 
            }

        }
    }
    String makeListToCondi(int target, ArrayList<Integer> list){
        StringBuilder sb = new StringBuilder();
        for(Integer each: list){
            sb.append(each);
            sb.append("_");
        }
        while(sb.length() < 11*2){        //길이가 target*2 가 될때까지 0_ 추가
            sb.append("0_");
        }
        //sb 마지막 하나 빼기
        sb.setLength((11*2)-1);
        
        return sb.toString();
    }
    //몇 점차로 이겼는지 확인, 졌거나 비겼으면 -1 리턴
    int whoWin(String condi, int[] loser){
        int loserScore = 0;
        int condiScore = 0;
        String[] splited = condi.split("_");           
        for(int i=0; i<10; i++){
            if(loser[i] == 0 && splited[i].equals("0")){
                continue;
            }
            if(loser[i] >= Integer.parseInt(splited[i])){
                loserScore += (10-i);   //loser 승
            }else{
                condiScore += (10-i);   //condi 승
            }
        }
        if(loserScore >= condiScore){   //loser가 이겼다면
            return -1;
        }else{
            return condiScore - loserScore;
        }
    }
}

// 다른사람이 푼 것인데 이해가 하낫도 안되네....;;
class Solution {

    int max, ans[], apeach[];

    void find(int n, int cur) {
        int score = 0, state[] = new int[11];
        for(int i=1; i<=10; i++) {
            if((cur&1<<i) > 0) {
                n -= state[10-i] = apeach[10-i]+1;
                if(n < 0) return;
                score += i;
            }else if(apeach[10-i] > 0) score -= i;
        }
        if(score <= 0) return;
        state[10] = n;
        if(max < score) {
            max = score;
            ans = state;
        }else if(max == score) {
            for(int i=10; i>=0; i--) {
                if(ans[i] != state[i]) {
                    if(state[i] > ans[i]) 
                        ans = state;
                    return;
                }
            }
        }
    }
    int[] solution(int n, int[] info) {
        apeach = info;
        for(int i=1; i<1<<11; i++)
            if(Integer.bitCount(i) <= n)
                find(n, i);

        return max == 0 ? new int[] {-1} : ans;
    }
}

//------------------------------------------------------------------------------ 2_87 택배 배달과 수거하기
//트럭이 최소한으로 왔다갔다해야해
// => 멀리서부터 처리
// => 최대한 한번에 처리

//배달의 경우 cap만큼 무조건 배달
//수거의 경우 cap만큼 무조건 수거

// 뒤에서 인덱스를 시작해 앞으로 오면서
// 배달, 수거를 생각해서 거리를 설정
import java.util.*;


class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;   //이동거리
        
        int deliverIdx = n-1;
        int collectIdx = n-1;
        while(true){
            int startDeliverIdx = -1;
            int startCollectIdx = -1;
            int deliverCap = cap;
            int collectCap = cap;
            //cap이 다할때까지 deliverIdx 내리기 
            while(deliverIdx >= 0){
                int nowDeliver = deliveries[deliverIdx];
                //세팅 전, 처음 0이 아니라면 startDeliverIdx 세팅
                if(nowDeliver > 0 && startDeliverIdx == -1){
                    startDeliverIdx = deliverIdx;
                }
                if(nowDeliver >= deliverCap){
                    deliveries[deliverIdx] = nowDeliver-deliverCap;
                    break;
                }else{
                    deliverCap -= nowDeliver;
                    deliveries[deliverIdx] = 0;
                    deliverIdx--;
                }
            }
            //cap이 다할때까지 collectIdx 내리기 
            while(collectIdx >= 0){
                int nowCollect = pickups[collectIdx];
                if(nowCollect > 0 && startCollectIdx == -1){
                    startCollectIdx = collectIdx;
                }
                if(nowCollect >= collectCap){
                    pickups[collectIdx] = nowCollect-collectCap;
                    break;
                }else{
                    collectCap -= nowCollect;
                    pickups[collectIdx] = 0;
                    collectIdx--;
                }
            }
            //최종 거리는 어디든 먼곳 (idx니까 +1);
            int finalDist = Math.max(startDeliverIdx, startCollectIdx)+1;
            
            //목적지까지의 거리가 0이라면
            if(finalDist == 0){
                break;
            }else{
                // (왔다갔다 idx+1) *2만큼 추가
                answer+= finalDist*2;
            }
        }
        return answer;
    }
}


//------------------------------------------------------------------------------ 2_88 카카오프렌즈 컬러링북
import java.util.*;


class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        ArrayList<Integer> resultList = new ArrayList<>();
        // picture 2중 for문으로 돌면서
        for(int i=0; i<picture.length; i++){
            for(int j=0; j<picture[i].length; j++){
                // 0이 아니라면
                if(picture[i][j] != 0){
                    // bfs로 돌면서 갯수세기
                    // bfs에서 걸렸던 애들은 0으로 변경
                    int result = bfs(j,i,picture);
                    // 결과를 int[]{종류,갯수}로 ArrayList에 추가
                    resultList.add(result);
                }
            }
        }
        //거꾸로 sort
        Collections.sort(resultList,(a,b) -> Integer.compare(b,a));
            
        int[] answer = new int[2];
        answer[0] = resultList.size();
        answer[1] = resultList.get(0);
        return answer;
    }
    int bfs(int x, int y, int[][] map){
        int cnt = 0;
        int type = map[y][x];
        
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        
        LinkedList<int[]> que = new LinkedList<>();
        que.add(new int[]{x,y});
        map[y][x] = 0;
        
        while(!que.isEmpty()){
            int[] now = que.poll();
            cnt++;
            for(int i=0; i<4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                //범위 내라면
                if(0<= ny && ny < map.length &&
                   0<= nx && nx < map[0].length 
                  ){
                    //type과 같다면 추가
                    if(map[ny][nx] == type){
                        map[ny][nx] = 0;
                        que.add(new int[]{nx,ny});
                    }
                }
            }
        }
        
        return cnt;
    }
}

//------------------------------------------------------------------------------ 2_89 단체사진 찍기 (완전탐색 + 구현)
// 완전탐색 8! 40320 충분
import java.util.*;


class Solution {
    ArrayList<ArrayList<String>> condiList = new ArrayList<>();
    String[] members = {"A", "C", "F", "J", "M", "N", "R", "T"};
    public int solution(int n, String[] data) {
        //dfs로 모든 조합 생성
        ArrayList<String> list = new ArrayList<>();
        dfs(list);
        
        //data 파싱
        ArrayList<ArrayList<String>> parsed = parseRule(data);
        
        //돌면서 하나씩 확인
        int answer = 0;
        for(int i=0; i<condiList.size(); i++){
            ArrayList<String> now = condiList.get(i);
            if(checkRule(now, parsed)){
                answer++;
            }
        }
        
        return answer;
    }
    void dfs(ArrayList<String> condi){
        if(condi.size() == 8){
            ArrayList<String> copied = new ArrayList<>();
            copied.addAll(condi);
            condiList.add(copied);
            return;
        }
        for(int i=0; i<8; i++){
            if(condi.indexOf(members[i]) < 0){
                condi.add(members[i]);
                dfs(condi);
                condi.remove(members[i]);
            }
        }
    }
    //의미가 있나...?
    //의미 있음. 10번의 split으로 40320번의 split을 줄여줌
    ArrayList<ArrayList<String>> parseRule(String[] data){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        
        for(int i=0; i<data.length; i++){
            ArrayList<String> tmp = new ArrayList<>();
            String now = data[i];
            String[] splited = now.split("");
            tmp.add(splited[0]);
            tmp.add(splited[2]);
            tmp.add(splited[3]);
            tmp.add(splited[4]);
            //여기서 tmp에 넣고
            result.add(tmp);
        }
        return result;
    }
    boolean checkRule(ArrayList<String> condi, ArrayList<ArrayList<String>> rule){
        //rule 돌면서 확인
        for(int i=0; i<rule.size(); i++){
            ArrayList<String> nowRule = rule.get(i);
            int idx1 = condi.indexOf(nowRule.get(0));
            int idx2 = condi.indexOf(nowRule.get(1));
            String inequality  = nowRule.get(2);
            int haveToDist = Integer.parseInt(nowRule.get(3));  //원하는 거리
            int dist = Math.abs(idx2-idx1)-1;                     //실제 거리
            
            if(inequality.equals(">") && haveToDist >= dist){
                return false;
            }else if(inequality.equals("<") && haveToDist <= dist){
                return false;
            }else if(inequality.equals("=") && haveToDist != dist){
                return false;
            }
        }
        return true;
    }
}