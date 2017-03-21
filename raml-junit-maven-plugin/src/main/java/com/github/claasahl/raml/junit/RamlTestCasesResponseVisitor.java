package com.github.claasahl.raml.junit;

import java.util.function.Consumer;

import org.raml.model.MimeType;
import org.raml.model.Response;

import com.github.claasahl.raml.visitor.ResponseVisitorBase;

public class RamlTestCasesResponseVisitor extends ResponseVisitorBase {

	private final RamlTestCaseBuilder builder;
	private final Consumer<RamlTestCase> callback;

	public RamlTestCasesResponseVisitor(Consumer<RamlTestCase> callback, RamlTestCaseBuilder builder) {
		this.callback = callback;
		this.builder = builder;
	}

	@Override
	public void visitResponse(Response response) {
		super.visitResponse(response);
		RamlTestCase testCase = this.builder.build();
		callback.accept(testCase);
	}

	@Override
	public void visitBody(String key, MimeType mimeType) {
		this.builder.setMimeType(mimeType);
		super.visitBody(key, mimeType);
	}

}
