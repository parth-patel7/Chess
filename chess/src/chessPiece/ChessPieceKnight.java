package chessPiece;

import chess.*;

public class ChessPieceKnight extends ChessPiece{


	public ChessPieceKnight(boolean color) {
		this.isBlack = color;
	}


	/*
	 * Legal Moves (8)
	 * (i+2, j+1)
	 * (i+1, j+2)
	 * (i-1, j+2)
	 * (i-2, j+1)
	 * 
	 * (i-2, j-1)
	 * (i-1, j-2)
	 * (i+1, j-2)
	 * (i+2, j-1)
	 * 
	 */
	public static boolean knightLegalMove(int i, int j, int targetI, int targetJ) {

		boolean legalMove = false;


		if(!ChessHelper.isWithInBoard(targetI, targetJ)) {
			return false;
		}

		// All 8 possibilities
		if(i+2 == targetI && j+1 == targetJ) {
			legalMove = true;
		}
		if(i+1 == targetI && j+2 == targetJ) {
			legalMove = true;
		}
		if(i-1 == targetI && j+2 == targetJ) {
			legalMove = true;
		}
		if(i-2 == targetI && j+1 == targetJ) {
			legalMove = true;
		}
		if(i-2 == targetI && j-1 == targetJ) {
			legalMove = true;
		}
		if(i-1 == targetI && j-2 == targetJ) {
			legalMove = true;
		}
		if(i+1 == targetI && j-2 == targetJ) {
			legalMove = true;
		}
		if(i+2 == targetI && j-1 == targetJ) {
			legalMove = true;
		}

		// compare the current piece to the target piece and make sure they are not of same color
		if(chessBoard.board[i][j] != null && chessBoard.board[targetI][targetJ] != null) {
			if(chessBoard.board[i][j].isBlack == chessBoard.board[targetI][targetJ].isBlack) {
				return false;
			}
		}

		return legalMove;
	}


	public static void moveKnight(int i, int j, int targetI, int targetJ) {

		if(chessBoard.board[targetI][targetJ] != null) {
			chessBoard.board[targetI][targetJ] = null;
		}
		chessBoard.board[targetI][targetJ] = chessBoard.board[i][j];
		chessBoard.board[i][j] = null;
	}

	public String toString() {
		if(this.checkColor()) {
			return "bN ";
		}
		return "wN ";
	}


}
