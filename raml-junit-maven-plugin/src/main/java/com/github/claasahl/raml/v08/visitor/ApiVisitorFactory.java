package com.github.claasahl.raml.v08.visitor;

public interface ApiVisitorFactory {

	ApiVisitor createApiVisitor();

	MethodVisitor createMethodVisitor();

	ResourceVisitor createResourceVisitor();

	ResponseVisitor createResponseVisitor();

}
