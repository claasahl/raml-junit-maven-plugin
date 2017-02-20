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
public abstract class RamlVisitorBase implements RamlVisitor {

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
	@Override
	public void visitRaml(Raml raml, Path ramlPath) {
		visitBaseUriParameters(raml);
		visitDocumentationItems(raml);
		visitResources(raml);
		visitSchemas(raml);
		visitSecurityReferences(raml);
		visitSecuritySchemes(raml);
		visitResourceTypes(raml);
		visitTraits(raml);
	}

	@Override
	public void visitBaseUriParameter(String key, UriParameter uriParameter) {
		// empty default implementation
	}

	@Override
	public void visitDocumentationItem(DocumentationItem documentationItem) {
		// empty default implementation
	}

	@Override
	public void visitResource(String key, Resource resource) {
		// empty default implementation
	}

	@Override
	public void visitSchema(Map<String, String> schema) {
		// empty default implementation
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		// FIXME ???
	}

	@Override
	public void visitSecurityScheme(Map<String, SecurityScheme> securityScheme) {
		// FIXME ???
	}

	@Override
	public void visitResourceType(Map<String, Template> resourceType) {
		// empty default implementation
	}

	@Override
	public void visitTrait(Map<String, Template> trait) {
		// empty default implementation
	}

	/**
	 * A support method for iterating and visiting traits of the specified RAML
	 * specification. This implementation calls {@link #visitTrait(Map)} for all
	 * available traits.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @see Raml#getTraits()
	 */
	protected void visitTraits(Raml raml) {
		if (raml.getTraits() == null)
			return;
		for (Map<String, Template> trait : raml.getTraits()) {
			visitTrait(trait);
		}
	}

	/**
	 * A support method for iterating and visiting resource types of the
	 * specified RAML specification. This implementation calls
	 * {@link #visitResourceType(Map)} for all available resource types.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @see Raml#getResourceTypes()
	 */
	protected void visitResourceTypes(Raml raml) {
		if (raml.getResourceTypes() == null)
			return;
		for (Map<String, Template> resourceType : raml.getResourceTypes()) {
			visitResourceType(resourceType);
		}
	}

	/**
	 * A support method for iterating and visiting security schemes of the
	 * specified RAML specification. This implementation calls
	 * {@link #visitSecurityScheme(Map)} for all available security schemes.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @see Raml#getSecuritySchemes()
	 */
	protected void visitSecuritySchemes(Raml raml) {
		if (raml.getSecuritySchemes() == null)
			return;
		for (Map<String, SecurityScheme> securityScheme : raml.getSecuritySchemes()) {
			visitSecurityScheme(securityScheme);
		}
	}

	/**
	 * A support method for iterating and visiting security references of the
	 * specified RAML specification. This implementation calls
	 * {@link #visitSecurityReference(SecurityReference)} for all available
	 * security references.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @see Raml#getSecuredBy()
	 */
	protected void visitSecurityReferences(Raml raml) {
		if (raml.getSecuredBy() == null)
			return;
		for (SecurityReference securityReference : raml.getSecuredBy()) {
			visitSecurityReference(securityReference);
		}
	}

	/**
	 * A support method for iterating and visiting schemas of the specified RAML
	 * specification. This implementation calls {@link #visitSchema(Map)} for
	 * all available schemas.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @see Raml#getSchemas()
	 */
	protected void visitSchemas(Raml raml) {
		if (raml.getSchemas() == null)
			return;
		for (Map<String, String> schema : raml.getSchemas()) {
			visitSchema(schema);
		}
	}

	/**
	 * A support method for iterating and visiting resources of the specified
	 * RAML specification. This implementation calls
	 * {@link #visitResource(String, Resource)} for all available resources.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @see Raml#getResources()
	 */
	protected void visitResources(Raml raml) {
		if (raml.getResources() == null)
			return;
		for (Entry<String, Resource> entry : raml.getResources().entrySet()) {
			visitResource(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * A support method for iterating and visiting documentation items of the
	 * specified RAML specification. This implementation calls
	 * {@link #visitDocumentationItem(DocumentationItem)} for all available
	 * documentation items.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @see Raml#getDocumentation()
	 */
	protected void visitDocumentationItems(Raml raml) {
		if (raml.getDocumentation() == null)
			return;
		for (DocumentationItem documentationItem : raml.getDocumentation()) {
			visitDocumentationItem(documentationItem);
		}
	}

	/**
	 * A support method for iterating and visiting base URI parameters of the
	 * specified RAML specification. This implementation calls
	 * {@link #visitBaseUriParameter(String, UriParameter)} for all available
	 * base URI parameters.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @see Raml#getBaseUriParameters()
	 */
	protected void visitBaseUriParameters(Raml raml) {
		if (raml.getBaseUriParameters() == null)
			return;
		for (Entry<String, UriParameter> entry : raml.getBaseUriParameters().entrySet()) {
			visitBaseUriParameter(entry.getKey(), entry.getValue());
		}
	}

}
