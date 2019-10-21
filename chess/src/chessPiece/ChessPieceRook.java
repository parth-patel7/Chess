package chessPiece;

public class ChessPieceRook extends ChessPiece{

	public ChessPieceRook(boolean color) {
		this.isBlack = color;
	}

	public static boolean rookLegalMove(int currentI, int currentJ, int targetI, int targetJ) {

		// check this
		if( (currentI != targetI || currentI != targetJ) && (currentJ != targetI || currentJ != targetJ) ) {
			return false;
		}
		return true;
	}

	public static void isPathClearForRook(int i, int j, int targetI, int targetJ) {
		
		// If moving in I direction
		
		
		// If moving in J direction
		

	}

	public static void moveRook(int i, int j, int targetI, int targetJ) {
		
		// if both of above is true than move
		

	}

	public String toString() {
		if(this.checkColor()) {
			return "bR ";
		}
		return "wR ";
	}
}
