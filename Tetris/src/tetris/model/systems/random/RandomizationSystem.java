package tetris.model.systems.random;

import java.util.Random;

import tetris.model.BlockType;

public abstract class RandomizationSystem
{
	protected Random rand;
	
	public RandomizationSystem()
	{
		rand = new Random();
	}
	
	public RandomizationSystem(long seed)
	{
		rand = new Random(seed);
	}
	
	public abstract BlockType getRandomType();
}
