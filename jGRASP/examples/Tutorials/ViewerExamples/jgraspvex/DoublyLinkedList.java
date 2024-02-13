package jgraspvex;


import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class DoublyLinkedList<T extends Comparable<T>>
      extends AbstractSequentialList<T> {


   private class Iter implements ListIterator<T> {
   
      private int next;
      
      private DoublyLinkedNode<T> prevNode;
      
      private int targetNext = -1;
   
   
      public Iter(int nextIn) {
         next = nextIn;
         prevNode = getPrev(next);
      }
      
      
      public void add(T value) {
         DoublyLinkedList.this.add(next, value);
         next++;
         prevNode = prevNode.next;
         targetNext = -1;
      }
   
   
      public boolean hasNext() {
         return next < size;
      }
      
      
      public boolean hasPrevious() {
         return next > 0;
      }
      
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         targetNext = next;
         next++;
         prevNode = prevNode.next;
         return prevNode.value;
      }
      
      
      public int nextIndex() {
         return next;
      }
      
      
      public T previous() {
         if (!hasPrevious()) {
            throw new NoSuchElementException();
         }
         targetNext = next - 1;
         next--;
         T result = prevNode.value;
         prevNode = prevNode.prev;
         return result;
      }
      
      
      public int previousIndex() {
         return next - 1;
      }
      
      
      public void remove() {
         if (targetNext < 0) {
            throw new IllegalStateException();
         }
         if (targetNext < next) {
            next--;
            prevNode = prevNode.prev;
         }
         DoublyLinkedList.this.remove(targetNext);
      }
      
      
      public void set(T value) {
         if (targetNext < 0) {
            throw new IllegalStateException();
         }
         DoublyLinkedList.this.set(targetNext, value);
      }
   }

 
   private int size; 

   private DoublyLinkedNode<T> head;
   
   private DoublyLinkedNode<T> tail;


   public DoublyLinkedList() {
   }


   public boolean add(T value) {
      add(size, value);
      return true;
   }


   public void add(int index, T value) throws IndexOutOfBoundsException {
   
      if (index < 0 || index > size) {
         throw new IndexOutOfBoundsException();
      }
   
      DoublyLinkedNode<T> node = new DoublyLinkedNode<>(value);
      if (head == null) {
         node.next = node;
         node.prev = node;
         tail = node;
      }
      else {
         DoublyLinkedNode<T> prev = getPrev(index);
         node.next = prev.next;
         node.prev = prev;
         node.next.prev = node;
         prev.next = node;
      }
      if (index == 0) {
         head = node;
      }
      if (index == size) {
         tail = node;
      }
      
      size++;
   }
   
   
   private DoublyLinkedNode<T> getPrev(int index) {
      DoublyLinkedNode<T> prev = tail;
      if (index < size / 2) {
         for (int i = 0; i < index; i++) {
            prev = prev.next;
         }
      }
      else {
         for (int i = size; i > index; i--) {
            prev = prev.prev;
         }
      }
      return prev;
   }
   

   public T remove(int index) throws IndexOutOfBoundsException {
   
      if (index < 0 || index > size - 1) {
         throw new IndexOutOfBoundsException();
      }
   
      if (size == 1) {
         DoublyLinkedNode<T> result = head;
         head = null;
         tail = null;
         size--;
         return result.value;
      }
    
      DoublyLinkedNode<T> prev = getPrev(index);
      DoublyLinkedNode<T> result = prev.next;
      prev.next = prev.next.next;
      if (prev.next != null) {
         prev.next.prev = prev;
      }
      if (index == 0) {
         head = prev.next;
      }
      if (index == size - 1) {
         tail = prev;
      }
      size--;
      return result.value;
   }
   
   
   public int size() {
      return size;
   }


   public T get(int index) throws IndexOutOfBoundsException {
      if (index < 0 || index > size - 1) {
         throw new IndexOutOfBoundsException();
      }
      return getPrev(index).next.value;
   }
   
   
   public void selectionSort() {
      DoublyLinkedNode<T> pos = null;
      DoublyLinkedNode<T> scan = null;
      DoublyLinkedNode<T> min = null;
      for (pos = head; pos != tail; pos = pos.next) {
         min = pos;
         for (scan = min.next; scan != head; scan = scan.next) {
            T c1 = min.value;
            T c2 = scan.value;
            if (c2.compareTo(c1) < 0) {
               min = scan;
            }
         }
         if (min == pos) {
            continue;
         }
         swap(min, pos);
         pos = min;
      }   
   }


   public void swap(DoublyLinkedNode<T> n1, DoublyLinkedNode<T> n2) {
      DoublyLinkedNode<T> tmp = null;
      if (n1.next != n2 && n2.next != n1) {
         // Swap non-adjacent nodes.
         n1.prev.next = n2;
         n1.next.prev = n2;
         n2.prev.next = n1;
         n2.next.prev = n1;
         tmp = n1.next;
         n1.next = n2.next;
         n2.next = tmp;
         tmp = n1.prev;
         n1.prev = n2.prev;
         n2.prev = tmp;
      }
      else if (size != 2) {
         // Swap adjacent nodes.
         DoublyLinkedNode<T> swap1 = (n1.next == n2)? n1 : n2;
         DoublyLinkedNode<T> swap2 = (n1.next == n2)? n2 : n1;
         swap1.prev.next = swap2;
         swap2.next.prev = swap1;
         swap1.next = swap2.next;
         swap2.prev = swap1.prev;
         swap1.prev = swap2;
         swap2.next = swap1;
      }
      if (head == n1) {
         head = n2;
      }
      if (head == n2) {
         head = n1;
      }
      if (tail == n2) {
         tail = n1;
      }
      if (tail == n1) {
         tail = n2;
      }
   }
   
   
   public void printValues() {
      DoublyLinkedNode<T> node = head;
      if (node == null) {
         return;
      }
      do {
         if (node != head) {
            System.out.print(", ");
         }
         System.out.print(node.value);
         node = node.next;
      } while (node != head);
   }


   public ListIterator<T> listIterator(int index) {
      return new Iter(index);
   }
}

