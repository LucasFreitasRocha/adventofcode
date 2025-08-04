# Code Examples and Usage Instructions

## Overview

This document provides comprehensive code examples and usage instructions for all public APIs, functions, and components in the Advent of Code 2024 project.

---

## Quick Start Examples

### Basic Setup

```java
import io.github.lucasfreitasrocha.service.*;
import io.github.lucasfreitasrocha.util.FileService;
import java.io.IOException;

public class AdventOfCodeExample {
    public static void main(String[] args) {
        // Create file service instance
        FileService fileService = new FileService();
        
        try {
            // Example usage for each day
            runDayOneExamples(fileService);
            runDayTwoExamples(fileService);
            runDayThreeExamples(fileService);
            runDayFourExamples(fileService);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

---

## Day One Service Examples

### Basic Usage

```java
public static void runDayOneExamples(FileService fileService) throws IOException {
    DayOneService dayOne = new DayOneService(fileService);
    
    // Part One: Calculate total distance
    Integer distance = dayOne.partOne("src/main/resources/dayOne/input.txt");
    System.out.println("Total distance: " + distance);
    
    // Part Two: Calculate similarity score
    Integer similarity = dayOne.partTwo("src/main/resources/dayOne/input.txt");
    System.out.println("Similarity score: " + similarity);
}
```

### Working with Test Data

```java
public class DayOneTestExample {
    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService();
        DayOneService service = new DayOneService(fileService);
        
        // Test with sample data
        String testFile = "src/main/resources/dayOne/test.txt";
        
        System.out.println("=== Day One Test Results ===");
        System.out.println("Part 1 (Distance): " + service.partOne(testFile));
        System.out.println("Part 2 (Similarity): " + service.partTwo(testFile));
    }
}
```

### Custom Input Processing

```java
public class CustomDayOneExample {
    public static void main(String[] args) throws IOException {
        // Create temporary input file
        String customInput = """
            1   3
            2   4
            3   5
            4   6
            """;
        
        // Write to temporary file and process
        Path tempFile = Files.createTempFile("day1_custom", ".txt");
        Files.write(tempFile, customInput.getBytes());
        
        FileService fileService = new FileService();
        DayOneService service = new DayOneService(fileService);
        
        Integer result = service.partOne(tempFile.toString());
        System.out.println("Custom input result: " + result);
        
        // Cleanup
        Files.delete(tempFile);
    }
}
```

---

## Day Two Service Examples

### Basic Report Safety Analysis

```java
public static void runDayTwoExamples(FileService fileService) throws IOException {
    DayTwoService dayTwo = new DayTwoService(fileService);
    
    // Count safe reports (strict)
    Integer safeReports = dayTwo.partOne("src/main/resources/dayTwo/input.txt");
    System.out.println("Safe reports (strict): " + safeReports);
    
    // Count safe reports (with error tolerance)
    Integer tolerantSafeReports = dayTwo.partTwo("src/main/resources/dayTwo/input.txt");
    System.out.println("Safe reports (tolerant): " + tolerantSafeReports);
}
```

### Analyzing Individual Reports

```java
public class ReportAnalysisExample {
    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService();
        
        // Create custom reports for analysis
        String[] testReports = {
            "7 6 4 2 1",    // Safe (decreasing by 1-2)
            "1 2 7 8 9",    // Unsafe (increase of 5)
            "9 7 6 2 1",    // Unsafe (decrease of 4)
            "1 3 6 7 9",    // Safe (increasing by 1-3)
            "8 6 4 4 1"     // Unsafe (no change from 4 to 4)
        };
        
        // Write test data to temporary file
        Path tempFile = Files.createTempFile("reports", ".txt");
        Files.write(tempFile, String.join("\n", testReports).getBytes());
        
        DayTwoService service = new DayTwoService(fileService);
        
