# Advent of Code 2024 - API Documentation

## Project Overview

This is a Java 17 Maven project containing solutions for Advent of Code 2024 challenges (Days 1-4). The project follows a service-oriented architecture with utility classes for file processing and a common interface for line processing.

**Project Details:**
- **Group ID:** `io.github.lucasfreitasrocha`
- **Artifact ID:** `adventofcode`
- **Version:** `1.0-SNAPSHOT`
- **Java Version:** 17

## Package Structure

```
io.github.lucasfreitasrocha
├── App.java                    # Main application entry point
├── service/                    # Service classes for each day's solution
│   ├── ProcessLine.java        # Interface for line processing
│   ├── DayOneService.java      # Day 1 solution service
│   ├── DayTwoService.java      # Day 2 solution service
│   ├── DayThreeService.java    # Day 3 solution service
│   └── DayFourService.java     # Day 4 solution service
└── util/                       # Utility classes
    └── FileService.java        # File reading utility
```

---

## Interfaces

### ProcessLine Interface

**Package:** `io.github.lucasfreitasrocha.service`

A functional interface for processing individual lines from input files.

#### Methods

```java
void process(String line)
```

**Description:** Processes a single line of input data.

**Parameters:**
- `line` (String): The line of text to process

**Usage Example:**
```java
ProcessLine processor = line -> System.out.println("Processing: " + line);
processor.process("Sample line");
```

---

## Utility Classes

### FileService Class

**Package:** `io.github.lucasfreitasrocha.util`

A utility class for reading files line by line and processing them using the ProcessLine interface.

#### Constructor

```java
public FileService()
```

**Description:** Creates a new FileService instance.

#### Methods

```java
public void readFile(String path, ProcessLine processLine) throws IOException
```

**Description:** Reads a file line by line and processes each line using the provided ProcessLine implementation.

**Parameters:**
- `path` (String): The file path to read from
- `processLine` (ProcessLine): The processor to handle each line

**Throws:**
- `IOException`: If the file is not found or cannot be read

**Usage Example:**
```java
FileService fileService = new FileService();
fileService.readFile("input.txt", line -> {
    // Process each line
    System.out.println("Line: " + line);
});
```

---

## Service Classes

### DayOneService Class

**Package:** `io.github.lucasfreitasrocha.service`

Solves Advent of Code Day 1: Historian Hysteria. Processes two lists of integers and calculates distances and similarity scores.

#### Constructor

```java
public DayOneService(FileService fileService)
```

**Description:** Creates a new DayOneService with the specified FileService dependency.

**Parameters:**
- `fileService` (FileService): The file service for reading input files

#### Public Methods

```java
public Integer partOne(String path) throws IOException
```

**Description:** Solves part one by calculating the total distance between paired elements from two sorted lists.

**Parameters:**
- `path` (String): Path to the input file containing space-separated integer pairs

**Returns:** Integer - The sum of absolute differences between paired elements

**Input Format:** Each line contains two integers separated by three spaces:
```
3   4
4   3
2   5
```

**Usage Example:**
```java
DayOneService service = new DayOneService(new FileService());
Integer result = service.partOne("src/main/resources/dayOne/input.txt");
System.out.println("Total distance: " + result);
```

```java
public Integer partTwo(String path) throws IOException
```

**Description:** Solves part two by calculating a similarity score based on frequency of left list numbers in the right list.

**Parameters:**
- `path` (String): Path to the input file containing space-separated integer pairs

**Returns:** Integer - The similarity score (sum of left numbers multiplied by their frequency in right list)

**Usage Example:**
```java
DayOneService service = new DayOneService(new FileService());
Integer similarity = service.partTwo("src/main/resources/dayOne/input.txt");
System.out.println("Similarity score: " + similarity);
```

---

### DayTwoService Class

**Package:** `io.github.lucasfreitasrocha.service`

Solves Advent of Code Day 2: Red-Nosed Reports. Analyzes sequences of numbers to determine if they represent "safe" reports.

