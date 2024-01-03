package com.javarush.cryptoanalizer.vinogradov.applicationstarter;

import com.javarush.cryptoanalizer.vinogradov.caesarcryptografy.CaesarWorker;
import com.javarush.cryptoanalizer.vinogradov.userinterface.UserUI;

public class ApplicationWorker {
    public static void main(String[] args) {
        UserUI userUI = new UserUI();
        userUI.startingApplication();
    }


//    CaesarAlphabet alphabet = new CaesarAlphabet();
//    FilesOperations filesOperations = new FilesOperations();
//    CaesarWorker caesarWorker = new CaesarWorker();
//    CaesarBruteForce caesarBruteForce = new CaesarBruteForce();
//    CaesarCipher caesarCipher = new CaesarCipher(new CaesarAlphabet());
////        List<String> readable = filesOperations.readFile("src/main/java/com/javarush/cryptoanalizer/vinogradov/filesworker/test");
////        System.out.println(readable);
//    //  caesarWorker.encrypt("src/main/java/com/javarush/cryptoanalizer/vinogradov/filesworker/testRead123456","src/main/java/com/javarush/cryptoanalizer/vinogradov/filesworker/test", 3);
////        //filesOperations.writeFile("src/main/java/com/javarush/cryptoanalizer/vinogradov/filesworker/test1", readable);
//        caesarBruteForce.bruteForceDecrypt("src/main/java/com/javarush/cryptoanalizer/vinogradov/filesworker/test5", "src/main/java/com/javarush/cryptoanalizer/vinogradov/filesworker/bruteForce");
//
//}
}
