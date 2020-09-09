package com.alehe.TetrisLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.alehe.TetrisPieces.PieceType;
import com.alehe.TetrisPieces.LPiece;
import com.alehe.TetrisPieces.LRPiece;
import com.alehe.TetrisPieces.LongPiece;
import com.alehe.TetrisPieces.Shape;
import com.alehe.TetrisPieces.SquarePiece;
import com.alehe.TetrisPieces.StairPiece;
import com.alehe.TetrisPieces.StairRPiece;
import com.alehe.TetrisPieces.TPiece;
import com.alehe.TetrisPieces.TetrisBlock;

public class TetrisLogic {
	
	private static Random random = new Random();
	
	private static List<TetrisBlock> cleared_blocks;

	public static void addShapeToArray(Shape shape, TetrisBlock[][] array, int scaledown) {
		for (TetrisBlock block : shape.getBody()) {
			array[(int) block.getLayoutX() / scaledown][((int) block.getLayoutY()) / scaledown] = block;
		}
	}

	public static void movePieceInArray(Shape shape, TetrisBlock[][] array, int downscale, Action action) {
		removeFromArray(shape, array, downscale);
		switch (action) {
		case MOVELEFT:
			if (isInsideOfBoundsLeft(shape, array, downscale) && !collidesWithAnotherPieceLeft(shape, array, downscale))
				shape.moveLeft();
			break;
		case MOVEDOWN:
			if (isInsideOfBoundsDown(shape, array, downscale) && !collidesWithAnotherPieceDown(shape, array, downscale))
				shape.moveDown();
			break;
		case MOVERIGHT:
			if (isInsideOfBoundsRight(shape, array, downscale)
					&& !collidesWithAnotherPieceRight(shape, array, downscale))
				shape.moveRight();
			break;
		case ROTATERIGHT:
			if (isRotationValidRight(shape, array, downscale))
				shape.rotateRight();
			break;
		case ROTATELEFT:
			if (isRotationValidLeft(shape, array, downscale))
				shape.rotateLeft();
			break;
		default:
			break;
		}
		for (TetrisBlock block : shape.getBody()) {
			array[(int) block.getLayoutX() / downscale][((int) block.getLayoutY() / downscale)] = block;
		}
	}

	public static void removeFromArray(Shape shape, TetrisBlock[][] array, int downscale) {
		for (TetrisBlock block : shape.getBody()) {
			array[(int) block.getLayoutX() / downscale][((int) block.getLayoutY() / downscale)] = null;
		}
	}

	public static boolean collidesWithAnotherPieceLeft(Shape shape, TetrisBlock[][] array, int downscale) {
		for (TetrisBlock block : shape.getBody()) {
			if ((int) block.getLayoutX() / downscale - 1 > -0.1)
				if (array[(int) block.getLayoutX() / downscale - 1][((int) block.getLayoutY() / downscale)] != null
						&& !isInArray(
								array[(int) block.getLayoutX() / downscale - 1][((int) block.getLayoutY() / downscale)],
								shape.getBody()))
					return true;
		}
		return false;
	}

	public static boolean collidesWithAnotherPieceRight(Shape shape, TetrisBlock[][] array, int downscale) {
		for (TetrisBlock block : shape.getBody()) {
			if ((int) block.getLayoutX() / downscale + 1 < array.length)
				if (array[(int) block.getLayoutX() / downscale + 1][((int) block.getLayoutY() / downscale)] != null
						&& !isInArray(
								array[(int) block.getLayoutX() / downscale + 1][((int) block.getLayoutY() / downscale)],
								shape.getBody()))
					return true;
		}
		return false;
	}

	public static boolean collidesWithAnotherPieceDown(Shape shape, TetrisBlock[][] array, int downscale) {
		for (TetrisBlock block : shape.getBody()) {
			if ((int) block.getLayoutY() / downscale + 1 < array[0].length)
				if (array[(int) block.getLayoutX() / downscale][(((int) block.getLayoutY() / downscale) + 1)] != null
						&& !isInArray(array[(int) block.getLayoutX()
								/ downscale][(((int) block.getLayoutY() / downscale) + 1)], shape.getBody()))
					return true;
		}
		return false;
	}

