package org.ceasarcipher.cli;

import java.util.Scanner;

public class Executor {
    public void showMenu() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Caesar Cipher");

        while (true) {
            System.out.println("Please make your choice:");
            for (Commands command : Commands.values()) {
                System.out.println((command.ordinal() + 1) + ". " + command);
            }
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.next();
                continue;
            }
            int choice = sc.nextInt();
            if (choice < 1 || choice > Commands.values().length) {
                System.out.println("Invalid choice! Try again.");
                continue;
            }
            if (choice == 4) {
                System.out.println("Goodbye!");
                break;
            }
            Commands [] commands = Commands.values();
            commands[choice - 1].execute();
        }
    }
}
