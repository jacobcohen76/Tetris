package tetris.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JPanel;

import tetris.model.Block;
import tetris.model.BlockType;
import tetris.model.Tetromino;
import tetris.util.Vector;

public class TetrominoPanel extends JPanel
{
	private static final long serialVersionUID;
	private static final int NUM_ROWS;
	private static final int NUM_COLS;
	private static final int DEFAULT_BLOCK_SIZE;
	private static final int PADDING;
	private static final HashMap<BlockType, Vector> CENTER_VECTOR_MAP;
	
	
	static
	{
		serialVersionUID = -2686986774238238207L;
		NUM_ROWS = 4;
		NUM_COLS = 4;
		DEFAULT_BLOCK_SIZE = 11;
		PADDING = 0;
		
		CENTER_VECTOR_MAP = new HashMap<BlockType, Vector>();
		CENTER_VECTOR_MAP.put(BlockType.I, new Vector(2, 1.5));
		CENTER_VECTOR_MAP.put(BlockType.J, new Vector(1.5, 2));
		CENTER_VECTOR_MAP.put(BlockType.L, new Vector(1.5, 2));
		CENTER_VECTOR_MAP.put(BlockType.O, new Vector(2, 1));
		CENTER_VECTOR_MAP.put(BlockType.S, new Vector(1.5, 2));
		CENTER_VECTOR_MAP.put(BlockType.T, new Vector(1.5, 2));
		CENTER_VECTOR_MAP.put(BlockType.Z, new Vector(1.5, 2));
	}
	
	private Tetromino toDisplay;
	private int blockSize;
	
	public TetrominoPanel(Tetromino toDisplay, int blockSize)
	{
		this.toDisplay = center(toDisplay);
		this.blockSize = blockSize;
	}
	
	public TetrominoPanel(int blockSize)
	{
		this(null, blockSize);
	}
	
	public TetrominoPanel()
	{
		this(null, DEFAULT_BLOCK_SIZE);
	}
	
	public void setTetromino(Tetromino toDisplay)
	{
		this.toDisplay = center(toDisplay);
	}
	
	public Dimension getPreferredSize()
	{
		int width = NUM_ROWS * blockSize + 2 * PADDING;
		int height = NUM_COLS * blockSize + 2 * PADDING;
		return new Dimension(width, height);
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		int xShift = - PADDING;
		int yShift = getHeight() - PADDING;
		
		if(toDisplay != null)
			for(Block block : toDisplay)
				block.render(g, blockSize, xShift, yShift, block.pos);
	}
	
	private Tetromino center(Tetromino toCenter)
	{
		if(toCenter == null)
			return toCenter;
		else
			return toCenter.move(CENTER_VECTOR_MAP.get(toCenter.getType()));
	}
	
}
