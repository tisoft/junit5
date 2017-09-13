/*
 * Copyright 2015-2017 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.platform.commons.jpms;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Module reference scanner tests.
 *
 * @since 1.1
 */
class ModuleReferenceScannerTests {

	@Test
	void scanNullAsModuleReferenceFails() {
		ModuleReferenceScanner scanner = new ModuleReferenceScanner(__ -> true, __ -> true);
		assertThrows(NullPointerException.class, () -> scanner.scan(null));
	}

	@Test
	// TODO @DisabledWhenClassIsLoadedFromTheClassPath...
	void insideSameExplicitModule() {
		ModuleReferenceScanner scanner = new ModuleReferenceScanner(__ -> true, __ -> true);
		assertNotNull(getClass().getModule().getName());
		assertNotNull(scanner.getClass().getModule().getName());
		assertEquals(getClass().getModule(), scanner.getClass().getModule());
	}

	@Test
	void validClassNames() {
		ModuleReferenceScanner scanner = new ModuleReferenceScanner(__ -> true, __ -> true);
		assertAll("valid class name", //
			() -> assertEquals("Abc", scanner.className("Abc.class")), //
			() -> assertEquals("foo.bar.Baz", scanner.className("foo/bar/Baz.class")) //
		);
	}

}
