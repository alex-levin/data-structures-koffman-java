package KW.CH03;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Test Class to MyInput
 * @author Paul
 */
public class MyInputTest {
    private static final String NL = System.getProperty("line.separator");

    @Test(expected=IllegalArgumentException.class)
    public void testForInvalidInput() {
        int n = MyInput.readInt("Enter weight", 5, 2);
    }
    
    @Test
    public void testForNormalInput() {
        ByteArrayInputStream testIn = new ByteArrayInputStream("3".getBytes());
        System.setIn(testIn);
        int n = MyInput.readInt("Enter weight", 2, 5);
        assertEquals(n, 3);
    }
    
    @Test
    public void testThatPromptIsCorrectForNormalInput() {
        ByteArrayInputStream testIn = 
                new ByteArrayInputStream("3".getBytes());
        System.setIn(testIn);
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        int n = MyInput.readInt("Enter weight", 2, 5);
        assertEquals(n, 3);
        String displayedPrompt = testOut.toString();
        String expectedPrompt = "Enter weight" + 
                "\nEnter an integer between 2 and 5" + NL;
        assertEquals(expectedPrompt, displayedPrompt);
    }
    
// Insert solution to programming exercise 2, section 6, chapter 3 here
        
// Insert solution to programming exercise 1, section 6, chapter 3 here

}
    

