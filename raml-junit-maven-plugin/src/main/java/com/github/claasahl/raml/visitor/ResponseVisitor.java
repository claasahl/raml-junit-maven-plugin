package com.github.claasahl.raml.visitor;

import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.parameter.Header;

/**
 * The interface {@link ResponseVisitor}.
 * <p>
 * Superclass of all response-based visitors. This class is intended to model a
 * visitor for {@link Response} instances and their (composite) fields.
 * Implementations of this class will most likely extract details (e.g. headers
 * or body contents) or otherwise process responses.
 * <p>
 * This class resembles a derivation of the <i>visitor</i> design pattern. It
 * includes a visit-method for all composite fields of the {@link Response}
 * class.
 * 
 * @author Claas
 *
 */
public interface ResponseVisitor {

	void beforeVisit(Response response);

	void afterVisit(Response response);

	void visitBody(String key, MimeType mimeType);

	void visitHeader(String key, Header header);

}