        System.out.println("=== Report Safety Analysis ===");
        System.out.println("Strict mode: " + service.partOne(tempFile.toString()));
        System.out.println("Tolerant mode: " + service.partTwo(tempFile.toString()));
        
        Files.delete(tempFile);
    }
}
```

---

## Day Three Service Examples

### Memory Corruption Processing

```java
public static void runDayThreeExamples(FileService fileService) throws IOException {
    DayThreeService dayThree = new DayThreeService(fileService);
    
    // Process all valid multiplications
    Integer allMults = dayThree.partOne("src/main/resources/dayThree/input.txt");
    System.out.println("Sum of all multiplications: " + allMults);
    
    // Process with conditional execution
    Integer conditionalMults = dayThree.partTwo("src/main/resources/dayThree/input.txt");
    System.out.println("Sum with conditions: " + conditionalMults);
}
```

### Pattern Matching Examples

```java
public class MemoryPatternExample {
    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService();
        DayThreeService service = new DayThreeService(fileService);
        
        // Test different memory corruption patterns
        String[] corruptedMemory = {
            "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))",
            "mul(1,2)do()mul(3,4)don't()mul(5,6)do()mul(7,8)",
            "?mul(123,456)&*mul(1,1)@#$%^&*()",
            "do()mul(2,3)don't()mul(4,5)mul(6,7)do()mul(8,9)"
        };
        
        Path tempFile = Files.createTempFile("memory", ".txt");
        Files.write(tempFile, String.join("\n", corruptedMemory).getBytes());
        
        System.out.println("=== Memory Pattern Analysis ===");
        System.out.println("All valid muls: " + service.partOne(tempFile.toString()));
        System.out.println("Conditional muls: " + service.partTwo(tempFile.toString()));
        
        Files.delete(tempFile);
    }
}
```

---

## Day Four Service Examples

### Word Search Operations

```java
public static void runDayFourExamples(FileService fileService) throws IOException {
    DayFourService dayFour = new DayFourService(fileService);
    
    // Search for XMAS in all directions
    Integer xmasCount = dayFour.partOne("src/main/resources/dayFour/input.txt", false);
    System.out.println("XMAS occurrences: " + xmasCount);
    
    // Search for X-MAS patterns
    Integer xmasPatterns = dayFour.partTwo("src/main/resources/dayFour/input.txt", false);
    System.out.println("X-MAS patterns: " + xmasPatterns);
}
```

### Custom Grid Analysis

```java
public class WordSearchExample {
    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService();
        DayFourService service = new DayFourService(fileService);
        
        // Create a custom 10x10 grid for testing
        String[] grid = {
            "MMMSXXMASM",
            "MSAMXMSMSA",
            "AMXSXMAAMM",
            "MSAMASMSMX",
            "XMASAMXAMM",
            "XXAMMXXAMA",
            "SMSMSASXSS",
            "SAXAMASAAA",
            "MAMMMXMMMM",
            "MXMXAXMASX"
        };
        
        Path tempFile = Files.createTempFile("grid", ".txt");
        Files.write(tempFile, String.join("\n", grid).getBytes());
        
        System.out.println("=== Custom Grid Analysis ===");
        System.out.println("XMAS count: " + service.partOne(tempFile.toString(), true));
        System.out.println("X-MAS patterns: " + service.partTwo(tempFile.toString(), true));
        
        Files.delete(tempFile);
    }
}
```

---

## File Service Examples

### Basic File Reading

```java
public class FileServiceExample {
    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService();
        
        // Simple line counter
        AtomicInteger lineCount = new AtomicInteger(0);
        ProcessLine counter = line -> lineCount.incrementAndGet();
        
        fileService.readFile("src/main/resources/dayOne/input.txt", counter);
        System.out.println("Total lines: " + lineCount.get());
    }
}
```

### Custom Line Processing

```java
public class CustomProcessorExample {
    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService();
        
        // Multiple processors for different operations
        List<String> lines = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        
        // Collect all lines
        ProcessLine lineCollector = lines::add;
        
