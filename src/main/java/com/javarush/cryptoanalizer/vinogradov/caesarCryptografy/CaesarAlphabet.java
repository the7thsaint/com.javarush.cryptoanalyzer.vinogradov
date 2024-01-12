package com.javarush.cryptoanalizer.vinogradov.caesarCryptografy;

import com.javarush.cryptoanalizer.vinogradov.caesarCryptografy.exception.CaesarWorkerException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.javarush.cryptoanalizer.vinogradov.constants.PhraseConstants.*;

public class CaesarAlphabet {

    private final List<Character> alphabet;
    private final Map<Character, Integer> symbolsIndexes;
    private static final Character[] RU_WITH_SYMBOLS_ALPHABET;
    private static final Character[] EN_WITH_SYMBOLS_ALPHABET;

    static {

        RU_WITH_SYMBOLS_ALPHABET = new Character[]{
                'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
                'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
                'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ', '\n'
        };
        EN_WITH_SYMBOLS_ALPHABET = new Character[]{
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ', '\n'
        };

    }

    public CaesarAlphabet(List<String> languageFile) {
        if (isRussianLanguage(languageFile)) {
            alphabet = new ArrayList<>(Arrays.asList(RU_WITH_SYMBOLS_ALPHABET));
        } else {
            alphabet = new ArrayList<>(Arrays.asList(EN_WITH_SYMBOLS_ALPHABET));
        }
        symbolsIndexes = new HashMap<>();
        for (int i = 0; i < alphabet.size(); i++) {
            symbolsIndexes.put(alphabet.get(i), i);
        }
    }

    public Character getCharByIndex(int index) {
        if (index > alphabet.size()) {
            throw new CaesarWorkerException(INVALID_USER_INPUT_INDEX + index + SHOW_USER_INDEX + alphabet.size());
        }
        return alphabet.get(index);
    }

    public int getCharIndex(Character character) {
        if (!symbolsIndexes.containsKey(character)) {
            throw new CaesarWorkerException(INVALID_CHARACTER_MESSAGE + character);
        }
        return symbolsIndexes.get(character);
    }

    public int getAlphabetSize() {
        return symbolsIndexes.size();
    }

    private boolean isRussianLanguage(List<String> readedFile) {
        return readedFile.get(0).matches(CHOOSE_LANGUAGE_REGEX);
    }

}
