package ru.mail.polis.ads.part4.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7543318
public class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] cellsValues = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                cellsValues[i][j] = in.nextInt();
            }
        }

        int i = m - 1;
        int j = 1;
        int[][] maxValues = new int[m][n];
        String[][] paths = new String[m][n];
        maxValues[i][0] = cellsValues[i][0];
        paths[i][0] = "";

        while (j < n) {
            maxValues[i][j] = maxValues[i][j - 1] + cellsValues[i][j];
            paths[i][j] = paths[i][j - 1] + "R";
            ++j;
        }
        j = 1;
        --i;
        while (i >= 0) {
            maxValues[i][0] = maxValues[i + 1][0] + cellsValues[i][0];
            paths[i][0] = paths[i + 1][0] + "F";
            while (j < n) {
                if (maxValues[i + 1][j] > maxValues[i][j - 1]) {
                    maxValues[i][j] = maxValues[i + 1][j] + cellsValues[i][j];
                    paths[i][j] = paths[i + 1][j] + "F";
                } else {
                    maxValues[i][j] = maxValues[i][j - 1] + cellsValues[i][j];
                    paths[i][j] = paths[i][j - 1] + "R";
                }
                ++j;
            }
            j = 1;
            --i;
        }

        out.println(paths[0][n - 1]);
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
