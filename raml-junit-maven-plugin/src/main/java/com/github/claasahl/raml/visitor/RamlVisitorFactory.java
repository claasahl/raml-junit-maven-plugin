package com.github.claasahl.raml.visitor;

public interface RamlVisitorFactory {

	ActionVisitor createActionVisitor();

	RamlVisitor createRamlVisitor();

	ResourceVisitor createResourceVisitor();

	ResponseVisitor createResponseVisitor();

}
