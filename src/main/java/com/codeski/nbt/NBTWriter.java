package com.codeski.nbt;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import com.codeski.nbt.tags.NBT;

public class NBTWriter {
	private final File file;

	public NBTWriter(File file) throws FileNotFoundException {
		this.file = file;
	}

	public boolean writeJSON(NBT root) throws IOException {
		PrintWriter out = new PrintWriter(file);
		out.println("{ " + root.toJSON() + " }");
		out.close();
		return true;
	}

	public boolean writeNBT(NBT root) throws FileNotFoundException, IOException {
		DataOutputStream out = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
		out.write(root.toNBT());
		out.close();
		return true;
	}

	public boolean writeXML(NBT root) throws IOException {
		PrintWriter out = new PrintWriter(file);
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + root.toXML());
		out.close();
		return true;
	}
}
