package com.javarush.cryptoanalizer.vinogradov.filesworker;

import com.javarush.cryptoanalizer.vinogradov.filesworker.exception.FileWorkerException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FilesOperations {

    public List<String> readFile(String fileName) {
        try (FileReader reader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            List<String> readedFile = new ArrayList<>();
            String line = "";
            while (bufferedReader.ready()) {
                char ch = (char) bufferedReader.read();
                line += ch;
            }
            readedFile.add(line);
            return readedFile;
        } catch (IOException | InvalidPathException e) {
            throw new FileWorkerException(e.getMessage(), e);
        }
    }

    public List<String> readLibraryFile(String fileName) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(fileName))) {
            List<String> readedFile = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                readedFile.add(line);
            }
            return readedFile;
        } catch (IOException | InvalidPathException e) {
            throw new FileWorkerException(e.getMessage(), e);
        }
    }

    public void writeFile(String fileName, List<String> content) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(fileName))) {
            for (String value : content) {
                bufferedWriter.write(value);
            }
        } catch (IOException | InvalidPathException ex) {
            throw new FileWorkerException(ex.getMessage(), ex);
        }
    }


}
