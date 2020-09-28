package ru.mail.polis.ads.part1.FedorovDanila;

import java.io.*;
import java.util.StringTokenizer;

public class Task5 {
    private static int[] queue = new int[100];
    private static int beginIndex = 0;
    private static int endIndex = 0;
    private static int size = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        while (true) {
            String command = in.next();
            switch (command) {
                case ("push"):
                    queue[endIndex++] = in.nextInt();
                    ++size;
                    if (endIndex == 100) {
                        endIndex = 0;
                    }
                    out.println("ok");
                    break;
                case ("pop"):
                    out.println(queue[beginIndex++]);
                    --size;
                    if (beginIndex == 100) {
                        beginIndex = 0;
                    }
                    break;
                case ("front"):
                    out.println(queue[beginIndex]);
                    break;
                case ("size"):
                    out.println(size);
                    break;
                case ("clear"):
                    endIndex = beginIndex;
                    size = 0;
                    out.println("ok");
                    break;
                case ("exit"):
                    out.println("bye");
                    return;
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
