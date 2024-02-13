package jgraspvex;

/** AVL tree example node class. */
public class AVLTreeNode<T extends Comparable<T>> {
 
   AVLTreeNode<T> left;
   
   AVLTreeNode<T> right;
   
   AVLTreeNode<T> parent;
   
   int height;
   
   int balanceFactor;
   
   T value;


   public AVLTreeNode() {
   }

    
   public AVLTreeNode(T initValue) {
      value = initValue;
   }


   public Object getValue() {
      return value;
   }
}
