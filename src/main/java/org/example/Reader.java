package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Reader {
    private static BufferedReader in;
    private static StringTokenizer tok;
    private static PrintWriter out;

    public static void readRequest() throws IOException {
        in = new BufferedReader(new FileReader("src/main/resources/input.txt"));
        out = new PrintWriter("src/main/resources/output.txt");
        tok = new StringTokenizer("");
        String line = in.readLine();

        while (line != null) {
            Parser.parse(line);
            line = in.readLine();
        }

        out.println(Parser.queryToString());
        in.close();
        out.close();
    }
}
