package com.javarush.cryptoanalizer.vinogradov.userInterface;

import com.javarush.cryptoanalizer.vinogradov.caesarCryptografy.CaesarBruteForce;
import com.javarush.cryptoanalizer.vinogradov.caesarCryptografy.CaesarWorker;
import com.javarush.cryptoanalizer.vinogradov.caesarCryptografy.exception.CaesarWorkerException;
import com.javarush.cryptoanalizer.vinogradov.filesWorker.exception.FileWorkerException;

import java.util.Scanner;

import static com.javarush.cryptoanalizer.vinogradov.constants.PhraseConstants.*;

public class UserUI {
    private final CaesarWorker caesarWorker;
    private final Scanner scanner;
    private final CaesarBruteForce caesarBruteForce;

    public UserUI() {
        caesarWorker = new CaesarWorker();
        caesarBruteForce = new CaesarBruteForce();
        scanner = new Scanner(System.in);
    }

    public void startingApplication() {
        showApplicationMenu();
        processCommand(getUserCommand());
    }

    private void showApplicationMenu() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(CHOOSE_UI_OPTION);
        for (UserCommands userCommands : UserCommands.values()) {
            String command = String.format("%d -- %s", userCommands.getNumberOfCommand(), userCommands.getDescriptionOfCommand());
            System.out.println(command);
        }
    }

    private void processCommand(UserCommands userCommands) {
        switch (userCommands) {
            case EXIT -> exitCommand();
            case ENCRYPT -> encryptingCommand();
            case DECRYPT -> decryptingCommand();
            case BRUTEFORCEDECRYPT -> bruteForceCommand();
        }
    }

    private UserCommands getUserCommand() {
        boolean tryAgain = false;
        do {
            try {
                int numberOfCommand = readInt();
                return UserCommands.getCommandByNumber(numberOfCommand);
            } catch (IllegalArgumentException exception) {
                System.out.println(WRONG_NUMBER_OF_OPERATION + exception.getMessage());
                System.out.println(WRITE_AGAIN);
                String userInput = scanner.nextLine();
                if (USER_AGAIN_PHRASE.equalsIgnoreCase(userInput)) {
                    tryAgain = true;
                }
            }
        } while (tryAgain);
        return UserCommands.EXIT;
    }

    private void encryptingCommand() {
        System.out.println(ENTER_ORIGINAL_FILENAME);
        String originalText = scanner.nextLine();
        System.out.println(ENTER_TO_ENCRYPT_FILENAME);
        String encryptFile = scanner.nextLine();
        System.out.println(ENTER_KEY_TO_CIPHER);
        int key = readInt();

        try {
            caesarWorker.encrypt(originalText, encryptFile, key);
            System.out.println(SUCCESS_ENCRYPT_MESSAGE + encryptFile);
        } catch (FileWorkerException | CaesarWorkerException ex) {
            System.err.println(ERROR_EX + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void decryptingCommand() {
        System.out.println(ENTER_ENCRYPT_TEXT_FILENAME);
        String encryptFile = scanner.nextLine();
        System.out.println(ENTER_FILENAME_TO_DECRYPT);
        String originalText = scanner.nextLine();
        System.out.println(ENTER_KEY_TO_CIPHER);
        int key = readInt();

        try {
            caesarWorker.decrypt(encryptFile, originalText, key);
            System.out.println(SUCCESS_DECRYPT_MESSAGE + originalText);
        } catch (CaesarWorkerException | FileWorkerException ex) {
            System.err.println(ERROR_EX + ex.getMessage());
            ex.printStackTrace();
        }
    }


    private void bruteForceCommand() {
        System.out.println(ENTER_ENCRYPT_TEXT_FILENAME);
        String encryptFile = scanner.nextLine();
        System.out.println(ENTER_FILENAME_TO_DECRYPT);
        String originalText = scanner.nextLine();
        System.out.println(SUCCESS_BRUTEFORCE_DECRYPT_MESSAGE);
        try{
        caesarBruteForce.bruteForceDecrypt(encryptFile, originalText);}
        catch (CaesarWorkerException | FileWorkerException ex){
            System.err.println(ERROR_EX + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void exitCommand() {
        return;
    }


    private int readInt() {
        String userInput = scanner.nextLine();
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException(NOT_A_NUMBER_MESSAGE);
        }
    }


}



