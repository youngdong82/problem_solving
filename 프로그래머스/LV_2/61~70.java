//------------------------------------------------------------------------------ 2_61   교점에 별 만들기 => 런타임 에러 뜨는데 이유 좀... 
import java.util.*;


class Solution {
    public String[] solution(int[][] line) {
        ArrayList<long[]> vertaxList = new ArrayList<>();    //자동 중복제거
        for(int i=0; i<line.length-1; i++){
            for(int j=i+1; j<line.length; j++){
                // System.out.println(i+":"+j);
                 long[] vertax = getVertax(line[i], line[j]);
                if(vertax[0] != 100001 && vertax[0] != 100001){ //둘 다 정수일 경우
                    // System.out.println(Arrays.toString(vertax));
                    vertaxList.add(vertax);
                }
            }
        }
        
        // //최대 x,y 최소 x,y 구하기 => 나중에 합치기 가능
        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;
        for(int i=0; i<vertaxList.size(); i++){
            long[] now = vertaxList.get(i);
            if(now[0] < minX){
                minX = now[0];
            }
            if(now[0] > maxX){
                maxX = now[0];
            }
            if(now[1] < minY){
                minY = now[1];
            }
            if(now[1] > maxY){
                maxY = now[1];
            }
        }
        // answerChar 세팅  = 최대 1000
        int answerLenY = (int)(maxY-minY+1);
        int answerLenX = (int)(maxX-minX+1);
        char[][] answerChar = new char[Math.abs(answerLenY)][Math.abs(answerLenX)];
        for(int i=0; i<answerLenY; i++){
            for(int j=0; j<answerLenX; j++){
                answerChar[i][j] = '.';
            }
        }
        //vertaxList 돌면서 별로 바꾸기
        for(int i=0; i<vertaxList.size(); i++){
            long[] now = vertaxList.get(i);
            answerChar[(int)(now[1]-minY)][(int)(now[0]-minX)] = '*';
        }
        //String[]으로 변환 후 리턴
        String[] answer = new String[answerLenY];
        for(int i=0; i<answerLenY; i++){
            answer[answerLenY-i-1] = new String(answerChar[i]);
        }
        
        return answer;
    }
    long[] getVertax(int[] line1, int[] line2){
        int a = line1[0];
        int b = line1[1];
        int c = line1[2];
        int d = line2[0];
        int e = line2[1];
        int f = line2[2];
        long tempX1 = (long) (b*f) - (long) (c*e);
        long tempX2 = (long) (a*e) - (long) (b*d);
        if(tempX2 == 0 || tempX1 % tempX2 != 0){    //정수 아님
            return new long[]{100001,100001};
        }
        long g = tempX1/tempX2;
        long tempY = -1 * (long) ((long)(a*g)+c);
        if(b == 0 || tempY%b != 0){            //정수 아님
            return new long[]{100001,100001};
        }
        long h = tempY/b;
        return new long[]{g,h};
    }
}
//------------------------------------------------------------------------------ 2_62   혼자서 하는 틱택토 (구현...?)
class Solution {
    public int solution(String[] board) {
        //갯수확인
        int oCnt = 0;
        int xCnt = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i].charAt(j) == 'O'){
                    oCnt++;
                }else if(board[i].charAt(j) == 'X'){
                    xCnt++;
                }
            }
        }
        if(oCnt < xCnt || oCnt-xCnt >= 2){
            return 0;
        }
        //세트 확인
        boolean result = checkSetCnt(board, oCnt, xCnt);
        if(!result){
            return 0;
        }else{
            return 1;
        }
    }
    boolean checkSetCnt(String[] board, int oCnt, int xCnt){
        int[] verti = checkVerti(board);
        if(verti[0] + verti[1] > 1){    //2개 이상이면 안됨
            return false;
        }
        int[] horiz = checkHoriz(board);//2개 이상이면 안됨
        if(horiz[0] + horiz[1] > 1){
            return false;
        }
        //선공의 직각 성공
        if(verti[0] + horiz[0] == 2){
            return true;
        }

        int[] diag = checkDiag(board);
        if(diag[0] > 1){      // 선공이 2개 이상이면 됨
            return true;
        }
        //선공의 7자일 경우 가능
        if(verti[0] + diag[0] == 2 ||
           horiz[0] + diag[0] == 2 
        ){
           return true;
        }
        //x 세트가 1개인데 선공이 계속 둔 경우
        if(verti[1] + horiz[1] + diag[1] == 1 &&  oCnt > xCnt){
            return false;
        }
        //o 세트가 1개인데 후공이 계속 둔 경우
        if(verti[0] + horiz[0] + diag[0] == 1 &&  oCnt == xCnt){
            return false;
        }
        return true;
    }
    int[] checkVerti(String[] board){
        int oVerti = 0;
        int xVerti = 0;
        for(int i=0; i<3; i++){
            if(board[0].charAt(i) == board[1].charAt(i) &&
               board[1].charAt(i) == board[2].charAt(i)
              ){
                if(board[0].charAt(i) == 'O'){
                    oVerti++;
                }else if(board[0].charAt(i) == 'X'){
                    xVerti++;
                }
            }
        }
        return new int[]{oVerti, xVerti};
    }
    int[] checkHoriz(String[] board){
        int oHoriz = 0;
        int xHoriz = 0;
        for(int i=0; i<3; i++){
            if(board[i].equals("OOO")){
                oHoriz++;
            }else if(board[i].equals("XXX")){
                xHoriz++;
            }
        }
        return new int[]{oHoriz, xHoriz};
    }
    int[] checkDiag(String[] board){
        int oDiag = 0;
        int xDiag = 0;
        
        if(board[0].charAt(0) == board[1].charAt(1)&&
           board[1].charAt(1) == board[2].charAt(2)
        ){
            if(board[0].charAt(0) == 'O'){
                oDiag++;
            }else if(board[0].charAt(0) == 'X'){
                xDiag++;
            }
        }
        if(board[0].charAt(2) == board[1].charAt(1)&&
           board[1].charAt(1) == board[2].charAt(0)
        ){
            if(board[0].charAt(2) == 'O'){
                oDiag++;
            }else if(board[0].charAt(2) == 'X'){
                xDiag++;
            }
        }
        return new int[]{oDiag, xDiag};
    }
}
//------------------------------------------------------------------------------ 2_63   요격 시스템 (어렵다...)
// => 어떻게 풀어야하는지 감이 아예 안잡혔음...
import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (a,b) -> Integer.compare(a[1],b[1]));
        
        int last = -1;                  //미사일 위치
        for (int[] target : targets) {
            if (last == -1) {   //처음이라면
                answer++;
                last = target[1] - 1;
                continue;
            }
            //last가 target 사이에 있으면
            if (last >= target[0] && last <= target[1]) {   
                continue;
            }
            
            answer++;
            last = target[1] - 1;
        }

        return answer;
    }
}
//------------------------------------------------------------------------------ 2_64 하노이의 탑
import java.util.*;

