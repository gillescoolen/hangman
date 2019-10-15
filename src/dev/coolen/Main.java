package dev.coolen;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var hangman = new Hangman(scanner);
        hangman.initialize();
        scanner.close();
    }
}
