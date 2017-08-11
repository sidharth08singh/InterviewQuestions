package com.prep.algorithms.qf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sidharth1 on 8/1/17.
 */
public abstract class AbstractBinaryTree {

    protected BinaryTreeNode root;

    AbstractBinaryTree() {
        root = null;
    }

    List<Integer> queue = new ArrayList<Integer>();

    List<Integer> stack = new ArrayList<Integer>();

    List<Integer> inorderList = new ArrayList<Integer>();

    List<Integer> preorderList = new ArrayList<Integer>();

    List<Integer> postorderList = new ArrayList<Integer>();

    List<Integer> levelorderList = new ArrayList<Integer>();

    int preorderIndex = 0; //for constructing a binary tree using inorder and preorder traversals.

    int postorderIndex = 0; //for constructing a binary tree using inorder and postorder traversals.


    /************************************************************************************************
     * Insert into a Binary Tree
     ************************************************************************************************/
    BinaryTreeNode insert(BinaryTreeNode root, int data) {
        if (root == null) {
            return BinaryTreeNode.getNewNode(data);
        }

        if (root.getLeft() == null) {
            root.setLeft(insert(root.getLeft(), data));
        }

        if (root.getRight() == null) {
            root.setRight(insert(root.getRight(), data));
        } else {
            root.setLeft(insert(root.getLeft(), data));
        }

        return root;
    }

    /************************************************************************************************
     * Binary Tree traversal algorithms
     ************************************************************************************************/
    void inorder(BinaryTreeNode root) {
        if (root != null) {
            inorder(root.getLeft());
            System.out.println(root.getData());
            inorder(root.getRight());
        }
    }

    void preorder(BinaryTreeNode root) {
        if (root != null) {
            System.out.println(root.getData());
            inorder(root.getLeft());
            inorder(root.getRight());
        }
    }

    void postorder(BinaryTreeNode root) {
        if (root != null) {
            inorder(root.getLeft());
            inorder(root.getRight());
            System.out.println(root.getData());
        }
    }

    void levelOrderTraversal(BinaryTreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }

