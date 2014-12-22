package mxch.geometry.tetromino;

import java.awt.Color;
import java.util.ArrayList;

import mxch.geometry.block.FullBlock;
import mxch.geometry.tetromino.Piece.Orientation;
import mxch.geometry.tetromino.Piece.PieceType;
import mxch.tetris.Board;
import mxch.tetris.Main;

public class OPiece extends Piece {
	private static Color color = Color.YELLOW;

	public OPiece() {
		super(PieceType.O, color);
		/*
		// (4,0) (5,0) (4,1) (5,1)
		super.addBlock(new Block(4, 0, tetris, color));
		super.addBlock(new Block(5, 0, tetris, color));
		super.addBlock(new Block(4, 1, tetris, color));
		super.addBlock(new Block(5, 1, tetris, color));
		*/
		
		// generate above
		super.addBlock(new FullBlock(4, -1, Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(5, -1, Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(4, -2, Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(5, -2, Board.getDx(), Board.getDy(), color));
	}
	
	public OPiece(OPiece p) {
		super(PieceType.O, color);
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
		return;
	}
	
	@Override
	public void rotateL() {
		return;
	}
	
	@Override
	public Piece getRotateR() {
		return this;
	}

	@Override
	public Piece getRotateL() {
		return this;
	}

}
