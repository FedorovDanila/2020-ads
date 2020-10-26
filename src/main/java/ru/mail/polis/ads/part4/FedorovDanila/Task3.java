package ru.mail.polis.ads.part4.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7581248
public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount1 = in.nextInt();
        int[] numbers1 = new int[amount1];
        for (int i = 0; i < amount1; ++i) {
            numbers1[i] = in.nextInt();
        }
        int amount2 = in.nextInt();
        int[] numbers2 = new int[amount2];
        for (int i = 0; i < amount2; ++i) {
            numbers2[i] = in.nextInt();
        }
        int[][] values = new int [amount1][amount2];
        for (int i = 0; i < amount2; ++i) {
            if (numbers1[0] == numbers2[i]) {
                values[0][i] = 1;
            }
        }
        for (int i = 0; i < amount1; ++i) {
            if (numbers1[i] == numbers2[0]) {
                values[i][0] = 1;
            }
        }
        for (int i = 1; i < amount1; ++i) {
            for (int j = 1; j < amount2; ++j) {
                if (numbers1[i] == numbers2[j]) {
                    values[i][j] = values[i - 1][j - 1] + 1;
                } else {
                    values[i][j] = Math.max(values[i][j - 1], values[i - 1][j]);
                }
            }
        }
        out.println(values[amount1 - 1][amount2 - 1]);
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
