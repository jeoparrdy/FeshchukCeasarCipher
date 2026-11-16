
package org.ceasarcipher.cli;

import org.ceasarcipher.validators.ArgumentsValidator;

import java.util.Scanner;

public class Menu {

    public void showMenu() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Caesar Cipher");
        while (true) {
            System.out.println("Please make your choice:");
            Commands[] commands = Commands.values();
            for (Commands command : commands) {
                System.out.println((command.ordinal() + 1) + ". " + command);
            }
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice < 1 || choice > commands.length) {
                System.out.println("Invalid choice! Try again.");
                continue;
            }
            Commands selectedCommand = commands[choice - 1];
            if (selectedCommand == Commands.EXIT) {
                System.out.println("Goodbye!");
                break;
            }
            System.out.print("Enter file path: ");
            String filePath = ArgumentsValidator.validateFilePath(sc.nextLine()).toString();

            int key = 0;
            if (ArgumentsValidator.requiresKey(selectedCommand)) {
                System.out.print("Enter key: ");
                key = ArgumentsValidator.validateKey(sc.nextLine());
            }

            selectedCommand.execute(filePath, key);
        }
    }
}
