package com.github.claasahl.raml.visitor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.parameter.Header;

public class ResponseCoordinatorTest {

	private ResponseCoordinator coordinator;
	private Response response;

	@Before
	public void before() {
		this.coordinator = new ResponseCoordinator();
		this.response = new Response();
	}

	@Test
	public void shouldVisitBeforeAndAfter() {
		ResponseVisitor visitor = mock(ResponseVisitor.class);
		this.coordinator.visitResponse(this.response, visitor);

		InOrder inOrder = inOrder(visitor);
		inOrder.verify(visitor).beforeVisit(this.response);
		inOrder.verify(visitor).afterVisit(this.response);
		verify(visitor, never()).visitBody(any(), any());
		verify(visitor, never()).visitHeader(any(), any());
	}

	@Test
	public void shouldVisitAttributes() {
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

	@Test
	public void shouldNotVisitHeader() {
		this.response.setHeaders(null);

		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitHeaders(this.response, visitor);
		verify(visitor, never()).visitHeader(any(), any());
	}

	@Test
	public void shouldVisitNoHeader() {
		Map<String, Header> headers = headers();
		this.response.setHeaders(headers);

		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitHeaders(this.response, visitor);
		verify(visitor, never()).visitHeader(any(), any());
	}

	@Test
	public void shouldVisitAllHeaders() {
		Map<String, Header> headers = headers("a", "b");
		this.response.setHeaders(headers);

		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitHeaders(this.response, visitor);
		for (Entry<String, Header> entry : headers.entrySet()) {
			verify(visitor).visitHeader(entry.getKey(), entry.getValue());
		}
	}

	@Test
	public void shouldNotVisitBody() {
		this.response.setBody(null);

		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitHeaders(this.response, visitor);
		verify(visitor, never()).visitBody(any(), any());
	}

	@Test
	public void shouldVisitNoBody() {
		Map<String, MimeType> bodies = mimeTypes();
		this.response.setBody(bodies);

		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitHeaders(this.response, visitor);
		verify(visitor, never()).visitBody(any(), any());
	}

	@Test
	public void shouldVisitAllBodies() {
		Map<String, MimeType> bodies = mimeTypes("json", "xml");
		this.response.setBody(bodies);

		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitBodies(this.response, visitor);
		for (Entry<String, MimeType> entry : bodies.entrySet()) {
			verify(visitor).visitBody(entry.getKey(), entry.getValue());
		}
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

}
