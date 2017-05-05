package com.github.claasahl.raml.visitor;

import java.nio.file.Path;
import java.util.Map.Entry;

import org.raml.model.DocumentationItem;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.UriParameter;

/**
 * The class {@link RamlCoordinator}.
 * <p>
 * An implementation of the {@link Coordinator} pattern, which "iterates" over
 * relevant attributes of the {@link Raml} class and delegates them to their
 * respective visit-methods of an {@link RamlVisitor}.
 * <p>
 * This coordinator is thread-safe and context-free. As such, instances of this
 * class can be used multiple times (i.e. several RAML specification may be
 * processed).
 * 
 * @author Claas Ahlrichs
 *
 */
public class RamlCoordinator implements Coordinator {

	/**
	 * Visits the specified RAML specification and then delegates its attributes
	 * to with the specified visitor.
	 * 
	 * @param raml
	 *            the RAML specification
	 * @param ramlPath
	 *            path to the RAML specification
	 * @param visitor
	 *            the visitor
	 */
	public void visitRaml(Raml raml, Path ramlPath, RamlVisitor visitor) {
		visitor.beforeVisit(raml, ramlPath);
		visitBaseUriParameters(raml, visitor);
		visitDocumentationItems(raml, visitor);
		visitSecurityReferences(raml, visitor);
		visitResources(raml, visitor);
		visitor.afterVisit(raml, ramlPath);
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
	protected static void visitSecurityReferences(Raml raml, RamlVisitor visitor) {
		if (raml.getSecuredBy() == null)
			return;
		for (SecurityReference securityReference : raml.getSecuredBy()) {
			visitor.visitSecurityReference(securityReference);
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
	protected static void visitResources(Raml raml, RamlVisitor visitor) {
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
	protected static void visitBaseUriParameters(Raml raml, RamlVisitor visitor) {
		if (raml.getBaseUriParameters() == null)
			return;
		for (Entry<String, UriParameter> entry : raml.getBaseUriParameters().entrySet()) {
			visitor.visitBaseUriParameter(entry.getKey(), entry.getValue());
		}
	}

}
