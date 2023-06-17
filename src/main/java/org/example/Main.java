package org.example;

public class Main {

    public static void main(String[] args) {
        try {
            Reader.readStatement();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}