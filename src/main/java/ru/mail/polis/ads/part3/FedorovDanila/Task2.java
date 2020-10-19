package ru.mail.polis.ads.part3.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7542024
public class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap();
        int amount = in.nextInt();
        for (int i = 0; i < amount; ++i) {
            if (in.nextInt() == 0) {
                heap.insert(in.nextInt());
            } else {
                out.println(heap.extract());
            }
        }
    }

    private static class Heap {
        ArrayList<Integer> array;

        Heap() {
            array = new ArrayList<>();
            array.add(null);
        }

        void insert(Integer number) {
            array.add(number);
            swim(array.size() - 1);
        }

        Integer extract() {
            Collections.swap(array, 1, array.size() - 1);
            Integer number = array.remove(array.size() - 1);
            sink(1);
            return number;
        }

        private void sink(int index) {
            int swapIndex = index * 2;
            int maxIndex = array.size() - 1;
            while (swapIndex <= maxIndex) {
                if (swapIndex < maxIndex && array.get(swapIndex) < array.get(swapIndex + 1)) {
                    ++swapIndex;
                }
                if (array.get(index) >= array.get(swapIndex)) {
                    break;
                }
                Collections.swap(array, index, swapIndex);
                index = swapIndex;
                swapIndex = 2 * index;
            }
        }

        private void swim(int k) {
            while (k > 1 && array.get(k) > array.get(k / 2)) {
                Collections.swap(array, k, k / 2);
                k /= 2;
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
