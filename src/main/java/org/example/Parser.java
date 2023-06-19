package org.example;

import org.example.elements.Column;
import org.example.elements.Join;
import org.example.elements.Sort;
import org.example.elements.Source;
import org.example.elements.WhereClause;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Parser {
   private static final Query QUERY = new Query();

   private static final List<Query> NESTED_QUERIES = new ArrayList<>();
   private static int countOfNestedQueries = 0;

   public static void parse(String line, Query queryToParse) {
       String[] arg = line.split(" ");

       switch (arg[0]) {
           case "SELECT" -> {
               List<Column> selections = ToSelect.run(arg);
               queryToParse.setSelections(selections);
           }
           case "FROM" -> {
               List<Source> sources = ToGetFrom.run(arg);
               queryToParse.setFromSources(sources);
           }
           case "INNER", "LEFT", "RIGHT", "FULL", "CROSS" -> {
               ToJoin.run(arg);
               List<Join> joins = ToJoin.getJoins();
               queryToParse.setJoins(joins);
           }
           case "WHERE" -> {
               List<WhereClause> clauses = ToGetWhere.run(arg);
               queryToParse.setWhereClauses(clauses);
           }
           case "GROUP" -> {
               List<String> columns = ToGroup.run(arg);
               queryToParse.setGroupByColumns(columns);
           }
           case "ORDER" -> {
               List<Sort> sortColumns = ToOrder.run(arg);
               queryToParse.setSortColumns(sortColumns);
           }
           case "OFFSET" -> queryToParse.setOffset(Integer.parseInt(arg[1]));
           case "LIMIT" -> queryToParse.setLimit(Integer.parseInt(arg[1]));
           default -> System.out.println("unsupported statement");
       }
   }

    public static void parseQuery(String line) {
        line = prepareStatement(line);
        parse(line, getQuery());
    }

    public static int parseNestedQuery(List<String> request) {
        Query nestedQuery = new Query();

        request.forEach(line -> {
            try {
                parse(line, nestedQuery);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        NESTED_QUERIES.add(nestedQuery);
        int index = getCountOfNestedQueries();
        setCountOfNestedQueries(getCountOfNestedQueries() + 1);
        return index;
    }

    private static String prepareStatement(String line) {
        line = replaceSymbols(line, ",", " ,");
        line = replaceSymbols(line, "(", "( ");
        line = replaceSymbols(line, ")", " )");
        line = replaceSymbols(line, "'", "");
        return line;
    }

    private static String replaceSymbols(String line, String oldSymbol, String newSymbol) {
        int oldSymbolLength = oldSymbol.length();
        StringBuilder stringBuilder = new StringBuilder(line);

        int[] indexes = IntStream.range(0, line.length())
                .filter(i -> String.valueOf(line.charAt(i)).equals(oldSymbol))
                .toArray();

        if (Arrays.stream(indexes).count() != 0) {
            for (int i = indexes.length - 1; i >= 0; i--) {
                stringBuilder.replace(indexes[i], indexes[i] + oldSymbolLength, newSymbol);
            }
        }
        return stringBuilder.toString();
    }

    public static Query getQuery() {
        return QUERY;
    }

    public static int getCountOfNestedQueries() {
        return countOfNestedQueries;
    }

    public static List<Query> getNestedQueries() {
        return NESTED_QUERIES;
    }

    public static void setCountOfNestedQueries(int countOfNestedQueries) {
        Parser.countOfNestedQueries = countOfNestedQueries;
    }
    public static String queryToString() {
         return QUERY.toString();
    }
}
