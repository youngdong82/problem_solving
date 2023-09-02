//------------------------------------------------------------------------------ 1_31 삼총사 / 크기가 작은 부분 문자열
class Solution {
    public int solution(int[] number) {
        int answer = 0;
        for(int i=0; i<number.length-2; i++){
            for(int j=i+1; j<number.length-1; j++){
                for(int k=j+1; k<number.length; k++){
                    if(number[i]+number[j]+number[k] == 0){
                        answer ++;
                    }
                }
            }
        }
        return answer;
    }
}

class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        int pLen = p.length();
        
        for(int i=0; i<t.length()-pLen+1; i++){
            long cuttedInt = Long.parseLong(t.substring(i, i+pLen));
            if(cuttedInt <= Long.parseLong(p)){
                answer ++;
            }
        }
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 1_32 가장 가까운 같은 글자 / 푸드 파이트 대회
import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        Arrays.fill(answer, -1);
        
        String[] splitedS = s.split("");
        for(int i=0; i<splitedS.length; i++){
            int moveCount = 0;
            for(int j= i-1; j>=0; j--){
                moveCount ++;
                if(splitedS[i].equals(splitedS[j])){
                    answer[i] = moveCount;
                    break;
                }
            }
        }
        return answer;
    }
}

class Solution {
    public String solution(int[] food) {
        String answer = "0";
        //욕심 같아선 더블링크드 리스트 써보고 싶은디...
        for(int i=food.length-1; i>0; i--){
            for(int j=0; j<food[i]/2; j++){
                answer = i + answer + i;
            }
        }
        return answer;
    }
}
//------------------------------------------------------------------------------ 1_33 콜라 문제 / 추억 점수
class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        while(n>=a){
            answer +=  ((n/a)*b);
            n = (n - (a*(n/a)) + (n/a)*b);
        }

        return answer;
    }
}

import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];

        HashMap<String, Integer> yearnMap = new HashMap<>();
        for(int i=0; i<name.length; i++){
            yearnMap.put(name[i], yearning[i]);
        }
        for(int i=0; i<photo.length; i++){
            for(String eachName: photo[i]){
                answer[i] += yearnMap.getOrDefault(eachName,0);
            }
        }
        
        return answer;
    }
}


//------------------------------------------------------------------------------ 1_34 명예의 전당 (1) / 카드 뭉치
import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        ArrayList<Integer> allstars = new ArrayList<>();
        
        for(int i=0; i<score.length; i++){
            if(allstars.size() < k){
                allstars.add(score[i]);
            }else{
                if(allstars.get(allstars.size()-1) < score[i]){
                    allstars.remove(allstars.size()-1);
                    allstars.add(score[i]);
                }
            }

            allstars.sort(Comparator.reverseOrder());
            answer[i] = allstars.get(allstars.size()-1);
        }
        return answer;
    }
}

import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        
        for(int i=0; i<score.length; i++){
            if(priorityQueue.size() < k){
                priorityQueue.add(score[i]);
            }else{
                if(priorityQueue.peek() < score[i]){
                    priorityQueue.poll();
                    priorityQueue.add(score[i]);
                }
            }
            answer[i] = priorityQueue.peek();
        }
        return answer;
    }
}

import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        List<String> goalList = Arrays.asList(goal);
        
        int cards1PreviousIndex = 0;
        for(int i=0; i<cards1.length; i++){
            if(goalList.indexOf(cards1[i]) == -1){
                cards1PreviousIndex = 10;
            }else if(goalList.indexOf(cards1[i]) <cards1PreviousIndex){
                answer = "No";
                return answer;
            }else{
                cards1PreviousIndex = goalList.indexOf(cards1[i]);
            }
        }
        int cards2PreviousIndex = 0;
        for(int i=0; i<cards2.length; i++){
            if(goalList.indexOf(cards2[i]) == -1){
                cards2PreviousIndex = 10;
            }else if(goalList.indexOf(cards2[i]) <cards2PreviousIndex){
                answer = "No";
                return answer;
            }else{
                cards2PreviousIndex = goalList.indexOf(cards2[i]);
            }
        }
        return answer;
    }
}


//------------------------------------------------------------------------------ 1_35 과일 장수
import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        Arrays.sort(score);
        //최소 갯수의 배수만큼 자르기
        int minLen = (score.length / m) *m;
        int[] cuttedScore = Arrays.copyOfRange(score, score.length-minLen, score.length);

        for(int i=minLen-m; i>=0; i-=m){
            answer += (cuttedScore[i]*m);
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 1_36 덧칠하기
import java.util.*;

class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        //전체 벽에서 필요없는 부분 없앤 miniWall 초기화
        int[] minWall = new int[section[section.length-1]+1];
        Arrays.fill(minWall, 1);
        for(int e: section){
            minWall[e] = -1;
        }
        
        for(int i=1; i<minWall.length; i++){
            if(minWall[i] == -1){
            //칠해야하는 구역이라면 
                paint(minWall, i, m);   //기본 배열에 영향 줌
                answer ++;
            }
        }
        return answer;        
    }
    void paint(int[] target, int start, int paintLen){
        for(int i=0; i<paintLen; i++){
            if(start +i < target.length){
                target[start +i] = 2;
            }
        }
    }
}
//------------------------------------------------------------------------------ 1_37 기사단원의 무기
import java.util.*;

