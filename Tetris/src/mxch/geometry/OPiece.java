package mxch.geometry;

import java.awt.Color;

import mxch.tetris.Tetris;

public class OPiece extends Tetromino {
	private static Color color = Color.YELLOW;

	public OPiece(Tetris tetris) {
		super(Type.O, color, tetris);
		// (4,0) (5,0) (4,1) (5,1)
		super.addBlock(new Block(4, 0, tetris, color));
		super.addBlock(new Block(5, 0, tetris, color));
		super.addBlock(new Block(4, 1, tetris, color));
		super.addBlock(new Block(5, 1, tetris, color));
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
