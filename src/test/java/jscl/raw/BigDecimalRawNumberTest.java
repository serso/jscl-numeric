package jscl.raw;

import jscl.*;
import junit.framework.TestCase;

/**
 * User: serso
 * Date: 2/17/12
 * Time: 2:12 PM
 */
public class BigDecimalRawNumberTest extends TestCase {

	public void testCompareTo() throws Exception {
		JsclMathContext doubleMc = JsclMathContextImpl.newInstance(AngleUnit.rad, NumeralBase.hex, RawNumberType.DOUBLE);
		JsclMathContext bigDecimalMc = JsclMathContextImpl.newInstance(AngleUnit.rad, NumeralBase.hex, RawNumberType.BIG_DECIMAL);

		MathAssert.assertEquals(doubleMc.fromLong(1L), bigDecimalMc.fromLong(1L));
		MathAssert.assertEquals(doubleMc.fromDouble(1), bigDecimalMc.fromDouble(1));
		MathAssert.assertEquals(doubleMc.fromDouble(0.0d), bigDecimalMc.fromDouble(0.0d));
		MathAssert.assertEquals(doubleMc.fromDouble(1d/90921), bigDecimalMc.fromDouble(1d/90921));


		MathAssert.assertEquals(bigDecimalMc.fromLong(-10L), doubleMc.fromLong(-10L));
		MathAssert.assertEquals(bigDecimalMc.fromDouble(0.54), doubleMc.fromDouble(0.54));
		MathAssert.assertEquals(bigDecimalMc.fromDouble(1d/3), doubleMc.fromDouble(1d/3));
	}
}
