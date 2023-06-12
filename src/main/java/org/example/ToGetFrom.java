package org.example;

import org.example.elements.Source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToGetFrom {
    public static List<Source> run(String[] arg) throws IOException {
        List<Source> fromSources = new ArrayList<>();
        int[] indexOfComma = new int[arg.length / 2 + 1];
        int index = 0;
        for (int i = 0; i < arg.length; i++) {
            if (arg[i].equals(",")) {
                indexOfComma[index] = i;
                index++;
            }
        }

        if (arg.length == 2) {
            indexOfComma[index] = arg.length - 1;
        } else {
            indexOfComma[index] = arg.length;
        }

        int start = 0;
        for (int stop = 0; stop < indexOfComma.length; stop++) {
            Source source = new Source();
            if (indexOfComma[stop] != 0) {
                if (indexOfComma[stop] > 2) {
                    StringBuilder line = new StringBuilder();
                    for (int i = 1; i < arg.length - 1; i++) {
                        arg[i] = arg[i].replace("(", "");
                        arg[i] = arg[i].replace(")", "");
                        line.append(arg[i]).append(" ");
                    }
                    Parser.parse(line.toString());
                    Query query = Parser.getQuery();
                    source.setNestedQuery(query);
                    source.setAlias(arg[arg.length - 1]);
                    fromSources.add(source);
                } else {
                    source.setTableName(arg[start + 1]);
                    fromSources.add(source);
                }
                start = indexOfComma[stop];
            }
        }
        return fromSources;
    }
}
