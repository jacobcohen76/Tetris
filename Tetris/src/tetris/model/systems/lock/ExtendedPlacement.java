package tetris.model.systems.lock;

public class ExtendedPlacement extends LockSystem
{
	private int resetCounter;
	private int maxResets;
	
	protected ExtendedPlacement(int lockDelay, int maxResets)
	{
		super(lockDelay);
		this.maxResets = maxResets;
		resetCounter = 0;
	}

	public void update()
	{
		if(resetCounter < maxResets)
		{
			resetCounter++;
			reset();
		}
	}
	
	public void lock()
	{
		resetCounter = 0;
		super.lock();
	}
}
