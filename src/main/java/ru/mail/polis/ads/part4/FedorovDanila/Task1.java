package ru.mail.polis.ads.part4.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7543379
public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String line;
        try {
            line = in.next();
        } catch (Exception e) {
            return;
        }
        int length = line.length();
        int[][] mistakes = new int[length][length];
        String[][] substrings = new String[length][length];

        for (int i = 0; i < length; ++i) {
            mistakes[i][i] = 1;
            if (line.charAt(i) == '(' || line.charAt(i) == ')') {
                substrings[i][i] = "()";
            }
            if (line.charAt(i) == '[' || line.charAt(i) == ']') {
                substrings[i][i] = "[]";
            }
        }

        for (int i = 1; i < length; ++i) {
            substrings[i][i - 1] = "";
        }

        for (int j = 1; j < length; ++j) {
            for (int i = j - 1; i >= 0; --i) {
                int minMistakes = length + 1;
                String substring = "";
                if (line.charAt(i) == '(' && line.charAt(j) == ')'
                        || line.charAt(i) == '[' && line.charAt(j) == ']') {
                    minMistakes = mistakes[i + 1][j - 1];
                    substring = line.charAt(i) + substrings[i + 1][j - 1] + line.charAt(j);
                }

                for (int k = i; k < j; ++k) {
                    if (mistakes[i][k] + mistakes[k + 1][j] <= minMistakes) {
                        minMistakes = mistakes[i][k] + mistakes[k + 1][j];
                        substring = substrings[i][k] + substrings[k + 1][j];
                    }
                }
                mistakes[i][j] = minMistakes;
                substrings[i][j] = substring;
            }
        }

        out.println(substrings[0][length - 1]);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
