package jgraspvex;


/** Linked list example node class. */
public class LinkedNode<T extends Comparable<T>> {
   
   LinkedNode<T> next;
   
   T value;


   public LinkedNode() {
   }

    
   public LinkedNode(T initValue) {
      value = initValue;
   }


   public T getValue() {
      return value;
   }


   public void setValue(T newValue) {
      value = newValue;
   }
}
