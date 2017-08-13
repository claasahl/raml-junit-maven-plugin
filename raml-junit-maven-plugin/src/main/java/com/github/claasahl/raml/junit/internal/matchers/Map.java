package com.github.claasahl.raml.junit.internal.matchers;

import java.util.function.Function;

import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public class Map<T, U> extends FeatureMatcher<T, U> {

	private final Function<T, U> function;

	public Map(Matcher<? super U> subMatcher, Function<T, U> function) {
		super(subMatcher, "", "");
		this.function = function;
	}

	@Override
	protected U featureValueOf(T actual) {
		return function.apply(actual);
	}

	/**
	 * Creates a matcher for {@link Object}s that applies a function to them
	 * (e.g. converting them to a different type or otherwise manipulating them)
	 * and matches when the resulting object satisfies the specified matcher.
	 * 
	 * @param function
	 *            the function that is applied to the (original) object
	 * @param matcher
	 *            the matcher for the resulting object
	 */
	@Factory
	public static <T, U> Matcher<T> map(Function<T, U> function, Matcher<U> matcher) {
		return new Map<>(matcher, function);
	}

}
