# API Documentation

This document provides comprehensive technical documentation for all public APIs, classes, and methods in the Advent of Code 2024 Java solutions.

## Package: `io.github.lucasfreitasrocha`

### Class: `App`

**Package:** `io.github.lucasfreitasrocha`

**Description:** Main application entry point that demonstrates usage of all service classes.

**Constructor:**
- `App()` - Default constructor

**Methods:**
```java
/**
 * Main method that demonstrates usage of all puzzle services.
 * 
 * @param args Command line arguments (not used)
 * @throws IOException if any file operations fail
 */
public static void main(String[] args) throws IOException
```

**Dependencies:**
- `DayFourService`
- `FileService`

---

## Package: `io.github.lucasfreitasrocha.service`

### Interface: `ProcessLine`

**Package:** `io.github.lucasfreitasrocha.service`

**Description:** Functional interface for processing individual lines from input files.

**Methods:**
```java
/**
 * Processes a single line from an input file.
 * 
 * @param line The line to process
 */
void process(String line)
```

**Usage:** Implement this interface to define custom line processing logic for file operations.

---

### Class: `DayOneService`

**Package:** `io.github.lucasfreitasrocha.service`

**Description:** Service class for solving Day 1 puzzles involving list processing and mathematical operations.

**Implements:** `ProcessLine`

**Constructor:**
```java
/**
 * Creates a new DayOneService with the specified FileService.
 * 
 * @param fileService The FileService to use for file operations
 */
public DayOneService(FileService fileService)
```

**Public Methods:**

```java
/**
 * Solves the first part of Day 1 puzzle.
 * 
 * Calculates the sum of absolute differences between corresponding elements 
 * in two sorted lists. The input file should contain lines with two integers 
 * separated by three spaces.
 * 
 * @param path Path to the input file
 * @return Integer representing the solution
 * @throws IOException if the file cannot be read
 * @throws RuntimeException if the left and right lists have different sizes
 */
public Integer partOne(String path) throws IOException
```

```java
/**
 * Solves the second part of Day 1 puzzle.
 * 
 * Calculates the sum of products between left list values and their frequency 
 * in the right list. The input file should contain lines with two integers 
 * separated by three spaces.
 * 
 * @param path Path to the input file
 * @return Integer representing the solution
 * @throws IOException if the file cannot be read
 * @throws RuntimeException if the left and right lists have different sizes
 */
public Integer partTwo(String path) throws IOException
```

**Private Methods:**

```java
/**
 * Builds and sorts the left and right lists from the input file.
 * 
 * @param path Path to the input file
 * @return Lists object containing sorted left and right lists
 * @throws IOException if the file cannot be read
 * @throws RuntimeException if the left and right lists have different sizes
 */
private Lists buildAndSort(String path) throws IOException
```

```java
/**
 * Processes a single line from the input file.
 * 
 * Splits the line by three spaces and adds the first value to the left list
 * and the second value to the right list.
 * 
 * @param line The line to process
 */
@Override
public void process(String line)
```

**Private Record:**
```java
/**
 * Record to hold left and right lists.
 * 
 * @param left The left list of integers
 * @param right The right list of integers
 */
private record Lists(List<Integer> left, List<Integer> right)
```

**Input Format:**
```
1   5
3   2
7   1
```

---

### Class: `DayTwoService`

**Package:** `io.github.lucasfreitasrocha.service`

**Description:** Service class for solving Day 2 puzzles involving report validation and error tolerance.

**Implements:** `ProcessLine`

**Constructor:**
```java
/**
 * Creates a new DayTwoService with the specified FileService.
 * 
 * @param fileService The FileService to use for file operations
 */
public DayTwoService(FileService fileService)
```

**Public Methods:**

```java
/**
 * Solves the first part of Day 2 puzzle.
 * 
 * Validates reports based on ascending/descending order and maximum difference 
 * constraints. A report is considered safe if it is either strictly ascending, 
 * strictly descending, and all adjacent elements have a difference of 3 or less.
 * 
 * @param path Path to the input file
 * @return Integer representing the number of safe reports
 * @throws IOException if the file cannot be read
 */
public Integer partOne(String path) throws IOException
```

```java
/**
 * Solves the second part of Day 2 puzzle with error tolerance.
 * 
 * Attempts to remove one element at a time to find a valid report configuration.
 * A report is considered safe if removing one element results in a valid report
 * according to the same criteria as part one.
 * 
 * @param path Path to the input file
 * @return Integer representing the number of safe reports after error correction
 * @throws IOException if the file cannot be read
 */
public Integer partTwo(String path) throws IOException
```

