package com.github.claasahl.raml.junit.api.factories;

import java.net.URL;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * The interface {@link RamlUrlFactory}.
 * <p/>
 * This interface provides a customizable hook for looking up URLs to RAML
 * documents. Thus allowing to easily replace the default strategy with a more
 * specialized, suitable strategy, if the need arises.
 * <p/>
 * Implementing classes must provide a zero-argument constructor.
 * 
 * @author Claas Ahlrichs
 *
 */
public interface RamlUrlFactory {

	/**
	 * Returns URLs to RAML documents.
	 * <p/>
	 * <b>Note:</b> The returned list may not contain <code>null</code> values.
	 * the URL to the RAML document
	 * 
	 * @return URLs to RAML documents
	 */
	@Nonnull
	List<URL> getRamlUrls();

}
