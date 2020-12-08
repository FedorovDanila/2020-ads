package ru.mail.polis.ads.part9.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7973608
public class Task3 {

    private static class Edge {
        int from;
        int to;
        int value;

        Edge(int from, int to, int value) {
            this.from = --from;
            this.to = --to;
            this.value = value;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int verticesAmount = in.nextInt();
        int edgesAmount = in.nextInt();

        Edge[] edges = new Edge[edgesAmount];
        for (int i = 0; i < edgesAmount; ++i) {
            edges[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
        }

        int[] d = new int[verticesAmount];
        for (int i = 1; i < verticesAmount; ++i) {
            d[i] = 30000;
        }

        for (int i = 0; i < verticesAmount - 1; ++i) {
            for (Edge edge : edges) {
                if (d[edge.from] != 30000) {
                    d[edge.to] = Math.min(d[edge.to], d[edge.from] + edge.value);
                }
            }
        }

        for (int i = 0; i < verticesAmount; ++i) {
            out.print(d[i] + " ");
        }
        out.println();
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