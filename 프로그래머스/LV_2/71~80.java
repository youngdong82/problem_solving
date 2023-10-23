//------------------------------------------------------------------------------ 2_71 [3차] 압축
import java.util.*;

class Solution {
    public int[] solution(String msg) {
        int[] answer = {};
        
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //HashMap에 문자:index로 추가
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<alpha.length(); i++){
            map.put("" + alpha.charAt(i), i+1);
        }
        ArrayList<Integer> list = new ArrayList<>();
        //StringBuilder에 넣고
        StringBuilder sb = new StringBuilder(msg);
        while(sb.length() != 0){
            int idx = 0;
            //현재 인덱스에서
            //하나씩 추가해가면서 map에 있는지 확인
            StringBuilder tmpSb = new StringBuilder();
            for(int i=0; i<sb.length(); i++){
                tmpSb.append(sb.charAt(i));
                if(map.getOrDefault(tmpSb.toString(),0) == 0){
                    //map에 추가
                    map.put(tmpSb.toString(), map.size()+1);
                    idx = tmpSb.length()-1;
                    break;
                }else{
                    idx = tmpSb.length();
                }
            }
            //list에 추가
            tmpSb.setLength(idx);
            list.add(map.get(tmpSb.toString()));
            sb.delete(0, idx);       
        }
        
        return list.stream().mapToInt(e->e).toArray();
    }
}
//------------------------------------------------------------------------------ 2_72 [3차] n진수 게임
class Solution {
    public String solution(int n, int t, int m, int p) {
        //m명이서 참가하는 게임 중 p번째인 사람이 말할 번호 t개를 구하자
        StringBuilder sb = new StringBuilder();
        sb.append("0");
        int idx = 0;
        //반복문 돌면서
        while(sb.length() <= t*m){
            idx++;
            //누구차례인지
            String now = Integer.toString(idx,n);
            sb.append(now.toUpperCase());
        }
        String tmp = sb.toString();
        StringBuilder answerSb = new StringBuilder();
        for(int i=0; i<tmp.length(); i++){
            if((i%m)+1 == p){ //p의 차례라면
                answerSb.append(tmp.charAt(i));
                if(answerSb.length() == t){
                    break;
                }
            }
        }
        //answer를 t개로 끊으면 됨
        return answerSb.toString();
    }
}

//------------------------------------------------------------------------------ 2_73 주차 요금 계산
import java.util.*;


class Solution {
    public int[] solution(int[] fees, String[] records) {
        //records 돌면서
        //HashMap<차량번호, 시간*60_시간*60 String>에 넣고
        //ArrayList에 차량번호 넣기
        HashMap<String, String> map = new HashMap<>();
        for(int i=0; i<records.length; i++){
            String record = records[i];
            String[] splitedRecord = records[i].split(" ");
            String time = splitedRecord[0];
            if(map.getOrDefault(splitedRecord[1],"0") == "0"){
                map.put(splitedRecord[1], time);
            }else{
                map.put(splitedRecord[1], map.get(splitedRecord[1]) + "_" + time);
            }
        }
        ArrayList<String> list = new ArrayList<>();
        //HashMap 돌면서 시간 뒤집어까면서 요금으로 계산
        HashMap<String, Integer> afterCalcMap = new HashMap<>();
        for(String each: map.keySet()){
            list.add(each);
            String timeline = map.get(each);
            int totalTime = convertToTotalTime(timeline);
            int fee = convertTimeToFee(totalTime, fees);
            afterCalcMap.put(each, fee);
        }
        
        //ArrayList 정렬하고 돌면서
        //answer에 각 Hashmap value 넣기
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = afterCalcMap.get(list.get(i));
        }
        
        return answer;
    }
    int convertToTotalTime(String timeline){
        String[] splitedTimeline = timeline.split("_");
        ArrayList<Integer> tmpList = new ArrayList<>();
        for(String e : splitedTimeline){
            String[] splitedE = e.split(":");
            int time = (Integer.parseInt(splitedE[0]) * 60) + Integer.parseInt(splitedE[1]);
            tmpList.add(time);
        }
        //출차시간이 없다면
        if(tmpList.size()%2 != 0){
            tmpList.add(1439);
        }
        int total = 0;
        for(int i=0; i<tmpList.size(); i+=2){
            total += (tmpList.get(i+1) - tmpList.get(i));
        }
        return total;
    }
    int convertTimeToFee(int totalTime, int[] fees){
        if(totalTime <= fees[0]){   //기본 시간 이하
            return fees[1];
        }else{                      //기본 시간 초과
            int afterSub = totalTime - fees[0];
            int plusFee = 0;
            if(afterSub%fees[2] == 0){
                plusFee = (afterSub/fees[2]) * fees[3];
            }else{
                plusFee = ((afterSub/fees[2]) + 1) * fees[3];
            }
            return fees[1] + plusFee;
        }
    }
}

