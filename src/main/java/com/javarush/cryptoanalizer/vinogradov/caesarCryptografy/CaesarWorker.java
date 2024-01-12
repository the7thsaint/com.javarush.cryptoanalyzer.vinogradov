package com.javarush.cryptoanalizer.vinogradov.caesarCryptografy;

import com.javarush.cryptoanalizer.vinogradov.caesarCryptografy.exception.CaesarWorkerException;
import com.javarush.cryptoanalizer.vinogradov.filesWorker.FilenameValidator;
import com.javarush.cryptoanalizer.vinogradov.filesWorker.FilesOperations;

import java.util.ArrayList;
import java.util.List;

public class CaesarWorker {
    private CaesarCipher caesarCipher;
    private FilenameValidator filenameValidator;
    private FilesOperations filesOperations;

    public CaesarWorker() {
        this.filenameValidator = new FilenameValidator();
        this.filesOperations = new FilesOperations();
    }

    public void encrypt(String readableFile, String writableFile, int key) {
        filenameValidator.validateForReading(readableFile);
        filenameValidator.validateForWriting(writableFile);
        try {
            List<String> readableStrings = filesOperations.readFile(readableFile);
            caesarCipher = new CaesarCipher(new CaesarAlphabet(readableStrings));
            List<String> dataForEncrypt = new ArrayList<>();
            for (String result : readableStrings) {
                dataForEncrypt.add(caesarCipher.encrypt(result, key));
            }
            filesOperations.writeFile(writableFile, dataForEncrypt);
        } catch (RuntimeException exception) {
            throw new CaesarWorkerException(exception.getMessage());
        }
    }

    public void decrypt(String readableFile, String writableFile, int key) {
        filenameValidator.validateForReading(readableFile);
        filenameValidator.validateForWriting(writableFile);

        try {
            List<String> readableStrings = filesOperations.readFile(readableFile);
            caesarCipher = new CaesarCipher(new CaesarAlphabet(readableStrings));
            List<String> decryptingData = new ArrayList<>();
            for (String result : readableStrings) {
                decryptingData.add(caesarCipher.decrypt(result, key));
            }
            filesOperations.writeFile(writableFile, decryptingData);
        } catch (RuntimeException exception) {
            throw new CaesarWorkerException(exception.getMessage());
        }
    }

}
