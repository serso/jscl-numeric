package jscl.math.numeric.matrix;

import jscl.JsclMathContext;
import jscl.math.numeric.NumericNumber;
import jscl.math.numeric.Real;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

/**
 * User: serso
 * Date: 2/10/12
 * Time: 12:49 PM
 */
public class SparseVector extends NumericVector {

    @NotNull
    private final List<Entry> v;

    public static class Builder extends AbstractBuilder {

        @NotNull
        private final List<Entry> v;

        protected Builder(@NotNull JsclMathContext mc, int length) {
            this(mc, length, false);
        }

        protected Builder(@NotNull JsclMathContext mc, int length, final boolean transposed) {
            super(mc, length, transposed);

            this.v = new LinkedList<Entry>();
        }

        @NotNull
        @Override
        public NumericNumber getI(int index) {
            return getIFromList(v, mc, length, index);
        }

        @Override
        protected void setI0(int index, @NotNull NumericNumber value) {
            if (index >= length) {
                throw new IllegalArgumentException();
            }

            for (int i = 0; i < v.size(); i++) {
                final Entry e = v.get(i);
                if (e.getPos() == index) {
                    v.set(i, new Entry(index, value));
                    return;
                } else if (e.getPos() > index) {
                    v.add(i, new Entry(index, value));
                    return;
                }
            }

            v.add(new Entry(index, value));
        }

        @NotNull
        @Override
        protected SparseVector build0() {
            return new SparseVector(mc, v, length, transposed);
        }
    }

    private SparseVector(@NotNull JsclMathContext mc, @NotNull List<Entry> v, int length, boolean transposed) {
        super(mc, length, transposed);
        this.v = v;
    }

    @NotNull
    @Override
    protected Vector.Builder<NumericNumber, NumericVector> getBuilder(int length, boolean transposed) {
        return new Builder(mc, length, transposed);
    }

    @NotNull
    @Override
    public NumericNumber getI(int index) {
        return getIFromList(v, mc, length, index);
    }

    @NotNull
    private static NumericNumber getIFromList(final List<Entry> v, @NotNull final JsclMathContext mc, int length, int index) {
        if (index >= length) {
            throw new IllegalArgumentException();
        }

        // for each row check the entry
        for (final Entry e : v) {
            if (e.getPos() == index) {
                return e.getValue();
            } else if (e.getPos() > index) {
                break;
            }
        }

        return Real.ZERO(mc);
    }

    @NotNull
    @Override
    public SparseVector transpose() {
        return new SparseVector(mc, v, length, !transposed);
    }
}
