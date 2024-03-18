package FebMonth;

import java.util.LinkedList;
import java.util.Queue;

public class Day28_LC513 {
    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7);
        TreeNode node5 = new TreeNode(5, node7, null);
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node3 = new TreeNode(3, node5, node6);
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2, node4, null);
        TreeNode node1 = new TreeNode(1, node2, node3);

        Day28_LC513 obj = new Day28_LC513();
        System.out.println(obj.findBottomLeftValue(node1));
        System.out.println(obj.findBottomLeftValue1(node1));

    }
    public int findBottomLeftValue(TreeNode root) { // TC - O(N) SC - O(N)
        Queue<PairL> queue = new LinkedList<>();
        int curLevel = -1;
        int lastNodeVal = -1;
        queue.add(new PairL(root, 0));

        while(!queue.isEmpty()){
            PairL p = queue.poll();
            int level = p.level;
            TreeNode node = p.node;
            if(level != curLevel){
                lastNodeVal = node.val;
                curLevel = level;
            }

            if(node.left != null) queue.add(new PairL(node.left, level + 1));
            if(node.right != null) queue.add(new PairL(node.right, level + 1));
        }

        return lastNodeVal;
    }

    public int findBottomLeftValue1(TreeNode root) { // TC - O(N) SC - O(N)
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode temp = null;
        queue.add(root);

        while(!queue.isEmpty()){
            temp = queue.poll();

            if(temp.right != null) queue.add(temp.right);
            if(temp.left != null) queue.add(temp.left);
        }

        return temp.val;
    }
}
class PairL{
    TreeNode node;
    int level;

    PairL(TreeNode node, int level){
        this.node = node;
        this.level = level;
    }
}
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(){}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
