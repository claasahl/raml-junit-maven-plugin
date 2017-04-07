package com.github.claasahl.raml.junit;

import java.util.function.Consumer;

import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.parameter.Header;

import com.github.claasahl.raml.visitor.ResponseVisitor;

public class RamlTestCasesResponseVisitor implements ResponseVisitor {

	private final RamlTestCaseBuilder builder;
	private final Consumer<RamlTestCase> callback;

	public RamlTestCasesResponseVisitor(Consumer<RamlTestCase> callback, RamlTestCaseBuilder builder) {
		this.callback = callback;
		this.builder = builder;
	}

	@Override
	public void visitResponse(Response response) {
		// TODO this should be called AFTER visitBody and visitHeader
		// ... but it is not
		RamlTestCase testCase = this.builder.build();
		callback.accept(testCase);
	}
	
	@Override
	public void visitBody(String key, MimeType mimeType) {
		this.builder.setMimeType(mimeType);
	}


	@Override
	public void visitHeader(String key, Header header) {
		// nothing to be done
	}

}
