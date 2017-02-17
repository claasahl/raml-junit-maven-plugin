package com.github.claasahl.raml.visitor.debug;

import java.util.List;

import org.raml.model.Action;
import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.Header;
import org.raml.model.parameter.QueryParameter;
import org.raml.model.parameter.UriParameter;

import com.github.claasahl.raml.visitor.ActionVisitorBase;

public class DebugActionVisitor extends ActionVisitorBase {

	@Override
	public void visitAction(Action action) {
		// TODO Auto-generated method stub
		super.visitAction(action);
	}

	@Override
	public void visitBaseUriParameter(String key, List<UriParameter> uriParameters) {
		// TODO Auto-generated method stub
		super.visitBaseUriParameter(key, uriParameters);
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

	@Override
	public void visitQueryParameter(String key, QueryParameter queryParameter) {
		// TODO Auto-generated method stub
		super.visitQueryParameter(key, queryParameter);
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		// TODO Auto-generated method stub
		super.visitSecurityReference(securityReference);
	}

	@Override
	public void visitResponse(String key, Response response) {
		// TODO Auto-generated method stub
		super.visitResponse(key, response);
	}

}
