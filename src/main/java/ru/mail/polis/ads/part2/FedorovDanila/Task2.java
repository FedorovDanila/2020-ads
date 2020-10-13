package ru.mail.polis.ads.part2.FedorovDanila;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7496691
public class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();
        ArrayList<Integer> array = new ArrayList<>(amount);
        for (int i = 0; i < amount; ++i) {
            array.add(in.nextInt());
        }

        array.sort(new comp());
        for (var number : array) {
            out.print(number + " ");
        }
    }

    private static class comp implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            int lastNumber1 = o1 % 10;
            int lastNumber2 = o2 % 10;
            int lastNumbersDifference = lastNumber1 - lastNumber2;

            if (lastNumbersDifference != 0) {
                return lastNumbersDifference;
            } else {
                return o1 - o2;
            }
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
