package com.github.claasahl.raml.junit.internal;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.stream.Stream;

import org.raml.v2.api.RamlModelBuilder;
import org.raml.v2.api.RamlModelResult;
import org.raml.v2.api.model.common.ValidationResult;

public final class Utils {

	public static boolean isRamlUrl(URL ramlUrl) {
		RamlModelResult ramlModelResult = buildApi(ramlUrl);
		return ramlModelResult != null;
	}

	public static String getRamlVersion(URL ramlUrl) {
		RamlModelResult ramlModelResult = buildApi(ramlUrl);
		if (ramlModelResult == null) {
			// TODO translate error message
			throw new IllegalArgumentException("The RAML document could not be loaded or contains errors.");
		} else if (ramlModelResult.isVersion08()) {
			return ramlModelResult.getApiV08().ramlVersion();
		} else if (ramlModelResult.isVersion10()) {
			return ramlModelResult.getApiV10().ramlVersion();
		} else {
			// TODO translate error message
			throw new IllegalArgumentException(
					"Could not identify version of RAML document. It is neither V08 nor V10.");
		}
	}

	private static RamlModelResult buildApi(URL ramlUrl) {
		try (Reader reader = new InputStreamReader(ramlUrl.openStream())) {
			String ramlLocation = ramlUrl.toExternalForm();
			RamlModelResult ramlModelResult = new RamlModelBuilder().buildApi(reader, ramlLocation);
			if (ramlModelResult.hasErrors()) {
				for (ValidationResult validationResult : ramlModelResult.getValidationResults()) {
					// TODO write error message to logger
					System.out.println(validationResult);
				}
				return null;
			}
			return ramlModelResult;
		} catch (IOException e) {
			// TODO write error message to logger
			return null;
		}
	}

	public static Stream<TestCase> getTestCases() {
		return Suppliers.getSuppliers().getRamlUrls().stream().flatMap(u -> {
			String ramlVersion = Utils.getRamlVersion(u);
			return Factories.getFactories(ramlVersion).createTestCases(u).stream();
		}).map(TestCase::new);
	}

	public static org.raml.v2.api.model.v08.api.Api buildApiV08(URL ramlUrl) {
		RamlModelResult ramlModelResult = buildApi(ramlUrl);
		if (ramlModelResult != null) {
			return ramlModelResult.getApiV08();
		} else {
			return null;
		}
	}

	public static org.raml.v2.api.model.v10.api.Api buildApiV10(URL ramlUrl) {
		RamlModelResult ramlModelResult = buildApi(ramlUrl);
		if (ramlModelResult != null) {
			return ramlModelResult.getApiV10();
		} else {
			return null;
		}
	}

	protected static <T> T createFactory(String propertyKey) {
		String factoryClass = System.getProperty(propertyKey);
		if (factoryClass != null) {
			try {
				@SuppressWarnings("unchecked")
				Class<T> factory = (Class<T>) Class.forName(factoryClass);
				return factory.newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO write error message to logger
				return null;
			}
		} else {
			return null;
		}
	}
}
