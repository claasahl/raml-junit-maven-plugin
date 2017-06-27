package com.github.claasahl.raml.v08.junit;

import java.io.File;
import java.util.stream.Stream;

import org.raml.v2.api.RamlModelBuilder;
import org.raml.v2.api.RamlModelResult;
import org.raml.v2.api.model.common.ValidationResult;
import org.raml.v2.api.model.v08.api.Api;
import org.raml.v2.api.model.v08.bodies.Response;
import org.raml.v2.api.model.v08.methods.Method;
import org.raml.v2.api.model.v08.resources.Resource;

public class Test {

	public static void main(String[] args) {
		String RAML_DIR = "src/main/raml/v08/licenses2";
		String RAML_SPEC = "api.raml";
		File ramlFile = new File(RAML_DIR + "/" + RAML_SPEC);
		RamlModelResult ramlModelResult = new RamlModelBuilder().buildApi(ramlFile);
		if (ramlModelResult.hasErrors()) {
			for (ValidationResult validationResult : ramlModelResult.getValidationResults()) {
				System.out.println(validationResult);
			}
		} else if (ramlModelResult.isVersion08()) {
			v08(ramlModelResult.getApiV08());
		} else if (ramlModelResult.isVersion10()) {
			System.out.println(ramlModelResult.getApiV10());
		} else {
			// ...
		}
	}

	private static void v08(Api api) {
		Stream.of(api).flatMap(Test::resources).flatMap(Test::resources).flatMap(Test::methods).flatMap(Test::consume)
				.forEach(System.out::println);
	}

	private static Stream<Resource> resources(Api api) {
		return api.resources().stream();
	}

	private static Stream<Resource> resources(Resource resource) {
		return Stream.concat(Stream.of(resource), resource.resources().stream());
	}

	private static Stream<Method> methods(Resource resource) {
		return resource.methods().stream();
	}

	private static Stream<ResourceRequest> consume(Method method) {
		return method.responses().stream().flatMap(response -> t(method, response));
	}

	private static Stream<ResourceRequest> t(Method method, Response response) {
		if (method.body().isEmpty()) {
			if (response.body().isEmpty()) {
				return Stream.of(new ResourceRequestV08(method, null, response, null));
			} else {
				return response.body().stream()
						.map(responseBody -> new ResourceRequestV08(method, null, response, responseBody));
			}
		} else {
			return method.body().stream().flatMap(requestBody -> {
				if (response.body().isEmpty()) {
					return Stream.of(new ResourceRequestV08(method, requestBody, response, null));
				} else {
					return response.body().stream()
							.filter(responseBody -> requestBody.name().equals(responseBody.name()))
							.map(responseBody -> new ResourceRequestV08(method, requestBody, response, responseBody));
				}
			});
		}
	}

}
