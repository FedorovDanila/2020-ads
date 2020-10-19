package ru.mail.polis.ads.part3.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7541382
public class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int places = in.nextInt();
        int cows = in.nextInt();
        int[] array = new int[places];

        for (int i = 0; i < places; ++i) {
            array[i]= in.nextInt();
        }

        out.println(calculate(array, cows));
    }

    private static int calculate(int[] array, int cows) {
        int leftEdge = 0;
        int rightEdge = array[array.length - 1];
        int middle = (rightEdge + leftEdge) / 2;

        while (leftEdge < rightEdge - 1) {
            int counter = 1;
            int prevCoordinate = array[0];
            for (int i = 1; i < array.length; ++i) {
                if (array[i] - prevCoordinate >= middle) {
                    ++counter;
                    prevCoordinate = array[i];
                    if (counter == cows) {
                        break;
                    }
                }
            }

            if (counter < cows) {
                rightEdge = middle;
            } else {
                leftEdge = middle;
            }
            middle = (rightEdge + leftEdge) / 2;
        }


        return leftEdge;
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
