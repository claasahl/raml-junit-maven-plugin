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
 * method {@link #visitAction(Action)} already implements a mechanism for
 * visiting all composite fields (e.g. headers or URI parameters). The default
 * implementation of the remaining methods is empty.
 * <p>
 * This visitor is context-free. As such, instances of this class can be used to
 * multiple times (i.e. several actions may be processed).
 * 
 * @author Claas
 *
 */
public class ActionVisitorBase implements ActionVisitor {

	/**
	 * Visits the specified {@link Action}. The default implementation visits
	 * all composite fields (e.g. headers or URI parameters).
	 * <p>
	 * <b>Hint:</b> When overwriting this method, it is recommended to still
	 * call this method. Otherwise none of the composite fields may be visited.
	 * 
	 * @see ActionVisitor#visitAction(Action)
	 */
	@Override
	public void visitAction(Action action) {
		visitBaseUriParameters(action);
		visitBodies(action);
		visitHeaders(action);
		visitQueryParameters(action);
		visitSecurityReferences(action);
		visitResponses(action);
	}

	@Override
	public void visitBaseUriParameter(String key, List<UriParameter> uriParameters) {
		// empty default implementation
	}

	@Override
	public void visitBody(String key, MimeType mimeType) {
		// FIXME ???
	}

	@Override
	public void visitHeader(String key, Header header) {
		// empty default implementation
	}

	@Override
	public void visitQueryParameter(String key, QueryParameter queryParameter) {
		// empty default implementation
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		// FIXME ???
	}

	@Override
	public void visitResponse(String key, Response response) {
		// empty default implementation
	}

	/**
	 * A support method for iterating and visiting responses of the specified
	 * action. This implementation calls
	 * {@link #visitResponse(String, Response)} for all available responses.
	 * 
	 * @param action
	 *            the action
	 * @see Action#getResponses()
	 */
	protected void visitResponses(Action action) {
		for (Entry<String, Response> entry : action.getResponses().entrySet()) {
			visitResponse(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting security references of the
	 * specified action. This implementation calls
	 * {@link #visitSecurityReferences(Action)} for all available security
	 * references.
	 * 
	 * @param action
	 *            the action
	 * @see Action#getSecuredBy()
	 */
	protected void visitSecurityReferences(Action action) {
		for (SecurityReference securityReference : action.getSecuredBy()) {
			visitSecurityReference(securityReference);
		}
	}

	/**
	 * A support method for iterating and visiting query parameters of the
	 * specified action. This implementation calls
	 * {@link #visitQueryParameter(String, QueryParameter)} for all available
	 * query parameters.
	 * 
	 * @param action
	 *            the action
	 * @see Action#getQueryParameters()
	 */
	protected void visitQueryParameters(Action action) {
		for (Entry<String, QueryParameter> entry : action.getQueryParameters().entrySet()) {
			visitQueryParameter(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting headers of the specified
	 * action. This implementation calls {@link #visitHeader(String, Header)}
	 * for all available headers.
	 * 
	 * @param action
	 *            the action
	 * @see Action#getHeaders()
	 */
	protected void visitHeaders(Action action) {
		for (Entry<String, Header> entry : action.getHeaders().entrySet()) {
			visitHeader(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting bodies of the specified
	 * action. This implementation calls {@link #visitBody(String, MimeType)}
	 * for all available bodies.
	 * 
	 * @param action
	 *            the action
	 * @see Action#getBody()
	 */
	protected void visitBodies(Action action) {
		for (Entry<String, MimeType> entry : action.getBody().entrySet()) {
			visitBody(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting base URI parameters of the
	 * specified action. This implementation calls
	 * {@link #visitBaseUriParameter(String, List)} for all available base URI
	 * parameters.
	 * 
	 * @param action
	 *            the action
	 * @see Action#getBaseUriParameters()
	 */
	protected void visitBaseUriParameters(Action action) {
		for (Entry<String, List<UriParameter>> entry : action.getBaseUriParameters().entrySet()) {
			visitBaseUriParameter(entry.getKey(), entry.getValue());
		}
	}

}
