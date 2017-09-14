package com.github.claasahl.raml.junit.internal.matchers;

import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import com.github.claasahl.raml.junit.api.Body;

public class IsBodyWithContentType extends FeatureMatcher<Body, String> {

	public IsBodyWithContentType(Matcher<? super String> subMatcher) {
		super(subMatcher, "a body with content type", "content type");
	}

	@Override
	protected String featureValueOf(Body actual) {
		return actual.getContentType();
	}
	
	/**
	 * Creates a matcher for {@link Body}s that matches when the
	 * <code>getContentType()</code> method returns a value that satisfies the
	 * specified matcher.
	 * 
	 * @param matcher
	 *            a matcher for the content type of an examined {@link Body}
	 */
	@Factory
	public static Matcher<Body> hasContentType(Matcher<? super String> matcher) {
		return new IsBodyWithContentType(matcher);
	}
	
}