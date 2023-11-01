// gready_easy_01 --------------------------------------------------------------- Minimum Sum of Four Digit Number After Splitting Digits /  86.2%
class Solution {
    public int minimumSum(int num) {
        int[] digitArr = new int[4];
        int idx=0;
        while(idx<4){
            digitArr[idx] = num%10;
            num /= 10;
            idx++;
        }
        Arrays.sort(digitArr);
        int num1 = Integer.parseInt("" + digitArr[0] + digitArr[2]);
        int num2 = Integer.parseInt("" + digitArr[1] + digitArr[3]);

        return num1 + num2;
    }
}

// String으로 만들고 다시 int로 만들지 말고
// 어짜피 가장 작은 2개의 수가 10의자리 수로 가야하니까
// 바로 10을 곱한 값으로 숫자계산
class Solution {
    public int minimumSum(int num) {
        int[] digitArr = new int[4];
        int idx=0;
        while(idx<4){
            digitArr[idx] = num%10;
            num /= 10;
            idx++;
        }
        Arrays.sort(digitArr);
        return 10 * (digitArr[0] + digitArr[1]) + digitArr[2] + digitArr[3];
    }
}

// gready_easy_02 --------------------------------------------------------------- Split a String in Balanced Strings / 85.6%
// Stack 사용
class Solution {
  public int balancedStringSplit(String s) {
      Stack<Character> stack = new Stack();
      int res = 0;
      for (char ch : s.toCharArray()) {
          if (stack.isEmpty() || stack.peek() == ch)
              stack.push(ch);
          else
              stack.pop();
          if (stack.isEmpty()) 
              res++;
      }
      return res;
  }
}


// gready_easy_03 --------------------------------------------------------------- Maximum 69 Number / 82.0%
class Solution {
    public int maximum69Number (int num) {
        char[] splitedInt = String.valueOf(num).toCharArray();
        char[] answer = new char[splitedInt.length];
        boolean toggle = false;
        for(int i=0; i<splitedInt.length; i++){
            if(toggle){
                answer[i] = splitedInt[i];
            }else{
                if(splitedInt[i] == '6'){
                    answer[i] = '9';
                    toggle = true;
                }else{
                    answer[i] = splitedInt[i];
                }
            }
        }
        return Integer.parseInt(String.valueOf(answer));
    }
}


// gready_easy_04 ---------------------------------------------------------------  Maximum Sum With Exactly K Elements  / 81.6%
class Solution {
    public int maximizeSum(int[] nums, int k) {
        Arrays.sort(nums);
        int plus;
        if(k == 1){
            plus = 0;
        }else{
            plus = k*(k-1) /2;
        }
        int answer = nums[nums.length-1] * k + plus;
        return answer;
    }
}


// gready_easy_05 --------------------------------------------------------------- DI String Match / 78.0%
class Solution {
    ArrayList<ArrayList<Integer>> condiList = new ArrayList<>();
    public int[] diStringMatch(String s) {
        int[] answer = new int[s.length()+1];
        ArrayList<Integer> tmp = new ArrayList<>();
        dfs(s.length(), tmp);
        char[] charArrS = s.toCharArray();
        for(ArrayList<Integer> condi: condiList){
            if(isOk(charArrS, condi)){
                answer = condi.stream().mapToInt(e->e).toArray();
                break;
            }
        }
        
        return answer; 
    }
    //dfs로 CONDI만든 후
    void dfs(int target, ArrayList<Integer> list){
        if(list.size() == target+1){
            ArrayList<Integer> copied = new ArrayList<>();
            copied.addAll(list);
            condiList.add(copied);
            return;
        }
        for(int i=0; i<target+1; i++){
            if(list.indexOf(i) == -1){
                list.add(i);
                dfs(target, list);
                list.remove(list.size()-1);
            }
        }
    }
    boolean isOk(char[] charArr, ArrayList<Integer> target){
        for(int i=0; i<charArr.length; i++){
            if(charArr[i] == 'I'){
                if(target.get(i) >= target.get(i+1)){
                    return false;
                }
            }else{
                if(target.get(i) <= target.get(i+1)){
                    return false;
                }
            }
        }
        return true;
    }
}


