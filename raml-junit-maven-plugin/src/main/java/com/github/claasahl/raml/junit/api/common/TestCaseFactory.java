package com.github.claasahl.raml.junit.api.common;

import java.net.URL;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * The interface {@link TestCaseFactory}.
 * <p/>
 * This interface provides a customizable hook for generating test cases (i.e.
 * {@link TestCaseKey}s). Thus allowing to easily replace the default strategy
 * with a more specialized, suitable strategy, if the need arises.
 * <p/>
 * Implementations of this interface are meant to load a RAML specification and
 * extract a collection of test cases for the described API. As a rule of thumb,
 * implementations should produced a test case for every combination of HTTP
 * request verb, HTTP request URL, HTTP response status code and content type.
 * <p/>
 * For an overview of related classes, please refer to
 * {@link com.github.claasahl.raml.junit.api.common}.
 * <p/>
 * Implementing classes must provide a zero-argument constructor.
 * 
 * @author Claas Ahlrichs
 *
 */
public interface TestCaseFactory {
	// TODO implement sanity check that all returned test cases make use of the
	// ramlPath

	/**
	 * Returns test cases for the referenced RAML document. The returned test
	 * cases are expected to be based on the referenced RAML document only (i.e.
	 * {@link TestCaseKey#getRamlUrl()} must be equal to the specified RAML
	 * document).
	 * <p/>
	 * <b>Note:</b> The returned list may not contain <code>null</code> values.
	 * 
	 * @param ramlUrl
	 *            the URL to the RAML document
	 * @return test cases for the referenced RAML document
	 */
	@Nonnull
	List<TestCaseKey> createTestCases(URL ramlUrl);
}
