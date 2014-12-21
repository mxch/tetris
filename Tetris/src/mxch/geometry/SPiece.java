package mxch.geometry;

import java.awt.Color;

import mxch.tetris.Tetris;

public class SPiece extends Tetromino {
	private static Color color = Color.GREEN;

	public SPiece(Tetris tetris) {
		super(Type.S, color, tetris);
		// (4,0) (5,0) (3,1) (4,1)
		super.addBlock(new Block(4, 0, tetris, color));
		super.addBlock(new Block(5, 0, tetris, color));
		super.addBlock(new Block(3, 1, tetris, color));
		super.addBlock(new Block(4, 1, tetris, color));
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
