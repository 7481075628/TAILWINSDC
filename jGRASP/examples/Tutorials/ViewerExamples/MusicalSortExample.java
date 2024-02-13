import java.util.Arrays;


/**
 * MusicalSortExample - This example is intended to illustrate the capabilities of the musical bar graph viewer.  
 *
 * Open MusicalSortExample.jgrasp_canvas_xml .
 *
 * Set a breakpoint on the first line of code in the stop() method.
 *
 * Debug this program.
 *
 * When the debugger first stops, use the debugger controls to turn repeated resume on.
 *
 * Use the debugger controls to resume.
 *
 * The program will automatically animate, stopping at the breakpoint repeatedly. In addition to showing a bar graph
 * of the array and working copy (where used), each time a value changes, the viewer will play a musical note
 * corresponding to the change.
 *
 * Use the "Delay" bar on the viewer to control the animation speed.
 */
public class MusicalSortExample {


   public static void stop() {
      // Set breakpoint on next line.
      int i = 0;
   }


   private interface SortMethod {
      void sort(int[] array, int[] copy);
   }


   private static class SortData {
   
      private String name;
   
      private int length;
      
      private boolean usesCopy;
   
      private SortMethod sortMethod;
      
      public SortData(final String nameIn, final int lengthIn, final boolean usesCopyIn,
            final SortMethod sortMethodIn) {
         name = nameIn;
         length = lengthIn;
         usesCopy = usesCopyIn;
         sortMethod = sortMethodIn;
      }
   }


   private static SortData[] sorts = {
      new SortData("Selection Sort", 200, false, MusicalSortExample::selectionSort),
      new SortData("Quicksort", 200, false, MusicalSortExample::quicksort),
      new SortData("Top Down Merge Sort", 70, true, MusicalSortExample::mergeSort),
      new SortData("Insertion Sort", 70, false, MusicalSortExample::insertionSort),
      new SortData("Bottom Up Merge Sort", 70, true, MusicalSortExample::bottomUpMergeSort),
      new SortData("Bubble Sort", 50, false, MusicalSortExample::bubbleSort)
   };


   public static void main(String[] args) {
      String label = "Musical Sorting Example";
      for (int l = 0; l < 1000; l++) {
         int ind = l % sorts.length;
         int size = sorts[ind].length;
         int[] array = new int[size];
         int[] copy = sorts[ind].usesCopy? new int[size] : new int[0];
         label = "Fill With Random Values";
         for (int i = 0; i < size; i++) {
            int val = (int) (Math.random() * 200);
            array[i] = val;
            if (copy.length > 0) {
               copy[i] = val;
            }
            stop();
         }
         label = sorts[ind].name;
         try {
            Thread.sleep(1000);
         }
         catch (Exception e) {
         }
         sorts[ind].sortMethod.sort(array, copy);
         try {
            Thread.sleep(1000);
         }
         catch (Exception e) {
         }
      }
   }


   public static void selectionSort(final int[] array, final int[] copy) {
      for (int i = 0; i < array.length - 1; i++) {
         int min = array[i];
         int minInd = i;
         for (int j = i + 1; j < array.length; j++) {
            if (array[j] < min) {
               min = array[j];
               minInd = j;
            }
         }
         if (minInd != i) {
            int tmp = array[i];
            array[i] = array[minInd];
            array[minInd] = tmp;
            stop();
         }
      }
   }
   
   
   public static void insertionSort(final int[] array, final int[] copy) {
      for (int i = 1; i < array.length; i++) {
         for (int j = i; j > 0 && array[j - 1] > array[j]; j--) {
            int tmp = array[j];
            array[j] = array[j - 1];
            array[j - 1] = tmp;
            stop();
         }
      }
   }
   
   
   public static void mergeSort(final int[] array, final int[] copy) {
      splitAndMerge(array, 0, array.length, copy);
   }


   public static void splitAndMerge(final int[] array, final int start, final int end, final int[] copy) {
      if (start >= end - 1) {
         return;
      }
      int mid = (start + end) / 2;
      splitAndMerge(copy, start, mid, array);
      splitAndMerge(copy, mid, end, array);
      for (int k = start, i = start, j = mid; k < end; k++) {
         copy[k] = (i < mid && (j >= end || array[i] <= array[j]))? array[i++] : array[j++];
         stop();
      }
   }
   
   
   public static void bottomUpMergeSort(int[] array, int[] copy) {
      bottomUpMergeSort(array, copy, array.length);
   }
   
   
   public static void bottomUpMergeSort(int[] array1, int[] array2, int n) {
      boolean a1 = true;
      for (int width = 1; width < n; width = width * 2) {
         for (int i = 0; i < n; i = i + width * 2) {
            merge(a1? array1 : array2, i, Math.min(i + width, n), Math.min(i + width * 2, n), a1? array2 : array1);
         }
         a1 = !a1;
      }
   }


   public static void merge(int[] array1, int left, int right, int end, int[] array2) {
      for (int k = left, i = left, j = right; k < end; k++) {
         array2[k] = (i < right && (j >= end || array1[i] <= array1[j]))? array1[i++] : array1[j++];
         stop();
      } 
   }
   
   
   public static void quicksort(int[] array, int[] copy) {
      quicksort(array, 0, array.length - 1);
   }
   
   
   public static void quicksort(int[] array, int l, int h) {
      if (l < 0 || h < 0 || l >= h) {
         return;
      }
      int pivot = array[l + (h - l) / 2];
      int i = l - 1;
      int j = h + 1;
      while (true) {
         do {
            i++;
         } while (array[i] < pivot);
         do {
            j--;
         } while (array[j] > pivot);
         if (i >= j) {
            break;
         }
         int tmp = array[i];
         array[i] = array[j];
         array[j] = tmp;
         stop();
      }
    
      quicksort(array, l, j);
      quicksort(array, j + 1, h);
   }
   
   
   private static void bubbleSort(int[] array, int[] copy) {
      for (int i = 0; i < array.length; i++) {
         for (int j = 0; j < array.length - 1; j++) {
            if (array[j] > array[j + 1]){
               int tmp = array[j];
               array[j] = array[j + 1];
               array[j + 1] = tmp;
               stop();
            }
         }
      }
   }
}