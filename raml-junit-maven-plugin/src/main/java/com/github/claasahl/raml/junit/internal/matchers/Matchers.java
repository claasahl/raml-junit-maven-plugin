package com.github.claasahl.raml.junit.internal.matchers;

import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.hamcrest.Matcher;

import com.github.claasahl.raml.junit.api.model.Body;
import com.github.claasahl.raml.junit.api.model.Parameter;

public final class Matchers {

	/**
	 * Creates a matcher for {@link Parameter}s that matches when the
	 * <code>getValues()</code> method returns a value that satisfies the
	 * specified matcher.
	 * 
	 * @param matcher
	 *            a matcher for the values of an examined {@link Parameter}
	 */
	public static Matcher<Parameter> hasValues(Matcher<? super List<String>> matcher) {
		return IsParameterWithValues.hasValues(matcher);
	}
	
	/**
	 * Creates a matcher for {@link Parameter}s that matches when the
	 * <code>getName()</code> method returns a value that satisfies the
	 * specified matcher.
	 * 
	 * @param matcher
	 *            a matcher for the name of an examined {@link Parameter}
	 */
	public static Matcher<Parameter> hasName(Matcher<? super String> matcher) {
		return IsParameterWithName.hasName(matcher);
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
	public static Matcher<Parameter> everyValue(final Matcher<String> matcher) {
		return EveryValue.everyValue(matcher);	
	}
	
	/**
	 * Creates a matcher for {@link String}s that matches when the
	 * <code>length()</code> method returns a value that satisfies the specified
	 * matcher.
	 * 
	 * @param lengthMatcher
	 *            a matcher for the length of an examined {@link String}
	 */
	public static Matcher<String> hasLength(Matcher<? super Integer> lengthMatcher) {
		return IsStringWithLength.hasLength(lengthMatcher);
	}
	
	/**
	 * Creates a matcher for {@link String}s that converts them into an
	 * {@link Integer} and matches when the resulting number satisfies the
	 * specified matcher.
	 * 
	 * @param matcher
	 *            a matcher for the examined {@link Integer}
	 */
	public static Matcher<String> toInteger(Matcher<Integer> matcher) {
		return Map.map(Integer::valueOf, matcher);
	}
	
	/**
	 * Creates a matcher for {@link String}s that converts them into a
	 * {@link Double} and matches when the resulting number satisfies the
	 * specified matcher.
	 * 
	 * @param matcher
	 *            a matcher for the examined {@link Double}
	 */
	public static Matcher<String> toDouble(Matcher<Double> matcher) {
		return Map.map(Double::valueOf, matcher);
	}
	
	/**
	 * Creates a matcher for {@link String}s that matches when the string
	 * satisfies the specified pattern / regular expression.
	 * 
	 * @param pattern
	 *            the regular expression to which this string is to be matched
	 */
	public static Matcher<String> pattern(String pattern) {
		return Pattern.pattern(pattern);
	}

	public static Matcher<Parameter> isRequired() {
		return notNullValue(Parameter.class);
	}
	
	public static Matcher<String> isInteger() {
		return toInteger(notNullValue(Integer.class));
	}
	
	/**
	 * Creates a matcher for {@link Body}s that matches when the
	 * <code>getContentType()</code> method returns a value that satisfies the
	 * specified matcher.
	 * 
	 * @param matcher
	 *            a matcher for the content type of an examined {@link Body}
	 */
	public static Matcher<Body> hasContentType(Matcher<? super String> matcher) {
		return IsBodyWithContentType.hasContentType(matcher);
	}
}
