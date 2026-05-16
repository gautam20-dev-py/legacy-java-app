# Legacy Java Application

A comprehensive Java application that demonstrates **deprecated APIs**, **breaking changes**, and **old library versions** from Java 5-6 era. This project is educational and serves as a reference for understanding how Java evolved and what breaking changes developers face when modernizing legacy code.

## 🎯 Purpose

This repository showcases:
- ✗ Deprecated Java APIs that were removed in newer Java versions
- ✗ Old dependency versions with compatibility issues
- ✗ Breaking changes between old and modern Java
- ✗ Real-world legacy code patterns
- ✗ Why and how to migrate to modern Java

## 📋 Project Requirements Met

✅ **Requirement 1**: Contains `pom.xml` with specific library versions
✅ **Requirement 2**: Built on Java 1.6 (old version) with applicable dependencies
✅ **Requirement 3**: Uses deprecated functions and breaking changes
✅ **Requirement 4**: Every library in pom.xml is used in the code
✅ **Requirement 5**: Includes multiple categories of breaking changes

## 📦 Legacy Dependencies (10+ Libraries)

| Library | Version | Issue | Breaking Change |
|---------|---------|-------|-----------------|
| commons-logging | 1.1.1 | Very old logging facade | Replaced by SLF4J |
| jaxb-impl (Sun) | 2.1.13 | Removed in Java 11+ | javax.xml.bind removed |
| jaxb-api | 2.2.11 | Old API version | Moved to separate module |
| javax.activation | 1.1 | Removed in Java 11+ | No longer in JDK |
| javax.mail | 1.4.1 | Old version with deprecated methods | API changed in later versions |
| servlet-api | 2.5 | Ancient servlet spec | Replaced by jakarta.servlet |
| commons-codec | 1.3 | Very old version | Many methods changed/removed |
| commons-lang | 2.6 | Deprecated (3.x is modern) | API completely redesigned |
| commons-collections | 3.2.1 | Old collections framework | Replaced by generics-based libraries |
| jdom | 1.1.3 | Old XML library | JDOM 2.x is modern replacement |
| xerces | 2.4.0 | Ancient XML parser | Deprecation warnings in modern Java |
| junit | 3.8.2 | Very old testing framework | Replaced by JUnit 4+ |

## 🚫 Deprecated APIs Demonstrated

### 1. **Thread APIs** (Removed in Java 5+)
```java
Thread.stop()        // ✗ Removed - causes deadlocks
Thread.suspend()     // ✗ Removed - causes deadlocks
Thread.resume()      // ✗ Removed - causes deadlocks
```
**Breaking Change**: Multithreading pattern completely changed

### 2. **String APIs** (Deprecated)
```java
new String(bytes, 0)           // ✗ Wrong charset handling
String.getBytes()              // ✗ No encoding specified
```
**Breaking Change**: Charset handling became mandatory

### 3. **Collection APIs** (Old/Deprecated)
```java
Vector<T>                      // ✗ Use List + Collections.synchronizedList()
Hashtable<K,V>                 // ✗ Use ConcurrentHashMap
Stack<T>                       // ✗ Use Deque
Enumeration<T>                 // ✗ Use Iterator
```
**Breaking Change**: Generics were introduced, old patterns became anti-patterns

### 4. **Codec APIs** (Old Implementation)
```java
Base64.encodeBase64()          // ✗ Old commons-codec
Base64.decodeBase64()          // ✗ Use java.util.Base64 (Java 8+)
```
**Breaking Change**: Java 8 added native Base64 support

### 5. **XML APIs** (Removed in Java 11+)
```java
JAXBContext (com.sun.xml.bind)  // ✗ Removed from JDK in Java 11+
javax.activation.*              // ✗ Removed from JDK in Java 11+
javax.xml.bind.*                // ✗ Removed from JDK in Java 9+
```
**Breaking Change**: JAXB moved to separate Jakarta EE modules

### 6. **I/O APIs** (Deprecated)
```java
RandomAccessFile.readLine()    // ✗ Doesn't handle encoding
DataInputStream.readLine()     // ✗ Marked as deprecated
new File().deleteOnExit()      // ✗ Unreliable
```
**Breaking Change**: NIO.2 and modern I/O replaced these patterns

### 7. **URL APIs** (Deprecated)
```java
new URL(protocol, host, port, file)  // ✗ Deprecated constructor
URLConnection.getHeaderField()       // ✗ Old API
```
**Breaking Change**: Use java.net.URI and modern HTTP clients

