package jscl.raw;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;

/**
 * User: serso
 * Date: 1/30/12
 * Time: 3:13 PM
 */
public class BigDecimalRawNumber implements RawNumber {

	@NotNull
	private final BigDecimal value;

	// infinity
	@Nullable
	private final SpecialDoubleType specialDoubleType;

	// double value for fast access
	@Nullable
	private DoubleRawNumber doubleValue;

	private static final java.math.MathContext mc = java.math.MathContext.UNLIMITED;

	public static final BigDecimalRawNumber POSITIVE_INFINITY = new BigDecimalRawNumber(BigDecimal.ZERO, SpecialDoubleType.positive_inf);
	public static final BigDecimalRawNumber NEGATIVE_INFINITY = new BigDecimalRawNumber(BigDecimal.ZERO, SpecialDoubleType.negative_inf);
	public static final BigDecimalRawNumber NAN = new BigDecimalRawNumber(BigDecimal.ZERO, SpecialDoubleType.nan);

	@NotNull
	public static BigDecimalRawNumber newInstance(@NotNull BigDecimal value) {
		return new BigDecimalRawNumber(value, null);
	}

	BigDecimalRawNumber(@NotNull BigDecimal value, @Nullable SpecialDoubleType specialDoubleType) {
		this.value = value;
		this.specialDoubleType = specialDoubleType;
	}

	@NotNull
	public static BigDecimalRawNumber newInstance(long value) {
		return new BigDecimalRawNumber(BigDecimal.valueOf(value), null);
	}

	@NotNull
	public static BigDecimalRawNumber newInstance(double value) {
		final SpecialDoubleType sdt = SpecialDoubleType.valueOf(value);

		if (sdt == null) {
			return new BigDecimalRawNumber(BigDecimal.valueOf(value), null);
		} else {
			return sdt.getBigDecimalRawNumber();
		}
	}

	@NotNull
	public static BigDecimalRawNumber newInstance(@NotNull DoubleRawNumber value) {
		return newInstance(value.asDouble());
	}

	@NotNull
	@Override
	public RawNumber negate() {
		if ( this.specialDoubleType == null ) {
			return newInstance(this.value.negate());
		} else {
			// todo serso: in case of infinities should we change we sign?
			return this.specialDoubleType.getBigDecimalRawNumber();
		}
	}

	@Override
	public int signum() {
		// todo serso: in case of infinities should we return correct sign?
		return this.value.signum();
	}

	@NotNull
	@Override
	public RawNumber sqrt() {
		return newInstance(asDoubleRawNumber().sqrt());
	}

	@NotNull
	@Override
	public RawNumber atan2(@NotNull RawNumber that) {
		return newInstance(asDoubleRawNumber().atan2(that));
	}

	@NotNull
	@Override
	public RawNumber log() {
		return newInstance(asDoubleRawNumber().log());
	}

	@NotNull
	@Override
	public RawNumber log10() {
		return newInstance(asDoubleRawNumber().log10());
	}

	@NotNull
	@Override
	public RawNumber sin() {
		return newInstance(asDoubleRawNumber().sin());
	}

	@NotNull
	@Override
	public RawNumber cos() {
		return newInstance(asDoubleRawNumber().cos());
	}

	@NotNull
	@Override
	public RawNumber exp() {
		return newInstance(asDoubleRawNumber().exp());
	}

	@Override
	public boolean isZero() {
		return mathEquals(BigDecimal.ZERO, this.value) && this.specialDoubleType == null;
	}

	@Override
	public boolean isOne() {
		return mathEquals(BigDecimal.ONE, this.value);
	}

	@Override
	public boolean positive() {
		return this.value.signum() > 0;
	}

	@Override
	public boolean negative() {
		return this.value.signum() < 0;
	}

	@NotNull
	@Override
	public RawNumber pow(@NotNull RawNumber that) {
		return newInstance(asDoubleRawNumber().pow(that));
	}

	@NotNull
	@Override
	public RawNumber acos() {
		return newInstance(asDoubleRawNumber().acos());
	}

	@Override
	public boolean isNaN() {
		return this.specialDoubleType == SpecialDoubleType.nan;
	}

	@NotNull
	@Override
	public RawNumber asin() {
		return newInstance(asDoubleRawNumber().asin());
	}

	@NotNull
	@Override
	public RawNumber atan() {
		return newInstance(asDoubleRawNumber().atan());
	}

	@NotNull
	@Override
	public RawNumber tan() {
		return newInstance(asDoubleRawNumber().tan());
	}

