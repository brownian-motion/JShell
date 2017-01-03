# com.brownian.jshell.JShell

A simple text-based shell application. Ideally, it will work across multiple operating systems. It will target Windows and Linux.

## Built-In Commands
- **cd** Changes the current active directory of the shell.
- **exit** Closes the current shell window.
- **jshell** Opens a new instance of the shell in a new window.
- **clear** Clears the current screen.
- **putc** Puts a character at a specific position on the screen.
- **style** Styles a character(s) at a specific position (or range of positions).
- **get-width** Gets the width of the shell, in characters.
- **get-height** Gets the height of the shell, in characters.
- **echo** Outputs text to the shell.

## Inter-process communication
Any valid ouput is valid input to a process, and can be bassed between processes using a pipe object **|**.

## Project scope and goals
I eventually would like to be able to output things like graphics and objects to the shell. For now, the scope will be just text.

## Contributing
I'll accept any contributions via a pull request, but this is mostly a personal project.

## License
This code is subject to the M.I.T. License.
