package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Utils {

    public static int returnIndexOfNestedQuery(String[] arg, int indexOfOpenBracket, int indexOfCloseBracket) {
        String[] nestedQuery = Arrays.copyOfRange(arg, indexOfOpenBracket + 1, indexOfCloseBracket);
        return Reader.processNestedStatement(nestedQuery);
    }

    public static String returnAlias(String[] arg, int to, int from) {
        if (to - from == 2) {
            return arg[from + 1];
        }
        return null;
    }

    public static List<Integer> findIndexesOfComma(String[] arg) {
        List<Integer> indexOfComma = new ArrayList<>();
        for (int i = 0; i < arg.length; i++) {
            if (arg[i].equals(",")) {
                indexOfComma.add(i);
            }
        }
        indexOfComma.add(arg.length);
        return indexOfComma;
    }

    public static int findIndexOfSymbol(String[] arg, int start, int stop, String symbol) {
        return IntStream.range(start, stop)
                .filter(i -> arg[i].equals(symbol))
                .findFirst()
                .orElse(-1);
    }

    public static int findIndexOfSymbolInString(String[] arg, int indexOfWord, List<String> symbols) {
        String[] letters = arg[indexOfWord].split("");
        for (String symbol : symbols) {
            int delimiterIndex = findIndexOfSymbol(letters, 0, letters.length, symbol);
            if (delimiterIndex != -1) {
                return delimiterIndex;
            }
        }
        return -1;
    }
}