#### Constructor

```java
public DayTwoService(FileService fileService)
```

**Description:** Creates a new DayTwoService with the specified FileService dependency.

**Parameters:**
- `fileService` (FileService): The file service for reading input files

#### Public Methods

```java
public Integer partOne(String path) throws IOException
```

**Description:** Counts the number of safe reports. A report is safe if it's either all increasing or all decreasing, and adjacent levels differ by at least 1 and at most 3.

**Parameters:**
- `path` (String): Path to the input file containing space-separated integer sequences

**Returns:** Integer - The number of safe reports

**Input Format:** Each line contains space-separated integers:
```
7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
```

**Usage Example:**
```java
DayTwoService service = new DayTwoService(new FileService());
Integer safeCount = service.partOne("src/main/resources/dayTwo/input.txt");
System.out.println("Safe reports: " + safeCount);
```

```java
public Integer partTwo(String path) throws IOException
```

**Description:** Counts safe reports with error tolerance. A report is safe if removing at most one level makes it satisfy the safety criteria.

**Parameters:**
- `path` (String): Path to the input file containing space-separated integer sequences

**Returns:** Integer - The number of safe reports with error tolerance

**Usage Example:**
```java
DayTwoService service = new DayTwoService(new FileService());
Integer tolerantSafeCount = service.partTwo("src/main/resources/dayTwo/input.txt");
System.out.println("Safe reports (with tolerance): " + tolerantSafeCount);
```

---

### DayThreeService Class

**Package:** `io.github.lucasfreitasrocha.service`

Solves Advent of Code Day 3: Mull It Over. Parses corrupted memory to find and execute valid multiplication instructions.

#### Constructor

```java
public DayThreeService(FileService fileService)
```

**Description:** Creates a new DayThreeService with the specified FileService dependency.

**Parameters:**
- `fileService` (FileService): The file service for reading input files

#### Public Methods

```java
public Integer partOne(String path) throws IOException
```

**Description:** Finds all valid `mul(x,y)` instructions and sums their results.

**Parameters:**
- `path` (String): Path to the input file containing corrupted memory data

**Returns:** Integer - The sum of all multiplication results

**Pattern Matched:** `mul(x,y)` where x and y are 1-3 digit numbers

**Usage Example:**
```java
DayThreeService service = new DayThreeService(new FileService());
Integer sum = service.partOne("src/main/resources/dayThree/input.txt");
System.out.println("Sum of multiplications: " + sum);
```

```java
public Integer partTwo(String path) throws IOException
```

**Description:** Processes multiplication instructions with conditional execution based on `do()` and `don't()` instructions.

**Parameters:**
- `path` (String): Path to the input file containing corrupted memory data

**Returns:** Integer - The sum of enabled multiplication results

**Pattern Matched:** 
- `mul(x,y)` - multiplication instruction
- `do()` - enable future multiplications
- `don't()` - disable future multiplications

**Usage Example:**
```java
DayThreeService service = new DayThreeService(new FileService());
Integer conditionalSum = service.partTwo("src/main/resources/dayThree/input.txt");
System.out.println("Conditional sum: " + conditionalSum);
```

---

### DayFourService Class

**Package:** `io.github.lucasfreitasrocha.service`

Solves Advent of Code Day 4: Ceres Search. Searches for word patterns in a 2D character grid.

#### Constructor

```java
public DayFourService(FileService fileService)
```

**Description:** Creates a new DayFourService with the specified FileService dependency.

**Parameters:**
- `fileService` (FileService): The file service for reading input files

#### Public Methods

```java
public Integer partOne(String path, boolean test) throws IOException
```

**Description:** Counts occurrences of the word "XMAS" in all eight directions (horizontal, vertical, and diagonal).

**Parameters:**
- `path` (String): Path to the input file containing the character grid
- `test` (boolean): If true, uses 10x10 grid size; if false, uses 140x140 grid size

