
package org.ceasarcipher.validators;

import org.ceasarcipher.cli.Commands;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public final class ArgumentsValidator {

    private ArgumentsValidator() {
    }

    public static void validateArgs(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("No arguments provided."
            );
        }
        Commands command = validateCommand(args[0]);
        int expectedArgs = requiresKey(command) ? 3 : 2;
        if (args.length != expectedArgs) {
            throw new IllegalArgumentException(
                    "Invalid number of arguments. Usage: " +
                            "<ENCRYPT|DECRYPT|BRUT_FORCE> <filePath> [key]"
            );
        }
        validateFilePath(args[1]);
        if (requiresKey(command)) {
            validateKey(args[2]);
        }
    }

    public static Commands validateCommand(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Command cannot be empty." );
        }

        try {
            Commands command = Commands.valueOf(input.trim().toUpperCase());
            if (command == Commands.EXIT) {
                throw new IllegalArgumentException("EXIT is not allowed as a command-line argument.");
            }
            return command;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Invalid command: " + input + ". Allowed: ENCRYPT, DECRYPT, BRUT_FORCE", e
            );
        }
    }

    public static Path validateFilePath(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("File path cannot be empty." );
        }
        Path path;
        try {
            path = Path.of(input.trim());
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException(
                    "Invalid file path: " + input, e
            );
        }
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException("File does not exist or is not a regular file: " + path);
        }
        if (!Files.isReadable(path)) {
            throw new IllegalArgumentException("File is not readable: " + path);
        }
        return path;
    }

    public static int validateKey(String input) {

        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Key cannot be empty.");
        }

        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid key: " + input + ". Key must be an integer.", e );
        }
    }

    public static boolean requiresKey(Commands command) {
        return command == Commands.ENCRYPT || command == Commands.DECRYPT;
    }
}
