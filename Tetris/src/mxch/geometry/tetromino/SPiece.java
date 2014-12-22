package mxch.geometry.tetromino;

import java.awt.Color;
import java.util.ArrayList;

import mxch.geometry.block.FullBlock;
import mxch.geometry.tetromino.Piece.Orientation;
import mxch.geometry.tetromino.Piece.PieceType;
import mxch.tetris.Board;
import mxch.tetris.Main;

public class SPiece extends Piece {
	private static Color color = Color.GREEN;

	public SPiece() {
		super(PieceType.S, color);	
		// check above
		super.addBlock(new FullBlock(3, -1, Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(4, -1, Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(4, -2, Board.getDx(), Board.getDy(), color));
		super.addBlock(new FullBlock(5, -2, Board.getDx(), Board.getDy(), color));
	}
	
	public SPiece(SPiece p) {
		super(PieceType.S, color);
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
			//System.out.println("Rotating UP to RIGHT");
			getBlocks().get(0).moveBlock(0, 2);
			getBlocks().get(1).moveBlock(1, 1);
			getBlocks().get(2).moveBlock(0, 0);
			getBlocks().get(3).moveBlock(1, -1);
			this.setOrientation(Orientation.RIGHT);
			break;
		case DOWN:
			/*TEST*/
			//System.out.println("Rotating DOWN to LEFT");
			getBlocks().get(0).moveBlock(0, -2);
			getBlocks().get(1).moveBlock(-1, -1);
			getBlocks().get(2).moveBlock(0, 0);
			getBlocks().get(3).moveBlock(-1, 1);
			this.setOrientation(Orientation.LEFT);
			break;
		case LEFT:
			/*TEST*/
			//System.out.println("Rotating LEFT to UP");
			getBlocks().get(0).moveBlock(2, 0);
			getBlocks().get(1).moveBlock(1, -1);
			getBlocks().get(2).moveBlock(0, 0);
			getBlocks().get(3).moveBlock(-1, -1);
			this.setOrientation(Orientation.UP);
			break;
		case RIGHT:
			/*TEST*/
			//System.out.println("Rotating RIGHT to DOWN");
			getBlocks().get(0).moveBlock(-2, 0);
			getBlocks().get(1).moveBlock(-1, 1);
			getBlocks().get(2).moveBlock(0, 0);
			getBlocks().get(3).moveBlock(1, 1);
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
			//System.out.println("Rotating UP to LEFT");
			getBlocks().get(0).moveBlock(-2, 0);
			getBlocks().get(1).moveBlock(-1, 1);
			getBlocks().get(2).moveBlock(0, 0);
			getBlocks().get(3).moveBlock(1, 1);
			this.setOrientation(Orientation.LEFT);
			break;
		case DOWN:
			/*TEST*/
			//System.out.println("Rotating DOWN to RIGHT");
			getBlocks().get(0).moveBlock(2, 0);
			getBlocks().get(1).moveBlock(1, -1);
			getBlocks().get(2).moveBlock(0, 0);
			getBlocks().get(3).moveBlock(-1, -1	);
			this.setOrientation(Orientation.RIGHT);
			break;
		case LEFT:
			/*TEST*/
			//System.out.println("Rotating LEFT to DOWN");
			getBlocks().get(0).moveBlock(0, 2);
			getBlocks().get(1).moveBlock(1, 1);
			getBlocks().get(2).moveBlock(0, 0);
			getBlocks().get(3).moveBlock(1, -1);
			this.setOrientation(Orientation.DOWN);
			break;
		case RIGHT:
			/*TEST*/
			//System.out.println("Rotating RIGHT to UP");
			getBlocks().get(0).moveBlock(0, -2);
			getBlocks().get(1).moveBlock(-1, -1);
			getBlocks().get(2).moveBlock(0, 0);
			getBlocks().get(3).moveBlock(-1, 1);
			this.setOrientation(Orientation.UP);
			break;
		}
	}
	
	@Override
	public Piece getRotateR() {
		SPiece temp = new SPiece(this);
		/*TEST*/
		//System.out.println("TEMP: ");
		temp.rotateR();
		return temp;
	}

	@Override
	public Piece getRotateL() {
		SPiece temp = new SPiece(this);
		/*TEST*/
		//System.out.println("TEMP: ");
		temp.rotateL();
		return temp;
	}
	
	@Override
	public Piece getGhostPiece() {
		Piece temp = new SPiece(this);
		temp.setColor(Color.white);
		return temp;
	}

}
