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
	public static final int END = 0, BYTE = 1, SHORT = 2, INTEGER = 3, LONG = 4, FLOAT = 5, DOUBLE = 6, BYTE_ARRAY = 7, STRING = 8, LIST = 9, COMPOUND = 10, INTEGER_ARRAY = 11;
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

	public boolean writeNBT(NBT root) {
		try {
			DataOutputStream out = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean writeXML(NBT root) throws IOException {
		PrintWriter out = new PrintWriter(file);
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + root.toXML());
		out.close();
		return true;
	}
}
