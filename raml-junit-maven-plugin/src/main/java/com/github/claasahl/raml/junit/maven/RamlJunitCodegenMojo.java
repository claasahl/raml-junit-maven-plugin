package com.github.claasahl.raml.junit.maven;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMethod;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_TEST_SOURCES)
public class RamlJunitCodegenMojo extends AbstractMojo {

	/**
	 * Skip plug-in execution.
	 */
	@Parameter(property = "skip", defaultValue = "false")
	private boolean skip;

	/**
	 * Target directory for generated Java source files.
	 */
	@Parameter(property = "outputDirectory", defaultValue = "${project.build.directory}/generated-test-sources/raml-junit")
	private File outputDirectory;

	/**
	 * Directory location of the RAML file(s).
	 */
	@Parameter(property = "sourceDirectory", defaultValue = "${basedir}/src/main/raml")
	private File sourceDirectory;

	/**
	 * Base package name used for generated Java classes.
	 */
	@Parameter(property = "basePackageName", required = true)
	private String basePackageName;

	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			JCodeModel cm = new JCodeModel();
			JDefinedClass dc = cm._class("foo.Bar");
			JMethod m = dc.method(0, int.class, "foo");
			m.body()._return(JExpr.lit(5));

			outputDirectory.mkdirs();
			cm.build(outputDirectory);
		} catch (Exception e) {
			throw new MojoExecutionException("Failed to generate Java source code.", e);
		}
	}
}
