package com.github.claasahl.raml.junit.api.model;

import javax.annotation.Nonnull;

/**
 * The class {@link Body}.
 * 
 * @author Claas Ahlrichs
 *
 */
public class Body {
	private final String contentType;
	private final String content;

	/**
	 * Creates a new body.
	 * 
	 * @param contentType
	 *            the body's content type
	 * @param content
	 *            the body's content
	 */
	public Body(String contentType, String content) {
		this.contentType = contentType;
		this.content = content;
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
	 * Returns the body's content.
	 * 
	 * @return the body's content
	 */
	@Nonnull
	public String getContent() {
		return content;
	}

}
