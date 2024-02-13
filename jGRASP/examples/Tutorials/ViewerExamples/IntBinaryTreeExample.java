
  /**
 * IntBinaryTreeExample - This example is intended to illustrate capabilities
 * of the Presentation Structure Identifier rendering of an integer-encoded
 * binary tree. This is a structure that would not automatically be recognized
 * and must be manually configured, by selecting
 * "Presentation - Structure Identifier" as the viewer then using the configure
 * (wrench icon) button on "Viewer Settings" to enter the structure parsing
 * expressions:
 *   Root Node:  (_tree_.length > 0)?0:-1
 *   Left Node:  _tree_[_node_]
 *   Right Node:  _tree_[_node_ + 1]
 *   Value: _tree_[_node_ + 2]
 *
 * Click the "Run in Canvas" button on the toolbar. Then on the canvas window,
 * click the "Play" button. Regulate the speed with the "Delay" slider.
 *
 * To end the program, click the "Stop" button on the canvas or debugger.
 */
public class IntBinaryTreeExample {
   public static void main(String[] args) {
      int[][] trees = new int[3][];
      trees[0] = new int[] { 3, 6, 0, -1, -1, 1, 9, 12,
            7, -1, -1, 5, -1, -1, 10 };
      trees[1] = new int[] { 3, 6, 0, -1, -1, 1, 12, 9,
            7, -1, -1, 5, -1, -1, 10 };
      trees[2] = new int[] { 3, 6, 0, -1, -1, 1, -1, 9,
            7, -1, -1, 5 };
      int[] ia;
      for (int i = 0; i < 100; i++) {
         ia = trees[i % 3];
      }
   }
}