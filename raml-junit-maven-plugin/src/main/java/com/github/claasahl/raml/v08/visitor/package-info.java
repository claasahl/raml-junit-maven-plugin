
/**
 * This package provides visitor-interfaces and base implementation for
 * key-classes within the {@link org.raml.v2.api.model.v08.api.Api} hierarchy.
 * The visitor interfaces are intended to provide methods for visiting composite
 * field for key-classes within the {@link org.raml.v2.api.model.v08.api.Api}
 * hierarchy. The overall idea is to enable to "walk" a RAML specification (i.e.
 * "walk" as in "walking" a directory structure).
 * <p>
 * Each of the identified key-classes have a dedicated visitor interface (as
 * well as a base implementation). The visitor interfaces provide methods for
 * visiting composite fields within the key-classes (i.e.
 * {@link java.lang.String} or {@link java.lang.Enum} are not considered
 * composite objects, but {@link java.util.Collection}s or
 * {@link java.util.Map}s are considered composite objects).
 * 
 * 
 * @author Claas Ahlrichs
 *
 */
package com.github.claasahl.raml.v08.visitor;