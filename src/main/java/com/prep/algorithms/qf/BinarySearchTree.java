package com.prep.algorithms.qf;

/**
 * Created by sidharth1 on 8/1/17.
 */
public class BinarySearchTree extends AbstractBinaryTree {

    /************************************************************************************************
     * Insert into a Binary Search Tree
     ************************************************************************************************/
    @Override
    BinaryTreeNode insert(BinaryTreeNode root, int data) {
        if (root == null) {
           return BinaryTreeNode.getNewNode(data);
        }

        if (data <= root.getData()) {
            root.setLeft(insert(root.getLeft(),data));
        }

        else {
            root.setRight(insert(root.getRight(),data));
        }
        return root;
    }

    /************************************************************************************************
     * Find a node in Binary Search Tree
     ************************************************************************************************/
    BinaryTreeNode find(BinaryTreeNode root, int data) {
        if (root == null) {
            throw new NullPointerException("Element not found");
        }

        if (data == root.getData()) {
            return root;
        }

        if (data < root.getData()) {
            return find(root.getLeft(), data);
        }
        else {
            return find(root.getRight(), data);
        }
    }

    /************************************************************************************************
     * Find min in a Binary Search Tree
     ************************************************************************************************/
    BinaryTreeNode findMin(BinaryTreeNode root) {
        if (root == null) {
            throw new NullPointerException("Tree does not have any nodes");
        }

        if (root.getLeft() == null) {
            return root;
        }
        return findMin(root.getLeft());
    }

    /************************************************************************************************
     * Find parent in a Binary Search Tree
     ************************************************************************************************/
    BinaryTreeNode findParent(BinaryTreeNode root, int data) {
        if (root == null) {
            throw new NullPointerException("Node not found or tree contains no nodes");
        }

        BinaryTreeNode parent = null;

        while(root != null) {
            if(root.getData() == data)
                return parent;
            parent = root;
            if (data < root.getData()) {
                root = root.getLeft();
            }
            else {
                root = root.getRight();
            }
        }
        return null;
    }

    /************************************************************************************************
     * Delete a node from Binary Search Tree
     ************************************************************************************************/
    BinaryTreeNode delete(BinaryTreeNode root, int data) {
        BinaryTreeNode toBeDeleted = find(root,data);
        BinaryTreeNode parent = findParent(root, data);

        //leaf node
        if (toBeDeleted.getLeft() ==  null && toBeDeleted.getRight() == null) {
            if (parent.getLeft() == toBeDeleted)
                parent.setLeft(null);
            else
                parent.setRight(null);
        }

        //right child is null, left child is not null
        else if (toBeDeleted.getLeft() !=  null && toBeDeleted.getRight() == null) {
            if (parent.getLeft() == toBeDeleted)
                parent.setLeft(toBeDeleted.getLeft());
            else
                parent.setRight(toBeDeleted.getLeft());
        }

        //left child is null, right child is not null
        else if (toBeDeleted.getRight() !=  null && toBeDeleted.getLeft() == null) {
            if (parent.getLeft() == toBeDeleted)
                parent.setLeft(toBeDeleted.getRight());
            else
                parent.setRight(toBeDeleted.getRight());
        }

        //both left and right are not null
        else {
            BinaryTreeNode min = findMin(toBeDeleted.getRight());
            BinaryTreeNode minParent = findParent(toBeDeleted,min.getData());
            toBeDeleted.setData(min.getData());
            minParent.setLeft(null);
        }

        return root;
    }

    BinaryTreeNode deleteRec(BinaryTreeNode root, int data) {
        if (root == null)
            return null;

        if (data < root.getData()) {
            root.setLeft(deleteRec(root.getLeft(), data));
        }

        else if(data > root.getData()) {
            root.setRight(deleteRec(root.getRight(), data));
        }

        else {
            //node to be deleted is a leaf node
            if(root.getLeft() == null && root.getRight() == null) {
                return null;
            }

            //node to be deleted has left child null and right child not null
            else if(root.getLeft() == null && root.getRight() != null) {
                return root.getRight();
            }

            //node to be deleted has right child null and left child not null
            else if(root.getRight() == null && root.getLeft() != null) {
                return root.getLeft();
            }

            //node to be deleted has both left and right child as not null
            else {
                root.setData(findMin(root.getRight()).getData());
                root.setRight(deleteRec(root.getRight(),root.getData()));
            }
        }
        return root;
    }

