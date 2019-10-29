package chessPiece;

import chess.ChessHelper;
import chess.chessBoard;
import java.lang.Math;

public class ChessPiecePawn extends ChessPiece{


	/* Pawn rules:
	 * - can move 2 spaces forward on starting position, one space forward otherwise
	 * - cannot move backwards
	 * - can move forward diagonally one space ONLY if a piece is there to capture.
	 */


	public ChessPiecePawn(boolean color) { 
		this.isBlack = color;
	}

	public static boolean pawnLegalMove(int i, int j, int targetI, int targetJ) {
		boolean legalMove = false;
		boolean attacking = false;
		int n = targetI - i;
		int m = targetJ - j;

		
		if(Math.abs(m)>1) {
			return false;
		}
		

		// checks if pawn tries to move diagonally -> must be a piece in the target space.
		if(Math.abs(m)==1) {
			if(chessBoard.board[i][j].isBlack) {
				
				

				// checks if EnPassant move is legal by checking canEnPassantHere array's index 1 is -1 or not
				// if it is legal than sets the enemy pawn to null
				if(chessBoard.board[i+1][targetJ] == null && ChessHelper.enPassantCounter == 2) {
					if(checkForEnPassant()) {
						if((i == chessBoard.canEnPassantHere[0]) &&
								(targetJ == chessBoard.canEnPassantHere[1])){
							chessBoard.board[i][targetJ] = null;
							chessBoard.canEnPassantHere[0] = -1;
							ChessHelper.enPassantCounter = 0;
							return true;
						}
					} else {
						return false;
					}
				}
				//  

				
				
				if(chessBoard.board[i+1][targetJ] != null ) {
					legalMove = true;
					attacking = true;
				} else { return false; }
			} else {
				
				
				

				// works
				// checks if EnPassant move is legal by checking canEnPassantHere array is null or not
				// if it is legal than sets the enemy pawn to null
				if(chessBoard.board[i-1][targetJ] == null && ChessHelper.enPassantCounter == 2) {
					if(checkForEnPassant()) {				
						if((i == chessBoard.canEnPassantHere[0]) &&
								(targetJ == chessBoard.canEnPassantHere[1])){
							chessBoard.board[i][targetJ] = null;
							chessBoard.canEnPassantHere[0] = -1;
							ChessHelper.enPassantCounter = 0;
							return true;
						}
					} else {
						return false;
					}
				}
				//

				
				
				if(chessBoard.board[i-1][targetJ] != null) {
					legalMove = true;
					attacking = true;
				} else { return false; }
			}
		}

		// (black) pawn cannot move backward
		if(chessBoard.board[i][j].isBlack && n<0) {
			return false;
		}

		// (white) pawn cannot move backward
		if(!chessBoard.board[i][j].isBlack && n>0) {
			return false;
		}

		// if (black) pawn is at starting position -> can move 2 spaces fwd unless there's a piece in between
		if (chessBoard.board[i][j].isBlack && i==1 && Math.abs(n) == 2) {
			if(chessBoard.board[targetI-1][j] == null && chessBoard.board[targetI][j] == null) {
				legalMove = true;
			} else { return false; }
			// if (white) pawn is at starting position -> can move 2 spaces fwd unless there's a piece in between
		} else if (!chessBoard.board[i][j].isBlack && i==6 && Math.abs(n) == 2) {
			if(chessBoard.board[targetI+1][j] == null && chessBoard.board[targetI][j] == null) {
				legalMove = true;
			} else { return false; }
			// if not at starting position -> can only move 1 space
		} else if(Math.abs(n) == 1) {
			if(!attacking) {
				if(chessBoard.board[i][j].isBlack) {
					if(chessBoard.board[i+1][j] == null) {
						legalMove = true;
					} else { return false; }
				} else {
					if(chessBoard.board[i-1][j] == null) {
						legalMove = true;
					} else { return false; }
				}
			}
		} else {
			return false;
		}

		// compare the current piece to the target piece and make sure they are not of same color
		if(sameColor(i,j,targetI,targetJ)){
			return false;
		}

		return legalMove;
	}


	
	public static boolean pawnCanCheck (int i, int j) {
		

		int down = i + 1;
		int up = i - 1;
		int left = j - 1;
		int right = j + 1;

		if(i + 1 > 7 || i - 1 < 0) {
			return false;
		}

		if(chessBoard.board[i][j].isBlack) {
			if(j != 7) {
				if(chessBoard.board[down][right] != null) {
					if(ChessPieceKing.class.isInstance(chessBoard.board[down][right])) {
						return true;
					}
				}
			}
			if(j != 0) {
				if(chessBoard.board[down][left] != null) {
					if(ChessPieceKing.class.isInstance(chessBoard.board[down][left])) {
						return true;
					}
				}
			}
		} else {
			if(j != 7) {
				if(chessBoard.board[up][right] != null) {
					if(ChessPieceKing.class.isInstance(chessBoard.board[up][right])) {
						return true;
					}
				}
			}
			if(j != 0) {
				if(chessBoard.board[up][left] != null) {
					if(ChessPieceKing.class.isInstance(chessBoard.board[up][left])) {
						return true;
					}
				}
			}
		}
		return false;
	}



	// if the canEnPassantHere array is not null then is it means there is a pawn that can be 
	// removed by en-passant move
	public static boolean checkForEnPassant() { 
		if(chessBoard.canEnPassantHere[0] == -1) {
			return false;
		}
		return true;
	}


	// if the pawn moves two cells than it means that the pawn is now removeable by en-passant move
	// So if the pawn moves two cells store its new location in canEnPassantHere array 
	public static boolean canEnPassantMove(int i, int j, int targetI, int targetJ) {
		boolean res = false;
		if(pawnLegalMove( i,  j,  targetI, targetJ)) {
			if (Math.abs(i-targetI) == 2) {
				chessBoard.canEnPassantHere[0] = targetI;
				chessBoard.canEnPassantHere[1] = targetJ;
				res = true;
			}
		}
		if(res == true) {
			ChessHelper.enPassantCounter = 1;
		}
		return res;
	}



	public String toString() {
		if(this.checkColor()) {
			return "bp ";
		}
		return "wp ";
	}
}
