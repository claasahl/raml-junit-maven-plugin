package com.github.claasahl.raml.visitor;

import java.util.List;

import org.raml.model.Action;
import org.raml.model.ActionType;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.UriParameter;

/**
 * The interface {@link ResourceVisitor}.
 * <p>
 * Superclass of all response-based visitors. This class is intended to model a
 * visitor for {@link Resource} instances and their (composite) fields.
 * Implementations of this class will most likely extract details (e.g. URI
 * parameters or actions) or otherwise process resources.
 * <p>
 * This class resembles a derivation of the <i>visitor</i> design pattern. It
 * includes a visit-method for all composite fields of the {@link Resource}
 * class.
 * 
 * @author Claas
 *
 */
public interface ResourceVisitor {

	void beforeVisit(Resource resource);

	void afterVisit(Resource resource);

	void visitSubResource(Resource resource);

	void visitAction(ActionType actionType, Action action);

	void visitBaseUriParameter(String key, List<UriParameter> uriParameters);

	void visitResolvedUriParameter(String key, UriParameter uriParameters);

	void visitSecurityReference(SecurityReference securityReference);

	void visitUriParameter(String key, UriParameter uriParameter);

}