class Solution {
    ArrayList<int[]> braodAnswer = new ArrayList<>();
    public int[][] solution(int n) {
        
        hanoi(n,1,3,2);
        int[][] answer = new int[braodAnswer.size()][2];
        for(int i=0; i<answer.length; i++){
            answer[i] = braodAnswer.get(i);
        }
        return answer;
    }
    void hanoi(int N, int from ,int to, int through){
        if(N == 1){
            move(1, from, to);
        }else{
            hanoi(N-1, from,through,to);
            move(N, from, to);
            hanoi(N-1, through,to,from);
        }
    }
    void move(int N, int from, int to){
        braodAnswer.add(new int[]{from, to});
    }
}
//------------------------------------------------------------------------------ 2_65 N-Queen => 답을 봐도 모르겠넼ㅋㅋㅋ
import java.util.*;


class Solution {
    int[] map;
    int cnt = 0;    //끝까지 왔을 경우
    public int solution(int n) {
        map = new int[n];
        dfs(0);
        return cnt;
    }
    void dfs(int depth){
        System.out.println(Arrays.toString(map));
        //한줄씩 내려가면서
        if(depth == map.length){
            cnt++;  //끝까지 왔으니까 추가
            System.out.println(Arrays.toString(map));
            return;
        }
        // 한 컬럼씩 확인 옮겨가면서
        for(int i=0; i<map.length; i++){
            map[depth] = i;
            if(isOkay(depth)){   //안죽는다면
                dfs(depth+1);
            }
        }
    }
    
