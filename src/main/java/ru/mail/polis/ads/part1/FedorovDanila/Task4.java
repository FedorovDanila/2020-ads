package ru.mail.polis.ads.part1.FedorovDanila;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Task4 {
    private static ArrayList<Integer> stack = new ArrayList<>();

    private static void solve(final FastScanner in, final PrintWriter out) {
        while (true) {
            String command = in.next();
            switch (command) {
                case ("push"):
                    stack.add(in.nextInt());
                    out.println("ok");
                    break;
                case ("pop"):
                    if (stack.size() == 0) {
                        out.println("error");
                    } else {
                        int index = stack.size() - 1;
                        out.println(stack.get(index));
                        stack.remove(index);
                    }
                    break;
                case ("back"):
                    if (stack.size() == 0) {
                        out.println("error");
                    } else {
                        out.println(stack.get(stack.size() - 1));
                    }
                    break;
                case ("size"):
                    out.println(stack.size());
                    break;
                case ("clear"):
                    stack.clear();
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
