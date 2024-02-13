package jgraspvex;


/** Doubly linked list example node class. */
public class DoublyLinkedNode<T extends Comparable<T>> {
   
   DoublyLinkedNode<T> next;
   
   DoublyLinkedNode<T> prev;
   
   T value;


   public DoublyLinkedNode() {
   }

    
   public DoublyLinkedNode(T initValue) {
      value = initValue;
   }


   public Object getValue() {
      return value;
   }


   public void setValue(T newValue) {
      value = newValue;
   }
}
