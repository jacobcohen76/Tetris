import java.util.LinkedList;

public class Tetris
{
	private static final Vector GRAVITY;
	private static final int DEFAULT_NUM_ROWS;
	private static final int DEFAULT_NUM_COLS;
	private static final int DEFAULT_NUM_PADDED_ROWS;
	private static final int DEFAULT_NUM_PREVIEW_PIECES;
	private static final int DEFAULT_MIN_LOCK_STAGE;
	private static final int DEFAULT_MAX_LOCK_STAGE;
	private static final LinkedList<Tetromino> PIECE_LIST;
	
	static
	{
		GRAVITY = new Vector(0, -1);
		DEFAULT_NUM_ROWS = 20;
		DEFAULT_NUM_COLS = 10;
		DEFAULT_NUM_PADDED_ROWS = 3;
		DEFAULT_NUM_PREVIEW_PIECES = 1;
		DEFAULT_MIN_LOCK_STAGE = 0;
		DEFAULT_MAX_LOCK_STAGE = 2;
		
		PIECE_LIST = new LinkedList<Tetromino>();
		PIECE_LIST.add(Tetromino.I);
		PIECE_LIST.add(Tetromino.J);
		PIECE_LIST.add(Tetromino.L);
		PIECE_LIST.add(Tetromino.O);
		PIECE_LIST.add(Tetromino.S);
		PIECE_LIST.add(Tetromino.T);
		PIECE_LIST.add(Tetromino.Z);
	}
	
	private PlayField field;
	private Bag<Tetromino> pieceBag;
	private LinkedList<Tetromino> previewList;
	private Tetromino falling;
	private int lockStage;
	private int minLockStage;
	private int maxLockStage;
	private boolean isGameOver;
	
	public Tetris(int numRows, int numCols, int numPaddedRows, int numPreviewPieces, int minLockStage, int maxLockStage)
	{
		field = new PlayField(numRows, numCols, numPaddedRows);
		pieceBag = new Bag<Tetromino>();
		previewList = getNewPreviewList(numPreviewPieces);
		falling = null;
		
		this.minLockStage = minLockStage;
		this.maxLockStage = maxLockStage;
		lockStage = minLockStage;
		
		isGameOver = false;
	}
	
	public Tetris(int numRows, int numCols)
	{
		this(numRows, numCols, DEFAULT_NUM_PADDED_ROWS, DEFAULT_NUM_PREVIEW_PIECES, DEFAULT_MIN_LOCK_STAGE, DEFAULT_MAX_LOCK_STAGE);
	}
	
	public Tetris()
	{
		this(DEFAULT_NUM_ROWS, DEFAULT_NUM_COLS);
	}
	
	public void loadNextPiece()
	{
		setFalling(getNextPiece());
	}
	
	private void setFalling(Tetromino falling)
	{
		this.falling = center(falling);
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
		int j = getNumRows() - 1;
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
			lockStage = moveFalling(GRAVITY) ? minLockStage : ++lockStage;
			if(maxLockStage < lockStage)
			{
				lockFalling();
				lockStage = minLockStage;
			}
		}
		removeFilledRows();
	}
	
	public void removeFilledRows()
	{
		field.removeFilledRows();
	}
	
	public void lockFalling()
	{
		emplace(falling);
		falling = null;
	}
	
	private Tetromino getNextPiece()
	{
		Tetromino nextPiece = previewList.poll();
		previewList.add(getRandomPiece());
		return nextPiece;
	}
	
	private void refillBag(int n)
	{
		for(int i = 0; i < n; i++)
		{
			pieceBag.addAll(PIECE_LIST);
			pieceBag.shuffle();
		}
	}
	
	private Tetromino getRandomPiece()
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
	
	public boolean shifFallingtLeft()
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
	
	private Tetromino getProjection(Tetromino tetro, Vector dir)
	{
		Tetromino projection;
		tetro = getProjection(tetro);
		do {
			projection = tetro.move(dir);
			tetro = field.collidesWith(projection) ? tetro : projection;
		} while(tetro == projection);
		return tetro;
	}
	
	public Tetromino getFallingProjection()
	{
		return getProjection(falling, GRAVITY);
	}
	
	private LinkedList<Tetromino> getNewPreviewList(int numPreviewPieces)
	{
		LinkedList<Tetromino> previewList = new LinkedList<Tetromino>();
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
}
