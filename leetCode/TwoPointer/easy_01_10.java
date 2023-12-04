// two_pointer_easy_01 --------------------------------------------------------------- Reverse Words in a String III / 82.9%
//-------------------------------------- Me - bad
class Solution {
    public String reverseWords(String s) {
        String[] splitedString = s.split(" ");
        String answer = new String();
        for(int i=0; i<splitedString.length; i++){
            String tempString = splitedString[i];
            for(int j= tempString.length()-1; j>=0; j--){
                answer += tempString.charAt(j);
            }
            if(i != splitedString.length-1){
                answer += " ";
            }
            
        }
        return answer;
    }
}
//-------------------------------------- genius
class Solution {
    public String reverseWords(String s) {
        final int len = s.length();
        if(len == 1)
            return s;
        int firstIndex, lastIndex;
        char[] ch = s.toCharArray();
        char temp;
        
        for(int index=0; index<len; index++){
            firstIndex = index;
            while(++index < len && ch[index] != ' ');
            lastIndex = index - 1;
            while(firstIndex < lastIndex){
                temp = ch[firstIndex];
                ch[firstIndex++] = ch[lastIndex];
                ch[lastIndex--] = temp;
            }
        }
        
        return new String(ch);
    }
}

// two_pointer_easy_02 --------------------------------------------------------------- Flipping an Image / 81.4%
class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        int [][] answer = new int[image.length][image[0].length];
        // flip horizontally + invert
        for(int i=0; i<image.length; i++){
            for(int j=0; j<image[i].length; j++){
                answer[i][j] = (image[i][(image[i].length-1)-j] == 0) ? 1:0;
            }
        }
        return answer;
    }
}

// two_pointer_easy_03 --------------------------------------------------------------- Reverse Prefix of Word / 80.1%
class Solution {
    public String reversePrefix(String word, char ch) {
        String answer = "";
        if(word.equals(""+ch)){
            return word;
        }
        if(word.indexOf(ch) != -1){  //ch가 있다면
            StringBuilder sb = new StringBuilder();
            int idx = word.indexOf(ch);
            //idx에서 자르고
            String rev = word.substring(0,idx+1);
            String normal = word.substring(idx+1);

            for(int i=rev.length()-1; i>=0; i--){
                sb.append(rev.charAt(i));
            }
            sb.append(normal);
            answer = sb.toString();
        }else{          //ch가 없다면
            answer = word;
        }
        return answer;
    }
}


// two_pointer_easy_04 --------------------------------------------------------------- Merge Strings Alternately / 79.1%
class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int p1 = 0;
        int p2 = 0;
        int sumLength = word1.length() + word2.length(); 
        for(int i=0; i<sumLength; i++){
            if(i%2 == 0){   //짝수라면 p1
                if(p1 == word1.length()){ //  p1이 word1의 길이와 같다면
                    sb.append(word2.charAt(p2));
                    p2++;
                }else{
                    sb.append(word1.charAt(p1));
                    p1++;
                }
            }else{          //홀수라면 p2
                if(p2 == word2.length()){      //  p1이 word1의 길이와 같다면
                    sb.append(word1.charAt(p1));
                    p1++;
                } else{
                    sb.append(word2.charAt(p2));
                    p2++;
                }
            }
        }
        return sb.toString();
    }
}

// two_pointer_easy_05 --------------------------------------------------------------- Middle of the Linked List / 76.8%
//이건 ListNode 사용법을 숙지해야할 필요가 있다.
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}


// two_pointer_easy_06 --------------------------------------------------------------- Sort Array By Parity / 76.2%
class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int even = 0;
        int odd = nums.length-1;

        int[] answer = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            if(nums[i] %2 == 0){
                answer[even] = nums[i];
                even++;
            }else{
                answer[odd] = nums[i];
                odd--;
            }
        }
        return answer;
    }
}


// two_pointer_easy_07 --------------------------------------------------------------- Intersection of Two Arrays / 71.9%
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        ArrayList<Integer> answer = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int idx1 = 0;
        int idx2 = 0;

        while(idx1<nums1.length && idx2<nums2.length){
            if(nums1[idx1] == nums2[idx2]){
                if(answer.indexOf(nums1[idx1]) == -1){
                    answer.add(nums1[idx1]);
                }
                idx1++;
                idx2++;
            }else if(nums1[idx1] > nums2[idx2]){
                idx2++;
            }else if(nums1[idx1] < nums2[idx2]){
                idx1++;
            }
            
        }
        return answer.stream().mapToInt(e->e).toArray();
    }
}

//더 쉽게 푸네...
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> num1Set = new HashSet<>();
        Set<Integer> num2Set = new HashSet<>();
        for(int num: nums1){
            num1Set.add(num);
        }
        for(int num: nums2){
            if(num1Set.contains(num)){
                num2Set.add(num);
            }
        }

        int[] answer = new int[num2Set.size()];
        int i=0;
        for(int num: num2Set){
            answer[i++] = num;
        }

        return answer;
    }
}


// two_pointer_easy_08 --------------------------------------------------------------- Sort Array By Parity II / 70.7%
class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        int even = 0;
        int odd = 1;

        int[] answer = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            if(nums[i] %2 == 0){
                answer[even] = nums[i]; 
                even += 2;
            }else{
                answer[odd] = nums[i];
                odd += 2;
            }
        }

        return answer;
    }
}


// two_pointer_easy_09 --------------------------------------------------------------- Largest Positive Integer That Exists With Its Negative / 67.8%
class Solution {
    public int findMaxK(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        Arrays.sort(nums);

        int answer = -1;
        while(start != end){
            int tmp = nums[start] + nums[end];
            if(tmp == 0){
                answer = nums[end];
                break;
            }else if(tmp < 0){
                start++;
            }else if(tmp > 0){
                end--;
            }
        }
        return answer;
    }
}


// two_pointer_easy_10 --------------------------------------------------------------- Count Binary Substrings / 65.5%
class Solution {
    public int countBinarySubstrings(String s) {
        int start = 0, mid = 0, ans = 0;
        for(int i=1; i<s.length(); i++){
            if(s.charAt(i) != s.charAt(i-1)){        //0이든 1이든 바뀌는 순간
                if(mid != 0){             //그룹 끝
                    String sub = s.substring(start, i);
                    ans += Math.min(mid-start, i-mid);
                    start = mid;
                }                  //다른 종류 추가
                mid = i;
            }
            if(i == s.length()-1){
                String sub = s.substring(start);
                ans += Math.min(mid-start, s.length()-mid);
            }
        }
        return ans;
    }  
}

//천재다....
class Solution {
    public int countBinarySubstrings(String s) {
        int ans = 0, prev = 0, curr = 1;
        //prev와 curr는 갯수야
        for(int i=1; i<s.length(); i++){
            if(s.charAt(i) != s.charAt(i-1)){
                ans += Math.min(prev,curr);
                prev = curr;
                curr=1;
            }
            else{
                curr++;
            }
        }
        return ans += Math.min(prev,curr);
    }  
}