package com.prep.algorithms.Impl;

/**
 * Created by sidharth1 on 7/1/17.
 */
public class BTNode {
    private int data;
    private BTNode left;
    private BTNode right;

    //Default Constructor
    public BTNode() {
        data = 0;
        left = null;
        right = null;
    }

    //Parameterized Constructor
    public BTNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BTNode getLeft() {
        return left;
    }

    public void setLeft(BTNode left) {
        this.left = left;
    }

    public BTNode getRight() {
        return right;
    }

    public void setRight(BTNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return("Data=" + this.data + "Left=" + this.left + "Right=" + this.right);
    }
}