//------------------------------------------------------------------------------ 2_74 오픈채팅방
//HashMap으로 id랑 이름 관리하고
//list에다 Enter || Leave, id만 적어놓고
//마지막에 한번에 Sting으로 바꾸자
import java.util.*;


class Solution {
    public String[] solution(String[] record) {
        ArrayList<String[]> list = new ArrayList<>();
        HashMap<String,String> map = new HashMap<>();
        for(int i=0; i<record.length; i++){
            String nowRecord = record[i];
            String[] splitedRecord = nowRecord.split(" ");
            
            if(splitedRecord[0].equals("Enter")){
                map.put(splitedRecord[1], splitedRecord[2]);
                //list에 추가
                list.add(new String[]{splitedRecord[0], splitedRecord[1]});
            }else if(splitedRecord[0].equals("Change")){
                map.put(splitedRecord[1], splitedRecord[2]);
            }else{
                //list에 추가
                list.add(new String[]{splitedRecord[0], splitedRecord[1]});
            }
        }
        String[] answer = new String[list.size()];
        for(int i=0; i<list.size(); i++){
            String[] nowRecord = list.get(i);
            if(nowRecord[0].equals("Enter")){
                answer[i] = (map.get(nowRecord[1]) + "님이 들어왔습니다.");
            }else{
                answer[i] = (map.get(nowRecord[1]) + "님이 나갔습니다.");
            }
        }
        return answer;
    }
}
//------------------------------------------------------------------------------ 2_75 [3차] 파일명 정렬
//런타임 에러 해결해야 해

import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        ArrayList<Node> list = new ArrayList<>();
        for(int i=0; i<files.length; i++){
            list.add(new Node(files[i],i));
        }
        Collections.sort(list, (a,b) -> {
            //1. head 비교
            //2. 숫자 비교
            //3. 원래 idx
            int compHead = a.getHead().compareTo(b.getHead());
            int compNum = a.getNumber().compareTo(b.getNumber());
            int compIdx = a.getOriIdx().compareTo(b.getOriIdx());
            if(compHead == 0){
                if(compNum == 0){
                    return compIdx;
                }else{
                    return compNum;
                }
            }else{
                return compHead;
            }
        });
        
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i).getOriginValue();
        }
        
        return answer;
    }
    class Node{
        String originValue;
        Integer originIdx;
        String head;
        Integer number;
        String tail;
        Node(){}
        Node(String str, int idx){
            this.originValue = str;
            this.originIdx = idx;
            interpret(str);
        }
        public void interpret (String str){
            int headNumIdx = 0;
            int numTailIdx = 0;
            for(int i=0; i<str.length(); i++){
                if(Character.isDigit(str.charAt(i))){
                    if(headNumIdx == 0){
                        headNumIdx = i;
                    }
                }else{
                    if(headNumIdx != 0 && numTailIdx == 0){
                        numTailIdx = i;
                    }
                }
            }
            //tail이 비어있을 수 있다.
            if(headNumIdx != 0 && numTailIdx != 0){
                this.head = str.substring(0,headNumIdx).toLowerCase();
                this.number = Integer.parseInt(str.substring(headNumIdx,numTailIdx));
                this.tail = str.substring(numTailIdx,str.length());
            }else{
                this.head = str.substring(0,headNumIdx).toLowerCase();
                this.number = Integer.parseInt(str.substring(headNumIdx,str.length()));
            }
            
        }
        String getHead(){
            return head;
        }
        Integer getNumber(){
            return number;
        }
        String getTail(){
            return tail;
        }
        Integer getOriIdx(){
            return originIdx;
        }
        String getOriginValue(){
            return originValue;
        }
    }
}
//------------------------------------------------------------------------------ 2_76 [1차] 프렌즈4블록
import java.util.*;


