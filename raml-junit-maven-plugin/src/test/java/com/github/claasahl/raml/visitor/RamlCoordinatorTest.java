package com.github.claasahl.raml.visitor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
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
	public void shouldCallBeforeVisitAndAfterVisit() {
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
		Map<String, UriParameter> baseUriParameters = uriParameters("a");
		this.raml.setBaseUriParameters(baseUriParameters);
		List<DocumentationItem> documentationItems = documentationItems("b");
		this.raml.setDocumentation(documentationItems);
		Map<String, Resource> resources = resources("/some/resource/path");
		this.raml.setResources(resources);
		List<SecurityReference> securedBy = securityReference("c");
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

	private static Map<String, Resource> resources(String... resourcePaths) {
		Map<String, Resource> resources = new HashMap<>();
		for (String resourcePath : resourcePaths) {
			resources.put(resourcePath, new Resource());
		}
		return resources;
	}

	private static Map<String, UriParameter> uriParameters(String... names) {
		Map<String, UriParameter> parameters = new HashMap<>();
		for (String name : names) {
			parameters.put(name, uriParameter(name));
		}
		return parameters;
	}

	private static UriParameter uriParameter(String name) {
		return new UriParameter(name);
	}

	private static List<SecurityReference> securityReference(String... names) {
		List<SecurityReference> references = new ArrayList<>();
		for (String name : names) {
			references.add(new SecurityReference(name));
		}
		return references;
	}

	private static List<DocumentationItem> documentationItems(String... names) {
		List<DocumentationItem> items = new ArrayList<>();
		for (String name : names) {
			DocumentationItem item = new DocumentationItem();
			item.setTitle(name);
			items.add(item);	
		}
		return items;
	}

	protected static Collection<Object[]> data() {
		// List<DocumentationItem> documentationItems= documentationItems("b");
		// this.raml.setDocumentation(documentationItems);
		// List<SecurityReference> securedBy = securityReference("c");
		// this.raml.setSecuredBy(securedBy);

		Supplier<Raml> supplier = Raml::new;
		return Arrays.asList(new Object[][] {
				{ supplier, uriParameters("a"),
						(BiConsumer<Raml, Map<String, UriParameter>>) Raml::setBaseUriParameters,
						(BiConsumer<Raml, RamlVisitor>) RamlCoordinator::visitBaseUriParameters,
						(TriConsumer<RamlVisitor, String, UriParameter>) RamlVisitor::visitBaseUriParameter,
						RamlVisitor.class },
				{ supplier, resources("/some/resource/path"),
						(BiConsumer<Raml, Map<String, Resource>>) Raml::setResources,
						(BiConsumer<Raml, RamlVisitor>) RamlCoordinator::visitResources,
						(TriConsumer<RamlVisitor, String, Resource>) RamlVisitor::visitResource,
						RamlVisitor.class }, });
	}

}
