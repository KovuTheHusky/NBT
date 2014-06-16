package com.codeski.nbt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.codeski.nbt.tags.NBTCompound;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		File f = new File(args[0]);
		System.out.println("Start reading " + f.getAbsolutePath() + "...");
		NBTCompound root = (NBTCompound) new NBTReader(f).read();
		System.out.println("Finished reading " + f.getAbsolutePath() + "...");
		System.out.println();
		System.out.println("{ " + root.toJSON() + " }");
		System.out.println();
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + root.toXML());
		System.out.println();
		System.out.println("Done!");
	}
}
