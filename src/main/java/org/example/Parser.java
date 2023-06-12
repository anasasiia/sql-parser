package org.example;

import org.example.elements.Column;
import org.example.elements.Join;
import org.example.elements.Sort;
import org.example.elements.Source;
import org.example.elements.WhereClause;

import java.io.IOException;
import java.util.List;

public class Parser {
   public static Query query = new Query();

    public static void parse(String line) throws IOException {
        line = line.replace(",", " ,");
        line = line.replace("(", "");
        line = line.replace(")", "");
        line = line.replace("'", "");

        String[] arg = line.split(" ");

        switch (arg[0]) {
            case "SELECT" -> {
                List<Column> selections = ToSelect.run(arg);
                query.setSelections(selections);
            }
            case "FROM" -> {
                List<Source> sources = ToGetFrom.run(arg);
                query.setFromSources(sources);
            }
            case "INNER", "LEFT", "RIGHT", "FULL", "CROSS" -> {
                ToJoin.run(arg);
                List<Join> joins = ToJoin.getJoins();
                query.setJoins(joins);
            }
            case "WHERE" -> {
                List<WhereClause> clauses = ToGetWhere.run(arg);
                query.setWhereClauses(clauses);
            }
            case "GROUP" -> {
                List<String> columns = ToGroup.run(arg);
                query.setGroupByColumns(columns);
            }
            case "ORDER" -> {
                List<Sort> sortColumns = ToOrder.run(arg);
                query.setSortColumns(sortColumns);
            }
            case "OFFSET" -> query.setOffset(Integer.parseInt(arg[1]));
            case "LIMIT" -> query.setLimit(Integer.parseInt(arg[1]));
        }
    }

//    private static String replaceSymbols(String line, String oldSymbol, String newSymbol) {
//        int oldSymbolLength = oldSymbol.length();
//        StringBuilder stringBuilder = new StringBuilder(line);
//
//        for (int i = 0; i < line.length(); i++) {
//            int index = stringBuilder.indexOf(oldSymbol, i);
//
//            if (index == -1) {
//                if (i == 0) {
//                    return line;
//                }
//                return stringBuilder.toString();
//            }
//            stringBuilder.replace(index, index + oldSymbolLength, newSymbol);
//        }
//        return stringBuilder.toString();
//    }

    public static Query getQuery() {
        return query;
    }

    public static String queryToString() {
         return query.toString();
    }
}
