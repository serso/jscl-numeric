package jscl.math;

import org.jetbrains.annotations.NotNull;

public interface Arithmetic<T extends Arithmetic<? extends T>> {

	@NotNull
	T add(@NotNull T that) throws ArithmeticOperationException;

	@NotNull
	T subtract(@NotNull T that) throws ArithmeticOperationException;

	@NotNull
	T multiply(@NotNull T that) throws ArithmeticOperationException;

	@NotNull
	T divide(@NotNull T that) throws ArithmeticOperationException;

}
