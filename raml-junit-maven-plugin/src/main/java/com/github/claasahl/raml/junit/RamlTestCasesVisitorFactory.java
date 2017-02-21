package com.github.claasahl.raml.junit;

import com.github.claasahl.raml.visitor.ActionVisitor;
import com.github.claasahl.raml.visitor.RamlVisitor;
import com.github.claasahl.raml.visitor.RamlVisitorFactory;
import com.github.claasahl.raml.visitor.ResourceVisitor;
import com.github.claasahl.raml.visitor.ResponseVisitor;

public class RamlTestCasesVisitorFactory implements RamlVisitorFactory {
	
	private final RamlTestCaseBuilder builder = new RamlTestCaseBuilder();

	@Override
	public ActionVisitor createActionVisitor() {
		return new RamlTestCasesActionVisitor(builder, this);
	}

	@Override
	public RamlVisitor createRamlVisitor() {
		return new RamlTestCasesVisitor(builder, this);
	}

	@Override
	public ResourceVisitor createResourceVisitor() {
		return new RamlTestCasesResourceVisitor(builder, this);
	}

	@Override
	public ResponseVisitor createResponseVisitor() {
		return new RamlTestCasesResponseVisitor(this.builder);
	}

}
