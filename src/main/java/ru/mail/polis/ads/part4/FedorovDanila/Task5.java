package ru.mail.polis.ads.part4.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7581099
public class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();
        ArrayList<Integer> array = new ArrayList<>(amount);
        for (int i = 0; i < amount; ++i) {
            array.add(in.nextInt());
        }
        out.println(count(array, 0, array.size() - 1));
    }

    private static int count(ArrayList<Integer> array, int leftEdge, int rightEdge) {
        if (leftEdge == rightEdge) {
            return 0;
        }
        int middle = (leftEdge + rightEdge) / 2;
        int result = count(array, leftEdge, middle) + count(array, middle + 1, rightEdge);
        result += intersections(array, leftEdge, rightEdge);
        return result;
    }

    private static int intersections(ArrayList<Integer> array, int leftEdge, int rightEdge) {
        int middle = (leftEdge + rightEdge) / 2;
        int i = leftEdge;
        int j = middle + 1;
        int inversions = 0;
        int arraySize = rightEdge - leftEdge + 1;
        ArrayList<Integer> temp = new ArrayList<>(arraySize);
        for (int k = 0; k < arraySize; ++k) {
            int number;
            if (i < middle + 1 && (j > rightEdge || array.get(j) > array.get(i))) {
                number = array.get(i++);
            } else  {
                number = array.get(j++);
                inversions += middle - i + 1;
            }
            temp.add(number);
        }
        for (int k = 0; k < arraySize; ++k) {
            array.set(leftEdge + k, temp.get(k));
        }
        return inversions;
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
