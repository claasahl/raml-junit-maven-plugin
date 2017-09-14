package com.github.claasahl.raml.junit.internal.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import com.github.claasahl.raml.junit.api.Parameter;

public class EveryValue extends TypeSafeDiagnosingMatcher<Parameter> {
	private final Matcher<String> matcher;

	public EveryValue(Matcher<String> matcher) {
		this.matcher = matcher;
	}

	@Override
	public boolean matchesSafely(Parameter parameter, Description mismatchDescription) {
		for (String value : parameter.getValues()) {
			if (!matcher.matches(value)) {
				mismatchDescription.appendText("a value ");
				matcher.describeMismatch(value, mismatchDescription);
				return false;
			}
		}
		return true;
	}

	@Override
	public void describeTo(Description description) {
		description.appendDescriptionOf(this.matcher);
	}

	/**
	 * Creates a matcher for {@link Parameter}s that only matches when a single
	 * pass over the examined {@link Parameter} yields values that are all
	 * matched by the specified <code>matcher</code>.
	 * 
	 * @param matcher
	 *            the matcher to apply to every value provided by the examined
	 *            {@link Parameter}
	 */
	@Factory
	public static Matcher<Parameter> everyValue(final Matcher<String> matcher) {
		return new EveryValue(matcher);
	}
}
