/* 2.0.6/2.0.7 Unified. */
package jgrasp_viewers_cpp;


import java.util.Locale;
import jgrasp.viewer.Util;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Value;


/** Primitive char detail viewer. **/
public class char_CharView extends IntView {

   /** Creates a new char viewer.
    *
    * @param vcd creation data. **/
   public char_CharView(final ViewerCreateData vcd) {
   }


   /** {@inheritDoc} **/
   @Override
   public String getDisplayText(final Value value, final DebugContext context) throws ViewerException {
      int val = value.toChar(context) & 0xff;
      return "Char: " + val + "\nSource format: '" + Util.encodeCppChar((byte) val) + "'\nDecimal: " + val
            + "\nHex: 0x" + Integer.toHexString(val).toUpperCase(Locale.ENGLISH) + "\nOctal: 0"
            + Integer.toOctalString(val) + "\nBinary: " + bitString(val, 7);
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("Detail viewer for chars");
      vi.setLongDescription("This viewer displays a char and its source format, and its numeric value in decimal, "
            + "hex, octal, and binary forms. The source format may not be the same as in the actual source file, but "
            + "would produce the same char.");
   }
}
