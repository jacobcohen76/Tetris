package tetris.model.systems;

public class FixedLevelUpSystem implements LevelUpSystem
{
	private int n;
	
	public FixedLevelUpSystem(int n)
	{
		this.n = n;
	}
	
	public int getLevel(int numLines, int level)
	{
		return numLines / n;
	}
}
