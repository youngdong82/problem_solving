//------------------------------------------------------------------------------ 1_51 실패율
import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] stuckStage = new int[N+1];    //맨 마지막은 다 깬 사람임
        //각 스테이지 도전자 세기
        for(int stage : stages){
            stuckStage[stage-1] += 1;
        }
        
        //실패율 구하기
        double[] tempAnswer = new double[N];
        Stack<Double> stack = new Stack<>();
        List<Double> list = new ArrayList<>();
        for(int i=0; i<N; i++){
            int reachCount = 0;
            for(int j=i; j<stuckStage.length; j++){
                reachCount+= stuckStage[j];
            }
            if(reachCount == 0){
                tempAnswer[i] = 0;
            }else{
                tempAnswer[i] = stuckStage[i] / (double) reachCount;
            }
        }
        
        //정렬
        double[] tempAnswerClone = tempAnswer.clone();
        Arrays.sort(tempAnswerClone);
        //list, stack에 값 넣어주기
        for(double each: tempAnswer){
            list.add(each);
        }
        for(double each: tempAnswerClone){
            stack.push(each);
        }
        //answer 세팅
        for(int i=0; i<N; i++){
            int idx = list.indexOf(stack.pop());
            answer[i] = idx+1;
            list.set(idx,null);
        }
        
        return answer;
    }
}
//------------------------------------------------------------------------------ 1_52 성격 유형 검사하기
import java.util.*;


class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
        map.put("R",0);
        map.put("T",0);
        map.put("C",0);
        map.put("F",0);
        map.put("J",0);
        map.put("M",0);
        map.put("A",0);
        map.put("N",0);

        for(int i=0; i<survey.length; i++){
            String[] twoFactor = survey[i].split("");
            if(choices[i] < 4){
                map.put(twoFactor[0], map.get(twoFactor[0]) + Math.abs(4-choices[i]));
            }else if(choices[i] > 4){
                map.put(twoFactor[1], map.get(twoFactor[1]) + Math.abs(4-choices[i]));
            }
            
        }
        answer += getFactor(map, "R","T");
        answer += getFactor(map, "C","F");
        answer += getFactor(map, "J","M");
        answer += getFactor(map, "A","N");
        
        return answer;
    }
    String getFactor(HashMap<String,Integer> map, String strA, String strB){
        int a = map.get(strA);
        int b = map.get(strB);
        if(a>b){
            return strA;
        }else if(a<b){
            return strB;
        }else{
            //사전순
            char[] charArray = {strA.charAt(0) ,strB.charAt(0)};
            Arrays.sort(charArray);
            return "" + charArray[0];
        }
    }
}

//------------------------------------------------------------------------------ 1_53 개인정보 수집 유효기간
//날짜 계산 진짜 까다롭네... => 전부 일수로 치환해서 계산하면 매우 편함
import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        ArrayList<Integer> answerList = new ArrayList<>();
        //today 나누기
        String[] splitedToday = today.split("\\.");
        int tyear = Integer.parseInt(splitedToday[0]);
        int tmonth = Integer.parseInt(splitedToday[1]);
        int tdate = Integer.parseInt(splitedToday[2]);
        
        //term map 세팅
        HashMap<String, Integer> termMap = new HashMap<>();
        for(String term: terms){
            String[] splitedTerm = term.split(" ");
            termMap.put(splitedTerm[0], Integer.parseInt(splitedTerm[1]));
        }
        
        //개인정보 돌면서 map 조회하며 확인
        for(int i=0; i<privacies.length; i++){
            String[] splitedPrivacy = privacies[i].split(" ");
            String type = splitedPrivacy[1];
            String allDate = splitedPrivacy[0];
            String[] splitedAllDate = allDate.split("\\.");
            int year = Integer.parseInt(splitedAllDate[0]);
            int month = Integer.parseInt(splitedAllDate[1]);
            int date = Integer.parseInt(splitedAllDate[2]);
            
            int monthFromMap = termMap.get(type);
            int[] result = calcDate(year,month,date,monthFromMap);
            System.out.println(Arrays.toString(result));
            //지났는지 확인
            if(tyear > result[0]){ //연도 지남
                answerList.add(i+1);
            }else if(tyear == result[0] && tmonth > result[1]){ //연도 동일 달 지남
                answerList.add(i+1);
            }else if(tyear == result[0] && tmonth == result[1] && tdate > result[2]){   //연도,달 동일   일수 지남
                answerList.add(i+1);
            }
        }
        answer = answerList.stream().mapToInt(e->e).toArray();
        return answer;
    }
    int[] calcDate(int year, int month, int day, int plus){
        int[] result = new int[3];
        int afterPlus = month + plus;
        //plus가 100 이하
        if(afterPlus%12 == 0){
            // 12일경우 24일경우
            // -1을 더하고 12로 남아있어야해
            result[0] = year + afterPlus/12-1;
            result[1] = 12;
        }else{
            result[0] = year + afterPlus/12;
            result[1] = afterPlus%12;
        }
   
        if(day == 1){
            result[1] -= 1;
            result[2] = 28;
        }else{
            result[2] = day-1;
        }
        
        return result;
    }
}
