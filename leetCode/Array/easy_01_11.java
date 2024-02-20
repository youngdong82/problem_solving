// array_easy_01 --------------------------------------------------------------- 2942. Find Words Containing Character
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> answer = new ArrayList<Integer>();
        for(int i=0; i<words.length; i++){
            //words[i]에 x가 있다면 
            if(words[i].contains("" +x)){
                answer.add(i);
            }
        }
        
        return answer;
    }
}


// array_easy_02 --------------------------------------------------------------- 1672. Richest Customer Wealth
class Solution {
    public int maximumWealth(int[][] accounts) {
        int maxAnswer = 0;
        for(int[] eachArr : accounts){
            int sum = 0;
            for(int each : eachArr){
                sum += each;
            }
            maxAnswer = Math.max(maxAnswer, sum);
        }
        return maxAnswer;
    }
}


// array_easy_03 --------------------------------------------------------------- 2114. Maximum Number of Words Found in Sentences
class Solution {
    public int mostWordsFound(String[] sentences) {
        int answer = 0;
        for(String eachSentence : sentences){
            String[] wordList = eachSentence.split(" ");
            answer = Math.max(answer, wordList.length);
        }
        return answer;
    }
}

// array_easy_04 --------------------------------------------------------------- 2574. Left and Right Sum Differences
class Solution {
    public int[] leftRightDifference(int[] nums) {
        int[] answer = new int[nums.length];
        int[] leftSumArr = new int[nums.length];
        int[] rightSumArr = new int[nums.length];

        int leftSum = 0;
        for(int i=0; i<nums.length-1; i++){
            leftSum += nums[i];
            leftSumArr[i+1] = leftSum;
        }

        int rightSum = 0;
        for(int i=nums.length-1; i>0; i--){
            rightSum += nums[i];
            rightSumArr[i-1] = rightSum;
        }

        for(int i=0; i<nums.length; i++){
            answer[i] = Math.abs(leftSumArr[i] - rightSumArr[i]);
        }

        return answer;
    }
}

// array_easy_05 --------------------------------------------------------------- 2032. Two Out of Three
class Solution {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Set<Integer> set = new HashSet<>();
        for(int each: nums1){
            set.add(each);
        }
        for(int each: nums2){
            set.add(each);
        }
        for(int each: nums3){
            set.add(each);
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for(int each: set){
            int count = 0;
            for(int eacheach: nums1){
                if(eacheach == each){
                    count += 1;
                    break;
                }
            }
            for(int eacheach: nums2){
                if(eacheach == each){
                    count += 1;
                    break;
                }
            }
            if(count == 2){
                answer.add(each);
                continue;
            }
            for(int eacheach: nums3){
                if(eacheach == each){
                    count += 1;
                    break;
                }
            }
            if(count >= 2){
                answer.add(each);
            }
        }
        return answer;
    }
}

// array_easy_06 --------------------------------------------------------------- 1748. Sum of Unique Elements
class Solution {
    public int sumOfUnique(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0) +1);
        }

        int answer = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == 1){
                answer += entry.getKey();
            }
        }

        return answer;
    }
}

// array_easy_07 --------------------------------------------------------------- 2951. Find the Peaks
class Solution {
    public List<Integer> findPeaks(int[] mountain) {
        ArrayList<Integer> answerList = new ArrayList<>();
        for(int i=1; i<mountain.length-1; i++){
            int left = mountain[i-1];
            int now = mountain[i];
            int right = mountain[i+1];
            if(now > left && now > right){
                answerList.add(i);
            }
        }
        return answerList;
    }
}


// array_easy_08 --------------------------------------------------------------- 88. Merge Sorted Array
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = m+n-1;
        if(m > 0 && n > 0){
            while(len >= 0){
                //여기서 nums1에서 가장 큰 값, nums2에서 가장 큰 값 비교한 후 
                //큰 값을 뒤에서부터 차례로 채우기.
                int tmpResult = 0;
                if(n == 0){
                    tmpResult = 1;
                }else if(m == 0){
                    tmpResult = 2;
                }else if(nums1[m-1] > nums2[n-1]){
                    tmpResult = 1;
                }else if(nums1[m-1] < nums2[n-1]){
                    tmpResult = 2;
                }else{  //같다면
                    if(m > n){
                        tmpResult = 1;
                    }else{
                        tmpResult = 2;
                    }
                }
                if(tmpResult == 1){
                    nums1[len] = nums1[m-1];
                    m -= 1;
                }else{
                    nums1[len] = nums2[n-1];
                    n -= 1;
                }
                len-=1;
            }
        }else if(m < n){    //nums1이 비어있을 때
            for(int i=0; i<len+1; i++){
                nums1[i] = nums2[i];
            }
        }
    }
}



// array_easy_09 --------------------------------------------------------------- 1207. Unique Number of Occurrences
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int each: arr){
            map.put(each, map.getOrDefault(each, 0) +1);
        }

        Set<Integer> set = new HashSet<>();
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            set.add(entry.getValue());
        }

        return map.size() == set.size();
    }
}



// array_easy_10 --------------------------------------------------------------- 169. Majority Element
class Solution {
    public int majorityElement(int[] nums) {
        int answer = -1;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num: nums){
            if(map.containsKey(num)){
                map.computeIfPresent(num, (key,value) -> value +1);
            }else{
                map.put(num, 1);
            }
        }
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(entry.getValue() > nums.length /2){
                answer = entry.getKey();
                break;
            }
        }

        return answer;
    }
}

// array_easy_11 --------------------------------------------------------------- 217. Contains Duplicate
class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num: nums){
            set.add(num);
        }
        return set.size() != nums.length;
    }
}
