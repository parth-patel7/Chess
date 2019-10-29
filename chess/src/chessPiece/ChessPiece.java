package chessPiece;
import chess.*;

public class ChessPiece {

	public boolean isBlack;

	public ChessPiece() {
	}

	public boolean checkColor(){
		if(this.isBlack == true)
			return true;
		else 
			return false;
	}

	// Move Piece
	public static void movePiece(int i, int j, int ti, int tj) {
		if(chessBoard.board[ti][tj] != null) {
			chessBoard.board[ti][tj] = null;
		}
		chessBoard.board[ti][tj] = chessBoard.board[i][j];
		chessBoard.board[i][j] = null;
	}

	// Move Piece
	public static boolean sameColor(int i, int j, int ti, int tj) {
		if(chessBoard.board[i][j] != null && chessBoard.board[ti][tj] != null) {
			if(chessBoard.board[i][j].isBlack == chessBoard.board[ti][tj].isBlack) {
				return true;
			}
		}
		return false;
	}


	public String toString() {
		return "";
	}

}

