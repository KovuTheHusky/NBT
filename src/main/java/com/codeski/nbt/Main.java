package com.codeski.nbt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		File f = new File(args[0]);
		System.out.println("Start reading " + f.getAbsolutePath() + "...");
		System.out.println(new NBTReader(f).read());
		System.out.println("Finished reading " + f.getAbsolutePath() + "...");
	}
}