	@NotNull
	@Override
	public DoubleRawNumber asDoubleRawNumber() {
		// todo serso: warn about possible loss of precision
		DoubleRawNumber localeDoubleValue = doubleValue;
		if (localeDoubleValue == null) {
			// no synchronization as we do not care about one instance (double is immutable)

			final double tmp;
			if (this.specialDoubleType != null) {
				tmp = this.specialDoubleType.getDoubleValue();
			} else {
				tmp = this.value.doubleValue();
			}

			doubleValue = (localeDoubleValue = DoubleRawNumber.newInstance(tmp));
		}
		return localeDoubleValue;
	}

	@Override
	public double asDouble() {
		return asDoubleRawNumber().asDouble();
	}

	@NotNull
	@Override
	public BigDecimalRawNumber asBigDecimalRawNumber() {
		return this;
	}

	@NotNull
	@Override
	public RawNumber add(@NotNull RawNumber that) {
		return this.add(that.asBigDecimalRawNumber());
	}

	@NotNull
	public BigDecimalRawNumber add(@NotNull BigDecimalRawNumber that) {
		if (this.specialDoubleType == null && that.specialDoubleType == null) {
			return newInstance(this.value.add(that.value, mc));
		}
		return getSpecialBigDecimal(that);
	}

	@NotNull
	private BigDecimalRawNumber getSpecialBigDecimal(@NotNull BigDecimalRawNumber that) {
		if (this.specialDoubleType == SpecialDoubleType.negative_inf || that.specialDoubleType == SpecialDoubleType.negative_inf) {
			return NEGATIVE_INFINITY;
		} else if (this.specialDoubleType == SpecialDoubleType.positive_inf || that.specialDoubleType == SpecialDoubleType.positive_inf) {
			return POSITIVE_INFINITY;
		} else {
			return NAN;
		}
	}

	@NotNull
	@Override
	public RawNumber subtract(@NotNull RawNumber that) {
		return this.subtract(that.asBigDecimalRawNumber());
	}

	@NotNull
	public BigDecimalRawNumber subtract(@NotNull BigDecimalRawNumber that) {
		if (this.specialDoubleType == null && that.specialDoubleType == null) {
			return newInstance(this.value.subtract(that.value, mc));
		}
		return getSpecialBigDecimal(that);
	}


	@NotNull
	@Override
	public RawNumber multiply(@NotNull RawNumber that) {
		return this.multiply(that.asBigDecimalRawNumber());
	}

	@NotNull
	public BigDecimalRawNumber multiply(@NotNull BigDecimalRawNumber that) {
		if (this.specialDoubleType == null && that.specialDoubleType == null) {
			return newInstance(this.value.multiply(that.value, mc));
		}
		return getSpecialBigDecimal(that);
	}


	@NotNull
	@Override
	public RawNumber divide(@NotNull RawNumber that) {
		return this.divide(that.asBigDecimalRawNumber());
	}

	@NotNull
	public BigDecimalRawNumber divide(@NotNull BigDecimalRawNumber that) {
		if (this.specialDoubleType == null && that.specialDoubleType == null) {
			return newInstance(this.value.divide(that.value, mc));
		}
		return getSpecialBigDecimal(that);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BigDecimalRawNumber that = (BigDecimalRawNumber) o;

		if (specialDoubleType != that.specialDoubleType) return false;
		if (!value.equals(that.value)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = value.hashCode();
		result = 31 * result + (specialDoubleType != null ? specialDoubleType.hashCode() : 0);
		return result;
	}

	private static boolean mathEquals(@NotNull BigDecimal l, @NotNull BigDecimal r) {
		return l.compareTo(r) == 0;
	}

	@Override
	public boolean mathEquals(@NotNull RawNumber that) {
		return this.compareTo(that) == 0;
	}

	@Override
	public int compareTo(RawNumber o) {
		final BigDecimalRawNumber that = o.asBigDecimalRawNumber();
		int result = this.value.compareTo(that.value);
		if (result == 0) {
			if (this.specialDoubleType == that.specialDoubleType ) {
				return 0;
			} else if ( this.specialDoubleType != null || that.specialDoubleType != null ) {
				return SpecialDoubleType.compare(this.specialDoubleType, that.specialDoubleType);
			} else {
				return 0;
			}
		} else {
			return result;
		}
	}

	@Override
	public String toString() {
		return "BigDecimalRawNumber{" +
				"value=" + value +
				", specialDoubleType=" + specialDoubleType +
				'}';
	}
}
