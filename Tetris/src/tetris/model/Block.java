package tetris.model;
import java.awt.Color;
import java.awt.Graphics;

import tetris.util.Point;
import tetris.util.Vector;

public class Block implements Cloneable
{
	public Point pos;
	public BlockType type;
	
	public Block(Point pos, BlockType type)
	{
		this.pos = pos;
		this.type = type;
	}
	
	public int getRow()
	{
		return (int) Math.round(pos.y);
	}
	
	public int getCol()
	{
		return (int) Math.round(pos.x);
	}
	
	public Block rotateCCWabout(Block pivot, double radians)
	{
		Point newPosition = pos.rotateCCW(pivot.pos, radians);
		return new Block(newPosition, type);
	}
	
	public Block rotateCWabout(Block pivot, double radians)
	{
		return rotateCCWabout(pivot, -radians);
	}
	
	public Block move(Vector direction)
	{
		Point newPosition = Point.add(pos, direction);
		return new Block(newPosition, type);
	}
	
	public String toString()
	{
		return pos + ", " + type;
	}
	
	public Block clone()
	{
		return new Block(new Point(pos.x, pos.y), type);
	}
	
	public void render(Graphics g, int blockSize, int xShift, int yShift)
	{
		int x = getCol() * blockSize - xShift;
		int y = yShift - (getRow() + 1) * blockSize;
		
		g.setColor(BlockType.getColor(type));
		g.fillRect(x, y, blockSize, blockSize);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, blockSize, blockSize);
	}
	
	public void render(Graphics g, int blockSize, int xShift, int yShift, Point pos)
	{
		int x = (int) Math.round(pos.x * blockSize - xShift);
		int y = (int) Math.round(yShift - (pos.y + 1) * blockSize);
		
		g.setColor(BlockType.getColor(type));
		g.fillRect(x, y, blockSize, blockSize);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, blockSize, blockSize);
	}
}
