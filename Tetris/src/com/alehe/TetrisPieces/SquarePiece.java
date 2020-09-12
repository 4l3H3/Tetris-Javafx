package com.alehe.TetrisPieces;
import java.util.List;

import javafx.scene.image.Image;

public class SquarePiece extends TetrisPiece{

	public SquarePiece(int x, int y, int size, Image image) {
		super(size);
		this.body = new TetrisBlock[] {
					new TetrisBlock(size, x + spacing, y, image),
					new TetrisBlock(size, x + spacing * 2, y, image),
					new TetrisBlock(size, x + spacing, y + spacing, image),
					new TetrisBlock(size, x + spacing * 2, y + spacing, image),
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
