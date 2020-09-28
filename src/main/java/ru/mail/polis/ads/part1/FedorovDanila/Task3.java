package ru.mail.polis.ads.part1.FedorovDanila;

import java.io.*;
import java.util.StringTokenizer;

public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String brackets = in.next();
        int bracketCount = 0;
        for (int i = 0; i < brackets.length(); ++i) {
            if (brackets.charAt(i) == '(') {
                ++bracketCount;
            } else {
                --bracketCount;
            }
            if (bracketCount < 0) {
                break;
            }
        }
        if (bracketCount == 0) {
            out.println("YES");
        } else {
            out.println("NO");
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
