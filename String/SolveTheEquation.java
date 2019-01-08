/*
* Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.

If there is no solution for the equation, return "No solution".

If there are infinite solutions for the equation, return "Infinite solutions".

If there is exactly one solution for the equation, we ensure that the value of x is an integer.

Example 1:
Input: "x+5-3+x=6+x-2"
Output: "x=2"
Example 2:
Input: "x=x"
Output: "Infinite solutions"
Example 3:
Input: "2x=x"
Output: "x=0"
Example 4:
Input: "2x+3x-6x=x+2"
Output: "x=-1"
Example 5:
Input: "x=x+2"
Output: "No solution"

    20190108
    640
    medium
    O(n)
    O(1)
* */
package leetcode.String;

public class SolveTheEquation {
    public static void main(String[] args) {
        System.out.println(new SolveTheEquation().solveEquation("2x+3x-6x=x+2"));
    }

    public String solveEquation(String equation) {
        final String InfSol = "Infinite solutions", NoSol = "No solution";
        String[] processed = equation.split("=");
        char[] left = (processed[0] + "+").toCharArray(), right = (processed[1] + "+").toCharArray();

        int leftCoefficient = 0, rightCoefficient = 0;

        for (int i = 0; i < left.length; i++) {
            char c = left[i];
            if (c == 'x') {
                leftCoefficient += getCoefficient(left, i);
            }
        }

        for (int i = 0; i < right.length; i++) {
            char c = right[i];
            if (c == 'x') {
                rightCoefficient += getCoefficient(right, i);
            }
        }

        int leftSum = getSum(left) - leftCoefficient, rightSum = getSum(right) - rightCoefficient;
        int coefficient = leftCoefficient - rightCoefficient;
        int sum = rightSum - leftSum;

        if (coefficient == 0) {
            return sum == 0 ? InfSol : NoSol;
        }

        return "x=" + sum / coefficient;
    }

    private int getSum(char[] exp) {
        int sum = 0;
        int num = 0;
        boolean positive = true;
        for (char c : exp) {
            if (c == ' ') {
                continue;
            }

            if (c == '+') {
                sum += positive ? num : -num;
                positive = true;
                num = 0;
            } else if (c == '-') {
                sum += positive ? num : -num;
                positive = false;
                num = 0;
            } else {
                num = 10 * num + c - '0';
            }
        }

        return sum;
    }

    private int getCoefficient(char[] exp, int i) {
        if (i == 0 || exp[i - 1] == '+') {
            exp[i] = '1';
            return 1;
        } else if (exp[i - 1] == '-') {
            exp[i] = '1';
            return -1;
        }

        exp[i--] = ' ';
        int res = exp[i--] - '0';
        while (i >= 0) {
            if (exp[i] == '+') {
                break;
            } else if (exp[i] == '-') {
                res = -res;
                break;
            }
            res = 10 * (exp[i] - '0') + res;
            i--;
        }
        return res;
    }
}
