package tetris.control;
import tetris.model.Tetris;

public interface Action
{
	public boolean perform(Tetris model);
}
