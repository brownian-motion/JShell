package com.brownian.jshell;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.InputStream;
import java.util.*;

/**
 * Actually runs the application.
 * Created by Jack on 1/2/2017.
 */
public class JShell {
    public static void main(String[] args){
        //noinspection InfiniteLoopStatement
        while(true) {
            String[] tokens;
            try {
                tokens = parseLineForTokens(System.in);
                System.out.println(String.join("\n ",tokens));
            } catch (SyntaxError err) {
                System.err.println(err.getMessage());
            }
        }
    }

    /**
     * Gets a line (separated by newlines) from the given input stream and parses it as tokens.
     * @param inputStream The stream from which to read a line of text
     * @return An array of Strings representing the tokens. If no tokens are present, an empty array is returned.
     */
    @NotNull
    public static String[] parseLineForTokens(@NotNull InputStream inputStream) throws SyntaxError{
        try (Scanner scanner = new Scanner(inputStream)) {
            String line = scanner.nextLine();
            return parseLineForTokens(line);
        } catch (Exception e){
            System.err.println("Encountered an error:\n\t"+e);
            return new String[0];
        }
    }

    @NotNull
    public static String[] parseLineForTokens(@NotNull String text) throws SyntaxError{
        List<String> tokens = new LinkedList<>();

        int cursorIndex = 0;

        while(cursorIndex < text.length()){
            String nextToken = getNextToken(text, cursorIndex);
            if(nextToken == null)
                break;
            cursorIndex += nextToken.length();
            tokens.add(nextToken);
        }

        return tokens.toArray( new String[tokens.size()] );
    }

    /**
     * Reads a token from the given text, starting at the given index. If no such token can be found,
     * returns null.
     * @param text the text to break into tokens
     * @param startingIndex the index from which to start tokenizing the given string
     * @throws SyntaxError if there is a syntax error in the given text
     * @return The text of the next token (excluding surrounding quotes, for quoted text), or null if no such token could be found
     */
    @Nullable
    public static String getNextToken(@NotNull String text, int startingIndex) throws SyntaxError{
        while(startingIndex < text.length() && Character.isWhitespace(text.charAt(startingIndex)) )
            startingIndex++;

        if(startingIndex >= text.length()){
            return null;
        } else {
            if(text.charAt(startingIndex) == '"'){
                int endQuote = startingIndex + 1;
                while(endQuote < text.length() && text.charAt(endQuote) != '"'){
                    endQuote++;
                }
                if(endQuote == text.length()){
                    throw new SyntaxError("No end quote for string literal", text, startingIndex);
                }
                return text.substring(startingIndex + 1, endQuote);
            } else {
                int endingIndex = startingIndex + 1;
                while(endingIndex < text.length() && !Character.isWhitespace(text.charAt(endingIndex)) )
                    endingIndex++;
                return text.substring(startingIndex, endingIndex);
            }
        }
    }

}