    boolean isOkay(int depth){
        //0번째부터 현재 depth까지 확인
        for(int i=0; i<depth; i++){
            //가로세로 확인
            //값이 다르다 = 다른 컬럼
            if(map[i] == map[depth]){
                return false;
            }
            //대각선 확인
            //열의 차와 행의 차가 같을 경우
            if(Math.abs(depth - i) == Math.abs(map[depth] - map[i])){
                return false;
            }
        }
        return true;
    }
}

//------------------------------------------------------------------------------ 2_66 3 x n 타일링
// 2- 3                 = 3
// 4- dp[i-2]*3 + dp[i-4]*2 + 2 = 11
// 6- dp[i-2]*3 + dp[i-4]*2 + 2 = 33+6+2 = 41
// 8- dp[i-2]*3 + dp[i-4]*2 + dp[i-6]*2 + 2 = 123+22+6+2 = 153

import java.util.*;


class Solution {
    public int solution(int n) {
        long[] dp = new long[n+1];
        dp[0] = 0;
        dp[2] = 3;
        for(int i=4; i<dp.length; i+=2){
            long now = 0;
            for(int j=2; j<i; j+=2){
                if(j== 2){
                    now += dp[i-j] * 3;
                }else{
                    now += dp[i-j] * 2;
                }
            }
            now += 2;
            dp[i] = now % 1000000007;
        }
        return (int) dp[n];
    }
}
//------------------------------------------------------------------------------ 2_67 [1차] 캐시 => 적절한 자료구조
import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if(cacheSize == 0){
            return 5 * cities.length;
        }
        LinkedList<String> que = new LinkedList<>();
        HashMap<String, Boolean> map = new HashMap<>();
        for(String city: cities){
            city = city.toUpperCase();
            //que에 있니?
            if(map.getOrDefault(city, false) == true){
                //있어
                answer++;
                //que에 city 끌어올려, map은 건드릴 필요 없음
                que.remove(city);
                que.add(city);
            }else{
                //없어    
                answer += 5;
                if(map.size() == cacheSize){    // que 갯수확인
                    // 꽉찼으면
                    //que,map에 city 밀어넣기
                    String popedCity = que.poll();
                    que.add(city);
                    map.remove(popedCity);
                    map.put(city,true);
                }else{
                    // 빈자리 남았다면
                    //que,map에 추가
                    map.put(city,true);
                    que.add(city);
                }
            }   
        }
        return answer;
    }
}
//------------------------------------------------------------------------------ 2_68 튜플
import java.util.*;

