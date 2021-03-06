package jscl.math.numeric.matrix;

import jscl.JsclMathContext;
import jscl.JsclMathContextImpl;
import jscl.math.numeric.NumericNumber;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: serso
 * Date: 2/14/12
 * Time: 4:18 PM
 */
public class SparseVectorTest extends VectorTest {

    private static final JsclMathContext mc = JsclMathContextImpl.defaultInstance();

    @Test
    public void testGetI() throws Exception {
        final int length = 10;

        final SparseVector.Builder b = new SparseVector.Builder(mc, length);
        for (int i = 0; i < length; i++) {
            b.setI(i, mc.newReal(i));
        }

        final Vector v = b.build();

        Assert.assertTrue(v.getLength() == length);
        for (int i = 0; i < length; i++) {
            Assert.assertEquals(mc.newReal(i), v.getI(i));
        }

        final SparseVector.Builder b1 = new SparseVector.Builder(mc, length);
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                b1.setI(i, mc.newReal(i));
            }
        }

        final Vector v1 = b1.build();

        Assert.assertTrue(v.getLength() == length);
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                Assert.assertEquals(mc.newReal(i), v1.getI(i));
            } else {
                Assert.assertEquals(mc.newReal(0), v1.getI(i));
            }
        }

        final SparseVector.Builder b2 = new SparseVector.Builder(mc, length);
        final Vector v2 = b2.build();
        for (int i = 0; i < length; i++) {
            Assert.assertEquals(mc.newReal(0), v2.getI(i));
        }
    }

    @NotNull
    @Override
    protected Vector.Builder<NumericNumber, NumericVector> getBuilder(@NotNull JsclMathContext mc, int length) {
        return new SparseVector.Builder(mc, length);
    }
}
