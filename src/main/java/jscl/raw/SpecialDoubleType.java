package jscl.raw;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * User: serso
 * Date: 2/18/12
 * Time: 5:32 PM
 */
enum SpecialDoubleType {

	nan(Double.NaN) {
		@Override
		protected boolean isOfType(double value) {
			return Double.isNaN(value);
		}
	},

	positive_inf(Double.POSITIVE_INFINITY) {
		@Override
		protected boolean isOfType(double value) {
			return this.doubleValue == value;
		}
	},

	negative_inf(Double.NEGATIVE_INFINITY) {
		@Override
		protected boolean isOfType(double value) {
			return this.doubleValue == value;
		}
	};

	protected final double doubleValue;

	@Nullable
	private BigDecimalRawNumber bigDecimalRawNumber;

	SpecialDoubleType(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public double getDoubleValue() {
		return doubleValue;
	}

	@NotNull
	public BigDecimalRawNumber getBigDecimalRawNumber() {
		BigDecimalRawNumber localBigDecimal = bigDecimalRawNumber;
		if (localBigDecimal == null) {

			if (this == nan) {
				localBigDecimal = BigDecimalRawNumber.NAN;
			} else if (this == positive_inf) {
				localBigDecimal = BigDecimalRawNumber.POSITIVE_INFINITY;
			} else if (this == negative_inf) {
				localBigDecimal = BigDecimalRawNumber.NEGATIVE_INFINITY;
			} else {
				throw new UnsupportedOperationException("BigDecimalRawNumber is not implemented for " + this);
			}

			bigDecimalRawNumber = localBigDecimal;
		}

		return localBigDecimal;
	}

	@Nullable
	public static SpecialDoubleType valueOf(double value) {
		for (SpecialDoubleType specialDoubleType : values()) {
			if (specialDoubleType.isOfType(value)) {
				return specialDoubleType;
			}
		}

		return null;
	}

	protected abstract boolean isOfType(double value);

	public static int compare(@Nullable SpecialDoubleType l, @Nullable SpecialDoubleType r) {
		if (l == null && r == null) {
			throw new IllegalArgumentException();
		} else if ( l == r ) {
			return 0;
		} else if (l == null) {
			return -compare(r, l);
		} else {
			// l != null
			if (r == null) {
				// r == null => comparing with normal number
				if (l == nan || l == negative_inf) {
					// nan and negative infinity by definition always less than normal numbers
					return -1;
				} else {
					return 1;
				}
			} else {
				// r != null => comparing with special number
				if ( l == nan ) {
					return -1;
				} else if ( l == negative_inf ) {
					if ( r == nan ) {
						return 1;
					} else {
						return -1;
					}
				} else if ( l == positive_inf ) {
					return 1;
				} else {
					throw new UnsupportedOperationException("Compare is not supported by " + l + " and " + r);
				}
			}
		}
	}
}
