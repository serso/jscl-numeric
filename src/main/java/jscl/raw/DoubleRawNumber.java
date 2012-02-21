package jscl.raw;

import jscl.math.NotDivisibleException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * User: serso
 * Date: 1/30/12
 * Time: 3:04 PM
 */
public class DoubleRawNumber implements RawNumber {

    private final double value;

    // BigDecimal value for fast access
    @Nullable
    private BigDecimalRawNumber bigDecimalValue;

    DoubleRawNumber(double value) {
        this.value = value;
    }

    @NotNull
    public static DoubleRawNumber newInstance(double value) {
        return new DoubleRawNumber(value);
    }

    @NotNull
    public static DoubleRawNumber newInstance(long value) {
        return new DoubleRawNumber(value);
    }

    @NotNull
    @Override
    public DoubleRawNumber negate() {
        return newInstance(-this.value);
    }

    @Override
    public int signum() {
        return this.value > 0d ? 1 : (this.value < 0d ? -1 : 0);
    }

    @NotNull
    @Override
    public DoubleRawNumber sqrt() {
        return newInstance(Math.sqrt(this.value));
    }

    @NotNull
    @Override
    public DoubleRawNumber atan2(@NotNull RawNumber that) {
        return newInstance(Math.atan2(this.value, that.asDoubleRawNumber().value));
    }

    @NotNull
    @Override
    public DoubleRawNumber log() {
        return newInstance(Math.log(this.value));
    }

    @NotNull
    @Override
    public DoubleRawNumber log10() {
        return newInstance(Math.log10(this.value));
    }

    @NotNull
    @Override
    public DoubleRawNumber sin() {
        return newInstance(Math.sin(this.value));
    }

    @NotNull
    @Override
    public DoubleRawNumber cos() {
        return newInstance(Math.cos(this.value));
    }

    @NotNull
    @Override
    public DoubleRawNumber exp() {
        return newInstance(Math.exp(this.value));
    }

    @Override
    public boolean isZero() {
        return signum() == 0;
    }

    @Override
    public boolean isOne() {
        return this.value == 1d;
    }

    @Override
    public boolean positive() {
        return signum() > 0;
    }

    @Override
    public boolean negative() {
        return signum() < 0;
    }

    @NotNull
    @Override
    public DoubleRawNumber pow(@NotNull RawNumber that) {
        return newInstance(Math.pow(this.value, that.asDoubleRawNumber().value));
    }

    @NotNull
    @Override
    public DoubleRawNumber acos() {
        return newInstance(Math.acos(this.value));
    }

    @Override
    public boolean isNaN() {
        return Double.isNaN(this.value);
    }

    @NotNull
    @Override
    public DoubleRawNumber asin() {
        return newInstance(Math.asin(this.value));
    }

    @NotNull
    @Override
    public DoubleRawNumber atan() {
        return newInstance(Math.atan(this.value));
    }

    @NotNull
    @Override
    public DoubleRawNumber tan() {
        return newInstance(Math.tan(this.value));
    }

    @NotNull
    @Override
    public DoubleRawNumber add(@NotNull RawNumber that) {
        return newInstance(this.value + that.asDoubleRawNumber().value);
    }

    @NotNull
    @Override
    public DoubleRawNumber subtract(@NotNull RawNumber that) {
        return newInstance(this.value - that.asDoubleRawNumber().value);
    }

    @NotNull
    @Override
    public DoubleRawNumber multiply(@NotNull RawNumber that) {
        return newInstance(this.value * that.asDoubleRawNumber().value);
    }

    @NotNull
    @Override
    public DoubleRawNumber divide(@NotNull RawNumber that) throws NotDivisibleException {
        return newInstance(this.value / that.asDoubleRawNumber().value);
    }

    @Override
    public int compareTo(@NotNull RawNumber that) {
        return Double.compare(this.value, that.asDoubleRawNumber().value);
    }

    @Override
    public boolean mathEquals(@NotNull RawNumber that) {
        return this.compareTo(that) == 0;
    }

    @NotNull
    @Override
    public DoubleRawNumber asDoubleRawNumber() {
        return this;
    }

    @Override
    public double asDouble() {
        return this.value;
    }

    @NotNull
    @Override
    public BigDecimalRawNumber asBigDecimalRawNumber() {
        BigDecimalRawNumber localeBigDecimal = bigDecimalValue;
        if (localeBigDecimal == null) {
            // no synchronization as we do not care about one instance (BigDecimal is immutable)
            bigDecimalValue = (localeBigDecimal = BigDecimalRawNumber.newInstance(value));
        }
        return localeBigDecimal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoubleRawNumber)) return false;

        DoubleRawNumber that = (DoubleRawNumber) o;

        if (Double.compare(that.value, value) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = value != +0.0d ? Double.doubleToLongBits(value) : 0L;
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return "DoubleRawNumber{" +
                "value=" + value +
                '}';
    }
}
