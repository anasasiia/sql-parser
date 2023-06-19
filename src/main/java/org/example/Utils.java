package org.example;

import org.example.elements.ColumnSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Utils {
    public static ColumnSource prepareColumnSourceWithNestedQuery(String[] arg, int indexOfOpenBracket, int lengthOfArg) {
        ColumnSource columnSource = new ColumnSource();

        int indexOfCloseBracket = IntStream.range(indexOfOpenBracket, lengthOfArg)
                .filter(i -> arg[i].equals(")"))
                .findFirst()
                .orElse(-1);

        String[] nestedQuery = Arrays.copyOfRange(arg, indexOfOpenBracket + 1, indexOfCloseBracket);
        int indexOfNestedQuery = Reader.processNestedStatement(nestedQuery);

        columnSource.setIndexOfNestedQuery(indexOfNestedQuery);

        if (lengthOfArg - indexOfCloseBracket > 0) {
            columnSource.setAlias(arg[indexOfCloseBracket + 1]);
        }
        return columnSource;
    }

    public static List<Integer> findIndexesOfComma(String[] arg) {
        List<Integer> indexOfComma = new ArrayList<>();
        for (int i = 0; i < arg.length; i++) {
            if (arg[i].equals(",")) {
                indexOfComma.add(i);
            }
        }
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
