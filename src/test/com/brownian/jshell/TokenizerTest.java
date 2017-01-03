package test.com.brownian.jshell;

import com.brownian.jshell.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the tokenizing part of the application
 *
 * Created by Jack on 1/2/2017.
 */
public class TokenizerTest {
    @Test
    public void test_getNextToken_singleWord(){
        String text = "word";
        try {
            String result = JShell.getNextToken(text, 0);
            assertEquals(text, result, "Should have parsed the single word \""+text+"\"");
        } catch(SyntaxError error){
            fail(error.getMessage());
        }
    }

    @Test
    public void test_getNextToken_startingSpaces(){
        String text = "word";
        try {
            String result = JShell.getNextToken(" "+text, 0);
            assertEquals(text, result, "Should have parsed the single word \""+text+"\"");
        } catch(SyntaxError error){
            fail(error.getMessage());
        }
    }

    @Test
    public void test_getNextToken_withOffset(){
        String text = "one two";
        try {
            String resultOne = JShell.getNextToken(text, 0);
            assertEquals("one", resultOne, "Should have parsed the first word \"one\"");
            String resultTwo = JShell.getNextToken(text, 4);
            assertEquals("two",resultTwo, "Should have parsed the second word \"two\"");
        } catch(SyntaxError error){
            fail(error.getMessage());
        }
    }

    @Test
    public void test_getNextToken_noToken(){
        String text = " ";
        try {
            String result = JShell.getNextToken(text, 0);
            assertNull(result, "Should have parsed the given text (comprised as spaces) as no tokens");
        } catch(SyntaxError error){
            fail(error.getMessage());
        }
    }

}
