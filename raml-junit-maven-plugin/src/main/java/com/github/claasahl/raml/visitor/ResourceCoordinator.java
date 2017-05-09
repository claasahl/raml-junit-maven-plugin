package com.github.claasahl.raml.visitor;

import java.util.List;
import java.util.Map.Entry;

import org.raml.model.Action;
import org.raml.model.ActionType;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.UriParameter;

/**
 * The class {@link ResourceCoordinator}.
 * <p>
 * An implementation of the {@link Coordinator} pattern, which "iterates" over
 * relevant attributes of the {@link Resource} class and delegates them to their
 * respective visit-methods of an {@link ResourceVisitor}.
 * <p>
 * This coordinator is thread-safe and context-free. As such, instances of this
 * class can be used multiple times (i.e. several resources may be processed).
 * 
 * @author Claas Ahlrichs
 *
 */
public class ResourceCoordinator implements Coordinator {

	/**
	 * Visits the specified resource and then delegates its attributes to with
	 * the specified visitor.
	 * 
	 * @param resource
	 *            the resource
	 * @param visitor
	 *            the visitor
	 */
	public void visitResource(Resource resource, ResourceVisitor visitor) {
		visitor.beforeVisit(resource);
		visitBaseUriParameters(resource, visitor);
		visitResolvedUriParameters(resource, visitor);
		visitUriParameters(resource, visitor);
		visitSecurityReferences(resource, visitor);
		visitActions(resource, visitor);
		visitSubResources(resource, visitor);
		visitor.afterVisit(resource);
	}

	/**
	 * A support method for iterating and visiting sub-resources of the
	 * specified resource. This implementation calls
	 * {@link ResourceVisitor#visitSubResource(String, Resource)} for all
	 * available sub-resources.
	 * 
	 * @param resource
	 *            the resource
	 * @param visitor
	 *            the visitor
	 * @see Resource#getResources()
	 */
	protected static void visitSubResources(Resource resource, ResourceVisitor visitor) {
		if (resource.getResources() == null)
			return;
		for (Entry<String, Resource> entry : resource.getResources().entrySet()) {
			visitor.visitSubResource(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting security references of the
	 * specified resource. This implementation calls
	 * {@link ResourceVisitor#visitSecurityReference(SecurityReference)} for all
	 * available security references.
	 * 
	 * @param resource
	 *            the resource
	 * @param visitor
	 *            the visitor
	 * @see Resource#getSecuredBy()
	 */
	protected static void visitSecurityReferences(Resource resource, ResourceVisitor visitor) {
		if (resource.getSecuredBy() == null)
			return;
		for (SecurityReference securityReference : resource.getSecuredBy()) {
			visitor.visitSecurityReference(securityReference);
		}
	}

	/**
	 * A support method for iterating and visiting URI parameters of the
	 * specified resource. This implementation calls
	 * {@link ResourceVisitor#visitUriParameter(String, UriParameter)} for all
	 * available URI parameters.
	 * 
	 * @param resource
	 *            the resource
	 * @param visitor
	 *            the visitor
	 * @see Resource#getUriParameters()
	 */
	protected static void visitUriParameters(Resource resource, ResourceVisitor visitor) {
		if (resource.getUriParameters() == null)
			return;
		for (Entry<String, UriParameter> entry : resource.getUriParameters().entrySet()) {
			visitor.visitUriParameter(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting resolved URI parameters of
	 * the specified resource. This implementation calls
	 * {@link ResourceVisitor#visitResolvedUriParameter(String, UriParameter)}
	 * for all available resolved URI parameters.
	 * 
	 * @param resource
	 *            the resource
	 * @param visitor
	 *            the visitor
	 * @see Resource#getResolvedUriParameters()
	 */
	protected static void visitResolvedUriParameters(Resource resource, ResourceVisitor visitor) {
		if (resource.getResolvedUriParameters() == null)
			return;
		for (Entry<String, UriParameter> entry : resource.getResolvedUriParameters().entrySet()) {
			visitor.visitResolvedUriParameter(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting base URI parameters of the
	 * specified resource. This implementation calls
	 * {@link ResourceVisitor#visitBaseUriParameter(String, List)} for all
	 * available base URI parameters.
	 * 
	 * @param resource
	 *            the resource
	 * @param visitor
	 *            the visitor
	 * @see Resource#getBaseUriParameters()
	 */
	protected static void visitBaseUriParameters(Resource resource, ResourceVisitor visitor) {
		if (resource.getBaseUriParameters() == null)
			return;
		for (Entry<String, List<UriParameter>> entry : resource.getBaseUriParameters().entrySet()) {
			visitor.visitBaseUriParameter(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting actions of the specified
	 * resource. This implementation calls
	 * {@link ResourceVisitor#visitAction(ActionType, Action)} for all available
	 * actions.
	 * 
	 * @param resource
	 *            the resource
	 * @param visitor
	 *            the visitor
	 * @see Resource#getActions()
	 */
	protected static void visitActions(Resource resource, ResourceVisitor visitor) {
		if (resource.getActions() == null)
			return;
		for (Entry<ActionType, Action> entry : resource.getActions().entrySet()) {
			visitor.visitAction(entry.getKey(), entry.getValue());
		}
	}

}
