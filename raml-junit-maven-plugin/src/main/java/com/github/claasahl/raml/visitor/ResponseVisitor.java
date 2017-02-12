package com.github.claasahl.raml.visitor;

import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.parameter.Header;

public interface ResponseVisitor {
	
	void visitResponse(Response response);
	void visitBody(String key, MimeType mimeType);
	void visitHeader(String key, Header header);

}
