package com.github.claasahl.raml.junit;

import java.util.function.Supplier;

import com.github.claasahl.raml.visitor.ActionVisitor;
import com.github.claasahl.raml.visitor.RamlVisitor;
import com.github.claasahl.raml.visitor.RamlVisitorFactory;
import com.github.claasahl.raml.visitor.ResourceVisitor;
import com.github.claasahl.raml.visitor.ResponseVisitor;

public class RamlTestCasesVisitorFactory implements RamlVisitorFactory {
	
	private final Supplier<RamlTestCaseBuilder> builderSupplier;
	
	public RamlTestCasesVisitorFactory() {
		this.builderSupplier = RamlTestCaseBuilder::new;
	}
	
	private RamlTestCasesVisitorFactory(RamlTestCaseBuilder builder) {
		this.builderSupplier = () -> new RamlTestCaseBuilder(builder.build());
	}
	
	@Override
	public ActionVisitor createActionVisitor() {
		RamlTestCaseBuilder builder = builderSupplier.get();
		RamlVisitorFactory factory = new RamlTestCasesVisitorFactory(builder);
		return new RamlTestCasesActionVisitor(builder, factory);
	}

	@Override
	public RamlVisitor createRamlVisitor() {
		RamlTestCaseBuilder builder = builderSupplier.get();
		RamlVisitorFactory factory = new RamlTestCasesVisitorFactory(builder);
		return new RamlTestCasesVisitor(builder, factory);
	}

	@Override
	public ResourceVisitor createResourceVisitor() {
		RamlTestCaseBuilder builder = builderSupplier.get();
		RamlVisitorFactory factory = new RamlTestCasesVisitorFactory(builder);
		return new RamlTestCasesResourceVisitor(builder, factory);
	}
	
	@Override
	public ResponseVisitor createResponseVisitor() {
		RamlTestCaseBuilder builder = builderSupplier.get();
		return new RamlTestCasesResponseVisitor(builder);
	}

}
