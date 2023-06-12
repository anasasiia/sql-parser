package org.example;

import org.example.elements.Column;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ToSelect {
    public static List<Column> run(String[] arg) {
        List<Column> columns = new ArrayList<>();

        int[] indexOfComma = new int[arg.length / 2 + 1];
        int index = 0;
        for (int i = 0; i < arg.length; i++) {
            if (arg[i].equals(",")) {
                indexOfComma[index] = i;
                index++;
            }
        }

        if (arg.length == 2) {
            indexOfComma[index] = arg.length - 1;
        } else {
            indexOfComma[index] = arg.length;
        }

        int start = 0;
        if (arg[1].equals("*")) {
            Column column = new Column();
            column.setName("all columns");
            column.setTableAndColumnName(Map.of(arg[1], "empty"));
            columns.add(column);
        } else {
            for (int stop : indexOfComma) {
                if (stop != 0) {
                    Column column = new Column();
                    String name;
                    Map<String, String> tableAndColumnName = new HashMap<>();

                    if (stop - (start + 1) > 1) {
                        name = arg[start + 3];
                        tableAndColumnName.put(arg[start + 1], "empty");
                    } else {
                        name = "one column";
                        String str = arg[start + 1];
                        if (arg[start + 1].contains(".") || arg[start + 1].contains("_")) {
                            int commaIndex = 0;
                            for (int i = 0; i < str.length(); i++) {
                                if (str.charAt(i) == '.' || str.charAt(i) == '_') {
                                    commaIndex = i;
                                    break;
                                }
                            }
                            tableAndColumnName.put(str.substring(commaIndex + 1),
                                    str.substring(0, commaIndex));
                        } else {
                            tableAndColumnName.put(arg[start + 1], "empty");
                        }
                    }
                    column.setName(name);
                    column.setTableAndColumnName(tableAndColumnName);
                    columns.add(column);
                    start = stop;
                }
            }
        }
        return columns;
    }
}
