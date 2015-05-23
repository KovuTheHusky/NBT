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
	 */
	public void writeJSON(NBT<?> root) {
		try (PrintWriter out = new PrintWriter(file)) {
			out.println("{ " + root.toJSON() + " }");
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Writes the tag specified and its children as NBT binary data.
	 */
	public void writeNBT(NBT<?> root) {
		try (DataOutputStream out = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(file)))) {
			out.write(root.toNBT());
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Writes the tag specified and its children as XML text.
	 */
	public void writeXML(NBT<?> root) {
		try (PrintWriter out = new PrintWriter(file)) {
			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + root.toXML());
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}
}
