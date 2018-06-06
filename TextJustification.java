package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class TextJustification {
    /* todo
    public ArrayList<String> fullJustify(String[] words, int L) {
        ArrayList<String> sol = new ArrayList<>();
        Deque<String> tmp = new ArrayDeque<>();
        int wordsLength = 0;
        int actualLength = 0;
        for (String s : words) {
            int curLength = s.length();
            if (curLength + actualLength == L) {
                dealWithSingle(tmp, sol);
                wordsLength = 0;
                actualLength = 0;
            } else if (curLength + actualLength < L) {
                tmp.offerLast(s);
                wordsLength += curLength;
                actualLength += curLength + 1;
            } else { // curLength + actualLength > L
                dealWithMul(tmp, sol, wordsLength, L);
                wordsLength = 0;
                actualLength = 0;
            }
        }
        dealWithLast();
        return sol;
    }

    private void dealWithSingle(Deque<String> tmp, ArrayList<String> sol) {
        StringBuilder sb = new StringBuilder();
        sb.append(tmp.pollFirst());
        while (!tmp.isEmpty()) {
            sb.append(tmp.pollFirst());
            sb.append(' ');
        }
        sol.add(sb.toString());
    }

    private void dealWithMul(Deque<String> tmp, ArrayList<String> sol, int wordsLength, int L) {
        StringBuilder sb = new StringBuilder();
        int spaces = L - wordsLength;
        int each = tmp.size() == 1 ? spaces : spaces / (tmp.size() - 1);
        int left = spaces - each * (tmp.size() - 1);
        sb.append(tmp.pollFirst());
        if (tmp.isEmpty()) {
            for (int i = 0; i < each; i++) {
                sb.append(' ');
            }
        }
        while (!tmp.isEmpty()) {
            for (int i = 0; i < each; i++) {
                sb.append(' ');
            }
            if (left != 0) {
                sb.append(' ');
                left--;
            }
            sb.append(tmp.pollFirst());
        }
        sol.add(sb.toString());
    }

    private void dealWithLast(Deque<String> tmp, ArrayList<String> sol, int wordsLength, int L) {
        StringBuilder sb = new StringBuilder();
        int spaces = L - wordsLength;

    }
    */
}
