package com.github.claasahl.raml.junit.internal.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class Pattern extends TypeSafeMatcher<String> {

	private final String pattern;

	public Pattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("Does not match pattern: " + pattern);
	}

	@Override
	protected boolean matchesSafely(String item) {
		return item.matches(pattern);
	}

	/**
	 * Creates a matcher for {@link String}s that matches when the string
	 * satisfies the specified pattern / regular expression.
	 * 
	 * @param pattern
	 *            the regular expression to which this string is to be matched
	 */
	@Factory
	public static Matcher<String> pattern(String pattern) {
		return new Pattern(pattern);
	}
}
