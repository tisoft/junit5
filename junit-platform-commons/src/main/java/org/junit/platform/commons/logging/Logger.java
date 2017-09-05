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

	void error(Supplier<String> msgSupplier);

	void error(Throwable t, Supplier<String> msgSupplier);

	void warn(Supplier<String> msgSupplier);

	void warn(Throwable t, Supplier<String> msgSupplier);

	void info(Supplier<String> msgSupplier);

	void info(Throwable t, Supplier<String> msgSupplier);

	void debug(Supplier<String> msgSupplier);

	void debug(Throwable t, Supplier<String> msgSupplier);

	void trace(Supplier<String> msgSupplier);

	void trace(Throwable t, Supplier<String> msgSupplier);

}
