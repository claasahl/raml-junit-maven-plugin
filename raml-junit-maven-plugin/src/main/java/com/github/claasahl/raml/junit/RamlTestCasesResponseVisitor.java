package com.github.claasahl.raml.junit;

import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.parameter.Header;

import com.github.claasahl.raml.visitor.ResponseVisitorBase;

public class RamlTestCasesResponseVisitor extends ResponseVisitorBase {
	
	private final RamlTestCaseBuilder builder;

	public RamlTestCasesResponseVisitor(RamlTestCaseBuilder builder) {
		this.builder = builder;
	}

	@Override
	public void visitResponse(Response response) {
		System.out.println("RamlTestCasesResponseVisitor.visitResponse()");
		super.visitResponse(response);
	}

	@Override
	public void visitBody(String key, MimeType mimeType) {
		this.builder.setMimeType(mimeType);
		super.visitBody(key, mimeType);
	}

	@Override
	public void visitHeader(String key, Header header) {
		this.builder.setStatusCode(key);
		super.visitHeader(key, header);
	}

}
