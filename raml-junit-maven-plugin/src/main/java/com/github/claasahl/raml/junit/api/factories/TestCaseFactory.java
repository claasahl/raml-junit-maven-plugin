package com.github.claasahl.raml.junit.api.factories;

import java.util.List;

import javax.annotation.Nonnull;

import com.github.claasahl.raml.junit.api.TestCaseKey;

/**
 * The interface {@link TestCaseFactory}.
 * <p/>
 * This interface provides a customizable hook for generating test cases (i.e.
 * {@link TestCaseKey}s). Thus allowing to easily replace the default strategy
 * with a more specialized, suitable strategy, if the need arises.
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
	 * {@link TestCaseKey#getRamlPath()} must be equal to the specified RAML
	 * document).
	 * <p/>
	 * <b>Note:</b> The returned list may not contain <code>null</code> values.
	 * 
	 * @param ramlPath
	 *            the path to the RAML document
	 * @return test cases for the referenced RAML document
	 */
	@Nonnull
	List<TestCaseKey> createTestCases(String ramlPath);
}
