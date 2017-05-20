package com.github.claasahl.raml.visitor;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class VisitMapAttributeTest<R, V, K, W> {

	private final Supplier<R> resourceSupplier;
	private final Map<K, W> values;
	private final BiConsumer<R, Map<K, W>> attributeSetter;
	private final BiConsumer<R, V> visitFunction;
	private final TriConsumer<V, K, W> visitedFunction;
	private final Class<V> visitorClass;

	public VisitMapAttributeTest(Supplier<R> resourceSupplier, Map<K, W> values,
			BiConsumer<R, Map<K, W>> attributeSetter, BiConsumer<R, V> visitFunction,
			TriConsumer<V, K, W> visitedFunction, Class<V> visitorClass) {
		this.resourceSupplier = resourceSupplier;
		this.values = values;
		this.attributeSetter = attributeSetter;
		this.visitFunction = visitFunction;
		this.visitedFunction = visitedFunction;
		this.visitorClass = visitorClass;
	}

	@Test
	public void shouldNotVisit() throws Exception {
		R resource = resourceSupplier.get();
		this.attributeSetter.accept(resource, null);

		V visitor = mock(visitorClass);
		this.visitFunction.accept(resource, visitor);
		this.visitedFunction.apply(verify(visitor, never()), any(), any());
	}

	@Test
	public void shouldVisitZeroValues() throws Exception {
		R resource = resourceSupplier.get();
		this.attributeSetter.accept(resource, new HashMap<>());

		V visitor = mock(visitorClass);
		this.visitFunction.accept(resource, visitor);
		this.visitedFunction.apply(verify(visitor, never()), any(), any());
	}

	@Test
	public void shouldVisitAllValues() throws Exception {
		R resource = resourceSupplier.get();
		this.attributeSetter.accept(resource, this.values);
		assertFalse("Must contain values (i.e. may not be empty)", this.values.isEmpty());

		V visitor = mock(visitorClass);
		this.visitFunction.accept(resource, visitor);
		for (Entry<K, W> entry : this.values.entrySet()) {
			this.visitedFunction.apply(verify(visitor), entry.getKey(), entry.getValue());
		}
	}

	@Parameters
	public static Collection<Object[]> data() {
		Collection<Object[]> data = new ArrayList<>();
		data.addAll(ResourceCoordinatorTest.data());
		data.addAll(ActionCoordinatorTest.data());
		data.addAll(RamlCoordinatorTest.data());
		return data;
	}

}
