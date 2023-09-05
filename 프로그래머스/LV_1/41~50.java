//------------------------------------------------------------------------------ 1_41 대충 만든 자판
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        for(int i=0; i<targets.length; i++){
            int eachAnswer = 0;
            for(int j=0; j<targets[i].length(); j++){
                int minIndex = 101;
                
                for(int k=0; k<keymap.length; k++){ 
                    if(keymap[k].indexOf(targets[i].charAt(j)) != -1){
                        minIndex = Math.min(minIndex, keymap[k].indexOf(targets[i].charAt(j)));
                    }
                }
                if(minIndex == 101){
                    eachAnswer = -1;
                    break;
                }else{
                    eachAnswer += minIndex+1;
                }
            }
            answer[i] = eachAnswer;
        }
        return answer;
    }
}

//------------------------------------------------------------------------------ 1_42 둘만의 암호
class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for(String e: skip.split("")){
            alphabet = alphabet.replace(e,"");
        }
        for(String e: s.split("")){
            int afterIndex = (alphabet.indexOf(e) +index) % alphabet.length();
            sb.append(alphabet.charAt(afterIndex));
        }
        answer = sb.toString();
        return answer;
    }
}

//------------------------------------------------------------------------------ 1_43 햄버거 만들기
import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for(int each: ingredient){
            stack.push(each);
            if(stack.size() >=4 && check(stack)){
                stack.pop();
                stack.pop();
                stack.pop();
                stack.pop();
                answer++;
            }
        }
        return answer;
    }
    boolean check(Stack<Integer> stack){
        if( stack.get(stack.size()-4) == 1
        &&  stack.get(stack.size()-3) == 2
        &&  stack.get(stack.size()-2) == 3
        &&  stack.get(stack.size()-1) == 1
        ){
            return true;
        }
        
        return false;
    }
}
//------------------------------------------------------------------------------ 1_44 바탕화면 정리
class Solution {
    public int[] solution(String[] wallpaper) {
        int left = wallpaper[0].length(), top=wallpaper.length,right=-1, floor=-1;
        for(int i=0; i<wallpaper.length; i++){
            
            boolean hightIndex = wallpaper[i].contains("#");
            if(hightIndex){
                if(top == wallpaper.length){
                    top = i;
                    floor = i;
                }else{
                    floor = i;
                }
                
                int leftIndex = wallpaper[i].indexOf("#");
                int rightIndex = wallpaper[i].lastIndexOf("#");
                left = Math.min(left, leftIndex);
                right = Math.max(right, rightIndex);
            }
        }
        int[] answer = {top,left,floor+1,right+1};
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 1_45 달리기 경주
import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        HashMap<String, Integer> hashmap = new HashMap<>();
        for(int i=0; i<players.length; i++){
            hashmap.put(players[i], i);
        }

        for(String name: callings){
            int eachIndex = hashmap.get(name);
            String rightAfter = players[eachIndex - 1];

            players[eachIndex - 1] = name;
            hashmap.put(name, eachIndex - 1);
 
            players[eachIndex] = rightAfter;
            hashmap.put(rightAfter, eachIndex);
        }
        
        return players;
    }
}

//------------------------------------------------------------------------------ 1_46 공원 산책
import java.util.*;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        Map<String,Integer> map = new HashMap<>();
        map.put("N", 0);
        map.put("E", 1);
        map.put("S", 2);
        map.put("W", 3);
        int[] dx = {0,1,0,-1};
        int[] dy = {-1,0,1,0};
        int[] now = new int[2];
        
        //S찾기
        for(int i=0; i<park.length; i++){
            if(park[i].contains("S")){
                now[0] = i;
                now[1] = park[i].indexOf("S");
                break;
            }
        }
        
        for(String order: routes){
            String[] splitedOrder = order.split(" ");
            int direc = map.get(splitedOrder[0]);
            int many = Integer.parseInt(splitedOrder[1]);
            //안갔을 경우 백업 용
            int[] tmpNow = now.clone();
            
            //한칸씩 가보면서
            for(int i=0; i<many; i++){
                int nX = tmpNow[1] + dx[direc];
                int nY = tmpNow[0] + dy[direc];
                //범위확인
                if(0 <= nX 
                && nX <park[0].length() 
                && 0 <= nY 
                && nY <park.length
                && park[nY].charAt(nX) != 'X'
                  ){
                    tmpNow[1] = nX;
                    tmpNow[0] = nY;
                }else{
                    //없던일로
                    System.out.println("no");
                    tmpNow[1] = now[1];
                    tmpNow[0] = now[0];
                    break;
                }
            }
            //통과했다면 현재위치 변경
            if(now[0] != tmpNow[0] || now[1] != tmpNow[1]){
                now[0] = tmpNow[0];
                now[1] = tmpNow[1];
            }
        }

        return now;
    }
}



//------------------------------------------------------------------------------ 1_47 신고 결과 받기
import java.util.*;


