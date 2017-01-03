package com.brownian.jshell;

/**
 * An exception used to report a syntax error in the source code.
 * Reports the source text, as well as where in the source text the error is.
 * Created by Jack on 1/2/2017.
 */
public class SyntaxError extends RuntimeException {
    public SyntaxError(String errorDescription, String source, int characterIndex) {
        super(String.format(
                "Encountered a syntax error at character %d while parsing text:\n\t%s\n\t%s",
                characterIndex, errorDescription, source));
    }

    public SyntaxError(String source, int characterIndex) {
        super(String.format(
                "Encountered a syntax error at character %d while parsing text:\n\t%s",
                characterIndex, source));
    }
}
