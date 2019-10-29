package chess;

import java.util.*;

import chessPiece.*;

public class chessBoard {

	public static boolean whiteCastleKing = true;
	public static boolean whiteCastleQueen = true;
	public static boolean blackCastleKing = true;
	public static boolean blackCastleQueen = true;

	public static boolean wantPromo = false;
	public static boolean canPromo = false;
	public static Character promo;
	public static boolean promoColor;

	public static ChessPiece [][] board = new ChessPiece[9][9];
	public static int [] canEnPassantHere = new int[2];


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
			board[1][i] = new ChessPiecePawn(true);
		}

		// Filling all white pawns
		for(int i=0; i<8; i++) {
			board[6][i] = new ChessPiecePawn(false);
		}
	}


	public static void printBoard() {

		for(int i =0; i<9; i++) {
			if(i == 8) {
				System.out.println(" a  b  c  d  e  f  g  h");
				break;
			}
			for(int j =0; j<9; j++) {
				if (j==8) {
					System.out.print(8-i);
				} else {
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
			}
			System.out.println();
		}
		System.out.println();
	}



	public static void runGame() {


		int counter = 0;
		int currentI, currentJ, targetI, targetJ;
		String input = null;
		boolean drawMode = false;

		while(true) {

			Scanner scanner = new Scanner(System.in);

			//*
			ChessHelper.enPassantCounter++;
			if(ChessHelper.enPassantCounter == 3) {
				ChessHelper.enPassantCounter = 0;
				chessBoard.canEnPassantHere[0] = -1;
			}
			//*

			if(counter%2 == 0) {
				// check if the piece is black 
				System.out.print("White's move:");
				input = scanner.nextLine();
				if(input.equalsIgnoreCase("resign")) {
					System.out.print("Black wins");
					break;
				}
				System.out.println();

			} else {
				// check if the piece is white 
				System.out.print("Black's move:");
				input = scanner.nextLine();
				if(input.equalsIgnoreCase("resign")) {
					System.out.print("White wins");
					break;
				}
				System.out.println();
			}

			// draw implementation
			if(input.contains("draw")) {
				if(!drawMode) {
					drawMode = true;
				} else {
					System.out.println("draw");
					break;
				}
			}

			currentI = ChessHelper.getCurrentI(input);
			currentJ = ChessHelper.getCurrentJ(input);
			targetI = ChessHelper.getTargetI(input);
			targetJ = ChessHelper.getTargetJ(input);

			if(input.length()==7) {
				wantPromo=true;
			}

			// Checks White and Black turns
			if(!ChessHelper.checkTurn(counter,currentI,currentJ )) {
				continue;
			}	

			// If turn is correct, move on
			if(!ChessHelper.movePiece(currentI, currentJ, targetI, targetJ)) {
				System.out.println("Illegal move, try again\n");
				ChessHelper.enPassantCounter--;
				continue;
			}

			if(canPromo) {
				promo = input.charAt(6);
				promoColor = board[targetI][targetJ].isBlack;

				if(promo.equals('N')) {
					board[targetI][targetJ] = new ChessPieceKnight(promoColor);
				}
				if(promo.equals('R')) {
					board[targetI][targetJ] = new ChessPieceRook(promoColor);
				}
				if(promo.equals('Q')) {
					board[targetI][targetJ] = new ChessPieceQueen(promoColor);
				}
				if(promo.equals('B')) {
					board[targetI][targetJ] = new ChessPieceBishop(promoColor);
				}

				wantPromo=false;
				canPromo=false;

			}

			if(ChessHelper.inCheck(!chessBoard.board[targetI][targetJ].isBlack)) {
				System.out.println("Check");
				if(!ChessHelper.canUncheck(!chessBoard.board[targetI][targetJ].isBlack)) {
					if(chessBoard.board[targetI][targetJ].isBlack) {
						System.out.println("Black Wins.");
					}else{
						System.out.println("White Wins.");
					}
					chessBoard.printBoard();
					break;
				}
			}



			if(ChessPieceKing.class.isInstance(board[targetI][targetJ])) {
				if(board[targetI][targetJ].isBlack) {
					blackCastleKing = false;
					blackCastleQueen = false;
				} else {
					whiteCastleKing = false;
					whiteCastleQueen = false;
				}

			}

			if(currentI==7 && currentJ==7) {
				whiteCastleKing = false;
			}
			if(currentI==7 && currentJ==0) {
				whiteCastleQueen = false;
			}
			if(currentI==0 && currentJ==0) {
				blackCastleQueen = false;
			}
			if(currentI==0 && currentJ==7) {
				blackCastleKing = false;
			}

			chessBoard.printBoard();
			counter++;
		}
	}
}



