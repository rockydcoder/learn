package sarangi.rakesh.leetcode;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {

    public static void main(String[] args) {
        ValidSudoku validSudoku = new ValidSudoku();
        char[][] board = new char[][]{
                {'.', '.', '.', '.', '5', '.', '.', '1', '.'},
                {'.', '4', '.', '3', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '3', '.', '.', '1'},
                {'8', '.', '.', '.', '.', '.', '.', '2', '.'},
                {'.', '.', '2', '.', '7', '.', '.', '.', '.'},
                {'.', '1', '5', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
                {'.', '2', '.', '9', '.', '.', '.', '.', '.'},
                {'.', '.', '4', '.', '.', '.', '.', '.', '.'}};
        System.out.println(validSudoku.isValidSudoku(board));;
    }
    public boolean isValidSudoku(char[][] board) {
        Set<Character> uniqueChars;
        // Row
        for (int i = 0; i < 9; i++) {
            uniqueChars = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    continue;
                else {
                    if (uniqueChars.contains(board[i][j]))
                        return false;
                    else
                        uniqueChars.add(board[i][j]);
                }
            }
        }
        // Column
        for (int i = 0; i < 9; i++) {
            uniqueChars = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == '.')
                    continue;
                else {
                    if (uniqueChars.contains(board[j][i]))
                        return false;
                    else
                        uniqueChars.add(board[j][i]);
                }
            }
        }
        // Box
        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {
                if (!checkDuplicates(j, i, board))
                    return false;
            }
        }
        return true;
    }

    private boolean checkDuplicates(int columnStart, int rowStart, char[][] board) {
        int columnEnd = columnStart + 3, rowEnd = rowStart + 3;
        Set<Character> uniqueChars = new HashSet<>();
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = columnStart; j < columnEnd; j++) {
                if (board[i][j] == '.')
                    continue;
                else {
                    if (uniqueChars.contains(board[i][j]))
                        return false;
                    else
                        uniqueChars.add(board[i][j]);
                }
            }
        }
        return true;
    }
}
