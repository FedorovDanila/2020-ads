package ru.mail.polis.ads.part5.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7622262
public class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = in.nextDouble();
        double leftEdge = 0;
        double rightEdge = 100000;
        double eps = 0.0000001;
        double x = (rightEdge + leftEdge) / 2;
        while (rightEdge - leftEdge > eps) {

            if (x * x + Math.sqrt(x) < c) {
                leftEdge = x;
            } else {
                rightEdge = x;
            }
            x = (rightEdge + leftEdge) / 2;
        }
        System.out.println(x);
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
