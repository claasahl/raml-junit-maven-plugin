package com.github.claasahl.raml.v08.visitor;

import org.raml.v2.api.model.v08.bodies.BodyLike;
import org.raml.v2.api.model.v08.bodies.Response;
import org.raml.v2.api.model.v08.methods.Method;
import org.raml.v2.api.model.v08.methods.TraitRef;
import org.raml.v2.api.model.v08.parameters.Parameter;
import org.raml.v2.api.model.v08.security.SecuritySchemeRef;

import com.github.claasahl.raml.common.visitor.Coordinator;

/**
 * The class {@link MethodCoordinator}.
 * <p>
 * An implementation of the {@link Coordinator} pattern, which "iterates" over
 * relevant attributes of the {@link Method} class and delegates them to their
 * respective visit-methods of an {@link MethodVisitor}.
 * <p>
 * This coordinator is thread-safe and context-free. As such, instances of this
 * class can be used multiple times (i.e. several methods may be processed).
 * 
 * @author Claas Ahlrichs
 *
 */
public class MethodCoordinator implements Coordinator<Method, MethodVisitor> {

	@Override
	public void coordinate(Method visitee, MethodVisitor visitor) {
		visitor.beforeVisit(visitee);
		visitTrait(visitee, visitor);
		visitResponse(visitee, visitor);
		visitBody(visitee, visitor);
		visitProtocol(visitee, visitor);
		visitSecuredBy(visitee, visitor);
		visitBaseUriParameter(visitee, visitor);
		visitQueryParameter(visitee, visitor);
		visitHeader(visitee, visitor);
		visitor.afterVisit(visitee);
	}

	/**
	 * A support method for iterating and visiting traits. This implementation
	 * calls {@link MethodVisitor#visitTrait(TraitRef)} for all available
	 * traits.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Method#is()
	 */
	protected static void visitTrait(Method visitee, MethodVisitor visitor) {
		if (visitee.is() == null)
			return;
		for (TraitRef trait : visitee.is()) {
			visitor.visitTrait(trait);
		}
	}

	/**
	 * A support method for iterating and visiting responses. This
	 * implementation calls {@link MethodVisitor#visitResponse(Response)} for
	 * all available responses.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Method#responses()
	 */
	protected static void visitResponse(Method visitee, MethodVisitor visitor) {
		if (visitee.responses() == null)
			return;
		for (Response response : visitee.responses()) {
			visitor.visitResponse(response);
		}
	}

	/**
	 * A support method for iterating and visiting bodies. This implementation
	 * calls {@link MethodVisitor#visitBaseUriParameter(Parameter)} for all
	 * available bodies.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Method#body()
	 */
	protected static void visitBody(Method visitee, MethodVisitor visitor) {
		if (visitee.body() == null)
			return;
		for (BodyLike body : visitee.body()) {
			visitor.visitBody(body);
		}
	}

	/**
	 * A support method for iterating and visiting protocols. This
	 * implementation calls {@link MethodVisitor#visitProtocol(String)} for all
	 * available protocols.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Method#protocols()
	 */
	protected static void visitProtocol(Method visitee, MethodVisitor visitor) {
		if (visitee.protocols() == null)
			return;
		for (String protocol : visitee.protocols()) {
			visitor.visitProtocol(protocol);
		}
	}

	/**
	 * A support method for iterating and visiting security schemes. This
	 * implementation calls
	 * {@link MethodVisitor#visitSecuredBy(SecuritySchemeRef)} for all available
	 * security schemes.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Method#securedBy()
	 */
	protected static void visitSecuredBy(Method visitee, MethodVisitor visitor) {
		if (visitee.securedBy() == null)
			return;
		for (SecuritySchemeRef securedBy : visitee.securedBy()) {
			visitor.visitSecuredBy(securedBy);
		}
	}

	/**
	 * A support method for iterating and visiting base URI parameters. This
	 * implementation calls
	 * {@link MethodVisitor#visitBaseUriParameter(Parameter)} for all available
	 * base URI parameters.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Method#baseUriParameters()
	 */
	protected static void visitBaseUriParameter(Method visitee, MethodVisitor visitor) {
		if (visitee.baseUriParameters() == null)
			return;
		for (Parameter baseUriParameter : visitee.baseUriParameters()) {
			visitor.visitBaseUriParameter(baseUriParameter);
		}
	}

	/**
	 * A support method for iterating and visiting query parameters. This
	 * implementation calls
	 * {@link MethodVisitor#visitBaseUriParameter(Parameter)} for all available
	 * query parameters.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Method#queryParameters()
	 */
	protected static void visitQueryParameter(Method visitee, MethodVisitor visitor) {
		if (visitee.queryParameters() == null)
			return;
		for (Parameter queryParameter : visitee.queryParameters()) {
			visitor.visitQueryParameter(queryParameter);
		}
	}

	/**
	 * A support method for iterating and visiting headers. This implementation
	 * calls {@link MethodVisitor#visitHeader(Parameter)} for all available
	 * headers.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Method#headers()
	 */
	protected static void visitHeader(Method visitee, MethodVisitor visitor) {
		if (visitee.headers() == null)
			return;
		for (Parameter header : visitee.headers()) {
			visitor.visitHeader(header);
		}
	}

}
