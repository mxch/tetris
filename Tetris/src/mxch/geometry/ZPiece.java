package mxch.geometry;

import java.awt.Color;

import mxch.tetris.Tetris;

public class ZPiece extends Tetromino {
	private static Color color = Color.RED;

	public ZPiece(Tetris tetris) {
		super(Type.Z, color, tetris);
		// (3,0) (4,0) (4,1) (5,1)
		super.addBlock(new Block(3, 0, tetris, color));
		super.addBlock(new Block(4, 0, tetris, color));
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
