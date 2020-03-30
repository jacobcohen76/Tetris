package tetris.model;
import java.util.LinkedList;

import tetris.control.Action;
import tetris.model.systems.levelup.FixedLevelUp;
import tetris.model.systems.levelup.LevelUpSystem;
import tetris.model.systems.lock.LockSystem;
import tetris.model.systems.lock.StepReset;
import tetris.model.systems.random.RandomizationSystem;
import tetris.model.systems.random.SinglePieceRNG;
import tetris.util.Vector;

public class Tetris
{
	private static final Vector GRAVITY;
	private static final int DEFAULT_NUM_ROWS;
	private static final int DEFAULT_NUM_COLS;
	private static final int DEFAULT_NUM_PADDED_ROWS;
	private static final LinkedList<BlockType> PIECE_LIST;
	private static final RandomizationSystem DEFAULT_RANDOMIZATION_SYSTEM;
	private static final LevelUpSystem DEFAULT_LEVEL_UP_SYSTEM;
	private static final LockSystem DEFAULT_LOCK_SYSTEM;
	
	static
	{
		GRAVITY = new Vector(0, -1);
		DEFAULT_NUM_ROWS = 20;
		DEFAULT_NUM_COLS = 10;
		DEFAULT_NUM_PADDED_ROWS = 3;
		
		PIECE_LIST = new LinkedList<BlockType>();
		PIECE_LIST.add(BlockType.I);
		PIECE_LIST.add(BlockType.J);
		PIECE_LIST.add(BlockType.L);
		PIECE_LIST.add(BlockType.O);
		PIECE_LIST.add(BlockType.S);
		PIECE_LIST.add(BlockType.T);
		PIECE_LIST.add(BlockType.Z);
		
		DEFAULT_RANDOMIZATION_SYSTEM = new SinglePieceRNG();
		DEFAULT_LEVEL_UP_SYSTEM = new FixedLevelUp(10);
		DEFAULT_LOCK_SYSTEM = new StepReset(0);
	}
	
	private PlayField playField;
	
	private ScoringSystem scoring;
	private RandomizationSystem randomizer;
	private LevelUpSystem levelUpSystem;
	private LockSystem lockSystem;
	
	private Tetromino falling;
	private BlockType holding;
	private BlockType next;
	
	private int numLines;
	private int level;
	
	private boolean isGameOver;
	private boolean canHold;
	
	public Tetris(int numRows, int numCols, int numPaddedRows, RandomizationSystem randomizer, LevelUpSystem levelUpSystem, LockSystem lockSystem)
	{
		playField = new PlayField(numRows, numCols, numPaddedRows);
		
		this.scoring = new ScoringSystem();		
		this.randomizer = randomizer;
		this.levelUpSystem = levelUpSystem;
		this.lockSystem = lockSystem;
		
		falling = null;
		holding = null;
		next = randomizer.getRandomType();
		
		numLines = 0;
		level = 0;
		
		isGameOver = false;
		canHold = false;
	}
	
	public Tetris(int numRows, int numCols)
	{
		this(numRows, numCols,
				DEFAULT_NUM_PADDED_ROWS,
				DEFAULT_RANDOMIZATION_SYSTEM,
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
		isGameOver |= playField.collidesWith(falling);
	}
	
	public boolean isGameOver()
	{
		return isGameOver;
	}
	
	public String toString()
	{
		String str = "";
		str += playField;
		return str;
	}
	
	public int getNumRows()
	{
		return playField.getNumRows();
	}
	
	public int getNumCols()
	{
		return playField.getNumCols();
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
			loadNextPiece();
		step();
		removeFilledRows();
	}
	
	public void step()
	{
		if(moveFalling(GRAVITY) == true)
			lockSystem.reset();
		else
			advanceLock();
	}
	
	private void advanceLock()
	{
		lockSystem.advance();
		if(lockSystem.isLocked())
		{
			lockFalling();
			lockSystem.reset();
		}
	}
	
	public void perform(Action action)
	{
		if(action.perform(this) == true)
			lockSystem.update();
	}
	
	public void removeFilledRows()
	{
		int numFilledRows = playField.getNumFilledRows();
		playField.removeFilledRows();
		scoring.score(numFilledRows, level);
		numLines += numFilledRows;
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
		BlockType current = next;
		next = randomizer.getRandomType();
		return Tetromino.getPiece(current);
	}
	
	public BlockType getNextType()
	{
		return next;
	}
	
	public BlockType getHoldingType()
	{
		return holding;
	}
	
	private boolean emplace(Polyomino poly)
	{
		return playField.emplace(poly);
	}
	
	public boolean rotateFallingCCW()
	{
		if(falling != null)
		{
			Tetromino rotated = falling.rotateCCW();
			falling = playField.collidesWith(rotated) ? falling : rotated;
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
			falling = playField.collidesWith(rotated) ? falling : rotated;
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
			falling = playField.collidesWith(moved) ? falling : moved;
			return falling == moved;
		}
		else
			return false;
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
			tetro = playField.collidesWith(projection) ? tetro : projection;
		} while(tetro == projection);
		return tetro;
	}
	
	public Tetromino getGhostProjection()
	{
		Tetromino ghost = falling.changeType(BlockType.GHOST);
		return getProjection(ghost, GRAVITY);
	}
	
	public PlayField getPlayField()
	{
		return playField;
	}
	
	public Polyomino getFalling()
	{
		return falling;
	}
	
	public long getScore()
	{
		return scoring.getCurrentScore();
	}
	
	public long getHighScore()
	{
		return ScoringSystem.getHighScore();
	}
	
	public int getLevel()
	{
		return level;
	}
}
