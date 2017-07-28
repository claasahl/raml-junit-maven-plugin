package com.github.claasahl.raml.junit.internal;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.raml.v2.api.RamlModelBuilder;
import org.raml.v2.api.RamlModelResult;

@RunWith(Parameterized.class)
public class ValidateRamlSpecification {

	private final URL ramlUrl;

	public ValidateRamlSpecification(URL ramlUrl) {
		this.ramlUrl = ramlUrl;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Factories.getRamlUrls().stream().map(t -> new Object[] { t }).collect(Collectors.toList());
	}

	@Test
	public void bla() throws IOException {
		Reader reader = new InputStreamReader(this.ramlUrl.openStream());
		String ramlLocation = this.ramlUrl.toExternalForm();

		RamlModelResult ramlModelResult = new RamlModelBuilder().buildApi(reader, ramlLocation);
		assertThat(ramlModelResult.getValidationResults(), empty());
	}

	@Test
	public void bbb() {
		assertThat(Utils.getRamlVersion(ramlUrl), notNullValue());
	}

	@Test
	public void apiV08() {
		String ramlVersion = Utils.getRamlVersion(ramlUrl);
		assumeThat(ramlVersion, equalTo("0.8"));
		assertThat(Utils.buildApiV08(ramlUrl), notNullValue());
	}

	@Test
	public void apiV10() {
		String ramlVersion = Utils.getRamlVersion(ramlUrl);
		assumeThat(ramlVersion, equalTo("1.0"));
		assertThat(Utils.buildApiV10(ramlUrl), notNullValue());
	}

}
