package com.github.claasahl.raml.junit.internal.matchers;

import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public class FromStringToDouble extends FeatureMatcher<String, Double>{

	public FromStringToDouble(Matcher<? super Double> subMatcher) {
		super(subMatcher, "conversion of string to integer", "integer");
	}

	@Override
	protected Double featureValueOf(String actual) {
		return Double.valueOf(actual);
	}
	
	/**
	 * Creates a matcher for {@link String}s that converts them into an
	 * {@link Double} and matches when the resulting number satisfies the
	 * specified matcher.
	 * 
	 * @param matcher
	 *            a matcher for the length of an examined {@link String}
	 */
	@Factory
	public static Matcher<String> toDouble(Matcher<? super Double> matcher) {
		return new FromStringToDouble(matcher);
	}

}
