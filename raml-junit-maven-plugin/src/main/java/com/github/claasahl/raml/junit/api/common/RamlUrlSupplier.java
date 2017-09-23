package com.github.claasahl.raml.junit.api.common;

import java.net.URL;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * The interface {@link RamlUrlSupplier}.
 * <p/>
 * This interface provides a customizable hook for looking up URLs to RAML
 * documents. Thus allowing easy replacement of the default strategy with a more
 * specialized, suitable strategy, if the need arises.
 * <p/>
 * Implementations of this interface supply a collection URLs to RAML
 * specifications. They are meant to look for RAML specifications in local
 * directories, return a fixed list of URLs or otherwise discover RAML
 * specifications.
 * <p/>
 * For an overview of related classes, please refer to
 * {@link com.github.claasahl.raml.junit.api.common}.
 * <p/>
 * Implementing classes must provide a zero-argument constructor.
 * 
 * @author Claas Ahlrichs
 *
 */
public interface RamlUrlSupplier {

	/**
	 * Returns URLs to RAML documents.
	 * <p/>
	 * <b>Note:</b> The returned list may not contain <code>null</code> values.
	 * 
	 * @return URLs to RAML documents
	 */
	@Nonnull
	List<URL> getRamlUrls();

}
