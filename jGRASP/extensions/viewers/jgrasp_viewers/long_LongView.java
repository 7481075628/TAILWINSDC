/* 2.0.6/2.0.7 Unified. */
package jgrasp_viewers;


import java.util.Locale;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Value;


/** Primitive long detail viewer. **/
public class long_LongView extends IntView {

   /** Creates a new long viewer.
    *
    * @param vcd creation data. **/
   public long_LongView(final ViewerCreateData vcd) {
   }


   /** {@inheritDoc} **/
   @Override
   public String getDisplayText(final Value value, final DebugContext context) throws ViewerException {
      long val = value.toLong(context);
      return "Decimal: " + val + "\nHex: 0x" + Long.toHexString(val).toUpperCase(Locale.ENGLISH) + "\nOctal: 0"
            + Long.toOctalString(val) + "\nBinary: " + bitString(val, 63);
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("Detail viewer for longs");
      vi.setLongDescription("This viewer displays a long in decimal, hex, octal, and binary forms.");
   }
}
