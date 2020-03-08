package tetris.model.systems;

public class VariableLevelUpSystem implements LevelUpSystem
{
	private int n;
	
	public VariableLevelUpSystem(int n)
	{
		this.n = n;
	}

	public int getLevel(int numLines, int level)
	{
		return numLines / (n * (level + 1));
	}
}
