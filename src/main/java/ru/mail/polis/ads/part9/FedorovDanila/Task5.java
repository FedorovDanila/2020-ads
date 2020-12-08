package ru.mail.polis.ads.part9.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

//  https://www.e-olymp.com/ru/submissions/7973755
public class Task5 {
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

        ArrayList<Integer>[] array = new ArrayList[verticesAmount];
        for (int i = 0; i < verticesAmount; ++i) {
            array[i] = new ArrayList<>();
        }

        for (int i = 0; i < edgesAmount; ++i) {
            int first = in.nextInt();
            int second = in.nextInt();
            array[--first].add(--second);
            array[second].add(first);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(from);
        int[] path = new int[verticesAmount];
        Arrays.fill(path, -1);
        path[from] = from;
        int[] length = new int[verticesAmount];

        while (!queue.isEmpty()) {
            Integer elem = queue.poll();

            for (Integer i : array[elem]) {
                if (path[i] == -1) {
                    path[i] = elem;
                    length[i] = length[elem] + 1;
                    if (i == to) {
                        break;
                    }
                    queue.offer(i);
                }
            }
        }

        if (path[to] == -1) {
            out.println(-1);
            return;
        }

        out.println(length[to]);
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