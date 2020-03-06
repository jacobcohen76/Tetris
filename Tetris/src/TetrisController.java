import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("unused")
public class TetrisController
{
	private Tetris model;
	private TetrisView view;
	private int blockSize;
	private Controls controls;
	private ExecutorService threadExecutor;
	
	public TetrisController(Tetris model, TetrisView view)
	{
		this.model = model;
		this.view = view;
		
		threadExecutor = Executors.newFixedThreadPool(3);
		
		controls = new Controls();
		controls.bind(KeyEvent.VK_LEFT, new ShiftLeft());
		controls.bind(KeyEvent.VK_RIGHT, new ShiftRight());
		controls.bind(KeyEvent.VK_Z, new RotateCCW());
		controls.bind(KeyEvent.VK_X, new RotateCW());
		view.addKeyListener(controls);
		
		init();
	}
	
	private void init()
	{
		blockSize = 30;
		updateView();
		view.pack();
		view.setResizable(false);
		view.setVisible(true);
	}
	
	public void updateView()
	{
		view.setBlockSize(blockSize);
		view.setNumRows(model.getNumRows());
		view.setNumCols(model.getNumCols());		
		view.enqueue(getBlocks());
		view.render();
	}
	
	private LinkedList<Block> getBlocks()
	{
		LinkedList<Block> toRender = new LinkedList<Block>();
		
		PlayField field = model.getField();
		for(Row row : field)
			for(Block block : row)
				if(block != null)
					toRender.add(block);
		
		Polyomino falling = model.getFalling(), projection;
		if(falling != null)
		{
			projection = model.getFallingProjection();
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
	
	private void sleep(long millis)
	{
		if(millis > 0)
			try { Thread.sleep(millis); } catch(Exception ex) {}
	}
	
	public static void main(String args[])
	{
		Tetris model = new Tetris();
		TetrisView view = new TetrisView();
		TetrisController controller = new TetrisController(model, view);
		
		while(true)
			controller.tick(150L);
	}

	public interface Action
	{
		public void perform();
	}
	
	public class ShiftLeft implements Action
	{
		public void perform()
		{
			model.shifFallingtLeft();
			updateView();
		}
	}
	
	public class ShiftRight implements Action
	{
		public void perform()
		{
			model.shiftFallingRight();
			updateView();
		}
	}
	
	public class RotateCCW implements Action
	{
		public void perform()
		{
			model.rotateFallingCCW();
			updateView();
		}
	}
	
	public class RotateCW implements Action
	{
		public void perform()
		{
			model.rotateFallingCW();
			updateView();
		}
	}
	
	public class Hold implements Action
	{
		public void perform()
		{
			
		}
	}
}

class Controls implements KeyListener
{
	private HashMap<Integer, TetrisController.Action> keyBindings;
	
	public Controls()
	{
		keyBindings = new HashMap<Integer, TetrisController.Action>();
	}
	
	public void bind(Integer keyCode, TetrisController.Action action)
	{
		keyBindings.put(keyCode, action);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(keyBindings.containsKey(e.getKeyCode()))
			keyBindings.get(e.getKeyCode()).perform();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}