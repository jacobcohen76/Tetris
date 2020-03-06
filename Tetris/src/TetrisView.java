import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TetrisView extends JFrame
{
	private static final long serialVersionUID = 6411499808530678723L;
	
	private int blockSize;
	private int numRows;
	private int numCols;
	
	private PaintPanel paintPanel;
	
	public TetrisView()
	{		
		setTitle("Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paintPanel = new PaintPanel();
		getContentPane().add(paintPanel);
	}
	
	public void setBlockSize(int blockSize)
	{
		this.blockSize = blockSize;
	}
	
	public void setNumRows(int numRows)
	{
		this.numRows = numRows;
	}
	
	public void setNumCols(int numCols)
	{
		this.numCols = numCols;
	}
	
	public void enqueue(Collection<Block> toRender)
	{
		paintPanel.enqueue(toRender);
	}
	
	public void render()
	{
		paintPanel.repaint();
	}
	
	private class PaintPanel extends JPanel
	{
		private static final long serialVersionUID = -3690563991928805574L;

		private Queue<Block> renderQueue;
		
		public PaintPanel()
		{
			renderQueue = new LinkedList<Block>();
		}
		
		public void enqueue(Collection<Block> toRender)
		{
			renderQueue.addAll(toRender);
		}
		
		public Dimension getPreferredSize()
		{
			int width = numCols * blockSize;
			int height = numRows * blockSize;
			return new Dimension(width, height);
		}
		
		public void paint(Graphics g)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			int xShift = 0;
			int yShift = getHeight();
			
			while(renderQueue.isEmpty() == false)
				renderQueue.poll().render(g, blockSize, xShift, yShift);
		}
	}
}
