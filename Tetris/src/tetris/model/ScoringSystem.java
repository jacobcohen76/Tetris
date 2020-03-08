package tetris.model;

public class ScoringSystem
{
	private static long highScore;
	
	private static final long[] LEVEL_COEFFICIENT = { 0L, 40L, 100L, 300L, 1200L };
	private long currentScore;
	
	static
	{
		highScore = 10000L;
	}
	
	public ScoringSystem()
	{
		currentScore = 0L;
	}
	
	public void score(int numLines, int level)
	{
		if(numLines < 0 || 4 < numLines)
		{
			throw new Error("Error, you filled a wierd number of lines, (numLines = " + numLines + "), "
					+ "not sure how you managed to that one mate, but the number of captured lines, (numLines), "
					+ "should be within the range of [0, 4].");
		}
		
		currentScore += LEVEL_COEFFICIENT[numLines] * (level + 1L);
		setHighScore(currentScore);
	}
	
	public long getCurrentScore()
	{
		return currentScore;
	}
	
	public static long getHighScore()
	{
		return highScore;
	}
	
	public static void setHighScore(long newHighScore)
	{
		if(highScore < newHighScore)
			highScore = newHighScore;
	}
	
	
}