**Private Methods:**

```java
/**
 * Processes a single line from the input file.
 * 
 * Splits the line by spaces and validates the report according to the current
 * error tolerance setting.
 * 
 * @param line The line to process
 */
@Override
public void process(String line)
```

```java
/**
 * Verifies if a report is safe according to the validation criteria.
 * 
 * A report is safe if it is either strictly ascending or strictly descending,
 * and all adjacent elements have a difference of 3 or less.
 * 
 * @param report List of integers representing the report
 * @return true if the report is safe, false otherwise
 */
private boolean verifyIfReportIsSafe(List<String> report)
```

**Input Format:**
```
1 3 5 7 9
9 7 5 3 1
1 2 4 8 16
```

---

### Class: `DayThreeService`

**Package:** `io.github.lucasfreitasrocha.service`

**Description:** Service class for solving Day 3 puzzles involving pattern matching and instruction processing.

**Implements:** `ProcessLine`

**Constructor:**
```java
/**
 * Creates a new DayThreeService with the specified FileService.
 * 
 * @param fileService The FileService to use for file operations
 */
public DayThreeService(FileService fileService)
```

**Public Methods:**

```java
/**
 * Solves the first part of Day 3 puzzle.
 * 
 * Processes multiplication operations without instruction control.
 * All mul(x,y) operations are processed and their results summed.
 * 
 * @param path Path to the input file
 * @return Integer representing the calculated result
 * @throws IOException if the file cannot be read
 */
public Integer partOne(String path) throws IOException
```

```java
/**
 * Solves the second part of Day 3 puzzle with instruction control.
 * 
 * Processes multiplication operations with enable/disable instruction control.
 * Only mul(x,y) operations are processed when enabled by do() instruction.
 * 
 * @param path Path to the input file
 * @return Integer representing the calculated result
 * @throws IOException if the file cannot be read
 */
public Integer partTwo(String path) throws IOException
```

**Private Methods:**

```java
/**
 * Processes a single line from the input file.
 * 
 * Uses regex pattern matching to find and process instructions:
 * - do(): Enables processing
 * - don't(): Disables processing
 * - mul(x,y): Multiplies two numbers
 * 
 * @param line The line to process
 */
@Override
public void process(String line)
```

**Supported Instructions:**
- `do()`: Enables processing
- `don't()`: Disables processing
- `mul(x,y)`: Multiplies two numbers (only processed when enabled in part 2)

**Input Format:**
```
mul(2,3)
do()
mul(4,5)
don't()
mul(1,10)
```

---

### Class: `DayFourService`

**Package:** `io.github.lucasfreitasrocha.service`

**Description:** Service class for solving Day 4 puzzles involving grid pattern matching and word search.

**Implements:** `ProcessLine`

**Constructor:**
```java
/**
 * Creates a new DayFourService with the specified FileService.
 * 
 * @param fileService The FileService to use for file operations
 */
public DayFourService(FileService fileService)
```

**Public Methods:**

```java
/**
 * Solves the first part of Day 4 puzzle.
 * 
 * Searches for the word "XMAS" in all 8 directions on the grid.
 * The grid size is determined by the test parameter.
 * 
 * @param path Path to the input file
 * @param test Whether using test data (affects grid size: 10x10 for test, 140x140 for production)
 * @return Integer representing the number of found patterns
 * @throws IOException if the file cannot be read
 */
public Integer partOne(String path, boolean test) throws IOException
```

```java
/**
 * Solves the second part of Day 4 puzzle.
 * 
 * Searches for specific "XMAS" patterns with specific arrangements.
 * The grid size is determined by the test parameter.
 * 
 * @param path Path to the input file
 * @param test Whether using test data (affects grid size: 10x10 for test, 140x140 for production)
 * @return Integer representing the number of found patterns
 * @throws IOException if the file cannot be read
 */
public Integer partTwo(String path, boolean test) throws IOException
```

**Public Methods (Grid Checking):**

```java
/**
 * Checks for specific "XMAS" patterns in part two.
 * 
 * Validates specific arrangements of the word "XMAS" around a central 'A'.
 * 
 * @param x X coordinate in the grid
 * @param y Y coordinate in the grid
 * @param position Position in the word (0=X, 1=M, 2=A, 3=S)
 * @return true if the pattern is found, false otherwise
 */
public boolean checkGridPartTwo(int x, int y, int position)
```

**Private Methods:**

