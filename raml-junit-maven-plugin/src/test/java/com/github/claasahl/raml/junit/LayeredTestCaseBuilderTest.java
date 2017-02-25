package com.github.claasahl.raml.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LayeredTestCaseBuilderTest {

	private LayeredTestCaseBuilder builder;

	@Before
	public void before() {
		this.builder = new LayeredTestCaseBuilder();
	}
	
	@Test
	public void shouldHaveZeroLayers() {
		assertEquals(0, this.builder.getLayers());
	}
	
	@Test
	public void shouldNotRemoveLayer() {
		assertNull(this.builder.removeLayer());
	}
	
	@Test
	public void shouldNotGetLayer() {
		assertNull(this.builder.getLayer());
	}
	
	@Test
	public void shouldAddLayer() {
		assertNotNull(this.builder.addLayer());
		assertEquals(1, this.builder.getLayers());
	}

}
