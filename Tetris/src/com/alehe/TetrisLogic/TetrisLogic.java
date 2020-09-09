package com.alehe.TetrisLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.alehe.TetrisPieces.PieceType;
import com.alehe.TetrisPieces.LPiece;
import com.alehe.TetrisPieces.LRPiece;
import com.alehe.TetrisPieces.LongPiece;
import com.alehe.TetrisPieces.TetrisPiece;
import com.alehe.TetrisPieces.SquarePiece;
import com.alehe.TetrisPieces.StairPiece;
import com.alehe.TetrisPieces.StairRPiece;
import com.alehe.TetrisPieces.TPiece;
import com.alehe.TetrisPieces.TetrisBlock;

public class TetrisLogic {
	
	private static Random random = new Random();
	
	private static List<TetrisBlock> cleared_blocks;

	public static void addPieceToArray(TetrisPiece shape, TetrisBlock[][] array, int scaledown) {
		for (TetrisBlock block : shape.getBody()) {
			array[(int) block.getLayoutX() / scaledown][((int) block.getLayoutY()) / scaledown] = block;
		}
	}

	public static void movePieceInArray(TetrisPiece piece, TetrisBlock[][] array, int downscale, Action action) {
		removeFromArray(piece, array, downscale);
		switch (action) {
		case MOVELEFT:
			if (isInsideOfBoundsLeft(piece, array, downscale) && !collidesWithAnotherPieceLeft(piece, array, downscale))
				piece.moveLeft();
			break;
		case MOVEDOWN:
			if (isInsideOfBoundsDown(piece, array, downscale) && !collidesWithAnotherPieceDown(piece, array, downscale))
				piece.moveDown();
			break;
		case MOVERIGHT:
			if (isInsideOfBoundsRight(piece, array, downscale)
					&& !collidesWithAnotherPieceRight(piece, array, downscale))
				piece.moveRight();
			break;
		case ROTATERIGHT:
			if (isRotationValidRight(piece, array, downscale))
				piece.rotateRight();
			break;
		case ROTATELEFT:
			if (isRotationValidLeft(piece, array, downscale))
				piece.rotateLeft();
			break;
		default:
			break;
		}
		for (TetrisBlock block : piece.getBody()) {
			array[(int) block.getLayoutX() / downscale][((int) block.getLayoutY() / downscale)] = block;
		}
	}

	public static void removeFromArray(TetrisPiece piece, TetrisBlock[][] array, int downscale) {
		for (TetrisBlock block : piece.getBody()) {
			array[(int) block.getLayoutX() / downscale][((int) block.getLayoutY() / downscale)] = null;
		}
	}

	public static boolean collidesWithAnotherPieceLeft(TetrisPiece shape, TetrisBlock[][] array, int downscale) {
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

	public static boolean collidesWithAnotherPieceRight(TetrisPiece piece, TetrisBlock[][] array, int downscale) {
		for (TetrisBlock block : piece.getBody()) {
			if ((int) block.getLayoutX() / downscale + 1 < array.length)
				if (array[(int) block.getLayoutX() / downscale + 1][((int) block.getLayoutY() / downscale)] != null
						&& !isInArray(
								array[(int) block.getLayoutX() / downscale + 1][((int) block.getLayoutY() / downscale)],
								piece.getBody()))
					return true;
		}
		return false;
	}

	public static boolean collidesWithAnotherPieceDown(TetrisPiece shape, TetrisBlock[][] array, int downscale) {
		for (TetrisBlock block : shape.getBody()) {
			if ((int) block.getLayoutY() / downscale + 1 < array[0].length)
				if (array[(int) block.getLayoutX() / downscale][(((int) block.getLayoutY() / downscale) + 1)] != null
						&& !isInArray(array[(int) block.getLayoutX()
								/ downscale][(((int) block.getLayoutY() / downscale) + 1)], shape.getBody()))
					return true;
		}
		return false;
	}

	public static boolean isRotationValidLeft(TetrisPiece piece, TetrisBlock[][] array, int downscale) {
		List<Integer> new_coords = piece.getNextRotationPointsRight();
		for (int i = 0; i < new_coords.size(); i += 2) {
			if (array[new_coords.get(i) / downscale][new_coords.get(i + 1) / downscale] != null
					&& !isInArray(array[new_coords.get(i) / downscale][new_coords.get(i + 1) / downscale],
							piece.getBody()))
				return false;
		}
		return true;
	}

	public static boolean isRotationValidRight(TetrisPiece piece, TetrisBlock[][] array, int downscale) {
		List<Integer> new_coords = piece.getNextRotationPointsLeft();
		for (int i = 0; i < new_coords.size(); i += 2) {
			if (array[new_coords.get(i) / downscale][new_coords.get(i + 1) / downscale] != null
					&& !isInArray(array[new_coords.get(i) / downscale][new_coords.get(i + 1) / downscale],
							piece.getBody()))
				return false;
		}
		return true;
	}

	public static boolean isInsideOfBoundsRight(TetrisPiece piece, TetrisBlock[][] array, int downscale) {
		for (TetrisBlock block : piece.getBody()) {
			if (block.getLayoutX() / downscale + 1 >= array.length)
				return false;
		}
		return true;
	}

	public static boolean isInsideOfBoundsLeft(TetrisPiece piece, TetrisBlock[][] array, int downscale) {
		for (TetrisBlock block : piece.getBody()) {
			if (block.getLayoutX() / downscale - 1 <= -0.1)
				return false;
		}
		return true;
	}

	public static boolean isInsideOfBoundsDown(TetrisPiece piece, TetrisBlock[][] array, int downscale) {
		boolean is_inside = true;
		for (TetrisBlock block : piece.getBody()) {
			if (((int) block.getLayoutY() / downscale + 1) >= array[0].length)
				is_inside = false;
		}
		return is_inside;
	}

	public static boolean isInArray(Object object, Object[] array) {
		for (Object i : array) {
			if (i == object)
				return true;
		}
		return false;
	}
	
	public static TetrisPiece getNewTetrisPiece(int x, int y, int size) {
		PieceType form = PieceType.values()[random.nextInt(7)];
		switch (form) {
		case LONGPIECE:
			return new LongPiece(x, y, size);
		case LPIECE:
			return new LPiece(x, y, size);
		case LPIECEREVERSE:
			return new LRPiece(x, y, size);
		case SQUAREPIECE:
			return new SquarePiece(x, y, size);
		case STAIRPIECE:
			return new StairPiece(x, y, size);
		case STAIRPIECEREVERSE:
			return new StairRPiece(x, y, size);
		case TPIECE:
			return new TPiece(x, y, size);
		default:
			return null;
		}
	}
	
	public static void clearEmptyRows(TetrisPiece piece, TetrisBlock[][] array, int downscale) {
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
