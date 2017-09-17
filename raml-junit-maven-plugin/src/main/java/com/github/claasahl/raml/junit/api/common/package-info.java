/**
 * Provides interfaces and common data structures for mapping of RAML
 * specifications (e.g. v0.8, v1.0 as well as future versions) to a common data
 * model. A minimal implementation boils down to these factories and their
 * referenced data models as listed below.
 * <ul>
 * <li>{@link com.github.claasahl.raml.junit.api.common.TestCaseFactory}</li>
 * <ul>
 * <li>{@link com.github.claasahl.raml.junit.api.common.TestCaseKey}</li>
 * </ul>
 * <li>{@link com.github.claasahl.raml.junit.api.common.ConstraintsFactory}</li>
 * <ul>
 * <li>{@link com.github.claasahl.raml.junit.api.common.RequestConstraints}</li>
 * <li>{@link com.github.claasahl.raml.junit.api.common.ResponseConstraints}</li>
 * <li>{@link com.github.claasahl.raml.junit.api.common.ParameterConstraints}</li>
 * <li>{@link com.github.claasahl.raml.junit.api.common.BodyConstraints}</li>
 * </ul>
 * <li>{@link com.github.claasahl.raml.junit.api.common.RequestFactory}</li>
 * <ul>
 * <li>{@link com.github.claasahl.raml.junit.api.common.Request}</li>
 * <li>{@link com.github.claasahl.raml.junit.api.common.Parameter}</li>
 * <li>{@link com.github.claasahl.raml.junit.api.common.Body}</li>
 * </ul>
 * </ul>
 * 
 * <h1>Process</h1>
 * <p/>
 * The process and flow of data is as follows.
 * 
 * <ol>
 * <li><b>TestCaseFactory</b>: This factory is meant to load a RAML
 * specification and extract a collection of test cases for the described API.
 * As a rule of thumb, this factory produces a test case for every HTTP status
 * code of every resource.</li>
 * <li><b>ConstraintsFactory</b>: This factory <i>feeds</i> on the previously
 * extracted test cases and looks up details about a particular test case. For
 * each test case, this factory is meant to provide a set of constraints to
 * which an HTTP request and its HTTP response must adhere.</li>
 * <li><b>RequestFactory</b>: This factory is meant to produce HTTP requests for
 * a particular test case. These HTTP requests may be based on the RAML document
 * itself (e.g. default values or example values), local JSON / XML files, or
 * other sources. Naturally, these HTTP requests must also adhere to the above
 * provided constraints.</li>
 * </ol>
 * 
 * @author Claas Ahlrichs
 *
 */
package com.github.claasahl.raml.junit.api.common;