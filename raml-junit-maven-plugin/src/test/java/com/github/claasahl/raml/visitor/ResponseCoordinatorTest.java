package com.github.claasahl.raml.visitor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.junit.Before;
import org.mockito.InOrder;
import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.parameter.Header;

public class ResponseCoordinatorTest extends CoordinatorTest {

	private ResponseCoordinator coordinator;
	private Response response;

	@Before
	public void before() {
		this.coordinator = new ResponseCoordinator();
		this.response = new Response();
	}

	@Override
	public void shouldCallBeforeVisitAndAfterVisit() {
		ResponseVisitor visitor = mock(ResponseVisitor.class);
		this.coordinator.visitResponse(this.response, visitor);

		InOrder inOrder = inOrder(visitor);
		inOrder.verify(visitor).beforeVisit(this.response);
		inOrder.verify(visitor).afterVisit(this.response);
		verify(visitor, never()).visitBody(any(), any());
		verify(visitor, never()).visitHeader(any(), any());
	}

	@Override
	public void shouldCallAllVisitMethods() {
		Map<String, MimeType> bodies = mimeTypes("json");
		this.response.setBody(bodies);
		Map<String, Header> headers = headers("a");
		this.response.setHeaders(headers);
		ResponseVisitor visitor = mock(ResponseVisitor.class);
		this.coordinator.visitResponse(this.response, visitor);

		verify(visitor).beforeVisit(this.response);
		verify(visitor).visitBody(any(), any());
		verify(visitor).visitHeader(any(), any());
		verify(visitor).afterVisit(this.response);
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

	protected static Collection<Object[]> data() {
		Supplier<Response> supplier = Response::new;
		return Arrays.asList(new Object[][] {
				{ supplier, headers("a", "b"), (BiConsumer<Response, Map<String, Header>>) Response::setHeaders,
						(BiConsumer<Response, ResponseVisitor>) ResponseCoordinator::visitHeaders,
						(TriConsumer<ResponseVisitor, String, Header>) ResponseVisitor::visitHeader,
						ResponseVisitor.class },
				{ supplier, mimeTypes("json", "xml"), (BiConsumer<Response, Map<String, MimeType>>) Response::setBody,
						(BiConsumer<Response, ResponseVisitor>) ResponseCoordinator::visitBodies,
						(TriConsumer<ResponseVisitor, String, MimeType>) ResponseVisitor::visitBody,
						ResponseVisitor.class } });
	}

}
