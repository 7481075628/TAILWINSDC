package jgraspvex;

/** Binary tree example node class. */
public class BinaryTreeNode<T extends Comparable<T>> {
   
   BinaryTreeNode<T> left;
   
   BinaryTreeNode<T> right;
   
   T value;


   public BinaryTreeNode() {
   }

    
   public BinaryTreeNode(T initValue) {
      value = initValue;
   }


   public Object getValue() {
      return value;
   }
}
