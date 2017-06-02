package com.github.claasahl.raml.common.visitor;

import java.util.Collection;
import java.util.Map;

/**
 * The interface {@link VisitorStrategy}.
 * <p>
 * Superclass of all RAML-related visitors. This class models is intended to
 * model a structure that is common to all visitors of arbitrary objects and
 * their (composite) fields. Implementations of this class will most likely
 * extract details or otherwise process visited objects.
 * <p>
 * This interface resembles a derivation of the <i>visitor</i> design pattern.
 * It is still intended to visit objects in an object structure, however, not
 * necessarily objects that are part of the same class-hierarchy. In contrast to
 * the original design pattern which includes a separate visit-method for types
 * in an object-structure, this derivation includes a visit-method for composite
 * fields of the visited class. More specifically, implementations are meant to
 * provide a visit-method for all (relevant) fields that return a
 * {@link Collection}, a {@link Map} or similarly structured objects.
 * 
 * @author Claas Ahlrichs
 *
 * @param <T>
 *            type of class being visited
 */
public interface VisitorStrategy<T> {
	/**
	 * Called before (composite) fields are being visited. This method provides
	 * a callback for arbitrary actions that need to be performed before the
	 * visitee is being visited.
	 * <p>
	 * This method will typically be used for preparing the (upcoming) visit.
	 * 
	 * @param visitee
	 *            the object being visited
	 */
	void beforeVisit(T visitee);

	/**
	 * Called after (composite) fields have been visited. This method provides a
	 * callback for arbitrary actions that need to be performed after the
	 * visitee has been visited.
	 * <p>
	 * This method will typically be used for cleaning up operations.
	 * 
	 * @param visitee
	 *            the object being visited
	 */
	void afterVisit(T visitee);
}
