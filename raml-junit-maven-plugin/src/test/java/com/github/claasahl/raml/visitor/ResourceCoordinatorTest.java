package com.github.claasahl.raml.visitor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

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
import org.raml.model.Action;
import org.raml.model.ActionType;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.UriParameter;

public class ResourceCoordinatorTest extends CoordinatorTest {

	private ResourceCoordinator coordinator;
	private Resource resource;

	@Before
	public void before() {
		this.coordinator = new ResourceCoordinator();
		this.resource = new Resource();
	}

	@Override
	public void shouldCallBeforeVisitAndAfterVisit() {
		ResourceVisitor visitor = mock(ResourceVisitor.class);
		this.coordinator.visitResource(this.resource, visitor);

		InOrder inOrder = inOrder(visitor);
		inOrder.verify(visitor).beforeVisit(this.resource);
		inOrder.verify(visitor).afterVisit(this.resource);
		verify(visitor, never()).visitAction(any(), any());
		verify(visitor, never()).visitBaseUriParameter(any(), any());
		verify(visitor, never()).visitResolvedUriParameter(any(), any());
		verify(visitor, never()).visitSecurityReference(any());
		verify(visitor, never()).visitSubResource(any());
		verify(visitor, never()).visitUriParameter(any(), any());
	}

	@Override
	public void shouldCallAllVisitMethods() {
		Map<ActionType, Action> actions = actions(ActionType.GET);
		this.resource.setActions(actions);
		Map<String, List<UriParameter>> baseUriParameters = baseUriParameters("a");
		this.resource.setBaseUriParameters(baseUriParameters);
		List<SecurityReference> securedBy = securityReference("b");
		this.resource.setSecuredBy(securedBy);
		Map<String, Resource> resources = resources("/some/resource/path");
		this.resource.setResources(resources);
		Map<String, UriParameter> uriParameters = uriParameters("c");
		this.resource.setUriParameters(uriParameters);
		ResourceVisitor visitor = mock(ResourceVisitor.class);
		this.coordinator.visitResource(this.resource, visitor);

		verify(visitor).beforeVisit(this.resource);
		verify(visitor).visitAction(any(), any());
		verify(visitor).visitBaseUriParameter(any(), any());
		verify(visitor).visitResolvedUriParameter(any(), any());
		verify(visitor).visitSecurityReference(any());
		verify(visitor).visitSubResource(any());
		verify(visitor).visitUriParameter(any(), any());
		verify(visitor).afterVisit(this.resource);
	}

	private static Map<ActionType, Action> actions(ActionType... types) {
		Map<ActionType, Action> actions = new HashMap<>();
		for (ActionType type : types) {
			actions.put(type, new Action());
		}
		return actions;
	}

	private static Map<String, Resource> resources(String... resourcePaths) {
		Map<String, Resource> resources = new HashMap<>();
		for (String resourcePath : resourcePaths) {
			resources.put(resourcePath, new Resource());
		}
		return resources;
	}

	private static Map<String, List<UriParameter>> baseUriParameters(String... names) {
		Map<String, List<UriParameter>> parameters = new HashMap<>();
		for (String name : names) {
			parameters.put(name, Arrays.asList(uriParameter(name)));
		}
		return parameters;
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

	protected static Collection<Object[]> data() {
		// List<SecurityReference> securedBy = securityReference("b");
		// this.resource.setSecuredBy(securedBy);
		// Map<String, Resource> resources = resources("/some/resource/path");
		// this.resource.setResources(resources);

		Supplier<Resource> supplier = Resource::new;
		return Arrays.asList(new Object[][] {
				{ supplier, actions(ActionType.GET),
						(BiConsumer<Resource, Map<ActionType, Action>>) Resource::setActions,
						(BiConsumer<Resource, ResourceVisitor>) ResourceCoordinator::visitActions,
						(TriConsumer<ResourceVisitor, ActionType, Action>) ResourceVisitor::visitAction,
						ResourceVisitor.class },
				{ supplier, baseUriParameters("a"),
						(BiConsumer<Resource, Map<String, List<UriParameter>>>) Resource::setBaseUriParameters,
						(BiConsumer<Resource, ResourceVisitor>) ResourceCoordinator::visitBaseUriParameters,
						(TriConsumer<ResourceVisitor, String, List<UriParameter>>) ResourceVisitor::visitBaseUriParameter,
						ResourceVisitor.class },
				{ supplier, uriParameters("c"),
						(BiConsumer<Resource, Map<String, UriParameter>>) Resource::setUriParameters,
						(BiConsumer<Resource, ResourceVisitor>) ResourceCoordinator::visitUriParameters,
						(TriConsumer<ResourceVisitor, String, UriParameter>) ResourceVisitor::visitUriParameter,
						ResourceVisitor.class },
				{ supplier, uriParameters("d"),
						(BiConsumer<Resource, Map<String, UriParameter>>) Resource::setUriParameters,
						(BiConsumer<Resource, ResourceVisitor>) ResourceCoordinator::visitResolvedUriParameters,
						(TriConsumer<ResourceVisitor, String, UriParameter>) ResourceVisitor::visitResolvedUriParameter,
						ResourceVisitor.class } });
	}

}
