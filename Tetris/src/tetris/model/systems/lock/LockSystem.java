package tetris.model.systems.lock;

public abstract class LockSystem
{
	protected int counter;
	protected int delay;
	
	protected LockSystem(int lockDelay)
	{
		counter = 0;
		delay = lockDelay;
	}
	
	public final boolean isLocked()
	{
		return counter >= delay;
	}
	
	public final void advance()
	{
		counter++;
	}
	
	public final void reset()
	{
		counter = 0;
	}
	
	public void lock()
	{
		counter = delay;
	}
	
	public abstract void update();
}
