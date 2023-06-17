package org.example;

import org.example.elements.Column;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ToSelect {
    public static List<Column> run(String[] arg) throws FileNotFoundException {
        List<Column> columns = new ArrayList<>();

        List<Integer> indexOfComma = new ArrayList<>();
        for (int i = 0; i < arg.length; i++) {
            if (arg[i].equals(",")) {
                indexOfComma.add(i);
            }
        }
        indexOfComma.add(arg.length);

        int start = 0;

        for (Integer stop : indexOfComma) {
            Column column = new Column();
            String alias = null;
            Map<String, String> tableAndColumnName = new HashMap<>();

            int indexOfOpenBracket = IntStream.range(start, stop)
                    .filter(i -> arg[i].equals("("))
                    .findFirst()
                    .orElse(-1);

            if (stop - (start + 1) == 3) {
                alias = arg[start + 3];
                tableAndColumnName.put(arg[start + 1], "");
            }

            if (stop - start == 2 || indexOfOpenBracket - start > 1) {
                String str = arg[start + 1];
                int delimiterIndex = IntStream.range(0, str.length())
                        .filter(i -> str.charAt(i) == '.' || str.charAt(i) == '_')
                        .findFirst()
                        .orElse(-1);

                if (delimiterIndex == -1) {
                    tableAndColumnName.put(arg[start + 1], "");
                } else {
                    tableAndColumnName.put(str.substring(delimiterIndex + 1),
                            str.substring(0, delimiterIndex));
                }
            }
            column.setAlias(alias);
            column.setTableAndColumnName(tableAndColumnName);
            columns.add(column);

            if (stop - (start + 1) > 3 || arg[start + 1].equals("(")) {
                Column column1 = prepareColumnWithNestedQuery(arg, indexOfOpenBracket, arg.length);
                columns.add(column1);
                break;
            }
            start = stop;
        }
        return columns;
    }

    private static Column prepareColumnWithNestedQuery(String[] arg, int indexOfOpenBracket, int lengthOfArg) {
        Column column = new Column();

        int indexOfCloseBracket = IntStream.range(indexOfOpenBracket, lengthOfArg)
                .filter(i -> arg[i].equals(")"))
                .findFirst()
                .orElse(-1);

        String[] nestedQuery = Arrays.copyOfRange(arg, indexOfOpenBracket + 1, indexOfCloseBracket);
        int indexOfNestedQuery = Reader.processNestedStatement(nestedQuery);

        column.setIndexOfNestedQuery(indexOfNestedQuery);

        if (lengthOfArg - indexOfCloseBracket > 0) {
            column.setAlias(arg[indexOfCloseBracket + 1]);
        }
        return column;
    }
}
