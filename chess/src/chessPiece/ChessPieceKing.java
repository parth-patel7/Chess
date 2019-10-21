package chessPiece;

public class ChessPieceKing extends ChessPiece{


	public ChessPieceKing(boolean color) {
		this.isBlack = color;
	}


	public String toString() {
		if(this.checkColor()) {
			return "bK ";
		}
		return "wK ";
	}
}
