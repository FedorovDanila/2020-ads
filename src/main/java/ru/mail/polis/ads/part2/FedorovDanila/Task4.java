package ru.mail.polis.ads.part2.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7499720
public class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {

        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());
        String[] strings = scanner.nextLine().split(" ");
        ArrayList<BigInteger> array = new ArrayList<>(strings.length);
        for (String string : strings) {
            array.add(new BigInteger(string));
        }

        out.print(findElement(array, k));
    }

    private static BigInteger findElement(ArrayList<BigInteger> array, int k) {
        --k;

        int leftEdge = 0;
        int rightEdge = array.size() - 1;
        int mid;
        do {
           mid = partition(array, leftEdge, rightEdge);

           if (k < mid) {
               rightEdge = mid - 1;
           } else if (k > mid) {
               leftEdge = mid + 1;
           }

        } while (mid != k);
        return array.get(mid);
    }

    private static int partition(ArrayList<BigInteger> array, int leftEdge, int rightEdge) {
        int index = leftEdge + (int)(Math.random()*(rightEdge - leftEdge));
        BigInteger x = array.get(index);
        int i = leftEdge;
        int j = rightEdge;
        while (true) {
            while (array.get(i).compareTo(x) > 0) {
                ++i;
            }
            while (array.get(j).compareTo(x) < 0) {
                --j;
            }

            if (i >= j) {
                break;
            }
            if (i == index) {
                index = j;
            } else if (j == index) {
                index = i;
            }
            Collections.swap(array, i, j);
            ++i;
            --j;

        }
        if (index < j) {
            Collections.swap(array, j, index);
            index = j;
        } else if (index > i) {
            Collections.swap(array, i, index);
            index = i;
        }
        return index;
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

        int nextInt() { return Integer.parseInt(next()); }

        BigInteger nextBigInt() { return new BigInteger(next()); }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}