package ru.mail.polis.ads.part3.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7543084
public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        MaxHeap maxHeap = new MaxHeap();
        MinHeap minHeap = new MinHeap();
        int median = 0;
        while (true) {
            int number;
            try {
                number = in.nextInt();
            } catch (Exception e) {
                return;
            }

            if (number > median) {
                minHeap.insert(number);
            } else {
                maxHeap.insert(number);
            }

            while (maxHeap.getSize() - minHeap.getSize() > 1) {
                minHeap.insert(maxHeap.extract());
            }
            while (minHeap.getSize() - maxHeap.getSize() > 1) {
                maxHeap.insert(minHeap.extract());
            }

            if (maxHeap.getSize() == minHeap.getSize()) {
                median = (maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                median = maxHeap.getSize() > minHeap.getSize() ? maxHeap.peek() : minHeap.peek();
            }
            out.println(median);
        }
    }

    private static class MaxHeap {
        ArrayList<Integer> array;

        MaxHeap() {
            array = new ArrayList<>();
            array.add(0);
        }
        int getSize() {
            return array.size() - 1;
        }

        void insert(Integer number) {
            array.add(number);
            swim(array.size() - 1);
        }

        Integer peek() {
            return array.get(1);
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

    private static class MinHeap {
        ArrayList<Integer> array;

        MinHeap() {
            array = new ArrayList<>();
            array.add(0);
        }

        int getSize() {
            return array.size() - 1;
        }

        void insert(Integer number) {
            array.add(number);
            swim(array.size() - 1);
        }

        Integer peek() {
            return array.get(1);
        }

        Integer extract() {
            Collections.swap(array, 1, array.size() - 1);
            Integer number = array.remove(array.size() - 1);
            sink(1);
            return number;
        }

        private void sink(int index) {
            int swapIndex = index * 2;
            int MaxIndex = array.size() - 1;
            while (swapIndex <= MaxIndex) {
                if (swapIndex < MaxIndex && array.get(swapIndex) > array.get(swapIndex + 1)) {
                    ++swapIndex;
                }
                if (array.get(index) <= array.get(swapIndex)) {
                    break;
                }
                Collections.swap(array, index, swapIndex);
                index = swapIndex;
                swapIndex = 2 * index;
            }
        }

        private void swim(int k) {
            while (k > 1 && array.get(k) < array.get(k / 2)) {
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
