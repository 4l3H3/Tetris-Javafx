package com.alehe.TetrisPieces;
import java.util.List;

import javafx.scene.paint.Color;

public class SquarePiece extends Shape{

	public SquarePiece(int x, int y, int size) {
		super(size);
		this.body = new TetrisBlock[] {
					new TetrisBlock(size, x + spacing, y, Color.YELLOW),
					new TetrisBlock(size, x + spacing * 2, y, Color.YELLOW),
					new TetrisBlock(size, x + spacing, y + spacing, Color.YELLOW),
					new TetrisBlock(size, x + spacing * 2, y + spacing, Color.YELLOW),
				};
		// TODO Auto-generated constructor stub
	}

	@Override
	public void rotateRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Integer> getNextRotationPointsRight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rotateLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Integer> getNextRotationPointsLeft() {
		// TODO Auto-generated method stub
		return null;
	}

}
