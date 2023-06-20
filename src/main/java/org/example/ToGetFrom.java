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
            int indexOfOpenBracket = Utils.findIndexOfSymbol(arg, start, arg.length, "(");

            if (!arg[start + 1].equals("(")) {
                Source source1 = new Source();
                source1.setAlias(Utils.returnAlias(arg, stop, start + 1));
                source1.setTableName(arg[start + 1]);
                fromSources.add(source1);
            }

            if (stop - (start + 1) > 2) {
                Source source2 = new Source();
                int indexOfCloseBracket = Utils.findIndexOfSymbol(arg, start, stop, ")");
                source2.setIndexOfNestedQuery(Utils.returnIndexOfNestedQuery(arg, indexOfOpenBracket,
                                                                                indexOfCloseBracket));
                source2.setAlias(Utils.returnAlias(arg, arg.length, indexOfCloseBracket));
                fromSources.add(source2);
                break;
            }
            start = stop;
        }
        return fromSources;
    }
}
