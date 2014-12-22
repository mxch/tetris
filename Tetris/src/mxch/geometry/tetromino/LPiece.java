package mxch.geometry.tetromino;

import java.awt.Color;
import java.util.ArrayList;

import mxch.geometry.block.FullBlock;
import mxch.geometry.tetromino.Piece.Orientation;
import mxch.geometry.tetromino.Piece.PieceType;
import mxch.tetris.Board;
import mxch.tetris.Main;

public class LPiece extends Piece {
	private static Color color = Color.ORANGE;

	public LPiece() {
		super(PieceType.L, color);
		/*
		// (3,0) (4,0) (5,0) (5,1)
		super.addBlock(new Block(3, 0, tetris, color));
		super.addBlock(new Block(4, 0, tetris, color));
		super.addBlock(new Block(5, 0, tetris, color));
		super.addBlock(new Block(5, 1, tetris, color));
		*/
		
		// generate above
		super.addBlock(new FullBlock(3, -1, Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(4, -1, Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(5, -1, Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(5, -2, Board.getDx(), Board.getDy(), color));
	}
	
	public LPiece(LPiece p) {
		super(PieceType.L, color);
		super.setOrientation(p.getOrientation());
		FullBlock b1 = p.getBlocks().get(0);
		FullBlock b2 = p.getBlocks().get(1);
		FullBlock b3 = p.getBlocks().get(2);
		FullBlock b4 = p.getBlocks().get(3);
		super.addBlock(new FullBlock(b1.getIntX(), b1.getIntY(), Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(b2.getIntX(), b2.getIntY(), Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(b3.getIntX(), b3.getIntY(), Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(b4.getIntX(), b4.getIntY(), Board.getDx(), Board.getDy(), color));
	}

	@Override
	public void rotateR() {
		Orientation o = this.getOrientation();
		switch (o) {
		case UP:
			/*TEST*/
			System.out.println("Rotating UP to RIGHT");
			getBlocks().get(0).moveBlock(-1, 1);
			getBlocks().get(1).moveBlock(0, 0);
			getBlocks().get(2).moveBlock(1, -1);
			getBlocks().get(3).moveBlock(0, -2);
			this.setOrientation(Orientation.RIGHT);
			break;
		case DOWN:
			/*TEST*/
			System.out.println("Rotating DOWN to LEFT");
			getBlocks().get(0).moveBlock(1, -1);
			getBlocks().get(1).moveBlock(0, 0);
			getBlocks().get(2).moveBlock(-1, 1);
			getBlocks().get(3).moveBlock(0, 2);
			this.setOrientation(Orientation.LEFT);
			break;
		case LEFT:
			/*TEST*/
			System.out.println("Rotating LEFT to UP");
			getBlocks().get(0).moveBlock(1, 1);
			getBlocks().get(1).moveBlock(0, 0);
			getBlocks().get(2).moveBlock(-1, -1);
			getBlocks().get(3).moveBlock(-2, 0);
			this.setOrientation(Orientation.UP);
			break;
		case RIGHT:
			/*TEST*/
			System.out.println("Rotating RIGHT to DOWN");
			getBlocks().get(0).moveBlock(-1, -1);
			getBlocks().get(1).moveBlock(0, 0);
			getBlocks().get(2).moveBlock(1, 1);
			getBlocks().get(3).moveBlock(2, 0);
			this.setOrientation(Orientation.DOWN);
			break;
		}
	}
	
	@Override
	public void rotateL() {
		Orientation o = this.getOrientation();
		switch (o) {
		case UP:
			/*TEST*/
			System.out.println("Rotating UP to LEFT");
			getBlocks().get(0).moveBlock(-1, -1);
			getBlocks().get(1).moveBlock(0, 0);
			getBlocks().get(2).moveBlock(1, 1);
			getBlocks().get(3).moveBlock(2, 0);
			this.setOrientation(Orientation.LEFT);
			break;
		case DOWN:
			/*TEST*/
			System.out.println("Rotating DOWN to RIGHT");
			getBlocks().get(0).moveBlock(1, 1);
			getBlocks().get(1).moveBlock(0, 0);
			getBlocks().get(2).moveBlock(-1, -1);
			getBlocks().get(3).moveBlock(-2, 0);
			this.setOrientation(Orientation.RIGHT);
			break;
		case LEFT:
			/*TEST*/
			System.out.println("Rotating LEFT to DOWN");
			getBlocks().get(0).moveBlock(-1, 1);
			getBlocks().get(1).moveBlock(0, 0);
			getBlocks().get(2).moveBlock(1, -1);
			getBlocks().get(3).moveBlock(0, -2);
			this.setOrientation(Orientation.DOWN);
			break;
		case RIGHT:
			/*TEST*/
			System.out.println("Rotating RIGHT to UP");
			getBlocks().get(0).moveBlock(1, -1);
			getBlocks().get(1).moveBlock(0, 0);
			getBlocks().get(2).moveBlock(-1, 1);
			getBlocks().get(3).moveBlock(0, 2);
			this.setOrientation(Orientation.UP);
			break;
		}
	}
	
	@Override
	public Piece getRotateR() {
		LPiece temp = new LPiece(this);
		/*TEST*/
		System.out.println("TEMP: ");
		temp.rotateR();
		return temp;
	}

	@Override
	public Piece getRotateL() {
		LPiece temp = new LPiece(this);
		/*TEST*/
		System.out.println("TEMP: ");
		temp.rotateL();
		return temp;
	}
}
