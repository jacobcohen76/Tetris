package tetris.control;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import tetris.model.Tetris;

public class KeyBindings implements KeyListener
{
	private Tetris model;
	private HashMap<Integer, LinkedList<Action>> bindings;
	private HashMap<Integer, Boolean> pressed;
	
	public KeyBindings(Tetris model)
	{
		this.model = model;
		bindings = new HashMap<Integer, LinkedList<Action>>();
		pressed = new HashMap<Integer, Boolean>();
	}
	
	public void bind(int code, Action action)
	{
		if(bindings.containsKey(code) == false)
		{
			bindings.put(code, new LinkedList<Action>());
			pressed.put(code, false);
		}
		bindings.get(code).add(action);
	}
	
	public boolean isPressed(int key)
	{
		return pressed.get(key);
	}
	
	private void perform(LinkedList<Action> actions)
	{
		Iterator<Action> itr = actions.iterator();
		while(itr.hasNext())
			model.perform(itr.next());
	}

	public void keyTyped(KeyEvent e)
	{
		
	}

	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		if(bindings.containsKey(code) && isPressed(code) == false)
			perform(bindings.get(code));
		if(pressed.containsKey(code))
			pressed.put(code, true);
	}

	public void keyReleased(KeyEvent e)
	{
		pressed.put(e.getKeyCode(), false);
	}
}
