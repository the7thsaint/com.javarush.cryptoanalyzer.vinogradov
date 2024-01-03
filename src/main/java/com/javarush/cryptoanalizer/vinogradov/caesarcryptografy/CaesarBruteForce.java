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
                    System.out.printf("User text is correctly decrypted with key: %d \n Text written to the file - %s", i, decryptingFilename);
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
        if (bruteForceDecryptText.contains(" ") || bruteForceDecryptText.contains(": ") || bruteForceDecryptText.contains("; ") || bruteForceDecryptText.contains(", ") || bruteForceDecryptText.contains(". ") || bruteForceDecryptText.contains("? ") || bruteForceDecryptText.contains("! ")) {
            successValidate = true;
        }

        while (successValidate) {
            System.out.printf("Decrypted text:  %s \n", bruteForceDecryptText);
            System.out.println("This text is correctly decrypted? Input YES/NO");
            String userAnswer = scanner.nextLine();
            if (userAnswer.equalsIgnoreCase("yes")) {
                return true;
            } else if (userAnswer.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Incorrect input. Correct input - Yes or No");
            }
        }
        return false;
    }


}
