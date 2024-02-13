
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;


/**
 * CollectionsExample -- This example is intended to
 * illustrate the capabilities of the jGRASP viewers for
 * the classes in the Java Collections Framework.
 *
 * To create a canvas, click the "Run in Canvas" button on the
 * toolbar. Then on the canvas, click the "Step" and Step-in" 
 * buttons as needed until you see objects of interest in the 
 * Variables tab and then drag one or more onto the canvas. 
 * Arrange the viewers and click the "Play" button.
 */
public class CollectionsExample {

   public static void main(String[] args) {
      Integer[] integerList = { Integer.valueOf(1), Integer.valueOf(2),
            Integer.valueOf(9), Integer.valueOf(7), Integer.valueOf(4),
            Integer.valueOf(8), Integer.valueOf(10), Integer.valueOf(5),
            Integer.valueOf(3), Integer.valueOf(6) };
      String[] stringList = {"cat", "dog", "mouse",
            "ant", "monkey", "flea", "gnu", "lizard",
            "frog", "horse" };
      int[] myint = new int[10];
      List<String> myVector = new Vector<>();
      List<String> myArrayList = new ArrayList<>();
      List<String> myLinkedList = new LinkedList<>();
      Stack<String> myStack = new Stack<>();
      Map<String, Integer> myHashMap = new HashMap<>(7, 1.5f);
      Map<String, Integer> myHashtable = new Hashtable<>(7, 1.5f);
      myHashMap.put(null, Integer.valueOf(0));
      Map<String, Integer> myTreeMap = new TreeMap<>();
   
      while (true) {
         for (int i = 0; i < integerList.length; i++) {
            myVector.add(stringList[i]);
            myArrayList.add(stringList[i]);
            myLinkedList.add(stringList[i]);
            myStack.push(stringList[i]);
            myHashMap.put(stringList[i], integerList[i]);
            myHashtable.put(stringList[i], integerList[i]);
            myTreeMap.put(stringList[i], integerList[i]);
            myint[i] = integerList[i].intValue();
         }
         for (int i = integerList.length - 1; i >= 0; i--) {
            myHashMap.remove(stringList[i]);
         }
         myArrayList.remove(3);
         myArrayList.add(3, "rat");
         myStack.pop();
         myVector.clear();
         myArrayList.clear();
         myLinkedList.clear();
         myHashMap.clear();
         myHashtable.clear();
         myTreeMap.clear();
      }
   }
}