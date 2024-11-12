import org.example.Main;
import org.example.exception.InvalidArgumentException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MainTest {

    private static final PrintStream originalOut = System.out;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    public static void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainWithUnknownParameter() {
        String[] args = new String[]{"-invalid"};
        try {
            Main.main(args);
            fail("Expected exception");
        } catch (InvalidArgumentException e) {
            assertEquals("Unrecognized option: -invalid", e.getMessage());
        }
    }

    @Test
    public void testMainHelpArgument() {
        String[] args = new String[]{"-h"};
        String expectedOutput = """
                usage: Main [-a] [-b] [-f] [-h] [-o] [-r] [-u]
                 -a,--array      find missing element in an array example.
                 -b,--binary     find max binary gap example.
                 -f,--fib        print this Fibonacci sequence starting at x for y
                                 iterations.
                 -h,--help       print this help message to the output stream.
                 -o,--optional   example using optional fields.
                 -r,--rotate     rotate an array.
                 -u,--unpair     find unpaired element in list example.
                """;
        try {
            Main.main(args);
            Assertions.assertTrue(outContent.toString().contains(expectedOutput));
        } catch (InvalidArgumentException e) {
            fail("No Expected exception");
        }
    }

    @Test
    public void TestMainFibParameterWithoutStartOrLimit() {
        String[] args = new String[]{"-f"};
        try {
            Main.main(args);
            fail("Expected exception");
        } catch (InvalidArgumentException e) {
            assertEquals("You must also provide a starting and end number for this option.", e.getMessage());
        }
    }

    @Test
    public void TestMainFibWithInvalidStart() {
        String[] args = new String[]{"-f", "5x", "10"};
        try {
            Main.main(args);
            fail("Expected exception");
        } catch (InvalidArgumentException e) {
            assertEquals("start sequence, and iteration count for -f are expected and must be numbers.",
                    e.getMessage());
        }
    }

    @Test
    public void TestMainFibWithInvalidLimit() {
        String[] args = new String[]{"-f", "5", "10x"};
        try {
            Main.main(args);
            fail("Expected exception");
        } catch (InvalidArgumentException e) {
            assertEquals("start sequence, and iteration count for -f are expected and must be numbers.",
                    e.getMessage());
        }
    }

    @Test
    public void TestMainFibArgument() {
        String[] args = new String[]{"-f", "5", "10"};
        String expectedOutput = "Fibonacci Sequence is: [5, 8, 13, 21, 34, 55, 89, 144, 233, 377]";
        try {
            Main.main(args);
            Assertions.assertTrue(outContent.toString().contains(expectedOutput));
        } catch (InvalidArgumentException e) {
            assertEquals("start sequence, and iteration count for -f are expected and must be numbers.",
                    e.getMessage());
        }
    }
}