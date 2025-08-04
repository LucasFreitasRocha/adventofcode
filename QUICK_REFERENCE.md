# Quick Reference Guide

This guide provides quick code snippets and common usage patterns for all public APIs in the Advent of Code 2024 Java solutions.

## Table of Contents

- [Basic Setup](#basic-setup)
- [Service Classes Quick Reference](#service-classes-quick-reference)
- [File Processing](#file-processing)
- [Common Patterns](#common-patterns)
- [Error Handling](#error-handling)
- [Performance Tips](#performance-tips)

## Basic Setup

### Import Statements
```java
import io.github.lucasfreitasrocha.service.DayOneService;
import io.github.lucasfreitasrocha.service.DayTwoService;
import io.github.lucasfreitasrocha.service.DayThreeService;
import io.github.lucasfreitasrocha.service.DayFourService;
import io.github.lucasfreitasrocha.util.FileService;
import java.io.IOException;
```

### Basic Service Instantiation
```java
FileService fileService = new FileService();

DayOneService dayOneService = new DayOneService(fileService);
DayTwoService dayTwoService = new DayTwoService(fileService);
DayThreeService dayThreeService = new DayThreeService(fileService);
DayFourService dayFourService = new DayFourService(fileService);
```

## Service Classes Quick Reference

### DayOneService

**Purpose:** List processing and mathematical operations

```java
// Basic usage
Integer result = dayOneService.partOne("input.txt");
Integer result2 = dayOneService.partTwo("input.txt");

// With error handling
try {
    Integer result = dayOneService.partOne("input.txt");
    System.out.println("Result: " + result);
} catch (IOException e) {
    System.err.println("Error reading file: " + e.getMessage());
} catch (RuntimeException e) {
    System.err.println("Data validation error: " + e.getMessage());
}
```

**Input Format:**
```
1   5
3   2
7   1
```

### DayTwoService

**Purpose:** Report validation with error tolerance

```java
// Basic usage
Integer safeReports = dayTwoService.partOne("input.txt");
Integer safeReportsWithTolerance = dayTwoService.partTwo("input.txt");

// Batch processing
String[] files = {"input1.txt", "input2.txt", "input3.txt"};
for (String file : files) {
    try {
        Integer result = dayTwoService.partOne(file);
        System.out.println(file + ": " + result + " safe reports");
    } catch (IOException e) {
        System.err.println("Error processing " + file + ": " + e.getMessage());
    }
}
```

**Input Format:**
```
1 3 5 7 9
9 7 5 3 1
1 2 4 8 16
```

### DayThreeService

**Purpose:** Pattern matching and instruction processing

```java
// Basic usage
Integer result = dayThreeService.partOne("input.txt");
Integer result2 = dayThreeService.partTwo("input.txt");

// Processing multiple files
Map<String, Integer> results = new HashMap<>();
String[] files = {"test.txt", "input.txt"};

for (String file : files) {
    try {
        results.put(file + "_part1", dayThreeService.partOne(file));
        results.put(file + "_part2", dayThreeService.partTwo(file));
    } catch (IOException e) {
        System.err.println("Error processing " + file);
    }
}
```

**Input Format:**
```
mul(2,3)
do()
mul(4,5)
don't()
mul(1,10)
```

### DayFourService

**Purpose:** Grid pattern matching and word search

```java
// Basic usage
Integer result = dayFourService.partOne("input.txt", false);  // production mode
Integer result2 = dayFourService.partTwo("input.txt", false);

// Test vs Production
Integer testResult = dayFourService.partOne("test.txt", true);   // 10x10 grid
Integer prodResult = dayFourService.partOne("input.txt", false); // 140x140 grid

// Batch processing with mode selection
boolean[] modes = {true, false}; // test, production
for (boolean testMode : modes) {
    try {
        Integer result = dayFourService.partOne("input.txt", testMode);
        System.out.println((testMode ? "Test" : "Production") + " mode: " + result);
    } catch (IOException e) {
        System.err.println("Error in " + (testMode ? "test" : "production") + " mode");
    }
}
```

**Input Format:**
```
XMASXMASXX
MASXMASXMA
SXMASXMASX
MASXMASXMA
```

## File Processing

### Basic File Reading
```java
FileService fileService = new FileService();

// Simple line processing
fileService.readFile("input.txt", new ProcessLine() {
    @Override
    public void process(String line) {
        System.out.println("Line: " + line);
    }
});
```

### Custom Line Processor
```java
public class CustomProcessor implements ProcessLine {
    private int lineCount = 0;
    private List<String> processedLines = new ArrayList<>();
    
    @Override
    public void process(String line) {
        lineCount++;
        processedLines.add(line.toUpperCase());
    }
    
    public int getLineCount() { return lineCount; }
    public List<String> getProcessedLines() { return processedLines; }
}

// Usage
CustomProcessor processor = new CustomProcessor();
fileService.readFile("input.txt", processor);
System.out.println("Processed " + processor.getLineCount() + " lines");
```

### Lambda-based Processing
```java
// Using lambda for simple processing
fileService.readFile("input.txt", line -> {
    if (line.startsWith("#")) {
        System.out.println("Comment: " + line);
    } else {
        System.out.println("Data: " + line);
    }
});
```

## Common Patterns

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
}

// Usage
ServiceFactory factory = new ServiceFactory();
DayOneService dayOne = factory.createDayOneService();
DayTwoService dayTwo = factory.createDayTwoService();
```

### Result Wrapper Pattern
```java
public class PuzzleResult {
    private final String puzzleName;
    private final Integer partOneResult;
    private final Integer partTwoResult;
    private final Exception error;
    
    public PuzzleResult(String puzzleName, Integer partOneResult, Integer partTwoResult) {
        this.puzzleName = puzzleName;
        this.partOneResult = partOneResult;
        this.partTwoResult = partTwoResult;
        this.error = null;
    }
    
    public PuzzleResult(String puzzleName, Exception error) {
        this.puzzleName = puzzleName;
        this.partOneResult = null;
        this.partTwoResult = null;
        this.error = error;
    }
    
    // Getters...
    public String getPuzzleName() { return puzzleName; }
    public Integer getPartOneResult() { return partOneResult; }
    public Integer getPartTwoResult() { return partTwoResult; }
    public Exception getError() { return error; }
    public boolean hasError() { return error != null; }
}

// Usage
public PuzzleResult solveDayOne(String inputPath) {
    try {
        DayOneService service = new DayOneService(new FileService());
        Integer partOne = service.partOne(inputPath);
        Integer partTwo = service.partTwo(inputPath);
        return new PuzzleResult("Day One", partOne, partTwo);
    } catch (Exception e) {
        return new PuzzleResult("Day One", e);
    }
}
```

### Batch Processing Pattern
```java
public class BatchProcessor {
    private final ServiceFactory factory;
    
    public BatchProcessor() {
        this.factory = new ServiceFactory();
    }
    
    public Map<String, PuzzleResult> processAllDays(String basePath) {
        Map<String, PuzzleResult> results = new HashMap<>();
        
        // Day One
        results.put("DayOne", solveDayOne(basePath + "/dayOne/input.txt"));
        
        // Day Two
        results.put("DayTwo", solveDayTwo(basePath + "/dayTwo/input.txt"));
        
        // Day Three
        results.put("DayThree", solveDayThree(basePath + "/dayThree/input.txt"));
        
        // Day Four
        results.put("DayFour", solveDayFour(basePath + "/dayFour/input.txt"));
        
        return results;
    }
    
    private PuzzleResult solveDayOne(String path) {
        try {
            DayOneService service = factory.createDayOneService();
            return new PuzzleResult("Day One", 
                service.partOne(path), 
                service.partTwo(path));
        } catch (Exception e) {
            return new PuzzleResult("Day One", e);
        }
    }
    
    // Similar methods for other days...
}
```

## Error Handling

### Try-Catch Pattern
```java
public class SafePuzzleSolver {
    
    public Optional<Integer> solveSafely(String inputPath, 
                                       Function<String, Integer> solver) {
        try {
            Integer result = solver.apply(inputPath);
            return Optional.of(result);
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
            return Optional.empty();
        } catch (RuntimeException e) {
            System.err.println("Processing error: " + e.getMessage());
            return Optional.empty();
        }
    }
    
    public void solveWithFallback(String inputPath, 
                                 Function<String, Integer> primarySolver,
                                 Function<String, Integer> fallbackSolver) {
        Optional<Integer> result = solveSafely(inputPath, primarySolver);
        
        if (result.isPresent()) {
            System.out.println("Primary result: " + result.get());
        } else {
            System.out.println("Trying fallback solver...");
            Optional<Integer> fallbackResult = solveSafely(inputPath, fallbackSolver);
            fallbackResult.ifPresent(r -> System.out.println("Fallback result: " + r));
        }
    }
}

// Usage
SafePuzzleSolver solver = new SafePuzzleSolver();
DayOneService dayOne = new DayOneService(new FileService());

solver.solveSafely("input.txt", dayOne::partOne)
    .ifPresent(result -> System.out.println("Result: " + result));
```

### Exception Handler Pattern
```java
public class PuzzleExceptionHandler {
    
    public interface PuzzleSolver {
        Integer solve(String path) throws IOException;
    }
    
    public Integer solveWithRetry(String path, PuzzleSolver solver, int maxRetries) {
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                return solver.solve(path);
            } catch (IOException e) {
                if (attempt == maxRetries) {
                    throw new RuntimeException("Failed after " + maxRetries + " attempts", e);
                }
                System.err.println("Attempt " + attempt + " failed, retrying...");
            }
        }
        throw new RuntimeException("Unexpected retry failure");
    }
}

// Usage
PuzzleExceptionHandler handler = new PuzzleExceptionHandler();
DayOneService dayOne = new DayOneService(new FileService());

try {
    Integer result = handler.solveWithRetry("input.txt", dayOne::partOne, 3);
    System.out.println("Result: " + result);
} catch (RuntimeException e) {
    System.err.println("All attempts failed: " + e.getMessage());
}
```

## Performance Tips

### Reuse FileService
```java
// Good: Reuse FileService instance
FileService fileService = new FileService();
DayOneService dayOne = new DayOneService(fileService);
DayTwoService dayTwo = new DayTwoService(fileService);
DayThreeService dayThree = new DayThreeService(fileService);
DayFourService dayFour = new DayFourService(fileService);

// Avoid: Creating new FileService for each service
DayOneService dayOne = new DayOneService(new FileService()); // Bad
DayTwoService dayTwo = new DayTwoService(new FileService()); // Bad
```

### Batch Processing
```java
// Process multiple files efficiently
public class BatchFileProcessor {
    private final FileService fileService;
    
    public BatchFileProcessor() {
        this.fileService = new FileService();
    }
    
    public void processFiles(String[] paths, ProcessLine processor) {
        for (String path : paths) {
            try {
                fileService.readFile(path, processor);
            } catch (IOException e) {
                System.err.println("Error processing " + path + ": " + e.getMessage());
            }
        }
    }
}
```

### Memory-Efficient Processing
```java
// Use streaming for large files
public class StreamingProcessor implements ProcessLine {
    private final Consumer<String> lineProcessor;
    private int processedCount = 0;
    
    public StreamingProcessor(Consumer<String> lineProcessor) {
        this.lineProcessor = lineProcessor;
    }
    
    @Override
    public void process(String line) {
        lineProcessor.accept(line);
        processedCount++;
        
        // Optional: Progress reporting
        if (processedCount % 1000 == 0) {
            System.out.println("Processed " + processedCount + " lines");
        }
    }
    
    public int getProcessedCount() {
        return processedCount;
    }
}

// Usage
StreamingProcessor processor = new StreamingProcessor(line -> {
    // Process each line without storing in memory
    if (line.contains("target")) {
        System.out.println("Found target: " + line);
    }
});

fileService.readFile("large-input.txt", processor);
System.out.println("Total processed: " + processor.getProcessedCount());
```

## Common Input Validation

```java
public class InputValidator {
    
    public static boolean isValidDayOneInput(String line) {
        if (line == null || line.trim().isEmpty()) {
            return false;
        }
        
        String[] parts = line.split("   ");
        if (parts.length != 2) {
            return false;
        }
        
        try {
            Integer.parseInt(parts[0].trim());
            Integer.parseInt(parts[1].trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isValidDayTwoInput(String line) {
        if (line == null || line.trim().isEmpty()) {
            return false;
        }
        
        String[] parts = line.split(" ");
        if (parts.length < 2) {
            return false;
        }
        
        try {
            for (String part : parts) {
                Integer.parseInt(part.trim());
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isValidDayThreeInput(String line) {
        if (line == null || line.trim().isEmpty()) {
            return false;
        }
        
        // Check for valid instructions
        String trimmed = line.trim();
        return trimmed.matches("(do\\(\\)|don't\\(\\)|mul\\(\\d+,\\d+\\))");
    }
    
    public static boolean isValidDayFourInput(String line, int expectedLength) {
        if (line == null || line.trim().isEmpty()) {
            return false;
        }
        
        String trimmed = line.trim();
        if (trimmed.length() != expectedLength) {
            return false;
        }
        
        // Check if line contains only valid characters
        return trimmed.matches("[A-Za-z]+");
    }
}
```

## Testing Patterns

```java
// Mock FileService for testing
public class MockFileService extends FileService {
    private final List<String> lines;
    
    public MockFileService(List<String> lines) {
        this.lines = lines;
    }
    
    @Override
    public void readFile(String path, ProcessLine processLine) throws IOException {
        for (String line : lines) {
            processLine.process(line);
        }
    }
}

// Test usage
@Test
public void testDayOneService() throws IOException {
    List<String> testData = Arrays.asList("1   5", "3   2", "7   1");
    MockFileService mockFileService = new MockFileService(testData);
    
    DayOneService service = new DayOneService(mockFileService);
    Integer result = service.partOne("dummy-path");
    
    assertEquals(expectedValue, result);
}
```