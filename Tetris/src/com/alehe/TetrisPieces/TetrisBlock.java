package com.alehe.TetrisPieces;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TetrisBlock extends ImageView {

	private final int spacing;
	
	public TetrisBlock(int size, int x, int y, Image image) {
		super.setImage(image);
		super.setFitHeight(size);
		super.setFitWidth(size);
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
