package org.example;

import org.example.elements.Column;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ToSelect {
    public static List<Column> run(String[] arg) {
        List<Column> columns = new ArrayList<>();

        int index = 0;
        int[] indexOfComma = new int[arg.length / 2 + 1];
        for (int i = 0; i < arg.length; i++) {
            if (arg[i].equals(",")) {
                indexOfComma[index] = i;
                index++;
            }
        }
        indexOfComma[index] = arg.length - 1;
        indexOfComma = Arrays.stream(indexOfComma)
                .filter(i -> i != 0)
                .toArray();

        int start = 0;
        int stop;

        for (int j : indexOfComma) {
            stop = j;
            Column column = new Column();
            String name;
            Map<String, String> tableAndColumnName = new HashMap<>();

            if (arg[start + 1].equals("*")) {
                name = "all columns";
                tableAndColumnName.put(arg[start + 1], "empty");
            } else if (stop - (start + 1) > 1) {
                name = arg[start + 1];
                tableAndColumnName.put(arg[start + 1], "empty");
            } else {
                name = "one column";
                String str = arg[start + 1];
                int commaIndex = IntStream.range(0, str.length())
                        .filter(i -> str.charAt(i) == '.' || str.charAt(i) == '_')
                        .findFirst()
                        .orElse(-1);

                if (commaIndex == -1) {
                    tableAndColumnName.put(arg[start + 1], "empty");
                } else {
                    tableAndColumnName.put(str.substring(commaIndex + 1),
                            str.substring(0, commaIndex));
                }
            }
            column.setName(name);
            column.setTableAndColumnName(tableAndColumnName);
            columns.add(column);

            start = stop;
        }
        return columns;
    }
}
