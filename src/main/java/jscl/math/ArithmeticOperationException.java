package jscl.math;

import jscl.AbstractJsclArithmeticException;
import jscl.msg.Messages;
import org.jetbrains.annotations.NotNull;
import org.solovyev.common.msg.Message;

/**
 * User: serso
 * Date: 2/16/12
 * Time: 1:07 PM
 */
public class ArithmeticOperationException extends AbstractJsclArithmeticException {

    public ArithmeticOperationException() {
        super(Messages.msg_23);
    }

    public ArithmeticOperationException(@NotNull String messageCode, Object... parameters) {
        super(messageCode, parameters);
    }

    public ArithmeticOperationException(@NotNull Message message) {
        super(message);
    }
}
