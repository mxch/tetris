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
	
	public IPiece(IPiece p, int dx1, int dy1, int dx2, int dy2, int dx3, int dy3, int dx4, int dy4) {
		super(PieceType.I, color);
		FullBlock b1 = p.getBlocks().get(0);
		FullBlock b2 = p.getBlocks().get(1);
		FullBlock b3 = p.getBlocks().get(2);
		FullBlock b4 = p.getBlocks().get(3);
		super.addBlock(new FullBlock(b1.getIntX() + dx1, b1.getIntY() + dy1, Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(b2.getIntX() + dx2, b2.getIntY() + dy2, Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(b3.getIntX() + dx3, b3.getIntY() + dy3, Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(b4.getIntX() + dx4, b4.getIntY() + dy4, Board.getDx(), Board.getDy(), color));
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
	public Piece getRotateR() {
		int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0, dx3 = 0, dy3 = 0, dx4 = 0, dy4 = 0;
		Orientation o = this.getOrientation();
		switch (o) {
		case UP:
			break;
		case DOWN:
			break;
		case LEFT:
			break;
		case RIGHT:
			break;
		}
		return new IPiece(this, dx1, dy1, dx2, dy2, dx3, dy3, dx4, dy4);
	}

	@Override
	public Piece getRotateL() {
		int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0, dx3 = 0, dy3 = 0, dx4 = 0, dy4 = 0;
		Orientation o = this.getOrientation();
		switch (o) {
		case UP:
			break;
		case DOWN:
			break;
		case LEFT:
			break;
		case RIGHT:
			break;
		}
		return new IPiece(this, dx1, dy1, dx2, dy2, dx3, dy3, dx4, dy4);
	}

}
