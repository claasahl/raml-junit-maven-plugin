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
 * The class {@link ActionVisitorBase}.
 * <p>
 * A base implementation of the {@link ActionVisitor} interface, which provides
 * default implementations for all methods of the aforementioned interface. The
 * method {@link #visitAction(Action, ActionVisitor)} already implements a
 * mechanism for visiting all composite fields (e.g. headers or URI parameters).
 * The default implementation of the remaining methods is empty.
 * <p>
 * This visitor is context-free. As such, instances of this class can be used to
 * multiple times (i.e. several actions may be processed).
 * 
 * @author Claas
 *
 */
public class ActionVisitorBase {

	public void visitAction(Action action, ActionVisitor visitor) {
		visitBaseUriParameters(action, visitor);
		visitBodies(action, visitor);
		visitHeaders(action, visitor);
		visitQueryParameters(action, visitor);
		visitSecurityReferences(action, visitor);
		visitResponses(action, visitor);
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
	protected void visitResponses(Action action, ActionVisitor visitor) {
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
	protected void visitSecurityReferences(Action action, ActionVisitor visitor) {
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
	protected void visitQueryParameters(Action action, ActionVisitor visitor) {
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
	protected void visitHeaders(Action action, ActionVisitor visitor) {
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
	protected void visitBodies(Action action, ActionVisitor visitor) {
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
	protected void visitBaseUriParameters(Action action, ActionVisitor visitor) {
		if (action.getBaseUriParameters() == null)
			return;
		for (Entry<String, List<UriParameter>> entry : action.getBaseUriParameters().entrySet()) {
			visitor.visitBaseUriParameter(entry.getKey(), entry.getValue());
		}
	}

}
