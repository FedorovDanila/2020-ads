package ru.mail.polis.ads.part5.FedorovDanila;

import java.io.*;
import java.util.StringTokenizer;

public class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String template = in.next();
        String line = in.next();

        int[][] D = new int[template.length()][line.length()];
        int i = 0;
        int j = 0;
        while (j < line.length()) {

        }
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
