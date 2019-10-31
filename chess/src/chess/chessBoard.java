package chess;

import java.util.*;

import chessPiece.*;

/**
 * @author parthpatel
 * @author joshherrera
 */
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


	/**
	 * This method loads all the pieces in the chess board array
	 */
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


	/**
	 * This method prints the chess board
	 */
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


	/**
	 * This method runs the game 
	 * checks if the move and turns are legal 
	 * if there is a check, checkmate or stalemate
	 */
	public static void runGame() {

		int counter = 0;
		int currentI, currentJ, targetI, targetJ;
		String input = null;
		boolean drawMode = false;
		int drawCounter = 0;

		while(true) {

			Scanner scanner = new Scanner(System.in);

			//*
			if(chessBoard.canEnPassantHere[0] != -1) {
				ChessHelper.enPassantCounter++;
			}
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

			if(input.endsWith("draw?")) {
				drawCounter = 1;
			}

			if(input.endsWith("draw")) {
				if(drawCounter == 1) {
					System.out.println("draw");
					break;
				}
			}

			if(!input.endsWith("draw") && !input.endsWith("draw?")) {
				if(drawCounter == 1) {
					drawCounter = 0;
				}
			}

			currentI = ChessHelper.getCurrentI(input);
			currentJ = ChessHelper.getCurrentJ(input);
			targetI = ChessHelper.getTargetI(input);
			targetJ = ChessHelper.getTargetJ(input);
			
			
			if(input.contains("Q") || input.contains("N") || input.contains("R") || input.contains("B")) {
				wantPromo=true;
			}

			// Checks White and Black turns
			if(!ChessHelper.checkTurn(counter,currentI,currentJ)) {
				ChessHelper.enPassantCounter--;
				continue;
			}	
		

			// If turn is correct, move on
			if(!ChessHelper.movePiece(currentI, currentJ, targetI, targetJ)) {
				System.out.println("Illegal move, try again\n");
				ChessHelper.enPassantCounter--;
				continue;
			}

			// (pawn promotion)
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

			chessBoard.printBoard();

			// (end game):
			// if: in check && cannot uncheck -> checkmate
			// if: not in check && cannot uncheck -> stalemate
			// if: not in check && can uncheck -> continue
			if(ChessHelper.inCheck(!chessBoard.board[targetI][targetJ].isBlack)) {
				System.out.println("Check");
				// Detect for checkmate
				if(!ChessHelper.canUncheck(!chessBoard.board[targetI][targetJ].isBlack)) {
					System.out.println("Checkmate");
					if(chessBoard.board[targetI][targetJ].isBlack) {
						System.out.println("Black Wins");
					}else{
						System.out.println("White Wins");

					}
					break;
				}
			}else {
				// Detect for stalemate
				if(!ChessHelper.canUncheck(!chessBoard.board[targetI][targetJ].isBlack)) {
					System.out.println("Stalemate");
					System.out.println("draw");
					break;
				}
			}

			// (castling): 	determine if either king has moved, if so, that team cannot castle.
			if(ChessPieceKing.class.isInstance(board[targetI][targetJ])) {
				if(board[targetI][targetJ].isBlack) {
					blackCastleKing = false;
					blackCastleQueen = false;
				} else {
					whiteCastleKing = false;
					whiteCastleQueen = false;
				}

			}

			// (castling): 	determine if attempted rook has ever moved or had been captured, 
			//				if so, that team cannot castle on that side.
			if((currentI==7 && currentJ==7) || (targetI==7 && targetJ==7)) {
				whiteCastleKing = false;
			}
			if((currentI==7 && currentJ==0) || (targetI==7 && targetJ==0)) {
				whiteCastleQueen = false;
			}
			if(currentI==0 && currentJ==0 || (targetI==0 && targetJ==0)) {
				blackCastleQueen = false;
			}
			if(currentI==0 && currentJ==7 || (targetI==0 && targetJ==7)) {
				blackCastleKing = false;
			}

			counter++;
		}
	}
}



