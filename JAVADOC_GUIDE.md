# JavaDoc Documentation Guide

## Overview

This guide explains how to generate and use JavaDoc documentation for the Advent of Code 2024 project. The project includes comprehensive JavaDoc comments for all public APIs, methods, and classes.

## Generating JavaDoc

### Using Maven

Add the following plugin to your `pom.xml` (if not already present):

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-javadoc-plugin</artifactId>
            <version>3.4.1</version>
            <configuration>
                <source>17</source>
                <target>17</target>
                <show>private</show>
                <nohelp>true</nohelp>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### Generate Documentation

```bash
# Generate JavaDoc documentation
mvn javadoc:javadoc

# Generate aggregated JavaDoc for all modules
mvn javadoc:aggregate

# Generate JavaDoc and create JAR
mvn javadoc:jar
```

### Output Location

Generated JavaDoc will be available at:
- `target/site/apidocs/index.html` - Main documentation entry point
- `target/site/apidocs/` - All generated HTML files

## JavaDoc Comments Structure

### Class-Level Documentation

```java
/**
 * Service class for solving Advent of Code Day 1: Historian Hysteria.
 * <p>
 * This service processes two lists of integers from input files and provides
 * methods to calculate distances and similarity scores between the lists.
 * The service implements the ProcessLine interface for line-by-line processing.
 * </p>
 * 
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * FileService fileService = new FileService();
 * DayOneService service = new DayOneService(fileService);
 * Integer result = service.partOne("input.txt");
 * }</pre>
 * 
 * @author Lucas Freitas Rocha
 * @version 1.0
 * @since 1.0
 * @see ProcessLine
 * @see FileService
 */
public class DayOneService implements ProcessLine {
    // Implementation
}
```

### Method-Level Documentation

```java
/**
 * Calculates the total distance between paired elements from two sorted lists.
 * <p>
 * This method reads pairs of integers from the specified file, sorts both lists,
 * and calculates the sum of absolute differences between corresponding elements.
 * </p>
 * 
 * <h3>Algorithm:</h3>
 * <ol>
 * <li>Parse input file into two separate lists</li>
 * <li>Sort both lists in ascending order</li>
 * <li>Calculate absolute difference for each pair</li>
 * <li>Sum all differences</li>
 * </ol>
 * 
 * <h3>Input Format:</h3>
 * Each line should contain two integers separated by three spaces:
 * <pre>
 * 3   4
 * 4   3
 * 2   5
 * </pre>
 * 
 * @param path the file path containing space-separated integer pairs
 * @return the sum of absolute differences between paired elements
 * @throws IOException if the file cannot be read or has invalid format
 * @throws RuntimeException if the two lists have different sizes
 * 
 * @see #partTwo(String)
 * @see #buildAndSort(String)
 */
public Integer partOne(String path) throws IOException {
    // Implementation
}
```

### Interface Documentation

```java
/**
 * Functional interface for processing individual lines from input files.
 * <p>
 * This interface defines a contract for line processing strategies used
 * throughout the Advent of Code solutions. Implementations should handle
 * the parsing and processing of a single line of input data.
 * </p>
 * 
 * <h3>Usage Examples:</h3>
 * <pre>{@code
 * // Lambda expression
 * ProcessLine processor = line -> System.out.println("Processing: " + line);
 * 
 * // Method reference
 * ProcessLine processor = System.out::println;
 * 
 * // Anonymous class
 * ProcessLine processor = new ProcessLine() {
 *     public void process(String line) {
 *         // Custom processing logic
 *     }
 * };
 * }</pre>
 * 
 * @author Lucas Freitas Rocha
 * @version 1.0
 * @since 1.0
 * @see FileService#readFile(String, ProcessLine)
 */
@FunctionalInterface
public interface ProcessLine {
    /**
     * Processes a single line of input data.
     * <p>
     * Implementations should handle the parsing and processing of the provided
     * line according to the specific requirements of each day's challenge.
     * </p>
     * 
     * @param line the line of text to process, never null
     * @throws IllegalArgumentException if the line format is invalid
     */
    void process(String line);
}
```

## Best Practices for JavaDoc

### 1. Use Proper HTML Tags