```java
/**
 * Recursively checks for the word "XMAS" in a specific direction.
 * 
 * @param x Current X coordinate
 * @param y Current Y coordinate
 * @param xDirection X direction vector
 * @param yDirection Y direction vector
 * @param position Current position in the word
 * @return true if the complete word is found, false otherwise
 */
private boolean checkGrid(int x, int y, int xDirection, int yDirection, int position)
```

```java
/**
 * Checks if a specific position contains the expected letter.
 * 
 * @param x X coordinate in the grid
 * @param y Y coordinate in the grid
 * @param position Position in the word (0=X, 1=M, 2=A, 3=S)
 * @return true if the letter matches, false otherwise
 */
private boolean isTheLetter(int x, int y, int position)
```

```java
/**
 * Processes a single line from the input file.
 * 
 * Splits the line into individual characters and stores them in the grid.
 * 
 * @param line The line to process
 */
@Override
public void process(String line)
```

**Grid Configuration:**
- **Test mode:** 10x10 grid
- **Production mode:** 140x140 grid

**Input Format:**
```
XMASXMASXX
MASXMASXMA
SXMASXMASX
MASXMASXMA
```

---

## Package: `io.github.lucasfreitasrocha.util`

### Class: `FileService`

**Package:** `io.github.lucasfreitasrocha.util`

**Description:** Utility class for file reading operations with line-by-line processing.

**Constructor:**
```java
/**
 * Creates a new FileService instance.
 */
public FileService()
```

**Public Methods:**

```java
/**
 * Reads a file line by line and processes each line using the provided ProcessLine implementation.
 * 
 * Uses automatic resource management with try-with-resources for efficient file handling.
 * Includes null check for file existence and proper exception handling.
 * 
 * @param path Path to the file to read
 * @param processLine Implementation of ProcessLine interface for line processing
 * @throws IOException if the file cannot be found or read
 */
public void readFile(String path, ProcessLine processLine) throws IOException
```

**Features:**
- Automatic resource management with try-with-resources
- Line-by-line processing for memory efficiency
- Null check for file existence
- Proper exception handling

**Example Usage:**
```java
FileService fileService = new FileService();
fileService.readFile("input.txt", new ProcessLine() {
    @Override
    public void process(String line) {
        System.out.println("Processing: " + line);
    }
});
```

---

## Error Handling

### Exceptions

**IOException:**
- Thrown by all file operations when files cannot be found or read
- Includes descriptive error messages with file paths

**RuntimeException:**
- Thrown by `DayOneService` when left and right lists have different sizes
- Includes descriptive error message

### Error Messages

- `"File not Found: {path}"` - When file cannot be found
- `"the list has different size"` - When lists in DayOneService have mismatched sizes

---

## Performance Considerations

### Memory Efficiency
- Line-by-line processing prevents loading entire files into memory
- Automatic resource management ensures proper cleanup
- Efficient data structures for puzzle solutions

### Algorithm Complexity
- **DayOneService:** O(n log n) due to sorting operations
- **DayTwoService:** O(n²) in worst case for part two due to element removal attempts
- **DayThreeService:** O(n) for pattern matching with regex
- **DayFourService:** O(n²) for grid traversal in all directions

### Resource Management
- Try-with-resources ensures proper file handle cleanup
- Scanner automatically closes underlying streams
- No memory leaks from unclosed resources

---

## Thread Safety

**Note:** The current implementation is not thread-safe. Service classes maintain state during processing and should not be shared between threads without proper synchronization.

### Thread-Safe Usage
```java
// Create separate instances for each thread
DayOneService service1 = new DayOneService(new FileService());
DayOneService service2 = new DayOneService(new FileService());

// Use in separate threads
Thread thread1 = new Thread(() -> {
    try {
        service1.partOne("input1.txt");
    } catch (IOException e) {
        e.printStackTrace();
    }
});

Thread thread2 = new Thread(() -> {
    try {
        service2.partOne("input2.txt");
    } catch (IOException e) {
        e.printStackTrace();
    }
});
```

---

## Testing Considerations

### Unit Testing
- Each service class can be tested independently
- Mock `FileService` for testing without file I/O
- Test both valid and invalid input scenarios

### Integration Testing
- Test with actual input files
- Verify results against expected outputs
- Test error conditions and edge cases

### Example Test Structure
```java
@Test
public void testDayOneServicePartOne() throws IOException {
    // Arrange
    FileService mockFileService = createMockFileService();
    DayOneService service = new DayOneService(mockFileService);
    
    // Act
    Integer result = service.partOne("test-input.txt");
    
    // Assert
    assertEquals(expectedValue, result);
}
```