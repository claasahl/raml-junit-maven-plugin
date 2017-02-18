package com.github.claasahl.raml.junit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.raml.model.Action;
import org.raml.model.Protocol;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.Response;
import org.raml.model.parameter.Header;
import org.raml.parser.loader.FileResourceLoader;
import org.raml.parser.loader.ResourceLoader;
import org.raml.parser.rule.ValidationResult;
import org.raml.parser.visitor.RamlDocumentBuilder;
import org.raml.parser.visitor.RamlValidationService;

public class LoadRaml {

	public static void main(String[] args) throws FileNotFoundException {
		String RAML_DIR = "src/main/raml/v1";
		String RAML_SPEC = "api.raml";
		FileInputStream inputStream = new FileInputStream(RAML_DIR + File.separator + RAML_SPEC);
		ResourceLoader loader = new FileResourceLoader(RAML_DIR);
		List<ValidationResult> results = RamlValidationService.createDefault(loader).validate(inputStream, "");
		ValidationResult.areValid(results);

		Raml raml = new RamlDocumentBuilder(loader).build(RAML_SPEC);
		System.out.println(raml);
		bb(raml.getResources().values());
		
		Path ramlPath = Paths.get("src/main/raml", "v1/api.raml");
		List<RamlTestCase> testCases = generateTestCases(raml, ramlPath);
		System.out.println(testCases);
	}
	
	private static List<RamlTestCase> generateTestCases(Raml raml, Path ramlPath) {
		List<RamlTestCase> testCases = new ArrayList<>();
		for(Resource resource : raml.getResources().values()) {
			// generate test case(s) for current resource
			RamlTestCaseBuilder builder = new RamlTestCaseBuilder();
			builder.setRaml(raml, ramlPath).setResource(resource);

			testCases.add(builder.build());
			
			// generate test case(s) for sub-resources
			// ...
		}
		return testCases;
	}
	
	private static void generateTestCasesForResource(List<RamlTestCase> testCases, RamlTestCaseBuilder builder) {
		
	}
	
	private static void bb(Collection<Resource> resources) {
		for (Resource resource : resources) {
			System.out.println("\n//" + resource.getDisplayName());
			System.out.println("//" + resource.getDescription());
			System.out.println("Class name: " + resource.getUri() + "Test");
			actions(resource.getActions().values());
			bb(resource.getResources().values());
		}
	}

	private static void actions(Collection<Action> actions) {
		for (Action action : actions) {
			if (action.getProtocols().isEmpty()) {
				System.out.println("//" + action.getDisplayName());
				System.out.println("//" + action.getDescription());
				System.out.println("//" + action.getType());
				System.out.println("// no protocol");
				System.out.println("Test: " + action.getType());
				responses(action.getResponses());
			} else {
				for (Protocol protocol : action.getProtocols()) {
					System.out.println("//" + action.getDisplayName());
					System.out.println("//" + action.getDescription());
					System.out.println("//" + action.getType());
					System.out.println("//" + protocol);
					System.out.println("Test: " + action.getType());
					responses(action.getResponses());
				}
			}
		}
	}

	private static void responses(Map<String, Response> map) {
		for(String statusCode : map.keySet()) {
			Response response = map.get(statusCode);
			System.out.println("// " + statusCode);
			System.out.println("// descr:" + response.getDescription());
			headers(response.getHeaders().values());
		}

	}

	private static void headers(Collection<Header> headers) {
		for (Header header : headers) {
			System.out.println("// header:" + header);
		}
	}

}