```java
/**
 * Processes multiplication instructions with conditional execution.
 * <p>
 * This method recognizes the following patterns:
 * </p>
 * <ul>
 * <li><code>mul(x,y)</code> - multiplication instruction</li>
 * <li><code>do()</code> - enable future multiplications</li>
 * <li><code>don't()</code> - disable future multiplications</li>
 * </ul>
 * 
 * <h3>Pattern Matching:</h3>
 * The regex pattern used is: <code>(do\\(\\))|(don't\\(\\))|(mul\\((\\d+),(\\d+)\\))</code>
 */
```

### 2. Include Code Examples

```java
/**
 * Reads a file line by line and processes each line.
 * 
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * FileService fileService = new FileService();
 * fileService.readFile("input.txt", line -> {
 *     System.out.println("Processing: " + line);
 * });
 * }</pre>
 */
```

### 3. Document Exceptions

```java
/**
 * @throws IOException if the file is not found or cannot be read
 * @throws IllegalArgumentException if the path is null or empty
 * @throws SecurityException if access to the file is denied
 */
```

### 4. Use @see References

```java
/**
 * @see #partTwo(String)
 * @see FileService#readFile(String, ProcessLine)
 * @see ProcessLine#process(String)
 * @see <a href="https://adventofcode.com/2024/day/1">Advent of Code Day 1</a>
 */
```

## Custom JavaDoc Tags

You can define custom tags in the Maven plugin configuration:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-javadoc-plugin</artifactId>
    <version>3.4.1</version>
    <configuration>
        <tags>
            <tag>
                <name>complexity</name>
                <placement>a</placement>
                <head>Time Complexity:</head>
            </tag>
            <tag>
                <name>space</name>
                <placement>a</placement>
                <head>Space Complexity:</head>
            </tag>
        </tags>
    </configuration>
</plugin>
```

Usage:
```java
/**
 * Sorts both lists and calculates distances.
 * 
 * @complexity O(n log n) due to sorting operations
 * @space O(n) for storing the two lists
 */
```

## Package Documentation

Create `package-info.java` files:

```java
/**
 * Service classes for Advent of Code 2024 solutions.
 * <p>
 * This package contains service classes that implement solutions for each day
 * of the Advent of Code 2024 challenge. Each service class follows a consistent
 * pattern with partOne() and partTwo() methods corresponding to the two parts
 * of each day's challenge.
 * </p>
 * 
 * <h2>Architecture:</h2>
 * <ul>
 * <li>All services implement the {@link ProcessLine} interface</li>
 * <li>File I/O is handled by {@link io.github.lucasfreitasrocha.util.FileService}</li>
 * <li>Each service is responsible for parsing and solving its specific challenge</li>
 * </ul>
 * 
 * @author Lucas Freitas Rocha
 * @version 1.0
 * @since 1.0
 */
package io.github.lucasfreitasrocha.service;
```

## Viewing Generated Documentation

1. Generate the documentation:
   ```bash
   mvn javadoc:javadoc
   ```

2. Open in browser:
   ```bash
   # On Linux/Mac
   open target/site/apidocs/index.html
   
   # On Windows
   start target/site/apidocs/index.html
   ```

3. Navigate through:
   - **Overview** - Project summary
   - **Package** - Package-level documentation
   - **Class** - Individual class documentation
   - **Use** - Where classes/methods are used
   - **Tree** - Class hierarchy
   - **Index** - Alphabetical index

## Integration with IDEs

### IntelliJ IDEA
- JavaDoc comments appear in quick documentation (Ctrl+Q)
- Auto-completion shows JavaDoc descriptions
- Generate JavaDoc: Tools → Generate JavaDoc

### Eclipse
- Hover over methods/classes to see JavaDoc
- Generate JavaDoc: Project → Generate Javadoc

### VS Code
- Install Java Extension Pack
- JavaDoc appears in hover tooltips
- Use Ctrl+Shift+P → "Java: Generate Javadoc"

## Continuous Integration

Add JavaDoc generation to your CI pipeline:

```yaml
# GitHub Actions example
- name: Generate JavaDoc
  run: mvn javadoc:javadoc

- name: Deploy to GitHub Pages
  uses: peaceiris/actions-gh-pages@v3
  with:
    github_token: ${{ secrets.GITHUB_TOKEN }}
    publish_dir: ./target/site/apidocs
```

This comprehensive JavaDoc setup ensures that all public APIs are well-documented with examples, parameter descriptions, return values, and exception handling information.