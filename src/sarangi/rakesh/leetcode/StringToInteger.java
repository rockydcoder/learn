package sarangi.rakesh.leetcode;

public class StringToInteger {

    public static void main(String[] args) {
        StringToInteger stringToInteger = new StringToInteger();

        System.out.println(stringToInteger.myAtoi("-91283472332"));
    }
    public int myAtoi(String str) {
        int result = 0, newResult = 0, digit;
        boolean isNegative = false, isValid = false, isSigned = false;
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c == ' ') {
                if (isValid)
                    return result;
                if (isSigned)
                    return 0;
                continue;
            }
            if (c == '-') {
                if (isSigned)
                    return 0;
                isSigned = true;
                isNegative = true;
            }
            else if (c == '+') {
                if (isSigned)
                    return 0;
                isSigned = true;
                continue;
            }
            else if (c >= 48 && c <= 57) {
                isValid = true;
                digit = c - 48;
                newResult = result * 10 + digit;
                if (result > 0 && newResult < 0) {
                    return Integer.MAX_VALUE;
                }
                if (result < 0 && newResult > 0) {
                    return Integer.MIN_VALUE;
                }
//                if ((newResult-digit) / 10 != result) { // Overflow
//                    if (isNegative)
//                        return Integer.MIN_VALUE;
//                    else
//                        return Integer.MAX_VALUE;
//                }
                result = newResult;
            } else if (!isValid) {
                return 0;
            } else {
                return isNegative ? result * -1 : result;
            }
        }
        if (isValid)
            return isNegative ? result * -1 : result;
        return 0;

    }

}
