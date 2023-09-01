//------------------------------------------------------------------------------ 1_21 두 개 뽑아서 더하기
import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = {};
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<numbers.length; i++){
            for(int j=i+1; j<numbers.length; j++){
                set.add(numbers[i] + numbers[j]);
            }    
        }
        for (int e : set) {
            System.out.println(e);
        }
        answer = set.stream().sorted().mapToInt(e->e).toArray();
        return answer;
    }
}

//------------------------------------------------------------------------------ 1_22 폰켓몬
import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int canTake = nums.length/2;
        Set<Integer> set = new HashSet<>();
        for(int num: nums){
            set.add(num);
        }
        answer = Math.min(set.size(), canTake);
        return answer;
    }
}

//------------------------------------------------------------------------------ 1_23 문자열 내 마음대로 정렬하기
import java.util.*;

class Solution {
  public String[] solution(String[] strings, int n) {
      Arrays.sort(strings, new Comparator<String>(){
          @Override
          public int compare(String s1, String s2){
              if(s1.charAt(n) > s2.charAt(n)) return 1;
              else if(s1.charAt(n) == s2.charAt(n)) return s1.compareTo(s2);
              else if(s1.charAt(n) < s2.charAt(n)) return -1;
              else return 0;
          }
      });
      return strings;
  }
}

//------------------------------------------------------------------------------ 1_24 모의고사 (완전탐색)
import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] stu1 = {1,2,3,4,5};
        int[] stu2 = {2,1,2,3,2,4,2,5};
        int[] stu3 = {3,3,1,1,2,2,4,4,5,5};
        
        int stu1correct = 0;
        int stu2correct = 0;
        int stu3correct = 0;
        
        for(int i=0; i<answers.length; i++){
            int thisAns = answers[i];
            if(stu1[i%5] == thisAns) stu1correct += 1;
            if(stu2[i%8] == thisAns) stu2correct += 1;
            if(stu3[i%10] == thisAns) stu3correct += 1;
        }
        int maxValue = Math.max(Math.max(stu1correct, stu2correct), stu3correct);
        
        ArrayList<Integer> answerList = new ArrayList<>();
        if(maxValue == stu1correct) answerList.add(1);
        if(maxValue == stu2correct) answerList.add(2);
        if(maxValue == stu3correct) answerList.add(3);
        

        return answerList.stream().mapToInt(e->e).toArray();
    }
}

//------------------------------------------------------------------------------ 1_25 2016년
class Solution {
    public String solution(int a, int b) {
        String days = "SUN,MON,TUE,WED,THU,FRI,SAT";
        String[] daysArray = days.split(",");
        int[] monthDays ={31,29,31,30,31,30,31,31,30,31,30,31};
        int daySum = b;
        
        for(int i=0; i<a-1; i++){
            daySum += monthDays[i];
        }
        int answerIndex = (daySum+4)%7;
        
        return daysArray[answerIndex];
    }
}

//------------------------------------------------------------------------------ 1_26 소수 만들기
class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        for(int i=0; i<nums.length-2; i++){
           for(int j=i+1; j<nums.length-1; j++){
               for(int k=j+1; k<nums.length; k++){
                   int sum = nums[i]+nums[j]+nums[k];
                   if(isDemical(sum)){
                       answer += 1;
                   }
               }
           }
        }
        return answer;
    }
    boolean isDemical(int target){
        for(int i=2; i<(int) (Math.sqrt(target)+1); i++){
            if(target%i == 0){
                return false;
            }
        }
        return true;
    }
}
//------------------------------------------------------------------------------ 1_27 소수 찾기
class Solution {
    public int solution(int n) {
        int answer = 0;
        for(int i=2; i<n+1; i++){
            if(isPrime(i)== true){
                answer += 1;
            }
        }
        return answer;
    }
    boolean isPrime(int target){
        for(int i=2; i<(int) (Math.sqrt(target)+1); i++){
            if(target%i==0){
                return false;
            }
        }
        return true;
    }
}

//------------------------------------------------------------------------------ 1_28 완주하지 못한 선수
import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> partiHash = new HashMap<>();
        for(int i=0; i<participant.length; i++){
            partiHash.put(
                participant[i],
                partiHash.getOrDefault(participant[i], 0)+1);
        }

        for(int i=0; i<completion.length; i++){
            partiHash.replace(
                completion[i],
                partiHash.get(completion[i])-1);
        }
        for(String name: partiHash.keySet()){
            if(partiHash.get(name) > 0){
                answer = name;
                break;
            }
        }

        return answer;
    }
}

//------------------------------------------------------------------------------ 1_29 로또의 최고 순위와 최저 순위
import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int eraseOne = 0;
        int matchCount = 0;
        for(int lotto: lottos){
            if(lotto == 0){
                eraseOne ++;
            }else{
                for(int num: win_nums){
                    if(num == lotto){
                        matchCount ++;
                    }
                }
            }
        }

        int maxRank = 7-matchCount-eraseOne == 7 ? 6: 7-matchCount-eraseOne;
        int minRank = 7-matchCount == 7 ? 6: 7-matchCount;
        int[] answer = {maxRank, minRank};
        
        return answer;
    }
}

//------------------------------------------------------------------------------ 1_30 체육복
import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n-lost.length;
        Arrays.sort(lost);
        Arrays.sort(reserve);

        for(int i=0; i<lost.length; i++){
            for(int j=0; j<reserve.length; j++){
                if(lost[i] == reserve[j]
                  ){
                    lost[i] = -1;
                    reserve[j] = -1;
                    answer ++;
                    break;
                }
            }
        }
        for(int i=0; i<lost.length; i++){
            for(int j=0; j<reserve.length; j++){
                if(lost[i]-1 == reserve[j]
                || lost[i]+1 == reserve[j]
                  ){
                    reserve[j] = -1;
                    answer ++;
                    break;
                }
            }
        }
        return answer;
    }
}
