package leetcode;

public class NestedListWeightSum {
    public static void main (String[] args) {
        NestedListWeightSum sol = new NestedListWeightSum();
        String test = "[1]";
        int res = sol.depthSum(test);
        System.out.print(res);
    }
    public int depthSum(String nestlists) {
        //corner case
        if (nestlists == null || nestlists.length() == 0) {
            return 0;
        }
        int res = 0;
        int depth = 0;
        int num = 0;
        for (int i = 0; i < nestlists.length(); i++) {
            if (nestlists.charAt(i) == '[') {
                depth++;
            } else if (nestlists.charAt(i) == ',') {
                res += depth * num;
                num = 0;
            } else if (nestlists.charAt(i) == ']') {
                res += depth-- * num;
                num = 0;
            } else {
                num = 10 * num + (int)(nestlists.charAt(i) - '0');
            }
        }
        return res;
    }
}
