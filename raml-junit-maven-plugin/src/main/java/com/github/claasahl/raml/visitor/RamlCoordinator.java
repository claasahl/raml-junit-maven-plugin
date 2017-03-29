package com.github.claasahl.raml.visitor;

import java.nio.file.Path;
import java.util.Map;
import java.util.Map.Entry;

import org.raml.model.DocumentationItem;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.Response;
import org.raml.model.SecurityReference;
import org.raml.model.SecurityScheme;
import org.raml.model.Template;
import org.raml.model.parameter.UriParameter;

/**
 * The class {@link RamlVisitorBase}.
 * <p>
 * A base implementation of the {@link RamlVisitor} interface, which provides
 * default implementations for all methods of the aforementioned interface. The
 * method {@link #visitResponse(Response)} already implements a mechanism for
 * visiting all composite fields (e.g. URI parameters or resources). The default
 * implementation of the remaining methods is empty.
 * <p>
 * This visitor is context-free. As such, instances of this class can be used to
 * multiple times (i.e. several RAML specifications may be processed).
 * 
 * @author Claas
 *
 */
public class RamlCoordinator {

	/**
	 * Visits the specified {@link Raml} specification. The default
	 * implementation visits all composite fields (e.g. URI parameters or
	 * resources).
	 * <p>
	 * <b>Hint:</b> When overwriting this method, it is recommended to still
	 * call this method. Otherwise none of the composite fields may be visited.
	 * 
	 * @see RamlVisitor#visitRaml(Raml, Path)
	 */
	public void visitRaml(Raml raml, Path ramlPath, RamlVisitor visitor) {
		visitBaseUriParameters(raml, visitor);
		visitDocumentationItems(raml, visitor);
		visitResources(raml, visitor);
		visitSchemas(raml, visitor);
		visitSecurityReferences(raml, visitor);
		visitSecuritySchemes(raml, visitor);
		visitResourceTypes(raml, visitor);
		visitTraits(raml, visitor);
	}

	/**
	 * A support method for iterating and visiting traits of the specified RAML
	 * specification. This implementation calls
	 * {@link RamlVisitor#visitTrait(Map)} for all available traits.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @param visitor
	 *            the visitor
	 * @see Raml#getTraits()
	 */
	protected void visitTraits(Raml raml, RamlVisitor visitor) {
		if (raml.getTraits() == null)
			return;
		for (Map<String, Template> trait : raml.getTraits()) {
			visitor.visitTrait(trait);
		}
	}

	/**
	 * A support method for iterating and visiting resource types of the
	 * specified RAML specification. This implementation calls
	 * {@link RamlVisitor#visitResourceType(Map)} for all available resource
	 * types.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @param visitor
	 *            the visitor
	 * @see Raml#getResourceTypes()
	 */
	protected void visitResourceTypes(Raml raml, RamlVisitor visitor) {
		if (raml.getResourceTypes() == null)
			return;
		for (Map<String, Template> resourceType : raml.getResourceTypes()) {
			visitor.visitResourceType(resourceType);
		}
	}

	/**
	 * A support method for iterating and visiting security schemes of the
	 * specified RAML specification. This implementation calls
	 * {@link RamlVisitor#visitSecurityScheme(Map)} for all available security
	 * schemes.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @param visitor
	 *            the visitor
	 * @see Raml#getSecuritySchemes()
	 */
	protected void visitSecuritySchemes(Raml raml, RamlVisitor visitor) {
		if (raml.getSecuritySchemes() == null)
			return;
		for (Map<String, SecurityScheme> securityScheme : raml.getSecuritySchemes()) {
			visitor.visitSecurityScheme(securityScheme);
		}
	}

	/**
	 * A support method for iterating and visiting security references of the
	 * specified RAML specification. This implementation calls
	 * {@link RamlVisitor#visitSecurityReference(SecurityReference)} for all
	 * available security references.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @param visitor
	 *            the visitor
	 * @see Raml#getSecuredBy()
	 */
	protected void visitSecurityReferences(Raml raml, RamlVisitor visitor) {
		if (raml.getSecuredBy() == null)
			return;
		for (SecurityReference securityReference : raml.getSecuredBy()) {
			visitor.visitSecurityReference(securityReference);
		}
	}

	/**
	 * A support method for iterating and visiting schemas of the specified RAML
	 * specification. This implementation calls
	 * {@link RamlVisitor#visitSchema(Map)} for all available schemas.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @param visitor
	 *            the visitor
	 * @see Raml#getSchemas()
	 */
	protected void visitSchemas(Raml raml, RamlVisitor visitor) {
		if (raml.getSchemas() == null)
			return;
		for (Map<String, String> schema : raml.getSchemas()) {
			visitor.visitSchema(schema);
		}
	}

	/**
	 * A support method for iterating and visiting resources of the specified
	 * RAML specification. This implementation calls
	 * {@link RamlVisitor#visitResource(String, Resource)} for all available
	 * resources.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @param visitor
	 *            the visitor
	 * @see Raml#getResources()
	 */
	protected void visitResources(Raml raml, RamlVisitor visitor) {
		if (raml.getResources() == null)
			return;
		for (Entry<String, Resource> entry : raml.getResources().entrySet()) {
			visitor.visitResource(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting documentation items of the
	 * specified RAML specification. This implementation calls
	 * {@link RamlVisitor#visitDocumentationItem(DocumentationItem)} for all
	 * available documentation items.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @param visitor
	 *            the visitor
	 * @see Raml#getDocumentation()
	 */
	protected void visitDocumentationItems(Raml raml, RamlVisitor visitor) {
		if (raml.getDocumentation() == null)
			return;
		for (DocumentationItem documentationItem : raml.getDocumentation()) {
			visitor.visitDocumentationItem(documentationItem);
		}
	}

	/**
	 * A support method for iterating and visiting base URI parameters of the
	 * specified RAML specification. This implementation calls
	 * {@link RamlVisitor#visitBaseUriParameter(String, UriParameter)} for all
	 * available base URI parameters.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @param visitor
	 *            the visitor
	 * @see Raml#getBaseUriParameters()
	 */
	protected void visitBaseUriParameters(Raml raml, RamlVisitor visitor) {
		if (raml.getBaseUriParameters() == null)
			return;
		for (Entry<String, UriParameter> entry : raml.getBaseUriParameters().entrySet()) {
			visitor.visitBaseUriParameter(entry.getKey(), entry.getValue());
		}
	}

}
