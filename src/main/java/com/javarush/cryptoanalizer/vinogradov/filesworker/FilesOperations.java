package com.javarush.cryptoanalizer.vinogradov.filesworker;

import com.javarush.cryptoanalizer.vinogradov.filesworker.exception.FileWorkerException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FilesOperations {

    public List<String> readFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(Path.of(fileName).toUri())))) {
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
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(Path.of(fileName).toUri())))) {
            for (String value : content) {
                bufferedWriter.write(value);
            }
        } catch (IOException | InvalidPathException ex) {
            throw new FileWorkerException(ex.getMessage(), ex);
        }
    }


}
