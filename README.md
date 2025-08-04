# Advent of Code 2024 - Java Solutions

This project contains Java solutions for Advent of Code 2024 challenges. The codebase is organized into service classes that handle different puzzle days, with utility classes for file operations.

## Table of Contents

- [Project Structure](#project-structure)
- [Core Components](#core-components)
- [Service Classes](#service-classes)
- [Utility Classes](#utility-classes)
- [Usage Examples](#usage-examples)
- [API Documentation](#api-documentation)

## Project Structure

```
src/main/java/io/github/lucasfreitasrocha/
├── App.java                    # Main application entry point
├── service/                    # Puzzle solution services
│   ├── DayOneService.java     # Day 1 puzzle solutions
│   ├── DayTwoService.java     # Day 2 puzzle solutions
│   ├── DayThreeService.java   # Day 3 puzzle solutions
│   ├── DayFourService.java    # Day 4 puzzle solutions
│   └── ProcessLine.java       # Interface for line processing
└── util/                      # Utility classes
    └── FileService.java       # File reading utility
```

## Core Components

### Main Application (`App.java`)

The main entry point for the application that demonstrates how to use each service.

**Location:** `src/main/java/io/github/lucasfreitasrocha/App.java`

**Purpose:** Demonstrates usage of all service classes and provides examples of running puzzle solutions.

**Key Features:**
- Shows how to instantiate service classes
- Demonstrates both test and input file usage
- Provides examples for all puzzle parts

## Service Classes

### DayOneService

**Location:** `src/main/java/io/github/lucasfreitasrocha/service/DayOneService.java`

**Purpose:** Solves Day 1 puzzles involving list processing and mathematical operations.

#### Public Methods

##### `partOne(String path) throws IOException`
- **Purpose:** Solves the first part of Day 1 puzzle
- **Parameters:** 
  - `path` (String): Path to the input file
- **Returns:** Integer representing the solution
- **Throws:** IOException if file cannot be read
- **Algorithm:** Calculates the sum of absolute differences between corresponding elements in two sorted lists

**Example:**
```java
DayOneService dayOneService = new DayOneService(new FileService());
Integer result = dayOneService.partOne("src/main/resources/dayOne/input.txt");
System.out.println("Part 1 result: " + result);
```

##### `partTwo(String path) throws IOException`
- **Purpose:** Solves the second part of Day 1 puzzle
- **Parameters:** 
  - `path` (String): Path to the input file
- **Returns:** Integer representing the solution
- **Throws:** IOException if file cannot be read
- **Algorithm:** Calculates the sum of products between left list values and their frequency in the right list

**Example:**
```java
DayOneService dayOneService = new DayOneService(new FileService());
Integer result = dayOneService.partTwo("src/main/resources/dayOne/input.txt");
System.out.println("Part 2 result: " + result);
```

#### Input Format
The service expects input files with lines containing two integers separated by three spaces:
```
1   5
3   2
7   1
```

### DayTwoService

**Location:** `src/main/java/io/github/lucasfreitasrocha/service/DayTwoService.java`

**Purpose:** Solves Day 2 puzzles involving report validation and error tolerance.

#### Public Methods

##### `partOne(String path) throws IOException`
- **Purpose:** Solves the first part of Day 2 puzzle
- **Parameters:** 
  - `path` (String): Path to the input file
- **Returns:** Integer representing the number of safe reports
- **Throws:** IOException if file cannot be read
- **Algorithm:** Validates reports based on ascending/descending order and maximum difference constraints

**Example:**
```java
DayTwoService dayTwoService = new DayTwoService(new FileService());
Integer result = dayTwoService.partOne("src/main/resources/dayTwo/input.txt");
System.out.println("Safe reports: " + result);
```

##### `partTwo(String path) throws IOException`
- **Purpose:** Solves the second part of Day 2 puzzle with error tolerance
- **Parameters:** 
  - `path` (String): Path to the input file
- **Returns:** Integer representing the number of safe reports after error correction
- **Throws:** IOException if file cannot be read
- **Algorithm:** Attempts to remove one element at a time to find a valid report configuration

**Example:**
```java
DayTwoService dayTwoService = new DayTwoService(new FileService());
Integer result = dayTwoService.partTwo("src/main/resources/dayTwo/input.txt");
System.out.println("Safe reports with error tolerance: " + result);
```

#### Input Format
The service expects input files with lines containing space-separated integers:
```
1 3 5 7 9
9 7 5 3 1
1 2 4 8 16
```

### DayThreeService

**Location:** `src/main/java/io/github/lucasfreitasrocha/service/DayThreeService.java`

**Purpose:** Solves Day 3 puzzles involving pattern matching and instruction processing.

#### Public Methods

##### `partOne(String path) throws IOException`
- **Purpose:** Solves the first part of Day 3 puzzle
- **Parameters:** 
  - `path` (String): Path to the input file
- **Returns:** Integer representing the calculated result
- **Throws:** IOException if file cannot be read
- **Algorithm:** Processes multiplication operations without instruction control

**Example:**
```java
DayThreeService dayThreeService = new DayThreeService(new FileService());
Integer result = dayThreeService.partOne("src/main/resources/dayThree/input.txt");
System.out.println("Part 1 result: " + result);
```

##### `partTwo(String path) throws IOException`
- **Purpose:** Solves the second part of Day 3 puzzle with instruction control
- **Parameters:** 
  - `path` (String): Path to the input file
- **Returns:** Integer representing the calculated result
- **Throws:** IOException if file cannot be read
- **Algorithm:** Processes multiplication operations with enable/disable instruction control

**Example:**
```java
DayThreeService dayThreeService = new DayThreeService(new FileService());
Integer result = dayThreeService.partTwo("src/main/resources/dayThree/input.txt");
System.out.println("Part 2 result: " + result);
```

#### Supported Instructions
- `do()`: Enables processing
- `don't()`: Disables processing
- `mul(x,y)`: Multiplies two numbers (only processed when enabled in part 2)

#### Input Format
The service expects input files with lines containing instructions:
```
mul(2,3)
do()
mul(4,5)
don't()
mul(1,10)
```

### DayFourService

**Location:** `src/main/java/io/github/lucasfreitasrocha/service/DayFourService.java`

**Purpose:** Solves Day 4 puzzles involving grid pattern matching and word search.

#### Public Methods

##### `partOne(String path, boolean test) throws IOException`
- **Purpose:** Solves the first part of Day 4 puzzle
- **Parameters:** 
  - `path` (String): Path to the input file
  - `test` (boolean): Whether using test data (affects grid size)
- **Returns:** Integer representing the number of found patterns
- **Throws:** IOException if file cannot be read
- **Algorithm:** Searches for the word "XMAS" in all 8 directions on the grid

**Example:**
```java
DayFourService dayFourService = new DayFourService(new FileService());
Integer result = dayFourService.partOne("src/main/resources/dayFour/input.txt", false);
System.out.println("Part 1 result: " + result);
```

##### `partTwo(String path, boolean test) throws IOException`
- **Purpose:** Solves the second part of Day 4 puzzle
- **Parameters:** 
  - `path` (String): Path to the input file
  - `test` (boolean): Whether using test data (affects grid size)
- **Returns:** Integer representing the number of found patterns
- **Throws:** IOException if file cannot be read
- **Algorithm:** Searches for specific "XMAS" patterns with specific arrangements

**Example:**
```java
DayFourService dayFourService = new DayFourService(new FileService());
Integer result = dayFourService.partTwo("src/main/resources/dayFour/input.txt", false);
System.out.println("Part 2 result: " + result);
```

#### Grid Configuration
- **Test mode:** 10x10 grid
- **Production mode:** 140x140 grid

#### Input Format
The service expects input files with lines representing grid rows:
```
XMASXMASXX
MASXMASXMA
SXMASXMASX
MASXMASXMA
```

## Utility Classes

### FileService

**Location:** `src/main/java/io/github/lucasfreitasrocha/util/FileService.java`

**Purpose:** Provides file reading functionality with line-by-line processing.

#### Public Methods

##### `readFile(String path, ProcessLine processLine) throws IOException`
- **Purpose:** Reads a file line by line and processes each line
- **Parameters:** 
  - `path` (String): Path to the file to read
  - `processLine` (ProcessLine): Interface implementation for line processing
- **Throws:** IOException if file cannot be found or read
- **Features:** 
  - Automatic resource management with try-with-resources
  - Line-by-line processing for memory efficiency
  - Null check for file existence

**Example:**
```java
FileService fileService = new FileService();
fileService.readFile("input.txt", new ProcessLine() {
    @Override
    public void process(String line) {
        System.out.println("Processing: " + line);
    }
});
```

### ProcessLine Interface

**Location:** `src/main/java/io/github/lucasfreitasrocha/service/ProcessLine.java`

**Purpose:** Defines the contract for line processing implementations.

#### Methods

##### `process(String line)`
- **Purpose:** Processes a single line from a file
- **Parameters:** 
  - `line` (String): The line to process
- **Returns:** void
- **Usage:** Implement this method to define custom line processing logic

**Example Implementation:**
```java
public class MyProcessor implements ProcessLine {
    @Override
    public void process(String line) {
        // Custom processing logic here
        System.out.println("Processed: " + line);
    }
}
```

## Usage Examples

### Running Day One Solutions

```java
import io.github.lucasfreitasrocha.service.DayOneService;
import io.github.lucasfreitasrocha.util.FileService;

public class Example {
    public static void main(String[] args) throws IOException {
        DayOneService dayOneService = new DayOneService(new FileService());
        
        // Part 1
        Integer partOneResult = dayOneService.partOne("src/main/resources/dayOne/input.txt");
        System.out.println("Day 1 Part 1: " + partOneResult);
        
        // Part 2
        Integer partTwoResult = dayOneService.partTwo("src/main/resources/dayOne/input.txt");
        System.out.println("Day 1 Part 2: " + partTwoResult);
    }
}
```

### Running Day Two Solutions

```java
import io.github.lucasfreitasrocha.service.DayTwoService;
import io.github.lucasfreitasrocha.util.FileService;

public class Example {
    public static void main(String[] args) throws IOException {
        DayTwoService dayTwoService = new DayTwoService(new FileService());
        
        // Part 1
        Integer partOneResult = dayTwoService.partOne("src/main/resources/dayTwo/input.txt");
        System.out.println("Day 2 Part 1: " + partOneResult);
        
        // Part 2
        Integer partTwoResult = dayTwoService.partTwo("src/main/resources/dayTwo/input.txt");
        System.out.println("Day 2 Part 2: " + partTwoResult);
    }
}
```

### Running Day Three Solutions

```java
import io.github.lucasfreitasrocha.service.DayThreeService;
import io.github.lucasfreitasrocha.util.FileService;

public class Example {
    public static void main(String[] args) throws IOException {
        DayThreeService dayThreeService = new DayThreeService(new FileService());
        
        // Part 1
        Integer partOneResult = dayThreeService.partOne("src/main/resources/dayThree/input.txt");
        System.out.println("Day 3 Part 1: " + partOneResult);
        
        // Part 2
        Integer partTwoResult = dayThreeService.partTwo("src/main/resources/dayThree/input.txt");
        System.out.println("Day 3 Part 2: " + partTwoResult);
    }
}
```

### Running Day Four Solutions

```java
import io.github.lucasfreitasrocha.service.DayFourService;
import io.github.lucasfreitasrocha.util.FileService;

public class Example {
    public static void main(String[] args) throws IOException {
        DayFourService dayFourService = new DayFourService(new FileService());
        
        // Part 1
        Integer partOneResult = dayFourService.partOne("src/main/resources/dayFour/input.txt", false);
        System.out.println("Day 4 Part 1: " + partOneResult);
        
        // Part 2
        Integer partTwoResult = dayFourService.partTwo("src/main/resources/dayFour/input.txt", false);
        System.out.println("Day 4 Part 2: " + partTwoResult);
    }
}
```

### Custom File Processing

```java
import io.github.lucasfreitasrocha.util.FileService;
import io.github.lucasfreitasrocha.service.ProcessLine;

public class CustomProcessor implements ProcessLine {
    private int lineCount = 0;
    
    @Override
    public void process(String line) {
        lineCount++;
        System.out.println("Line " + lineCount + ": " + line);
    }
    
    public int getLineCount() {
        return lineCount;
    }
}

public class Example {
    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService();
        CustomProcessor processor = new CustomProcessor();
        
        fileService.readFile("input.txt", processor);
        System.out.println("Total lines processed: " + processor.getLineCount());
    }
}
```

## API Documentation

### Package: `io.github.lucasfreitasrocha`

#### Class: `App`
- **Purpose:** Main application entry point
- **Methods:** `main(String[] args)`
- **Dependencies:** All service classes and FileService

### Package: `io.github.lucasfreitasrocha.service`

#### Interface: `ProcessLine`
- **Purpose:** Contract for line processing implementations
- **Methods:** `process(String line)`

#### Class: `DayOneService`
- **Dependencies:** FileService
- **Implements:** ProcessLine
- **Methods:** `partOne(String path)`, `partTwo(String path)`

#### Class: `DayTwoService`
- **Dependencies:** FileService
- **Implements:** ProcessLine
- **Methods:** `partOne(String path)`, `partTwo(String path)`

#### Class: `DayThreeService`
- **Dependencies:** FileService
- **Implements:** ProcessLine
- **Methods:** `partOne(String path)`, `partTwo(String path)`

#### Class: `DayFourService`
- **Dependencies:** FileService
- **Implements:** ProcessLine
- **Methods:** `partOne(String path, boolean test)`, `partTwo(String path, boolean test)`

### Package: `io.github.lucasfreitasrocha.util`

#### Class: `FileService`
- **Dependencies:** ProcessLine interface
- **Methods:** `readFile(String path, ProcessLine processLine)`

## Technical Details

### Java Version
- **Minimum Version:** Java 17
- **Compilation Target:** Java 17
- **Encoding:** UTF-8

### Dependencies
- **Build Tool:** Maven
- **External Dependencies:** None (uses only Java standard library)

### Error Handling
- All file operations throw `IOException` for proper error handling
- Service classes include validation for input data
- FileService includes null checks for file existence

### Performance Considerations
- Line-by-line processing for memory efficiency
- Automatic resource management with try-with-resources
- Efficient algorithms for puzzle solutions

## Contributing

To add new puzzle solutions:
1. Create a new service class implementing `ProcessLine`
2. Add the service to the main `App.java` for testing
3. Create appropriate input files in the resources directory
4. Update this documentation with the new service details