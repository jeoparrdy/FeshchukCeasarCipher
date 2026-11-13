package org.ceasarcipher.services;

import org.ceasarcipher.cipher.Decrypter;
import org.ceasarcipher.cipher.Encrypter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public final class FileService {

    private final Encrypter encrypter = Encrypter.INSTANCE;
    private final Decrypter decrypter = Decrypter.INSTANCE;

    private FileService() {
    }

    public String read(Path path) throws IOException {
        return Files.readString(path, StandardCharsets.UTF_8);
    }

    public List<String> readLines(Path path) throws IOException {
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    public void write(Path path, String content) throws IOException {
        Files.writeString(path, content, StandardCharsets.UTF_8);
    }

    public void append(Path path, String content) throws IOException {
        Files.writeString(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public void encryptFile(Path source, Path target, int key) throws IOException {


    }

    public void decryptFile(Path source, Path target, int key) throws IOException {

    }

    private void fileChange(Path source, Path target, int key, String direction) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(source, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(target, StandardCharsets.UTF_8)) {

            String line;

            while ((line = reader.readLine()) != null) {
                if (direction.equals("encrypt")) {
                    String fileLine = encrypter.encrypt(line, key);
                    writer.write(fileLine);
                    writer.newLine();
                } else if(direction.equals("decrypt")) {
                    String fileLine = decrypter.decrypt(line, key);
                    writer.write(fileLine);
                    writer.newLine();
                }

            }
        }
    }

}
