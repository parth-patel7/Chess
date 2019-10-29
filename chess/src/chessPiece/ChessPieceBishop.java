package chessPiece;

import chess.*;

import java.lang.Math;

public class ChessPieceBishop extends ChessPiece{

	public ChessPieceBishop(boolean color) {
		this.isBlack = color;
	}
	
	/*
	 * Legal Moves (13)
	 * n = |targeti - i|
	 * targetj == j + n || targetj == j - n
	 * 
	 * can't skip pieces.
	*/
	
	public static boolean bishopLegalMove(int i, int j, int targetI, int targetJ) {
		boolean legalMove = false;
		int n = targetI - i;
		int m = targetJ - j;
	
		if(!ChessHelper.isWithInBoard(targetI, targetJ)) {
			return false;
		}
		
		// Checks if target spot is occupied by same color piece
		if(chessBoard.board[i][j] != null && chessBoard.board[targetI][targetJ] != null) {
			if(chessBoard.board[i][j].isBlack == chessBoard.board[targetI][targetJ].isBlack) {
				return false;
			}
		}
		
		if(Math.abs(n) != Math.abs(m)) {
			return false;
		}
		
		// up-right (checks for skips)
		if(n<0 && m>0) {
			for(int k=-1; k>=n; k--) {
				if(chessBoard.board[i+k][j+Math.abs(k)] != null && chessBoard.board[i][j] != null) {
					if(chessBoard.board[i+k][j+Math.abs(k)].isBlack == chessBoard.board[i][j].isBlack) {
						return false;
					}
				}
			}
			legalMove = true;
		}
		
		// down-right (checks for skips)
		if(n>0 && m>0) {
			for(int k=1; k<=n; k++) {
				if(chessBoard.board[i+k][j+k] != null && chessBoard.board[i][j] != null) {
					if(chessBoard.board[i+k][j+k].isBlack == chessBoard.board[i][j].isBlack) {
						return false;
					}
				}
			}
			legalMove = true;
		}
		
		// up-left (checks for skips)
		if(n<0 && m<0) {
			for(int k=-1; k>=n; k--) {
				if(chessBoard.board[i+k][j+k] != null && chessBoard.board[i][j] != null) {
					if(chessBoard.board[i+k][j+k].isBlack == chessBoard.board[i][j].isBlack) {
						return false;
					}
				}
			}
			legalMove = true;
		}
		
		// down-left (checks for skips)
		if(n>0 && m<0) {
			for(int k=-1; k>=m; k--) {
				if(chessBoard.board[i+Math.abs(k)][j+k] != null && chessBoard.board[i][j] != null) {
					if(chessBoard.board[i+Math.abs(k)][j+k].isBlack == chessBoard.board[i][j].isBlack) {
						return false;
					}
				}
			}
			legalMove = true;
		}
		
		// compare the current piece to the target piece and make sure they are not of same color
		if(sameColor(i,j,targetI,targetJ)){
			return false;
		}
		
		return legalMove;
	}

	public static boolean bishopCanCheck(int i, int j) {
		int up,down,left,right;
		up = i;
		down = i;
		left = j;
		right = j;
		
		while(down<7 && left>0) {
			down = down+1;
			left = left-1;
			if(chessBoard.board[down][left] != null) {
				if(ChessPieceKing.class.isInstance(chessBoard.board[down][left])) {
					if(chessBoard.board[i][j].isBlack != chessBoard.board[down][left].isBlack) {
						return true;
					}
				}else{ break; }
			}
		}
		down = i;
		left = j;
		
		while(down<7 && right<7) {
			down = down+1;
			right = right+1;
			if(chessBoard.board[down][right] != null) {
				if(ChessPieceKing.class.isInstance(chessBoard.board[down][right])) {
					if(chessBoard.board[i][j].isBlack != chessBoard.board[down][right].isBlack) {
						return true;
					}
				}else{ break; }
			}
		}
		down = i;
		right = j;
		
		while(up>0 && left>0) {
			up = up-1;
			left = left-1;
			if(chessBoard.board[up][left] != null) {
				if(ChessPieceKing.class.isInstance(chessBoard.board[up][left])) {
					if(chessBoard.board[i][j].isBlack != chessBoard.board[up][left].isBlack) {
						return true;
					}
				}else{ break; }
			}
		}
		up = i;
		left = j;
		
		while(up>0 && right<7) {
			up = up-1;
			right = right+1;
			if(chessBoard.board[up][right] != null) {
				if(ChessPieceKing.class.isInstance(chessBoard.board[up][right])) {
					if(chessBoard.board[i][j].isBlack != chessBoard.board[up][right].isBlack) {
						return true;
					}
				}else{ break; }
			}
		}
		
		return false;
	
	}
	
	public String toString() {
		if(this.checkColor()) {
			return "bB ";
		}
		return "wB ";
	}
}
