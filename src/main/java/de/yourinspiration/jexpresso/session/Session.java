package de.yourinspiration.jexpresso.session;

import java.io.Serializable;

/**
 * Represents a user session for HTTP requests.
 * 
 * @author Marcel Härle
 *
 */
public interface Session {

    /**
     * Get the session data for the given name.
     * 
     * @param name
     *            the name
     * @return returns <code>null</code> if no such name exists
     */
    Serializable get(final String name);

    /**
     * Set the value for the given name.
     * 
     * @param name
     *            the name
     * @param value
     *            the value
     */
    void set(final String name, final Serializable value);

    /**
     * Invalidate the session.
     */
    void invalidate();

}
