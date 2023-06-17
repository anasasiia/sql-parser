package org.example;

import org.example.elements.Source;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ToGetFrom {
    public static List<Source> run(String[] arg) throws FileNotFoundException {
        List<Source> fromSources = new ArrayList<>();
        List<Integer> indexOfComma = new ArrayList<>();
        for (int i = 0; i < arg.length; i++) {
            if (arg[i].equals(",")) {
                indexOfComma.add(i);
            }
        }

        if (arg.length == 2) {
            indexOfComma.add(arg.length - 1);
        } else {
            indexOfComma.add(arg.length);
        }

        int start = 0;
        for (Integer stop: indexOfComma) {
            Source source = new Source();
            if (arg[start + 1].equals("(")) {
                Source source1 = prepareSourceWithNestedQuery(arg, start + 1, arg.length);
                fromSources.add(source1);
                break;
            } else {
                if (stop - (start + 1) == 2) {
                    source.setAlias(arg[start + 2]);
                }
                source.setTableName(arg[start + 1]);
            }
            fromSources.add(source);

            if (stop - (start + 1) > 2) {
                int indexOfOpenBracket = IntStream.range(start, arg.length)
                        .filter(i -> arg[i].equals("("))
                        .findFirst()
                        .orElse(-1);
                Source source1 = prepareSourceWithNestedQuery(arg, indexOfOpenBracket, arg.length);
                fromSources.add(source1);
                break;
            }
            start = stop;
        }
        return fromSources;
    }

    private static Source prepareSourceWithNestedQuery(String[] arg, int indexOfOpenBracket, int lengthOfArg) {
        Source source = new Source();

        int indexOfCloseBracket = IntStream.range(indexOfOpenBracket, lengthOfArg)
                .filter(i -> arg[i].equals(")"))
                .findFirst()
                .orElse(-1);

        String[] nestedQuery = Arrays.copyOfRange(arg, indexOfOpenBracket + 1, indexOfCloseBracket);
        int indexOfNestedQuery = Reader.processNestedStatement(nestedQuery);

        source.setIndexOfNestedQuery(indexOfNestedQuery);

        if (lengthOfArg - indexOfCloseBracket > 0) {
            source.setAlias(arg[indexOfCloseBracket + 1]);
        }
        return source;
    }
}
