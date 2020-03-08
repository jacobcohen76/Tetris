package tetris.model;
import java.util.LinkedList;

import tetris.control.Action;
import tetris.model.systems.levelup.FixedLevelUp;
import tetris.model.systems.levelup.LevelUpSystem;
import tetris.model.systems.lock.LockSystem;
import tetris.model.systems.lock.StepReset;
import tetris.util.Bag;
import tetris.util.Vector;

public class Tetris
{
	private static final Vector GRAVITY;
	private static final int DEFAULT_NUM_ROWS;
	private static final int DEFAULT_NUM_COLS;
	private static final int DEFAULT_NUM_PADDED_ROWS;
	private static final int DEFAULT_NUM_PREVIEW_PIECES;
	private static final LinkedList<BlockType> PIECE_LIST;
	private static final LevelUpSystem DEFAULT_LEVEL_UP_SYSTEM;
	private static final LockSystem DEFAULT_LOCK_SYSTEM;
	
	static
	{
		GRAVITY = new Vector(0, -1);
		DEFAULT_NUM_ROWS = 20;
		DEFAULT_NUM_COLS = 10;
		DEFAULT_NUM_PADDED_ROWS = 3;
		DEFAULT_NUM_PREVIEW_PIECES = 1;
		
		PIECE_LIST = new LinkedList<BlockType>();
		PIECE_LIST.add(BlockType.I);
		PIECE_LIST.add(BlockType.J);
		PIECE_LIST.add(BlockType.L);
		PIECE_LIST.add(BlockType.O);
		PIECE_LIST.add(BlockType.S);
		PIECE_LIST.add(BlockType.T);
		PIECE_LIST.add(BlockType.Z);
		
		DEFAULT_LEVEL_UP_SYSTEM = new FixedLevelUp(10);
		DEFAULT_LOCK_SYSTEM = new StepReset(1);
	}
	
	private PlayField field;
	
	private Bag<BlockType> pieceBag;
	private LinkedList<BlockType> previewList;
	
	private Tetromino falling;
	private BlockType holding;
	
	private ScoringSystem scoring;
	
	private int numLines;
	private int level;
	
	private boolean isGameOver;
	private boolean canHold;
	
	private LevelUpSystem levelUpSystem;
	private LockSystem lockSystem;
	
	public Tetris(int numRows, int numCols, int numPaddedRows, int numPreviewPieces, LevelUpSystem levelUpSystem, LockSystem lockSystem)
	{
		field = new PlayField(numRows, numCols, numPaddedRows);
		
		pieceBag = new Bag<BlockType>();
		previewList = getNewPreviewList(numPreviewPieces);
		
		falling = null;
		holding = null;
		
		scoring = new ScoringSystem();
		
		numLines = 0;
		level = 0;
		
		isGameOver = false;
		canHold = false;
		
		this.levelUpSystem = levelUpSystem;
		this.lockSystem = lockSystem;
	}
	
	public Tetris(int numRows, int numCols)
	{
		this(numRows, numCols,
				DEFAULT_NUM_PADDED_ROWS,
				DEFAULT_NUM_PREVIEW_PIECES,
				DEFAULT_LEVEL_UP_SYSTEM,
				DEFAULT_LOCK_SYSTEM);
	}
	
	public Tetris()
	{
		this(DEFAULT_NUM_ROWS, DEFAULT_NUM_COLS);
	}
	
	public void loadNextPiece()
	{
		setFalling(getNextPiece());
		canHold = true;
	}
	
	public boolean hold()
	{
		if(canHold == true)
		{
			BlockType temp = holding;
			holding = falling.getPivot().type;
			if(temp != null)
				setFalling(Tetromino.getPiece(temp));
			else
				falling = null;
			canHold = false;
			return true;
		}
		return false;
	}
	
	private void setFalling(Tetromino newFalling)
	{
		falling = center(newFalling);
		isGameOver |= field.collidesWith(falling);
	}
	
	public boolean isGameOver()
	{
		return isGameOver;
	}
	
	public String toString()
	{
		String str = "";
		str += field;
		return str;
	}
	
	public int getNumRows()
	{
		return field.getNumRows();
	}
	
	public int getNumCols()
	{
		return field.getNumCols();
	}
	
