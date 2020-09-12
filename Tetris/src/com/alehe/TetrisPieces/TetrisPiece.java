package com.alehe.TetrisPieces;
import java.util.List;

import javafx.scene.image.ImageView;

public abstract class TetrisPiece extends ImageView {

	protected TetrisBlock[] body;
	protected final int spacing;
	protected int rotationpoint_x;
	protected int rotationpoint_y;
	
	public TetrisPiece(int size) {	
		spacing = size;
	}
	
	public TetrisBlock[] getBody() {
		return body;
	}
	
	public void moveDown() {
		for (TetrisBlock block : body)
			block.moveDown();
		rotationpoint_y += spacing;
	}
	
	public void moveLeft() {
		for (TetrisBlock block : body)
			block.moveLeft();
		rotationpoint_x -= spacing;
	}
	public void moveRight() {
		for (TetrisBlock block : body)
			block.moveRight();
		rotationpoint_x += spacing;
	}
	public abstract void rotateRight();
	public abstract void rotateLeft();
	public abstract List<Integer> getNextRotationPointsRight();
	public abstract List<Integer> getNextRotationPointsLeft();

}
