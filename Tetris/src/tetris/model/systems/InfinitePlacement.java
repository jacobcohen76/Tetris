package tetris.model.systems;

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
