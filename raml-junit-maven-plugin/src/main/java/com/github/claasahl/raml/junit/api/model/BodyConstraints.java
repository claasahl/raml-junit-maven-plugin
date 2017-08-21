package com.github.claasahl.raml.junit.api.model;

import java.util.List;

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
	private final List<Matcher<Body>> matchers;

	/**
	 * Creates a new constraint body.
	 * 
	 * @param contentType
	 *            the body's content type
	 * @param matchers
	 *            the body's constraints
	 */
	public BodyConstraints(String contentType, List<Matcher<Body>> matchers) {
		this.contentType = contentType;
		this.matchers = matchers;
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
	 * Returns the list of Hamcrest matcher for validating the body's content.
	 * Each matcher is evaluated individually. Together, the returned matchers
	 * are expected expected to handle all aspects of validation (e.g. whether
	 * the content type matches, whether the content matches a schema and so
	 * forth).
	 * 
	 * @return the list of Hamcrest matcher for validating the body's content
	 */
	public List<Matcher<Body>> getMatchers() {
		return matchers;
	}

}
