package com.github.claasahl.raml.visitor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

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
	}
	
	@Test
	public void shouldVisitAttributes() {
		Map<String, MimeType> body = new HashMap<>();
		String type = "application/json";
		MimeType mimeType = new MimeType(type);
		body.put(type, mimeType);
		this.response.setBody(body);
		
		Map<String, Header> headers = new HashMap<>();
		String headerName = "location";
		Header header = new Header();
		headers.put(headerName, header);
		this.response.setHeaders(headers);
		
		ResponseVisitor visitor = mock(ResponseVisitor.class);
		this.coordinator.visitResponse(this.response, visitor);
		
		verify(visitor).beforeVisit(this.response);
		verify(visitor).visitBody(type, mimeType);
		verify(visitor).visitHeader(headerName, header);
		verify(visitor).afterVisit(this.response);
	}
	
	@Test
	public void shouldNotVisitHeader() {
		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitHeaders(this.response, visitor);
		verify(visitor, never()).visitHeader(any(), any());
	}
	
	@Test
	public void shouldVisitHeader() {
		Map<String, Header> headers = new HashMap<>();
		String headerName = "location";
		Header header = new Header();
		headers.put(headerName, header);
		String headerName2 = "location2";
		Header header2 = new Header();
		headers.put(headerName2, header2);
		this.response.setHeaders(headers);
		
		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitHeaders(this.response, visitor);
		verify(visitor).visitHeader(headerName, header);
		verify(visitor).visitHeader(headerName2, header2);
	}
	
	@Test
	public void shouldNotVisitBody() {
		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitHeaders(this.response, visitor);
		verify(visitor, never()).visitBody(any(), any());
	}
	
	@Test
	public void shouldVisitBody() {
		Map<String, MimeType> body = new HashMap<>();
		String type = "application/json";
		MimeType mimeType = new MimeType(type);
		body.put(type, mimeType);
		String type2 = "application/xml";
		MimeType mimeType2 = new MimeType(type);
		body.put(type2, mimeType2);
		this.response.setBody(body);
		
		ResponseVisitor visitor = mock(ResponseVisitor.class);
		ResponseCoordinator.visitBodies(this.response, visitor);
		verify(visitor).visitBody(type, mimeType);
		verify(visitor).visitBody(type2, mimeType2);
	}

}
