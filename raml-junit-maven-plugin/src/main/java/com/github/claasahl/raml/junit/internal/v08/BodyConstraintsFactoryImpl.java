package com.github.claasahl.raml.junit.internal.v08;

import static com.github.claasahl.raml.junit.internal.matchers.Matchers.hasContentType;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.raml.v2.api.model.v08.bodies.BodyLike;

import com.github.claasahl.raml.junit.api.model.Body;
import com.github.claasahl.raml.junit.api.model.BodyConstraints;

public final class BodyConstraintsFactoryImpl {
	public static BodyConstraints createConstraints(BodyLike body) {
		String contentType = body.name();
		List<Matcher<Body>> matchers = new ArrayList<>();
		matchers.add(notNullValue(Body.class));
		matchers.add(hasContentType(equalTo(contentType)));
		// TODO schema validation
		return new BodyConstraints(contentType, matchers);
	}
}
