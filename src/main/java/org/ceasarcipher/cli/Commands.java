
package org.ceasarcipher.cli;

import org.ceasarcipher.cipher.BrutForcer;
import org.ceasarcipher.cipher.Decrypter;
import org.ceasarcipher.cipher.Encrypter;

public enum Commands {

    ENCRYPT("Encrypt file", Encrypter.INSTANCE),
    DECRYPT("Decrypt file", Decrypter.INSTANCE),
    BRUT_FORCE("Brute force", BrutForcer.INSTANCE),
    EXIT("Exit program", null);

    private final String label;
    private final Object command;

    Commands(String label, Object command) {
        this.label = label;
        this.command = command;
    }

    public void execute(String filePath, int key) {
        switch (this) {
            case ENCRYPT ->
                    Encrypter.INSTANCE.execute(filePath, key);
            case DECRYPT ->
                    Decrypter.INSTANCE.execute(filePath, key);
            case BRUT_FORCE ->
                    BrutForcer.INSTANCE.execute(filePath);
            case EXIT -> {
            }
        }
    }

    @Override
    public String toString() {
        return label;
    }
}
