package org.claasahl.raml.junit;

import java.nio.file.Path;

import org.raml.model.Action;
import org.raml.model.MimeType;
import org.raml.model.Protocol;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;

public class RamlTestCaseBuilder {
	private final RamlTestCase testCase;

	public RamlTestCaseBuilder() {
		this.testCase = new RamlTestCase();
	}

	public RamlTestCaseBuilder setRaml(Raml raml, Path ramlPath) {
		this.testCase.setRaml(raml);
		this.testCase.setRamlPath(ramlPath);
		return this;
	}

	public RamlTestCaseBuilder setResource(Resource resource) {
		this.testCase.setResource(resource);
		return this;
	}

	public RamlTestCaseBuilder setAction(Action action) {
		this.testCase.setAction(action);
		return this;
	}

	public RamlTestCaseBuilder setProtocol(Protocol protocol) {
		this.testCase.setProtocol(protocol);
		return this;
	}

	public RamlTestCaseBuilder setMimeType(MimeType mimeType) {
		this.testCase.setMimeType(mimeType);
		return this;
	}

	public RamlTestCaseBuilder setStatusCode(String statusCode) {
		this.testCase.setStatusCode(statusCode);
		return this;
	}

	public RamlTestCaseBuilder setSecuredBy(SecurityReference securedBy) {
		this.testCase.setSecuredBy(securedBy);
		return this;
	}

	public RamlTestCase build() {
		return new RamlTestCase(this.testCase);
	}

}