// 엄청 간단해보이는데 너무 어렵게 풀어서
// 계속 시간초과
// 결국 답 찾아봄;;;

class Solution {
  public int[] diStringMatch(String s) {
    final int n = s.length();
    int[] ans = new int[n + 1];
    int min = 0;
    int max = n;

    for (int i = 0; i < n; ++i){
        ans[i] = s.charAt(i) == 'I' ? min++ : max--;
        // System.out.println(ans[i]);
        // System.out.println("min: " + min);
        // System.out.println("max: " + max);
    }
      
    ans[n] = min;

    return ans;
  }
}


// gready_easy_06 --------------------------------------------------------------- Minimum Time to Type Word Using Special Typewriter / 73.9%
class Solution {
    public int minTimeToType(String word) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int answer = word.length();
        int pointer = 0;
        for(int i=0; i<word.length(); i++){
            int now = alpha.indexOf(word.charAt(i));
            int diff = Math.abs(now-pointer);
            int minDist = Math.min(diff, 26-diff);
            answer += minDist;
            pointer = now;
        }
        return answer;
    }
}

// gready_easy_07 --------------------------------------------------------------- Minimum Subsequence in Non-Increasing Order / 72.5%
class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int i=nums.length-1; i>=0; i--){
            sum+=nums[i];
        }
        int tmpSum = 0;
        List<Integer> ans = new ArrayList<>();
        for(int i=nums.length-1; i>=0; i--){
            tmpSum +=nums[i];
            sum-=nums[i];
            ans.add(nums[i]);
            if(tmpSum > sum){   
                break;
            }
        }
        return ans;
    }
}

// gready_easy_08 --------------------------------------------------------------- Make Array Zero by Subtracting Equal Amounts / 71.9%
class Solution {
    public int minimumOperations(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);

        while(true){
            int cnt = 0;
            int tmp = 0;
            for(int i=0; i<nums.length; i++){
                if(nums[i] == 0){
                    cnt++;
                }else{
                    if(tmp == 0){
                        tmp = nums[i];
                    }
                    nums[i] -= tmp;
                }
            }
            if(cnt == nums.length){
                break;
            }
            ans++;
        }
        return ans;
    }
}

//아ㅋㅋㅋㅋㅋ 결국엔 0을 제외한 중복하지 않은 숫자 갯수 구하는거구나...
class Solution {
    public int minimumOperations(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int i:nums){
            if(i>0){set.add(i);}
        }
        System.out.println(set);
        return set.size();
    }
}

// gready_easy_09 --------------------------------------------------------------- Longest Subsequence With Limited Sum / 71.8%
class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        int[] ans = new int[queries.length];
        Arrays.sort(nums);
        for(int i=0; i<queries.length; i++){
            int limit = queries[i];
            ans[i] = getSubSeq(nums, limit);
        }
        return ans;
    }
    int getSubSeq(int[] nums, int limit){
        int minSum = limit;
        int maxCnt = 0;
        
        int startIdx = 0;
        int endIdx = 0;
        int sum = 0;
        while(endIdx <= nums.length){
            if(sum <= limit){
                if(endIdx-startIdx > maxCnt){ 
                    minSum = sum;
                    maxCnt = endIdx-startIdx;
                }
                if(endIdx <= nums.length){
                    endIdx++;
                    if(endIdx >nums.length){
                        break;
                    }
                    sum += nums[endIdx-1];
                }
            }else{
                startIdx++;
                sum -= nums[startIdx-1];
            }   
        }
        return maxCnt;
    }
}

// gready_easy_10 --------------------------------------------------------------- Two Furthest Houses With Different Colors / 66.6%
class Solution {
    public int maxDistance(int[] colors) {
        int ans1 = 0;
        int end1 = colors[colors.length-1];
        for(int i=0; i<colors.length; i++){
            if(colors[i] != end1){
                ans1 = colors.length - i-1;
                break;
            }
        }
        int ans2 = 0;
        int end2 = colors[0];
        for(int i=colors.length-1; i>=0; i--){
            if(colors[i] != end2){
                ans2 = i;
                break;
            }
        }
        return Math.max(ans1,ans2);
    }
}