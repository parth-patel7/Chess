package chessPiece;

public class ChessPieceBishop extends ChessPiece{

	public ChessPieceBishop(boolean color) {
		this.isBlack = color;
	}
	
	public String toString() {
		if(this.checkColor()) {
			return "bB ";
		}
		return "wB ";
	}
}
