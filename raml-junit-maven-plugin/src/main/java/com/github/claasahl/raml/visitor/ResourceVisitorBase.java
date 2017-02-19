package com.github.claasahl.raml.visitor;

import java.util.List;
import java.util.Map.Entry;

import org.raml.model.Action;
import org.raml.model.ActionType;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.UriParameter;

/**
 * The class {@link ResourceVisitorBase}.
 * <p>
 * A base implementation of the {@link ResourceVisitor} interface, which
 * provides default implementations for all methods of the aforementioned
 * interface. The method {@link #visitResource(Resource)} already implements a
 * mechanism for visiting all composite fields (e.g. headers or body contents).
 * The default implementation of the remaining methods is empty.
 * <p>
 * This visitor is context-free. As such, instances of this class can be used to
 * multiple times (i.e. several resources may be processed).
 * 
 * @author Claas
 *
 */
public class ResourceVisitorBase implements ResourceVisitor {

	/**
	 * Visits the specified {@link Resource}. The default implementation visits
	 * all composite fields (e.g. headers or body contents).
	 * <p>
	 * <b>Hint:</b> When overwriting this method, it is recommended to still
	 * call this method. Otherwise none of the composite fields may be visited.
	 * 
	 * @see ResourceVisitor#visitResource(Resource)
	 */
	@Override
	public void visitResource(Resource resource) {
		visitActions(resource);
		visitBaseUriParameters(resource);
		visitResolvedUriParameters(resource);
		visitUriParameters(resource);
		visitSecurityReferences(resource);
		visitSubResources(resource);
	}

	@Override
	public void visitAction(ActionType actionType, Action action) {
		// empty default implementation
	}

	@Override
	public void visitBaseUriParameter(String key, List<UriParameter> uriParameters) {
		// empty default implementation
	}

	@Override
	public void visitResolvedUriParameter(String key, UriParameter uriParameters) {
		// empty default implementation
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		// FIXME ???
	}

	@Override
	public void visitUriParameter(String key, UriParameter uriParameter) {
		// empty default implementation
	}

	/**
	 * A support method for iterating and visiting sub-resources of the
	 * specified resource. This implementation calls
	 * {@link #visitResource(Resource)} for all available sub-resources.
	 * 
	 * @param resource
	 *            the resource
	 * @see Resource#getResources()
	 */
	protected void visitSubResources(Resource resource) {
		if (resource.getResources() == null)
			return;
		for (Resource subResource : resource.getResources().values()) {
			visitResource(subResource);
		}
	}

	/**
	 * A support method for iterating and visiting security references of the
	 * specified resource. This implementation calls
	 * {@link #visitSecurityReference(SecurityReference)} for all available
	 * security references.
	 * 
	 * @param resource
	 *            the resource
	 * @see Resource#getSecuredBy()
	 */
	protected void visitSecurityReferences(Resource resource) {
		if (resource.getSecuredBy() == null)
			return;
		for (SecurityReference securityReference : resource.getSecuredBy()) {
			visitSecurityReference(securityReference);
		}
	}

	/**
	 * A support method for iterating and visiting URI parameters of the
	 * specified resource. This implementation calls
	 * {@link #visitUriParameter(String, UriParameter)} for all available URI
	 * parameters.
	 * 
	 * @param resource
	 *            the resource
	 * @see Resource#getUriParameters()
	 */
	protected void visitUriParameters(Resource resource) {
		if (resource.getUriParameters() == null)
			return;
		for (Entry<String, UriParameter> entry : resource.getUriParameters().entrySet()) {
			visitUriParameter(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting resolved URI parameters of
	 * the specified resource. This implementation calls
	 * {@link #visitResolvedUriParameter(String, UriParameter)} for all
	 * available resolved URI parameters.
	 * 
	 * @param resource
	 *            the resource
	 * @see Resource#getResolvedUriParameters()
	 */
	protected void visitResolvedUriParameters(Resource resource) {
		if (resource.getResolvedUriParameters() == null)
			return;
		for (Entry<String, UriParameter> entry : resource.getResolvedUriParameters().entrySet()) {
			visitResolvedUriParameter(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting base URI parameters of the
	 * specified resource. This implementation calls
	 * {@link #visitBaseUriParameter(String, List)} for all available base URI
	 * parameters.
	 * 
	 * @param resource
	 *            the resource
	 * @see Resource#getBaseUriParameters()
	 */
	protected void visitBaseUriParameters(Resource resource) {
		if (resource.getBaseUriParameters() == null)
			return;
		for (Entry<String, List<UriParameter>> entry : resource.getBaseUriParameters().entrySet()) {
			visitBaseUriParameter(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting actions of the specified
	 * resource. This implementation calls
	 * {@link #visitAction(ActionType, Action)} for all available actions.
	 * 
	 * @param resource
	 *            the resource
	 * @see Resource#getActions()
	 */
	protected void visitActions(Resource resource) {
		if (resource.getActions() == null)
			return;
		for (Entry<ActionType, Action> entry : resource.getActions().entrySet()) {
			visitAction(entry.getKey(), entry.getValue());
		}
	}

}
