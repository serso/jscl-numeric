package jscl.math.numeric.matrix;

import jscl.JsclMathContext;
import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 2/3/12
 * Time: 11:38 AM
 */
public class SparseMatrixTest extends MatrixTest<SparseMatrix> {

	@NotNull
	@Override
	protected Matrix.Builder<SparseMatrix> getBuilder(@NotNull JsclMathContext mc, int rows, int cols) {
		return new SparseMatrix.Builder(mc, rows, cols);
	}
}
