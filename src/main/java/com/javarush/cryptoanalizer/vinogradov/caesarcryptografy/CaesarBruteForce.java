package com.javarush.cryptoanalizer.vinogradov.caesarcryptografy;

import com.javarush.cryptoanalizer.vinogradov.filesworker.FilenameValidator;
import com.javarush.cryptoanalizer.vinogradov.filesworker.FilesOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CaesarBruteForce {
    private CaesarCipher caesarCipher;
    private final FilenameValidator filenameValidator;
    private final FilesOperations filesOperations;
    private final CaesarAlphabet alphabet;

    public CaesarBruteForce(){
        this.alphabet = new CaesarAlphabet();
        this.caesarCipher = new CaesarCipher(alphabet);
        this.filesOperations = new FilesOperations();
        this.filenameValidator = new FilenameValidator();
    }

    public void bruteForceDecrypt(String encryptFilename, String decryptingFilename){
        filenameValidator.validateForReading(encryptFilename);
        filenameValidator.validateForWriting(decryptingFilename);

        StringBuilder stringBuilder = new StringBuilder();
        List<String> readableStrings = filesOperations.readFile(encryptFilename);
        for (String textString : readableStrings){
            stringBuilder.append(textString).append(System.lineSeparator());
        }
        for(int i = 0; i < alphabet.getAlphabetSize(); i++){
            String decryptString = caesarCipher.decrypt(stringBuilder.toString(), i);
            if(userValidateText(decryptString)){
                List<String> resultOfDecrypt = new ArrayList<>();
                resultOfDecrypt.add(decryptString);
                filesOperations.writeFile(decryptingFilename, resultOfDecrypt);
                System.out.printf("Текст успешно расшифрован с ключом %d и записан в файл + %s", i, decryptingFilename);
                break;
            }
        }
    }



    private boolean userValidateText(String bruteForceDecryptText){
        Scanner scanner = new Scanner(System.in);
        boolean successValidate = false;
        System.out.println(bruteForceDecryptText);
        if(bruteForceDecryptText.contains(", ") || bruteForceDecryptText.contains(". ") || bruteForceDecryptText.contains("? ") || bruteForceDecryptText.contains("! ")){
            successValidate = true;
        }
        while (successValidate){
            System.out.printf("Расшифрованный текст /n %s", bruteForceDecryptText);
            System.out.println("Текст верно расшифрован? Введите Да или Нет");
            String userAnswer = scanner.nextLine();
            if(userAnswer.equalsIgnoreCase("да")){
                successValidate = true;
            } else if(userAnswer.equalsIgnoreCase("нет")){
                successValidate = false;
            } else {
                System.out.println("Необрабатываемое значение. Введите Да или Нет");
            }
        }
        return false;
    }


}
