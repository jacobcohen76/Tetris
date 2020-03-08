package tetris.model.systems.random;

import tetris.model.BlockType;

public class SinglePieceRNG extends RandomizationSystem
{
	public SinglePieceRNG()
	{
		super();
	}
	
	public SinglePieceRNG(long seed)
	{
		super(seed);
	}
	
	public BlockType getRandomType()
	{
		switch(rand.nextInt(7))
		{
		case 0:
			return BlockType.I;
		case 1:
			return BlockType.J;
		case 2:
			return BlockType.L;
		case 3:
			return BlockType.O;
		case 4:
			return BlockType.S;
		case 5:
			return BlockType.T;
		case 6:
			return BlockType.Z;
		default:
			return null;
		}
	}
}