class Solution {
    public int[] solution(String s) {
        //string을 ArrayList로 변경
        ArrayList<ArrayList<Integer>> result = convertToIntArr(s);
        //각 arr를 갯수dp 따라 sort
        Collections.sort(result, (a,b) -> Integer.compare(a.size(), b.size()));
        ArrayList<Integer> answer = new ArrayList<>();
        for(int i=0; i<result.size(); i++){
            ArrayList<Integer> now = result.get(i);
            for(int j=0; j<now.size(); j++){
                if(answer.indexOf(now.get(j)) < 0){ //  없다면
                    answer.add(now.get(j));
                }
            }
        }
        
        return answer.stream().mapToInt(e->e).toArray();
    }
    ArrayList<ArrayList<Integer>> convertToIntArr (String target){ 
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> tmpResult = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<target.length()-1; i++){
            char now = target.charAt(i);
            if(now == '{'){
                continue;
            }else if(now == '}'){
                //마지막 숫자 넣어주기
                tmpResult.add(Integer.parseInt(sb.toString()));
                sb.setLength(0); 
            }else if(now == ','){
                if(target.charAt(i-1) != '}'){
                    //주변이 숫자일 경우
                    tmpResult.add(Integer.parseInt(sb.toString()));
                    sb.setLength(0); 
                }else if(target.charAt(i-1) == '}'){
                    //주변이 }{ 일경우
                    result.add((ArrayList<Integer>) tmpResult.clone());  //  참조 문제 해결필요
                    tmpResult.clear();
                }
            }else{
                //숫자일 경우
                sb.append(now);
            }
            if(i == target.length()-2){
                result.add((ArrayList<Integer>)tmpResult.clone());  //  참조 문제 해결필요
                tmpResult.clear();
            }
        }
        return result;
    }
}
//------------------------------------------------------------------------------ 2_69 [1차] 뉴스 클러스터링
import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        HashMap<String, Integer> str1Arr = devideStr(str1);
        HashMap<String, Integer> str2Arr = devideStr(str2);
        
        return getJacad(str1Arr, str2Arr);
    }
    HashMap<String, Integer> devideStr(String target){
        HashMap<String, Integer> result = new HashMap<>();
        for(int i=0; i<target.length()-1; i++){
            if(Character.isLetter(target.charAt(i)) && Character.isLetter(target.charAt(i+1)) ){
                String now = "" + target.charAt(i) + target.charAt(i+1);
                String lowNow = now.toLowerCase();
                result.put(lowNow, result.getOrDefault(lowNow, 0) + 1);
            }
        }
        return result;
    }
    int getJacad(HashMap<String, Integer> map1, HashMap<String, Integer> map2){
        int and = 0;
        //교집합
        for(String key: map1.keySet()){
            int min = Math.min(map1.get(key), map2.getOrDefault(key,0));
            if(min > 0){
                and+= min;
            }
        }
        int or = 0;
        //합집합
        Set<String> keyset = new HashSet<>();
        for(String key: map1.keySet()){
            keyset.add(key);
        }
        for(String key: map2.keySet()){
            keyset.add(key);
        }
        for(String key: keyset){
            int max = Math.max(map1.getOrDefault(key,0), map2.getOrDefault(key,0));
            if(max > 0){
                or+= max;
            }
        }
        if(or == 0){
            return 65536;
        }
        return and*65536 /or;
    }
}
//------------------------------------------------------------------------------ 2_70 k진수에서 소수 개수 구하기
import java.util.*;


class Solution {
    public int solution(int n, int k) {
        int answer = -1;
        //진수변환
        String afterConvert = Integer.toString(n, k);
        String[] splited = afterConvert.split("0");
        System.out.println(Arrays.toString(splited));
        //소수찾기
        
        return findPrime(afterConvert);
    }
    int findPrime(String target){
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<target.length(); i++){
            if(i == target.length()-1){
                if(target.charAt(i) == '0'){
                    if(sb.length() >0){
                        list.add(sb.toString());
                        sb.setLength(0);
                    }
                }else{
                    sb.append(target.charAt(i));
                    list.add(sb.toString());
                    sb.setLength(0);
                }  
            }else{
                if(target.charAt(i) == '0'){
                    if(sb.length() >0){
                        list.add(sb.toString());
                        sb.setLength(0);
                    }
                }else{
                    sb.append(target.charAt(i));
                }
            }
        }
        System.out.println(list);
        int cnt = 0;
        for(String each: list){
            if(isPrime(Long.parseLong(each))){
                cnt++;
            }
        }
        return cnt;
    }
    boolean isPrime(Long target){
        if(target < 2){
            return false;
        }
        for(int i=2; i< (int)(Math.sqrt(target)+1); i++){
            if(target % i == 0){
                return false;
            }
        }
        return true;
    }
}