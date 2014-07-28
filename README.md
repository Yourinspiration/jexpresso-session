# JExpresso Session Support Middleware

Middleware for JExpresso to provide session support.

## Maven

```xml
<repository>
  <id>nexus.yourinspiration.de</id>
  <url>http://nexus.yourinspiration.de/nexus/content/repositories/releases/</url>
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

### In Memory Store

```java
final JExpresso app = new JExpresso();
app.use(new JExpressoSession(new InMemoryStore()));
```
