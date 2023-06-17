package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public static void readStatement() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("src/main/resources/input.txt"));
        PrintWriter out = new PrintWriter("src/main/resources/output.txt");
        String line = in.readLine();

        while (line != null) {
            Parser.parseQuery(line);
            line = in.readLine();
        }
        out.println(Parser.queryToString());
        in.close();
        out.close();
    }

    public static int processNestedStatement(String[] arg) {
        List<String> keyWords = List.of("FROM", "WHERE", "GROUP", "ORDER", "INNER", "LEFT",
                "RIGHT", "CROSS", "FULL", "OFFSET", "LIMIT");

        List<String> newStatement = new ArrayList<>();

        List<String> oneLine = new ArrayList<>();
        for (String s : arg) {
            if (keyWords.contains(s)) {
                String line = String.join(" ", oneLine);
                newStatement.add(line);
                oneLine.clear();
            }
            oneLine.add(s);
        }
        String lastLine = String.join(" ", oneLine);
        newStatement.add(lastLine);

        return Parser.parseNestedQuery(newStatement);
    }
}
