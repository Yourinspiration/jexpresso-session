package de.yourinspiration.jexpresso.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStore implements SessionStore {

    private final ConcurrentHashMap<String, Map<String, Serializable>> sessions = new ConcurrentHashMap<>();

    @Override
    public Serializable get(final String key, final String sessionId) {
        Map<String, Serializable> sessionData = sessions.get(sessionId);
        if (sessionData == null) {
            sessionData = new HashMap<>();
            sessions.put(sessionId, sessionData);
        }
        return sessionData.get(key);
    }

    @Override
    public void set(final String key, final Serializable value, final String sessionId) {
        Map<String, Serializable> sessionData = sessions.get(sessionId);
        if (sessionData == null) {
            sessionData = new HashMap<>();
        }
        sessionData.put(key, value);
        sessions.put(sessionId, sessionData);
    }

    @Override
    public long size() {
        return sessions.size();
    }

    @Override
    public void clear() {
        sessions.clear();
    }

    @Override
    public void clear(String sessionId) {
        sessions.remove(sessionId);
    }

}
