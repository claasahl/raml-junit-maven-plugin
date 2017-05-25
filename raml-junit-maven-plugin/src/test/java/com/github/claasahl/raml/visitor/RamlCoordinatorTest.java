package com.github.claasahl.raml.visitor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.junit.Before;
import org.mockito.InOrder;
import org.raml.model.DocumentationItem;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.UriParameter;

public class RamlCoordinatorTest extends CoordinatorTest {

	private RamlCoordinator coordinator;
	private Raml raml;
	private Path ramlPath;

	@Before
	public void before() throws IOException {
		this.coordinator = new RamlCoordinator();
		this.raml = new Raml();
		this.ramlPath = File.createTempFile(getClass().getSimpleName(), null).toPath();
	}

	@Override
	public void shouldCallBeforeVisitThenAfterVisit() {
		RamlVisitor visitor = mock(RamlVisitor.class);
		this.coordinator.visitRaml(this.raml, this.ramlPath, visitor);

		InOrder inOrder = inOrder(visitor);
		inOrder.verify(visitor).beforeVisit(this.raml, this.ramlPath);
		inOrder.verify(visitor).afterVisit(this.raml, this.ramlPath);
		verify(visitor, never()).visitBaseUriParameter(any(), any());
		verify(visitor, never()).visitDocumentationItem(any());
		verify(visitor, never()).visitResource(any(), any());
		verify(visitor, never()).visitSecurityReference(any());
	}

	@Override
	public void shouldCallAllVisitMethods() {
		Map<String, UriParameter> baseUriParameters = AttributeSupplier.uriParameters("a");
		this.raml.setBaseUriParameters(baseUriParameters);
		List<DocumentationItem> documentationItems = AttributeSupplier.documentationItems("b");
		this.raml.setDocumentation(documentationItems);
		Map<String, Resource> resources = AttributeSupplier.resources("/some/resource/path");
		this.raml.setResources(resources);
		List<SecurityReference> securedBy = AttributeSupplier.securityReference("c");
		this.raml.setSecuredBy(securedBy);
		RamlVisitor visitor = mock(RamlVisitor.class);
		this.coordinator.visitRaml(this.raml, this.ramlPath, visitor);

		verify(visitor).beforeVisit(this.raml, this.ramlPath);
		verify(visitor).visitBaseUriParameter(any(), any());
		verify(visitor).visitDocumentationItem(any());
		verify(visitor).visitResource(any(), any());
		verify(visitor).visitSecurityReference(any());
		verify(visitor).afterVisit(this.raml, this.ramlPath);
	}	

	protected static Collection<Object[]> data() {
		// List<DocumentationItem> documentationItems= documentationItems("b");
		// this.raml.setDocumentation(documentationItems);
		// List<SecurityReference> securedBy = securityReference("c");
		// this.raml.setSecuredBy(securedBy);

		Supplier<Raml> supplier = Raml::new;
		return Arrays.asList(new Object[][] {
				{ supplier, AttributeSupplier.uriParameters("a"),
						(BiConsumer<Raml, Map<String, UriParameter>>) Raml::setBaseUriParameters,
						(BiConsumer<Raml, RamlVisitor>) RamlCoordinator::visitBaseUriParameters,
						(TriConsumer<RamlVisitor, String, UriParameter>) RamlVisitor::visitBaseUriParameter,
						RamlVisitor.class },
				{ supplier, AttributeSupplier.resources("/some/resource/path"),
						(BiConsumer<Raml, Map<String, Resource>>) Raml::setResources,
						(BiConsumer<Raml, RamlVisitor>) RamlCoordinator::visitResources,
						(TriConsumer<RamlVisitor, String, Resource>) RamlVisitor::visitResource,
						RamlVisitor.class }, });
	}

}
