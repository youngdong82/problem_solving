// bfs_easy_01 --------------------------------------------------------------- 733. Flood Fill
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originColor = image[sr][sc];
        if(originColor == color){
            return image;
        }
        int[] dx = {1,0,-1,0};
        int[] dy = {0,-1,0,1};
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{sr,sc});
        image[sr][sc] = color;

        while(!que.isEmpty()){
            int[] now = que.poll();
            for(int i=0; i<4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(nx >= 0 && nx < image.length && ny >=0 && ny < image[0].length){
                    if(image[nx][ny] == originColor){
                        image[nx][ny] = color;
                        que.add(new int[]{nx,ny});
                    }
                }
            }
        }

        return image;
    }
}


// bfs_easy_02 --------------------------------------------------------------- 101. Symmetric Tree
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root.left);
        que.add(root.right);
        while(!que.isEmpty()){
            TreeNode left = que.poll();
            TreeNode right = que.poll();
            if(left == null && right == null){
                continue;
            }
            if(left == null || right == null || left.val != right.val){
                return false;
            }
            que.add(left.left);
            que.add(right.right);
            que.add(left.right);
            que.add(right.left);
        }
        return true;
    }
}


// bfs_easy_03 --------------------------------------------------------------- 104. Maximum Depth of Binary Tree
import com.sun.source.tree.Tree;


class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        int depth = 0;
        while(!que.isEmpty()){
            int size = que.size();
            System.out.println(size);
            while(size-- >0){
                System.out.println("in while " + size);

                TreeNode node = que.poll();
                if(node.left != null){
                    que.add(node.left);
                }
                if(node.right != null){
                    que.add(node.right);
                }
            }
            depth++;
        }

        return depth;
    }
}

// bfs_easy_04 --------------------------------------------------------------- 226. Invert Binary Tree
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }

        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()){
            TreeNode now = que.poll();
            TreeNode left = now.left;
            now.left = now.right;
            now.right = left;

            if(now.left != null){
                que.add(now.left);
            }
            if(now.right != null){
                que.add(now.right);
            }
        }

        return root;
    }
}

// bfs_easy_05 --------------------------------------------------------------- 463. Island Perimeter
class Solution {
    public int islandPerimeter(int[][] grid) {
        int answer = 0;
        int startx = -1;
        int starty = -1;
        //land 찾기
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                if(grid[i][j] == 1){
                    startx = i;
                    starty = j;
                    break;
                }
            }
            if(startx != -1 || starty != -1){
                break;
            }
        }
        int[] dx = {1,0,-1,0};
        int[] dy = {0,-1,0,1};

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{startx, starty});
        visited[startx][starty] = true;

        while(!que.isEmpty()){
            int[] now = que.poll();
            int tmpPeri = 4;
            for(int i=0; i<4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(nx>= 0 && nx<grid.length && ny>=0 && ny<grid[0].length){
                    if(grid[nx][ny] == 1){
                        tmpPeri-=1;
                        if(!visited[nx][ny]){
                            visited[nx][ny] = true;
                            que.add(new int[]{nx,ny});
                        }
                        
                    }
                }
            }
            answer += tmpPeri;
        }

        return answer;
    }
}


// bfs_easy_06 --------------------------------------------------------------- 653. Two Sum IV - Input is a BST
// 다른 것보다 set.contains(k-now.val) => 이건 진짜 유용하다...!
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if(root == null){
            return false;
        }

        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()){
            TreeNode now = que.poll();
            if(set.contains(k-now.val)){
                return true;
            }
            set.add(now.val);
            if(now.left != null){
                que.add(now.left);
            }
            if(now.right != null){
                que.add(now.right);
            }
        }
        return false;
    }
}

// bfs_easy_07 --------------------------------------------------------------- 112. Path Sum
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        stack.push(root);
        sumStack.push(root.val);
        while(!stack.isEmpty()){
            TreeNode now = stack.pop();
            int sumNow = sumStack.pop();

            if(now.left == null && now.right == null){  //leaf 다
                if(sumNow == targetSum){
                    return true;
                }
            }else{
                if(now.left != null){
                    stack.push(now.left);
                    sumStack.push(sumNow + now.left.val);
                }
                if(now.right != null){
                    stack.push(now.right);
                    sumStack.push(sumNow + now.right.val);
                }
            }
        }
        return false;
    }
}


//미친것 같다... 개쩔어...
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null && root.val == targetSum){
            return true;
        }

        return hasPathSum(root.left, targetSum-root.val) || hasPathSum(root.right, targetSum-root.val);
    }
}

// bfs_easy_08 --------------------------------------------------------------- 993. Cousins in Binary Tree
// 개쩐다.....
// 익숙하지 않아서 그런건지... 왜이렇게 어렵냨ㅋㅋㅋㅋㅋ 동시에 정답들은 신박하네...
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root == null){
            return false;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        int depthY = -1;
        int depthX = -2;
        int level = 0;

        while(!que.isEmpty()){
            int size = que.size();
            for(int i=0; i<size; i++){
                TreeNode now = que.poll();
                if(now.left != null && now.right != null){  //둘 다 있으면
                    //sibling이 같다면
                    if(now.left.val == x && now.right.val == y) return false;
                    if(now.left.val == y && now.right.val == x) return false;
                }
                if(now.val == x)    depthX = level;
                if(now.val == y)    depthY = level;              
                if(now.left != null)    que.add(now.left);
                if(now.right != null)   que.add(now.right);
            }
            level++;
        }
        
        return depthX == depthY;
    }
}

// bfs_easy_09 --------------------------------------------------------------- 404. Sum of Left Leaves
// 처음으로 스스로 풀었다!!! 오예!!!!
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()){
            TreeNode now = que.poll();
            if(now.left != null){
                if(now.left.left == null && now.left.right == null){  //leaf!!
                    sum += now.left.val;
                }
                que.add(now.left);
            }
            if(now.right != null){
                que.add(now.right);
            }
        }
        return sum;
    }
}


// bfs_easy_10 --------------------------------------------------------------- 617. Merge Two Binary Trees
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null){
            return root2;
        }
        if(root2 == null){
            return root1;
        }
        TreeNode merged = new TreeNode(root1.val + root2.val);
        merged.left = mergeTrees(root1.left, root2.left);
        merged.right = mergeTrees(root1.right, root2.right);
        return merged;
    }
}