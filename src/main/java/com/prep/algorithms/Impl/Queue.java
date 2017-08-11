package com.prep.algorithms.Impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sidharth1 on 7/4/17.
 */
public class Queue {

    private List<BTNode> queue = new ArrayList<BTNode>();

    //default constructor.
    public Queue() {

    }

    //add to the rear of the queue.
    public void enqueue(BTNode node) {
        queue.add(node);
    }

    //remove from the front of a queue.
    public BTNode dequeue() {
        return queue.remove(0);
    }

    //peek at the queue - get the first element from the queue.
    public BTNode peek() {
       return queue.get(0);
    }

    //size of queue.
    public int queueSize() {
        return queue.size();
    }
}
