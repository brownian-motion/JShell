package com.brownian.jshell;

import com.sun.istack.internal.Nullable;

/**
 * An enum representing the built-in commands implemented by {@link JShell}.
 * Created by Jack on 1/3/2017.
 */
public enum BuiltIn {
    HELP("help"), CD("cd"), EXIT("exit");

    public final String command;

    BuiltIn(String command) {
        this.command = command;
    }

    /**
     * Returns an enum representing a built-in command that matches the given command
     *
     * @param command the command to look for
     * @return a unique enum representing the given command
     * @throws IllegalArgumentException if the given command is not found
     */
    public static BuiltIn getBuiltIn(@Nullable String command) {
        for (BuiltIn builtIn : values())
            if (builtIn.command.equals(command))
                return builtIn;
        throw new IllegalArgumentException("Could not find built-in command matching " + command);
    }
}