	public static boolean isRotationValidLeft(Shape shape, TetrisBlock[][] array, int downscale) {
		List<Integer> new_coords = shape.getNextRotationPointsRight();
		for (int i = 0; i < new_coords.size(); i += 2) {
			if (array[new_coords.get(i) / downscale][new_coords.get(i + 1) / downscale] != null
					&& !isInArray(array[new_coords.get(i) / downscale][new_coords.get(i + 1) / downscale],
							shape.getBody()))
				return false;
		}
		return true;
	}

	public static boolean isRotationValidRight(Shape shape, TetrisBlock[][] array, int downscale) {
		List<Integer> new_coords = shape.getNextRotationPointsLeft();
		for (int i = 0; i < new_coords.size(); i += 2) {
			if (array[new_coords.get(i) / downscale][new_coords.get(i + 1) / downscale] != null
					&& !isInArray(array[new_coords.get(i) / downscale][new_coords.get(i + 1) / downscale],
							shape.getBody()))
				return false;
		}
		return true;
	}

	public static boolean isInsideOfBoundsRight(Shape shape, TetrisBlock[][] array, int downscale) {
		for (TetrisBlock block : shape.getBody()) {
			if (block.getLayoutX() / downscale + 1 >= array.length)
				return false;
		}
		return true;
	}

	public static boolean isInsideOfBoundsLeft(Shape shape, TetrisBlock[][] array, int downscale) {
		for (TetrisBlock block : shape.getBody()) {
			if (block.getLayoutX() / downscale - 1 <= -0.1)
				return false;
		}
		return true;
	}

	public static boolean isInsideOfBoundsDown(Shape shape, TetrisBlock[][] array, int downscale) {
		boolean is_inside = true;
		for (TetrisBlock block : shape.getBody()) {
			if (((int) block.getLayoutY() / downscale + 1) >= array[0].length)
				is_inside = false;
		}
		return is_inside;
	}

	public static boolean isInArray(TetrisBlock block, TetrisBlock[] array) {
		for (TetrisBlock i : array) {
			if (i == block)
				return true;
		}
		return false;
	}
	
	public static Shape getNewShape(int x, int y, int size) {
		PieceType form = PieceType.values()[random.nextInt(7)];
		Shape new_shape = null;
		switch (form) {
		case LONGPIECE:
			new_shape = new LongPiece(x, y, size);
			break;
		case LPIECE:
			new_shape = new LPiece(x, y, size);
			break;
		case LPIECEREVERSE:
			new_shape = new LRPiece(x, y, size);
			break;
		case SQUAREPIECE:
			new_shape = new SquarePiece(x, y, size);
			break;
		case STAIRPIECE:
			new_shape = new StairPiece(x, y, size);
			break;
		case STAIRPIECEREVERSE:
			new_shape = new StairRPiece(x, y, size);
			break;
		case TPIECE:
			new_shape = new TPiece(x, y, size);
			break;
		default:
			break;
		}
		return new_shape;
	}
	
	public static void clearEmptyRows(Shape shape, TetrisBlock[][] array, int downscale) {
		cleared_blocks = new ArrayList<TetrisBlock>();
		for (int j = 0; j < array[0].length; j++) {
			boolean is_full = true;
			for (int i = 0; i < array.length; i++) {
				if (array[i][j] == null)
					is_full = false;
			}
			if (is_full) {
				clearRow(j, array);
				moveRowDown(j, array, downscale);
			}
		}
	}

	private static void clearRow(int i, TetrisBlock[][] array) {
		for (int j = 0; j < array.length; j++) {
			cleared_blocks.add(array[j][i]);
			array[j][i] = null;
		}
	}
	
	public static List<TetrisBlock> getClearedObjects() {
		return cleared_blocks;
	}

	private static void moveRowDown(int i, TetrisBlock[][] array, int downscale) {
		for (; i > 0; i--) {
			for (int j = 0; j < array.length; j++)
				if (array[j][i] != null && array[j][i - 1] != null) {
					array[j][i - 1].setLayoutY(array[j][i].getLayoutY());
					array[j][i] = array[j][i - 1];
					array[j][i - 1] = null;
				} else if (array[j][i] == null && array[j][i - 1] != null) {
					array[j][i] = array[j][i - 1];
					array[j][i].setLayoutY(array[j][i].getLayoutY() + downscale);
					array[j][i - 1] = null;
				} else if (array[j][i] != null && array[j][i - 1] == null) {
					array[j][i] = array[j][i - 1];
					array[j][i].setLayoutY(array[j][i].getLayoutY() + downscale);
				} else {
					array[j][i] = array[j][i - 1];
				}
		}
	}

}
