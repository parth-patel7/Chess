package chess;

import java.util.*;

import chessPiece.*;

public class chessBoard {



	public static ChessPiece [][] board = new ChessPiece[8][8];

	public static void loadGame() {

		// Filling all black pieces
		board[0][0] = new ChessPieceRook(true);	
		board[0][1] = new ChessPieceKnight(true);
		board[0][2] = new ChessPieceBishop(true);
		board[0][3] = new ChessPieceQueen(true);	
		board[0][4] = new ChessPieceKing(true);
		board[0][5] = new ChessPieceBishop(true);
		board[0][6] = new ChessPieceKnight(true);
		board[0][7] = new ChessPieceRook(true);	


		// Filling all white pieces
		board[7][0] = new ChessPieceRook(false);	
		board[7][1] = new ChessPieceKnight(false);
		board[7][2] = new ChessPieceBishop(false);	
		board[7][3] = new ChessPieceQueen(false);	
		board[7][4] = new ChessPieceKing(false);
		board[7][5] = new ChessPieceBishop(false);
		board[7][6] = new ChessPieceKnight(false);
		board[7][7] = new ChessPieceRook(false);	

		// Filling all black pawns
		for(int i=0; i<8; i++) {
			board[1][i] = new ChessPiecePawn(false);
		}

		// Filling all white pawns
		for(int i=0; i<8; i++) {
			board[6][i] = new ChessPiecePawn(false);
		}
	}

	public void getPiece(int i, int j){


	}


	public static void printBoard() {

		for(int i =0; i<8; i++) {
			for(int j =0; j<8; j++) {

				if(board[i][j] == null) {
					if(i%2 != 0) {
						if(j%2 == 0) {
							System.out.print("## ");
						} else {
							System.out.print("   ");
						}
					} else {
						if(j%2 != 0) {
							System.out.print("## ");
						} else {
							System.out.print("   ");
						}
					}

				} else {
					System.out.print(board[i][j].toString());
				}
			}
			System.out.println();
		}
		System.out.println();
	}



	public static void runGame() {

		int counter = 0;
		int currentI, currentJ, targetI, targetJ;
		String input = null;

		while(counter != 10) {

			Scanner scanner = new Scanner(System.in);

			if(counter%2 == 0) {
				// check if the piece is black 
				System.out.print("White's move:");
				input = scanner.nextLine();
				System.out.println();

			} else {
				// check if the piece is white 
				System.out.print("Black's move:");
				input = scanner.nextLine();
				System.out.println();
			}

			currentI = ChessHelper.getCurrentI(input);
			currentJ = ChessHelper.getCurrentJ(input);
			targetI = ChessHelper.getTargetI(input);
			targetJ = ChessHelper.getTargetJ(input);

			// Checks White and Black turns
			if(!ChessHelper.checkTurn(counter,currentI,currentJ )) {
				continue;
			}
	
			// If turn is correct, move on
			if(!ChessHelper.movePiece(currentI, currentJ, targetI, targetJ)) {
				System.out.println("Illegal move, try again\n");
			}

			chessBoard.printBoard();
			counter++;
		}
	}
}



