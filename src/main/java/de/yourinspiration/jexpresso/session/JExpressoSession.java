package de.yourinspiration.jexpresso.session;

import io.netty.handler.codec.http.Cookie;
import io.netty.handler.codec.http.DefaultCookie;

import java.util.UUID;

import de.yourinspiration.jexpresso.MiddlewareHandler;
import de.yourinspiration.jexpresso.Next;
import de.yourinspiration.jexpresso.Request;
import de.yourinspiration.jexpresso.Response;

/**
 * A middleware component for session support. A session id (JEXPRESSIONID) will
 * be stored as a cookie.
 * 
 * @author Marcel Härle
 *
 */
public class JExpressoSession implements MiddlewareHandler {

    public static final String COOKIE_NAME = "JEXPRESSOSESSIONID";
    public static final String SESSION_ATTR = "session";

    private final SessionStore sessionStore;

    /**
     * Constructs a new session middleware with the given session store.
     * 
     * @param sessionStore
     *            the session store
     */
    public JExpressoSession(final SessionStore sessionStore) {
        this.sessionStore = sessionStore;
    }

    @Override
    public void handle(final Request request, final Response response, final Next next) {
        if (request.cookie(COOKIE_NAME) == null) {
            final String sessionId = generateSessionId();
            final Cookie cookie = new DefaultCookie(COOKIE_NAME, sessionId);
            response.cookie(cookie);
            request.setCookie(cookie);
        }

        request.attribute(SESSION_ATTR, new SessionImpl(request, sessionStore));

        next.next();
    }

    /**
     * Helper method to retrieve the session of the current request.
     * 
     * @param request
     *            the current request
     * @return returns <code>null</code> if no session exists
     */
    public static Session session(final Request request) {
        return (Session) request.attribute(JExpressoSession.SESSION_ATTR);
    }

    private String generateSessionId() {
        return UUID.randomUUID().toString().replaceAll("-", "") + UUID.randomUUID().toString().replaceAll("-", "");
    }

}
