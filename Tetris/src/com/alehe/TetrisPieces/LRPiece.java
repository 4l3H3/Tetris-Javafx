package com.alehe.TetrisPieces;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class LRPiece extends Shape {

	public LRPiece(int x, int y, int size) {
		super(size);
		this.body = new TetrisBlock[] {
			new TetrisBlock(size, x, y, Color.BLUE),
			new TetrisBlock(size, x + spacing, y, Color.BLUE),
			new TetrisBlock(size, x + spacing * 2, y, Color.BLUE),
			new TetrisBlock(size, x + spacing * 2, y + spacing, Color.BLUE),
		}; 
		super.rotationpoint_x = (int)this.body[1].getLayoutX();
		super.rotationpoint_y = (int)this.body[1].getLayoutY();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void rotateRight() {	
		for (TetrisBlock block : this.body) {
			int new_x = (int)block.getLayoutX() - rotationpoint_x;
			int new_y = (int)block.getLayoutY() - rotationpoint_y;
			block.setLayoutX(-new_y + rotationpoint_x);
			block.setLayoutY(new_x + rotationpoint_y);
		}
	}

	@Override
	public List<Integer> getNextRotationPointsRight() {
		List<Integer> list = new ArrayList<Integer>();
		for (TetrisBlock block : this.body) {
			int new_x = (int)block.getLayoutX() - rotationpoint_x;
			int new_y = (int)block.getLayoutY() - rotationpoint_y;
			list.add(-new_y + rotationpoint_x);
			list.add(new_x + rotationpoint_y);
		}
		return list;
	}

	@Override
	public void rotateLeft() {
		for (TetrisBlock block : this.body) {
			int new_x = (int)block.getLayoutX() - rotationpoint_x;
			int new_y = (int)block.getLayoutY() - rotationpoint_y;
			block.setLayoutX(new_y + rotationpoint_x);
			block.setLayoutY(-new_x + rotationpoint_y);
		}
	}

	@Override
	public List<Integer> getNextRotationPointsLeft() {
		List<Integer> list = new ArrayList<Integer>();
		for (TetrisBlock block : this.body) {
			int new_x = (int)block.getLayoutX() - rotationpoint_x;
			int new_y = (int)block.getLayoutY() - rotationpoint_y;
			list.add(new_y + rotationpoint_x);
			list.add(-new_x + rotationpoint_y);
		}
		return list;
	}
	
}