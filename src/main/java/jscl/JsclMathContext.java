package jscl;

import jscl.math.raw.RawNumber;
import jscl.math.raw.RawNumberCreator;
import jscl.math.numeric.Complex;
import jscl.math.numeric.Real;
import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 1/30/12
 * Time: 2:01 PM
 */
public interface JsclMathContext extends RawNumberCreator {

	@NotNull
	AngleUnit getAngleUnits();

	@NotNull
	NumeralBase getNumeralBase();

	@NotNull
	String format(@NotNull RawNumber value) throws NumeralBaseException;

	@NotNull
	String format(@NotNull RawNumber value, @NotNull NumeralBase nb) throws NumeralBaseException;

	@NotNull
	Real newReal(long value);

	@NotNull
	Real newReal(double value);

	@NotNull
	Complex newComplex(long real, long imaginary);

	@NotNull
	Real randomReal();
}
