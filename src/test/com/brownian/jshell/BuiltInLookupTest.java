package test.com.brownian.jshell;

import com.brownian.jshell.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the ability of {@link com.brownian.jshell.BuiltIn} to look up commands
 * Created by Jack on 1/3/2017.
 */
public class BuiltInLookupTest {
    @Test
    public void test_BuiltIn_lookupCd(){
        assertEquals(BuiltIn.CD, BuiltIn.getBuiltIn("cd"));
    }

    @Test
    public void test_BuiltIn_lookupExit(){
        assertEquals(BuiltIn.EXIT, BuiltIn.getBuiltIn("exit"));
    }

    @Test
    public void test_BuiltIn_lookupHelp(){
        assertEquals(BuiltIn.HELP, BuiltIn.getBuiltIn("help"));
    }

    @Test
    public void test_BuiltIn_lookupNull(){
        try{
            BuiltIn.getBuiltIn(null);
            fail("Should not have been able to look up null");
        } catch(IllegalArgumentException e){
            //do nothing, pass test
        }
    }

    @Test
    public void test_BuiltIn_lookupInvalid(){
        try{
            BuiltIn.getBuiltIn("buttslol");
            fail("Should not have been able to look up buttslol");
        } catch(IllegalArgumentException e){
            //do nothing, pass test
        }
    }
}
