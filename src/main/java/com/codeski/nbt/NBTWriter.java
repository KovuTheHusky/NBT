package com.codeski.nbt;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import com.codeski.nbt.tags.NBT;

/**
 * Class for writing NBT structures as NBT, JSON, or XML.
 */
public class NBTWriter {
	private final File file;

	public NBTWriter(File file) throws FileNotFoundException {
		this.file = file;
	}

	/**
	 * Writes the tag specified and its children as JSON text.
	 * 
	 * @throws IOException
	 */
	public void writeJSON(NBT root) throws IOException {
		PrintWriter out = new PrintWriter(file);
		out.println("{ " + root.toJSON() + " }");
		out.close();
	}

	/**
	 * Writes the tag specified and its children as NBT binary data.
	 * 
	 * @throws IOException
	 */
	public void writeNBT(NBT root) throws IOException {
		DataOutputStream out = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
		out.write(root.toNBT());
		out.close();
	}

	/**
	 * Writes the tag specified and its children as XML text.
	 * 
	 * @throws IOException
	 */
	public void writeXML(NBT root) throws IOException {
		PrintWriter out = new PrintWriter(file);
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + root.toXML());
		out.close();
	}
}
