package com.javarush.cryptoanalizer.vinogradov.caesarcryptografy;

import com.javarush.cryptoanalizer.vinogradov.caesarcryptografy.exception.CaesarWorkerException;
import com.javarush.cryptoanalizer.vinogradov.filesworker.FilenameValidator;
import com.javarush.cryptoanalizer.vinogradov.filesworker.FilesOperations;
import com.javarush.cryptoanalizer.vinogradov.filesworker.exception.FileWorkerException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CaesarBruteForce {
    private final CaesarCipher caesarCipher;
    private final FilenameValidator filenameValidator;
    private final FilesOperations filesOperations;
    private final CaesarAlphabet alphabet;
    private static final String RU_WORDS_LIBRARY = "src/main/java/com/javarush/cryptoanalizer/vinogradov/caesarcryptografy/wordslibrary/RussianWordsLibrary.txt";
    private static final String USER_POSITIVE_ANSWER = "yes";
    private static final String USER_NEGATIVE_ANSWER = "no";


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
                    System.out.printf("User text is correctly decrypted with key: %d \nText written to the file - %s", i, decryptingFilename);
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
            System.out.printf("Decrypted text:\n%s", bruteForceDecryptText);
            System.out.println("This text is correctly decrypted? Input YES/NO");
            String userAnswer = scanner.nextLine();
            if (userAnswer.equalsIgnoreCase(USER_POSITIVE_ANSWER)) {
                return true;
            } else if (userAnswer.equalsIgnoreCase(USER_NEGATIVE_ANSWER)) {
                return false;
            } else {
                System.out.println("Incorrect input. Correct input - Yes or No");
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
