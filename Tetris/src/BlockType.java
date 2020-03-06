import java.awt.Color;
import java.util.HashMap;

public enum BlockType
{
	I,
	J,
	L,
	O,
	S,
	T,
	Z,
	NULL,
	wall,
	projection;
	
	private static HashMap<BlockType, Color> colorMap;
	
	static
	{
		colorMap = new HashMap<BlockType, Color>();
		colorMap.put(I, Color.CYAN);
		colorMap.put(J, Color.BLUE);
		colorMap.put(L, Color.ORANGE);
		colorMap.put(O, Color.YELLOW);
		colorMap.put(S, Color.GREEN);
		colorMap.put(T, Color.MAGENTA);
		colorMap.put(Z, Color.RED);
		colorMap.put(NULL, Color.BLACK);
		colorMap.put(wall, Color.GRAY);
		colorMap.put(projection, new Color(255, 253, 208));
	}
	
	public static Color getColor(BlockType key)
	{
		return colorMap.get(key);
	}
}
