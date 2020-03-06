import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class PlayField implements Iterable<Row>
{
	private ArrayList<Row> rowList;
	private ArrayList<Row> filledRows;
	
	private int numRows;
	private int numCols;
	private int rowPadding;
	
	public PlayField(int numRows, int numCols, int rowPadding)
	{
		this.numRows = numRows;
		this.numCols = numCols;
		this.rowPadding = rowPadding;
		rowList = new ArrayList<Row>(numRows + rowPadding);
		for(int i = 0; i < (numRows + rowPadding); i++)
			rowList.add(new Row(i, numCols));
		filledRows = new ArrayList<Row>();
	}
	
	public int getNumRows()
	{
		return numRows;
	}
	
	public int getNumCols()
	{
		return numCols;
	}
	
	public int getNumPaddedRows()
	{
		return rowPadding;
	}
	
	public boolean isEmpty(int row, int col)
	{
		return isWithinBounds(row, col) && rowList.get(row).isEmpty(col);
	}
	
	public boolean collidesWith(Block b)
	{
		return isEmpty(b.getRow(), b.getCol()) == false;
	}
	
	public boolean collidesWith(Polyomino poly)
	{
		boolean collision = false;
		for(Block b : poly)
			collision |= collidesWith(b);
		return collision;
	}
	
	public boolean emplace(Polyomino poly)
	{
		boolean isEmplaced = true;
		for(Block b : poly)
			isEmplaced &= emplace(b);
		return isEmplaced;
	}
	
	public boolean emplace(Block b)
	{
		return emplace(b.getRow(), b.getCol(), b);
	}
	
	private boolean emplace(int row, int col, Block b)
	{
		boolean isEmplaced = isWithinBounds(row, col);
		if(isEmplaced == true)
		{
			Row emplacedIn = rowList.get(row);
			isEmplaced &= emplacedIn.emplace(col, b);
			if(isEmplaced == true && emplacedIn.isFull())
				filledRows.add(emplacedIn);
		}
		return isEmplaced;
	}
	
	public boolean isWithinBounds(int row, int col)
	{
		boolean withinBounds = true;
		withinBounds &= 0 <= row && row < numRows;
		withinBounds &= withinBounds && rowList.get(row).isWithinBounds(col);
		return withinBounds;
	}
	
	public void removeFilledRows()
	{
		Collections.sort(filledRows);
		for(Row filledRow: filledRows)
			remove(filledRow);
		filledRows.clear();
	}
	
	public ArrayList<Row> getFilledRows()
	{
		return filledRows;
	}
	
	private void remove(Row toRemove)
	{
		for(int i = numRows - 1; i > toRemove.getRowNum(); i--)
			rowList.get(i).shiftRowNum(-1);
		rowList.remove(toRemove.getRowNum());
		rowList.add(new Row(rowList.size(), getNumCols()));
	}
	
	public String toString(Polyomino poly)
	{
		char[][] array = new char[rowList.size() - rowPadding][getNumCols()];
		
		for(int i = array.length - 1; i >= 0; i--)
			for(int j = 0; j < array[i].length; j++)
				array[i][j] = isEmpty(i, j) ? '_' : '#';
		
		if(poly != null)
		{
			for(Block b : poly)
				array[b.getRow()][b.getCol()] = 'F';
			array[poly.getPivot().getRow()][poly.getPivot().getCol()] = 'P';
		}
		
		String str = "";
		for(int i = array.length - 1; i >= 0; i--)
		{
			for(char b : array[i])
				str += b;
			str += "\n";
		}
		
		return str;
	}
	
	public String toString()
	{		
		String str = "";
		for(Row row : this)
			str += row + "\n";
		if(str.length() > 0)
			str = str.substring(0, str.length() - 1);
		return str;
	}
	
	private class RowIterator implements Iterator<Row>
	{
		int i;
		
		public RowIterator()
		{
			i = rowList.size(); 
		}
		
		public boolean hasNext()
		{
			return i > 0;
		}

		public Row next()
		{
			return rowList.get(--i);
		}		
	}

	public Iterator<Row> iterator()
	{
		return new RowIterator();
	}
	
	public void render(Graphics g, int blockSize, int xShift, int yShift)
	{
		for(Row row : this)
			row.render(g, blockSize, xShift, yShift);
	}
}
