package com.github.claasahl.raml.visitor;

import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.parameter.Header;

/**
 * The class {@link ResponseVisitorBase}.
 * <p>
 * A base implementation of the {@link ResponseVisitor} interface. The default
 * implementations do nothing (i.e. empty method bodies).
 * 
 * @author Claas Ahlrichs
 *
 */
public class ResponseVisitorBase implements ResponseVisitor {

	@Override
	public void beforeVisit(Response response) {
		// empty body
	}

	@Override
	public void afterVisit(Response response) {
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

}
