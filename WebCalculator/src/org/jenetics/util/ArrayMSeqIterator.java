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

/**
 * @author <a href="mailto:franz.wilhelmstoetter@gmx.at">Franz Wilhelmstötter</a>
 * @since 1.0
 * @version 1.0 &ndash; <em>$Revision: 1511 $</em>
 */
final class ArrayMSeqIterator<T> extends ArraySeqIterator<T> {

	public ArrayMSeqIterator(final Array<T> array) {
		super(array);
	}

	@Override
	public void set(final T value) {
		final Array<T> array = (Array<T>)_array;
		array.set(_pos, value);
	}
}
