package com.github.claasahl.raml.junit.api;

import javax.annotation.Nonnull;

import com.github.claasahl.raml.junit.api.factories.ConstraintsFactory;
import com.github.claasahl.raml.junit.api.factories.RequestFactory;

/**
 * The class {@link TestCaseKey}.
 * <p/>
 * This class identifies individual test cases for resources within a RAML
 * specification. As such, a test case is defined as the combination of RAML
 * specification (i.e. reference to the RAML document), request verb (i.e. HTTP
 * verb of the HTTP request), request URL (i.e. URL of the HTTP request) and
 * response code (i.e. status code of HTTP response).
 * <p/>
 * The factories {@link RequestFactory} and {@link ConstraintsFactory} use this
 * class to generate HTTP requests (i.e. by looking up concrete values for
 * headers, parameters and body) and to identify the constraints on HTTP
 * requests / responses as specified in the RAML document.
 * 
 * @author Claas Ahlrichs
 *
 */
public final class TestCaseKey {
	// TODO security settings should also be included here

	private final String ramlPath;
	private final String requestVerb;
	private final String requestUrl;
	private final int responseCode;

	/**
	 * Creates a test case.
	 * 
	 * @param ramlPath
	 *            the test case's path to the RAML document
	 * @param requestVerb
	 *            the test case's request verb
	 * @param requestUrl
	 *            the test case's request URL
	 * @param responseCode
	 *            the test case's response code
	 */
	public TestCaseKey(String ramlPath, String requestVerb, String requestUrl, int responseCode) {
		this.ramlPath = ramlPath;
		this.requestVerb = requestVerb;
		this.requestUrl = requestUrl;
		this.responseCode = responseCode;
	}

	/**
	 * Returns the path to the RAML document on which the test case is based.
	 * This information will be used to retrieve constraints for both the HTTP
	 * request and the HTTP response for this test case. Some implementations of
	 * {@link RequestFactory} may also use this information to generate a
	 * concrete HTTP request (e.g. by extracting example requests or default
	 * values).
	 * 
	 * @return the path to the RAML document on which the test case is based
	 */
	@Nonnull
	public String getRamlPath() {
		return ramlPath;
	}

	/**
	 * Returns the (fully-qualified) request URL (i.e. URL of HTTP request) of
	 * the test case.
	 * 
	 * @return the request URL of the test case
	 */
	@Nonnull
	public String getRequestUrl() {
		return requestUrl;
	}

	/**
	 * Returns the request verb (i.e. verb of HTTP request) of the test case.
	 * This may be any valid HTTP verb, but most commonly one of <i>get</i>,
	 * <i>post</i>, <i>put</i> or <i>delete</i>.
	 * 
	 * @return the request verb of the test case
	 */
	@Nonnull
	public String getRequestVerb() {
		return requestVerb;
	}

	/**
	 * Returns the response code (i.e. HTTP status code) of the test case.
	 * 
	 * @return the response code of the test case
	 */
	@Nonnull
	public int getResponseCode() {
		return responseCode;
	}
}
