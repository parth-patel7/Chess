package chess;


import chessPiece.*;

public class ChessHelper {


	static int[] swap = {8,7,6,5,4,3,2,1,0};

	// NEED TO CONVERT THE ORDER
	// Gets all the current and target indexes
	public static int getCurrentI(String input) {
		return swap[Character.getNumericValue(input.charAt(1))];
	}

	public static int getCurrentJ(String input) {
		return (int)input.charAt(0)-(int)'a';
	}

	public static int getTargetI(String input) {
		return swap[Character.getNumericValue(input.charAt(4))];
	}

	public static int getTargetJ(String input) {
		return (int)input.charAt(3)-(int)'a';
	}




	// Tells us whether the piece is knight, queen etc
	public static boolean movePiece(int i, int j, int ti, int tj) {

		if(ChessPieceKnight.class.isInstance(chessBoard.board[i][j])) {
			if(ChessPieceKnight.knightLegalMove(i, j, ti, tj)) {
				ChessPieceKnight.moveKnight(i, j, ti, tj);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}



	public static boolean isWithInBoard(int i, int j) {
		if(i > 8 || j > 8) {
			return false;
		}
		return true;
	}

	
	// checks is the turns are correct (white moves first)
	public static boolean checkTurn(int counter, int currentI, int currentJ) {
		if(counter%2 == 0) {
			if(chessBoard.board[currentI][currentJ].checkColor()) {
				System.out.println("Illegal move, try again\n");
				chessBoard.printBoard();
				return false;
			}
		} else {
			if(chessBoard.board[currentI][currentJ].checkColor()) {
				System.out.println("Illegal move, try again\n");
				chessBoard.printBoard();
				return false;
			}
		}
		return true;
	}


}
