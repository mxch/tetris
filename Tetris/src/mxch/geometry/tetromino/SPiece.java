package mxch.geometry.tetromino;

import java.awt.Color;
import java.util.ArrayList;

import mxch.geometry.block.FullBlock;
import mxch.tetris.Board;
import mxch.tetris.Main;

public class SPiece extends Piece {
	private static Color color = Color.GREEN;

	public SPiece() {
		super(PieceType.S, color);
		/*
		// (4,0) (5,0) (3,1) (4,1)
		super.addBlock(new Block(4, 0, tetris, color));
		super.addBlock(new Block(5, 0, tetris, color));
		super.addBlock(new Block(3, 1, tetris, color));
		super.addBlock(new Block(4, 1, tetris, color));
		*/
		
		// check above
		super.addBlock(new FullBlock(4, -1, Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(5, -1, Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(3, -2, Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(4, -2, Board.getDx(), Board.getDy(), color));
	}

	@Override
	public void rotateR() {
		// TODO Auto-generated method stub

	}

	@Override
	public void rotateL() {
		// TODO Auto-generated method stub

	}

}