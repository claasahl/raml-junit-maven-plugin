package com.github.claasahl.raml.v08.visitor;

import org.raml.v2.api.model.v08.bodies.BodyLike;
import org.raml.v2.api.model.v08.bodies.Response;
import org.raml.v2.api.model.v08.parameters.Parameter;

import com.github.claasahl.raml.common.visitor.VisitorStrategy;

/**
 * The interface {@link ResponseVisitor}.
 * <p>
 * Superclass of all response-based visitors. This class is intended to model a
 * visitor for {@link Response} instances and their (composite) fields.
 * Implementations of this class will most likely extract details (e.g. headers)
 * or otherwise process APIs.
 * <p>
 * This class resembles a derivation of the <i>visitor</i> design pattern. It
 * includes a visit-method for composite fields of the {@link Response} class.
 * 
 * @author Claas Ahlrichs
 * @see VisitorStrategy
 *
 */
public interface ResponseVisitor extends VisitorStrategy<Response> {

	/**
	 * Visit method for headers returned by {@link Response#headers()}.
	 * 
	 * @param header
	 *            a header
	 */
	void visitHeader(Parameter header);

	/**
	 * Visit method for bodies returned by {@link Response#body()}.
	 * 
	 * @param body
	 *            a body
	 */
	void visitBody(BodyLike body);
}
