package tetris.model.systems.levelup;

public class VariableLevelUp implements LevelUpSystem
{
	private int n;
	
	public VariableLevelUp(int n)
	{
		this.n = n;
	}

	public int getLevel(int numLines, int level)
	{
		return numLines / (n * (level + 1));
	}
}
