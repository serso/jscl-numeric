package jscl.math.numeric.matrix;

import jscl.IBuilder;
import jscl.math.numeric.INumber;
import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 2/9/12
 * Time: 6:08 PM
 */
public interface Vector<
		N extends INumber,
		V extends Vector<N, V>> extends CommonVectorInterface<N> {

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
	N multiply(@NotNull V that);

	boolean isTransposed();

	/*
	 * ********************************
	 * 		SCALAR ARITHMETIC OPERATIONS
	 * ********************************
	 */

	@NotNull
	V multiply(@NotNull N that);

	@NotNull
	V divide(@NotNull N that);

	@NotNull
	V transpose();

	/**
	 * Main interface for building new vector
	 * NOTE: this builder provides access to the elements and this is the last point where the elements might be modified
	 *
	 * @param <V>
	 */
	public static interface Builder<
			N extends INumber,
			V extends Vector<N, V>> extends CommonVectorInterface<N>, IBuilder<V> {

		void setI(int index, @NotNull N value);
	}
}
