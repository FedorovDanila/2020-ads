package ru.mail.polis.ads.part5.FedorovDanila;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7548286
public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();
        ArrayList<Integer> array = new ArrayList<>(amount);
        for (int i = 0; i < amount; ++i) {
            array.add(i + 1);
        }

        print(out, array);
        while (true) {
            int j = array.size() - 1;
            while (j > 0 && array.get(j) < array.get(j - 1)) {
                --j;
            }
            if (j == 0) {
                break;
            }
            int i = array.size() - 1;
            while (array.get(i) < array.get(j - 1)) {
                --i;
            }
            Collections.swap(array, i, j - 1);
            i = array.size() - 1;
            while (j < i) {
                Collections.swap(array, i--, j++);
            }
            print(out, array);
        }
    }

    private static void print(final PrintWriter out, ArrayList<Integer> array) {
        for (Integer i : array) {
            out.print(i + " ");
        }
        out.println();
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
