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
import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.Header;
import org.raml.model.parameter.QueryParameter;
import org.raml.model.parameter.UriParameter;

public class ActionCoordinatorTest extends CoordinatorTest {

	private ActionCoordinator coordinator;
	private Action action;

	@Before
	public void before() {
		this.coordinator = new ActionCoordinator();
		this.action = new Action();
	}

	@Override
	public void shouldCallBeforeVisitAndAfterVisit() {
		ActionVisitor visitor = mock(ActionVisitor.class);
		this.coordinator.visitAction(this.action, visitor);

		InOrder inOrder = inOrder(visitor);
		inOrder.verify(visitor).beforeVisit(this.action);
		inOrder.verify(visitor).afterVisit(this.action);
		verify(visitor, never()).visitBaseUriParameter(any(), any());
		verify(visitor, never()).visitBody(any(), any());
		verify(visitor, never()).visitHeader(any(), any());
		verify(visitor, never()).visitQueryParameter(any(), any());
		verify(visitor, never()).visitResponse(any(), any());
		verify(visitor, never()).visitSecurityReference(any());
	}

	@Override
	public void shouldCallAllVisitMethods() {
		Map<String, List<UriParameter>> baseUriParameters = baseUriParameters("a");
		this.action.setBaseUriParameters(baseUriParameters);
		Map<String, MimeType> bodies = mimeTypes("json");
		this.action.setBody(bodies);
		Map<String, Header> headers = headers("b");
		this.action.setHeaders(headers);
		Map<String, QueryParameter> queryParameters = queryParameters("c");
		this.action.setQueryParameters(queryParameters);
		Map<String, Response> responses = responses("d");
		this.action.setResponses(responses);
		List<SecurityReference> securedBy = securityReference("e");
		this.action.setSecuredBy(securedBy);
		ActionVisitor visitor = mock(ActionVisitor.class);
		this.coordinator.visitAction(this.action, visitor);

		verify(visitor).beforeVisit(this.action);
		verify(visitor).visitBaseUriParameter(any(), any());
		verify(visitor).visitBody(any(), any());
		verify(visitor).visitHeader(any(), any());
		verify(visitor).visitQueryParameter(any(), any());
		verify(visitor).visitResponse(any(), any());
		verify(visitor).visitSecurityReference(any());
		verify(visitor).afterVisit(this.action);
	}

	private static Map<String, Header> headers(String... fieldNames) {
		Map<String, Header> headers = new HashMap<>();
		for (String fieldName : fieldNames) {
			headers.put(fieldName, new Header());
		}
		return headers;
	}

	private static Map<String, MimeType> mimeTypes(String... types) {
		Map<String, MimeType> mimeTypes = new HashMap<>();
		for (String type : types) {
			mimeTypes.put(type, new MimeType(type));
		}
		return mimeTypes;
	}

	private static Map<String, List<UriParameter>> baseUriParameters(String... names) {
		Map<String, List<UriParameter>> parameters = new HashMap<>();
		for (String name : names) {
			parameters.put(name, Arrays.asList(uriParameter(name)));
		}
		return parameters;
	}

	private static Map<String, QueryParameter> queryParameters(String... names) {
		Map<String, QueryParameter> parameters = new HashMap<>();
		for (String name : names) {
			parameters.put(name, new QueryParameter());
		}
		return parameters;
	}

	private static Map<String, Response> responses(String... names) {
		Map<String, Response> responses = new HashMap<>();
		for (String name : names) {
			responses.put(name, new Response());
		}
		return responses;
	}

	private static List<SecurityReference> securityReference(String... names) {
		List<SecurityReference> references = new ArrayList<>();
		for (String name : names) {
			references.add(new SecurityReference(name));
		}
		return references;
	}

	private static UriParameter uriParameter(String name) {
		return new UriParameter(name);
	}

	protected static Collection<Object[]> data() {
		// List<SecurityReference> securedBy = securityReference("e");
		// this.action.setSecuredBy(securedBy);

		Supplier<Action> supplier = Action::new;
		return Arrays.asList(new Object[][] {
				{ supplier, baseUriParameters("a"),
						(BiConsumer<Action, Map<String, List<UriParameter>>>) Action::setBaseUriParameters,
						(BiConsumer<Action, ActionVisitor>) ActionCoordinator::visitBaseUriParameters,
						(TriConsumer<ActionVisitor, String, List<UriParameter>>) ActionVisitor::visitBaseUriParameter,
						ActionVisitor.class },
				{ supplier, mimeTypes("json"), (BiConsumer<Action, Map<String, MimeType>>) Action::setBody,
						(BiConsumer<Action, ActionVisitor>) ActionCoordinator::visitBodies,
						(TriConsumer<ActionVisitor, String, MimeType>) ActionVisitor::visitBody, ActionVisitor.class },
				{ supplier, headers("b"), (BiConsumer<Action, Map<String, Header>>) Action::setHeaders,
						(BiConsumer<Action, ActionVisitor>) ActionCoordinator::visitHeaders,
						(TriConsumer<ActionVisitor, String, Header>) ActionVisitor::visitHeader, ActionVisitor.class },
				{ supplier, queryParameters("c"),
						(BiConsumer<Action, Map<String, QueryParameter>>) Action::setQueryParameters,
						(BiConsumer<Action, ActionVisitor>) ActionCoordinator::visitQueryParameters,
						(TriConsumer<ActionVisitor, String, QueryParameter>) ActionVisitor::visitQueryParameter,
						ActionVisitor.class },
				{ supplier, responses("d"), (BiConsumer<Action, Map<String, Response>>) Action::setResponses,
						(BiConsumer<Action, ActionVisitor>) ActionCoordinator::visitResponses,
						(TriConsumer<ActionVisitor, String, Response>) ActionVisitor::visitResponse,
						ActionVisitor.class }, });
	}

}
