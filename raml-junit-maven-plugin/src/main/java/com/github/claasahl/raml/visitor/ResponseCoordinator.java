package com.github.claasahl.raml.visitor;

import java.util.Map.Entry;

import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.parameter.Header;

/**
 * The class {@link ResponseVisitorBase}.
 * <p>
 * A base implementation of the {@link ResponseVisitor} interface, which
 * provides default implementations for all methods of the aforementioned
 * interface. The method {@link #visitResponse(Response)} already implements a
 * mechanism for visiting all composite fields (e.g. body contents or headers).
 * The default implementation of the remaining methods is empty.
 * <p>
 * This visitor is context-free. As such, instances of this class can be used to
 * multiple times (i.e. several responses may be processed).
 * 
 * @author Claas
 *
 */
public class ResponseCoordinator {

	/**
	 * Visits the specified {@link Response}. The default implementation visits
	 * all composite fields (e.g. body contents or headers).
	 * <p>
	 * <b>Hint:</b> When overwriting this method, it is recommended to still
	 * call this method. Otherwise none of the composite fields may be visited.
	 * 
	 * @see ResponseVisitor#visitResponse(Response)
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
	protected void visitHeaders(Response response, ResponseVisitor visitor) {
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
	protected void visitBodies(Response response, ResponseVisitor visitor) {
		if (response.getBody() == null)
			return;
		for (Entry<String, MimeType> entry : response.getBody().entrySet()) {
			visitor.visitBody(entry.getKey(), entry.getValue());
		}
	}

}
