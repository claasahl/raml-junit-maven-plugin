package com.github.claasahl.raml.v08.junit;

import java.io.File;
import java.util.stream.Stream;

import org.raml.v2.api.RamlModelBuilder;
import org.raml.v2.api.RamlModelResult;
import org.raml.v2.api.model.common.ValidationResult;
import org.raml.v2.api.model.v08.api.Api;
import org.raml.v2.api.model.v08.bodies.BodyLike;
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
		Stream.of(api).flatMap(Test::resources).flatMap(Test::resources).flatMap(Test::methods)
				.map(Test::consume);
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
	
	private static ApiTestCase consume(Method method) {
		System.out.format("%s - %s\n", method.method(), method.resource().resourcePath());
		// TODO request body
		// TODO request headers
		method.baseUriParameters().stream().forEach(System.out::println);
		method.resource().uriParameters().stream().map(p -> p.displayName()).forEach(System.out::println);
		method.protocols().stream().forEach(System.out::println);
		method.queryParameters().stream().forEach(System.out::println);
		method.responses().stream().map(r -> r.code().value() + "--" + r.description().value()).forEach(System.out::println);
		return null;
	}

}
