package org.example;

import org.example.elements.Join;

import java.util.ArrayList;
import java.util.List;

public class ToJoin {
    private static final List<Join> joins = new ArrayList<>();
    public static void run(String[] arg) {
        Join join = new Join();
        join.setJoinType(arg[0]);
        join.setTable2(arg[2]);

        if (!join.getJoinType().equals("CROSS")) {
            int index1 = arg[5].lastIndexOf('.');
            String table1 = arg[5].substring(0, index1);
            join.setTable1(table1);
            join.setColumnOfTable1(arg[5].substring(index1 + 1));

            int index2 = arg[7].lastIndexOf('.');
            join.setColumnOfTable2(arg[7].substring(index2 + 1));
        }
        joins.add(join);
    }

    public static List<Join> getJoins() {
        return joins;
    }
}
