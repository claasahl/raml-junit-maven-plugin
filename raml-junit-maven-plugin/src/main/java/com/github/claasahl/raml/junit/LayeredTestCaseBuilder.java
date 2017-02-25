package com.github.claasahl.raml.junit;

import java.util.Stack;

public class LayeredTestCaseBuilder {

	private final Stack<RamlTestCaseBuilder> builders;

	public LayeredTestCaseBuilder() {
		super();
		this.builders = new Stack<>();
	}

	public int getLayers() {
		return builders.size();
	}

	public RamlTestCaseBuilder addLayer() {
		RamlTestCaseBuilder builder = null;
		if (this.builders.isEmpty()) {
			builder = new RamlTestCaseBuilder();
		} else {
			RamlTestCase testCase = this.builders.peek().build();
			builder = new RamlTestCaseBuilder(testCase);
		}
		return this.builders.push(builder);
	}

	public RamlTestCaseBuilder removeLayer() {
		RamlTestCaseBuilder builder = null;
		if (!this.builders.isEmpty()) {
			builder = this.builders.pop();
		}
		return builder;
	}

	public RamlTestCaseBuilder getLayer() {
		RamlTestCaseBuilder builder = null;
		if (!this.builders.isEmpty()) {
			builder = this.builders.peek();
		}
		return builder;
	}

}
