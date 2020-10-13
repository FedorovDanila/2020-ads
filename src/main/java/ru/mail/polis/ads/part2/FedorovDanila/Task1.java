package ru.mail.polis.ads.part2.FedorovDanila;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7499407
public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();
        int[] array = new int[amount];
        for (int i = 0; i < amount; ++i) {
            array[i] = in.nextInt();
        }

        sort(array, 0, amount - 1);

        for (Integer number : array) {
            out.print(number + " ");
        }
    }

    private static int[] sort(int[] array, int leftEdge, int rightEdge) {
        if (leftEdge >= rightEdge) {
            return array;
        }

        int index = (leftEdge + (int) (Math.random() * (rightEdge - leftEdge)));
        int number = array[index];

        int i = leftEdge;
        int j = rightEdge;
        while (true) {
            while (array[i] < number && i < rightEdge) {
                ++i;
            }
            while (array[j] > number && j > leftEdge) {
                --j;
            }

            if (i >= j) {
                sort(array, leftEdge, j);
                sort(array, j+1, rightEdge);
                return array;
            }

            int tmp = array[i];
            array[i++] = array[j];
            array[j--] = tmp;
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
