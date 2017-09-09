package com.github.claasahl.raml.junit.api;

import java.net.URL;

import javax.annotation.Nonnull;

/**
 * The class {@link TestCaseKey}.
 * <p/>
 * This class identifies individual test cases for resources within a RAML
 * specification. As such, a test case is defined as the combination of RAML
 * specification (i.e. reference to the RAML document), request verb (i.e. HTTP
 * verb of the HTTP request), request URL (i.e. URL of the HTTP request),
 * response code (i.e. status code of HTTP response) and content type (i.e.
 * content / media type of HTTP request and HTTP response).
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
	// TODO does RAML enforce the same content type for requests and responses?

	private final URL ramlUrl;
	private final String requestVerb;
	private final String requestUrl;
	private final String responseCode;
	private final String contentType;

	/**
	 * Creates a test case.
	 * 
	 * @param ramlUrl
	 *            the test case's URL to the RAML document
	 * @param requestVerb
	 *            the test case's request verb
	 * @param requestUrl
	 *            the test case's request URL
	 * @param responseCode
	 *            the test case's response code
	 * @param contentType
	 *            the test case's content type
	 */
	public TestCaseKey(URL ramlUrl, String requestVerb, String requestUrl, String responseCode, String contentType) {
		this.ramlUrl = ramlUrl;
		this.requestVerb = requestVerb;
		this.requestUrl = requestUrl;
		this.responseCode = responseCode;
		this.contentType = contentType;
	}

	/**
	 * Returns the URL to the RAML document on which the test case is based.
	 * This information will be used to retrieve constraints for both the HTTP
	 * request and the HTTP response for this test case. Some implementations of
	 * {@link RequestFactory} may also use this information to generate a
	 * concrete HTTP request (e.g. by extracting example requests or default
	 * values).
	 * 
	 * @return the URL to the RAML document on which the test case is based
	 */
	@Nonnull
	public URL getRamlUrl() {
		return ramlUrl;
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
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * Returns the content type (i.e. content / media type of HTTP request and
	 * HTTP response) of the test case. If no content type is required, then
	 * <code>null</code> may be returned.
	 * 
	 * @return the content type of the test case
	 */
	public String getContentType() {
		return contentType;
	}

	@Override
	public String toString() {
		return "TestCaseKey [ramlUrl=" + ramlUrl + ", requestVerb=" + requestVerb + ", requestUrl=" + requestUrl
				+ ", responseCode=" + responseCode + ", contentType=" + contentType + "]";
	}
}
