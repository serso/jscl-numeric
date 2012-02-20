package jscl.math.numeric.matrix;

import jscl.JsclMathContext;
import jscl.math.numeric.NumericNumber;
import jscl.math.numeric.Real;
import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 2/2/12
 * Time: 12:53 PM
 */
public class DenseMatrix extends NumericMatrix {

	public static class Builder extends AbstractBuilder {
		@NotNull
		private final NumericNumber m[][];

		public Builder(@NotNull JsclMathContext mc, int rows, int cols) {
			super(mc, rows, cols);

			this.m = new NumericNumber[rows][cols];
		}

		@NotNull
		@Override
		protected NumericNumber getIJ0(int row, int col) {
			return this.m[row][col];
		}

		@Override
		protected void setIJ0(int row, int col, @NotNull NumericNumber value) {
			this.m[row][col] = value;
		}

		@Override
		@NotNull
		protected DenseMatrix build0() {

			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m[i].length; j++) {
					if (this.m[i][j] == null) {
						this.m[i][j] = Real.ZERO(mc);
					}
				}
			}

			return new DenseMatrix(mc, m);
		}
	}

	@NotNull
	private final NumericNumber m[][];

	private DenseMatrix(@NotNull final JsclMathContext mc, @NotNull NumericNumber m[][]) {
		this(mc, m, false);
	}

	private DenseMatrix(@NotNull final JsclMathContext mc, @NotNull NumericNumber m[][], final boolean transposed) {
		super(mc, m.length, m.length > 0 ? m[0].length : 0, transposed);
		this.m = m;
	}

	@NotNull
	@Override
	protected Matrix.Builder<NumericNumber, NumericVector, NumericMatrix> getBuilder(int rows, int cols) {
		return new Builder(this.mc, rows, cols);
	}

	@Override
	@NotNull
	public NumericNumber getIJ0(int row, int col) {
		return m[row][col];
	}

	@NotNull
	@Override
	public DenseMatrix transpose() {
		return new DenseMatrix(mc, m, !transposed);
	}
}
