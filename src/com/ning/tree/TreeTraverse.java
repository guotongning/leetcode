package com.ning.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树相关算法练习
 *
 * @author <a href="guotongning@126.com">Nicholas</a>
 * @since 1.0.0
 * Created on 2021/7/26 21:17
 */
public class TreeTraverse {
    public static void main(String[] args) {
        /*
            1
          2   3
         4 5 6 7
         */
        inOrderTraversal(TreeNode.defaultTree()).forEach(node -> System.out.print(node + " -> "));
    }


    /**
     * 二叉树的中序遍历 - 简单
     *
     * @param root 二叉树根节点
     * @return {@link List<Integer>} - 二叉树的中序遍历
     * Created on 2021/7/26 21:29
     */
    public static List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
//        res = inorder(root);
//        res = inorder1(root);
        return res;
    }

    /**
     * 递归 - 二叉树中序遍历
     *
     * @param root 二叉树根节点
     * @param res  结果收集
     * @return {@link Void} - 二叉树的中序遍历
     * Created on 2021/7/26 21:44
     */
    private static void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    /**
     * 迭代 - 二叉树中序遍历
     *
     * @param root 二叉树根节点
     * @return {@link List<Integer>} - 结果收集
     * Created on 2021/7/26 21:48
     */
    private static List<Integer> inorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stk = new LinkedList<>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    /**
     * Morris - 二叉树中序遍历
     * 它能将非递归的中序遍历空间复杂度降为 O(1)
     *
     * @param root 二叉树根节点
     * @return {@link List<Integer>} - 结果收集
     * Created on 2021/7/26 21:48
     */
    public static List<Integer> inorder1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode predecessor = null;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    res.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}