    /************************************************************************************************
     * Successor/Predecessor in a Binary Search Tree
     ************************************************************************************************/
    BinaryTreeNode inorderSuccessor(BinaryTreeNode root) {
        return null;
    }

    BinaryTreeNode inorderPredecessor(BinaryTreeNode root) {
        return null;
    }

    /************************************************************************************************
     * Lowest Common Ancestor in a Binary Search Tree
     ************************************************************************************************/
    BinaryTreeNode lowestCommonAncestor(BinaryTreeNode root, BinaryTreeNode node1, BinaryTreeNode node2) {
        if (!isExists(root, node1) || !isExists(root, node2)) { return null;}

        if (root == null) { return null;}

        if (node1.getData() < root.getData() && node2.getData() < root.getData()) {
           return  lowestCommonAncestor(root.getLeft(), node1, node2);
        }

        if (node1.getData() < root.getData() && node2.getData() < root.getData()) {
            return  lowestCommonAncestor(root.getRight(), node1, node2);
        }

        return root;
    }

    /************************************************************************************************
     * Is the node present in a BST? (Not optimized to exploit properties of BST)
     ************************************************************************************************/
    boolean isExists(BinaryTreeNode root, BinaryTreeNode node) {
        if (root == null) {
            return false;
        }
        if (root.getData() == node.getData()) { return true; }
        return isExists(root.getLeft(), node) || isExists(root.getRight(), node);
    }

    /************************************************************************************************
     * Is the node present in a BST? (Optimized to exploit properties of BST)
     ************************************************************************************************/
    boolean isExistsOptimized(BinaryTreeNode root, BinaryTreeNode node) {
        if (root == null) {
            return false;
        }
        if (root.getData() == node.getData()) { return true; }
        if (node.getData() < root.getData()) {
            return isExistsOptimized(root.getLeft(), node);
        }
        else {
            return isExistsOptimized(root.getRight(), node);
        }
    }

    /************************************************************************************************
     * Distance between root and a node
     * Defined as number of edges between root and the node.
     ************************************************************************************************/
    int distanceFromRoot(BinaryTreeNode root, BinaryTreeNode node) {
        if (root == null) { return -1; }
        int dist = 0;
        boolean found = false;
        while (root != null) {
            if (node.getData() < root.getData()) {
                root = root.getLeft();
                dist ++;
            }
            else if (node.getData() > root.getData()) {
                root = root.getRight();
                dist ++;
            }

            else {
                found = true;
                break;
            }
        }

        if (found) return dist;
        else return -1;
    }

    int distanceFromRootRecursively(BinaryTreeNode root, BinaryTreeNode node) {
        if (root == null) {
            return -1;
        }

        if (root.getData() == node.getData()) {
            return 0;
        }

        else if (node.getData() < root.getData()) {
            int countleft = distanceFromRootRecursively(root.getLeft(), node);
            if (countleft != -1) countleft++;
            return countleft;
        }

        else {
            int countRight = distanceFromRootRecursively(root.getRight(), node);
            if (countRight != -1) countRight++;
            return countRight;
        }
    }

    /*************************************************************************************************************
     * Distance between 2 nodes in a binary search tree.
     * Calculated as: distanceFromRoot(node1) + distanceFromRoot(node2) - 2*distanceFromRoot(LowestCommonAncestor)
     *************************************************************************************************************/
    int distanceBetween2Nodes(BinaryTreeNode root, BinaryTreeNode node1, BinaryTreeNode node2) {
        if (node1 == null || node2 == null) {
            return -1;
        }
        BinaryTreeNode lca = lowestCommonAncestor(root, node1, node2);
        return (distanceFromRootRecursively(root, node1) + distanceFromRootRecursively(root, node2)) - 2 * distanceFromRootRecursively(root, lca);
    }
}
