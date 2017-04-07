package com.github.claasahl.raml.junit;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.github.claasahl.raml.visitor.ActionVisitor;
import com.github.claasahl.raml.visitor.RamlCoordinatorFactory;
import com.github.claasahl.raml.visitor.RamlVisitor;
import com.github.claasahl.raml.visitor.RamlVisitorFactory;
import com.github.claasahl.raml.visitor.ResourceVisitor;
import com.github.claasahl.raml.visitor.ResponseVisitor;

public class RamlTestCasesVisitorFactory implements RamlVisitorFactory {

	private final Consumer<RamlTestCase> callback;
	private final Supplier<RamlTestCaseBuilder> builderSupplier;
	private final RamlCoordinatorFactory coordinatorFactory;

	public RamlTestCasesVisitorFactory(Consumer<RamlTestCase> callback, RamlCoordinatorFactory coordinatorFactory) {
		this.callback = callback;
		this.builderSupplier = RamlTestCaseBuilder::new;
		this.coordinatorFactory = coordinatorFactory;
	}

	private RamlTestCasesVisitorFactory(Consumer<RamlTestCase> callback, RamlTestCaseBuilder builder,
			RamlCoordinatorFactory coordinatorFactory) {
		this.callback = callback;
		this.builderSupplier = () -> new RamlTestCaseBuilder(builder.build());
		this.coordinatorFactory = coordinatorFactory;
	}

	@Override
	public ActionVisitor createActionVisitor() {
		RamlTestCaseBuilder builder = builderSupplier.get();
		RamlVisitorFactory visitorFactory = new RamlTestCasesVisitorFactory(this.callback, builder,
				this.coordinatorFactory);
		return new RamlTestCasesActionVisitor(builder, visitorFactory, this.coordinatorFactory);
	}

	@Override
	public RamlVisitor createRamlVisitor() {
		RamlTestCaseBuilder builder = builderSupplier.get();
		RamlVisitorFactory visitorFactory = new RamlTestCasesVisitorFactory(this.callback, builder,
				this.coordinatorFactory);
		return new RamlTestCasesVisitor(builder, visitorFactory, this.coordinatorFactory);
	}

	@Override
	public ResourceVisitor createResourceVisitor() {
		RamlTestCaseBuilder builder = builderSupplier.get();
		RamlVisitorFactory visitorFactory = new RamlTestCasesVisitorFactory(this.callback, builder,
				this.coordinatorFactory);
		return new RamlTestCasesResourceVisitor(builder, visitorFactory, this.coordinatorFactory);
	}

	@Override
	public ResponseVisitor createResponseVisitor() {
		RamlTestCaseBuilder builder = builderSupplier.get();
		return new RamlTestCasesResponseVisitor(this.callback, builder);
	}

}
