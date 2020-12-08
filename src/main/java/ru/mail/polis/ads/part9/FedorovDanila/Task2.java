package ru.mail.polis.ads.part9.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

//
public class Task2 {
    private static int[] prev;
    private static boolean[] cycle;
    private static int[] color;
    private static ArrayList<Integer>[] array;
    private static int minElem = 1_000_000;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertices = in.nextInt();
        int edges = in.nextInt();

        array = new ArrayList[vertices];
        for (int i = 0; i < vertices; ++i) {
            array[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges; ++i) {
            int from = in.nextInt();
            int to = in.nextInt();
            array[--from].add(--to);
            array[to].add(from);
        }

        prev = new int[vertices];
        cycle = new boolean[vertices];
        color = new int[vertices];

        for (int i = 0; i < vertices; ++i) {
            if (color[i] == 0) {
                dfs(i);
            }
        }

        for (int i = 0; i < vertices; ++i) {
            if (cycle[i]) {
                out.println("Yes");
                out.println(i + 1);
                return;
            }
        }

        out.println("No");
    }

    private static void dfs(int v) {
        color[v] = 1;

        for (Integer i : array[v]) {
            if (color[i] == 2 || i == prev[v]) {
                continue;
            }
            if (color[i] == 1) {
                cycle[i] = true;
                int j = v;
                while (j != i) {
                    cycle[j] = true;
                    j = prev[j];
                }
            } else if (color[i] == 0) {
                prev[i] = v;
                dfs(i);
            }
        }
        color[v] = 2;
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