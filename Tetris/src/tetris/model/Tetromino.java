package tetris.model;
import java.awt.Color;
import java.util.Iterator;

import tetris.util.Point;
import tetris.util.Vector;

public class Tetromino extends Polyomino
{
	public static Tetromino I, J, L, O, S, T, Z;
	public static Color I_COLOR, J_COLOR, L_COLOR, O_COLOR, S_COLOR, T_COLOR, Z_COLOR;
	
	static
	{
		I_COLOR = Color.CYAN;
		J_COLOR = Color.BLUE;
		L_COLOR = Color.ORANGE;
		O_COLOR = Color.YELLOW;
		S_COLOR = Color.GREEN;
		T_COLOR = Color.MAGENTA;
		Z_COLOR = Color.RED;
		
		Block pivot;
		Vector[] relativePoints;
		Rotation state0, state1, state2, state3;
		
		//constructs rotation pattern for I piece		
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector(-2,  0);
		relativePoints[1] = new Vector(-1,  0);
		relativePoints[2] = new Vector(+1,  0);
		state0 = new Rotation(relativePoints);
		
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector( 0, +2);
		relativePoints[1] = new Vector( 0, +1);
		relativePoints[2] = new Vector( 0, -1);
		state1 = new Rotation(relativePoints);
		
		state0.CCW = state1;
		state0.CW  = state1;
		
		state1.CCW = state0;
		state1.CW  = state0;		
		
		//constructs pivot for I piece
		pivot = new Block(new Point(0, 0), BlockType.I);
		
		//constructs I piece
		I = new Tetromino(pivot, state0, BlockType.I);
		
		//constructs rotation pattern for J piece
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector(-1,  0);
		relativePoints[1] = new Vector(+1,  0);
		relativePoints[2] = new Vector(+1, -1);
		state0 = new Rotation(relativePoints);
		
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector( 0, +1);
		relativePoints[1] = new Vector( 0, -1);
		relativePoints[2] = new Vector(-1, -1);
		state1 = new Rotation(relativePoints);
		
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector(+1,  0);
		relativePoints[1] = new Vector(-1,  0);
		relativePoints[2] = new Vector(-1, +1);
		state2 = new Rotation(relativePoints);
		
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector( 0, -1);
		relativePoints[1] = new Vector( 0, +1);
		relativePoints[2] = new Vector(+1, +1);
		state3 = new Rotation(relativePoints);
		
		state0.CCW = state1;
		state0.CW  = state3;
		
		state1.CCW = state2;
		state1.CW  = state0;	
		
		state2.CCW = state3;
		state2.CW  = state1;
		
		state3.CCW = state0;
		state3.CW  = state2;
		
		//constructs pivot for J piece
		pivot = new Block(new Point(0, 0), BlockType.J);
				
		//constructs J piece
		J = new Tetromino(pivot, state0, BlockType.J);
		
		//constructs rotation pattern for L piece
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector(-1,  0);
		relativePoints[1] = new Vector(+1,  0);
		relativePoints[2] = new Vector(-1, -1);
		state0 = new Rotation(relativePoints);
		
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector( 0, +1);
		relativePoints[1] = new Vector( 0, -1);
		relativePoints[2] = new Vector(-1, +1);
		state1 = new Rotation(relativePoints);
		
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector(+1,  0);
		relativePoints[1] = new Vector(-1,  0);
		relativePoints[2] = new Vector(+1, +1);
		state2 = new Rotation(relativePoints);
		
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector( 0, -1);
		relativePoints[1] = new Vector( 0, +1);
		relativePoints[2] = new Vector(+1, -1);
		state3 = new Rotation(relativePoints);
		
		state0.CCW = state1;
		state0.CW  = state3;
		
		state1.CCW = state2;
		state1.CW  = state0;	
		
		state2.CCW = state3;
		state2.CW  = state1;
		
		state3.CCW = state0;
		state3.CW  = state2;
		
		//constructs pivot for L piece
		pivot = new Block(new Point(0, 0), BlockType.L);
				
		//constructs L piece
		L = new Tetromino(pivot, state0, BlockType.L);
		
		//constructs rotation pattern for O piece
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector(-1,  0);
		relativePoints[1] = new Vector(-1, +1);
		relativePoints[2] = new Vector( 0, +1);
		state0 = new Rotation(relativePoints);
		
		state0.CCW = state0;
		state0.CW  = state0;
		
		//constructs pivot for O piece
		pivot = new Block(new Point(0, 0), BlockType.O);
		
		//constructs O piece
		O = new Tetromino(pivot, state0, BlockType.O);
		
		//constructs rotation pattern for S piece
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector(+1,  0);
		relativePoints[1] = new Vector(-1, -1);
		relativePoints[2] = new Vector( 0, -1);
		state0 = new Rotation(relativePoints);
		
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector( 0, +1);
		relativePoints[1] = new Vector(+1, -1);
		relativePoints[2] = new Vector(+1,  0);
		state1 = new Rotation(relativePoints);
		
		state0.CCW = state1;
		state0.CW  = state1;
		
		state1.CCW = state0;
		state1.CW  = state0;
		
		//constructs pivot for S piece
		pivot = new Block(new Point(0, 0), BlockType.S);
		
		//constructs S piece
		S = new Tetromino(pivot, state0, BlockType.S);
		
		//constructs rotation pattern for T piece
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector(-1,  0);
		relativePoints[1] = new Vector(+1,  0);
		relativePoints[2] = new Vector( 0, -1);
		state0 = new Rotation(relativePoints);
		
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector( 0, -1);
		relativePoints[1] = new Vector( 0, +1);
		relativePoints[2] = new Vector(+1,  0);
		state1 = new Rotation(relativePoints);
		
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector(+1,  0);
		relativePoints[1] = new Vector(-1,  0);
		relativePoints[2] = new Vector( 0, +1);
		state2 = new Rotation(relativePoints);
		
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector( 0, +1);
		relativePoints[1] = new Vector( 0, -1);
		relativePoints[2] = new Vector(-1,  0);
		state3 = new Rotation(relativePoints);
		
		state0.CCW = state1;
		state0.CW  = state3;
		
		state1.CCW = state2;
		state1.CW  = state0;	
		
		state2.CCW = state3;
		state2.CW  = state1;
		
		state3.CCW = state0;
		state3.CW  = state2;
		
		//constructs pivot for T piece
		pivot = new Block(new Point(0, 0), BlockType.T);
				
		//constructs T piece
		T = new Tetromino(pivot, state0, BlockType.T);
		
		//constructs rotation pattern for Z piece
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector(-1,  0);
		relativePoints[1] = new Vector( 0, -1);
		relativePoints[2] = new Vector(+1, -1);
		state0 = new Rotation(relativePoints);
		
		relativePoints = new Vector[3];
		relativePoints[0] = new Vector( 0, -1);
		relativePoints[1] = new Vector(+1,  0);
		relativePoints[2] = new Vector(+1, +1);
		state1 = new Rotation(relativePoints);
		
		state0.CCW = state1;
		state0.CW  = state1;
		
		state1.CCW = state0;
		state1.CW  = state0;
		
		//constructs pivot for Z piece
		pivot = new Block(new Point(0, 0), BlockType.Z);
		
		//constructs Z piece
		Z = new Tetromino(pivot, state0, BlockType.Z);
	}
	
