package com.github.claasahl.raml.visitor;

import java.util.Map;
import java.util.Map.Entry;

import org.raml.model.DocumentationItem;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.SecurityScheme;
import org.raml.model.Template;
import org.raml.model.parameter.UriParameter;

public abstract class RamlVisitorBase implements RamlVisitor {

	@Override
	public void visitRaml(Raml raml) {
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
		// UriParameter is a "flat" object, there is no need to dive any deeper
	}

	@Override
	public void visitDocumentationItem(DocumentationItem documentationItem) {
		// DocumentationItem is a "flat" object, there is no need to dive any deeper
	}

	@Override
	public void visitResource(String key, Resource resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSchema(Map<String, String> schema) {
		// flat
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		// SecurityReference is a "flat" object, there is no need to dive any deeper
	}

	@Override
	public void visitSecurityScheme(Map<String, SecurityScheme> securityScheme) {
		// FIXME ???
	}

	@Override
	public void visitResourceType(Map<String, Template> resourceType) {
		// flat
	}

	@Override
	public void visitTrait(Map<String, Template> trait) {
		// flat
	}
	
	private void visitTraits(Raml raml) {
		for (Map<String, Template> trait : raml.getTraits()) {
			visitTrait(trait);
		}
	}

	private void visitResourceTypes(Raml raml) {
		for (Map<String, Template> resourceType : raml.getResourceTypes()) {
			visitResourceType(resourceType);
		}
	}

	private void visitSecuritySchemes(Raml raml) {
		for (Map<String, SecurityScheme> securityScheme : raml.getSecuritySchemes()) {
			visitSecurityScheme(securityScheme);
		}
	}

	private void visitSecurityReferences(Raml raml) {
		for (SecurityReference securityReference : raml.getSecuredBy()) {
			visitSecurityReference(securityReference);
		}
	}

	private void visitSchemas(Raml raml) {
		for (Map<String, String> schema : raml.getSchemas()) {
			visitSchema(schema);
		}
	}

	private void visitResources(Raml raml) {
		for (Entry<String, Resource> entry : raml.getResources().entrySet()) {
			visitResource(entry.getKey(), entry.getValue());
		}
	}

	private void visitDocumentationItems(Raml raml) {
		for (DocumentationItem documentationItem : raml.getDocumentation()) {
			visitDocumentationItem(documentationItem);
		}
	}

	private void visitBaseUriParameters(Raml raml) {
		for (Entry<String, UriParameter> entry : raml.getBaseUriParameters().entrySet()) {
			visitBaseUriParameter(entry.getKey(), entry.getValue());
		}
	}

}
