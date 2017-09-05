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
import java.util.logging.Level;

import org.junit.platform.commons.meta.API;
import org.junit.platform.commons.util.Preconditions;

/**
 * @since 1.0
 */
@API(Internal)
public final class LoggerFactory {

	///CLOVER:OFF
	private LoggerFactory() {
		/* no-op */
	}
	///CLOVER:ON

	public static Logger getLogger(Class<?> clazz) {
		return new DelegatingLogger(clazz);
	}

	private static final class DelegatingLogger implements Logger {

		private final String name;

		private java.util.logging.Logger julLogger;

		DelegatingLogger(Class<?> clazz) {
			Preconditions.notNull(clazz, "Class must not be null");
			this.name = clazz.getName();
			this.julLogger = java.util.logging.Logger.getLogger(this.name);
		}

		@Override
		public void error(Supplier<String> msgSupplier) {
			log(Level.SEVERE, null, msgSupplier);
		}

		@Override
		public void error(Throwable t, Supplier<String> msgSupplier) {
			log(Level.SEVERE, t, msgSupplier);
		}

		@Override
		public void warn(Supplier<String> msgSupplier) {
			log(Level.WARNING, null, msgSupplier);
		}

		@Override
		public void warn(Throwable t, Supplier<String> msgSupplier) {
			log(Level.WARNING, t, msgSupplier);
		}

		@Override
		public void info(Supplier<String> msgSupplier) {
			log(Level.INFO, null, msgSupplier);
		}

		@Override
		public void info(Throwable t, Supplier<String> msgSupplier) {
			log(Level.INFO, t, msgSupplier);
		}

		@Override
		public void debug(Supplier<String> msgSupplier) {
			log(Level.FINE, null, msgSupplier);
		}

		@Override
		public void debug(Throwable t, Supplier<String> msgSupplier) {
			log(Level.FINE, t, msgSupplier);
		}

		@Override
		public void trace(Supplier<String> msgSupplier) {
			log(Level.FINEST, null, msgSupplier);
		}

		@Override
		public void trace(Throwable t, Supplier<String> msgSupplier) {
			log(Level.FINEST, t, msgSupplier);
		}

		private void log(Level level, Throwable t, Supplier<String> msgSupplier) {
			if (this.julLogger.isLoggable(level)) {

				// TODO Supply "correct" sourceClassName and sourceMethodName
				// properties to an instance of java.util.logging.LogRecord and
				// invoke log(LogRecord) instead of directly invoking the standard
				// log() method.

				this.julLogger.log(level, t, msgSupplier);
			}
		}

	}

}
