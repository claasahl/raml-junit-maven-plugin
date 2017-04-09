package com.github.claasahl.raml.junit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.raml.model.Raml;
import org.raml.parser.loader.FileResourceLoader;
import org.raml.parser.loader.ResourceLoader;
import org.raml.parser.rule.ValidationResult;
import org.raml.parser.visitor.RamlDocumentBuilder;
import org.raml.parser.visitor.RamlValidationService;

import com.github.claasahl.raml.visitor.RamlCoordinatorFactory;
import com.github.claasahl.raml.visitor.RamlVisitor;

public class LoadRaml {

	public static void main(String[] args) throws FileNotFoundException {
		String RAML_DIR = "src/main/raml/v1";
		String RAML_SPEC = "api.raml";
		FileInputStream inputStream = new FileInputStream(RAML_DIR + File.separator + RAML_SPEC);
		ResourceLoader loader = new FileResourceLoader(RAML_DIR);
		List<ValidationResult> results = RamlValidationService.createDefault(loader).validate(inputStream, "");
		ValidationResult.areValid(results);

		RamlCoordinatorFactory coordinatorFactory = new RamlCoordinatorFactory();
		Path baseDir = Paths.get("src/main/raml");
		Stream<Path> stream = findRamlSpecifications(baseDir);
		stream.map(baseDir::relativize).flatMap((Path ramlSpec) -> {
			Raml raml = loadRamlSpecification(baseDir, ramlSpec);
			List<RamlTestCase> testCases = new ArrayList<>();
			if (raml != null) {
				RamlTestCasesVisitorFactory factory = new RamlTestCasesVisitorFactory(testCases::add, coordinatorFactory);
				RamlVisitor visitor = factory.createRamlVisitor();
				coordinatorFactory.visitRaml(raml, ramlSpec, visitor);
			}
			return testCases.stream();
		}).forEach(System.out::println);
	}

	private static Stream<Path> findRamlSpecifications(Path baseDir) {
		try {
			return Files.find(baseDir, Integer.MAX_VALUE, LoadRaml::isRamlSpecification);
		} catch (IOException e) {
			e.printStackTrace();
			return Stream.empty();
		}
	}

	private static boolean isRamlSpecification(Path path, BasicFileAttributes attributes) {
		return path.getFileName().toFile().getName().endsWith(".raml");
	}

	private static Raml loadRamlSpecification(Path baseDir, Path ramlSpec) {
		try {
			Path resolvedPath = baseDir.resolve(ramlSpec);
			FileInputStream inputStream = new FileInputStream(resolvedPath.toFile());
			ResourceLoader loader = new FileResourceLoader(resolvedPath.getParent().toFile());
			List<ValidationResult> results = RamlValidationService.createDefault(loader).validate(inputStream, "");
			if (ValidationResult.areValid(results)) {
				return new RamlDocumentBuilder(loader).build(resolvedPath.getFileName().toString());
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
