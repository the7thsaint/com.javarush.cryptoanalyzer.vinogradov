package com.javarush.cryptoanalizer.vinogradov.caesarcryptografy;

public class CaesarCipher {
    private final CaesarAlphabet alphabet;

    public CaesarCipher(CaesarAlphabet alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String currentText, int key) {
        return cipher(currentText, key);
    }

    public String decrypt(String currentText, int key) {
        return cipher(currentText, -key);
    }

    private String cipher(String currentText, int key) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < currentText.length(); i++) {
            Character currentTextChar = Character.toLowerCase(currentText.charAt(i));
            int currentTextCharIndex = alphabet.getCharIndex(currentTextChar);
            int resultTextCharIndex = (alphabet.getAlphabetSize() + (currentTextCharIndex + key)) % alphabet.getAlphabetSize();
            stringBuilder.append(alphabet.getCharByIndex(resultTextCharIndex));
        }
        return stringBuilder.toString();
    }

}
