package com.brownian.jshell;

import com.sun.istack.internal.NotNull;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Actually runs the application.
 * Created by Jack on 1/2/2017.
 */
public class JShell {
    //TODO: make JShell an instance, and have main() just pull one up
    //TODO: figure out piping
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        //noinspection InfiniteLoopStatement
        while(true) {
            try {
                printPrompt(System.out);
                //TODO: break up lines by semicolons
                SingleLineTokenizer tokenizer = new SingleLineTokenizer(input.nextLine());
                List<String> tokens = new LinkedList<>();
                while(tokenizer.hasNext())
                    tokens.add(tokenizer.next());
                for(String token : tokens){
                    System.out.println(token);
                }

                if(tokens.isEmpty())
                    continue;

                String command = tokens.remove(0);

                try{
                    BuiltIn builtIn = BuiltIn.getBuiltIn(command);
                    executeBuiltIn(builtIn, tokens);
                } catch(IllegalArgumentException e) { //indicating that it is not a built-in
                    executeFile(command, tokens);
                }
            } catch (SyntaxError err) {
                System.err.println(err.getMessage());
            }
        }
    }

    /**
     * Prints the prompt to the specified PrintStream object
     * @param out the PrintStream object to print to
     */
    public static void printPrompt(PrintStream out){
        out.print("> ");
    }

    /**
     * Executes the given built-in command with the given parameters
     * @param builtIn   Which built-in command to execute
     * @param arguments What arguments were passed to this command
     */
    public static void executeBuiltIn(@NotNull BuiltIn builtIn, @NotNull List<String> arguments){
        switch(builtIn){
            case CD:
                if(arguments.size() == 1)
                    cd(arguments.get(0));
                else
                    System.err.println("Invalid arguments to cd. Type \"help\" for more details.");
                break;
            case EXIT:
                System.exit(0);
                break;
            case HELP:
                //TODO: Check for flags like -h, --help, -v, --verbose, or /?
                printShortHelp();
                break;
            default:
                throw new AssertionError("Did not implement built-in command "+builtIn);
        }
    }

    /**
     * Change directory to the given arguments
     * @param path the path to change directory to
     */
    public static void cd(String path){
        System.out.println("Changing directory to "+path);
    }

    /**
     * Prints a help message explaining how to use the shell, including instructions for the built-in commands.
     */
    public static void printShortHelp(){
        System.out.println("Help");
    }

    public static void executeFile(@NotNull String filePath, @NotNull List<String> arguments){

    }

}
