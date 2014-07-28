package de.yourinspiration.jexpresso.session;

import java.io.Serializable;

import de.yourinspiration.jexpresso.Request;

/**
 * Simple implemenation for {@link Session}.
 * 
 * @author Marcel Härle
 *
 */
public class SessionImpl implements Session {

    private final Request request;
    private final SessionStore sessionStore;
    private final String sessionId;

    protected SessionImpl(final Request request, final SessionStore sessionStore) {
        this.request = request;
        this.sessionStore = sessionStore;
        this.sessionId = request.cookie(JExpressoSession.COOKIE_NAME).getValue();
    }

    @Override
    public Serializable get(final String name) {
        return sessionStore.get(name, sessionId);
    }

    @Override
    public void set(final String name, final Serializable value) {
        sessionStore.set(name, value, sessionId);
    }

    @Override
    public void invalidate() {
        sessionStore.clear(sessionId);
        request.cookie(JExpressoSession.COOKIE_NAME).setDiscard(true);
    }

}
