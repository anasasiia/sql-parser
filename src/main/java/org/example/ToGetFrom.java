package org.example;

import org.example.elements.Source;

import java.util.ArrayList;
import java.util.List;

public class ToGetFrom {
    public static List<Source> run(String[] arg) {
        List<Source> fromSources = new ArrayList<>();
        List<Integer> indexOfComma = Utils.findIndexesOfComma(arg);

        if (arg.length == 2) {
            indexOfComma.add(arg.length - 1);
        } else {
            indexOfComma.add(arg.length);
        }

        int start = 0;
        for (Integer stop: indexOfComma) {
            Source source = new Source();
            if (!arg[start + 1].equals("(")) {
                if (stop - (start + 1) == 2) {
                    source.setAlias(arg[start + 2]);
                }
                source.setTableName(arg[start + 1]);
            }

            fromSources.add(source);

            if (stop - (start + 1) > 2) {
                int indexOfOpenBracket = Utils.findIndexOfSymbol(arg, start, arg.length, "(");
                Source source1 = (Source) Utils.prepareColumnSourceWithNestedQuery(arg, indexOfOpenBracket, arg.length);
                fromSources.add(source1);
                break;
            }
            start = stop;
        }
        return fromSources;
    }
}
