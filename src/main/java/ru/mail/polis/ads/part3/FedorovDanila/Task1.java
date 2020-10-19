package ru.mail.polis.ads.part3.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7541366
public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();
        int[] array = new int[amount + 1];

        for (int i = 1; i <= amount; ++i) {
            array[i] = in.nextInt();
        }

        for (int i = amount; i > 1; --i) {
            if (array[i] < array[i/2]) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
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
