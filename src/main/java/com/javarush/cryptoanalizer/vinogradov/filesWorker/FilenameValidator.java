package com.javarush.cryptoanalizer.vinogradov.filesWorker;

import com.javarush.cryptoanalizer.vinogradov.filesWorker.exception.FileWorkerException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

import static com.javarush.cryptoanalizer.vinogradov.constants.PhraseConstants.*;

public class FilenameValidator {

    private static final List<String> FORBIDDEN_DIRS_FILES = List.of(".bash_history", ".bash_profile", "etc", "proc");

    public Path validatePath(String fileName) {
        for (String pathPart : fileName.split(System.getProperty("file.separator"))) {
            if (FORBIDDEN_DIRS_FILES.contains(pathPart)) {
                throw new FileWorkerException(USE_INCORRECT_FILES + pathPart);
            }
        }

        try {
            Path path = Path.of(fileName);
            return path;
        } catch (InvalidPathException e) {
            throw new FileWorkerException(INVALID_PATH + e.getMessage(), e);
        }
    }

    public void validateForWriting(String filename) {
        Path path = validatePath(filename);
        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                throw new FileWorkerException(NOT_A_FILE);
            }
        }
    }

    public void validateForReading(String filename) {
        Path path = validatePath(filename);

        if (Files.notExists(path)) {
            throw new FileWorkerException(FILE_DOESNT_EXIST);
        }

        if (Files.isDirectory(path)) {
            throw new FileWorkerException(NOT_A_FILE);
        }

        if (!Files.isReadable(path)) {
            throw new FileWorkerException(UNREADBLE_FILE);
        }
    }


}
