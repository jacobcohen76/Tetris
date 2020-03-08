package tetris.model.systems;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import tetris.model.ScoringSystem;

public class SaveSystem
{
	public static void saveTo(File path) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(path.toString() + "\\data.save");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeLong(ScoringSystem.getHighScore());
		
		oos.close();
	}
	
	public static void loadFrom(File path) throws IOException
	{
		FileInputStream fis = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		ScoringSystem.setHighScore(ois.readLong());
		
		ois.close();
	}
}
