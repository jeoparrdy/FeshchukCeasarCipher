
package org.ceasarcipher.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public final class FileService {

    private FileService() {
    }

    public static String read(Path path) throws IOException {
        return Files.readString(path, StandardCharsets.UTF_8);
    }

    public static List<String> readLines(Path path) throws IOException {
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    public static void write(Path path, String content) throws IOException {
        Files.writeString(path, content, StandardCharsets.UTF_8);
    }

    public static void append(Path path, String content) throws IOException {
        Files.writeString(
                path,
                content,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }

    public static Path generateOutputPath(Path source, String suffix) {
        String fileName = source.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        String newFileName;
        if (dotIndex > 0) {
            String name = fileName.substring(0, dotIndex);
            String extension = fileName.substring(dotIndex);
            newFileName = name + suffix + extension;
        } else {
            newFileName = fileName + suffix;
        }
        return source.resolveSibling(newFileName);
    }
}
