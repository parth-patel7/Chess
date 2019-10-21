package chessPiece;

public class ChessPiecePawn extends ChessPiece{


	public ChessPiecePawn(boolean color) {
		this.isBlack = color;

	}

	public static void legalmove() {

	}

	public String toString() {
		if(this.checkColor()) {
			return "bp ";
		}
		return "wp ";
	}
}
