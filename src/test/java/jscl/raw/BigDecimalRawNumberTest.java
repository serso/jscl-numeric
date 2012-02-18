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

	public void testSpecialDoubles() throws Exception {
		JsclMathContext bigDecimalMc = JsclMathContextImpl.newInstance(AngleUnit.rad, NumeralBase.hex, RawNumberType.BIG_DECIMAL);

		final RawNumber v = bigDecimalMc.fromDouble(2.5);
		final BigDecimalRawNumber nan = SpecialDoubleType.nan.getBigDecimalRawNumber();

		for (SpecialDoubleType specialDoubleType : SpecialDoubleType.values()) {
			final RawNumber specialNumber = specialDoubleType.getBigDecimalRawNumber();
			MathAssert.assertEquals(specialNumber, specialNumber.multiply(v));
			MathAssert.assertEquals(specialNumber, v.multiply(specialNumber));

			MathAssert.assertEquals(specialNumber, specialNumber.add(v));
			MathAssert.assertEquals(specialNumber, v.add(specialNumber));

			MathAssert.assertEquals(specialNumber, specialNumber.subtract(v));
			MathAssert.assertEquals(specialNumber, v.subtract(specialNumber));

			MathAssert.assertEquals(specialNumber, specialNumber.divide(v));
			MathAssert.assertEquals(specialNumber, v.divide(specialNumber));

			MathAssert.assertEquals(nan, specialNumber.acos());
			MathAssert.assertEquals(nan, specialNumber.sin());
			MathAssert.assertEquals(specialNumber, specialNumber.negate());
			if (specialDoubleType != SpecialDoubleType.negative_inf) {
				MathAssert.assertEquals(specialNumber, specialNumber.pow(v));
			}
		}

		MathAssert.assertEquals(bigDecimalMc.fromDouble(1.5707963267948966), SpecialDoubleType.positive_inf.getBigDecimalRawNumber().atan());
	}
}
