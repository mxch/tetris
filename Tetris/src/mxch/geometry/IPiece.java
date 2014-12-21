package mxch.geometry;

import java.awt.Color;

import mxch.tetris.Tetris;

public class IPiece extends Tetromino {
	private static Color color = Color.CYAN;

	public IPiece(Tetris tetris) {
		super(Type.I, color, tetris);
		// (3,0) (4,0) (5,0) (6,0)
		super.addBlock(new Block(3, 0, tetris, color));
		super.addBlock(new Block(4, 0, tetris, color));
		super.addBlock(new Block(5, 0, tetris, color));
		super.addBlock(new Block(6, 0, tetris, color));
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
