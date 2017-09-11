/**
 * Provides the means to map RAML V0.8 specifications to a common data model.
 * This package contains implementations for the factories and related data
 * models.
 * <p/>
 * A minimal implementation boils down to these factories and their referenced
 * data models as listed below.
 * <ul>
 * <li>{@link com.github.claasahl.raml.junit.api.TestCaseFactory}</li>
 * <ul>
 * <li>{@link com.github.claasahl.raml.junit.api.TestCaseKey}</li>
 * </ul>
 * <li>{@link com.github.claasahl.raml.junit.api.RequestFactory}</li>
 * <ul>
 * <li>{@link com.github.claasahl.raml.junit.api.model.Request}</li>
 * <li>{@link com.github.claasahl.raml.junit.api.model.Parameter}</li>
 * <li>{@link com.github.claasahl.raml.junit.api.model.Body}</li>
 * </ul>
 * <li>{@link com.github.claasahl.raml.junit.api.ConstraintsFactory}</li>
 * <ul>
 * <li>{@link com.github.claasahl.raml.junit.api.model.RequestConstraints}</li>
 * <li>{@link com.github.claasahl.raml.junit.api.model.ResponseConstraints}</li>
 * <li>{@link com.github.claasahl.raml.junit.api.model.ParameterConstraints}</li>
 * <li>{@link com.github.claasahl.raml.junit.api.model.BodyConstraints}</li>
 * </ul>
 * </ul>
 * 
 * @author Claas Ahlrichs
 *
 */
package com.github.claasahl.raml.junit.internal.v08;