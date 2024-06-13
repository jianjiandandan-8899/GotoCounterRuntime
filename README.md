
# Goto Counter

This project demonstrates how to use the Soot framework to count the number of `goto` statements executed at runtime in a Java file. In this example, we use `ForLoop.java` which contains multiple simulated `goto` statements using labeled loops and `break`/`continue`.

## Project Structure

- `GotoCounter.java`: Soot-based instrumentation tool that inserts runtime counting code into Java bytecode.
- `GotoCounterRuntime.java`: Utility class to keep track of `goto` statements execution count.
- `ForLoop.java`: Example Java file containing multiple simulated `goto` statements.

## Setup

1. **Clone the repository**:

   ```sh
   git clone https://github.com/yourusername/goto-counter.git
   cd goto-counter
   ```

2. **Set up Soot**:
   - If you're using Maven, add the following dependency to your `pom.xml`:

     ```xml
     <dependency>
         <groupId>ca.mcgill.sable</groupId>
         <artifactId>soot</artifactId>
         <version>4.2.1</version>
     </dependency>
     ```

   - If not using Maven, download the Soot JAR and include it in your project's classpath.

## Usage

1. **Compile the example `ForLoop.java`**:

   ```sh
   javac ForLoop.java
   ```

2. **Run the `GotoCounter` tool to instrument the bytecode**:

   ```sh
   java -cp .:path/to/sootclasses-trunk-jar-with-dependencies.jar GotoCounter
   ```

3. **Run the instrumented `ForLoop` class**:

   ```sh
   java -cp . ForLoop
   ```

   This will execute the instrumented `ForLoop` class and output the number of `goto` statements executed.

## Example Output

```
Breaking outer loop
First loop done, count = 7
Breaking inner loop
Second loop done, count = 15
Goto statements executed: 2
```

## Explanation

- **GotoCounter.java**: This file contains the logic to instrument Java bytecode using Soot. It inserts a call to `GotoCounterRuntime.recordGoto()` before each `goto` statement.
- **GotoCounterRuntime.java**: This class maintains a static counter to track the number of `goto` statements executed.
- **ForLoop.java**: This is an example Java file that contains labeled loops and `break`/`continue` statements to simulate `goto` behavior.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
