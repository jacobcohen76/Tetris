package tetris.model.systems.levelup;

public class FixedLevelUp implements LevelUpSystem
{
	private int n;
	
	public FixedLevelUp(int n)
	{
		this.n = n;
	}
	
	public int getLevel(int numLines, int level)
	{
		return numLines / n;
	}
}
