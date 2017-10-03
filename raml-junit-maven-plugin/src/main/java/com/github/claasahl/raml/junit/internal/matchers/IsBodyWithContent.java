package com.github.claasahl.raml.junit.internal.matchers;

import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import com.github.claasahl.raml.junit.api.common.Body;

public class IsBodyWithContent extends FeatureMatcher<Body, String> {

	public IsBodyWithContent(Matcher<? super String> subMatcher) {
		super(subMatcher, "a body with content", "content");
	}

	@Override
	protected String featureValueOf(Body actual) {
		return actual.getContent();
	}

	/**
	 * Creates a matcher for {@link Body}s that matches when the
	 * <code>getContent()</code> method returns a value that satisfies the
	 * specified matcher.
	 * 
	 * @param matcher
	 *            a matcher for the content of an examined {@link Body}
	 */
	@Factory
	public static Matcher<Body> hasContent(Matcher<? super String> matcher) {
		return new IsBodyWithContent(matcher);
	}

}