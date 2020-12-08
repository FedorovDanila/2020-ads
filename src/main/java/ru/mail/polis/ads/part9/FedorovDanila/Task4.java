package ru.mail.polis.ads.part9.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

//  https://www.e-olymp.com/ru/submissions/7973751
public class Task4 {

    private static class EndPoint {
        int to;
        int value;

        EndPoint(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int verticesAmount = in.nextInt();
        int edgesAmount = in.nextInt();

        int from = in.nextInt();
        int to = in.nextInt();

        if (from == to) {
            out.println(0);
            out.println(from);
        }
        from--;
        to--;

        ArrayList<EndPoint>[] array = new ArrayList[verticesAmount];
        for (int i = 0; i < verticesAmount; ++i) {
            array[i] = new ArrayList<>();
        }

        for (int i = 0; i < edgesAmount; ++i) {
            int first = in.nextInt();
            int second = in.nextInt();
            int value = in.nextInt();
            array[--first].add(new EndPoint(--second, value));
            array[second].add(new EndPoint(first, value));
        }

        int[] path = new int[verticesAmount];
        int[] dist = new int[verticesAmount];
        Arrays.fill(dist, 1_000_000);
        dist[from] = 0;
        path[from] = from;

        PriorityQueue<EndPoint> queue = new PriorityQueue<>(Comparator.comparing(x -> dist[x.to]));

        queue.add(new EndPoint(from, 0));
        while (!queue.isEmpty()) {
            EndPoint v = queue.poll();
            if (v.to == to) {
                break;
            }

            for (EndPoint i : array[v.to]) {
                if (dist[i.to] > dist[v.to] + i.value) {
                    dist[i.to] = dist[v.to] + i.value;
                    path[i.to] = v.to;
                    queue.add(i);
                }
            }
        }

        if (path[to] == -1) {
            out.println(-1);
            return;
        }

        out.println(dist[to]);

        Stack<Integer> stack = new Stack<>();
        stack.push(to);
        while (from != to) {
            to = path[to];
            stack.push(to);
        }
        while (!stack.empty()) {
            out.print((stack.pop() + 1) + " ");
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