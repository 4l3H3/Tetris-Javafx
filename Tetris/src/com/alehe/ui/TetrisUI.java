package com.alehe.ui;

import java.util.Random;

import com.alehe.Board.Board;
import com.alehe.Board.BoardType;
import com.alehe.TetrisLogic.Action;
import com.alehe.TetrisLogic.TetrisLogic;
import com.alehe.TetrisPieces.Shape;
import com.alehe.TetrisPieces.TetrisBlock;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TetrisUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	//TODO hold a piece, score, game over
	
	private final int SIZE = 20;
	private final int SCALE_X = 10;
	private final int SCALE_Y = 24;
	private final int MAX_X = SIZE * SCALE_X;
	private final int MAX_Y = SIZE * SCALE_Y;
	private final int BLOCKSPAWNPOINT_X = SIZE * (SCALE_X / 2) - SIZE * 2;
	private final int BLOCKSPAWNPOINT_Y = SIZE * 0;

	private final TetrisBlock[][] arrayboard = new TetrisBlock[MAX_X / SIZE][MAX_Y / SIZE];

	Random random = new Random();

	private Shape current_tetris_piece = null;
	private Board board = new Board(MAX_X, MAX_Y, SIZE, BoardType.Raster);
	private Rectangle hold = new Rectangle(SIZE * 4, SIZE * 4, Color.WHITE);
	private Rectangle next = new Rectangle(SIZE * 4, SIZE * 4, Color.WHITE);

	public void start(Stage primarystage) throws Exception {
		
		hold.setLayoutX(MAX_X + MAX_X / 2 - SIZE * 2);
		hold.setLayoutY(MAX_Y / 2 - SIZE * 8);
		board.getChildren().add(hold);
		
		next.setLayoutX(MAX_X + MAX_X / 2 - SIZE * 2);
		next.setLayoutY(MAX_Y / 2 - SIZE / 8);
		board.getChildren().add(next);
		
		current_tetris_piece = TetrisLogic.getNewShape(BLOCKSPAWNPOINT_X, BLOCKSPAWNPOINT_Y, SIZE);
		board.getChildren().addAll(current_tetris_piece.getBody());
		TetrisLogic.addShapeToArray(current_tetris_piece, arrayboard, SIZE);
		AnimationTimer timer = new AnimationTimer() {

			public void handle(long arg0) {
				update();
			}
		};
		timer.start();
		Scene scene = new Scene(board);
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case LEFT:
				TetrisLogic.movePieceInArray(current_tetris_piece, arrayboard, SIZE, Action.MOVELEFT);
				break;
			case RIGHT:
				TetrisLogic.movePieceInArray(current_tetris_piece, arrayboard, SIZE, Action.MOVERIGHT);
				break;
			case DOWN:
				TetrisLogic.movePieceInArray(current_tetris_piece, arrayboard, SIZE, Action.MOVEDOWN);
				break;
			case A:
				TetrisLogic.movePieceInArray(current_tetris_piece, arrayboard, SIZE, Action.ROTATELEFT);
				break;
			case D:
				TetrisLogic.movePieceInArray(current_tetris_piece, arrayboard, SIZE, Action.ROTATERIGHT);
				break;
			case S:
				break;
			default:
				break;
			}
		});

		primarystage.setTitle("Tetris");
		primarystage.setScene(scene);
		primarystage.show();
	}

	private void update() {
		if (TetrisLogic.isInsideOfBoundsDown(current_tetris_piece, arrayboard, SIZE) && 
				!TetrisLogic.collidesWithAnotherPieceDown(current_tetris_piece, arrayboard, SIZE)) {
			TetrisLogic.movePieceInArray(current_tetris_piece, arrayboard, SIZE, Action.MOVEDOWN);
			
		}
		else {
			TetrisLogic.clearEmptyRows(current_tetris_piece, arrayboard, SIZE);
			if (TetrisLogic.getClearedObjects() != null)
				board.getChildren().removeAll(TetrisLogic.getClearedObjects());
			current_tetris_piece = TetrisLogic.getNewShape(BLOCKSPAWNPOINT_X, BLOCKSPAWNPOINT_Y, SIZE);
			TetrisLogic.addShapeToArray(current_tetris_piece, arrayboard, SIZE);
			board.getChildren().addAll(current_tetris_piece.getBody());
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
