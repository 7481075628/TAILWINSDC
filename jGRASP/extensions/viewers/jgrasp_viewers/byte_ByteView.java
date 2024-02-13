/* 2.0.6/2.0.7 Unified. */
package jgrasp_viewers;


import java.util.Locale;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Value;


/** Primitive byte detail viewer. **/
public class byte_ByteView extends IntView {

   /** Creates a new byte viewer.
    *
    * @param vcd creation data. **/
   public byte_ByteView(final ViewerCreateData vcd) {
   }


   /** {@inheritDoc} **/
   @Override
   public String getDisplayText(final Value value, final DebugContext context) throws ViewerException {
      byte val = value.toByte(context);
      int intVal = val & 0xFF;
      return "Decimal: " + val + "\nHex: 0x" + Integer.toHexString(intVal).toUpperCase(Locale.ENGLISH) + "\nOctal: 0"
            + Integer.toOctalString(intVal) + "\nBinary: " + bitString(val, 7);
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("Detail viewer for bytes");
      vi.setLongDescription("This viewer displays a byte in decimal, hex, octal, and binary forms.");
   }
}
