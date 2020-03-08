package tetris.util;
import java.math.BigInteger;
import java.util.HashMap;

import tetris.model.BlockType;

public class Statistics
{
	private HashMap<BlockType, BigInteger> statsMap;
	
	public Statistics()
	{
		statsMap = new HashMap<BlockType, BigInteger>();
		statsMap.put(BlockType.I, new BigInteger("0"));
		statsMap.put(BlockType.J, new BigInteger("0"));
		statsMap.put(BlockType.L, new BigInteger("0"));
		statsMap.put(BlockType.O, new BigInteger("0"));
		statsMap.put(BlockType.S, new BigInteger("0"));
		statsMap.put(BlockType.T, new BigInteger("0"));
		statsMap.put(BlockType.Z, new BigInteger("0"));
	}
	
	private void update(BlockType key, BigInteger value)
	{
		statsMap.put(key, getStats(key).add(value));
	}
	
	public void update(BlockType key)
	{
		update(key, BigInteger.ONE);
	}
	
	public void add(Statistics stats)
	{
		update(BlockType.I, stats.getStats(BlockType.I));
		update(BlockType.J, stats.getStats(BlockType.J));
		update(BlockType.L, stats.getStats(BlockType.L));
		update(BlockType.O, stats.getStats(BlockType.O));
		update(BlockType.S, stats.getStats(BlockType.S));
		update(BlockType.T, stats.getStats(BlockType.T));
		update(BlockType.Z, stats.getStats(BlockType.Z));
	}
	
	public BigInteger getStats(BlockType key)
	{
		return statsMap.get(key);
	}
	
	private String format(BigInteger val, int n)
	{
		return String.format("%0" + n + "d", val.intValue());
	}
	
	public String format(BlockType key, int n)
	{
		return key + " - " + format(getStats(key), n);
	}
	
	public String toString(int n)
	{
		String str = "";
		str += format(BlockType.I, n) + "\n";
		str += format(BlockType.J, n) + "\n";
		str += format(BlockType.L, n) + "\n";
		str += format(BlockType.O, n) + "\n";
		str += format(BlockType.S, n) + "\n";
		str += format(BlockType.T, n) + "\n";
		str += format(BlockType.Z, n);
		return str;
	}
	
	public String toString()
	{
		return toString(3);
	}
}
