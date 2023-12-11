// two_pointer_easy_11 --------------------------------------------------------------- Move Zeroes / 61.5%
class Solution {
    public int[] moveZeroes(int[] nums) {
        int start = 0, end = nums.length-1;
        int[] answer = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            if(nums[i] == 0){
                answer[end] = 0;
                end--;
            }else{
                answer[start] = nums[i];
                start++;
            }
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }
}

// 복사하지 말고 그 자리에서 하세요...?
// ㅋㅋㅋㅋㅋ 개 어거지로 풀었다. 
// 뒤에서 0.04%ㅋㅋㅋㅋㅋㅋㅋ
class Solution {
    public void moveZeroes(int[] nums) {
        int cnt = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] == 0){
                cnt++;
            }    
        }
        for(int i=0; i<nums.length; i++){
            if(nums[i] == 0 && cnt > 0){               //0인데
                cnt--;
                int zeroIdx = i;
                while(zeroIdx != nums.length-1){
                    //(i+1이 0이거나 마지막)이 아니라면 swap 끝까지 반복
                    nums[zeroIdx] = nums[zeroIdx+1];
                    nums[zeroIdx+1] = 0;
                    zeroIdx++;
                }
                // System.out.println(Arrays.toString(nums));
                i--;
            }
        }
    }
}

// 엄청 간단한데...? 왜 이런걸 생각해내지 못할까...?
class Solution {
    public void moveZeroes(int[] nums) {
        int cnt = 0;
        int k = 0;      //여기서 k는 0이 아닌 숫자가 들어갈 자리
        for(int i=0; i<nums.length; i++){
            if(nums[i] == 0){
                cnt++;
            }    
        }
       if(cnt > 0){
            for(int i=0;i<nums.length;i++){
                if(nums[i]!=0){             //0이 아니라면
                    int temp = nums[i];
                    nums[i] = nums[k];      //0이 아닌 idx k로 기억
                    nums[k] = temp;
                    k++;
                }
            }
        }
    }
}
// 연습으로 0을 뒤로 보내는 걸로 해보자
// => 순서를 지켜야해서 불가능


// two_pointer_easy_12 --------------------------------------------------------------- Number of Distinct Averages / 58.0%
class Solution {
    public int distinctAverages(int[] nums) {
        Set<Double> ansSet = new HashSet<>();
        int end = nums.length-1;
        Arrays.sort(nums);

        for(int i=0; i<(nums.length)/2; i++){
            ansSet.add((double)(nums[i] + nums[end])/2);
            end--;
        }

        return ansSet.size();
    }
}


// two_pointer_easy_13 --------------------------------------------------------------- Intersection of Two Arrays II / 56.2%
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        //갯수 세는 arr 만든다음 두개 비교
        int[] cntArr1 = new int[1001];
        int[] cntArr2 = new int[1001];

        for(int i=0; i<nums1.length; i++){
            cntArr1[nums1[i]] += 1;
        }
        for(int i=0; i<nums2.length; i++){
            cntArr2[nums2[i]] += 1;
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<1001; i++){
            if(cntArr1[i] != 0 && cntArr2[i] != 0){
                for(int j=0; j<Math.min(cntArr1[i], cntArr2[i]); j++){
                    list.add(i);
                }
            }
        }
        
        return list.stream().mapToInt(e->e).toArray();
    }
}


// two_pointer_easy_14 --------------------------------------------------------------- Remove Element / 55.2%
// 풀긴 했는데 뭔가 어리둥절...
class Solution {
    public int removeElement(int[] nums, int val) {
        int cnt = 0;
        for(int num: nums){
            if(num == val){
                cnt++;
            }
        }
        if(cnt > 0){
            int i=0;
            int k = nums.length-1;
            while(i < nums.length-cnt){
                if(nums[i] == val){
                    int tmp = nums[k];
                    nums[i] = tmp;
                    nums[k] = 0;
                    k--;
                }else{
                    i++;
                }
            }
        }
        return nums.length-cnt;
    }
}

// two_pointer_easy_15 --------------------------------------------------------------- Remove Duplicates from Sorted Array / 53.9%
class Solution {
    public int removeDuplicates(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num,0)+1);
        }
        int k=0;
        int i = 0;
        while(i<nums.length){
            if(i != k){
                //k에 i값을 넣어
                nums[k] = nums[i];
            }
            int many = map.get(nums[i]);
            i += many;
            k++;
        }

        return map.size();
    }
}

// two_pointer_easy_16 --------------------------------------------------------------- Reverse Vowels of a String / 51.8%
class Solution {
    public String reverseVowels(String s) {
        String vowels = "aeiouAEIOU";
        int start = 0, end=s.length()-1;
        StringBuilder sb = new StringBuilder(s);

        while(start < end){
            if(vowels.contains(s.charAt(start)+"") && vowels.contains(s.charAt(end)+"") ){
                sb.setCharAt(start, s.charAt(end));
                sb.setCharAt(end, s.charAt(start));
                start++;
                end--;
            }
            else if(vowels.contains(s.charAt(start)+"")){
                end--;
            }
            else if(vowels.contains(s.charAt(end)+"")){
                start++;
            }
            else{
                start++;
                end--;
            }
        }
        
        return sb.toString();
    }
}


