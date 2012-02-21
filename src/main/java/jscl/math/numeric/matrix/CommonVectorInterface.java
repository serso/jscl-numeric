package jscl.math.numeric.matrix;

import jscl.math.numeric.INumber;
import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 2/15/12
 * Time: 2:09 PM
 */
interface CommonVectorInterface<N extends INumber> {

    @NotNull
    N getI(int index);

    int getLength();
}
