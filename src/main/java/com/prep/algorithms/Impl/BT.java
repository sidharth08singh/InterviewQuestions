package com.prep.algorithms.Impl;

import javax.naming.BinaryRefAddr;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sidharth1 on 7/1/17.
 */
public class BT {

    private BTNode root;

    public BT() {
        root = null;
    }

    // inserts a node into a binary tree.
    public void insert(int data) {
       this.root = insert(this.root, data);
    }

    public BTNode insert(BTNode root, int data) {
        if (root == null) {
            BTNode newnode = new BTNode(data);
            return newnode;
        }
        else if (root.getLeft() == null && root.getRight() == null) {
            BTNode newnode = new BTNode(data);
            root.setLeft(newnode);
            return root;
        }
        else if (root.getLeft() != null && root.getRight() == null) {
            BTNode newnode = new BTNode(data);
            root.setRight(newnode);
            return root;
        }
        else {
            insert(root.getLeft(), data);
            return root;
        }
    }

    // inserts a node into binary search tree.
    public void insertBST(int data) {
        this.root = insertBST(this.root, data);
    }

    public BTNode insertBST(BTNode root, int data) {
        if (root == null) {
            return new BTNode(data);
        }

        if (data <= root.getData()) {
            root.setLeft(insertBST(root.getLeft(), data));
        }

        else {
            root.setRight(insertBST(root.getRight(), data));
        }

        return root;
    }

    // print all the nodes in a binary tree.
    public void inorder() {
       inorder(this.root);
    }

    public void inorder(BTNode root) {
        if (root != null) {
            inorder(root.getLeft());
            System.out.println(root.getData());
            inorder(root.getRight());
        }
    }

    // print all the nodes in a binary tree.
    public void preorder() {
        preorder(this.root);
    }

    public void preorder(BTNode root) {
        if (root != null) {
            System.out.println(root.getData());
            inorder(root.getLeft());
            inorder(root.getRight());
        }
    }

    // print all the nodes in a binary tree.
    public void postorder() {
        postorder(this.root);
    }

    public void postorder(BTNode root) {
        if (root != null) {
            inorder(root.getLeft());
            inorder(root.getRight());
            System.out.println(root.getData());
        }
    }

    // count the number of nodes in a binary tree.
    public int countNodes() {
        return countNodes(this.root);
    }

