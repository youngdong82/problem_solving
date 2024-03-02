// bfs_easy_11 --------------------------------------------------------------- 111. Minimum Depth of Binary Tree
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);

        while(!que.isEmpty()){
            int size = que.size();
            for(int i=0; i<size; i++){
                TreeNode now = que.poll();
                if(now.left == null && now.right == null){
                    que.clear();
                    break;
                }
                if(now.left != null){
                    que.add(now.left);
                }
                if(now.right != null){
                    que.add(now.right);
                }
            }
            depth++;
        }
        return depth;
    }
}
// bfs_easy_12 --------------------------------------------------------------- 1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree
class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        
        Queue<TreeNode> que = new LinkedList<>();
        que.add(cloned);
        while(!que.isEmpty()){
            TreeNode now = que.poll();
            if(now.val == target.val){
                return now;
            }
            if(now.left != null){
                que.add(now.left);
            }
            if(now.right != null){
                que.add(now.right);
            }
        }
        return cloned;
    }
}


// bfs_easy_13 --------------------------------------------------------------- 1971. Find if Path Exists in Graph
class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {

        boolean[] visited = new boolean[n];
        Queue<Integer> que = new LinkedList<>();
        que.add(source);
        visited[source] = true;

        while(!que.isEmpty()){
            int now = que.poll();
            if(now == destination){
                return true;
            }
            for(int i=0; i<edges.length; i++){
                if(edges[i][0] == now && !visited[edges[i][1]]){
                    que.add(edges[i][1]);
                    visited[edges[i][1]] = true;
                }else if(edges[i][1] == now && !visited[edges[i][0]]){
                    que.add(edges[i][0]);
                    visited[edges[i][0]] = true;
                }
            }
        }
        return false;
    }
}

// bfs_easy_14 --------------------------------------------------------------- 637. Average of Levels in Binary Tree
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        ArrayList<Double> answer = new ArrayList<>();

        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()){
            int size = que.size();
            long sum = 0;
            for(int i=0; i<size; i++){
                TreeNode now = que.poll();
                sum += now.val;
                if(now.left != null){
                    que.add(now.left);
                }
                if(now.right != null){
                    que.add(now.right);
                }
            }
            answer.add((double) sum/size);
        }
        return answer;
    }
}


// bfs_easy_15 --------------------------------------------------------------- 100. Same Tree
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> que1 = new LinkedList<>();
        Queue<TreeNode> que2 = new LinkedList<>();
        que1.add(p);
        que2.add(q);

        while(!(que1.isEmpty() && que2.isEmpty())){
            TreeNode now1 = que1.poll();
            TreeNode now2 = que2.poll();
            if((now1 == null && now2 != null) || (now1 != null && now2 == null)){
                return false;
            }
            if(now1.val != now2.val){
                return false;
            }
            if(now1.left != null || now2.left != null){
                que1.add(now1.left);
                que2.add(now2.left);
            }
            if(now1.right != null || now2.right != null){
                que1.add(now1.right);
                que2.add(now2.right);
            }
        }

        return true;
    }
}


// bfs_easy_16 --------------------------------------------------------------- 530. Minimum Absolute Difference in BST
class Solution {
    public int getMinimumDifference(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()){
            TreeNode now = que.poll();
            list.add(now.val);
            if(now.left != null) que.add(now.left);
            if(now.right != null) que.add(now.right);
        }
        Collections.sort(list);
        System.out.println(list);
        int answer = Integer.MAX_VALUE;
        for(int i=0; i<list.size()-1; i++){
            answer = Math.min(list.get(i+1) - list.get(i),answer);
        }
        return answer;
    }
}


// bfs_easy_17 --------------------------------------------------------------- 783. Minimum Distance Between BST Nodes
class Solution {
    public int minDiffInBST(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Deque<TreeNode> que = new ArrayDeque();
        que.add(root);
        while(!que.isEmpty()){
            TreeNode now = que.poll();
            list.add(now.val);
            if(now.left != null){
                que.add(now.left);
            }
            if(now.right != null){
                que.add(now.right);
            }
        }
        Collections.sort(list);
        int diff = Integer.MAX_VALUE;
        for(int i=1; i<list.size(); i++){
            diff = Math.min(diff, list.get(i) - list.get(i-1));
        }
        return diff;
    }
}


// bfs_easy_18 --------------------------------------------------------------- 965. Univalued Binary Tree
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        if(root == null){
            return true;
        }
        int sameVal = root.val;
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()){
            TreeNode now = que.poll();
            if(now.val != sameVal){
                return false;
            }
            if(now.left != null) que.add(now.left);
            if(now.right != null) que.add(now.right);
        }
        return true;
    }
}


// bfs_easy_19 --------------------------------------------------------------- 559. Maximum Depth of N-ary Tree
class Solution {
    public int maxDepth(Node root) {
        int depth = 0;
        if(root == null){
            return depth;
        }
        Queue<Node> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()){
            int size = que.size();
            for(int i=0; i<size; i++){
                Node now = que.poll();
                if(now.children != null){
                    for(int j=0; j<now.children.size(); j++){
                        que.add(now.children.get(j));
                    }
                }
            }
            depth++;
        }
        return depth;
    }
}


// bfs_easy_20 --------------------------------------------------------------- 1469. Find All The Lonely Nodes
class Solution {
    public List<Integer> getLonelyNodes(TreeNode root) {
        ArrayList<Integer> answer = new ArrayList<>();
        if(root == null){
            return answer;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.add(root);
        while(!que.isEmpty()){
            TreeNode now = que.poll();
            if(now.left == null && now.right == null){
                continue;
            }
            if(now.left == null){
                answer.add(now.right.val);
            }
            if(now.right == null){
                answer.add(now.left.val);
            }
            if(now.left != null) que.add(now.left);
            if(now.right != null) que.add(now.right);
        }
        return answer;
    }
}
