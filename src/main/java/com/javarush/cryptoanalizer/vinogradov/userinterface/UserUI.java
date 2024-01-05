package com.javarush.cryptoanalizer.vinogradov.userinterface;

import com.javarush.cryptoanalizer.vinogradov.caesarcryptografy.CaesarBruteForce;
import com.javarush.cryptoanalizer.vinogradov.caesarcryptografy.CaesarWorker;
import com.javarush.cryptoanalizer.vinogradov.caesarcryptografy.exception.CaesarWorkerException;
import com.javarush.cryptoanalizer.vinogradov.filesworker.exception.FileWorkerException;

import java.util.Scanner;

public class UserUI {
    private final CaesarWorker caesarWorker;
    private final Scanner scanner;
    private final CaesarBruteForce caesarBruteForce;
    private final static String WELCOME_MESSAGE = """
            **************************************************************
            *------------Welcome to the caesar cipher program------------*
            **************************************************************
            """;
    private final static String USER_AGAIN_PHRASE = "Again";

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
        System.out.println("Choose application option");
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
                System.out.println("Number of operation is wrong. \n Reason: " + exception.getMessage());
                System.out.println("Write 'Again' to trying more");
                String userInput = scanner.nextLine();
                if (USER_AGAIN_PHRASE.equalsIgnoreCase(userInput)) {
                    tryAgain = true;
                }
            }
        } while (tryAgain);
        return UserCommands.EXIT;
    }

    private void encryptingCommand() {
        System.out.println("Enter filename which contains original text:");
        String originalText = scanner.nextLine();
        System.out.println("Enter file name where you want to save encrypting file:");
        String encryptFile = scanner.nextLine();
        System.out.println("Enter key for Caesar cipher: ");
        int key = readInt();

        try {
            caesarWorker.encrypt(originalText, encryptFile, key);
            System.out.println("Successful encrypt. Check your file! Address - " + encryptFile);
        } catch (FileWorkerException | CaesarWorkerException ex) {
            System.err.println("Error! Reason: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void decryptingCommand() {
        System.out.println("Enter filename which contains encrypting text: ");
        String encryptFile = scanner.nextLine();
        System.out.println("Enter file name where you want to save decrypting file: ");
        String originalText = scanner.nextLine();
        System.out.println("Enter key for Caesar cipher: ");
        int key = readInt();

        try {
            caesarWorker.decrypt(encryptFile, originalText, key);
            System.out.println("Successful decrypt. Check your file! Address - " + originalText);
        } catch (CaesarWorkerException | FileWorkerException ex) {
            System.err.println("Error!" + ex.getMessage());
            ex.printStackTrace();
        }
    }


    private void bruteForceCommand() {
        System.out.println("Enter filename which contains encrypting text: ");
        String encryptFile = scanner.nextLine();
        System.out.println("Enter file name when you want to save decrypting file: ");
        String originalText = scanner.nextLine();
        System.out.println("Brute force decrypt starting. Please check information in console!");
        try{
        caesarBruteForce.bruteForceDecrypt(encryptFile, originalText);}
        catch (CaesarWorkerException | FileWorkerException ex){
            System.err.println("Error!" + ex.getMessage());
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
            throw new NumberFormatException("Your input not a number");
        }
    }


}



