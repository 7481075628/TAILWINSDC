package jgraspvex;


public class BinaryTree<T extends Comparable<T>> {
 
   private int size; 

   private BinaryTreeNode<T> root; 


   public BinaryTree() {
   }


   public void add(T value) {
      BinaryTreeNode<T> node = new BinaryTreeNode<>(value);
      if (root == null) {
         root = node;
         size++;
      }
      else {
         add(root, node);
      }
   }


   private void add(BinaryTreeNode<T> branch, BinaryTreeNode<T> node) {
      int cmp = branch.value.compareTo(node.value);
      if (cmp > 0) {
         if (branch.left == null) {
            branch.left = node;
            size++;
         }
         else {
            add(branch.left, node);
         }
      }
      else if (cmp < 0) {
         if (branch.right == null) {
            branch.right = node;
            size++;
         }
         else {
            add(branch.right, node);
         }
      } 
   }


   public boolean contains(T value) {
      return contains(root, value);
   }


   private boolean contains(BinaryTreeNode<T> branch, T value) {
      if (branch == null) {
         return false;
      }
      int cmp = branch.value.compareTo(value);
      if (cmp < 0) {
         return contains(branch.right, value);
      }
      else if (cmp > 0) {
         return contains(branch.left, value);
      }
      return true;
   }


   public boolean remove(T value) {
      if (root == null) {
         return false;
      }
      int cmp = root.value.compareTo(value);
      if (cmp == 0) {
         if (root.left == null) {
            root = root.right;
         }
         else if (root.right == null) {
            root = root.left;
         }
         else {
            BinaryTreeNode<T> tmp = root.right;
            root = root.left;
            add(root, tmp);
            size--;
         }
         size--;
         return true;
      }
      return remove(root, value, cmp);
   }


   private boolean remove(BinaryTreeNode<T> parentBranch, T value,
         int parentCmp) {
         
      BinaryTreeNode<T> branch = (parentCmp > 0)? parentBranch.left
            : parentBranch.right;
            
      if (branch == null) {
         return false;
      }
      int cmp = branch.value.compareTo(value);
      if (cmp == 0) {
         BinaryTreeNode<T> newRoot;
         if (branch.left == null) {
            newRoot = branch.right;
         }
         else if (branch.right == null) {
            newRoot = branch.left;
         }
         else {
            BinaryTreeNode<T> tmp = branch.right;
            newRoot = branch.left;
            add(newRoot, tmp);
            size--;
         }
         if (parentCmp > 0) {
            parentBranch.left = newRoot;
         }
         else {
            parentBranch.right = newRoot;
         }
         size--;
         return true;
      }
      return remove(branch, value, cmp);
   }


   public T[] elements() {
      @SuppressWarnings("unchecked")
      T[] result = (T[]) new Object[size];
      fillElements(root, 0, result);
      return result;
   }


   private int fillElements(BinaryTreeNode<T> branch, int index,
         T[] result) {
      if (branch.left != null) {
         index = fillElements(branch.left, index, result);
      }
      result[index++] = branch.value;
      if (branch.right != null) {
         fillElements(branch.right, index, result);
      }
      return index;
   }


   public void clear() {
      root = null;
      size = 0;
   }


   public int size() {
      return size;
   }
}

