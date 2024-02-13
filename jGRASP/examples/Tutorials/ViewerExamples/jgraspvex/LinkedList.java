package jgraspvex;


import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class LinkedList<T extends Comparable<T>> extends AbstractSequentialList<T> {
 
 
   private class Iter implements ListIterator<T> {
   
      private int next;
      
      private LinkedNode<T> prevNode;
      
      private int targetNext = -1;
   
   
      public Iter(int nextIn) {
         next = nextIn;
         prevNode = getPrev(next);
      }
      
      
      public void add(T value) {
         LinkedList.this.add(next, value);
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
         if (prevNode == null) {
            prevNode = head;
         }
         else {
            prevNode = prevNode.next;
         }
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
         prevNode = getPrev(next);
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
            prevNode = getPrev(next);
         }
         LinkedList.this.remove(targetNext);
      }
      
      
      public void set(T value) {
         if (targetNext < 0) {
            throw new IllegalStateException();
         }
         LinkedList.this.set(targetNext, value);
      }
   }


   private int size; 

   private LinkedNode<T> head;


   public LinkedList() {
   }


   public boolean add(T value) {
      add(size, value);
      return true;
   }


   public void add(int index, T value) {
   
      if (index < 0 || index > size) {
         throw new IndexOutOfBoundsException();
      }
      
      LinkedNode<T> node = new LinkedNode<>(value);
      if (index == 0) {
         node.next = head;
         head = node;
      }
      else {
         LinkedNode<T> prev = getPrev(index);
         node.next = prev.next;
         prev.next = node;
      }
   
      size++;
   }
   

   private LinkedNode<T> getPrev(int index) {
      if (index == 0) {
         return null;
      }
      LinkedNode<T> prev = head;
      for (int i = 1; i < index; i++) {
         prev = prev.next;
      }
      return prev;
   }


   public T remove(int index) {
      if (index == 0) {
         Object result = head;
         size--;
         head = head.next;
         return head.value;
      }
      LinkedNode<T> prev = getPrev(index);
      LinkedNode<T> result = prev.next;
      size--;
      prev.next = prev.next.next;
      return result.value;
   }
   
   
   public int size() {
      return size;
   }


   public T get(int index) {
      LinkedNode<T> node = head;
      for (int i = 0; i < index; i++) {
         node = node.next;
      }
      return node.value;
   }
   
   
   public void selectionSort() {
      LinkedNode<T> pos = null;
      LinkedNode<T> posPrev = null;
      LinkedNode<T> scan = null;
      LinkedNode<T> scanPrev = null;
      LinkedNode<T> min = null;
      LinkedNode<T> minPrev = null;
      for (pos = head; pos != null; pos = pos.next) {
         min = pos;
         scanPrev = min;
         for (scan = min.next; scan != null; scan = scan.next) {
            T c1 = min.value;
            T c2 = scan.value;
            if (c2.compareTo(c1) < 0) {
               min = scan;
               minPrev = scanPrev;
            }
            scanPrev = scan;
         }
         if (min != pos) {
            swap(min, minPrev, pos, posPrev);
            pos = min; // Set breakpoint here for MusicalLinkedListSortExample.
         }
         posPrev = pos;
      }   
   }


   public void swap(LinkedNode<T> n1, LinkedNode<T> n1Prev,
         LinkedNode<T> n2, LinkedNode<T> n2Prev) {
      LinkedNode<T> tmp = null;
      if (n1Prev != n2 && n2Prev != n1) {
         // Swap non-adjacent nodes.
         if (n1Prev != null) {
            n1Prev.next = n2;
         }
         if (n2Prev != null) {
            n2Prev.next = n1;
         }
         tmp = n1.next;
         n1.next = n2.next;
         n2.next = tmp;
      }
      else {
         // Swap adjacent nodes.
         LinkedNode<T> prev = (n1Prev == n2)? n2Prev : n1Prev;
         LinkedNode<T> swap1 = (n1Prev == n2)? n2 : n1;
         LinkedNode<T> swap2 = (n1Prev == n2)? n1 : n2;
         if (prev != null) {
            prev.next = swap2;
         }
         swap1.next = swap2.next;
         swap2.next = swap1;
      }
      if (head == n1) {
         head = n2;
      }
      if (head == n2) {
         head = n1;
      }
   }
   
   
   public void printValues() {
      LinkedNode<T> node = head;
      while(node != null) {
         if (node != head) {
            System.out.print(", ");
         }
         System.out.print(node.value);
         node = node.next;
      }
   }


   public ListIterator<T> listIterator(int index) {
      return new Iter(index);
   }
}