// two_pointer_easy_17 --------------------------------------------------------------- Reverse String II / 50.5%
class Solution {
    public String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int i=0;
        while(i<s.length()){
            int reverseI = i+k-1;
            int tmpI = i;
            if(reverseI > s.length()-1){
                reverseI = s.length()-1;
            }
            while(reverseI > tmpI){
                sb.setCharAt(reverseI, s.charAt(tmpI));
                sb.setCharAt(tmpI, s.charAt(reverseI));
                tmpI++;
                reverseI--;
            }
            i += (2*k);
        }
        return sb.toString();
    }
}

//StringBuilder보다 toCharArray()로 진행하면 더욱 빠르다.
class Solution {
    public String reverseStr(String s, int k) {
        char[] sCharArr = s.toCharArray();
        int i=0;
        while(i<s.length()){
            int reverseI = i+k-1;
            int tmpI = i;
            if(reverseI > s.length()-1){
                reverseI = s.length()-1;
            }
            while(reverseI > tmpI){
                sCharArr[reverseI] = s.charAt(tmpI);
                sCharArr[tmpI] = s.charAt(reverseI);
                tmpI++;
                reverseI--;
            }
            i += (2*k);
        }
        return new String(sCharArr);
    }
}


// two_pointer_easy_18 --------------------------------------------------------------- Backspace String Compare / 49.1%
//쉬워보이는데 뭔가 엄청 헷갈리다 결국 답 봄;;
class Solution {
    public boolean backspaceCompare(String s, String t) {
        char[] sCharArr = s.toCharArray();
        char[] tCharArr = t.toCharArray();
        int sEnd = s.length()-1, tEnd = t.length()-1;
        int sCnt = 0, tCnt=0;   
        boolean answer = true;
        while(sEnd>=0 || tEnd>=0){        //둘다 0 이상일 때만 반복
            while(sEnd >= 0){
                if(sCharArr[sEnd] == '#'){
                    sEnd--;
                    sCnt++;
                }else if(sCnt>0){
                    sEnd --;
                    sCnt --;
                }else{
                    break;
                }
            }
            
            while(tEnd >= 0){
                if(tCharArr[tEnd] == '#'){
                    tEnd--;
                    tCnt++;
                }else if(tCnt>0){
                    tEnd --;
                    tCnt --;
                }else{
                    break;
                }
            }
            if(sEnd >= 0 && tEnd >= 0 && sCharArr[sEnd] != tCharArr[tEnd]){
                answer = false;
                break;
            }

            if((sEnd>=0) != (tEnd>=0)){
                answer = false;
                break;
            }
            sEnd--;
            tEnd--;
        }

        return answer;
    }
}

// two_pointer_easy_19 --------------------------------------------------------------- Is Subsequence / 48.0%
class Solution {
    public boolean isSubsequence(String s, String t) {
        //s가 t의 부분수열이면 true 리턴
        char[] tCharArr = t.toCharArray();
        char[] sCharArr = s.toCharArray();
        int j=0;
        for(int i=0; i<tCharArr.length; i++){
            if(j == sCharArr.length){
                break;
            }
            if(tCharArr[i] == sCharArr[j]){
                j++;
            }
        }
        if(j < sCharArr.length){
            return false;
        }
        return true;
    }
}

// two_pointer_easy_20 --------------------------------------------------------------- Valid Palindrome / 46.2%
class Solution {
    public boolean isPalindrome(String s) {
        String lowerS = s.toLowerCase();
        int start=0, end=s.length()-1;
        boolean answer = true;
        while(start < end){
            while(start < end && !Character.isLetterOrDigit(lowerS.charAt(start))){
                start++;
            }
            while(start < end && !Character.isLetterOrDigit(lowerS.charAt(end))){
                end--;
            }
            if(lowerS.charAt(start) != lowerS.charAt(end)){
                answer = false;
                break;
            }
            start++;
            end--;
        }
        return answer;
    }
}


// two_pointer_easy_21 --------------------------------------------------------------- Valid Palindrome II / 39.9%
// 확실히 어려웠음;;
class Solution {
    public boolean validPalindrome(String s) {
        boolean answer = true;
        char[] sCharArr = s.toCharArray();
        int start=0, end=s.length()-1;
        while(start<end){
            if(sCharArr[start] == sCharArr[end]){
                start++;
                end--;
            }
            else{       //마지막 찬스 여기서 다시 시작: 둘 중 하나라도 true라면 true
                return try2Way(sCharArr,start+1 ,end) || try2Way(sCharArr, start, end-1);
            }
        }
        return answer;
    }

    public boolean try2Way(char[] sCharArr, int start, int end){
        while(start<end){
            if(sCharArr[start] != sCharArr[end]){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
