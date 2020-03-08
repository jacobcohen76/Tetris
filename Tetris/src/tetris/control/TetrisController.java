package tetris.control;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tetris.model.Block;
import tetris.model.PlayField;
import tetris.model.Polyomino;
import tetris.model.Row;
import tetris.model.Tetris;
import tetris.view.TetrisView;

@SuppressWarnings("unused")
public class TetrisController
{
	private Tetris model;
	private TetrisView view;
	private int blockSize;
	private KeyBindings controls;
	private ExecutorService threadExecutor;
	
	public TetrisController(Tetris model, TetrisView view, int blockSize)
	{
		this.model = model;
		this.view = view;
		this.blockSize = blockSize;
		
		threadExecutor = Executors.newFixedThreadPool(3);
		
		controls = new KeyBindings(model);
		controls.bind(KeyEvent.VK_LEFT, new ShiftLeft());
		controls.bind(KeyEvent.VK_RIGHT, new ShiftRight());
		controls.bind(KeyEvent.VK_Z, new RotateCCW());
		controls.bind(KeyEvent.VK_X, new RotateCW());
		controls.bind(KeyEvent.VK_C, new Hold());
		controls.bind(KeyEvent.VK_DOWN, new JumpToBottom());
		view.addKeyListener(controls);
		
		init();
	}
	
	private void init()
	{
		view.setBlockSize(blockSize);
		view.setNumRows(model.getNumRows());
		view.setNumCols(model.getNumCols());
		
		updateView();
		
		view.pack();
		view.setResizable(false);
		view.setVisible(true);
	}
	
	public void updateView()
	{
		view.setNumLines(model.getNumLines());
		view.setScore(model.getScore());
		view.setLevel(model.getLevel());
		view.setNext(model.getNextType());
		view.setHold(model.getHoldingType());
		view.enqueue(getBlocks());
		view.render();
	}
	
	private LinkedList<Block> getBlocks()
	{
		LinkedList<Block> toRender = new LinkedList<Block>();
		
		PlayField field = model.getPlayField();
		for(Row row : field)
			for(Block block : row)
				if(block != null)
					toRender.add(block);
		
		Polyomino falling = model.getFalling(), projection;
		if(falling != null)
		{
			projection = model.getGhostProjection();
			for(Block block : projection)
				if(block != null)
					toRender.add(block);
			
			for(Block block : falling)
				if(block != null)
					toRender.add(block);
		}
		
		return toRender;
	}
	
	public void tick(long millis)
	{
		long start = System.currentTimeMillis();
		model.tick();
		updateView();
		sleep(millis - (System.currentTimeMillis() - start));
	}
	
	public void sleep(long millis)
	{
		if(millis > 0)
			try { Thread.sleep(millis); } catch(Exception ex) {}
	}
	
	public static void main(String args[])
	{
		Tetris model = new Tetris();
		TetrisView view = new TetrisView();
		TetrisController controller = new TetrisController(model, view, 20);
		
		while(model.isGameOver() == false)
			controller.tick(500L);
		
		System.exit(0);
	}
	
	private class ShiftLeft implements Action
	{
		public boolean perform(Tetris model)
		{
			boolean success = model.shiftFallingtLeft();
			updateView();
			return success;
		}
	}
	
	private class ShiftRight implements Action
	{
		public boolean perform(Tetris model)
		{
			boolean success = model.shiftFallingRight();
			updateView();
			return success;
		}
	}
	
	private class RotateCCW implements Action
	{
		public boolean perform(Tetris model)
		{
			boolean success = model.rotateFallingCCW();
			updateView();
			return success;
		}
	}
	
	private class RotateCW implements Action
	{
		public boolean perform(Tetris model)
		{
			boolean success = model.rotateFallingCW();
			updateView();
			return success;
		}
	}
	
	private class Hold implements Action
	{
		public boolean perform(Tetris model)
		{
			boolean success = model.hold();
			updateView();
			return success;
		}
	}
	
	private class JumpToBottom implements Action
	{
		public boolean perform(Tetris model)
		{
			boolean success = model.moveFallingToBottom();
			updateView();
			return success;
		}
	}
}