package com.github.claasahl.raml.v08.visitor;

import java.util.List;

import org.raml.v2.api.model.v08.methods.Method;
import org.raml.v2.api.model.v08.methods.TraitRef;
import org.raml.v2.api.model.v08.parameters.Parameter;
import org.raml.v2.api.model.v08.resources.Resource;
import org.raml.v2.api.model.v08.security.SecuritySchemeRef;

import com.github.claasahl.raml.common.visitor.VisitorStrategy;

/**
 * The interface {@link ResourceVisitor}.
 * <p>
 * Superclass of all resource-based visitors. This class is intended to model a
 * visitor for {@link Resource} instances and their (composite) fields.
 * Implementations of this class will most likely extract details (e.g.
 * sub-resources or URI parameters) or otherwise process APIs.
 * <p>
 * This class resembles a derivation of the <i>visitor</i> design pattern. It
 * includes a visit-method for composite fields of the {@link Resource} class.
 * 
 * @author Claas Ahlrichs
 * @see VisitorStrategy
 *
 */
public interface ResourceVisitor extends VisitorStrategy<Resource> {

	/**
	 * Visit method for traits returned by {@link Resource#is()}.
	 * 
	 * @param trait
	 *            a trait
	 */
	void visitTrait(TraitRef trait);

	/**
	 * Visit method for security schemes returned by {@link Resource#}.
	 * 
	 * @param securedBy
	 *            a security scheme
	 */
	void visitSecuredBy(SecuritySchemeRef securedBy);

	/**
	 * Visit method for URI parameters returned by
	 * {@link Resource#uriParameters()}.
	 * 
	 * @param uriParameter
	 *            a URI parameter
	 */
	void visitUriParameter(Parameter uriParameter);

	/**
	 * Visit method for methods returned by {@link Resource#methods()}.
	 * 
	 * @param method
	 *            a method
	 */
	void visitMethod(Method method);

	/**
	 * Visit method for sub-resources returned by {@link Resource#resources()}.
	 * 
	 * @param resource
	 *            a sub-resource
	 */
	void visitSubResource(Resource resource);

	/**
	 * Visit method for base URI parameters returned by
	 * {@link Resource#baseUriParameters()}.
	 * 
	 * @param baseUriParameter
	 *            a base URI parameter
	 */
	void visitBaseUriParameter(Parameter baseUriParameter);
}
