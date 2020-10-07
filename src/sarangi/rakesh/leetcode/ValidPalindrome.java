package sarangi.rakesh.leetcode;

public class ValidPalindrome {

    public static void main(String[] args) {
        ValidPalindrome validPalindrome = new ValidPalindrome();
        System.out.println(validPalindrome.isPalindrome("race a car"));
    }

    public boolean isPalindrome(String s) {
        int length = s.length(), i = 0, j = s.length() - 1;
        if (length == 0)
            return true;
        s = s.toLowerCase();
        boolean isBValid, isEValid;
        char c, b, e;
        while (i < j)  {
            b = s.charAt(i);
            e = s.charAt(j);
            isBValid = false;
            isEValid = false;
            if ((b >= 97 && b <= 122) || (b >= 48 && b <= 57))
                isBValid = true;
            if ((e >= 97 && e <= 122) || (e >= 48 && e <= 57))
                isEValid = true;
            if (!isBValid) {
                i++;
                continue;
            }
            if (!isEValid) {
                j--;
                continue;
            }
            if (b != e)
                return false;
            i++;
            j--;
        }
        return true;
    }
}
