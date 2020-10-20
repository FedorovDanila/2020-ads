package ru.mail.polis.ads.part4.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7543399
public class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();
        int[] steps = new int[amount + 2];
        steps[0] = 0;
        steps[amount + 1] = 0;
        for (int i = 1; i <= amount; ++i) {
            steps[i] = in.nextInt();
        }
        int k = in.nextInt();
        int[] stepsMaxValue = new int[amount + 2];
        stepsMaxValue[0] = 0;
        for (int i = 1; i < amount + 2; ++i) {
            int maxValue = -1001;
            for (int j = i < k ? 0 : i - k; j < i; ++j) {
                maxValue = Math.max(maxValue, stepsMaxValue[j]);
            }
            stepsMaxValue[i] = maxValue + steps[i];
        }

        for (int i = k; i < amount + 2; ++i) {
            /*
            По условию значения |x| < 1000
            Но в тестовых данных |x| > 10000
            */
            int maxValue = Integer.MIN_VALUE;
            for (int j = i - k; j < i; ++j) {
                maxValue = Math.max(maxValue, stepsMaxValue[j]);
            }
            stepsMaxValue[i] = maxValue + steps[i];
        }
        out.println(stepsMaxValue[amount + 1]);
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