        // Extract numbers from lines
        ProcessLine numberExtractor = line -> {
            Pattern numberPattern = Pattern.compile("\\d+");
            Matcher matcher = numberPattern.matcher(line);
            while (matcher.find()) {
                numbers.add(Integer.parseInt(matcher.group()));
            }
        };
        
        // Chain processors
        ProcessLine chainedProcessor = line -> {
            lineCollector.process(line);
            numberExtractor.process(line);
        };
        
        fileService.readFile("src/main/resources/dayOne/input.txt", chainedProcessor);
        
        System.out.println("Lines collected: " + lines.size());
        System.out.println("Numbers found: " + numbers.size());
        System.out.println("Sum of numbers: " + numbers.stream().mapToInt(Integer::intValue).sum());
    }
}
```

### Error Handling Examples

```java
public class ErrorHandlingExample {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        
        // Robust error handling
        String[] testFiles = {
            "src/main/resources/dayOne/input.txt",  // Valid file
            "nonexistent.txt",                      // Missing file
            "/root/protected.txt"                   // Permission denied
        };
        
        for (String filename : testFiles) {
            try {
                AtomicInteger lineCount = new AtomicInteger(0);
                fileService.readFile(filename, line -> lineCount.incrementAndGet());
                System.out.println(filename + ": " + lineCount.get() + " lines");
            } catch (IOException e) {
                System.err.println("Error reading " + filename + ": " + e.getMessage());
            }
        }
    }
}
```

---

## Advanced Usage Patterns

### Service Factory Pattern

```java
public class ServiceFactory {
    private final FileService fileService;
    
    public ServiceFactory() {
        this.fileService = new FileService();
    }
    
    public DayOneService createDayOneService() {
        return new DayOneService(fileService);
    }
    
    public DayTwoService createDayTwoService() {
        return new DayTwoService(fileService);
    }
    
    public DayThreeService createDayThreeService() {
        return new DayThreeService(fileService);
    }
    
    public DayFourService createDayFourService() {
        return new DayFourService(fileService);
    }
    
    // Usage example
    public static void main(String[] args) throws IOException {
        ServiceFactory factory = new ServiceFactory();
        
        // Run all solutions
        Map<String, Integer> results = new HashMap<>();
        
        results.put("Day1-Part1", factory.createDayOneService()
            .partOne("src/main/resources/dayOne/input.txt"));
        results.put("Day1-Part2", factory.createDayOneService()
            .partTwo("src/main/resources/dayOne/input.txt"));
        
        results.put("Day2-Part1", factory.createDayTwoService()
            .partOne("src/main/resources/dayTwo/input.txt"));
        results.put("Day2-Part2", factory.createDayTwoService()
            .partTwo("src/main/resources/dayTwo/input.txt"));
        
        // Print all results
        results.forEach((key, value) -> 
            System.out.println(key + ": " + value));
    }
}
```

### Performance Monitoring

```java
public class PerformanceMonitor {
    public static <T> T timeExecution(String operation, Supplier<T> supplier) {
        long startTime = System.nanoTime();
        T result = supplier.get();
        long endTime = System.nanoTime();
        
        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("%s completed in %.2f ms%n", operation, durationMs);
        
        return result;
    }
    
    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService();
        
        // Monitor Day One performance
        DayOneService dayOne = new DayOneService(fileService);
        
