package FebMonth;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day29_LC1609 {
    public static void main(String[] args) {

//        TreeNode node2 = new TreeNode(5);
//        TreeNode node6 = new TreeNode(6);
//
//        TreeNode node8 = new TreeNode(7);
//        TreeNode node12 = new TreeNode(12);
//
//        TreeNode node7 = new TreeNode(7, node6, null);
//        TreeNode node9 = new TreeNode(9, null, node2);
//        TreeNode node3 = new TreeNode(3, node12, node8);
//
//        TreeNode node4 = new TreeNode(4, node7, node9);
//        TreeNode node10 = new TreeNode(10, node3, null);
//
//        TreeNode node1 = new TreeNode(1, node10, node4);

        TreeNode node7 = new TreeNode(7);
        TreeNode node3 = new TreeNode(3);
        TreeNode node_3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2, node7, null);
        TreeNode node4 = new TreeNode(4, node3, node_3);
        TreeNode node5 = new TreeNode(5, node4, node2);

        Day29_LC1609 obj = new Day29_LC1609();
        System.out.println(obj.isEvenOddTree(node5));
        System.out.println(obj.isEvenOddTree1(node5));

    }
    public boolean isEvenOddTree(TreeNode root) { // TC - O(N) SC -O(N)
        Queue<PairL> queue = new LinkedList<>();
        int curLevel = -1;
        int prevVal = -1;
        queue.add(new PairL(root, 0));

        while(!queue.isEmpty()){
            PairL p = queue.poll();
            int level = p.level;
            TreeNode node = p.node;

            if(level != curLevel){
                curLevel = level;
                if(level % 2 == 0) prevVal = 0;
                else prevVal = Integer.MAX_VALUE;
            }

            if(level % 2 == 0){
                if(node.val % 2 == 0 || node.val <= prevVal) return false;
            }
            else{
                if(node.val % 2 != 0 || node.val >= prevVal) return false;
            }

            prevVal = node.val;

            if(node.left != null) queue.add(new PairL(node.left, level + 1));
            if(node.right != null) queue.add(new PairL(node.right, level + 1));
        }
        return  true;
    }
    public boolean isEvenOddTree1(TreeNode root) { // TC - O(N) SC -O(N)
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 0;
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            int prevVal = level % 2 == 0 ? 0 : 10000000;
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if(level % 2 == 0){
                    if(node.val % 2 == 0 || node.val <= prevVal) return false;
                }
                else{
                    if(node.val % 2 != 0 || node.val >= prevVal) return false;
                }
                prevVal = node.val;
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            level += 1;
        }
        return true;
    }
}
