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
    private CaesarCipher caesarCipher;
    private final FilenameValidator filenameValidator;
    private final FilesOperations filesOperations;
    private CaesarAlphabet alphabet;


    public CaesarBruteForce() {
        this.filesOperations = new FilesOperations();
        this.filenameValidator = new FilenameValidator();
    }

    public void bruteForceDecrypt(String encryptFilename, String decryptingFilename) {
        filenameValidator.validateForReading(encryptFilename);
        filenameValidator.validateForWriting(decryptingFilename);

        StringBuilder stringBuilder = new StringBuilder();
        try {
            List<String> readableStrings = filesOperations.readFile(encryptFilename);
            alphabet = new CaesarAlphabet(readableStrings);
            caesarCipher = new CaesarCipher(alphabet);
            for (String textString : readableStrings) {
                stringBuilder.append(textString);
            }
            for (int i = 0; i < alphabet.getAlphabetSize(); i++) {
                String decryptString = caesarCipher.decrypt(stringBuilder.toString(), i);
                if (isUserTextValid(decryptString)) {
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

    private boolean isUserTextValid(String bruteForceDecryptText) {
        Scanner scanner = new Scanner(System.in);
        boolean successValidate = false;
        if ((isLibraryContains(bruteForceDecryptText))) {
            successValidate = true;
        }

        if (successValidate) {
            System.out.printf(SHOW_DECRYPTED_TEXT, bruteForceDecryptText);
            System.out.println(SHOW_ANSWER_FOR_USER);
            String userAnswer = scanner.nextLine();
            switch (userAnswer.toLowerCase()) {
                case USER_POSITIVE_ANSWER -> {
                    return true;
                }
                case USER_NEGATIVE_ANSWER -> {
                    return false;
                }
                default -> System.out.println(INCORRECT_USER_ANSWER_FOR_BRUTE_FORCE_DECRYPT);
            }
        }
        return false;
    }

    private boolean isLibraryContains(String bruteForceDecryptText) {
        String[] decryptWords = bruteForceDecryptText.replaceAll(BRUTE_FORCE_SEARCH_REGEX, "").split(BRUTE_FORCE_SPLIT_REGEX);
        int count = 0;
        for (String splitString : decryptWords) {
            if (splitString.matches(CHOOSE_LANGUAGE_REGEX)) {
                if (filesOperations.readLibraryFile(RU_WORDS_LIBRARY).contains(splitString)) {
                    count++;
                }
            } else {
                if (filesOperations.readLibraryFile(ENG_WORDS_LIBRARY).contains(splitString)) {
                    count++;
                }
            }

        }
        return count >= decryptWords.length * 0.8;
    }


}
