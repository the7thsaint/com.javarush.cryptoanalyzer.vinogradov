package com.javarush.cryptoanalizer.vinogradov.userInterface;

import static com.javarush.cryptoanalizer.vinogradov.constants.PhraseConstants.*;

public enum UserCommands {
    EXIT(0, UI_EXIT),
    ENCRYPT(1, UI_ENCRYPT),
    DECRYPT(2, UI_DECRYPT),
    BRUTEFORCEDECRYPT(3, UI_BRUTEFORCEDECRYPT);

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
        throw new IllegalArgumentException(WRONG_NUMBER_EX);
    }


}
