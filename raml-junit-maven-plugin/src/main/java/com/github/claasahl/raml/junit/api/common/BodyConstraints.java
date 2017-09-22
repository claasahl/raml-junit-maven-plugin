package com.github.claasahl.raml.junit.api.common;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import org.hamcrest.Matcher;

/**
 * The class {@link BodyConstraints}.
 * <p/>
 * This class is meant to capture relevant constraints of a typed body or typed
 * content. It associates a named key with any number of constraints (i.e. 0 or
 * more). When validating HTTP requests / responses, these constraints can be
 * enforced for their respective bodies.
 * 
 * @see Body
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
		this.matchers = Collections.unmodifiableList(matchers);
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
