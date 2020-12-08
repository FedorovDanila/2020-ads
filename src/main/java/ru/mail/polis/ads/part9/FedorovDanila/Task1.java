package ru.mail.polis.ads.part9.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7973527
public class Task1 {
    private static int[] color;
    private static ArrayList<Integer>[] array;
    private static Stack<Integer> ans;
    private static boolean isCyclic = false;

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
        }

        color = new int[vertices];
        ans = new Stack<>();

        topologicalSort();

        if (isCyclic) {
            out.println(-1);
        } else {
            for (int i = 0; i < vertices; ++i) {
                out.print((ans.pop() + 1) + " ");
            }
            out.println();
        }
    }

    private static void topologicalSort() {
        for (int i = 0; i < array.length; ++i) {
            if (isCyclic) {
                break;
            }
            dfs(i);
        }
    }

    private static void dfs(int v) {
        if (color[v] == 2 ) {
            return;
        }
        if (isCyclic) {
            return;
        }
        if (color[v] == 1) {
            isCyclic = true;
            return;
        }
        color[v] = 1;
        for (Integer i : array[v]) {
            dfs(i);
        }
        color[v] = 2;
        ans.push(v);
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
