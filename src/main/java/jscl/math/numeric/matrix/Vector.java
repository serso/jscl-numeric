package jscl.math.numeric.matrix;

import jscl.math.numeric.NumericNumber;
import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 2/9/12
 * Time: 6:08 PM
 */
public interface Vector<V extends Vector<V>> extends CommonVectorInterface {

	/*
	 * ********************************
	 * 		VECTOR ARITHMETIC OPERATIONS
	 * ********************************
	 */
	@NotNull
	V add(@NotNull V that);

	@NotNull
	V subtract(@NotNull V that);

	@NotNull
	NumericNumber multiply(@NotNull V that);

	boolean isTransposed();

	/*
	 * ********************************
	 * 		SCALAR ARITHMETIC OPERATIONS
	 * ********************************
	 */

	@NotNull
	V multiply(@NotNull NumericNumber that);

	@NotNull
	V divide(@NotNull NumericNumber that);

	@NotNull
	V transpose();

	/**
	 * Main interface for building new vector
	 * NOTE: this builder provides access to the elements and this is the last point where the elements might be modified
	 *
	 * @param <T>
	 */
	public static interface Builder<T extends Vector> extends CommonVectorInterface {

		void setI(int index, @NotNull NumericNumber value);

		@NotNull
		T build();
	}
}