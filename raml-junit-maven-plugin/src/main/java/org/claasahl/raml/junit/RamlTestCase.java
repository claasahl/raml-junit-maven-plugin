package org.claasahl.raml.junit;

import java.nio.file.Path;

import org.raml.model.Action;
import org.raml.model.MimeType;
import org.raml.model.Protocol;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;

public class RamlTestCase {

	private Raml raml;
	private Path ramlPath;
	private Resource resource;
	private Action action;
	private Protocol protocol;
	private MimeType mimeType;
	private String statusCode;
	private SecurityReference securedBy;

	public RamlTestCase() {
	}

	public RamlTestCase(RamlTestCase testCase) {
		this.raml = testCase.getRaml();
		this.ramlPath = testCase.getRamlPath();
		this.resource = testCase.getResource();
		this.action = testCase.getAction();
		this.protocol = testCase.getProtocol();
		this.mimeType = testCase.getMimeType();
		this.statusCode = testCase.getStatusCode();
		this.securedBy = testCase.securedBy;
	}

	public Raml getRaml() {
		return raml;
	}

	public void setRaml(Raml raml) {
		this.raml = raml;
	}

	public Path getRamlPath() {
		return ramlPath;
	}

	public void setRamlPath(Path ramlPath) {
		this.ramlPath = ramlPath;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	public MimeType getMimeType() {
		return mimeType;
	}

	public void setMimeType(MimeType mimeType) {
		this.mimeType = mimeType;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public SecurityReference getSecuredBy() {
		return securedBy;
	}

	public void setSecuredBy(SecurityReference securedBy) {
		this.securedBy = securedBy;
	}

}
