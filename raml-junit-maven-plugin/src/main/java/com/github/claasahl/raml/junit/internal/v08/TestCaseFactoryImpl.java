package com.github.claasahl.raml.junit.internal.v08;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.raml.v2.api.RamlModelBuilder;
import org.raml.v2.api.RamlModelResult;
import org.raml.v2.api.model.common.ValidationResult;
import org.raml.v2.api.model.v08.bodies.Response;
import org.raml.v2.api.model.v08.methods.Method;

import com.github.claasahl.raml.junit.api.TestCaseFactory;
import com.github.claasahl.raml.junit.api.TestCaseKey;

/**
 * The class {@link TestCaseFactoryImpl}.
 * <p/>
 * This is an implementation of the interface {@link TestCaseFactory} for RAML
 * (v0.8) specifications.
 * 
 * @author Claas Ahlrichs
 *
 */
public class TestCaseFactoryImpl implements TestCaseFactory {

	@Override
	public List<TestCaseKey> createTestCases(URL ramlUrl) {
		try (Reader reader = new InputStreamReader(ramlUrl.openStream())) {
			RamlModelResult ramlModelResult = new RamlModelBuilder().buildApi(reader, ramlUrl.toExternalForm());
			if (ramlModelResult.hasErrors()) {
				for (ValidationResult validationResult : ramlModelResult.getValidationResults()) {
					// TODO write error message to logger
					System.out.println(validationResult);
				}
			} else if (ramlModelResult.isVersion08()) {
				return Stream.of(ramlModelResult.getApiV08()).flatMap(api -> api.resources().stream())
						.flatMap(resource -> Stream.concat(Stream.of(resource), resource.resources().stream()))// <-
																												// not
																												// recursive
						.flatMap(resource -> resource.methods().stream())
						.flatMap(
								method -> method.responses().stream().flatMap(response -> t(ramlUrl, method, response)))
						.collect(Collectors.toList());
			} else if (ramlModelResult.isVersion10()) {
				// TODO write error message to logger
				System.out.println(ramlModelResult.getApiV10());
			} else {
				// TODO write error message to logger
			}
		} catch (IOException e) {
			// TODO write error message to logger
		}
		return new ArrayList<>();
	}

	private static Stream<TestCaseKey> t(URL ramlPath, Method method, Response response) {
		if (method.body().isEmpty()) {
			return Stream.of(new TestCaseKey(ramlPath, method.method(), method.resource().resourcePath(),
					response.code().value(), null));
		} else {
			return method.body().stream().map(requestBody -> new TestCaseKey(ramlPath, method.method(),
					method.resource().resourcePath(), response.code().value(), requestBody.name()));
		}
	}

}
