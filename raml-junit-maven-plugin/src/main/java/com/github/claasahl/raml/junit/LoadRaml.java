package com.github.claasahl.raml.junit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.raml.model.Raml;
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
		Path ramlPath = Paths.get("src/main/raml", "v1/api.raml");
		List<RamlTestCase> testCases = new ArrayList<>();
		RamlTestCasesVisitorFactory factory = new RamlTestCasesVisitorFactory(testCases::add);
		factory.createRamlVisitor().visitRaml(raml, ramlPath);
		System.out.println(testCases);
	}
	
}
