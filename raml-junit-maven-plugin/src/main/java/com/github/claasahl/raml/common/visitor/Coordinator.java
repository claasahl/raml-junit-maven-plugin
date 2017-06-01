package com.github.claasahl.raml.common.visitor;

/**
 * The tagging interface {@link Coordinator}.
 * <p>
 * A coordinator is intended to loosely resemble the <i>iterator</i> design
 * pattern, but rather than iterating over objects of the same type,
 * coordinators "iterate" over attributes of a single object. Furthermore,
 * coordinators are backed by a plain old java object, rather than being backed
 * by a list of some sort.
 * <p>
 * Here, the <i>coordinator</i> pattern is meant to be used in combination with
 * the <i>visitor</i> design pattern. In much the same way the <i>iterator</i>
 * design pattern can be used to traverse and eventually visit a family of
 * related objects, the <i>coordinator</i> pattern is used to traverse
 * attributes an object and present those to a visitor.
 * <p>
 * <b>Example:</b> Whereas an iterator would iterate over a list of objects
 * (e.g. letters), the coordinator would "iterate" over relevant attributes of
 * an object (e.g. name and address of sender, name and address of recipient,
 * date of arrival, etc.).
 * 
 * @author Claas Ahlrichs
 *
 */
public interface Coordinator {

}
