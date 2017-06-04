package com.github.claasahl.raml.v08.visitor;

import org.raml.v2.api.model.v08.api.Api;
import org.raml.v2.api.model.v08.api.DocumentationItem;
import org.raml.v2.api.model.v08.api.GlobalSchema;
import org.raml.v2.api.model.v08.methods.Trait;
import org.raml.v2.api.model.v08.parameters.Parameter;
import org.raml.v2.api.model.v08.resources.Resource;
import org.raml.v2.api.model.v08.resources.ResourceType;
import org.raml.v2.api.model.v08.security.SecurityScheme;
import org.raml.v2.api.model.v08.security.SecuritySchemeRef;

import com.github.claasahl.raml.common.visitor.Coordinator;

/**
 * The class {@link ApiCoordinator}.
 * <p>
 * An implementation of the {@link Coordinator} pattern, which "iterates" over
 * relevant attributes of the {@link Api} class and delegates them to their
 * respective visit-methods of an {@link ApiVisitor}.
 * <p>
 * This coordinator is thread-safe and context-free. As such, instances of this
 * class can be used multiple times (i.e. several RAML specification may be
 * processed).
 * 
 * @author Claas Ahlrichs
 *
 */
public class ApiCoordinator implements Coordinator<Api, ApiVisitor> {

	@Override
	public void coordinate(Api visitee, ApiVisitor visitor) {
		visitor.beforeVisit(visitee);
		visitBaseUriParameter(visitee, visitor);
		visitUriParameter(visitee, visitor);
		visitProtocol(visitee, visitor);
		visitSchema(visitee, visitor);
		visitTrait(visitee, visitor);
		visitSecuredBy(visitee, visitor);
		visitSecurityScheme(visitee, visitor);
		visitResourceType(visitee, visitor);
		visitResource(visitee, visitor);
		visitDocumentation(visitee, visitor);
		visitor.afterVisit(visitee);
	}

	/**
	 * A support method for iterating and visiting base URI parameters. This
	 * implementation calls {@link ApiVisitor#visitBaseUriParameter(Parameter)}
	 * for all available base URI parameters.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Api#baseUriParameters()
	 */
	protected static void visitBaseUriParameter(Api visitee, ApiVisitor visitor) {
		if (visitee.baseUriParameters() == null)
			return;
		for (Parameter baseUriParameter : visitee.baseUriParameters()) {
			visitor.visitBaseUriParameter(baseUriParameter);
		}
	}

	/**
	 * A support method for iterating and visiting URI parameters. This
	 * implementation calls {@link ApiVisitor#visitUriParameter(Parameter)} for
	 * all available URI parameters.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Api#uriParameters()
	 */
	protected static void visitUriParameter(Api visitee, ApiVisitor visitor) {
		if (visitee.uriParameters() == null)
			return;
		for (Parameter uriParameter : visitee.uriParameters()) {
			visitor.visitUriParameter(uriParameter);
		}
	}

	/**
	 * A support method for iterating and visiting protocols. This
	 * implementation calls {@link ApiVisitor#visitProtocol(String)} for all
	 * available protocols.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Api#protocols()
	 */
	protected static void visitProtocol(Api visitee, ApiVisitor visitor) {
		if (visitee.protocols() == null)
			return;
		for (String protocol : visitee.protocols()) {
			visitor.visitProtocol(protocol);
		}
	}

	/**
	 * A support method for iterating and visiting schema definitions. This
	 * implementation calls {@link ApiVisitor#visitSchema(GlobalSchema) for all
	 * available schema definitions.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Api#schemas()
	 */
	protected static void visitSchema(Api visitee, ApiVisitor visitor) {
		if (visitee.schemas() == null)
			return;
		for (GlobalSchema schema : visitee.schemas()) {
			visitor.visitSchema(schema);
		}
	}

	/**
	 * A support method for iterating and visiting traits. This implementation
	 * calls {@link ApiVisitor#visitTrait(Trait)} for all available traits.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Api#traits()
	 */
	protected static void visitTrait(Api visitee, ApiVisitor visitor) {
		if (visitee.traits() == null)
			return;
		for (Trait trait : visitee.traits()) {
			visitor.visitTrait(trait);
		}
	}

	/**
	 * A support method for iterating and visiting security schemes. This
	 * implementation calls {@link ApiVisitor#visitSecuredBy(SecuritySchemeRef)}
	 * for all available security schemes.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Api#securedBy()
	 */
	protected static void visitSecuredBy(Api visitee, ApiVisitor visitor) {
		if (visitee.securedBy() == null)
			return;
		for (SecuritySchemeRef securedBy : visitee.securedBy()) {
			visitor.visitSecuredBy(securedBy);
		}
	}

	/**
	 * A support method for iterating and visiting security schemes. This
	 * implementation calls
	 * {@link ApiVisitor#visitSecurityScheme(SecurityScheme)} for all available
	 * security schemes.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Api#securitySchemes()
	 */
	protected static void visitSecurityScheme(Api visitee, ApiVisitor visitor) {
		if (visitee.securitySchemes() == null)
			return;
		for (SecurityScheme securityScheme : visitee.securitySchemes()) {
			visitor.visitSecurityScheme(securityScheme);
		}
	}

	/**
	 * A support method for iterating and visiting resource types. This
	 * implementation calls {@link ApiVisitor#visitResourceType(ResourceType)}
	 * for all available resource types.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Api#resourceTypes()
	 */
	protected static void visitResourceType(Api visitee, ApiVisitor visitor) {
		if (visitee.resourceTypes() == null)
			return;
		for (ResourceType resourceType : visitee.resourceTypes()) {
			visitor.visitResourceType(resourceType);
		}
	}

	/**
	 * A support method for iterating and visiting resources. This
	 * implementation calls {@link ApiVisitor#visitResource(Resource)} for all
	 * available resources.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Api#resources()
	 */
	protected static void visitResource(Api visitee, ApiVisitor visitor) {
		if (visitee.resources() == null)
			return;
		for (Resource resource : visitee.resources()) {
			visitor.visitResource(resource);
		}
	}

	/**
	 * A support method for iterating and visiting reference documentations.
	 * This implementation calls
	 * {@link ApiVisitor#visitDocumentation(DocumentationItem)} for all
	 * available reference documentations.
	 * 
	 * @param visitee
	 *            the object being visited
	 * @param visitor
	 *            the visitor
	 * @see Api#documentation()
	 */
	protected static void visitDocumentation(Api visitee, ApiVisitor visitor) {
		if (visitee.documentation() == null)
			return;
		for (DocumentationItem documentationItem : visitee.documentation()) {
			visitor.visitDocumentation(documentationItem);
		}
	}

}
