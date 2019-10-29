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
		if(sameColor(i,j,targetI,targetJ)){
			return false;
		}

		return legalMove;
	}

	public static boolean knightCanCheck(int i, int j) {
		boolean c = chessBoard.board[i][j].isBlack;
		
		if(i<=5 && j<=6) {
			if(chessBoard.board[i+2][j+1]!=null) {
				if(ChessPieceKing.class.isInstance(chessBoard.board[i+2][j+1]) && chessBoard.board[i+2][j+1].isBlack != c) {
					return true;
				}
			}
		}
		if(i<=5 && j>=1) {
			if(chessBoard.board[i+2][j-1]!=null) {
				if(ChessPieceKing.class.isInstance(chessBoard.board[i+2][j-1]) && chessBoard.board[i+2][j-1].isBlack != c) {
					return true;
				}
			}
		}
		if(i>=2 && j<=6) {
			if(chessBoard.board[i-2][j+1]!=null) {
				if(ChessPieceKing.class.isInstance(chessBoard.board[i-2][j+1]) && chessBoard.board[i-2][j+1].isBlack != c) {
					return true;
				}
			}
		}
		if(i>=2 && j>=1) {
			if(chessBoard.board[i-2][j-1]!=null) {
				if(ChessPieceKing.class.isInstance(chessBoard.board[i-2][j-1]) && chessBoard.board[i-2][j-1].isBlack != c) {
					return true;
				}
			}
		}
		if(i<=6 && j<=5) {
			if(chessBoard.board[i+1][j+2]!=null) {
				if(ChessPieceKing.class.isInstance(chessBoard.board[i+1][j+2]) && chessBoard.board[i+1][j+2].isBlack != c) {
					return true;
				}
			}
		}
		if(i>=1 && j<=5) {
			if(chessBoard.board[i-1][j+2]!=null) {
				if(ChessPieceKing.class.isInstance(chessBoard.board[i-1][j+2]) && chessBoard.board[i-1][j+2].isBlack != c) {
					return true;
				}
			}
		}
		if(i<=6 && j>=2) {
			if(chessBoard.board[i+1][j-2]!=null) {
				if(ChessPieceKing.class.isInstance(chessBoard.board[i+1][j-2]) && chessBoard.board[i+1][j-2].isBlack != c) {
					return true;
				}
			}
		}
		if(i>=1 && j>=2) {
			if(chessBoard.board[i-1][j-2]!=null) {
				if(ChessPieceKing.class.isInstance(chessBoard.board[i-1][j-2]) && chessBoard.board[i-1][j-2].isBlack != c) {
					return true;
				}
			}
		}
			
		return false;
	}

	public String toString() {
		if(this.checkColor()) {
			return "bN ";
		}
		return "wN ";
	}


}
