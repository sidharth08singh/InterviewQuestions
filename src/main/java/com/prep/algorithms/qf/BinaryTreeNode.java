package com.prep.algorithms.qf;

/**
 * Created by sidharth1 on 8/1/17.
 */
public class BinaryTreeNode {

    private int data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    BinaryTreeNode(int data) {
       this.data = data;
       this.left = null;
       this.right = null;

    }

    static BinaryTreeNode getNewNode(int data) {
        return new BinaryTreeNode(data);
    }

    //Getters and setters.
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
}
