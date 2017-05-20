package com.github.claasahl.raml.visitor;

import static org.junit.Assert.assertFalse;
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
		Map<String, MimeType> bodies = AttributeSupplier.mimeTypes("json");
		this.response.setBody(bodies);
		Map<String, Header> headers = AttributeSupplier.headers("a");
		this.response.setHeaders(headers);
		ResponseVisitor visitor = mock(ResponseVisitor.class);
		this.coordinator.visitResponse(this.response, visitor);

		verify(visitor).beforeVisit(this.response);
		verify(visitor).visitBody(any(), any());
		verify(visitor).visitHeader(any(), any());
		verify(visitor).afterVisit(this.response);
	}

	@Test
	public void shouldHandleNullHeaders() {
		this.response.setHeaders(null);

		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitHeaders(this.response, visitor);
		verify(visitor, never()).visitHeader(any(), any());
	}

	@Test
	public void shouldNotVisitHeader() throws Exception {
		this.response.setHeaders(new HashMap<>());

		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitHeaders(this.response, visitor);
		verify(visitor, never()).visitHeader(any(), any());
	}

	@Test
	public void shouldVisitHeader() throws Exception {
		Map<String, Header> values = AttributeSupplier.headers("a", "b");
		assertFalse("Must contain values (i.e. may not be empty)", values.isEmpty());
		this.response.setHeaders(values);

		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitHeaders(this.response, visitor);
		for (Entry<String, Header> entry : values.entrySet()) {
			verify(visitor).visitHeader(entry.getKey(), entry.getValue());
		}
	}
	
	@Test
	public void shouldHandleNullBodies() {
		this.response.setBody(null);

		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitBodies(this.response, visitor);
		verify(visitor, never()).visitBody(any(), any());
	}

	@Test
	public void shouldNotVisitBody() throws Exception {
		this.response.setBody(new HashMap<>());

		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitBodies(this.response, visitor);
		verify(visitor, never()).visitBody(any(), any());
	}

	@Test
	public void shouldVisiBody() throws Exception {
		Map<String, MimeType> values = AttributeSupplier.mimeTypes("json", "xml");
		assertFalse("Must contain values (i.e. may not be empty)", values.isEmpty());
		this.response.setBody(values);

		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitBodies(this.response, visitor);
		for (Entry<String, MimeType> entry : values.entrySet()) {
			verify(visitor).visitBody(entry.getKey(), entry.getValue());
		}
	}

}
