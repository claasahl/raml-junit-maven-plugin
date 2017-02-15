package com.github.claasahl.raml.visitor;

import java.util.List;

import org.raml.model.Action;
import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.Header;
import org.raml.model.parameter.QueryParameter;
import org.raml.model.parameter.UriParameter;

/**
 * The interface {@link ActionVisitor}.
 * <p>
 * Superclass of all action-based visitors. This class is intended to model a
 * visitor for {@link Action} instances and their (composite) fields.
 * Implementations of this class will most likely extract details (e.g. headers
 * or URI parameters) or otherwise process actions.
 * <p>
 * This class resembles a derivation of the <i>visitor</i> design pattern. It
 * includes a visit-method for all composite fields of the {@link Action} class.
 * 
 * @author Claas
 *
 */
public interface ActionVisitor {

	void visitAction(Action action);

	void visitBaseUriParameter(String key, List<UriParameter> uriParameters);

	void visitBody(String key, MimeType mimeType);

	void visitHeader(String key, Header header);

	void visitQueryParameter(String key, QueryParameter queryParameter);

	void visitSecurityReference(SecurityReference securityReference);

	void visitResponse(String key, Response response);

}
