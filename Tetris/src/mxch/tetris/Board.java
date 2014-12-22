package mxch.tetris;

import java.util.ArrayList;

import mxch.geometry.block.Block;
import mxch.geometry.block.Block.BlockType;
import mxch.geometry.block.EmptyBlock;
import mxch.geometry.block.FullBlock;
import mxch.geometry.tetromino.Piece;
import mxch.geometry.tetromino.Piece.Orientation;
import mxch.geometry.tetromino.Piece.PieceType;

/**
 * This class represents a 2d array of the board.
 * Blocks are stored by row and column.
 * @author maxchiang
 *
 */
public class Board {
	public enum Movement {RIGHT_ONE, LEFT_ONE, UP_ONE, DOWN_ONE, DOWN_ALL, ROTATE_L, ROTATE_R};
	private static int pxWidth = 200, pxHeight = 400;
	private static int width = 10, height = 20;
	private static int dx = pxWidth/width, dy = pxHeight/height;
	private Block[][] rowLayout;

	public Board() {
		rowLayout = new Block[height][width];
		for (int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				rowLayout[row][col] = EmptyBlock.getInstance();
			}
		}
	}

	/**
	 * Getter for board dx.
	 * This is the number of pixels in the width of a block.
	 * @return
	 */
	public static int getDx() {
		return dx;
	}

	/**
	 * Getter for board dy.
	 * This is the number of pixels in the height of a block.
	 * @return
	 */
	public static int getDy() {
		return dy;
	}

	/**
	 * Getter for board width.
	 * Default is 10.
	 * @return
	 */
	public static int getWidth() {
		return width;
	}

	/**
	 * Getter for board height.
	 * Default is 20.
	 * @return
	 */
	public static int getHeight() {
		return height;
	}

	/**
	 * Returns a list of the FullBlocks on the board.
	 * @return
	 */
	public ArrayList<FullBlock> getBlocks() {
		ArrayList<FullBlock> blocks = new ArrayList<FullBlock>();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Block b = rowLayout[row][col];
				if (b.getType() == BlockType.FULL) {
					blocks.add((FullBlock)b);
				}
			}
		}
		return blocks;
	}

	/**
	 * Adds piece to the board. Each of the blocks contained in piece is added.
	 * @param piece
	 */
	public void addPiece(Piece piece) {
		//System.out.println("Adding Piece.");
		for (Block b : piece.getBlocks()) {
			int row = b.getIntY();
			int col = b.getIntX();
			//System.out.println("Adding block at " + row + " " + col);
			if (rowColIsValid(row, col)) rowLayout[row][col] = b;

		}
	}

	/**
	 * Removes piece from board. Each of the blocks contained in piece is 
	 * removed.
	 * @param piece
	 */
	public void removePiece(Piece piece) {
		for (Block b : piece.getBlocks()) {
			int row = b.getIntY();
			int col = b.getIntX();
			if (rowColIsValid(row, col)) rowLayout[row][col] = EmptyBlock.getInstance();
		}
	}
	
	public void addBlock(Block b) {
		int row = b.getIntY();
		int col = b.getIntX();
		//System.out.println("Adding block at " + row + " " + col);
		if (rowColIsValid(row, col)) rowLayout[row][col] = b;
	}
	
	public void removeBlock(Block b) {
		int row = b.getIntY();
		int col = b.getIntX();
		if (rowColIsValid(row, col)) rowLayout[row][col] = EmptyBlock.getInstance();
	}

	/**
	 * Attempts to move piece by the Movement specified by m.
	 * @param piece
	 * @param m
	 */
	public void move(Piece piece, Movement m) {
		if (m == Movement.DOWN_ALL) {
			m = Movement.DOWN_ONE;
			while (canMoveOne(piece, m)) {
				removePiece(piece);
				piece.moveOne(m);
				addPiece(piece);
			}
		}
		else if (m == Movement.ROTATE_L) {
			removePiece(piece);
			if (canRotateL(piece)) {
				/*TEST*/
				System.out.println("Can rotate L.");
				piece.rotateL();
			}
			addPiece(piece);
		}
		else if (m == Movement.ROTATE_R) {
			removePiece(piece);
			if (canRotateR(piece)) {
				/*TEST*/
				System.out.println("Can rotate R.");
				piece.rotateR();
			}
			addPiece(piece);

		} else {
			if (canMoveOne(piece, m)) {
				removePiece(piece);
				piece.moveOne(m);
				addPiece(piece);
			}
		}

	}

	private boolean rowColIsValid(int row, int col) {
		return col >= 0 && col < width &&
				row >= 0 && row < height - 2; // for statusbar offset
	}

	/**
	 * Checks to see if block b is adjacent to a block within the board
	 * that is not part of the same piece as b.
	 * @param piece The piece b is a part of.
	 * @param b The block to check.
	 * @param m The direction of movement to check.
	 * @return True if b is adjacent to a block x in the movement direction m
	 * and x is not a part of piece. False otherwise.
	 */
	private boolean blockOnBlock(Piece piece, Block b, Movement m) {
		int row = 0, col = 0;
		switch (m) {
		case RIGHT_ONE:
			row = b.getIntY();
			col = b.getIntX() + 1;
			break;
		case LEFT_ONE:
			row = b.getIntY();
			col = b.getIntX() - 1;
			break;
		case DOWN_ONE:
			row = b.getIntY() + 1;
			col = b.getIntX();
			break;
		}

		if (rowColIsValid(row, col)) {
			Block o = rowLayout[row][col];
			if (!piece.getBlocks().contains(o)) {
				return o.getType() == BlockType.FULL;
			}
		}
		return false;
	}

	/**
	 * Checks to see if the block b is adjacent to the top, bottom, right or
	 * leftmost edge of the board.
	 * @param b The block to check.
	 * @param m The direction of movement to check.
	 * @return True if b is adjacent to an edge in the movement direction m.
	 * False otherwise.
	 */
	private boolean blockOnEdge(Block b, Movement m) {
		switch (m) {
		case DOWN_ONE:
			return b.getIntY() + 1 + 2 == height; // + 2 for statusbar offset...
		case RIGHT_ONE:
			return b.getIntX() + 1 == width;
		case LEFT_ONE:
			return b.getIntX() == 0;
		case UP_ONE:
			return b.getIntY() <= 0; // checks if the block is on or above the top edge.
		default:
			return false;
		}
	}

	/**
	 * Returns true if piece is in place, meaning for each block b of piece,
	 * b is on the bottom of the board or on another block, false otherwise.
	 * @param piece
	 * @return
	 */
	public boolean isPieceInPlace(Piece piece) {
		for (Block b : piece.getBlocks()) {
			if (blockOnEdge(b, Movement.DOWN_ONE) || 
					blockOnBlock(piece, b, Movement.DOWN_ONE)) {
				return true;
			}
		}
		return false;
	}

	
	public boolean isPieceOnTopEdge(Piece piece) {
		for (Block b : piece.getBlocks()) {
			if (blockOnEdge(b, Movement.UP_ONE)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isBlockValid(Piece p, Block b) {
		int row = b.getIntY();
		int col = b.getIntX();
		if (row < 0) {
			return true;
		}
		else if (rowColIsValid(row,col) &&
				rowLayout[row][col].getType() == BlockType.EMPTY) {
			return true;
		}
		return false;
	}
	
	private boolean isPieceValid(Piece piece) {
		for (Block b : piece.getBlocks()) {
			if(!isBlockValid(piece, b)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Returns true if it is possible to perform a right rotation on piece, 
	 * false otherwise.
	 * 
	 * J Piece:	1
	 * 			2 3 4
	 * 
	 * L Piece: 	4
	 * 			1 2 3
	 * 
	 * I Piece: 1 2 3 4
	 * 
	 * Z Piece:	1 2
	 * 			  3 4
	 * 
	 * S Piece:   3 4
	 * 			1 2
	 * 
	 * T Piece:   3
	 * 			1 2 4
	 * @param piece
	 * @return
	 */
	public boolean canRotateR(Piece piece) {
		Piece temp = piece.getRotateR();
		if (isPieceValid(temp)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if it is possible to perform a left rotation on piece, 
	 * false otherwise.
	 * @param piece
	 * @return
	 */
	public boolean canRotateL(Piece piece) {
		Piece temp = piece.getRotateL();
		if (isPieceValid(temp)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if it is possible to move piece down one, false otherwise.
	 * @param piece
	 * @return
	 */
	public boolean canMoveOne(Piece piece, Movement m) {
		for (FullBlock b : piece.getBlocks()) {
			// check if block is on bottom or on another block
			if (blockOnEdge(b, m) || blockOnBlock(piece, b, m)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Clears all rows containing all FullBlocks.
	 * @return The number of rows cleared.
	 */
	public int clearLines() {
		int numLines = 0;
		for (int row = 0; row < height; row++) {
			if (isLine(rowLayout[row])) {
				/* TEST */
				//System.out.println("Clearing row: " + row);
				clearLine(rowLayout[row]);
				compressLines(row);
				numLines++;
			}
		}
		return numLines;
	}
	
	private boolean isLine(Block[] a) {
		for(int i = 0; i < a.length; i++) {
			if (a[i].getType() == BlockType.EMPTY) {
				return false;
			}
		}
		return true;
	}
	
	private void clearLine(Block[] a) {
		for(int i = 0; i < a.length; i++) {
			a[i] = EmptyBlock.getInstance();
		}
	}
	
	private void compressLines(int deletedRow) {
		for (int row = deletedRow-1; row >= 0; row--) {
			for (int col = 0; col < width; col++) {
				Block top = rowLayout[row][col];
				if (top.getType() == BlockType.FULL) {
					/*TEST*/
					//System.out.println("Moving top to bottom");
					removeBlock(top);
					top.moveDownOne();
					addBlock(top);
				}
			}
		}
	}
}
