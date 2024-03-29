/*
 * Java Genetic Algorithm Library (jenetics-1.0.0).
 * Copyright (c) 2007-2012 Franz Wilhelmstötter
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Author:
 *     Franz Wilhelmstötter (franz.wilhelmstoetter@gmx.at)
 *
 */
package org.jenetics.util;

import java.util.Properties;
import java.util.concurrent.ForkJoinPool;

import javolution.context.ConcurrentContext;
import javolution.context.Context;
import javolution.context.LogContext;
import javolution.lang.Configurable;

/**
 * Simplify the usage of the {@link ConcurrentContext} usage by using the the
 * Java 'try' for resources capability.
 * <p/>
 * Normally you will write
 * [code]
 * ConcurrentContext.enter();
 * try {
 *     ConcurrentContext.execute(task1);
 *     ConcurrentContext.execute(task2); }
 * } finally {
 *     ConcurrentContext.exit();
 * }
 * [/code]
 * to execute two tasks. By using this class you can shorten the code to be
 * written to:
 * [code]
 * try (Concurrency c = Concurrency.start()) {
 *     c.execute(task1);
 *     c.execute(task2);
 * }
 * [/code]
 *
 * The configuration is performed as followed, before executing any concurrent
 * code.
 * [code]
 * public class Main {
 *     public static void main(final String[] args) {
 *         // Using 10 threads for evolving.
 *         Concurrency.setConcurrency(9);
 *
 *         // Forces the application only to use one thread.
 *         Concurrency.setConcurrency(0);
 *     }
 * }
 * [/code]
 *
 * The {@code ConcurrentContext} from the <i>JScience</i> project uses it's
 * own--optimized--thread-pool implementation. If you need to have a single
 * executor service, for the GA and your own classes, you can initialize the
 * {@code Concurrency} class with the {@link ForkJoinPool} from the JDK.
 *
 * [code]
 * public class Main {
 *     public static void main(final String[] args) {
 *         final int nthreads = 10;
 *         final ForkJoinPool pool = new ForkJoinPool(nthreads);
 *         Concurrency.setForkJoinPool(pool);
 *         ...
 *     }
 * }
 * [/code]
 *
 * @author <a href="mailto:franz.wilhelmstoetter@gmx.at">Franz Wilhelmstötter</a>
 * @since 1.0
 * @version 1.0 &ndash; <em>$Revision: 1511 $</em>
 */
public final class Concurrency implements AutoCloseable {

	private static final String KEY_CONTEXT =
		"javolution.context.ConcurrentContext#DEFAULT";

	private static final String KEY_CONCURRENTCY =
		"javolution.context.ConcurrentContext#MAXIMUM_CONCURRENCY";

	private static final Concurrency INSTANCE = new Concurrency();

	private Concurrency() {
	}

	/**
	 * Set the number of threads to use by the {@link ConcurrentContext}.
	 *
	 * @param concurrency the number of threads to use for the default concurernt
	 *        context.
	 */
	public static void setConcurrency(final int concurrency) {
		if (concurrency > ConcurrentContext.getConcurrency()) {
			final Properties properties = new Properties();
			properties.put(KEY_CONCURRENTCY, concurrency);
			setProperties(properties);
		}

		ConcurrentContext.setConcurrency(concurrency);
	}

	/**
	 * Set the concurrent-context to be used by the concurrency.
	 *
	 * @param type  the concurrent-context type.
	 * @throws NullPointerException if the given {@code type} is {@code null}.
	 */
	public static void setContext(final Class<? extends ConcurrentContext> type) {
		final Properties properties = new Properties();
		properties.put(KEY_CONTEXT, type);
		setProperties(properties);
	}

	/**
	 * Convenience method for setting the {@link ForkJoinPool} and the concurrent
	 * context to {@link ForkJoinContext}.
	 *
	 * @param pool the {@link ForkJoinPool} to use for concurrency.
	 */
	public static void setForkJoinPool(final ForkJoinPool pool) {
		ForkJoinContext.setForkkJoinPool(pool);
		setContext(ForkJoinContext.class);
	}

	private static void setProperties(final Properties properties) {
		LogContext.enter(LogContext.NULL);
		try {
			Configurable.read(properties);
		} finally {
			LogContext.exit();
		}
	}

	/**
	 * Reset to the default default context.
	 */
	public static void setDefaultContext() {
		setContext(ConcurrentContext.DEFAULT.get());
	}

	@SuppressWarnings("unchecked")
	public static Class<ConcurrentContext> getContext() {
		final Context context = ConcurrentContext.getCurrent();
		return (Class<ConcurrentContext>)
				ConcurrentContext.class.cast(context).getClass();
	}

	public static Concurrency start() {
		ConcurrentContext.enter();
		return INSTANCE;
	}

	public void execute(final Runnable task) {
		ConcurrentContext.execute(task);
	}

	@Override
	public void close() {
		ConcurrentContext.exit();
	}

}
