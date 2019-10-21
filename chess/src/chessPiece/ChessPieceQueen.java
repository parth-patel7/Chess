package chessPiece;

public class ChessPieceQueen extends ChessPiece{
	
	public ChessPieceQueen(boolean color) {
		this.isBlack = color;
	}
	
	public String toString() {
		if(this.checkColor()) {
			return "bQ ";
		}
		return "wQ ";
	}
}
