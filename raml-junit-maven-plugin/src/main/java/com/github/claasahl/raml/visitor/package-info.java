
/**
 * This package provides visitor-interface and base implementation for
 * key-classes within the {@link org.raml.model.Raml} hierarchy. The visitor
 * interfaces are intended to provide methods for visiting any composite objects
 * for all key-classes within the {@link org.raml.model.Raml} hierarchy. The
 * overall idea is to enable to "walk" a RAML specification (i.e. "walk" as in
 * "walking" a directory structure).
 * <p>
 * Each for the identified key-classes have a dedicated visitor interface (as
 * well as a base implementation). The visitor interfaces provide methods for
 * visiting any composite objects within the key-classes (i.e.
 * {@link java.lang.String} or {@link java.lang.Enum} are not considered
 * composite objects, but {@link org.raml.model.parameter.Header} or
 * {@link org.raml.model.parameter.UriParameter} are considered composite
 * objects).
 * 
 * 
 * @author Claas
 *
 */
package com.github.claasahl.raml.visitor;
