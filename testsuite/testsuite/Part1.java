package testsuite;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

import testsuite.internal.CatchExitRule;

import static testsuite.TestSuiteHelper.*;

public class Part1 {

    private static final int FIVE_SECONDS = 5000;

    @Rule
    public Timeout timeout = new Timeout(FIVE_SECONDS);

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Rule
    public CatchExitRule disableSystemExit = new CatchExitRule();

    @Test
    public void testNormalSquares() {
        assertEquals(readFile("testcases/outputs/turtle_normal.res"), runMain(
                "testcases/inputs/turtle_normal.dat", tmpFolder));
    }
    
    @Test
    public void testNormalsRace() {
        assertEquals(readFile("testcases/outputs/turtles_normal.res"), runMain(
                "testcases/inputs/turtles_normal.dat", tmpFolder));
    }
    
}