class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;        
        for(int i=1; i<=number; i++){
            int count = getdivisorCount(i);

            if(count > limit){
                answer += power;
            }else{
                answer += count;
            }
        }
        return answer;
    }
    int getdivisorCount(int target){
        int count = 0;
        if(target == 1){
            return 1;
        }

        for(int i=1; i<Math.ceil(Math.sqrt(target)); i++){
            if(target%i == 0){
                count +=2;
            }
        }
        if((int)(Math.sqrt(target)) == Math.sqrt(target)){
            count++;
        }
        
        return count;
    }
}

//------------------------------------------------------------------------------ 1_38 옹알이 (2)
class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] canSay = {"aya", "ye", "woo", "ma"};
        
        for(String bab: babbling){
            String target = bab;
            for(int i=0; i<4; i++){
                target = target.replace(canSay[i],i+"");
            }
            if(checkCanSay(target)){
                answer ++;
            }            
        }
        return answer;
    }
    boolean checkCanSay(String target){
        try{
            Integer.parseInt(target);
            String[] splitedTarget = target.split("");
            
            String before = splitedTarget[0];
            for(int i=1; i<splitedTarget.length; i++){
                if(splitedTarget[i].equals(before)){
                    return false;
                }else{
                    before = splitedTarget[i];
                }
            }
        }catch(Exception e){
            return false;
        }
        return true;
    }
}

class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] canSay = {"aya", "ye", "woo", "ma"};
        
        for(String bab: babbling){
            String target = bab;
            if(target.contains("ayaaya") 
            || target.contains("yeye") 
            || target.contains("woowoo") 
            || target.contains("mama")
            ){
                continue;
            }
            for(int i=0; i<4; i++){
                target = target.replace(canSay[i],i+"");
            }
            if(checkCanSay(target)){
                answer ++;
            }            
        }
        return answer;
    }
    boolean checkCanSay(String target){
        try{
            Integer.parseInt(target);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
//------------------------------------------------------------------------------ 1_39 숫자 짝꿍 => 훌륭한 문제!!!!
import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        String answer = "";
        HashMap<String, Long> tank = new HashMap<>();
        for(String eachX: X.split("")){
            tank.put(eachX,(long)tank.getOrDefault(eachX,(long)0)+1);
        }
        for(String eachY: Y.split("")){
            if(tank.get(eachY) != null && tank.get(eachY) != (long)0){
                answer += eachY;
                tank.put(eachY, (long) (tank.get(eachY)-(long)1));
            }
        }
        
        if(answer.length() == 0){
            answer = "-1";
        }else{
            //내림차순 정렬
            String[] splitedAnswer = answer.split("");
            Arrays.sort(splitedAnswer, Comparator.reverseOrder());
            if(splitedAnswer[0].equals("0")){
                answer = "0";
            }else{
                answer = String.join("",splitedAnswer);   
            }
        }
        return answer;
    }
}



import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(String X, String Y) {
        String answer = "";
        ArrayList<String> ansList = new ArrayList<>();
        HashMap<String, Integer> tank = new HashMap<>();
        for(String eachX: X.split("")){
            tank.put(eachX, tank.getOrDefault(eachX,0)+1);
        }
        for(String eachY: Y.split("")){
            if(tank.get(eachY) != null && tank.get(eachY) != 0){
                ansList.add(eachY);
                tank.put(eachY, (tank.get(eachY)-1));
            }
        }
        //내림차순 정렬
        answer = ansList.stream()
            .sorted(Collections.reverseOrder())
            .collect(Collectors.joining());
        
        if(answer.length() == 0) answer = "-1";
        if(answer.charAt(0) == '0') answer = "0";
        return answer;
    }
}
//------------------------------------------------------------------------------ 1_40 문자열 나누기
class Solution {
    public int solution(String s) {
        int answer = 0;
        String[] splitedS = s.split("");
        int xCount = 0;
        int notXCount = 0;
        String x = "";
        for(int i=0; i<splitedS.length; i++){
            if(x.equals("")){
                x = splitedS[i];
                xCount++;
            }else if(splitedS[i].equals(x)){
                xCount++;
            }else{
                notXCount++;
            }
            if(xCount == notXCount){
                System.out.println(x);
                answer ++;
                x = "";
            }
        }
        if(!x.equals("")) answer ++;
        return answer;
    }
}
