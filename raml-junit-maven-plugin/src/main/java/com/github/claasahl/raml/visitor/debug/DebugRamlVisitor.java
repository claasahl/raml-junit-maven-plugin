package com.github.claasahl.raml.visitor.debug;

import java.util.Map;

import org.raml.model.DocumentationItem;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.SecurityScheme;
import org.raml.model.Template;
import org.raml.model.parameter.UriParameter;

import com.github.claasahl.raml.visitor.RamlVisitorBase;

public class DebugRamlVisitor extends RamlVisitorBase {

	@Override
	public void visitRaml(Raml raml) {
		// TODO Auto-generated method stub
		super.visitRaml(raml);
	}

	@Override
	public void visitBaseUriParameter(String key, UriParameter uriParameter) {
		// TODO Auto-generated method stub
		super.visitBaseUriParameter(key, uriParameter);
	}

	@Override
	public void visitDocumentationItem(DocumentationItem documentationItem) {
		// TODO Auto-generated method stub
		super.visitDocumentationItem(documentationItem);
	}

	@Override
	public void visitResource(String key, Resource resource) {
		// TODO Auto-generated method stub
		super.visitResource(key, resource);
	}

	@Override
	public void visitSchema(Map<String, String> schema) {
		// TODO Auto-generated method stub
		super.visitSchema(schema);
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		// TODO Auto-generated method stub
		super.visitSecurityReference(securityReference);
	}

	@Override
	public void visitSecurityScheme(Map<String, SecurityScheme> securityScheme) {
		// TODO Auto-generated method stub
		super.visitSecurityScheme(securityScheme);
	}

	@Override
	public void visitResourceType(Map<String, Template> resourceType) {
		// TODO Auto-generated method stub
		super.visitResourceType(resourceType);
	}

	@Override
	public void visitTrait(Map<String, Template> trait) {
		// TODO Auto-generated method stub
		super.visitTrait(trait);
	}

}
