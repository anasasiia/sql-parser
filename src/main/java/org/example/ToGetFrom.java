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
            Source source = new Source();
            int indexOfOpenBracket = Utils.findIndexOfSymbol(arg, start, arg.length, "(");

            if (!arg[start + 1].equals("(")) {
                source.setAlias(Utils.returnAlias(arg, stop, start + 1));
                source.setTableName(arg[start + 1]);
                fromSources.add(source);
            }

            if (stop - (start + 1) > 2) {
                int indexOfCloseBracket = Utils.findIndexOfSymbol(arg, start, stop, ")");
                source.setIndexOfNestedQuery(Utils.returnIndexOfNestedQuery(arg, indexOfOpenBracket,
                                                                                indexOfCloseBracket));
                source.setAlias(Utils.returnAlias(arg,arg.length, indexOfCloseBracket));
                fromSources.add(source);
                break;
            }
            start = stop;
        }
        return fromSources;
    }
}
