package ru.mail.polis.ads.part1.FedorovDanila;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.Character.isUpperCase;

public class Task2 {
    private static ArrayList<StringBuilder> array = new ArrayList<>();
    private static String currentCommand;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();
        for (int i = 0; i < amount; ++i) {
            currentCommand = in.next();
            processSymbol(currentCommand.length() - 1, 0);
            StringBuilder newCommand = new StringBuilder();
            for (int j = array.size() - 1; j >= 0; --j) {
                newCommand.append(array.get(j).toString());
            }
            out.println(newCommand.toString());
            array.clear();
        }
    }

    private  static int processSymbol(int i, int depth) {
        char symbol = currentCommand.charAt(i--);
        if (array.size() <= depth) {
            array.add(new StringBuilder());
        }

        array.get(depth).append(symbol);

        if (isUpperCase(symbol)) {
            i = processSymbol(i, depth + 1);
            i = processSymbol(i, depth + 1);
        }

        return i;
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
        new Thread(null, () -> {
            final FastScanner in = new FastScanner(System.in);
            try (PrintWriter out = new PrintWriter(System.out)) {
                solve(in, out);
            }
        }, "name", 1 << 23).start();

    }
}
