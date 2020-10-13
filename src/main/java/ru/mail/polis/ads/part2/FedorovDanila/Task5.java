package ru.mail.polis.ads.part2.FedorovDanila;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//  https://www.e-olymp.com/ru/submissions/7499400
public class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();
        ArrayList<Robot> array = new ArrayList<>(amount);
        for (int i = 0; i < amount; ++i) {
            array.add(in.nextRobot());
        }

        sort(array, 0, array.size() - 1);

        for (Robot robot : array) {
            out.println(robot);
        }
    }

    private static void sort(ArrayList<Robot> array, int leftEdge, int rightEdge) {
        if (rightEdge - leftEdge < 2) {
            if (array.get(leftEdge).compareTo(array.get(rightEdge)) > 0) {
                Collections.swap(array, leftEdge, rightEdge);
            }
            return;
        }
        int middleIndex = (rightEdge + leftEdge) / 2;
        sort(array, leftEdge, middleIndex);
        sort(array, middleIndex + 1, rightEdge);

        int i = leftEdge;
        int j = middleIndex + 1;
        int tempArraySize = rightEdge - leftEdge + 1;
        ArrayList<Robot> tempArray = new ArrayList<>(tempArraySize);
        for (int k = 0; k < tempArraySize; ++k) {
            if (i > middleIndex) {
                tempArray.add(array.get(j++));
            } else if (j > rightEdge) {
                tempArray.add(array.get(i++));
            } else {
                Robot firstRobot = array.get(i);
                Robot secondRobot = array.get(j);

                if (firstRobot.compareTo(secondRobot) > 0) {
                    tempArray.add(secondRobot);
                    ++j;
                } else {
                    tempArray.add(firstRobot);
                    ++i;
                }
            }
        }

        for (int k = 0; k < tempArraySize; ++k) {
            array.set(leftEdge + k, tempArray.get(k));
        }
    }

    private static class Robot {
        private int mainNumber;
        private int secondNumber;

        Robot(int mainNumber, int secondNumber) {
            this.mainNumber = mainNumber;
            this.secondNumber = secondNumber;
        }

        @Override
        public String toString() {
            return mainNumber + " " + secondNumber;
        }

        int compareTo(Robot robot) {
            return this.mainNumber - robot.mainNumber;
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
        Robot nextRobot() {
            return new Robot(nextInt(), nextInt());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}