package org.example;

import org.example.elements.Sort;

import java.util.ArrayList;
import java.util.List;

public class ToOrder {
    public static List<Sort> run(String[] arg) {
        List<Sort> columnsAndOrders = new ArrayList<>();

        for (int i = 2; i < arg.length; i += 3) {
            Sort sortColumn = new Sort();
            sortColumn.setColumn(arg[i]);

            if (i + 1 < arg.length && !arg[i + 1].equals(",")) {
                sortColumn.setOrder(arg[i + 1]);
            }

            columnsAndOrders.add(sortColumn);
        }
        return columnsAndOrders;
    }
}
