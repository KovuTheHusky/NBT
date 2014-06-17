package com.codeski.nbt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.codeski.nbt.tags.NBTCompound;

public class Main
{
	public static String tabs = "";
	private static final boolean debug = true;

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		if (debug) {
			System.out.println("Please specify file to read...");
			Scanner s = new Scanner(System.in);
			NBTCompound r = new NBTReader(new File(s.nextLine())).read();
			new NBTWriter(new File("test.dat")).writeNBT(r);
			new NBTWriter(new File("test.json")).writeJSON(r);
			new NBTWriter(new File("test.xml")).writeXML(r);
		} else if (args.length == 2) {
			// Read in a user specified file...
			File in = new File(args[0]);
			System.out.println("Reading " + in.getCanonicalPath() + "...");
			NBTCompound root = new NBTReader(in).read();
			// Write the file to text, JSON, or XML...
			File out = new File(args[1]);
			String extension = args[1].substring(args[1].lastIndexOf('.') + 1);
			System.out.println("Writing " + out.getCanonicalPath() + "...");
			NBTWriter nw = new NBTWriter(out);
			if (extension.equalsIgnoreCase("dat"))
				nw.writeNBT(root);
			else if (extension.equalsIgnoreCase("json"))
				nw.writeJSON(root);
			else if (extension.equalsIgnoreCase("xml"))
				nw.writeXML(root);
			else
				System.out.println("Cannot write file... Supported file extensions are dat, json, and xml.");
		} else
			System.out.println("Usage: java -jar " + new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName() + " <file-to-read> <file-to-write>");
	}
}
