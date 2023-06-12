package org.example;

import org.example.elements.WhereClause;

import java.util.ArrayList;
import java.util.List;

public class ToGetWhere {
    public static List<WhereClause> run(String[] arg) {
        List<WhereClause> whereClauses = new ArrayList<>();

        int[] indexOfAnd = new int[arg.length / 2 + 1];
        int index = 0;
        for (int i = 0; i < arg.length; i++) {
            if (i >= 2 && !arg[i - 2].equals("BETWEEN") && arg[i].equals("AND")) {
                indexOfAnd[index] = i;
                index++;
            }
        }

        int start = 0;

        for (int value : indexOfAnd) {
            int stop = value;
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
            if (stop == 0) {
                stop = arg.length;
                for (int j = start + 3; j < stop; j++) {
                    if (!arg[j].equals("IN") && !arg[j].equals("LIKE") && !arg[j].equals("IS")
                            && !arg[j].equals("AND") && !arg[j].equals(",")) {
                        values.add(arg[j]);
                    }
                }
                whereClause.setValue(values);
                whereClauses.add(whereClause);
                break;
            }
            for (int k = start + 3; k < stop; k++) {
                if (!arg[k].equals("IN") && !arg[k].equals("LIKE") && !arg[k].equals("IS")
                        && !arg[k].equals("AND")) {
                    values.add(arg[k]);
                }
            }
            whereClause.setValue(values);
            whereClauses.add(whereClause);
            start = stop;
        }
        return whereClauses;
    }
}
