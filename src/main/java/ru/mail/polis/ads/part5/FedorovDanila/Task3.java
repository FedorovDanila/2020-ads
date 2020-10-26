package ru.mail.polis.ads.part5.FedorovDanila;

import java.io.*;
import java.util.StringTokenizer;

public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        long w = in.nextInt();
        long h = in.nextInt();
        long n = in.nextInt();

        long min = Math.max(h, w);
        long max = min * n;
        while (min < max) {
            long middle = (max + min) / 2;
            if (middle / h * middle / w < n) {
                min = middle + 1;
            } else {
                max = middle;
            }
        }
        out.println(max);
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
