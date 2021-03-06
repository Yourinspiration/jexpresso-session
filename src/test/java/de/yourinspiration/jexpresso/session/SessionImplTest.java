package de.yourinspiration.jexpresso.session;

import static org.junit.Assert.assertEquals;
import io.netty.handler.codec.http.DefaultCookie;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import de.yourinspiration.jexpresso.Request;

/**
 * Test case for {@link SessionImpl}.
 * 
 * @author Marcel Härle
 *
 */
public class SessionImplTest {

    private SessionImpl sessionImpl;

    @Mock
    private Request request;
    @Mock
    private SessionStore sessionStore;

    private final String sessionId = "4711";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(request.cookie(JExpressoSession.COOKIE_NAME)).thenReturn(
                new DefaultCookie(JExpressoSession.COOKIE_NAME, sessionId));

        sessionImpl = new SessionImpl(request, sessionStore);
    }

    @Test
    public void testGet() {
        Mockito.when(sessionStore.get("test", sessionId, String.class)).thenReturn("myValue");
        assertEquals("myValue", sessionImpl.get("test", String.class));
    }

    @Test
    public void testSet() {
        sessionImpl.set("test", "myValue");
        Mockito.verify(sessionStore).set("test", "myValue", sessionId);
    }

    @Test
    public void testInvalidate() {
        sessionImpl.invalidate();
        Mockito.verify(sessionStore).clear(sessionId);
    }

}
