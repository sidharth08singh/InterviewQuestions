package com.prep.algorithms.Impl;

/**
 * Created by sidharth1 on 7/8/17.
 */
public class BSTImpl {

    public BSTNode root;

    public BSTImpl() {
        this.root = null;
    }

    //insert a node into BST, return root node
    public void insertBST(int data) {
        this.root = insertBST(this.root, data);
    }

    public BSTNode insertBST(BSTNode root, int data) {
        if (root == null) {
            return new BSTNode(data);
        }

        if (data <= root.data) {
            root.left = insertBST(root.left, data);
        }
        else {
            root.right = insertBST(root.right, data);
        }

        return root;
    }

    /**
     * inorder traversal - print nodes.
     */
    public void inorder() {
        inorder(this.root);
    }

    public void inorder(BSTNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    /**
     * find node based on data
     * @param data
     * @return BSTNode if found, null if not found.
     */
    public BSTNode find(int data) {
        return find(this.root, data);
    }

    public BSTNode find(BSTNode root, int data) {
        if (root == null) {
            return null;
        }

        if (data == root.data) {
            return root;
        }

        if (data < root.data) {
            return find(root.left, data);
        }
        else {
            return find(root.right, data);
        }
    }

    /**
     * find min iteratively - return BSTNode.
     * @return BSTNode containing minimum element in the BST.
     */
    public BSTNode findMin() {
        return findMin(this.root);
    }

    public BSTNode findMin(BSTNode root) {
        if (root == null) {
            return null;
        }
        while(root.left != null) {
            root = root.left;
        }
        return root;
    }

    /**
     * find max iteratively - return BSTNode
     * @return BSTNode containing maximum element in the BST.
     */
    public BSTNode findMax() {
        return findMax(this.root);
    }

    public BSTNode findMax(BSTNode root) {
        if (root == null) {
            return null;
        }
        while(root.right != null) {
            root = root.right;
        }
        return root;
    }

    public BSTNode findMaxRecursively() {
        return findMaxRecursively(this.root);
    }

    public BSTNode findMaxRecursively(BSTNode root) {
        if (root == null) {
            return null;
        }
        if (root.right == null) {
            return root;
        }

        return findMaxRecursively(root.right);
    }

    /**
     * find min recursively - returns BSTNode.
     * @return
     */
    public BSTNode findMinRecursively() {
        return findMinRecursively(this.root);
    }

    public BSTNode findMinRecursively(BSTNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null) {
            return root;
        }
        return findMinRecursively(root.left);
    }

    /**
     * find parent of a given node
     * @param data
     * @return
     */
    public BSTNode findParent(int data) {
        return findParent(this.root, data);
    }

    public BSTNode findParent(BSTNode root, int data) {
        if (root == null) {
            return null;
        }

        if (root.data == data) {
            return null;
        }

        BSTNode parent = null;
        while(root != null) {
            if (data == root.data) {
                return parent;
            }
            if (data < root.data) {
                parent = root;
                root = root.left;
            }
            else {
                parent = root;
                root = root.right;
            }
        }
        return null;
    }

    /**
     * find parent of a given node recursively.
     * @param data
     * @return
     */
    public BSTNode findParentRecursively(int data) {
        BSTNode parent = null;
        return findParentRecursively(this.root, data, parent);
    }

    public BSTNode findParentRecursively(BSTNode root, int data, BSTNode parent) {
        if (root == null) {
            return null;
        }

        if (data == root.data) {
            return parent;
        }

        if (data <= root.data) {
            return findParentRecursively(root.left, data, root);
        }

        else {
            return findParentRecursively(root.right, data, root);
        }
    }

    /**
     * delete node from BST, maintain BST property.
     * @param data
     */
    public void deleteNode(int data) {
        root = deleteNode(this.root, data);
    }