        List<BinaryTreeNode> queue = new ArrayList<BinaryTreeNode>();
        queue.add(root);
        BinaryTreeNode current;
        while (queue.size() > 0) {
            current = queue.remove(0);
            System.out.println(current.getData());
            if (current.getLeft() != null)
                queue.add(queue.size(), current.getLeft());
            if (current.getRight() != null)
                queue.add(queue.size(), current.getRight());
        }
    }

    void getInorder(BinaryTreeNode root) {
        if (root != null) {
            getInorder(root.getLeft());
            inorderList.add(root.getData());
            getInorder(root.getRight());
        }
    }

    void getPreorder(BinaryTreeNode root) {
        if (root != null) {
            preorderList.add(root.getData());
            getPreorder(root.getLeft());
            getPreorder(root.getRight());
        }
    }

    void getPostorder(BinaryTreeNode root) {
        if (root != null) {
            getPostorder(root.getLeft());
            getPostorder(root.getRight());
            postorderList.add(root.getData());
        }
    }

    void getLevelorder(BinaryTreeNode root) {
        if (root == null)
            return;
        List<BinaryTreeNode> queue = new ArrayList<BinaryTreeNode>();
        queue.add(root);
        BinaryTreeNode current;
        while (queue.size() > 0) {
            current = queue.get(0);
            levelorderList.add(current.getData());
            if (current.getLeft() != null)
                queue.add(current.getLeft());
            if (current.getRight() != null)
                queue.add(current.getRight());
        }

    }
    /************************************************************************************************
     * Is the given Binary Tree a Binary Search Tree?
     ************************************************************************************************/
    boolean isBST_BruteForce(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }
        return isSubTreeLesser(root.getLeft(), root.getData()) &&
                isSubTreeGreater(root.getRight(), root.getData()) &&
                isBST_BruteForce(root.getLeft()) &&
                isBST_BruteForce(root.getRight());
    }

    boolean isSubTreeLesser(BinaryTreeNode root, int data) {
        if (root == null)
            return true;
        if ((root.getData() < data) && isSubTreeLesser(root.getLeft(), data) && isSubTreeLesser(root.getRight(), data))
            return true;
        else
            return false;
    }

    boolean isSubTreeGreater(BinaryTreeNode root, int data) {
        if (root == null)
            return true;
        if ((root.getData() > data) && isSubTreeGreater(root.getLeft(), data) && isSubTreeGreater(root.getRight(), data))
            return true;
        else
            return false;
    }

    boolean isBST_UsingRange(BinaryTreeNode root) {
        return isBST_UsingRange(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean isBST_UsingRange(BinaryTreeNode root, int min, int max) {
       if (root == null)
           return true;

       if (root.getData() < min || root.getData() > max)
           return false;

       return isBST_UsingRange(root.getLeft(), min, root.getData()) &&
               isBST_UsingRange(root.getRight(), root.getData(), max);
    }

    int min = Integer.MIN_VALUE;
    boolean isBST_UsingInorder(BinaryTreeNode root) {
        if (root != null) {
         isBST_UsingInorder(root.getLeft());

         if(root.getData() < min)
             return false;
         else
             min = root.getData();

         isBST_UsingInorder(root.getRight());
        }
        return true;
    }

    /************************************************************************************************
     * Mirror Image of a Binary Tree
     ************************************************************************************************/
    BinaryTreeNode getMirrorImage(BinaryTreeNode root) {
        if (root != null) {
            getMirrorImage(root.getLeft());
            getMirrorImage(root.getRight());
            BinaryTreeNode temp = root.getLeft();
            root.setLeft(root.getRight());
            root.setRight(temp);
        }
        return root;
    }

    boolean isMirrorImage(BinaryTreeNode root, BinaryTreeNode mirror) {
       if (root == null && mirror == null)
           return true;

       if (root == null || mirror == null)
            return false;

       return isMirrorImage(root.getLeft(), mirror.getLeft()) &&
                root.getLeft() != mirror.getRight() && root.getRight() != root.getLeft() &&
                isMirrorImage(root.getRight(), mirror.getRight());

      /*
            return root.getData() == mirror.getData() &&
                    isMirrorImage(root.getLeft(), mirror.getRight()) &&
                    isMirrorImage(root.getRight(), mirror.getLeft());
       */
    }

    boolean isMirrorImage2(BinaryTreeNode root, BinaryTreeNode mirror) {
        if (root == null && mirror == null)
            return true;

        if (root == null || mirror == null)
            return false;

        BinaryTreeNode expectedMirror = getMirrorImage(root);

        return isIdentical(mirror, expectedMirror);
    }

    boolean isIsomorphic(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.getData() != root2.getData()) {
            return false;
        }

        return isIsomorphic(root1.getLeft(), root2.getLeft()) && isIsomorphic(root1.getRight(), root2.getRight()) ||
                isIsomorphic(root1.getLeft(), root2.getRight()) && isIsomorphic(root1.getRight(), root2.getLeft());
    }

    boolean isIdentical(BinaryTreeNode original, BinaryTreeNode clone) {
        if (root == null && clone == null)
            return true;

        if (root == null || clone == null)
            return false;

        return original.getData() == clone.getData() &&
                isIdentical(original.getLeft(), clone.getLeft()) &&
                isIdentical(original.getRight(), clone.getLeft());

    }

    /************************************************************************************************
     * Is the given Binary Tree a sum tree?
     ************************************************************************************************/
    int isSumTree(BinaryTreeNode root) {
        if (root != null) {
            int leftsum = isSumTree(root.getLeft());
            int rightsum = isSumTree(root.getRight());
            if (leftsum == -1 || rightsum == -1) {
                return -1;
            }
            if (!isLeaf(root) && root.getData() != leftsum + rightsum) {
                return -1;
            }
            return root.getData() + leftsum + rightsum;
        }
        return 0;
    }

    /************************************************************************************************
     * Given a binary tree and a number, return true if the tree has a root-to-leaf path such that
     * adding up all the values along the path equals the given number. Return false if no such path
     * can be found.
     ************************************************************************************************/
    boolean isRootToLeafSumPath(BinaryTreeNode root, int k) {
       if (root == null) {
           return (k == 0);
       }
       else {
           int newsum = k - root.getData();

           if (newsum == 0 && isLeaf(root)) {
               return true;
           }

           if (newsum != 0 && isLeaf(root)) {
               return false;
           }
           return isRootToLeafSumPath(root.getLeft(), newsum) ||
                   isRootToLeafSumPath(root.getRight(), newsum);
       }
    }

    /************************************************************************************************
     * Same problem as above, just a different way of writing, just as efficient.
     ************************************************************************************************/
    boolean isRootToLeafSumPath2(BinaryTreeNode root, int k) {
        int sum = 0;
        if(root == null) {
            return sum == 0;
        }
        else {
            sum += root.getData();
            if (sum == k)
                return true;

            isRootToLeafSumPath2(root.getLeft(), k);
            isRootToLeafSumPath2(root.getRight(),k);

            sum -= root.getData();
        }
        return false;
    }

    /*****************************************************************************************************
     *  Given a binary tree and a number, print all root to leaf paths such that
     * adding up all the values along the path equals the given number.
     *****************************************************************************************************/
    void printRootToLeafPathsWithSum(BinaryTreeNode root, int k) {
        int sum = 0;
        if (root == null) {
            return;
        }
        else {
            sum += root.getData();
            queue.add(queue.size(), root.getData());
            if (sum == k && isLeaf(root))
                System.out.println(String.format("Path %s was found with sum = %s", queue.toString(), k));

            printPathsWithSum(root.getLeft(),k);
            printPathsWithSum(root.getRight(),k);

            sum -= root.getData();
            queue.remove(0);
        }
    }

    /*****************************************************************************************************
     *  Given a binary tree and a number, return all root to leaf paths such that
     * adding up all the values along the path equals the given number.
     *****************************************************************************************************/
    List<List<Integer>> allpaths = new ArrayList<List<Integer>>();
    List<List<Integer>> getRootToLeafPathsWithSum(BinaryTreeNode root, int k) {
        int sum = 0;
        if (root == null) {
            return Arrays.asList();
        }
        else {
            sum += root.getData();
            queue.add(queue.size(), root.getData());
            if (sum == k && isLeaf(root))
                allpaths.add(queue);

            printPathsWithSum(root.getLeft(),k);
            printPathsWithSum(root.getRight(),k);

            sum -= root.getData();
            queue.remove(0);
        }
        return allpaths;
    }

    /*****************************************************************************************************
     * Given a binary tree and a number, print all paths from root to any node (not necessarily leaf)
     * such that adding up all the values along the path equals the given number.
     *****************************************************************************************************/
    void printPathsWithSum(BinaryTreeNode root, int k) {
        int sum = 0;
        if (root == null) {
            return;
        }
        else {
            sum += root.getData();
            queue.add(queue.size(), root.getData());
            if (sum == k)
                System.out.println(String.format("Path %s was found with sum = %s", queue.toString(), k));

            printPathsWithSum(root.getLeft(),k);
            printPathsWithSum(root.getRight(),k);

            sum -= root.getData();
            queue.remove(0);
        }
    }

    /*****************************************************************************************************
     * Given a binary tree and a node in the tree, print all ancestors of node, ie, all nodes in the path
     * from root to node.
     *****************************************************************************************************/
    boolean printAncestors(BinaryTreeNode root, BinaryTreeNode target) {
        if (root == null)
            return false;

        if (root.getData() == target.getData())
            return true;

        if (printAncestors(root.getLeft(), target) || printAncestors(root.getRight(), target)) {
            System.out.println(root.getData());
            return true;
        }

        return false;
    }

    /*****************************************************************************************************
     * Given a binary tree and a node in the tree, get all ancestors of node, ie, all nodes in the path
     * from root to node.
     * The list of ancestors (if target is found) will be present in queue.
     *****************************************************************************************************/
    boolean getAncestors(BinaryTreeNode root, BinaryTreeNode target) {
        if (root == null)
            return false;

        if (root.getData() == target.getData())
            return true;

        if (printAncestors(root.getLeft(), target) || printAncestors(root.getRight(), target)) {
            queue.add(root.getData());
            return true;
        }

        return false;
    }

    /*****************************************************************************************************
     * Complete Binary Tree - all levels except possibly the last one are completely filled & all nodes
     * are as left as possible.
     *****************************************************************************************************/
    boolean isCompleteBinaryTree(BinaryTreeNode root) {
        return true;
    }

    /*****************************************************************************************************
     * Given two binary trees, check if the first tree is subtree of the second one. A subtree of a tree T
     * is a tree S consisting of a node in T and all of its descendants in T. The subtree corresponding
     * to the root node is the entire tree; the subtree corresponding to any other node is called a proper subtree.
     *****************************************************************************************************/

    //approach 1: complexity - O(mn) where m = number of nodes in subtree and n = number of nodes in the main tree
    boolean isSubTree(BinaryTreeNode root, BinaryTreeNode subtreeroot) {
        if (root == null && subtreeroot == null)
            return true;

        if (root != null && subtreeroot == null)
            return true;

        if (root == null && subtreeroot != null)
            return false;

        if (isIdentical(root,subtreeroot)) {
            return true;
        }

        return isSubTree(root.getLeft(), subtreeroot) || isSubTree(root.getRight(), subtreeroot);
    }

    //approach 2: perform inorder and preorder traversal on the given tree and the subtree to be checked.
    boolean isSubTreeOptimized(BinaryTreeNode root, BinaryTreeNode subtreeroot) {
        return true;
    }

    /*****************************************************************************************************
     * Construct Tree from given Inorder and Preorder traversals
     * Assumption: this method assumes that the inorder and preorder arrays are correct corresponding to a tree.
     * Tip: if needed, add another helper method to verify that contents of inorder and preorder are the same
     * either through sorting or by using a hash, (the 2 arrays will have different order of-course)
     *****************************************************************************************************/
    BinaryTreeNode buildBinaryTreeUsingInorderAndPreOrder(int[] inorder, int[] preorder, int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd)
            return null;

        BinaryTreeNode current = BinaryTreeNode.getNewNode(preorder[preorderIndex]);
        preorderIndex++;

        if (inorderStart == inorderEnd)
            return current;

        //assumption is that the element is guaranteed to be found in the inorder array, ie, the given inorder and preorder arrays are right.
        int inorderIndex = search(inorder, inorderStart, inorderEnd, current.getData());

        current.setLeft(buildBinaryTreeUsingInorderAndPreOrder(inorder, preorder, inorderStart, inorderIndex-1));
        current.setRight(buildBinaryTreeUsingInorderAndPreOrder(inorder, preorder, inorderIndex+1, inorderEnd));

        return current;
    }

    /*****************************************************************************************************
     * Construct Tree from given Inorder and Postorder traversals
     * Assumption: this method assumes that the inorder and postorder arrays are correct corresponding to a tree.
     * Tip: if needed, add another helper method to verify that contents of inorder and postorder are the same
     * either through sorting or by using a hash, (the 2 arrays will have different order of-course)
     *****************************************************************************************************/
    BinaryTreeNode buildBinaryTreeUsingInorderAndPostorder(int[] inorder, int[] postorder) {
        postorderIndex = inorder.length - 1;
        return  buildBinaryTreeUsingInorderAndPostorder(inorder, postorder, 0, inorder.length-1);
    }

    BinaryTreeNode buildBinaryTreeUsingInorderAndPostorder(int[] inorder, int[] postorder, int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd)
            return null;

        BinaryTreeNode current = BinaryTreeNode.getNewNode(postorder[postorderIndex]);
        postorderIndex--;

        //if leaf node found, return that node itself.
        if (inorderStart == inorderEnd)
            return current;

        //assumption is that the element is guaranteed to be found in the inorder array, ie, the given inorder and preorder arrays are right.
        int inorderIndex = search(inorder, inorderStart, inorderEnd, current.getData());

        current.setLeft(buildBinaryTreeUsingInorderAndPreOrder(inorder, postorder, inorderStart, inorderIndex-1));
        current.setRight(buildBinaryTreeUsingInorderAndPreOrder(inorder, postorder, inorderIndex+1, inorderEnd));

        return current;
    }

    /************************************************************************************************
     * Lowest Common Ancestor in a Binary Tree (Using POST ORDER pattern)
     ************************************************************************************************/
    BinaryTreeNode lowestCommonAncestor(BinaryTreeNode root, BinaryTreeNode node1, BinaryTreeNode node2) {
        if (root == null) { return null; }

        if (root.getData() == node1.getData() || root.getData() == node2.getData()) {
            return root;
        }

        BinaryTreeNode left = lowestCommonAncestor(root.getLeft(), node1,  node2);
        BinaryTreeNode right = lowestCommonAncestor(root.getRight(), node1,  node2);

        if (left != null && right != null) return root;

        else if (left != null || right != null) return left != null ?  left : right;

        else return null;
    }

    /************************************************************************************************
     * Lowest Common Ancestor Using extra space.
     ************************************************************************************************/
    BinaryTreeNode lowestCommonAncestorUsingExtraSpace(BinaryTreeNode root, BinaryTreeNode node1, BinaryTreeNode node2) {
       if (root == null) return null;

       List<BinaryTreeNode> list1, list2;

       getRootToNodePathList(root, node1);
       list1 = rootToNodePath;

       rootToNodePath.clear();

       getRootToNodePathList(root, node2);
       list2 = rootToNodePath;

       int size = list1.size() < list2.size() ? list1.size() : list2.size();
       int i = 0;

       for(i=0; i < size; i++) {
           if (list1.get(i) != list2.get(i)) {
               break;
           }
       }

       return list1.get(i-1);
    }

    /************************************************************************************************
     * Is the node present in a Binary Tree?
     ************************************************************************************************/
    boolean isExists(BinaryTreeNode root, BinaryTreeNode node) {

    }


    /************************************************************************************************
     * Distance between root and a node
     * Defined as number of edges between root and the node.
     ************************************************************************************************/
    int distanceFromRoot(BinaryTreeNode root, BinaryTreeNode node) {

    }

    int distanceFromRootRecursively(BinaryTreeNode root, BinaryTreeNode node) {

    }

    /*************************************************************************************************************
     * Distance between 2 nodes in a binary search tree.
     * Calculated as: distanceFromRoot(node1) + distanceFromRoot(node2) - 2*distanceFromRoot(LowestCommonAncestor)
     *************************************************************************************************************/
    int distanceBetween2Nodes(BinaryTreeNode root, BinaryTreeNode node1, BinaryTreeNode node2) {

    }


    /******************************************************************************************************
     * Helper methods
     ******************************************************************************************************/
    //is given node a leaf node?
    boolean isLeaf(BinaryTreeNode root) {
        return root.getLeft() == null && root.getRight() == null;
    }

    //sum of elements in a list
    int sumList(List<BinaryTreeNode> list) {
        int sum = 0;
        for(BinaryTreeNode node : list) {
            sum += node.getData();
        }
        return sum;
    }

    //max number of edges between the root and any leaf node in a binary tree
    int height(BinaryTreeNode root) {
       if (root == null)
           return -1; //return 0 if height of a tree is defined as the max number of nodes between root and any leaf node
       else {
           return max(height(root.getLeft()), height(root.getRight())) + 1;
       }
    }

    //number of nodes in a binary tree
    int count(BinaryTreeNode root) {
        if (root == null)
            return 0;
        else {
            return 1 + count(root.getLeft()) + count(root.getRight());
        }
    }

    //find max of 2 numbers
    int max(int a, int b) {
        return a >= b ? a : b;
    }

    //search in an array
    int search(int[] arr, int start, int end, int data) {
        int res = -1;
        for (int i = start; i <=end; i++) {
            if (arr[i] == data) {
                res = i;
            }
        }
        return res;
    }

    List<BinaryTreeNode> rootToNodePath = new ArrayList<BinaryTreeNode>();
    //return list of nodes from root to a node
    BinaryTreeNode getRootToNodePathList(BinaryTreeNode root, BinaryTreeNode node) {
        if (root == null) return null;

        rootToNodePath.add(root);

        if (root.getData() == node.getData()) {
            return root;
        }

        BinaryTreeNode left = getRootToNodePathList(root.getLeft(), node);
        BinaryTreeNode right = getRootToNodePathList(root.getRight(), node);

        //not found in left and right
        if (left == null && right == null) {
            rootToNodePath.remove(root);
            return null;
        }

        //found in either left or right
        else {
            return left != null ? left : right;
        }
    }
}
