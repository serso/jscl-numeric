package jscl.raw;

import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 1/31/12
 * Time: 11:14 AM
 */
public interface RawNumberCreator {

    /**
     * @return RawNumber equivalent to PI math constant
     */
    @NotNull
    RawNumber getPI();

    /**
     * @param value double value
     * @return RawNumber representation of specified double value
     */
    @NotNull
    RawNumber fromDouble(double value);

    /**
     * @param value long value
     * @return RawNumber representation of specified long value
     */
    @NotNull
    RawNumber fromLong(long value);

    /**
     * @return RawNumber equivalent to 0
     */
    @NotNull
    RawNumber ZERO();

    /**
     * @return RawNumber equivalent to 1
     */
    @NotNull
    RawNumber ONE();

    /**
     * @return random RawNumber
     */
    @NotNull
    RawNumber random();
}
