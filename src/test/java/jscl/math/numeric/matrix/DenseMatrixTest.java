package jscl.math.numeric.matrix;

import jscl.JsclMathContext;
import jscl.math.numeric.NumericNumber;
import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 2/3/12
 * Time: 11:36 AM
 */
public class DenseMatrixTest extends MatrixTest {

	@NotNull
	@Override
	protected Matrix.Builder<NumericNumber, NumericVector, NumericMatrix> getBuilder(@NotNull JsclMathContext mc, int rows, int cols) {
		return new DenseMatrix.Builder(mc, rows, cols);
	}
}
