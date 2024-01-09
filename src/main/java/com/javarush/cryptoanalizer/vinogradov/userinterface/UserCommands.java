package com.javarush.cryptoanalizer.vinogradov.userinterface;

public enum UserCommands {
    EXIT(0, "Exit from app"),
    ENCRYPT(1, "Encrypt text from file with caesar cipher"),
    DECRYPT(2, "Decrypt text from file with caesar cipher"),
    BRUTEFORCEDECRYPT(3, "Decrypt caesar cipher with brute force method");

    private final int numberOfOperation;
    private final String descriptionOfOperation;


    UserCommands(int numberOfOperation, String descriptionOfOperation) {
        this.numberOfOperation = numberOfOperation;
        this.descriptionOfOperation = descriptionOfOperation;
    }

    public int getNumberOfCommand() {
        return numberOfOperation;
    }

    public String getDescriptionOfCommand() {
        return descriptionOfOperation;
    }

    public static UserCommands getCommandByNumber(int numberOfOperation) {
        for (UserCommands userCommands : UserCommands.values()) {
            if (userCommands.getNumberOfCommand() == numberOfOperation) {
                return userCommands;
            }
        }
        throw new IllegalArgumentException("Wrong number of operation");
    }


}
