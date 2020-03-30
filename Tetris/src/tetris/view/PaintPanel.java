package tetris.view;

import java.util.ArrayList;

import tetris.model.Block;
import tetris.model.BlockType;
import tetris.model.Tetromino;

public class PaintPanel
{
	private ArrayList<BlockType[]> field;
	
	public PaintPanel(int numRows, int numCols, int blockSize)
	{
		field = new ArrayList<BlockType[]>();
	}	
	
	public void emplace(Tetromino tetro)
	{
		for(Block block : tetro)
			emplace(block);
	}
	
	private void emplace(Block b)
	{
		field.get(b.getRow())[b.getCol()] = b.type;
	}
}
