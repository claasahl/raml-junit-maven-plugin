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
 * The class {@link ActionVisitorBase}.
 * <p>
 * A base implementation of the {@link ActionVisitor} interface. The default
 * implementations do nothing (i.e. empty method bodies).
 * 
 * @author Claas Ahlrichs
 *
 */
public class ActionVisitorBase implements ActionVisitor {

	@Override
	public void beforeVisit(Action action) {
		// empty body
	}

	@Override
	public void afterVisit(Action action) {
		// empty body
	}

	@Override
	public void visitBaseUriParameter(String key, List<UriParameter> uriParameters) {
		// empty body
	}

	@Override
	public void visitBody(String key, MimeType mimeType) {
		// empty body
	}

	@Override
	public void visitHeader(String key, Header header) {
		// empty body
	}

	@Override
	public void visitQueryParameter(String key, QueryParameter queryParameter) {
		// empty body
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		// empty body
	}

	@Override
	public void visitResponse(String key, Response response) {
		// empty body
	}

}
