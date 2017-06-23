package com.github.claasahl.raml.v08.junit;

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
	 * Creates a new body with the specified parameters.
	 * 
	 * @param contentType
	 *            the content type of the body
	 * @param content
	 *            the content of the body
	 */
	public Body(String contentType, String content) {
		super();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Body other = (Body) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Body [contentType=" + contentType + ", content=" + content + "]";
	}

}
