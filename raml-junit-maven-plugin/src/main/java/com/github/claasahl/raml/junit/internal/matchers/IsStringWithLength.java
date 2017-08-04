package com.github.claasahl.raml.junit.internal.matchers;

import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public class IsStringWithLength extends FeatureMatcher<String, Integer> {

	public IsStringWithLength(Matcher<? super Integer> subMatcher) {
		super(subMatcher, "a string with length", "length");
	}

	@Override
	protected Integer featureValueOf(String actual) {
		return actual.length();
	}

	/**
	 * Creates a matcher for {@link String}s that matches when the
	 * <code>length()</code> method returns a value that satisfies the specified
	 * matcher.
	 * 
	 * @param lengthMatcher
	 *            a matcher for the length of an examined {@link String}
	 */
	@Factory
	public static Matcher<String> hasLength(Matcher<? super Integer> lengthMatcher) {
		return new IsStringWithLength(lengthMatcher);
	}

}
