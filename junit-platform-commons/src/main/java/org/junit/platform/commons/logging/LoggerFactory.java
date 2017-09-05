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
import java.util.logging.LogRecord;

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
		Preconditions.notNull(clazz, "Class must not be null");
		return new DelegatingLogger(clazz.getName());
	}

	private static final class DelegatingLogger implements Logger {

		private static final String FQCN = DelegatingLogger.class.getName();

		private final String name;

		private final java.util.logging.Logger julLogger;

		DelegatingLogger(String name) {
			this.name = name;
			this.julLogger = java.util.logging.Logger.getLogger(this.name);
		}

		@Override
		public void error(Supplier<String> messageSupplier) {
			log(Level.SEVERE, null, messageSupplier);
		}

		@Override
		public void error(Throwable t, Supplier<String> messageSupplier) {
			log(Level.SEVERE, t, messageSupplier);
		}

		@Override
		public void warn(Supplier<String> messageSupplier) {
			log(Level.WARNING, null, messageSupplier);
		}

		@Override
		public void warn(Throwable t, Supplier<String> messageSupplier) {
			log(Level.WARNING, t, messageSupplier);
		}

		@Override
		public void info(Supplier<String> messageSupplier) {
			log(Level.INFO, null, messageSupplier);
		}

		@Override
		public void info(Throwable t, Supplier<String> messageSupplier) {
			log(Level.INFO, t, messageSupplier);
		}

		@Override
		public void debug(Supplier<String> messageSupplier) {
			log(Level.FINE, null, messageSupplier);
		}

		@Override
		public void debug(Throwable t, Supplier<String> messageSupplier) {
			log(Level.FINE, t, messageSupplier);
		}

		@Override
		public void trace(Supplier<String> messageSupplier) {
			log(Level.FINER, null, messageSupplier);
		}

		@Override
		public void trace(Throwable t, Supplier<String> messageSupplier) {
			log(Level.FINER, t, messageSupplier);
		}

		private void log(Level level, Throwable t, Supplier<String> messageSupplier) {
			if (this.julLogger.isLoggable(level)) {
				this.julLogger.log(createLogRecord(level, t, messageSupplier));
			}
		}

		private LogRecord createLogRecord(Level level, Throwable t, Supplier<String> messageSupplier) {
			StackTraceElement[] stack = new Throwable().getStackTrace();
			String sourceClassName = null;
			String sourceMethodName = null;
			boolean found = false;
			for (StackTraceElement element : stack) {
				String className = element.getClassName();
				if (FQCN.equals(className)) {
					found = true;
				}
				else if (found) {
					sourceClassName = className;
					sourceMethodName = element.getMethodName();
					break;
				}
			}

			LogRecord logRecord = new LogRecord(level, nullSafeGet(messageSupplier));
			logRecord.setLoggerName(this.name);
			logRecord.setThrown(t);
			logRecord.setSourceClassName(sourceClassName);
			logRecord.setSourceMethodName(sourceMethodName);
			logRecord.setResourceBundleName(this.julLogger.getResourceBundleName());
			logRecord.setResourceBundle(this.julLogger.getResourceBundle());

			return logRecord;
		}

		private static String nullSafeGet(Supplier<String> messageSupplier) {
			return (messageSupplier != null ? messageSupplier.get() : null);
		}

	}

}
