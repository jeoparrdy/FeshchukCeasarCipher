package org.ceasarcipher;

import org.ceasarcipher.cli.Commands;
import org.ceasarcipher.cli.Menu;
import org.ceasarcipher.validators.ArgumentsValidator;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            new Menu().showMenu();
            return;
        }
        try {
            ArgumentsValidator.validateArgs(args);
            Commands command = ArgumentsValidator.validateCommand(args[0]);
            String filePath = ArgumentsValidator.validateFilePath(args[1]).toString();
            int key = ArgumentsValidator.requiresKey(command) ? ArgumentsValidator.validateKey(args[2]) : 0;
            command.execute(filePath, key);

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
