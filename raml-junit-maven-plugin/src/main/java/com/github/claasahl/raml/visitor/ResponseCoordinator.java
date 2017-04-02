package com.github.claasahl.raml.visitor;

import java.util.Map.Entry;

import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.parameter.Header;

/**
 * The class {@link ResponseCoordinator}.
 * <p>
 * An implementation of the {@link Coordinator} pattern, which "iterates" over
 * relevant attributes of the {@link Response} class and delegates them to their
 * respective visit-methods of an {@link ResponseVisitor}.
 * <p>
 * This coordinator is thread-safe and context-free. As such, instances of this
 * class can be used multiple times (i.e. several response may be processed).
 * 
 * @author Claas Ahlrichs
 *
 */
public class ResponseCoordinator {

	/**
	 * Delegates the attributes of the specified response with the specified
	 * visitor.
	 * 
	 * @param response
	 *            the response
	 * @param visitor
	 *            the visitor
	 */
	public void visitResponse(Response response, ResponseVisitor visitor) {
		visitBodies(response, visitor);
		visitHeaders(response, visitor);
	}

	/**
	 * A support method for iterating and visiting headers of the specified
	 * response. This implementation calls
	 * {@link ResponseVisitor#visitHeader(String, Header)} for all available
	 * headers.
	 * 
	 * @param response
	 *            the response
	 * @param visitor
	 *            the visitor
	 * @see Response#getHeaders()
	 */
	protected static void visitHeaders(Response response, ResponseVisitor visitor) {
		if (response.getHeaders() == null)
			return;
		for (Entry<String, Header> entry : response.getHeaders().entrySet()) {
			visitor.visitHeader(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting (response) bodies of the
	 * specified response. This implementation calls
	 * {@link ResponseVisitor#visitBody(String, MimeType)} for all available
	 * bodies.
	 * 
	 * @param response
	 *            the response
	 * @param visitor
	 *            the visitor
	 * @see Response#getBody()
	 */
	protected static void visitBodies(Response response, ResponseVisitor visitor) {
		if (response.getBody() == null)
			return;
		for (Entry<String, MimeType> entry : response.getBody().entrySet()) {
			visitor.visitBody(entry.getKey(), entry.getValue());
		}
	}

}
