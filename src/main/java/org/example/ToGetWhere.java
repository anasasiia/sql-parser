package org.example;

import org.example.elements.WhereClause;

import java.util.ArrayList;
import java.util.List;

public class ToGetWhere {
    private static final List<String> nonValues = List.of("IN", "LIKE", "IS", "AND", ",");

    public static List<WhereClause> run(String[] arg) {
        List<WhereClause> whereClauses = new ArrayList<>();

        List<Integer> indexOfAnd = new ArrayList<>();
        for (int i = 0; i < arg.length; i++) {
            if (i >= 2 && !arg[i - 2].equals("BETWEEN") && arg[i].equals("AND")) {
                indexOfAnd.add(i);
            }
        }
        indexOfAnd.add(arg.length);

        int start = 0;

        for (Integer stop : indexOfAnd) {
            WhereClause whereClause = new WhereClause();
            whereClause.setObject(arg[start + 1]);

            List<String> clauses = new ArrayList<>();

            if (arg[start + 2].equals("NOT")) {
                String clause = arg[start + 2] + " " + arg[start + 3];
                clauses.add(clause);
            } else if (arg[start + 2].equals("BETWEEN")) {
                clauses.add(arg[start + 2]);
                clauses.add(arg[start + 4]);
            } else {
                clauses.add(arg[start + 2]);
            }
            whereClause.setClause(clauses);

            List<String> values = new ArrayList<>();
            for (int j = start + 3; j < stop; j++) {
                if (!getNonValues().contains(arg[j])) {
                    values.add(arg[j]);
                }
            }
            whereClause.setValue(values);
            whereClauses.add(whereClause);

            start = stop;
        }
        return whereClauses;
    }

    public static List<String> getNonValues() {
        return nonValues;
    }
}
