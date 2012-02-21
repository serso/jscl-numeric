package jscl.math.numeric.matrix;

import jscl.math.ArithmeticOperationException;
import jscl.msg.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 2/15/12
 * Time: 10:59 AM
 */
public class DimensionMustAgreeException extends ArithmeticOperationException {

    public DimensionMustAgreeException(@NotNull Vector l, @NotNull Vector r) {
        super(Messages.msg_24);
    }

    public DimensionMustAgreeException(@NotNull Matrix l, @NotNull Matrix r) {
        super(Messages.msg_25);
    }

    public DimensionMustAgreeException(@NotNull Matrix l, @NotNull Vector r) {
        super(Messages.msg_25);
    }
}
