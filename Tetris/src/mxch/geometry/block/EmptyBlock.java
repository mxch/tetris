package mxch.geometry.block;

import java.awt.Color;

public class EmptyBlock extends Block {
	private static EmptyBlock instance = null;
	
	protected EmptyBlock() {
		super(BlockType.EMPTY, 0, 0, 0, 0);
	}
	
	public static EmptyBlock getInstance() {
		if (instance == null) {
			instance = new EmptyBlock();
		}
		return instance;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getXcoord() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getYcoord() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIntX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIntY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDx() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void moveDownOne() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveRightOne() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveLeftOne() {
		// TODO Auto-generated method stub
		
	}
}