class Solution {
    //오른쪽으로 돌려서 계산하면 떨어지는거 할때 완전 편할듯...?
    char[][] rotatedBoard;
    Set<String> removeList = new HashSet<>();
    public int solution(int m, int n, String[] board) {
        //m이 y, n이 x
        rotatedBoard = new char[n][m];
        //오른쪽으로 돌리기
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                rotatedBoard[j][m-i-1] = board[i].charAt(j);
            }
        }
        int answer = 0;   
        while(true){
            //하나씩 돌면서 확인
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(rotatedBoard[i][j] != 'X'){  //비어있지 않다면
                        check4box(j,i);
                    }
                }
            }
            if(removeList.size() == 0){
                break;
            }
            answer += removeList.size();
            //없애고 끌어당기고 나머지 빈 곳 'X'로 채우기
            removeSame();
            removeList.clear();
        }
        
        
        return answer;
    }
    void check4box(int nowX, int nowY){
        char nowChar = rotatedBoard[nowY][nowX];
        int[] dx = {0,1,1};
        int[] dy = {1,1,0};
        for(int i=0; i<3; i++){
            int nx = nowX + dx[i];
            int ny = nowY + dy[i];
            if(ny<rotatedBoard.length && nx<rotatedBoard[0].length){
                if(rotatedBoard[ny][nx] != nowChar){
                    return;
                }
            }else{
                return;
            }
        }
        //명단에 추가
        removeList.add(nowX +"_"+ nowY);
        removeList.add((nowX+1) +"_"+ nowY);
        removeList.add(nowX +"_"+ (nowY+1));
        removeList.add((nowX+1) +"_"+ (nowY+1));
    }
    void removeSame(){
        for(String removeOne : removeList){
            String[] splited = removeOne.split("_");
            rotatedBoard[Integer.parseInt(splited[1])][Integer.parseInt(splited[0])] = 'X';
        }
        for(int i=0; i<rotatedBoard.length; i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<rotatedBoard[i].length; j++){
                if(rotatedBoard[i][j] != 'X'){
                    sb.append(rotatedBoard[i][j]);
                }
            }
            while(sb.length() < rotatedBoard[i].length){
                sb.append('X');
            }
            
            rotatedBoard[i] = sb.toString().toCharArray();
        }
    }
}

//------------------------------------------------------------------------------ 2_77 메뉴 리뉴얼
import java.util.*;


class Solution {
    HashMap<String, Integer> map = new HashMap<>();
    public String[] solution(String[] orders, int[] course) {
        for(int i=0; i<orders.length; i++){
            for(int j=0; j<course.length; j++){
                getCandi(course[j], "", orders[i]);
            }
        }
        HashMap<String, Integer> refiendMap = new HashMap<>();
        for(String key: map.keySet()){
            if(map.get(key) > 1){
                refiendMap.put(key, map.get(key));
            }
        }
        // System.out.println(refiendMap);
        
        ArrayList<String> answerList = new ArrayList<>();
        int[] maxArr = new int[course[course.length-1]+1];

        for(String key: refiendMap.keySet()){
            maxArr[key.length()] = Math.max(maxArr[key.length()], refiendMap.get(key));
        }
        for(String key: refiendMap.keySet()){
            if(refiendMap.get(key) == maxArr[key.length()]){
                answerList.add(key);
            }
        }
        Collections.sort(answerList);
        String[] answer = new String[answerList.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
    void getCandi(int target, String now, String order){
        if(now.length() == target){
            map.put(now, map.getOrDefault(now,0) +1); // 추가
            return;
        }
        for(int i=0; i<order.length(); i++){
            if(now.indexOf(order.charAt(i)) == -1){
              //now의 마지막 char이 order.charAt(i)보다 작을경우
                if(now.length() >= 1){
                    if(now.charAt(now.length()-1) < order.charAt(i)){
                        getCandi(target, now+ order.charAt(i), order);
                    }
                }else{
                    getCandi(target, now+ order.charAt(i), order);
                }
            }
        }
        
    }
}

//------------------------------------------------------------------------------ 2_78 [3차] 방금그곡
import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        String convertedM = convertHashToLowercase(m);
        ArrayList<Music> list = new ArrayList<>();
        for(int i=0; i<musicinfos.length; i++){
            list.add(new Music(musicinfos[i], i));
        }
        ArrayList<Music> answerList = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            String nowRealMel = list.get(i).getRealMelody();
            if(nowRealMel.contains(convertedM)){
                answerList.add(list.get(i));
            }
            
        }
        if(answerList.size() == 0){
            return "(None)";
        }
        Collections.sort(answerList, (a,b) -> {
            int runningTime = Integer.compare(b.getRealMelody().length(), a.getRealMelody().length());
            int idx = Integer.compare(a.getIdx(), b.getIdx());
            if(runningTime == 0){
                return idx;
            }else{
                return runningTime;
            }
        });
        
