package org.example;

import org.example.elements.Source;

import java.util.ArrayList;
import java.util.List;

public class ToGetFrom {
    public static List<Source> run(String[] arg) {
        List<Source> fromSources = new ArrayList<>();
        List<Integer> indexOfComma = Utils.findIndexesOfComma(arg);

        int start = 0;
        for (Integer stop: indexOfComma) {
            Source source1 = new Source();
            int indexOfOpenBracket = Utils.findIndexOfSymbol(arg, start, arg.length, "(");

            if (!arg[start + 1].equals("(")) {
                source1.setAlias(Utils.returnAlias(arg, stop, start));
                source1.setTableName(arg[start + 1]);
            }

            fromSources.add(source1);

            if (stop - (start + 1) > 2) {
                Source source2 = new Source();
                source2.setIndexOfNestedQuery(Utils.returnIndexOfNestedQuery(arg, indexOfOpenBracket));
                source2.setAlias(Utils.returnAlias(arg, indexOfOpenBracket, arg.length));
                fromSources.add(source2);
                break;
            }
            start = stop;
        }
        return fromSources;
    }
}
