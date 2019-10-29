package chessPiece;
import chess.*;

public class ChessPieceQueen extends ChessPiece{

	public ChessPieceQueen(boolean color) {
		this.isBlack = color;
	}


	/*
	 * Legal Moves
	 * Both as Bishop or Rook
	 * can't skip pieces.
	 */

	// if current i is same as target i 
	// or if current j is same as target j then the queen is trying to move as rook
	// else as bishop
	public static boolean queenLegalMove(int currentI, int currentJ, int targetI, int targetJ) {

		if(!ChessHelper.isWithInBoard(targetI, targetJ)) {
			return false;
		}

		if( (currentI != targetI && currentJ != targetJ) ) {
			return ChessPieceBishop.bishopLegalMove(currentI, currentJ, targetI, targetJ);
		} else {
			return ChessPieceRook.rookLegalMove(currentI, currentJ, targetI, targetJ);
		}

	}
	
	public static boolean queenCanCheck(int i, int j) {
		if(ChessPieceBishop.bishopCanCheck(i, j)) {
			return true;
		}else if(ChessPieceRook.rookCanCheck(i, j)) {
			return true;
		}else{
			return false;
		}
	}

	public String toString() {
		if(this.checkColor()) {
			return "bQ ";
		}
		return "wQ ";
	}
}
