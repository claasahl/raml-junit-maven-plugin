package com.github.claasahl.raml.visitor;

import java.util.List;
import java.util.Map.Entry;

import org.raml.model.Action;
import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.Header;
import org.raml.model.parameter.QueryParameter;
import org.raml.model.parameter.UriParameter;

/**
 * The class {@link ActionCoordinator}.
 * <p>
 * An implementation of the {@link Coordinator} pattern, which "iterates" over
 * relevant attributes of the {@link Action} class and delegates them to their
 * respective visit-methods of an {@link ActionVisitor}.
 * <p>
 * This coordinator is thread-safe and context-free. As such, instances of this
 * class can be used multiple times (i.e. several actions may be processed).
 * 
 * @author Claas Ahlrichs
 *
 */
public class ActionCoordinator implements Coordinator {

	/**
	 * Visits the specified action and then delegates its attributes to with the
	 * specified visitor.
	 * 
	 * @param action
	 *            the action
	 * @param visitor
	 *            the visitor
	 */
	public void visitAction(Action action, ActionVisitor visitor) {
		visitor.beforeVisit(action);
		visitBaseUriParameters(action, visitor);
		visitBodies(action, visitor);
		visitHeaders(action, visitor);
		visitQueryParameters(action, visitor);
		visitSecurityReferences(action, visitor);
		visitResponses(action, visitor);
		visitor.afterVisit(action);
	}

	/**
	 * A support method for iterating and visiting responses of the specified
	 * action. This implementation calls
	 * {@link ActionVisitor#visitResponse(String, Response)} for all available
	 * responses.
	 * 
	 * @param action
	 *            the action
	 * @param visitor
	 *            the visitor
	 * @see Action#getResponses()
	 */
	protected static void visitResponses(Action action, ActionVisitor visitor) {
		if (action.getResponses() == null)
			return;
		for (Entry<String, Response> entry : action.getResponses().entrySet()) {
			visitor.visitResponse(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting security references of the
	 * specified action. This implementation calls
	 * {@link ActionVisitor#visitSecurityReferences(Action)} for all available
	 * security references.
	 * 
	 * @param action
	 *            the action
	 * @param visitor
	 *            the visitor
	 * @see Action#getSecuredBy()
	 */
	protected static void visitSecurityReferences(Action action, ActionVisitor visitor) {
		if (action.getSecuredBy() == null)
			return;
		for (SecurityReference securityReference : action.getSecuredBy()) {
			visitor.visitSecurityReference(securityReference);
		}
	}

	/**
	 * A support method for iterating and visiting query parameters of the
	 * specified action. This implementation calls
	 * {@link ActionVisitor#visitQueryParameter(String, QueryParameter)} for all
	 * available query parameters.
	 * 
	 * @param action
	 *            the action
	 * @param visitor
	 *            the visitor
	 * @see Action#getQueryParameters()
	 */
	protected static void visitQueryParameters(Action action, ActionVisitor visitor) {
		if (action.getQueryParameters() == null)
			return;
		for (Entry<String, QueryParameter> entry : action.getQueryParameters().entrySet()) {
			visitor.visitQueryParameter(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting headers of the specified
	 * action. This implementation calls
	 * {@link ActionVisitor#visitHeader(String, Header)} for all available
	 * headers.
	 * 
	 * @param action
	 *            the action
	 * @param visitor
	 *            the visitor
	 * @see Action#getHeaders()
	 */
	protected static void visitHeaders(Action action, ActionVisitor visitor) {
		if (action.getHeaders() == null)
			return;
		for (Entry<String, Header> entry : action.getHeaders().entrySet()) {
			visitor.visitHeader(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting bodies of the specified
	 * action. This implementation calls
	 * {@link ActionVisitor#visitBody(String, MimeType)} for all available
	 * bodies.
	 * 
	 * @param action
	 *            the action
	 * @param visitor
	 *            the visitor
	 * @see Action#getBody()
	 */
	protected static void visitBodies(Action action, ActionVisitor visitor) {
		if (action.getBody() == null)
			return;
		for (Entry<String, MimeType> entry : action.getBody().entrySet()) {
			visitor.visitBody(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting base URI parameters of the
	 * specified action. This implementation calls
	 * {@link ActionVisitor#visitBaseUriParameter(String, List)} for all
	 * available base URI parameters.
	 * 
	 * @param action
	 *            the action
	 * @param visitor
	 *            the visitor
	 * @see Action#getBaseUriParameters()
	 */
	protected static void visitBaseUriParameters(Action action, ActionVisitor visitor) {
		if (action.getBaseUriParameters() == null)
			return;
		for (Entry<String, List<UriParameter>> entry : action.getBaseUriParameters().entrySet()) {
			visitor.visitBaseUriParameter(entry.getKey(), entry.getValue());
		}
	}

}
