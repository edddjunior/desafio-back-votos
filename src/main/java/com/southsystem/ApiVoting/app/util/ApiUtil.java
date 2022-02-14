package com.southsystem.ApiVoting.app.util;

import java.util.function.Consumer;

public class ApiUtil {

	/**
	 * Represents the API version on the requests/responses header.
	 */
	public static final String HEADER_API_VERSION = "api-version";


	/**
	 * Sets some value with a given setter method if it's not null.
	 *
	 * @param <T>
	 * @param setter
	 * @param value
	 */
	public static <T> void setIfNotNull(final Consumer<T> setter, final T value) {
		if (value != null) {
			setter.accept(value);
		}
	}
}
