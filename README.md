# JExpresso Session Support Middleware

Middleware for JExpresso providing session support.

## Maven

Latest stable release:

```xml
<dependency>
  <groupId>de.yourinspiration</groupId>
  <artifactId>jexpresso-session</artifactId>
  <version>1.0.1</version>
</dependency>
```

## Usage


### General

The session data will be stored as a request attribute. To retrieve the current session call the attribute() method of the request object, or use the static helper method of the JExpressoSession class.

```java
Session session = (Session) req.attribute(JExpressoSession.SESSION_ATTR);
```

```java
Session session = JExpressoSession.session(req);
```

The Session interface provides methods to get and set data from/to the session, and a method to invalidate the current session.

### In Memory Store

```java
final JExpresso app = new JExpresso();
app.use(new JExpressoSession(new InMemoryStore()));
```
