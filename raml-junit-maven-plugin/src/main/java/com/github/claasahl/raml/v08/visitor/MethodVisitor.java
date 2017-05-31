package com.github.claasahl.raml.v08.visitor;

import org.raml.v2.api.model.v08.bodies.BodyLike;
import org.raml.v2.api.model.v08.bodies.Response;
import org.raml.v2.api.model.v08.methods.Method;
import org.raml.v2.api.model.v08.methods.TraitRef;
import org.raml.v2.api.model.v08.parameters.Parameter;
import org.raml.v2.api.model.v08.security.SecuritySchemeRef;

import com.github.claasahl.raml.common.visitor.VisitorStrategy;

/**
 * The interface {@link MethodVisitor}.
 * <p>
 * Superclass of all method-based visitors. This class is intended to model a
 * visitor for {@link Method} instances and their (composite) fields.
 * Implementations of this class will most likely extract details (e.g. headers
 * or URI parameters) or otherwise process APIs.
 * <p>
 * This class resembles a derivation of the <i>visitor</i> design pattern. It
 * includes a visit-method for composite fields of the {@link Method} class.
 * 
 * @author Claas Ahlrichs
 * @see VisitorStrategy
 *
 */
public interface MethodVisitor extends VisitorStrategy<Method> {
	/**
	 * Visit method for traits returned by {@link Method#is()}.
	 * 
	 * @param trait
	 *            a trait
	 */
	void visitTrait(TraitRef trait);

	/**
	 * Visit method for responses returned by {@link Method#responses()}.
	 * 
	 * @param response
	 *            a response
	 */
	void visitResponses(Response response);

	/**
	 * Visit method for bodies returned by {@link Method#body()}.
	 * 
	 * @param body
	 *            a body
	 */
	void visitBody(BodyLike body);

	/**
	 * Visit method for protocols returned by {@link Method#protocols()}.
	 * 
	 * @param protocol
	 *            a protocol
	 */
	void visitProtocols(String protocol);

	/**
	 * Visit method for security schemes returned by {@link Method#securedBy()}.
	 * 
	 * @param securedBy
	 *            a security scheme
	 */
	void visitSecuredBy(SecuritySchemeRef securedBy);

	/**
	 * Visit method for base URI parameters returned by
	 * {@link Method#baseUriParameters()}.
	 * 
	 * @param baseUriParameter
	 *            a base URI parameter
	 */
	void visitBaseUriParameters(Parameter baseUriParameter);

	/**
	 * Visit method for query parameters returned by
	 * {@link Method#queryParameters()}.
	 * 
	 * @param queryParameter
	 *            a query parameter
	 */
	void visitQueryParameters(Parameter queryParameter);

	/**
	 * Visit method for headers returned by {@link Method#headers()}.
	 * 
	 * @param header
	 *            a header
	 */
	void visitHeaders(Parameter header);
}
