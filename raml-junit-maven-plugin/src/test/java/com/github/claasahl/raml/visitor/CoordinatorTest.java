package com.github.claasahl.raml.visitor;

import org.junit.Test;

public abstract class CoordinatorTest{

	@Test
	public abstract void shouldCallBeforeVisitThenAfterVisit();

	@Test
	public abstract void shouldCallAllVisitMethods();

}
