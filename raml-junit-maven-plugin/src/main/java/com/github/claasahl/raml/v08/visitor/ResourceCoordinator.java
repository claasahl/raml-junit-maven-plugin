package com.github.claasahl.raml.v08.visitor;

import org.raml.v2.api.model.v08.methods.Method;
import org.raml.v2.api.model.v08.methods.TraitRef;
import org.raml.v2.api.model.v08.parameters.Parameter;
import org.raml.v2.api.model.v08.resources.Resource;
import org.raml.v2.api.model.v08.security.SecuritySchemeRef;

import com.github.claasahl.raml.common.visitor.Coordinator;

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
public class ResourceCoordinator implements Coordinator<Resource, ResourceVisitor> {

	@Override
	public void coordinate(Resource visitee, ResourceVisitor visitor) {
		visitor.beforeVisit(visitee);
		visitTrait(visitee, visitor);
		visitSecuredBy(visitee, visitor);
		visitUriParameter(visitee, visitor);
		visitMethod(visitee, visitor);
		visitSubResource(visitee, visitor);
		visitBaseUriParameter(visitee, visitor);
		visitor.afterVisit(visitee);
	}

	/**
	 * A support method for iterating and visiting traits. This implementation
	 * calls {@link ResourceVisitor#visitTrait(TraitRef)} for all available
	 * trait.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Resource#is()
	 */
	protected static void visitTrait(Resource visitee, ResourceVisitor visitor) {
		if (visitee.is() == null)
			return;
		for (TraitRef trait : visitee.is()) {
			visitor.visitTrait(trait);
		}
	}

	/**
	 * A support method for iterating and visiting  security schemes. This implementation
	 * calls {@link ResourceVisitor#visitSecuredBy(SecuritySchemeRef)} for all
	 * available  security schemes.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Resource#securedBy()
	 */
	protected static void visitSecuredBy(Resource visitee, ResourceVisitor visitor) {
		if (visitee.securedBy() == null)
			return;
		for (SecuritySchemeRef securedBy : visitee.securedBy()) {
			visitor.visitSecuredBy(securedBy);
		}
	}

	/**
	 * A support method for iterating and visiting URI parameters. This implementation
	 * calls {@link ResourceVisitor#visitUriParameter(Parameter)} for all
	 * available URI parameters.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Resource#uriParameters()
	 */
	protected static void visitUriParameter(Resource visitee, ResourceVisitor visitor) {
		if (visitee.uriParameters() == null)
			return;
		for (Parameter uriParameter : visitee.uriParameters()) {
			visitor.visitUriParameter(uriParameter);
		}
	}

	/**
	 * A support method for iterating and visiting methods. This implementation
	 * calls {@link ResourceVisitor#visitMethod(Method)} for all available
	 * methods.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Resource#methods()
	 */
	protected static void visitMethod(Resource visitee, ResourceVisitor visitor) {
		if (visitee.methods() == null)
			return;
		for (Method method : visitee.methods()) {
			visitor.visitMethod(method);
		}
	}

	/**
	 * A support method for iterating and visiting sub-resources. This implementation
	 * calls {@link ResourceVisitor#visitSubResource(Resource)} for all
	 * available sub-resources.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Resource#resources()
	 */
	protected static void visitSubResource(Resource visitee, ResourceVisitor visitor) {
		if (visitee.resources() == null)
			return;
		for (Resource resource : visitee.resources()) {
			visitor.visitSubResource(resource);
		}
	}

	/**
	 * A support method for iterating and visiting base URI parameters. This implementation
	 * calls {@link ResourceVisitor#visitBaseUriParameter(Parameter)} for all
	 * available base URI parameters.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Resource#baseUriParameters()
	 */
	protected static void visitBaseUriParameter(Resource visitee, ResourceVisitor visitor) {
		if (visitee.baseUriParameters() == null)
			return;
		for (Parameter baseUriParameter : visitee.baseUriParameters()) {
			visitor.visitBaseUriParameter(baseUriParameter);
		}
	}

}
