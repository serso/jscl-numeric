package jscl.math.numeric.matrix;

import jscl.math.numeric.INumber;
import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 2/15/12
 * Time: 2:14 PM
 */
interface CommonMatrixInterface<N extends INumber> {
    /**
     * @return number of rows in matrix
     */
    int getRows();

    /**
     * @return number of columns in matrix
     */
    int getCols();

    /**
     * Method returns (row, col) element of matrix
     *
     * @param row row of the element
     * @param col column of the element
     * @return (row, col) element of matrix
     */
    @NotNull
    N getIJ(int row, int col);
}
