package com.github.claasahl.raml.junit.internal.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class IsInteger extends TypeSafeDiagnosingMatcher<String> {

	@Override
	public void describeTo(Description description) {
		description.
	}

	@Override
	protected boolean matchesSafely(String item, Description mismatchDescription) {
		try {
			Integer.valueOf(item);
			return true;
		} catch(Exception e) {
			mismatchDescription.appendText("value is not an integer");
			return false;
		}
	}

}
