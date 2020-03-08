package tetris.model.systems.lock;

public class InfinitePlacement extends LockSystem
{
	protected InfinitePlacement(int lockDelay)
	{
		super(lockDelay);
	}

	public void update()
	{
		reset();
	}
}
