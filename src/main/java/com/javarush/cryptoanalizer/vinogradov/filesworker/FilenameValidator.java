package com.javarush.cryptoanalizer.vinogradov.filesworker;

import com.javarush.cryptoanalizer.vinogradov.filesworker.exception.FileWorkerException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class FilenameValidator {

    private static final List<String> FORBIDDEN_DIRS_FILES = List.of(".bash_history", ".bash_profile", "etc", "proc");

    public Path validatePath(String fileName) {
        for (String pathPart : fileName.split(System.getProperty("file.separator"))) {
            if (FORBIDDEN_DIRS_FILES.contains(pathPart)) {
                throw new FileWorkerException("Used incorrect files " + pathPart);
            }
        }

        try {
            Path path = Path.of(fileName);
            return path;
        } catch (InvalidPathException e) {
            throw new FileWorkerException("Invalid path: " + e.getMessage(), e);
        }
    }

    public void validateForWriting(String filename) {
        Path path = validatePath(filename);
        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                throw new FileWorkerException("This file is directory");
            }
        }

//        if (!Files.isWritable(path)) {
//            throw new FileWorkerException("Cannot writing to this file");
//        }
    }

    public void validateForReading(String filename) {
        Path path = validatePath(filename);

        if (Files.notExists(path)) {
            throw new FileWorkerException("File doesn't exists");
        }

        if (Files.isDirectory(path)) {
            throw new FileWorkerException("This file is directory");
        }

        if (!Files.isReadable(path)) {
            throw new FileWorkerException("Cannot read to this file");
        }
    }


}
