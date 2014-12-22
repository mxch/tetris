package mxch.geometry;

import java.awt.Color;
import java.util.ArrayList;

import mxch.tetris.Tetris;

public class TPiece extends Tetromino {
	private static Color color = Color.MAGENTA;

	public TPiece(Tetris tetris) {
		super(Type.T, color, tetris);
		/*
		// (4,0) (3,1) (4,1) (5,1)
		super.addBlock(new Block(4, 0, tetris, color));
		super.addBlock(new Block(3, 1, tetris, color));
		super.addBlock(new Block(4, 1, tetris, color));
		super.addBlock(new Block(5, 1, tetris, color));
		*/
		
		// generate above 
		super.addBlock(new Block(4, -1, tetris, color));
		super.addBlock(new Block(3, -2, tetris, color));
		super.addBlock(new Block(4, -2, tetris, color));
		super.addBlock(new Block(5, -2, tetris, color));
	}

	@Override
	public void rotateR() {
		// TODO Auto-generated method stub

	}

	@Override
	public void rotateL() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canRotateR(ArrayList<Block> boardBlocks) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canRotateL(ArrayList<Block> boardBlocks) {
		// TODO Auto-generated method stub
		return false;
	}

}
