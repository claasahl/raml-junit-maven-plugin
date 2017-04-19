package com.github.claasahl.raml.visitor;

public interface TriConsumer<S1, S2, S3> {
	void apply(S1 a, S2 b, S3 c);
}
