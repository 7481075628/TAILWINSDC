/* 2.0.6/2.0.7 Unified. */
package jgrasp_viewers;


import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.NoConversionException;
import jgrasp.viewer.jgrdi.Value;


/** Primitive float detail viewer. **/
public class float_FloatView extends FloatView {

   /** Creates a new float viewer.
    *
    * @param vcd creation data. **/
   public float_FloatView(final ViewerCreateData vcd) {
      super(31, 30, 23, 22, 0, 127);
   }


   /** {@inheritDoc} **/
   @Override
   public long getBits(final Value value, final DebugContext context) throws NoConversionException {
      return Float.floatToRawIntBits(value.toFloat(context));
   }


   /** {@inheritDoc} **/
   @Override
   public double getDoubleValue(final Value value, final DebugContext context) throws NoConversionException {
      return value.toDouble(context);
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("Detail viewer for floats");
      vi.setLongDescription("This viewer displays a float, and the details of its binary representation.");
   }


   /** {@inheritDoc} **/
   @Override
   public String getMantissaText(final double mantissa) {
      return Float.toString((float) mantissa);
   }


   /** {@inheritDoc} **/
   @Override
   public String getValueText(final Value value, final DebugContext context) throws NoConversionException {
      return Float.toString(value.toFloat(context));
   }
}
