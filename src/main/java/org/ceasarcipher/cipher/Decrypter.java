package org.ceasarcipher.cipher;

import org.ceasarcipher.services.FileService;

import java.io.IOException;
import java.nio.file.Path;

public final class Decrypter {

    public static final Decrypter INSTANCE = new Decrypter();

    private Decrypter() {
    }

    public void execute(String filePath, int key) {
        Path source = Path.of(filePath);
        Path target = source.resolveSibling("decrypted_" + source.getFileName());
        String content = null;
        try {
            content = FileService.read(source);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String decrypted = decrypt(content, key);
        try {
            FileService.write(target, decrypted);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String decrypt(String text, int key) {
        int reverseKey = -Math.floorMod(key, 26);
        return Encrypter.INSTANCE.encrypt(text, reverseKey);
    }
}