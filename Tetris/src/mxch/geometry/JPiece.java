package mxch.geometry;

import java.awt.Color;

import mxch.tetris.Tetris;

public class JPiece extends Tetromino {
	private static Color color = Color.BLUE;

	public JPiece(Tetris tetris) {
		super(Type.J, color, tetris);
		// (3,0) (3,1) (4,1) (5,1)
		super.addBlock(new Block(3, 0, tetris, color));
		super.addBlock(new Block(3, 1, tetris, color));
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
