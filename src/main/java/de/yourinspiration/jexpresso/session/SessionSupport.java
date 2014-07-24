package de.yourinspiration.jexpresso.session;

import io.netty.handler.codec.http.Cookie;
import io.netty.handler.codec.http.DefaultCookie;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import de.yourinspiration.jexpresso.MiddlewareHandler;
import de.yourinspiration.jexpresso.Next;
import de.yourinspiration.jexpresso.Request;
import de.yourinspiration.jexpresso.Response;

public class SessionSupport implements MiddlewareHandler {

    public static final String COOKIE_NAME = "JEXPRESSOSESSIONID";

    private static final ConcurrentHashMap<String, Map<String, Object>> sessions = new ConcurrentHashMap<>();

    @Override
    public void handle(final Request request, final Response response, final Next next) {
        if (request.cookie(COOKIE_NAME) == null) {
            final String sessionId = UUID.randomUUID().toString();
            final Cookie cookie = new DefaultCookie(COOKIE_NAME, sessionId);
            response.cookie(cookie);
            final Map<String, Object> sessionData = new HashMap<>();
            sessions.put(sessionId, sessionData);
            request.attribute("session", sessionData);
        } else {
            final String sessionId = request.cookie(COOKIE_NAME).getValue();
            Map<String, Object> sessionData = sessions.get(sessionId);
            if (sessionData == null) {
                sessionData = new HashMap<>();
                sessions.put(sessionId, sessionData);
            }
            request.attribute("session", sessionData);
        }
        next.next();
    }

    public static Object getSession(final Request request, final String key) {
        final String sessionId = request.cookie(COOKIE_NAME).getValue();
        Map<String, Object> sessionData = sessions.get(sessionId);
        if (sessionData == null) {
            sessionData = new HashMap<>();
            sessions.put(sessionId, sessionData);
        }
        return sessionData.get(key);
    }

    public static void setSession(final Request request, final String key, final Object value) {
        final String sessionId = request.cookie(COOKIE_NAME).getValue();
        Map<String, Object> sessionData = sessions.get(sessionId);
        if (sessionData == null) {
            sessionData = new HashMap<>();
        }
        sessionData.put(key, value);
        sessions.put(sessionId, sessionData);
    }

}
