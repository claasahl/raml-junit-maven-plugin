package com.github.claasahl.raml.junit.internal.v00;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.github.claasahl.raml.junit.api.factories.RamlUrlFactory;

/**
 * The class {@link EmptyRamlUrlFactory}.
 * <p/>
 * This is an implementation of the {@link RamlUrlFactory} which returns an
 * empty list of URLs.
 * 
 * 
 * @author Claas Ahlrichs
 *
 */
public class EmptyRamlUrlFactory implements RamlUrlFactory {

	@Override
	public List<URL> getRamlUrls() {
		return new ArrayList<>();
	}

}