class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        //같은사람이 같은사람 여러번 신고 제거
        Set<String> setReport = new HashSet<String>(Arrays.asList(report));
        //신고된사람, 신고한사람 목록
        Map<String, ArrayList<String>> reportMap = new HashMap<>();
        //각 아이디, 신고된 횟수
        Map<String, Integer> reportCountMap = new HashMap<>();
        //초기화
        for(String id: id_list){
            reportMap.put(id, new ArrayList<>());
            reportCountMap.put(id, 0);
        }
        //report 돌면서 추가
        for(String each: setReport){
            String[] splitedreport = each.split(" ");
            String from = splitedreport[0];
            String to = splitedreport[1];
            ArrayList<String> list = reportMap.get(to);
            list.add(from);
            
            reportMap.put(to,list);
            reportCountMap.put(to, reportCountMap.get(to)+1);
            
        }
        //reportCountMap 돌면서 k이상이라면
        Set<String> keyArray = reportCountMap.keySet();
        for(String key: keyArray){
            //신고당한사람이라면
            if(reportCountMap.get(key) >= k){
                //reportMap에서 찾아서 
                ArrayList<String> fromReportList = reportMap.get(key);
                for(String from: fromReportList){
                    answer[Arrays.asList(id_list).indexOf(from)]++;
                }
            }
        }        
        return answer;
    }
}
//------------------------------------------------------------------------------ 1_48 신규 아이디 추천
class Solution {
    public String solution(String new_id) {
        //1.소문자로 치환
        String firstId = new_id.toLowerCase();
        //2.불가능한 문자 제거
        String secondId = firstId.replaceAll("[~!@#$%^&*()=+\\[\\]{}:?,<>/]", "");
        //3.중복.제거
        String thirdId = secondId.replaceAll("\\.+", ".");
        //4.앞뒤.제거 => ㅅㅂ 이게 이리 어려운거여?
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<thirdId.length(); i++){
            if((i == 0 && thirdId.charAt(i) == '.')|| (i == thirdId.length()-1 && thirdId.charAt(i) == '.')){
                continue;
            }else{
                sb.append(thirdId.charAt(i));
            }
        }
        //5. 비어있을경우 a 추가
        //6. 16이상이라면 자르기 + '.'제거
        if(sb.length() == 0){
            sb.append("a");
        }else if(sb.length() >= 16){
            String tmp = sb.substring(0,15);
            sb.setLength(0);
            sb.append(tmp);
            if(sb.charAt(14) == '.'){
                sb.deleteCharAt(14);
            }
        }
        //7.2개 이하라면 3개까지 증식
        if(sb.length() <= 2){
            while(sb.length() < 3){
                sb.append(sb.charAt(sb.length()-1));
            }
        }

        return sb.toString();
    }
}
//------------------------------------------------------------------------------ 1_49 키패드 누르기
import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        String onlyLeft = "147";
        String onlyRight = "369";
        HashMap<String, String> map = new HashMap<>();
        map.put("1","0_0");
        map.put("2","0_1");
        map.put("3","0_2");
        map.put("4","1_0");
        map.put("5","1_1");
        map.put("6","1_2");
        map.put("7","2_0");
        map.put("8","2_1");
        map.put("9","2_2");
        map.put("*","3_0");
        map.put("0","3_1");
        map.put("#","3_2");
        
        String nowLeft = "*";
        String nowRight = "#";
        
        String[] strNumbers = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            strNumbers[i] = Integer.toString(numbers[i]);
        }
        
        for(String number: strNumbers){
            if(onlyLeft.contains(number)){
                nowLeft = number;
                sb.append("L");
            }else if(onlyRight.contains(number)){
                nowRight = number;
                sb.append("R");
            }else{
                int leftDist = calcDist(map, nowLeft, number);
                int rightDist = calcDist(map, nowRight, number);
                if(leftDist < rightDist){
                    nowLeft = number;
                    sb.append("L");
                }else if(leftDist > rightDist){
                    nowRight = number;
                    sb.append("R");
                }else{
                    if(hand.equals("left")){
                        nowLeft = number;
                        sb.append("L");
                    } else{
                        nowRight = number;
                        sb.append("R");
                    }
                }
            }
        }
        
        return sb.toString();
    }
    int calcDist(Map<String,String> map, String from, String to){
        String x1y1 = map.get(from);
        String x2y2 = map.get(to);
        
        String[] splitedX1y1 = x1y1.split("_");
        String[] splitedX2y2 = x2y2.split("_");
        
        int x1 = Integer.parseInt(splitedX1y1[0]);
        int y1 = Integer.parseInt(splitedX1y1[1]);
        int x2 = Integer.parseInt(splitedX2y2[0]);
        int y2 = Integer.parseInt(splitedX2y2[1]);
        
        return Math.abs(x2-x1) + Math.abs(y2-y1) ;
    }
}


//------------------------------------------------------------------------------ 1_50 크레인 인형뽑기 게임
import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for(int move: moves){
            for(int i=0; i<board.length; i++){
                if(board[i][move-1] != 0){
                    int tmp = board[i][move-1];
                    if(stack.size()>0 && stack.peek() == tmp){
                        stack.pop();
                        answer+=2;
                    }else{
                        stack.push(tmp);
                    }
                    board[i][move-1] = 0;
                    break;
                }
            }
        }
        
        return answer;
    }
}