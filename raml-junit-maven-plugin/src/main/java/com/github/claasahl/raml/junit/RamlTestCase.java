package com.github.claasahl.raml.junit;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((mimeType == null) ? 0 : mimeType.hashCode());
		result = prime * result + ((protocol == null) ? 0 : protocol.hashCode());
		result = prime * result + ((raml == null) ? 0 : raml.hashCode());
		result = prime * result + ((ramlPath == null) ? 0 : ramlPath.hashCode());
		result = prime * result + ((resource == null) ? 0 : resource.hashCode());
		result = prime * result + ((securedBy == null) ? 0 : securedBy.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RamlTestCase other = (RamlTestCase) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (mimeType == null) {
			if (other.mimeType != null)
				return false;
		} else if (!mimeType.equals(other.mimeType))
			return false;
		if (protocol != other.protocol)
			return false;
		if (raml == null) {
			if (other.raml != null)
				return false;
		} else if (!raml.equals(other.raml))
			return false;
		if (ramlPath == null) {
			if (other.ramlPath != null)
				return false;
		} else if (!ramlPath.equals(other.ramlPath))
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		if (securedBy == null) {
			if (other.securedBy != null)
				return false;
		} else if (!securedBy.equals(other.securedBy))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		return true;
	}

}
