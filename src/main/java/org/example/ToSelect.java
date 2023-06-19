package org.example;

import org.example.elements.Column;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToSelect {
    public static List<Column> run(String[] arg) {
        List<Column> columns = new ArrayList<>();

        List<Integer> indexOfComma = Utils.findIndexesOfComma(arg);

        int start = 0;

        for (Integer stop : indexOfComma) {
            int indexOfOpenBracket = Utils.findIndexOfSymbol(arg, start, stop, "(");
            Column column1 = setColumn(arg, start, stop);
            columns.add(column1);

            if (stop - (start + 1) > 3 || arg[start + 1].equals("(")) {
                Column column2 = new Column();
                column2.setIndexOfNestedQuery(Utils.returnIndexOfNestedQuery(arg, indexOfOpenBracket));
                int indexOfCloseBracket = Utils.findIndexOfSymbol(arg, indexOfOpenBracket, arg.length, ")");
                column2.setAlias(Utils.returnAlias(arg, indexOfCloseBracket, arg.length));
                columns.add(column2);
                break;
            }
            start = stop;
        }
        return columns;
    }

    private static Column setColumn(String[] arg, int start, int stop) {
        Column column = new Column();
        String alias = null;
        Map<String, String> tableAndColumnName = new HashMap<>();

        if (stop - (start + 1) == 3) {
            alias = arg[start + 3];
        }

        int delimiterIndex = Utils.findIndexOfSymbolInString(arg, start + 1, List.of(".", "_"));

        if (delimiterIndex == -1) {
            tableAndColumnName.put(arg[start + 1], "");
        } else {
            tableAndColumnName.put(arg[start + 1].substring(delimiterIndex + 1),
                    arg[start + 1].substring(0, delimiterIndex));
        }
        column.setAlias(alias);
        column.setTableAndColumnName(tableAndColumnName);
        return column;
    }
}
