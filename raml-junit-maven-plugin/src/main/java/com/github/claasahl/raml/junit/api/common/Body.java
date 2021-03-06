package com.github.claasahl.raml.junit.api.common;

import javax.annotation.Nonnull;

/**
 * The class {@link Body}.
 * <p/>
 * This class is meant to represent the concept of a typed body or typed
 * content. It simply associates content with its content type. HTTP requests
 * can be constructed from such bodies and HTTP responses can be condensed into
 * such a body. Naturally, HTTP requests and HTTP responses consist of more than
 * just a body. Please refer to {@link Request} and {@link Response},
 * respectively.
 * 
 * @see BodyConstraints
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
