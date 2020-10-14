import java.util.*;

public class MyNQueenSolution {

	public static boolean canBePlaced(int[][] board, int row, int col) {
		//This checks if a queen can be placed, meaning that it is not in the same column, row, or diagonal of another queen. 
		//If it finds that there is a conflict, it will return false and abort that candidate.
		int len = board.length;
		for (int i = 0; i < col; i++) {
			/*This one checks the column, there is no row check since we are placing the queens down the row,
			*meaning two queens will never share a row
			*
			*It also only checks the row behind. This is due to how the program works. 
			*A queen will never be place ahead in a row, so you only need to check the previous ones.
			*/
			if (board[row][i] == 1) {
				return false;
			}
		}
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			//The decrementing i and j indices are the first diagonal check, this is also going backwards since no queen will be in front.
			if (board[i][j] == 1) {
				return false;
			}
		}
		for (int i = row, j = col; j >= 0 && i < len; i++, j--) {
			//Checking the other diagonal from behind
			if (board[i][j] == 1) {
				return false;
			}
		}
		//if nothing is wrong, then it returns true and gets placed
		return true;
	}

	public static boolean solve(int[][] board, int col) {
		int len = board.length;
		if (col >= len) {
			//This is the recursive base case. This means that if this methods gets to the end of the board, it has succeeded.
			return true;
		}
		//This for loop contains the "candidates", or possible solutions. 
		//It puts in the queens one by one from the top of the next column to the bottom/ 
		for (int row = 0; row < len; row++) {
			//This if statement checks if a queen can be placed
			if (canBePlaced(board, row, col)) {
				//If a queen can be place, we put a 1 to represent that it has been placed
				board[row][col] = 1;
				//Here is the recursive check, this goes thorugh onces again to see if there is any solution
				if (solve(board, col + 1)) {
					return true;
				}
				//If there is no solution, then set that queen back to 0
				board[row][col] = 0;
			}
		}
		//If it goes through the whole board without finding any solution, it returns false.
		//This can mean there is no solution at all, or that the solution the program is currently processing fails, and it will backtrack 
		return false;
	}

	public static void clearBoard(int[][] board) {
		//Clears the board, sets all positions to 0
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = 0;
			}
		}
	}

	public static boolean solve2(int[][] board, int col) {
		int len = board.length;
		if (col >= len) {
			printBoard(board);
			return true;
		}
		for (int row = 0; row < len; row++) {
			if (canBePlaced(board, row, col)) {
				board[row][col] = 1;
				if (solve2(board, col + 1)) {
					board[row][col] = 0;
					continue;
				}
				board[row][col] = 0;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner s1 = new Scanner(System.in);
		System.out.println("Number of Queens: ");
		int N = Integer.parseInt(s1.nextLine());
		int[][] board = new int[N][N];
		if (!solve(board, 0)) {
			System.out.println("No solution");
		} else {
			System.out.println("One solution:");
			printBoard(board);
			int[][] board2 = new int[N][N];
			System.out.println("All Solutions:");
			System.out.println();
			solve2(board2.clone(), 0);
		}


	}

	public static void printBoard(int[][] board) {
		//Q means queen, X means nothing
		int len = board.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (board[i][j] == 1) {
					System.out.print("Q ");
				} else {
					System.out.print("x ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
