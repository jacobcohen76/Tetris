import java.awt.Graphics;
import java.util.Iterator;

public class Polyomino implements Iterable<Block>
{
	private static final int PIVOT = 0;
	
	protected Block[] blocks;
	protected int n;
	
	protected Polyomino(Block[] blocks, int n)
	{
		this.blocks = blocks;
		this.n = n;
		
		if(blocks.length != n)
			throw new Error("num blocks unequal to size n of this polyomino");
	}
	
	public Block getPivot()
	{
		return blocks[PIVOT];
	}
	
	private class PolyominoIterator implements Iterator<Block>
	{
		private int i;
		
		public PolyominoIterator()
		{
			i = 0;
		}
		
		public boolean hasNext()
		{
			return i < n;
		}

		public Block next()
		{
			return blocks[i++];
		}
	}
	
	public Iterator<Block> iterator()
	{
		return new PolyominoIterator();
	}
	
	public Polyomino move(Vector direction)
	{
		Block[] transformed = new Block[n];
		for(int i = 0; i < n; i++)
			transformed[i] = blocks[i].move(direction);
		return new Polyomino(transformed, n);
	}
	
	private Polyomino rotateCCWabout(Block pivot, double radians)
	{
		Block[] transformed = new Block[n];
		for(int i = 0; i < n; i++)
			transformed[i] = blocks[i].rotateCCWabout(pivot, radians);
		return new Polyomino(transformed, n);
	}
	
	public Polyomino rotateCCW(double radians)
	{
		return rotateCCWabout(getPivot(), radians);
	}
	
	public Polyomino rotateCW(double radians)
	{
		return rotateCCW(-radians);
	}
	
	public void render(Graphics g, int blockSize, int xShift, int yShift)
	{
		for(Block block : blocks)
			if(block != null)
				block.render(g, blockSize, xShift, yShift);
	}
}
