package com.github.claasahl.raml.junit.api.model;

import javax.annotation.Nonnull;

import org.hamcrest.Matcher;

/**
 * The class {@link BodyConstraints}.
 * 
 * @author Claas Ahlrichs
 *
 */
public class BodyConstraints {

	private final String contentType;
	private final Matcher<String> matcher;

	/**
	 * Creates a constraint body.
	 * 
	 * @param contentType
	 *            the body's content type
	 * @param matcher
	 *            the body's constraints
	 */
	public BodyConstraints(String contentType, Matcher<String> matcher) {
		this.contentType = contentType;
		this.matcher = matcher;
	}

	/**
	 * Returns the body's content type.
	 * 
	 * @return the body's content type
	 */
	@Nonnull
	public String getContentType() {
		return contentType;
	}

	/**
	 * Returns the Hamcrest matcher for validating the body's content. The
	 * returned matcher is expected to handle all aspects of validation (e.g.
	 * whether the content matches a schema).
	 * 
	 * @return the Hamcrest matcher for validating the body's content
	 */
	public Matcher<String> getMatcher() {
		return matcher;
	}

}
