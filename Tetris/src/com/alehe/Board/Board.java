package com.alehe.Board;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Board extends Pane {

	public Board(int width, int height, int rastersize, BoardType board_type) {
		super();
		switch(board_type) {
		case Chess:
			initiateChessBoard(width, height, rastersize);
			break;
		case Raster:
			initiateRasterBoard(width, height, rastersize);
			break;
		default:
			break;
		
		}
		

	}
	
	private void initiateChessBoard(int width, int height, int rastersize){
		this.setPrefWidth(width);
		this.setPrefHeight(height);
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		for (int i = 0; i <= width; i += rastersize) {
			for (int j = 0; j <= height; j += rastersize) {
				if ((i / rastersize + j / rastersize) % 2 == 0) {
					Rectangle rect = new Rectangle(rastersize, rastersize, Color.WHITE);
					rect.setLayoutX(i);
					rect.setLayoutY(j);
					this.getChildren().add(rect);
				}
			}
		}
	}
	
	private void initiateRasterBoard(int width, int height, int rastersize){
		this.setPrefWidth(width);
		this.setPrefHeight(height);
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		for (int i = 0; i <= width; i += rastersize) {
			for (int j = 0; j <= height; j += rastersize) {
				Line line1 = new Line(i, 0, i, height);
				line1.setStroke(Color.WHITE);
				Line line2 = new Line(0, j, width, j);
				line2.setStroke(Color.WHITE);
				this.getChildren().addAll(line1, line2);
			}
		}
	}
}
