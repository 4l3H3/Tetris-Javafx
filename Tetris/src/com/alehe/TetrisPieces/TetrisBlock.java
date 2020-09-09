package com.alehe.TetrisPieces;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TetrisBlock extends Rectangle {

	private final int spacing;
	
	public TetrisBlock(int size, int x, int y, Color color) {
		super(size - 1, size - 1, color);
		spacing = size;
		setLayoutX(x);
		setLayoutY(y);
	}
	
	public void moveDown() {
		setLayoutY(getLayoutY() + spacing);
	}
	
	public void moveLeft() {
		setLayoutX(getLayoutX() - spacing);
	}
	
	public void moveRight() {
		setLayoutX(getLayoutX() + spacing);
	}
}
