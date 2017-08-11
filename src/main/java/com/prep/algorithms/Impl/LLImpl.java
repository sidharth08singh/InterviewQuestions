package com.prep.algorithms.Impl;

import java.util.HashMap;

/**
 * Created by sidharth1 on 7/11/17.
 */

public class LLImpl {

    public LLNode head;

    public LLImpl() {
        this.head = null;
    }

    public LLNode createNewNode(int data) {
        return new LLNode(data);
    }

    /**
     * Add a node to the end of a list.
     * @param data
     * T = O(n)
     */
    public void add(int data) {
        LLNode newnode = createNewNode(data);
        if (head == null) {
            head = newnode;
        }
        else {
            LLNode cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = newnode;
        }
    }

    /**
     * Add a node at a specified index.
     * @param data
     * @param index index i = (i+1)th element in the list.
     * Best case - 0(1) when inserting at front
     * Worst case - O(n) when inserting at end
     */
    public void addAtIndex(int data, int index) {
        LLNode newnode = createNewNode(data);
        LLNode prev = null;
        LLNode current = head;
        if (index == 0) {
            newnode.next = head;
            head = newnode;
            return;
        }
        int itr = 0;
        while (current != null && itr!=index)  {
            prev = current;
            current = current.next;
            itr++;
        }
        if(current != null) {
            newnode.next = prev.next;
            prev.next = newnode;
        }
        else {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    /**
     * Get the size of the list.
     * @return int
     * T = O(n)
     */
    public int size() {
        LLNode current = head;
        int count = 0;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    /**
     * Get the element at index.
     * @param index
     * @return int data of the element indexed.
     * Best case - 0(1) when retrieving from the front.
     * Worst case - 0(n) when retrieving from the end.
     */
    public int get(int index) {
        LLNode current = head;
        int itr = 0;
        while (current != null) {
            if (itr == index)
                return current.data;
            itr++;
            current = current.next;
        }
        throw new IndexOutOfBoundsException("Index out of bounds");
    }

    /**
     * Remove an element at index.
     * @param index
     * Best case - O(1) when removing from the front
     * Worst case - O(n) when removing from rear
     */
    public void remove(int index) {
        LLNode prev = null;
        LLNode current = head;
        int itr = 0;
        while (current != null) {
            if (itr == index) {
                if (itr == 0) {
                    head = current.next;
                }
                else {
                    prev.next = current.next;
                }
                break;
            }
            itr++;
            prev = current;
            current = current.next;
        }
    }

    /**
     * Print a string representation.
     * @return
     */
    public void printList() {
        LLNode current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    /**
     * Remove duplicates from a linked list using hashmap.
     * Time - O(n)
     * Space - O(n)
     */
    public void removeDuplicatesUsingHash() {
        LLNode prev = null;
        LLNode current = head;
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        while(current != null) {
            if (hashMap.containsKey(current.data)) {
                prev.next = current.next;
                current = current.next;
            }
            else {
                hashMap.put(current.data, 1);
                prev = current;
                current = current.next;
            }
        }
    }

    public void removeDuplicatesWithoutBuffer() {
       LLNode ptr1, ptr2, dup;
       ptr1 = this.head;
       while (ptr1 != null) {
           ptr2 = ptr1.next;
           while (ptr2 != null) {
               if (ptr1.data == ptr2.data) {
                    ptr1.next = ptr2.next;
                    ptr2 = ptr2.next;
                    System.gc();
               }
               else {
                   ptr2 = ptr2.next;
               }
           }
           ptr1 = ptr1.next;
       }
    }

    /**
     * Get kth element from the end using size computation.
     * @param k
     * @return int - data of the kth element from the end.
     * Time: O(n)
     */
    public int getKthFromEndUsingSizeComputation(int k) {
        int size = 0;
        LLNode current = head;
        while(current != null) {
            current = current.next;
            size++;
        }
        System.out.println("size=" + size);
        System.out.println("k=" + k);
        System.out.println("kth from start=" + (size-k));

        int deletionIndex = size - k;
        current = head;
        for(int i = 0; i < deletionIndex; i++) {
            current = current.next;
        }
       return current.data;
    }

    /**
     * Get kth from end using 2 pointers.
     * @param k
     * @return int - data of the kth element from the end.
     * Time: O(n)
     */
    public int getKthFromEndUsing2Pointers(int k) {
        LLNode current, kth;
        current = kth = head;
        for (int i=0; i<k; i++) {
            kth = kth.next;
        }
        while(kth!=null) {
            kth = kth.next;
            current = current.next;
        }
        return current.data;
    }

    /**
     * Reverse a linked list using a stack
     * Time = O(n)
     * Space = O(n)
     * @return LLNode
     */
    public LLNode reverseUsingStack() {
        LLImpl stack = new LLImpl();
        LLNode current = this.head;
        while(current != null) {
            stack.addAtIndex(current.data, 0);
            current = current.next;
        }
        return stack.head;
    }

    /**
     * Reverse a linked list in place.
     * Time = O(n)
     * Space = Constant (three pointers).
     * @return
     */

    public LLNode reverseWithoutAuxiliaryMemory() {
        LLNode left, current, right;
        left = this.head;
        if (left == null) return null;
        if (left.next == null) return left;
        current = left.next;
        right = current.next;
        left.next = null;
        while (right != null) {
            current.next = left;
            left = current;
            current = right;
            right = right.next;
        }
        current.next = left;
        return current;
    }


    // 10 <- 20 <- 30
    // 10 -> 20 -> 30 -> 40
    public LLNode reverseRecursively() {
        return reverseRecursively(this.head);
    }

    public LLNode reverseRecursively(LLNode current) {
        LLNode newhead = null;
        if(current != null) {
            reverseRecursively(current.next);
            if(current.next == null) {
                newhead = current;
            }
            else {
                LLNode temp = current.next;
                temp.next = current;
                current.next = null;
            }
        }
        return newhead;
    }

    public LLNode placeAllEvenAfterOdd() {
        // if list has 0, 1 or 2 nodes only.
        if (head == null || head.next == null || head.next.next == null) {
            return this.head;
        }

        LLNode odd = this.head;
        LLNode even = odd.next;
        LLNode evenStart = even;

        while(true) {
            if (even == null || even.next == null) {
                odd.next = evenStart;
                even.next = null;
                break;
            }

            odd.next = even.next;
            odd = even.next;

            if (odd == null || odd.next == null) {
                odd.next = evenStart;
                even.next = null;
                break;
            }

            even.next = odd.next;
            even = odd.next;
        }
        return head;
    }

    /**
     * Driver function to test.
     * @param args
     */
    public static void main(String[] args) {
        LLImpl list = new LLImpl();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);


        list.printList();

        list.placeAllEvenAfterOdd();

        list.printList();

//        list.printList();
//        list.addAtIndex(40, 1);
//
//        list.printList();
//        list.addAtIndex(5,0);
//
//        list.printList();
//        list.addAtIndex(50,4);
//
//        list.printList();
//        System.out.println("Size=" + list.size());
//        list.add(100);
//        list.printList();
//
//        System.out.println("Element at index 0=" + list.get(0));
//        System.out.println("Element at last index=" + list.get(list.size()-1));
//        System.out.println("Element at index 4=" + list.get(4));
//
//        list.printList();
//        list.remove(4);
//        list.printList();
//        list.remove(0);
//        list.printList();
//        list.remove(1);
//        list.printList();
//        list.remove(list.size()-1);
//        list.printList();
//
//        list.add(40);
//        list.add(50);
//        list.add(60);
//        list.add(70);
//
//        list.printList();
//
////        //System.out.print(list.getKthFromEndUsingSizeComputation(3));
////        System.out.println(list.getKthFromEndUsing2Pointers(3));
////        System.out.println(list.getKthFromEndUsing2Pointers(1));
////        System.out.println(list.getKthFromEndUsing2Pointers(list.size()));
//
//        //list.printList();
//        //list.removeDuplicatesUsingHash();
//        list.printList();
//        list.head = list.reverseWithoutAuxiliaryMemory();
//        list.printList();
//        list.head = list.reverseUsingStack();
//        list.printList();
//        list.head = list.reverseRecursively();
//        list.printList();
    }
}
