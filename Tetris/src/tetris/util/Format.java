package tetris.util;

public class Format
{
	public static String formatNumber(long number, int numZeros)
    {
    	return String.format("%0" + numZeros + "d", number);
    }
	
	public static String formatNumber(long number, long max, int numZeros)
    {
		if(number > max)
			return max + "";
		else
			return formatNumber(number, numZeros);
    }
	
	public static long getMax(int NUMZEROS)
    {
    	String str = "";
    	for(int i = 0; i < NUMZEROS; i++)
    		str += "9";
    	return Long.valueOf(str);
    }
}
