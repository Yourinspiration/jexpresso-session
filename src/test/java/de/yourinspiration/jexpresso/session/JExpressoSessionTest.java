package de.yourinspiration.jexpresso.session;

import io.netty.handler.codec.http.DefaultCookie;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import de.yourinspiration.jexpresso.Next;
import de.yourinspiration.jexpresso.Request;
import de.yourinspiration.jexpresso.Response;

/**
 * Test case for {@link JExpressoSession}.
 * 
 * @author Marcel Härle
 *
 */
public class JExpressoSessionTest {

    private JExpressoSession jexpressoSession;

    @Mock
    private SessionStore sessionStore;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        jexpressoSession = new JExpressoSession(sessionStore);
    }

    @Test
    public void testHandle() {
        final Request request = Mockito.mock(Request.class);
        final Response response = Mockito.mock(Response.class);
        final Next next = Mockito.mock(Next.class);

        Mockito.when(request.cookie(JExpressoSession.COOKIE_NAME)).thenReturn(
                new DefaultCookie(JExpressoSession.COOKIE_NAME, "4711"));

        jexpressoSession.handle(request, response, next);

        Mockito.verify(request).attribute(Matchers.eq(JExpressoSession.SESSION_ATTR), Matchers.any(SessionImpl.class));
        Mockito.verify(next).next();
    }
}
