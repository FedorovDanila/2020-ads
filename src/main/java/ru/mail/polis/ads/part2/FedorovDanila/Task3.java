package ru.mail.polis.ads.part2.FedorovDanila;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7496740
public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();
        ArrayList<Integer> array = new ArrayList<>(amount);
        for (int i = 0; i < amount; ++i) {
            array.add(in.nextInt());
        }

        int swaps = 0;
        for (int i = 1; i < amount; ++i) {
            for (int j = 0; j < amount - i; ++j) {
                if (array.get(j) > array.get(j + 1)) {
                    Collections.swap(array, j, j+1);
                    ++swaps;
                }
            }
        }

        out.print(swaps);
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