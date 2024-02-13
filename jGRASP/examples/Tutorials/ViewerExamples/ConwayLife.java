import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * ConwayLife - This example is intended to illustrate the capabilities of the 2D image viewer, which displays a
 * 2D structure (array, list, or combination of the two) of int or Integer as an image.  
 *
 * Open ConwayLife.jgrasp_canvas_xml .
 *
 * Set a breakpoint near the end of main() on the line indicated.
 *
 * Debug this program.
 *
 * When the debugger first stops, use the debugger controls to turn repeated resume on.
 *
 * Use the debugger controls to resume.
 *
 * The program will automatically animate, stopping at the breakpoint repeatedly.
 *
 * Use the "Delay" bar on the viewer to control the animation speed.
 */
public class ConwayLife {

   public static void main(String[] args) {
      int size = 100;
      int[][] board = new int[size][size];
      int[][] board2 = new int[size][size];
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            board[i][j] = (Math.random() < .45)? 0xffffff : 0;
         }
      }
      while (true) {
         for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
               int neighbors = 0;
               for (int id = -1; id <= 1; id++) {
                  for (int jd = -1; jd <= 1; jd++) {
                     if (id == 0 && jd == 0) {
                        continue;
                     }
                     if (board[(i + id + size) % size][(j + jd + size) % size] != 0) {
                        neighbors++;
                     }
                  }
               }
               
               boolean occupied = board[i][j] != 0;
               board2[i][j] = (neighbors == 3 || (occupied && neighbors == 2))
                     ? (occupied? 0xffffff : 0x0000ff) : 0;
            }
         }
         int[][] tmp = board;
         // Set breakpoint on next line.
         board = board2;
         board2 = tmp;
      }
   }

}