	public static Tetromino getPiece(BlockType type)
	{
		if(type == null)
			return null;
		switch(type)
		{
		case I:
			return Tetromino.I;
		case J:
			return Tetromino.J;
		case L:
			return Tetromino.L;
		case O:
			return Tetromino.O;
		case S:
			return Tetromino.S;
		case T:
			return Tetromino.T;
		case Z:
			return Tetromino.Z;
		default:
			return null;
		}
	}
	
	private Rotation current;
	private BlockType type;
	
	protected Tetromino(Block pivot, Rotation current, BlockType type)
	{
		super(new Block[4], 4);
		this.current = current;
		this.type = type;
		blocks[0] = pivot;
		
		int i = 1;
		for(Vector relativePosition : current)
			blocks[i++] = getPivot().move(relativePosition);
	}
	
	protected Tetromino(Block[] blocks, Rotation current)
	{
		super(blocks, 4);
		this.current = current;
	}
	
	public Tetromino move(Vector amount)
	{
		Block[] transformed = new Block[n];
		for(int i = 0; i < n; i++)
			transformed[i] = blocks[i].move(amount);
		return new Tetromino(transformed, current);
	}
	
	public BlockType getType()
	{
		return type;
	}
	
	public Tetromino rotateCCW()
	{	
		return new Tetromino(getPivot().clone(), current.CCW, type);
	}
	
	public Tetromino rotateCW()
	{
		return new Tetromino(getPivot().clone(), current.CW, type);
	}
	
	public Tetromino changeType(BlockType newType)
	{
		Tetromino transformed = move(Vector.ZERO);
		for(Block b : transformed)
			b.type = newType;
		return transformed;
	}
}

class Rotation implements Iterable<Vector>
{
	public Vector[] relative;
	public Rotation CCW, CW;
	
	public Rotation(Vector[] vectors)
	{
		relative = vectors;
		CCW = null;
		CW = null;
	}
	
	private class RelativeIterator implements Iterator<Vector>
	{
		private int i;
		
		public RelativeIterator()
		{
			i = 0;
		}

		public boolean hasNext()
		{
			return i < relative.length;
		}

		public Vector next()
		{
			return relative[i++];
		}
	}
	
	public Iterator<Vector> iterator()
	{
		return new RelativeIterator();
	}
}
