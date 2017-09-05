/*
 * Copyright 2015-2017 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.platform.commons.logging;

import static org.junit.platform.commons.meta.API.Usage.Internal;

import java.util.function.Supplier;

import org.junit.platform.commons.meta.API;

/**
 * @since 1.0
 */
@API(Internal)
public interface Logger {

	void error(Supplier<String> messageSupplier);

	void error(Throwable t, Supplier<String> messageSupplier);

	void warn(Supplier<String> messageSupplier);

	void warn(Throwable t, Supplier<String> messageSupplier);

	void info(Supplier<String> messageSupplier);

	void info(Throwable t, Supplier<String> messageSupplier);

	void debug(Supplier<String> messageSupplier);

	void debug(Throwable t, Supplier<String> messageSupplier);

	void trace(Supplier<String> messageSupplier);

	void trace(Throwable t, Supplier<String> messageSupplier);

}
