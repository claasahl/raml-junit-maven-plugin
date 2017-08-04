package com.github.claasahl.raml.junit.internal.matchers;

import java.util.List;

import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import com.github.claasahl.raml.junit.api.model.Parameter;

public class IsParameterWithValues extends FeatureMatcher<Parameter, List<String>> {

	public IsParameterWithValues(Matcher<? super List<String>> subMatcher) {
		super(subMatcher, "a parameter with values", "values");
	}

	@Override
	protected List<String> featureValueOf(Parameter actual) {
		return actual.getValues();
	}

	/**
	 * Creates a matcher for {@link Parameter}s that matches when the
	 * <code>getValues()</code> method returns a value that satisfies the
	 * specified matcher.
	 * 
	 * @param valuesMatcher
	 *            a matcher for the values of an examined {@link Parameter}
	 */
	@Factory
	public static Matcher<Parameter> hasValues(Matcher<? super List<String>> valuesMatcher) {
		return new IsParameterWithValues(valuesMatcher);
	}

}
