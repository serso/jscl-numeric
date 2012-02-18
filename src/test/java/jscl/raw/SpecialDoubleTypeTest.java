package jscl.raw;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: serso
 * Date: 2/18/12
 * Time: 6:19 PM
 */
public class SpecialDoubleTypeTest {

	@Test
	public void testValueOf() throws Exception {
		Assert.assertEquals(SpecialDoubleType.nan, SpecialDoubleType.valueOf(Double.NaN));
		Assert.assertEquals(SpecialDoubleType.positive_inf, SpecialDoubleType.valueOf(Double.POSITIVE_INFINITY));
		Assert.assertEquals(SpecialDoubleType.negative_inf, SpecialDoubleType.valueOf(Double.NEGATIVE_INFINITY));
	}

	@Test
	public void testCompare() throws Exception {
		Assert.assertEquals(0, SpecialDoubleType.compare(SpecialDoubleType.nan, SpecialDoubleType.nan));

		Assert.assertEquals(-1, SpecialDoubleType.compare(SpecialDoubleType.nan, SpecialDoubleType.negative_inf));
		Assert.assertEquals(-1, SpecialDoubleType.compare(SpecialDoubleType.nan, SpecialDoubleType.positive_inf));
		Assert.assertEquals(1, SpecialDoubleType.compare(SpecialDoubleType.negative_inf, SpecialDoubleType.nan));
		Assert.assertEquals(1, SpecialDoubleType.compare(SpecialDoubleType.positive_inf, SpecialDoubleType.nan));

		Assert.assertEquals(1, SpecialDoubleType.compare(SpecialDoubleType.positive_inf, null));
	}
}
