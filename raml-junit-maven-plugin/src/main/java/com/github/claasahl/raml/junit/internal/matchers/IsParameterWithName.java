package com.github.claasahl.raml.junit.internal.matchers;

import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import com.github.claasahl.raml.junit.api.common.Parameter;

public class IsParameterWithName extends FeatureMatcher<Parameter, String> {

	public IsParameterWithName(Matcher<? super String> subMatcher) {
		super(subMatcher, "a parameter with name", "name");
	}

	@Override
	protected String featureValueOf(Parameter actual) {
		return actual.getName();
	}

	/**
	 * Creates a matcher for {@link Parameter}s that matches when the
	 * <code>getName()</code> method returns a value that satisfies the
	 * specified matcher.
	 * 
	 * @param matcher
	 *            a matcher for the name of an examined {@link Parameter}
	 */
	@Factory
	public static Matcher<Parameter> hasName(Matcher<? super String> matcher) {
		return new IsParameterWithName(matcher);
	}

}
