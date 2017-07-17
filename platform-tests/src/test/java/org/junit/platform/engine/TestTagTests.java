/*
 * Copyright 2015-2017 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.platform.engine;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link TestTag}.
 *
 * @since 1.0
 */
class TestTagTests {

	@Test
	void validSyntax() {
		// @formatter:off
		assertAll("Valid Tag Syntax",
			() -> yep("fast"),
			() -> yep("super-fast!"),
			() -> yep("unit-test"),
			() -> yep("  surrounded-by-whitespace\t\n"),
			() -> nope(null),
			() -> nope(""),
			() -> nope("     "),
			() -> nope("\t"),
			() -> nope("\f"),
			() -> nope("\r"),
			() -> nope("\n"),
			() -> nope("custom tag")
		);
		// @formatter:on
	}

	@Test
	void tagEqualsOtherTagWithSameName() {
		assertEquals(TestTag.create("fast"), TestTag.create("fast"));
		assertEquals(TestTag.create("fast").hashCode(), TestTag.create("fast").hashCode());
		assertNotEquals(null, TestTag.create("fast"));
		assertNotEquals(TestTag.create("fast"), null);
	}

	@Test
	void toStringPrintsName() {
		assertEquals("fast", TestTag.create("fast").toString());
	}

	private static void yep(String tag) {
		assertTrue(TestTag.isValidTag(tag), () -> String.format("'%s' should be a valid tag", tag));
	}

	private static void nope(String tag) {
		assertFalse(TestTag.isValidTag(tag), () -> String.format("'%s' should not be a valid tag", tag));
	}

}
