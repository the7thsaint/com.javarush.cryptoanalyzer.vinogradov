package com.javarush.cryptoanalizer.vinogradov.caesarcryptografy;

import com.javarush.cryptoanalizer.vinogradov.filesworker.FilenameValidator;
import com.javarush.cryptoanalizer.vinogradov.filesworker.FilesOperations;

import java.util.ArrayList;
import java.util.List;

public class CaesarWorker {
    private CaesarCipher caesarCipher;
    private FilenameValidator filenameValidator;
    private FilesOperations filesOperations;
    public CaesarWorker(){
        this.caesarCipher = new CaesarCipher(new CaesarAlphabet());
        this.filenameValidator = new FilenameValidator();
        this.filesOperations = new FilesOperations();
    }

    public void encrypt(String readableFile, String writableFile, int key){
        filenameValidator.validateForReading(readableFile);
        filenameValidator.validateForWriting(writableFile);

        List<String> readableStrings = filesOperations.readFile(readableFile);
        List<String> result1 = new ArrayList<>();
        for(String result : readableStrings){
            result1.add(caesarCipher.encrypt(result,key));
        }
        filesOperations.writeFile(writableFile,result1);
    }

    public void decrypt(String readableFile, String writableFile, int key){
        filenameValidator.validateForReading(readableFile);
        filenameValidator.validateForWriting(writableFile);

        List<String> readableStrings = filesOperations.readFile(readableFile);
        List<String> result1 = new ArrayList<>();
        for(String result : readableStrings){
            result1.add(caesarCipher.decrypt(result,key));
        }
        filesOperations.writeFile(writableFile,result1);
    }
}