	private Tetromino center(Tetromino tetro)
	{
		int i = getNumCols() / 2;
		int j = getNumRows() + 1;
		Vector center = new Vector(i, j);
		return tetro.move(center);
	}
	
	public void tick()
	{
		if(falling == null)
		{
			loadNextPiece();
		}
		else
		{
			if(moveFalling(GRAVITY) == true)
				lockSystem.reset();
			else
				lockSystem.advance();
			
			if(lockSystem.isLocked())
			{
				lockFalling();
				lockSystem.reset();
			}
		}
		removeFilledRows();
	}
	
	public void perform(Action action)
	{
		if(action.perform(this) == true)
			lockSystem.update();
	}
	
	public void removeFilledRows()
	{
		int numFilledRows = field.getNumFilledRows();
		scoring.score(numFilledRows, level);
		numLines += numFilledRows;
		field.removeFilledRows();
		updateLevel();
	}
	
	private void updateLevel()
	{
		level = levelUpSystem.getLevel(numLines, level);
	}
	
	public int getNumLines()
	{
		return numLines;
	}
	
	public void lockFalling()
	{
		canHold = false;
		emplace(falling);
		falling = null;
	}
	
	private Tetromino getNextPiece()
	{
		BlockType type = previewList.poll();
		previewList.add(getRandomPiece());
		return Tetromino.getPiece(type);
	}
	
	private void refillBag(int n)
	{
		for(int i = 0; i < n; i++)
		{
			pieceBag.addAll(PIECE_LIST);
			pieceBag.shuffle();
		}
	}
	
	private BlockType getRandomPiece()
	{
		if(pieceBag.size() < 35)
			refillBag(5);
		return pieceBag.pull();
	}
	
	private boolean emplace(Polyomino poly)
	{
		return field.emplace(poly);
	}
	
	public boolean rotateFallingCCW()
	{
		if(falling != null)
		{
			Tetromino rotated = falling.rotateCCW();
			falling = field.collidesWith(rotated) ? falling : rotated;
			return rotated == falling;
		}
		else
			return false;
	}
	
	public boolean rotateFallingCW()
	{
		if(falling != null)
		{
			Tetromino rotated = falling.rotateCW();
			falling = field.collidesWith(rotated) ? falling : rotated;
			return rotated == falling;
		}
		else
			return false;
	}
	
	public boolean applyGravity()
	{
		return moveFalling(GRAVITY);
	}
	
	public boolean shiftFallingtLeft()
	{
		return moveFalling(Vector.WEST);
	}
	
	public boolean shiftFallingRight()
	{
		return moveFalling(Vector.EAST);
	}
	
	private boolean moveFalling(Vector amount)
	{
		if(falling != null)
		{
			Tetromino moved = falling.move(amount);
			falling = field.collidesWith(moved) ? falling : moved;
			return falling == moved;
		}
		else
			return false;
	}
	
	private Tetromino getProjection(Tetromino tetro)
	{
		Tetromino projection = tetro.move(Vector.ZERO);
		for(Block b : projection)
			b.type = BlockType.projection;
		return projection;
	}
	
	private Tetromino getFallingBottom()
	{
		return getProjection(falling, GRAVITY);
	}
	
	public boolean moveFallingToBottom()
	{
		if(falling != null)
		{
			Tetromino temp = falling;
			falling = getFallingBottom();
			lockSystem.lock();
			return temp != falling;
		}
		return false;
	}
	
	private Tetromino getProjection(Tetromino tetro, Vector dir)
	{
		Tetromino projection;
		do {
			projection = tetro.move(dir);
			tetro = field.collidesWith(projection) ? tetro : projection;
		} while(tetro == projection);
		return tetro;
	}
	
	public Tetromino getFallingProjection()
	{
		return getProjection(getProjection(falling), GRAVITY);
	}
	
	private LinkedList<BlockType> getNewPreviewList(int numPreviewPieces)
	{
		LinkedList<BlockType> previewList = new LinkedList<BlockType>();
		for(int i = 0; i < numPreviewPieces; i++)
			previewList.add(getRandomPiece());
		return previewList;
	}
	
	public PlayField getField()
	{
		return field;
	}
	
	public Polyomino getFalling()
	{
		return falling;
	}
	
	public long getScore()
	{
		return scoring.getCurrentScore();
	}
	
	public int getLevel()
	{
		return level;
	}
}