    public int countNodes(BTNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.getLeft()) + countNodes(root.getRight());
    }

    // Complete Binary Tree - all levels except possibly the last one are completely filled & all nodes are as left as possible.
    public boolean checkBinaryTreeCompleteness() {
        return checkBinaryTreeCompleteness(this.root, 0);
    }

    public boolean checkBinaryTreeCompleteness(BTNode root, int index) {
        int count = countNodes();
        if (index >= count) {
            return false;
        }
        else {
            return (checkBinaryTreeCompleteness(root.getLeft(),(2*index)+1) && checkBinaryTreeCompleteness(root.getRight(),(2*index)+2));
        }
    }

    //height of a Binary Tree
    public int heightBinaryTree() {
        return heightBinaryTree(this.root);
    }

    public int heightBinaryTree(BTNode root) {
        if (root == null) {
            return -1;
        }

        int lmax = heightBinaryTree(root.getLeft());
        int rmax = heightBinaryTree(root.getRight());

        if (lmax > rmax) {
            return lmax + 1;
        }
        else {
            return rmax + 1;
        }
    }

    //find min in a Binary Search Tree.
    public int findMinBST() {
        return findMinBST(this.root);
    }

    public int findMinBST(BTNode root) {
        if (root == null) {
            return -1;
        }
        while(root.getLeft() != null) {
            root = root.getLeft();
        }
        return root.getData();
    }

    //find min in a Binary Search Tree using recursion.
    public int findMinBSTRecursion() {
        return findMinBSTRecursion(this.root);
    }

    public int findMinBSTRecursion(BTNode root) {
        if (root == null) {
            return -1;
        }

        else if (root.getLeft() == null) {
            return root.getData();
        }

        return findMinBSTRecursion(root.getLeft());
    }

    //level order traversal using a queue.
    public void levelOrderTraversal() {
        levelOrderTraversal(root);
    }

    public void levelOrderTraversal(BTNode root) {
       if (root == null) {
           System.out.println("No elements in the tree");
           return;
       }

       Queue q = new Queue();

       q.enqueue(root);

       while (q.queueSize() > 0) {
           BTNode temp = q.dequeue();
           System.out.println(temp.getData());
           if (temp.getLeft() != null)
               q.enqueue(temp.getLeft());
           if (temp.getRight() != null)
               q.enqueue(temp.getRight());
       }
    }

    //check if a binary tree is a binary search tree
    public boolean isBST() {
        return isBST(this.root);
    }


    public boolean isBST(BTNode root) {
        if (root == null) {
            return true;
        }

        return isSubTreeLesser(root.getLeft(), root.getData())
                && isSubTreeGreater(root.getRight(), root.getData())
                && isBST(root.getLeft())
                && isBST(root.getRight());
    }

    public boolean isBSTOptimized() {
        return isBSTOptimized(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Not allowing duplicates.
    public boolean isBSTOptimized(BTNode root, int min, int max) {
        if (root == null) {
            return true;
        }

        if (root.getData() < min || root.getData() > max) {
            return false;
        }

        return isBSTOptimized(root.getLeft(), min, max-1)
                && isBSTOptimized(root.getRight(), min + 1, max);
    }

    List<Integer> templist = new ArrayList<Integer>();

    public boolean isBSTUsingInorderTraversalAndAuxilarySpace() {
        return isBSTUsingInorderTraversalAndAuxilarySpace(this.root);
    }

    public boolean isBSTUsingInorderTraversalAndAuxilarySpace(BTNode root) {
        getInorderTraversal(root);
        System.out.print(templist);
        for (int i = 0; i < templist.size()-1; i++) {
            if (templist.get(i) > templist.get(i+1)) {
                return false;
            }
        }
        return true;
    }

    public void getInorderTraversal(BTNode root) {
        if (root != null) {
            getInorderTraversal(root.getLeft());
            templist.add(root.getData());
            getInorderTraversal(root.getRight());
        }
    }

    public boolean isBSTUsingInorderTraversalWithoutAuxiliarySpace() {
        return isBSTUsingInorderTraversalWithoutAuxiliarySpace(this.root);
    }

    public int min = Integer.MIN_VALUE;
    public boolean isBSTUsingInorderTraversalWithoutAuxiliarySpace(BTNode root) {
        if (root != null) {
            isBSTUsingInorderTraversalWithoutAuxiliarySpace(root.getLeft());
            if (root.getData() < min) {
                return false;
            }
            else {
                min = root.getData();
            }
            isBSTUsingInorderTraversalWithoutAuxiliarySpace(root.getRight());
        }
        return true;
    }


    public boolean isSubTreeLesser(BTNode root, int val) {
        if (root == null) {
            return true;
        }

        if (root.getData() < val && isSubTreeLesser(root.getLeft(),val) && isSubTreeLesser(root.getRight(), val)) {
            return true;
        }

        else {
            return false;
        }
    }

    public boolean isSubTreeGreater(BTNode root, int val) {
        if (root == null) {
            return true;
        }

        if (root.getData() > val && isSubTreeGreater(root.getRight(), val) && isSubTreeGreater(root.getRight(), val)) {
            return true;
        }

        else {
            return false;
        }
    }

    //  main function.
    public static void main(String[] args) {
        BT binaryTree = new BT();

        binaryTree.insertBST(1);
        binaryTree.insertBST(2);
        binaryTree.insertBST(5);
        binaryTree.insertBST(3);
        binaryTree.insertBST(6);
        binaryTree.insertBST(4);

//        binaryTree.inorder();

//        System.out.println(binaryTree.countNodes());
//
//        //System.out.println(binaryTree.checkBinaryTreeCompleteness());
//
//        System.out.println("Height=" + binaryTree.heightBinaryTree());
//
//        binaryTree.levelOrderTraversal();
//
//        System.out.println("Min=" + binaryTree.findMinBST());
//
//        System.out.println("Min=" + binaryTree.findMinBSTRecursion());
//
//        System.out.println("IsBST=" + binaryTree.isBST());
//
//        System.out.println("IsBST=" + binaryTree.isBSTOptimized());
//
//        System.out.println("IsBST=" + binaryTree.isBSTUsingInorderTraversalAndAuxilarySpace());
//
//        System.out.println("IsBST=" + binaryTree.isBSTUsingInorderTraversalWithoutAuxiliarySpace());
    }
}


