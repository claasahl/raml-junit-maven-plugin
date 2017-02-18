package com.github.claasahl.raml.visitor.debug;

import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.parameter.Header;

import com.github.claasahl.raml.visitor.ResponseVisitorBase;

public class DebugResponseVisitor extends ResponseVisitorBase {

	@Override
	public void visitResponse(Response response) {
		System.out.println(response);		
		super.visitResponse(response);
	}

	@Override
	public void visitBody(String key, MimeType mimeType) {
		System.out.format("k:%s, m:%s\n", key, mimeType);
		super.visitBody(key, mimeType);
	}

	@Override
	public void visitHeader(String key, Header header) {
		System.out.format("k:%s, h:%s\n", key, header);
		super.visitHeader(key, header);
	}

}
