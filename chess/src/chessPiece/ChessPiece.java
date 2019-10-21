package chessPiece;
import chess.*;

public class ChessPiece {

	boolean isBlack;
	boolean isAlive;

	public ChessPiece() {
		
	}

	public boolean checkColor(){
		if(this.isBlack == true)
			return true;
		else 
			return false;
	}


	public String toString() {
		return "";
	}

}

