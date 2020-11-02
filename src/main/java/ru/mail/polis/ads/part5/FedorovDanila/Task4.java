package ru.mail.polis.ads.part5.FedorovDanila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7622195
public class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String line1 = in.next();
        String line2 = in.next();
        String template;
        String line;
        if (line1.contains("*") || line1.contains("?")) {
            template = line1;
            line = line2;
        } else {
            template = line2;
            line = line1;
        }

        boolean[][] D = new boolean[template.length()][line.length()];
        char ch = template.charAt(0);
        if (ch == '*' || ch == '?' || ch == line.charAt(0)) {
            D[0][0] = true;
        } else {
            System.out.println("NO");
        }
        int i = 1;
        int j = 1;
        while (j < line.length()) {
            D[0][j] = ch == '*';
            ++j;
        }
        while (i < template.length()) {
            ch = template.charAt(i);
            D[i][0] = D[i - 1][0] && ch == '*';
            ++i;
        }
        i = 1;
        while (i < template.length()) {
            j = 1;
            ch = template.charAt(i);
            while (j < line.length()) {
                if (ch == '*' && (D[i][j - 1] || D[i - 1][j])) {
                    D[i][j] = true;
                } else if (ch == '?') {
                    D[i][j] = D[i - 1][j - 1];
                } else if (ch == line.charAt(j)) {
                    D[i][j] = D[i - 1][j - 1];
                } else {
                    D[i][j] = false;
                }
                ++j;
            }
            ++i;
        }
        if (D[template.length() - 1][line.length() - 1]) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
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
