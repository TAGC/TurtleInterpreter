package testsuite;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

import testsuite.internal.CatchExitRule;

import static testsuite.TestSuiteHelper.*;

public class Part2 {

    private static final int FIVE_SECONDS = 5000;

    @Rule
    public Timeout timeout = new Timeout(FIVE_SECONDS);

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Rule
    public CatchExitRule disableSystemExit = new CatchExitRule();

    @Test
    public void testBouncyTurtles() {
        assertEquals(readFile("testcases/outputs/turtles_bouncy.res"), runMain(
                "testcases/inputs/turtles_bouncy.dat", tmpFolder));
    }
    
    @Test
    public void testReflectingTurtles() {
        assertEquals(readFile("testcases/outputs/turtles_reflecting.res"), runMain(
                "testcases/inputs/turtles_reflecting.dat", tmpFolder));
    }
    
    @Test
    public void testContinuousTurtles() {
        assertEquals(readFile("testcases/outputs/turtles_continuous.res"), runMain(
                "testcases/inputs/turtles_continuous.dat", tmpFolder));
    }
    
    @Test
    public void testWrappingTurtles() {
        assertEquals(readFile("testcases/outputs/turtles_wrapping.res"), runMain(
                "testcases/inputs/turtles_wrapping.dat", tmpFolder));
    }
    
}
