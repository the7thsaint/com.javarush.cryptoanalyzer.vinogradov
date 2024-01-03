package com.javarush.cryptoanalizer.vinogradov.caesarcryptografy;

import com.javarush.cryptoanalizer.vinogradov.caesarcryptografy.exception.CaesarWorkerException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaesarAlphabet {

    private final List<Character> alphabet;
    private final Map<Character, Integer> symbolsIndexes;
    private static final Character[] RU_WITH_SYMBOLS_ALPHABET = {
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ', '\n'
    };

    public CaesarAlphabet() {
        alphabet = new ArrayList<>(Arrays.asList(RU_WITH_SYMBOLS_ALPHABET));
        symbolsIndexes = new HashMap<>();
        for (int i = 0; i < alphabet.size(); i++) {
            symbolsIndexes.put(alphabet.get(i), i);
        }
    }

    public Character getCharByIndex(int index) {
        if (index > alphabet.size()) {
            throw new CaesarWorkerException("Input valid index. Your index - " + index + "Your index < 0 || > " + alphabet.size());
        }
        return alphabet.get(index);
    }

    public int getCharIndex(Character character) {
        if (!symbolsIndexes.containsKey(character)) {
            throw new CaesarWorkerException("Invalid character. Your char - " + character);
        }
        return symbolsIndexes.get(character);
    }

    public int getAlphabetSize() {
        return symbolsIndexes.size();
    }


}
