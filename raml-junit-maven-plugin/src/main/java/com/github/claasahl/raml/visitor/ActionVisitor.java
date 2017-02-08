package com.github.claasahl.raml.visitor;

import java.util.List;

import org.raml.model.Action;
import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.Header;
import org.raml.model.parameter.QueryParameter;
import org.raml.model.parameter.UriParameter;

public interface ActionVisitor {
	
	void visitAction(Action action);
	void visitBaseUriParameter(String key, List<UriParameter> uriParameters);
	void visitBody(String key, MimeType mimeType);
	void visitHeader(String key, Header header);
	void visitQueryParameter(String key, QueryParameter queryParameter);
	void visitSecurityReference(SecurityReference securityReference);
	void visitResponse(String key, Response response);

}
