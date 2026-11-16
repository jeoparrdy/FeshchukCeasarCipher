package org.ceasarcipher.cipher;

import org.ceasarcipher.services.FileService;

import java.io.IOException;
import java.nio.file.Path;

public final class Encrypter {

    public static final Encrypter INSTANCE = new Encrypter();

    private Encrypter() {
    }

    public void execute(String filePath, int key)  {
        Path source = Path.of(filePath);
        Path target = source.resolveSibling("encrypted_" + source.getFileName());
        String content = null;
        try {
            content = FileService.read(source);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String encrypted = encrypt(content, key);
        try {
            FileService.write(target, encrypted);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String encrypt(String text, int key) {
        int normalizedKey = Math.floorMod(key, 26);
        StringBuilder result = new StringBuilder(text.length());
        for (char ch : text.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) ('A' + (ch - 'A' + normalizedKey) % 26);
            } else if (ch >= 'a' && ch <= 'z') {
                ch = (char) ('a' + (ch - 'a' + normalizedKey) % 26);
            }
            result.append(ch);
        }
        return result.toString();
    }
}