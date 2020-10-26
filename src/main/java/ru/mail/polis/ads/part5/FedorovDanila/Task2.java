package ru.mail.polis.ads.part5.FedorovDanila;

import java.io.*;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7548622
public class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();
        int[] numbers = new int[amount];
        for (int i = 0; i < amount; ++i) {
            numbers[i] = in.nextInt();
        }
        int[] values = new int[amount];
        int maxValue = 0;
        for (int i = 0; i < amount; ++i) {
            int value = 0;
            for (int j = 0; j < i; ++j) {
                if (numbers[j] != 0 && numbers[i] % numbers[j] == 0) {
                    value = Math.max(value, values[j]);
                }
            }
            values[i] = value + 1;
            maxValue = Math.max(maxValue, values[i]);
        }
        out.println(maxValue);
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
