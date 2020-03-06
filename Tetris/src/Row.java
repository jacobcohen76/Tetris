import java.awt.Graphics;
import java.util.Iterator;

public class Row implements Iterable<Block>, Comparable<Row>
{
	private Block[] blocks;
	private int rowNum;
	private int numPlaced;
	
	public Row(int rowNum, int size)
	{
		this.rowNum = rowNum;
		blocks = new Block[size];
		numPlaced = 0;
	}
	
	public boolean isWithinBounds(int col)
	{
		boolean withinBounds = true;
		withinBounds &= 0 <= col;
		withinBounds &= col < blocks.length;
		return withinBounds;
	}
	
	public Block get(int col)
	{
		return isWithinBounds(col) ? blocks[col] : null;
	}
	
	public int getRowNum()
	{
		return rowNum;
	}
	
	public int size()
	{
		return blocks.length;
	}
	
	public void shiftRowNum(int amount)
	{
		rowNum += amount;
		for(Block block : blocks)
			if(block != null)
				block.pos.y += amount;
	}
	
	public boolean emplace(int col, Block block)
	{
		boolean isEmplaced = isEmpty(col);
		if(isEmplaced == true)
		{
			blocks[col] = block;
			block.pos.x = col;
			block.pos.y = rowNum;
			numPlaced++;
		}
		return isEmplaced;
	}
	
	public boolean isEmpty(int col)
	{
		return isWithinBounds(col) && get(col) == null;
	}
	
	public boolean isFull()
	{
		return numPlaced == blocks.length;
	}
	
	private class RowIterator implements Iterator<Block>
	{
		private int col;
		
		public RowIterator()
		{
			col = -1;
		}
		
		public boolean hasNext()
		{
			return (col < blocks.length);
		}

		public Block next()
		{
			return get(++col);
		}
	}
	
	public Iterator<Block> iterator()
	{
		return new RowIterator();
	}
	
	public String toString()
	{
		String str = "";
		str += String.format("%1$2s", rowNum) + ": ";
		for(Block block : this)
			str += block == null ? "_" : "#";
		str += " " + String.format("%1$2s", numPlaced) + " / " + String.format("%1$2s", size()); 
		return str;
	}

	public int compareTo(Row o)
	{
		if(rowNum < o.rowNum)
			return -1;
		else if(rowNum == o.rowNum)
			return 0;
		else
			return +1;
	}
	
	public void render(Graphics g, int blockSize, int xShift, int yShift)
	{
		for(Block block : blocks)
			if(block != null)
				block.render(g, blockSize, xShift, yShift);
	}
}
