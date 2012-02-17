package jscl;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.core.IsEqual;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;

/**
 * User: serso
 * Date: 2/17/12
 * Time: 2:03 PM
 */
public class MathAssert {

	public static <T extends Comparable<? super T>> void assertEquals (@NotNull T expected, @NotNull T actual) {
		Assert.assertThat(actual, new CompareMatcher<T>(expected));
	}

	private static class CompareMatcher<T extends Comparable<? super T>> extends BaseMatcher<T> {

		@NotNull
		private final T expected;

		public CompareMatcher(@NotNull T expected) {
			this.expected = expected;
		}

		@Override
		public boolean matches(Object item) {
			try {
				return this.expected.compareTo((T)item) == 0;
			} catch (ClassCastException e) {
				return false;
			}
		}

		@Override
		public void describeTo(Description description) {
			description.appendValue(expected);
		}
	}
}
