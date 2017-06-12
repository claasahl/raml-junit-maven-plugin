package com.github.claasahl.raml.junit;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.junit.Test;
import org.raml.v2.api.model.v08.bodies.Response;

import com.github.claasahl.raml.v08.junit.ApiTestCase;

import io.restassured.http.ContentType;

public class BTest {

	@Test
	public void lotto_resource_returns_200_with_expected_id_and_winners() {
		// when().get("http://www.google.se?q={id}",
		// 5).then().assertThat().statusCode(200).body(not(isEmptyOrNullString()));
		// when().get("http://www.google.se?q={id}",
		// 5).then().assertThat().body(matchesJsonSchema(null));
	}

	@Test
	public void bla() {
		ApiTestCase tc = new ApiTestCase("get", "http://www.google.se", ContentType.HTML.getAcceptHeader(), null);
		given().queryParams(tc.getQueryParameters()).formParams(tc.getFormParameters())
				.pathParams(tc.getPathParameters()).cookies(tc.getCookies()).headers(tc.getHeaders())
				.contentType(tc.getContentType()).body(tc.getBody()).when().request(tc.getMethod(), tc.getPath());
	}
}
