package org.example;

import java.util.ArrayList;
import java.util.List;

public class ToGroup {
    public static List<String> run(String[] arg) {
        List<String> columns = new ArrayList<>();
        for (int i = 2; i < arg.length; i += 2) {
            columns.add(arg[i]);
        }
        return columns;
    }
}
