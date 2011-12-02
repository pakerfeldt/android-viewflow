package org.taptwo.android.widget;

/**
 * Handles the situation where a View has been requested but it is outside the adapter range.
 * This can occur in situations where the adapter range is adjusted dynamically after the ViewFlow widget is created.
 * 
 * @author polly
 *
 */
public class ViewOutOfBoundsException extends RuntimeException {

	private static final long serialVersionUID = 8160977913873774611L;

}
