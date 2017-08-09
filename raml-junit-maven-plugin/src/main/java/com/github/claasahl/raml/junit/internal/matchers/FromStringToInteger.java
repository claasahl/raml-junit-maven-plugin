package com.github.claasahl.raml.junit.internal.matchers;

import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public class FromStringToInteger extends FeatureMatcher<String, Integer>{

	public FromStringToInteger(Matcher<? super Integer> subMatcher) {
		super(subMatcher, "conversion of string to integer", "integer");
	}

	@Override
	protected Integer featureValueOf(String actual) {
		return Integer.valueOf(actual);
	}
	
	/**
	 * Creates a matcher for {@link String}s that converts them into an
	 * {@link Integer} and matches when the resulting number satisfies the
	 * specified matcher.
	 * 
	 * @param matcher
	 *            a matcher for the examined {@link Integer}
	 */
	@Factory
	public static Matcher<String> toInteger(Matcher<? super Integer> matcher) {
		return new FromStringToInteger(matcher);
	}

}
