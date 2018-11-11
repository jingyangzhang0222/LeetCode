package leetcode.String;

public class StudentAttendanceRecordI {
    public static void main(String[] args) {
        new StudentAttendanceRecordI().checkRecord("PPALLL");
    }

    public boolean checkRecord(String s) {
        int A = 0;
        int L = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'P') {
                L = 0;
            } else if (s.charAt(i) == 'A') {
                if (++A > 1) return false;
                L = 0;
            } else if (s.charAt(i) == 'L' && ++L > 2) {
                return false;
            }
        }
        return true;
    }
}
