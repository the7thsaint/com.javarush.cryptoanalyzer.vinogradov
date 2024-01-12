package com.javarush.cryptoanalizer.vinogradov.constants;

public class PhraseConstants {
    public static final String INVALID_CHARACTER_MESSAGE = "Invalid character. Your char - ";
    public static final String INVALID_USER_INPUT_INDEX = "Input valid index. Your index - ";
    public static final String SHOW_USER_INDEX = "Your index < 0 || > ";
    public static final String USER_POSITIVE_ANSWER = "yes";
    public static final String USER_NEGATIVE_ANSWER = "no";
    public static final String RU_WORDS_LIBRARY = "src/main/java/com/javarush/cryptoanalizer/vinogradov/caesarcryptografy/wordslibrary/RussianWordsLibrary.txt";
    public static final String ENG_WORDS_LIBRARY = "src/main/java/com/javarush/cryptoanalizer/vinogradov/caesarCryptografy/wordsLibrary/EnglishWordsLibrary.txt";
    public static final String CORRECT_BRUTE_FORCE_DECRYPT = "User text is correctly decrypted with key: %d \nText written to the file - %s";
    public static final String SHOW_DECRYPTED_TEXT = "Decrypted text:\n%s\n";
    public static final String SHOW_ANSWER_FOR_USER = "This text is correctly decrypted? Input YES/NO";
    public static final String INCORRECT_USER_ANSWER_FOR_BRUTE_FORCE_DECRYPT = "Incorrect input. Correct input - Yes or No";
    public static final String USE_INCORRECT_FILES = "Used incorrect files ";
    public static final String INVALID_PATH = "Invalid path: ";
    public static final String NOT_A_FILE = "This file is directory";
    public static final String FILE_DOESNT_EXIST = "File doesn't exists";
    public static final String UNREADBLE_FILE = "Cannot read to this file";
    public static final String UI_EXIT = "Exit from app";
    public static final String UI_ENCRYPT = "Encrypt text from file with caesar cipher";
    public static final String UI_DECRYPT = "Decrypt text from file with caesar cipher";
    public static final String UI_BRUTEFORCEDECRYPT = "Decrypt caesar cipher with brute force method";
    public static final String WRONG_NUMBER_EX = "Wrong number of operation";
    public final static String WELCOME_MESSAGE = """
            **************************************************************
            *------------Welcome to the caesar cipher program------------*
            **************************************************************
            """;
    public final static String USER_AGAIN_PHRASE = "Again";
    public final static String CHOOSE_UI_OPTION = "Choose application option";
    public final static String WRONG_NUMBER_OF_OPERATION = "Number of operation is wrong. \n Reason: ";
    public final static String WRITE_AGAIN = "Write 'Again' to trying more";
    public final static String ENTER_ORIGINAL_FILENAME = "Enter filename which contains original text:";
    public final static String ENTER_TO_ENCRYPT_FILENAME = "Enter file name where you want to save encrypting file:";
    public final static String ENTER_KEY_TO_CIPHER = "Enter key for Caesar cipher: ";
    public final static String SUCCESS_ENCRYPT_MESSAGE = "Successful encrypt. Check your file! Address - ";
    public final static String ERROR_EX = "Error!";
    public final static String ENTER_ENCRYPT_TEXT_FILENAME = "Enter filename which contains encrypting text: ";
    public final static String ENTER_FILENAME_TO_DECRYPT = "Enter file name where you want to save decrypting file: ";
    public final static String SUCCESS_DECRYPT_MESSAGE = "Successful decrypt. Check your file! Address - ";
    public final static String SUCCESS_BRUTEFORCE_DECRYPT_MESSAGE = "Brute force decrypt starting. Please check information in console!";
    public final static String NOT_A_NUMBER_MESSAGE = "Your input not a number";
    public final static String BRUTE_FORCE_SEARCH_REGEX = "[^A-Za-zА-Яа-яё.\\s]+";
    public final static String BRUTE_FORCE_SPLIT_REGEX = "\\s+";
    public final static String CHOOSE_LANGUAGE_REGEX = "[а-яёА-ЯЁ]+";


}
