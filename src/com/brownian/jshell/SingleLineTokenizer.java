package com.brownian.jshell;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.*;

/**
 * A class that will accept a single line of text as input and break it into tokens.
 * Created by Jack on 1/3/2017.
 */
public class SingleLineTokenizer implements Iterator<String> {
    private String sourceText;
    private int cursorIndex;

    /**
     * Creates a tokenizer that breaks up the given text into tokens.
     * @param source A string to be tokenized
     */
    public SingleLineTokenizer(@NotNull String source){
        sourceText = source;
        cursorIndex = 0;
    }

    /**
     * Reads a token from the given text, starting at the given index. If no such token can be found,
     * returns null.
     * @throws SyntaxError if there is a syntax error in the given text
     * @return The text of the next token (excluding surrounding quotes, for quoted text), or null if no such token could be found
     */
    @Nullable
    @Override
    public String next() throws SyntaxError{
        skipWhitespace();

        if(!hasNext()){
            throw new NoSuchElementException("No more tokens in this string");
        }

        String result;

        if(sourceText.charAt(cursorIndex) == '"'){
            int endQuote = cursorIndex + 1;
            while(endQuote < sourceText.length() && sourceText.charAt(endQuote) != '"'){
                endQuote++;
            }
            if(endQuote == sourceText.length()){
                throw new SyntaxError("No end quote for string literal", sourceText, cursorIndex);
            }
            result = sourceText.substring(cursorIndex + 1, endQuote);

            cursorIndex = endQuote + 2;
        } else {
            int endingIndex = cursorIndex + 1;
            while(endingIndex < sourceText.length() && !Character.isWhitespace(sourceText.charAt(endingIndex)) )
                endingIndex++;
            result = sourceText.substring(cursorIndex, endingIndex);
            cursorIndex = endingIndex+1;
        }

        return result;
    }

    /**
     * Returns the index of the next character that isn't whitespace.
     * @param text  The string to look through.
     * @param index The index to start at. (If out of bounds, then it's clamped within the bounds of the string.)
     * @return The index of the next character that isn't whitespace, or the length of the string if the end is reached.
     */
    private static int getEndOfWhitespaceIndex(@NotNull String text, int index){
        if(index > text.length())
            return text.length();
        if(index < 0)
            index = 0;
        while(index < text.length() && Character.isWhitespace(text.charAt(index)) )
            index++;
        return index;
    }

    /**
     * Advances the cursor to either the next character that isn't whitespace, or the end of the string.
     */
    private void skipWhitespace(){
        this.cursorIndex = getEndOfWhitespaceIndex(sourceText, cursorIndex);
    }

    /**
     * @return true if there is another token after the cursor, or false otherwise.
     */
    @Override
    public boolean hasNext(){
        skipWhitespace();
        return cursorIndex < sourceText.length();
    }
}
