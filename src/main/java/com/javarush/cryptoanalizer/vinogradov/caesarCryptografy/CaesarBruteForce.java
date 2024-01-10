package com.javarush.cryptoanalizer.vinogradov.caesarCryptografy;

import com.javarush.cryptoanalizer.vinogradov.caesarCryptografy.exception.CaesarWorkerException;
import com.javarush.cryptoanalizer.vinogradov.filesWorker.FilenameValidator;
import com.javarush.cryptoanalizer.vinogradov.filesWorker.FilesOperations;
import com.javarush.cryptoanalizer.vinogradov.filesWorker.exception.FileWorkerException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.javarush.cryptoanalizer.vinogradov.constants.PhraseConstants.*;

public class CaesarBruteForce {
    private final CaesarCipher caesarCipher;
    private final FilenameValidator filenameValidator;
    private final FilesOperations filesOperations;
    private final CaesarAlphabet alphabet;



    public CaesarBruteForce() {
        this.alphabet = new CaesarAlphabet();
        this.caesarCipher = new CaesarCipher(alphabet);
        this.filesOperations = new FilesOperations();
        this.filenameValidator = new FilenameValidator();
    }

    public void bruteForceDecrypt(String encryptFilename, String decryptingFilename) {
        filenameValidator.validateForReading(encryptFilename);
        filenameValidator.validateForWriting(decryptingFilename);

        StringBuilder stringBuilder = new StringBuilder();
        try {
            List<String> readableStrings = filesOperations.readFile(encryptFilename);
            for (String textString : readableStrings) {
                stringBuilder.append(textString);
            }
            for (int i = 0; i < alphabet.getAlphabetSize(); i++) {
                String decryptString = caesarCipher.decrypt(stringBuilder.toString(), i);
                if (userValidateText(decryptString)) {
                    List<String> resultOfDecrypt = new ArrayList<>();
                    resultOfDecrypt.add(decryptString);
                    filesOperations.writeFile(decryptingFilename, resultOfDecrypt);
                    System.out.printf(CORRECT_BRUTE_FORCE_DECRYPT, i, decryptingFilename);
                    break;
                }
            }
        } catch (FileWorkerException | CaesarWorkerException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }

    }

    private boolean userValidateText(String bruteForceDecryptText) {
        Scanner scanner = new Scanner(System.in);
        boolean successValidate = false;
        if ((libraryContains(bruteForceDecryptText))) {
            successValidate = true;
        }

        while (successValidate) {
            System.out.printf(SHOW_DECRYPTED_TEXT, bruteForceDecryptText);
            System.out.println(SHOW_ANSWER_FOR_USER);
            String userAnswer = scanner.nextLine();
            if (userAnswer.equalsIgnoreCase(USER_POSITIVE_ANSWER)) {
                return true;
            } else if (userAnswer.equalsIgnoreCase(USER_NEGATIVE_ANSWER)) {
                return false;
            } else {
                System.out.println(INCORRECT_USER_ANSWER_FOR_BRUTE_FORCE_DECRYPT);
            }
        }
        return false;
    }

    private boolean libraryContains(String bruteForceDecryptText) {
        String[] decryptWords = bruteForceDecryptText.replaceAll("[^A-Za-zА-Яа-яё.\\s]+", "").split("\\s+");
        int count = 0;
        for (String splitString : decryptWords) {
            if (filesOperations.readLibraryFile(RU_WORDS_LIBRARY).contains(splitString)) {
                count++;
            }
        }
        if (count >= decryptWords.length * 0.8) {
            return true;
        } else {
            return false;
        }
    }


}
