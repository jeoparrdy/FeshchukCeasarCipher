package org.ceasarcipher.cli;

import org.ceasarcipher.cipher.Decrypter;
import org.ceasarcipher.cipher.Encrypter;

public enum Commands {
    ENCRYPT("Encrypt file", Encrypter.INSTANCE),
    DECRYPT("Decrypt file", Decrypter.INSTANCE);

    private final String label;
    private final Object command;

    Commands(String name, Object command) {
        this.label = name;
        this.command = command;
    }

    public void execute() {
        if (command == null) {
            System.exit(0);
        }

        switch (command) {
            case Encrypter command -> command.execute();
            case Decrypter command -> command.execute();
            default -> System.exit(0);
        }
    }

    @Override
    public String toString() {
        return label;
    }
}
