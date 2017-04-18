package com.github.claasahl.raml.visitor;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Map;

import org.junit.Test;
import org.mockito.InOrder;
import org.raml.model.MimeType;
import org.raml.model.parameter.Header;

public abstract class CoordinatorTest{

	@Test
	public abstract void shouldCallBeforeVisitAndAfterVisit();

	@Test
	public abstract void shouldCallAllVisitMethods();

}
