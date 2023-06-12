package org.example;

import org.example.elements.Join;

import java.util.ArrayList;
import java.util.List;

public class ToJoin {
    private static final List<Join> joins = new ArrayList<>();
    public static void run(String[] arg) {
        Join join = new Join();
        join.setJoinType(arg[0]);
        join.setWhatTableToJoin(arg[2]);

        if (!join.getJoinType().equals("CROSS")) {
            String column1 = arg[4];
            int index1 = 0;
            for (int i = 0; i < column1.length(); i++) {
                if (column1.charAt(i) == '.') {
                    index1 = i;
                    break;
                }
            }
            String tableWhereToJoin = column1.substring(0, index1);
            column1 = column1.substring(index1 + 1, column1.length());

            join.setTableWhereToJoin(tableWhereToJoin);
            join.setColumnWhereToJoin(column1);

            String column2 = arg[6];
            int index2 = 0;
            for (int i = 0; i < column2.length(); i++) {
                if (column2.charAt(i) == '.') {
                    index2 = i;
                    break;
                }
            }
            column2 = column2.substring(index2 + 1, column2.length());
            join.setColumnOfTableToJoin(column2);
        }
        joins.add(join);
    }

    public static List<Join> getJoins() {
        return joins;
    }
}
