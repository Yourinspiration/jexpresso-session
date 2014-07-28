# JExpresso Session Support Middleware

Middleware for JExpresso to provide session support.

## Maven

```xml
<repository>
  <id>yourinspiration.realeases</id>
  <url>http://nexus.yourinspiration.de/nexus/content/repositories/releases/</url>
</repository>
<repository>
  <id>yourinspiration.snapshots</id>
  <url>http://nexus.yourinspiration.de/nexus/content/repositories/snapshots/</url>
</repository>
```

```xml
<dependency>
  <groupId>de.yourinspiration</groupId>
  <artifactId>jexpresso-session</artifactId>
  <version>0.0.1-SNAPSHOT</version>
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
