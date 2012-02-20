package jscl.math.numeric.matrix;

import jscl.math.numeric.INumber;
import jscl.math.numeric.Numeric;
import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 2/2/12
 * Time: 12:09 PM
 */
public interface Matrix<E extends INumber, M extends Matrix<E, M>> extends CommonMatrixInterface<E> {

	/*
	 * ********************************
	 * 		ARITHMETIC OPERATIONS
	 * ********************************
	 */
	@NotNull
	M add(@NotNull M that);

	@NotNull
	M subtract(@NotNull M that);

	@NotNull
	M multiply(@NotNull M that);

	@NotNull
	Numeric multiply(@NotNull Vector that);

	/**
	 * @return matrix transposed to current
	 */
	@NotNull
	M transpose();

	@NotNull
	E trace();

	@NotNull
	E determinant();

	/**
	 * Main interface for building new matrix
	 * NOTE: this builder provides access to the elements and this is the last point where the elements might be modified
	 * @param <M>
	 */
	public static interface Builder<E extends INumber, M extends Matrix<E, M>> extends CommonMatrixInterface<E> {

		void setIJ(int row, int col, @NotNull E value);

		@NotNull
		M build();
	}
}
