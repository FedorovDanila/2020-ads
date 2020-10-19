package ru.mail.polis.ads.part3.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7541096
public class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int arraySize = in.nextInt();
        int checksAmount = in.nextInt();
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; ++i) {
            array[i] = in.nextInt();
        }
        for (int i = 0; i < checksAmount; ++i) {
            if (find(array, in.nextInt())) {
                 out.println("YES");
            } else {
                 out.println("NO");
            }
        }
    }

    private static boolean find(int[] array, int number) {
        int leftEdge = 0;
        int rightEdge = array.length - 1;
        int middle = (leftEdge + rightEdge) / 2;
        int middleNumber = array[middle];
        while (leftEdge != rightEdge) {
            if (middleNumber == number) {
                break;
            }

            if (number < middleNumber) {
                rightEdge = middle;
            } else {
                leftEdge = middle + 1;
            }

            middle = (leftEdge + rightEdge) / 2;
            middleNumber = array[middle];
        }
        return  middleNumber == number;
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
