package com.github.claasahl.raml.v08.visitor;

import org.raml.v2.api.model.v08.bodies.BodyLike;
import org.raml.v2.api.model.v08.bodies.Response;
import org.raml.v2.api.model.v08.parameters.Parameter;

import com.github.claasahl.raml.common.visitor.Coordinator;

/**
 * The class {@link ResponseCoordinator}.
 * <p>
 * An implementation of the {@link Coordinator} pattern, which "iterates" over
 * relevant attributes of the {@link Response} class and delegates them to their
 * respective visit-methods of an {@link ResponseVisitor}.
 * <p>
 * This coordinator is thread-safe and context-free. As such, instances of this
 * class can be used multiple times (i.e. several responses may be processed).
 * 
 * @author Claas Ahlrichs
 *
 */
public class ResponseCoordinator implements Coordinator<Response, ResponseVisitor> {

	@Override
	public void coordinate(Response visitee, ResponseVisitor visitor) {
		visitor.beforeVisit(visitee);
		visitBody(visitee, visitor);
		visitHeader(visitee, visitor);
		visitor.afterVisit(visitee);
	}

	/**
	 * A support method for iterating and visiting bodies. This implementation
	 * calls {@link ResponseVisitor#visitBaseUriParameter(Parameter)} for all
	 * available bodies.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Response#body()
	 */
	protected static void visitBody(Response visitee, ResponseVisitor visitor) {
		if (visitee.body() == null)
			return;
		for (BodyLike body : visitee.body()) {
			visitor.visitBody(body);
		}
	}

	/**
	 * A support method for iterating and visiting headers. This implementation
	 * calls {@link ResponseVisitor#visitHeader(Parameter)} for all available
	 * headers.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Response#headers()
	 */
	protected static void visitHeader(Response visitee, ResponseVisitor visitor) {
		if (visitee.headers() == null)
			return;
		for (Parameter header : visitee.headers()) {
			visitor.visitHeader(header);
		}
	}

}