        Integer part1Result = timeExecution("Day 1 Part 1", () -> {
            try {
                return dayOne.partOne("src/main/resources/dayOne/input.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        
        Integer part2Result = timeExecution("Day 1 Part 2", () -> {
            try {
                return dayOne.partTwo("src/main/resources/dayOne/input.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        
        System.out.println("Results: Part 1 = " + part1Result + ", Part 2 = " + part2Result);
    }
}
```

### Batch Processing

```java
public class BatchProcessor {
    private final ServiceFactory serviceFactory;
    
    public BatchProcessor() {
        this.serviceFactory = new ServiceFactory();
    }
    
    public void processAllDays() {
        String basePath = "src/main/resources/";
        
        Map<String, Supplier<Integer>> tasks = Map.of(
            "Day 1 Part 1", () -> runSafely(() -> 
                serviceFactory.createDayOneService().partOne(basePath + "dayOne/input.txt")),
            "Day 1 Part 2", () -> runSafely(() -> 
                serviceFactory.createDayOneService().partTwo(basePath + "dayOne/input.txt")),
            "Day 2 Part 1", () -> runSafely(() -> 
                serviceFactory.createDayTwoService().partOne(basePath + "dayTwo/input.txt")),
            "Day 2 Part 2", () -> runSafely(() -> 
                serviceFactory.createDayTwoService().partTwo(basePath + "dayTwo/input.txt"))
        );
        
        System.out.println("=== Batch Processing Results ===");
        tasks.forEach((name, task) -> {
            Integer result = task.get();
            System.out.printf("%-15s: %s%n", name, 
                result != null ? result.toString() : "FAILED");
        });
    }
    
    private Integer runSafely(Supplier<Integer> task) {
        try {
            return task.get();
        } catch (Exception e) {
            System.err.println("Task failed: " + e.getMessage());
            return null;
        }
    }
    
    public static void main(String[] args) {
        new BatchProcessor().processAllDays();
    }
}
```

---

## Testing Examples

### Unit Test Examples

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class DayOneServiceTest {
    private FileService fileService;
    private DayOneService dayOneService;
    
    @BeforeEach
    void setUp() {
        fileService = new FileService();
        dayOneService = new DayOneService(fileService);
    }
    
    @Test
    void testPartOneWithSampleData() throws IOException {
        // Create temporary test file
        String testData = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
            """;
        
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, testData.getBytes());
        
        Integer result = dayOneService.partOne(tempFile.toString());
        assertEquals(11, result); // Expected result for sample data
        
        Files.delete(tempFile);
    }
    
    @Test
    void testFileNotFound() {
        assertThrows(IOException.class, () -> {
            dayOneService.partOne("nonexistent.txt");
        });
    }
}
```

### Integration Test Example

```java
public class IntegrationTest {
    @Test
    void testAllServicesWithRealData() throws IOException {
        FileService fileService = new FileService();
        String basePath = "src/main/resources/";
        
        // Test Day 1
        DayOneService dayOne = new DayOneService(fileService);
        assertNotNull(dayOne.partOne(basePath + "dayOne/test.txt"));
        assertNotNull(dayOne.partTwo(basePath + "dayOne/test.txt"));
        
        // Test Day 2
        DayTwoService dayTwo = new DayTwoService(fileService);
        assertNotNull(dayTwo.partOne(basePath + "dayTwo/test.txt"));
        assertNotNull(dayTwo.partTwo(basePath + "dayTwo/test.txt"));
        
        // Add more service tests...
    }
}
```

---

## Configuration and Customization

### Custom File Service Implementation

```java
public class BufferedFileService extends FileService {
    private final int bufferSize;
    
    public BufferedFileService(int bufferSize) {
        this.bufferSize = bufferSize;
    }
    
    @Override
    public void readFile(String path, ProcessLine processLine) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(path), StandardCharsets.UTF_8)) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                processLine.process(line);
            }
        }
    }
}
```

### Service Configuration

```java
public class ServiceConfiguration {
    public static final String DEFAULT_RESOURCE_PATH = "src/main/resources/";
    public static final boolean DEBUG_MODE = false;
    
    public static DayFourService createDayFourService(boolean isTestMode) {
        FileService fileService = DEBUG_MODE ? 
            new BufferedFileService(8192) : 
            new FileService();
        
        return new DayFourService(fileService);
    }
}
```

This comprehensive collection of examples demonstrates how to use all public APIs effectively, handle errors gracefully, and implement advanced patterns for production use.