**Returns:** Integer - The total count of "XMAS" occurrences

**Input Format:** Grid of characters, one row per line:
```
MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
```

**Usage Example:**
```java
DayFourService service = new DayFourService(new FileService());
Integer xmasCount = service.partOne("src/main/resources/dayFour/input.txt", false);
System.out.println("XMAS occurrences: " + xmasCount);
```

```java
public Integer partTwo(String path, boolean test) throws IOException
```

**Description:** Counts X-MAS patterns (MAS in X-shape) in the grid.

**Parameters:**
- `path` (String): Path to the input file containing the character grid
- `test` (boolean): If true, uses 10x10 grid size; if false, uses 140x140 grid size

**Returns:** Integer - The total count of X-MAS patterns

**Pattern:** Two "MAS" strings forming an X shape with 'A' at the center:
```
M.S    S.M    M.M    S.S
.A. or .A. or .A. or .A.
M.S    S.M    S.S    M.M
```

**Usage Example:**
```java
DayFourService service = new DayFourService(new FileService());
Integer xmasPatterns = service.partTwo("src/main/resources/dayFour/input.txt", false);
System.out.println("X-MAS patterns: " + xmasPatterns);
```

---

## Main Application

### App Class

**Package:** `io.github.lucasfreitasrocha`

The main application entry point that demonstrates usage of the various day services.

#### Methods

```java
public static void main(String[] args) throws IOException
```

**Description:** Main method that runs solutions for different days. Currently configured to run Day 4 solutions.

**Usage Example:**
```java
// Run the application
java -cp target/classes io.github.lucasfreitasrocha.App
```

**Current Configuration:** The main method is set up to run Day 4 solutions with both test and actual input files.

---

## Usage Patterns

### Basic Service Usage Pattern

```java
// 1. Create FileService instance
FileService fileService = new FileService();

// 2. Create specific day service
DayOneService dayOne = new DayOneService(fileService);

// 3. Run solutions
Integer partOneResult = dayOne.partOne("path/to/input.txt");
Integer partTwoResult = dayOne.partTwo("path/to/input.txt");
```

### Custom Line Processing

```java
FileService fileService = new FileService();

// Custom processor
ProcessLine customProcessor = line -> {
    // Your custom processing logic
    System.out.println("Processing: " + line);
};

fileService.readFile("input.txt", customProcessor);
```

### Error Handling

```java
try {
    DayOneService service = new DayOneService(new FileService());
    Integer result = service.partOne("nonexistent.txt");
} catch (IOException e) {
    System.err.println("File error: " + e.getMessage());
}
```

---

## Input File Formats

### Day 1 Format
```
3   4
4   3
2   5
1   3
3   9
3   3
```
Two integers per line separated by three spaces.

### Day 2 Format
```
7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 6 7 9
```
Space-separated integers representing level sequences.

### Day 3 Format
```
xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
```
Corrupted memory containing valid mul(x,y), do(), and don't() instructions.

### Day 4 Format
```
MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
```
Grid of characters for word search puzzles.

---

## Build and Run Instructions

### Prerequisites
- Java 17 or later
- Maven 3.6 or later

### Build
```bash
mvn clean compile
```

### Run
```bash
mvn exec:java -Dexec.mainClass="io.github.lucasfreitasrocha.App"
```

### Run Tests (if applicable)
```bash
mvn test
```

---

## Architecture Notes

1. **Dependency Injection**: Services accept FileService as a constructor parameter, enabling easy testing and modularity.

2. **Strategy Pattern**: The ProcessLine interface allows different processing strategies for each day's requirements.

3. **Immutable Records**: Day 1 uses a private record for data transfer, promoting immutability.

4. **Resource Management**: FileService properly handles resource cleanup with try-with-resources.

5. **Error Handling**: Appropriate exception handling for file operations and validation.

This documentation covers all public APIs, methods, and usage patterns for the Advent of Code 2024 Java project.