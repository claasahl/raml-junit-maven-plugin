package com.github.claasahl.raml.visitor.debug;

import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.parameter.Header;

import com.github.claasahl.raml.visitor.ResponseVisitorBase;

public class DebugResponseVisitor extends ResponseVisitorBase {

	@Override
	public void visitResponse(Response response) {
		// TODO Auto-generated method stub
		super.visitResponse(response);
	}

	@Override
	public void visitBody(String key, MimeType mimeType) {
		// TODO Auto-generated method stub
		super.visitBody(key, mimeType);
	}

	@Override
	public void visitHeader(String key, Header header) {
		// TODO Auto-generated method stub
		super.visitHeader(key, header);
	}

}
