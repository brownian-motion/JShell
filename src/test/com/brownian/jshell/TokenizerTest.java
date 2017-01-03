package test.com.brownian.jshell;

import com.brownian.jshell.*;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the tokenizing part of the application
 *
 * Created by Jack on 1/2/2017.
 */
public class TokenizerTest {
    @Test
    public void test_SingleLineTokenizer_singleWord(){
        String text = "word";
        SingleLineTokenizer tokenizer = new SingleLineTokenizer(text);
        try {
            assertTrue(tokenizer.hasNext());
            assertEquals(text, tokenizer.next(), "Should have parsed the single word \""+text+"\"");
            assertFalse(tokenizer.hasNext());
        } catch(SyntaxError error){
            fail(error.getMessage());
        }
    }

    @Test
    public void test_SingleLineTokenizer_startingSpaces(){
        String text = "word";
        SingleLineTokenizer tokenizer = new SingleLineTokenizer(" \t"+text);
        assertTrue(tokenizer.hasNext(), "Should be able to detect next token after spaces");
        assertEquals(text, tokenizer.next(), "Should have parsed the single word \""+text+"\"");
        assertFalse(tokenizer.hasNext(), "Should have consumed the only token");
    }

    @Test
    public void test_SingleLineTokenizer_twoWords(){
        SingleLineTokenizer tokenizer = new SingleLineTokenizer("one two");
        try {
            assertTrue(tokenizer.hasNext());
            assertEquals("one", tokenizer.next(), "Should have parsed the first word \"one\"");
            assertTrue(tokenizer.hasNext());
            assertEquals("two",tokenizer.next(), "Should have parsed the second word \"two\"");
            assertFalse(tokenizer.hasNext());
        } catch(SyntaxError error){
            fail(error.getMessage());
        }
    }

    @Test
    public void test_SingleLineTokenizer_noToken(){
        String text = " ";
        try {
            SingleLineTokenizer tokenizer = new SingleLineTokenizer(text);
            assertFalse(tokenizer.hasNext(),"Shouldn't have a token available");
            String result = tokenizer.next();
            fail("Should have thrown an error when tokenizing text with only whitespace");
        } catch(SyntaxError error){
            fail(error.getMessage());
        } catch(NoSuchElementException e){
            //do nothing, pass test
        }
    }

    @Test
    public void test_SingleLineTokenizer_quotedText(){
        String text = "Quoted text";
        String textInQuotes = "\""+text+"\"";
        SingleLineTokenizer tokenizer = new SingleLineTokenizer(textInQuotes);
        try{
            assertTrue(tokenizer.hasNext());
            assertEquals(text,tokenizer.next(), "Should return the entire body of text within quotes, without the quotes");
            assertFalse(tokenizer.hasNext());
        } catch(SyntaxError error){
            fail(error.getMessage());
        }
    }

}
