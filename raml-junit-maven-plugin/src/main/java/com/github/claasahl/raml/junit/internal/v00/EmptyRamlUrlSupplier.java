package com.github.claasahl.raml.junit.internal.v00;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.github.claasahl.raml.junit.api.RamlUrlSupplier;

/**
 * The class {@link EmptyRamlUrlSupplier}.
 * <p/>
 * This is an implementation of the {@link RamlUrlSupplier} which returns an
 * empty list of URLs.
 * 
 * 
 * @author Claas Ahlrichs
 *
 */
public class EmptyRamlUrlSupplier implements RamlUrlSupplier {

	@Override
	public List<URL> getRamlUrls() {
		return new ArrayList<>();
	}

}