        return answerList.get(0).getTitle();
    }
    String convertHashToLowercase(String melody){
        String afterC = melody.replaceAll("C#", "c");
        String afterD = afterC.replaceAll("D#", "d");
        String afterF = afterD.replaceAll("F#", "f");
        String afterG = afterF.replaceAll("G#", "g");
        String afterA = afterG.replaceAll("A#", "a");
        return afterA;
    }
    class Music{
        int idx;
        int start;
        int end;
        String title;
        String melody;
        String realMelody;
        
        
        Music(){}
        Music(String str, int idx){
            this.idx = idx;
            String[] splitedStr = str.split(",");
            this.start = convertToMin(splitedStr[0]);
            this.end = convertToMin(splitedStr[1]);
            this.title = splitedStr[2];
            this.melody = convertHashToLowercase(splitedStr[3]);
            this.realMelody = convertToRealMelody();
        }
        private int convertToMin(String time){
            String[] splitedTime = time.split(":");
            return (Integer.parseInt(splitedTime[0]) * 60) + Integer.parseInt(splitedTime[1]);           
        }
        private String convertToRealMelody(){
            StringBuilder sb = new StringBuilder();
            
            for(int i=0; i<end-start; i++){
                sb.append(melody.charAt(i%melody.length()));
            }
            return sb.toString();
        }
        public String getRealMelody(){
            return realMelody;
        }
        public int getIdx(){
            return idx;
        }
        public String getTitle(){
            return title;
        }
    }
}
//------------------------------------------------------------------------------ 2_79 문자열 압축
import java.util.*;


class Solution {
    public int solution(String s) {
        int answer = s.length();
        for(int i=1; i<(s.length())/2 + 1; i++){
            answer = Math.min(answer, compress(s, i));
        }
        
        return answer;
    }
    int compress(String target, int many){
        ArrayList<String> list = new ArrayList<>();
        for(int i=0; i<target.length(); i+=many){
            if(i+many >= target.length()){
                list.add(target.substring(i));
            }else{
                list.add(target.substring(i,i+many));
            }
            
        }
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        //list 돌면서
        for(int i=0; i<list.size(); i++){
            if(i == list.size()-1){     //마지막이라면 (비어있을 수 없음);
                if(stack.peek().equals(list.get(i))){   //같다면
                    sb.append(Integer.toString(stack.size()+1));
                    sb.append(list.get(i));
                    break;
                }else{                                  //다르다면
                    //sb에 Stack.size() 추가 후 
                    if(stack.size() > 1){   //1일땐 생략
                        sb.append(Integer.toString(stack.size()));
                    }
                    //값 추가 후
                    sb.append(stack.peek());
                    sb.append(list.get(i));
                    break;
                }
            }
            //Stack이 비었거나(처음일때, 이후는 빌 수 없음)
            //가장 바깥이 같으면 쌓고
            if(stack.isEmpty() || stack.peek().equals(list.get(i))){
                stack.push(list.get(i));
            }else{      //다르면
                //sb에 Stack.size() 추가 후 
                if(stack.size() > 1){   //1일땐 생략
                    sb.append(Integer.toString(stack.size()));
                }
                //값 추가 후
                sb.append(stack.peek());
                stack.clear();
                stack.push(list.get(i));
            }
        }
        return sb.length();
    }
}

//------------------------------------------------------------------------------ 2_80 거리두기 확인하기
import java.util.*;


class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for(int i=0; i<places.length; i++){
            String[] room = places[i];
            if(isOk(room)){
                answer[i] = 1;
            }else{
                answer[i] = 0;
            }
        }
        return answer;
    }
    boolean isOk(String[] room){
        ArrayList<int[]> pList = new ArrayList<>();
        //2차 반복문 돌면서
        //p list구하고
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(room[i].charAt(j) == 'P'){
                    pList.add(new int[]{j,i});
                }
            }
        }
        for(int i=0; i<pList.size()-1; i++){
            for(int j=i+1; j<pList.size(); j++){
                //맨해튼 거리 2이하 라면
                if(getDist(pList.get(i), pList.get(j)) <= 2){ 
                    //실제 확인
                    if(!checkWithRoom(pList.get(i), pList.get(j), room)){
                        return false;
                    }
                }
            }
        }        
        return true;
    }
    int getDist(int[] a, int[] b){
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
    boolean checkWithRoom(int[] start, int[] end, String[] room){
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        
        LinkedList<String> que = new LinkedList<>();
        que.add(start[0] + "_" + start[1] + "_" +0);
        while(!que.isEmpty()){
            String now = que.poll();
            String[] splNow = now.split("_");
            if(Integer.parseInt(splNow[0]) == end[0] && Integer.parseInt(splNow[1]) == end[1]){ //end를 만난다면
                return false;
            }
            if(Integer.parseInt(splNow[2]) == 2){ //거리가 2라면 
                continue;
            }
            for(int i=0; i<4; i++){
                int nx = Integer.parseInt(splNow[0]) + dx[i];
                int ny = Integer.parseInt(splNow[1]) + dy[i];
                if(0<= nx && nx < 5 && 0<= ny && ny < 5){//범위 내라면
                    if(room[ny].charAt(nx) != 'X'){    //파티션이 아니라면
                        que.add(nx +"_"+ ny +"_"+ (Integer.parseInt(splNow[2]) + 1));
                    }
                }
            }
        }
        return true;
    }
}