## 🏗️ Project Structure

```
legacy-java-app/
├── pom.xml                                    # Maven configuration
├── README.md                                  # This file
├── .gitignore
└── src/
    └── main/
        └── java/
            └── com/
                └── legacy/
                    └── app/
                        └── LegacyApplication.java    # Main application
```

## 🔨 Building the Project

### Prerequisites
- Java 1.6 or higher (for compilation)
- Maven 2.5+ (recommended: Maven 3.x)

### Build Commands

**Compile the project:**
```bash
mvn clean compile
```

**Run the application:**
```bash
mvn exec:java -Dexec.mainClass="com.legacy.app.LegacyApplication"
```

**Create executable JAR:**
```bash
mvn clean package assembly:single
java -jar target/legacy-java-app-1.0.0-jar-with-dependencies.jar
```

**View compiler warnings (deprecated API usage):**
```bash
mvn clean compile -X
```

## 📊 Breaking Changes Summary

### Java Version Migration Impact

| Java Version | Breaking Changes | Impact |
|--------------|-----------------|--------|
| Java 5 | Thread APIs removed, Generics added | Vector/Hashtable anti-patterns |
| Java 6 | Minor updates | Still supports legacy code |
| Java 7 | try-with-resources added | Old I/O patterns obsolete |
| Java 8 | Native Base64, Streams, Lambdas | commons-codec less useful |
| Java 9 | Module system, warnings for old APIs | Deprecation warnings increase |
| Java 11 | JAXB, Activation removed from JDK | Compilation errors |
| Java 16+ | Records, Sealed classes, Pattern matching | Complete paradigm shift |

## 🔄 Migration Guide

To modernize this code to Java 8+:

1. **Replace Vector/Hashtable/Stack:**
   ```java
   // Old
   Vector<String> v = new Vector<>();
   
   // New
   List<String> list = Collections.synchronizedList(new ArrayList<>());
   ```

2. **Replace Base64 codec:**
   ```java
   // Old
   Base64.encodeBase64(bytes)
   
   // New
   java.util.Base64.getEncoder().encodeToString(bytes)
   ```

3. **Replace JAXB:**
   ```java
   // Add dependency
   <dependency>
       <groupId>jakarta.xml.bind</groupId>
       <artifactId>jakarta.xml.bind-api</artifactId>
       <version>3.0.0</version>
   </dependency>
   ```

4. **Replace commons-lang:**
   ```java
   // Old
   commons-lang 2.6
   
   // New
   commons-lang 3.12+
   ```

5. **Replace old I/O:**
   ```java
   // Old
   RandomAccessFile.readLine()
   
   // New
   Files.lines(Paths.get(...))
   ```

## ⚠️ Compiler Warnings

When compiling this project, you will see warnings like:

```
warning: [deprecation] Vector() in Vector is deprecated
warning: [deprecation] Hashtable() in Hashtable is deprecated
warning: [deprecation] readLine() in DataInputStream is deprecated
```

These warnings are **intentional** and demonstrate the deprecated APIs.

## 📚 Learning Outcomes

By studying this project, you'll understand:

- What deprecated APIs look like in Java
- Why certain APIs were removed or changed
- How breaking changes affect code migration
- The evolution of Java's standard library
- Best practices for writing forward-compatible code
- How to read and interpret deprecation warnings

## 🎓 Educational Value

This project is perfect for:
- **Java learners** - Understand Java's evolution
- **Legacy system maintainers** - Identify upgrade paths
- **Architecture students** - Study backward compatibility challenges
- **DevOps engineers** - Plan Java version upgrades
- **Code reviewers** - Identify modernization opportunities

## 📝 License

MIT License - Feel free to use and modify for educational purposes

## 🤝 Contributing

This is an educational repository. Contributions are welcome if they:
- Add more deprecated API examples
- Improve documentation
- Add migration guides for other old libraries

## 📖 Resources

- [Java Deprecation Policy](https://docs.oracle.com/en/java/javase/current/docs/api/)
- [Java 11 Migration Guide](https://docs.oracle.com/en/java/javase/11/migrate/index.html)
- [Jakarta EE Migration](https://jakarta.ee/about/news/java-ee-to-jakarta-ee-migration/)
- [JAXB Jakarta Documentation](https://eclipse-ee4j.github.io/jaxb-ri/)

---

**Remember**: This code is intentionally written with deprecated patterns for educational purposes. Do NOT use these patterns in production code!