    public BSTNode deleteNode(BSTNode root, int data) {
        if (root == null) {
            return root;
        }

        if (data < root.data) {
            root.left = deleteNode(root.left, data);
        }
        else if(data > root.data){
            root.right = deleteNode(root.right, data);
        }
        else {
            if (root.left == null) {
                return root.right;
            }
            else if (root.right == null){
                return root.left;
            }
            else {
                root.data = findMin(root.right).data;
                root.right = deleteNode(root.right, root.data);
            }
        }
        return root;
    }

    /**
     * find inorder successor in a binary search tree.
     * @param data
     * @return
     */
    public BSTNode inorderSuccessor(int data) {
        return inorderSuccessor(this.root, data);
    }

    public BSTNode inorderSuccessor(BSTNode root, int data) {
        BSTNode node = find(data);
        if (node == null) {
            System.out.println("Node not found, therefore successor doesn't exist");
        }

        if (root == null) {
            System.out.println("Tree is empty, therefore successor doesn't exist");
        }

        if (node.right != null) {
            return findMin(node.right);
        }
        else {
            BSTNode successor = null;
            BSTNode current = root;
            while (current != node) {
                if (node.data < current.data) {
                    successor = current;
                    current = current.left;
                }
                else {
                    current = current.right;
                }
            }
            return successor;
        }
    }

    /**
     * find inorder predecessor in a binary search tree.
     * @param data
     * @return
     */
    public BSTNode inorderPredecessor(int data) {
        return inorderPredecessor(this.root, data);
    }

    public BSTNode inorderPredecessor(BSTNode root, int data) {
        return null;
    }


    public static void main(String[] args) {
        BSTImpl bstImpl = new BSTImpl();

        bstImpl.insertBST(50);
        bstImpl.insertBST(30);
        bstImpl.insertBST(20);
        bstImpl.insertBST(15);
        bstImpl.insertBST(10);
        bstImpl.insertBST(12);
        bstImpl.insertBST(18);
        bstImpl.insertBST(40);
        bstImpl.insertBST(42);
        bstImpl.insertBST(45);
        bstImpl.insertBST(70);
        bstImpl.insertBST(55);
        bstImpl.insertBST(65);
        bstImpl.insertBST(80);
        bstImpl.insertBST(75);
        bstImpl.insertBST(90);
        bstImpl.insertBST(100);
        bstImpl.insertBST(60);


        bstImpl.inorder();

//        BSTNode node = bstImpl.find(10);
//        if (node != null) {
//            System.out.print("\nFound : " + node.data);
//        }
//        else {
//            System.out.print("\nCould not find");
//        }
//
//        node = bstImpl.find(13);
//        if (node != null) {
//            System.out.print("\nFound : " + node.data);
//        }
//        else {
//            System.out.print("\nCould not find");
//        }
//
//        System.out.print("\nMin : " + bstImpl.findMin().data);
//        System.out.print("\nMin found recursively: " + bstImpl.findMinRecursively().data);
//
//        System.out.print("\nMax : " + bstImpl.findMax().data);
//        System.out.print("\nMax found recursively: " + bstImpl.findMaxRecursively().data);

//        try {
//            System.out.print("\nParent : " + bstImpl.findParent(50).data);
//            System.out.print("\nParent : " + bstImpl.findParentRecursively(50).data);
//        }
//        catch (NullPointerException e) {
//            System.out.print("\nNode not found, so parent=null");
//        }

//        bstImpl.deleteNode(30);
//
//        System.out.println();
//
//        bstImpl.inorder();
//
        System.out.println();

        try {
            System.out.println(bstImpl.inorderSuccessor(200).data);
        }
        catch (NullPointerException e) {
            System.out.println("Successor doesn't exist");
        }
//        System.out.println(bstImpl.inorderSuccessor(30).data);
//        System.out.println(bstImpl.inorderSuccessor(50).data);
//        System.out.println(bstImpl.inorderSuccessor(70).data);
//        System.out.println(bstImpl.inorderSuccessor(60).);
//        System.out.println(bstImpl.inorderSuccessor(100));
//        System.out.println(bstImpl.inorderSuccessor(200));

    }
}
