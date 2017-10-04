package com.github.claasahl.raml.junit.internal.v08;

import static com.github.claasahl.raml.junit.internal.matchers.Matchers.hasContent;
import static com.github.claasahl.raml.junit.internal.matchers.Matchers.hasContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.raml.v2.api.model.v08.bodies.BodyLike;

import com.github.claasahl.raml.junit.api.common.Body;
import com.github.claasahl.raml.junit.api.common.BodyConstraints;

/**
 * The class {@link BodyConstraintsFactoryImpl}.
 * 
 * This is a utility class which creates {@link BodyConstraints} for RAML (v0.8)
 * specifications.
 * 
 * @author Claas Ahlrichs
 *
 */
public final class BodyConstraintsFactoryImpl {
	public static BodyConstraints createConstraints(BodyLike body) {
		String contentType = body.name();
		List<Matcher<Body>> matchers = new ArrayList<>();
		matchers.add(notNullValue(Body.class));
		matchers.add(hasContentType(equalTo(contentType)));
		switch (contentType) {
		case "application/json":
			matchers.add(hasContent(matchesJsonSchema(body.schemaContent())));
			break;
		default:
			// TODO schema validation
			System.err.println(body.schemaContent());
			break;
		}
		return new BodyConstraints(contentType, matchers);
	}
}
