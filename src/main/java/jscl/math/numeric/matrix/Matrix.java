package jscl.math.numeric.matrix;

import jscl.IBuilder;
import jscl.math.numeric.INumber;
import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 2/2/12
 * Time: 12:09 PM
 */

/**
 * @param <N> type of number
 * @param <V> type of vector with which matrix of current type can work
 * @param <M> type of matrix
 */
public interface Matrix<
        N extends INumber,
        V extends Vector<N, V>,
        M extends Matrix<N, V, M>> extends CommonMatrixInterface<N> {

    /*
      * ********************************
      *         ARITHMETIC OPERATIONS
      * ********************************
      */
    @NotNull
    M add(@NotNull M that);

    @NotNull
    M subtract(@NotNull M that);

    @NotNull
    M multiply(@NotNull M that);

    @NotNull
    V multiply(@NotNull V that);

    /**
     * @return matrix transposed to current
     */
    @NotNull
    M transpose();

    @NotNull
    N trace();

    @NotNull
    N determinant();

    /**
     * Main interface for building new matrix
     * NOTE: this builder provides access to the elements and this is the last point where the elements might be modified
     *
     * @param <M>
     */
    public static interface Builder<
            N extends INumber,
            V extends Vector<N, V>,
            M extends Matrix<N, V, M>> extends CommonMatrixInterface<N>, IBuilder<M> {

        void setIJ(int row, int col, @NotNull N value);
    }
}
