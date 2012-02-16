package jscl.math.numeric.matrix;

import jscl.math.numeric.NumericNumber;
import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 2/15/12
 * Time: 2:09 PM
 */
interface CommonVectorInterface {

	@NotNull
	NumericNumber getI(int index);

	int getLength();
}
